package tz.co.itrust.services.nida.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.encodings.PKCS1Encoding;
import org.bouncycastle.crypto.engines.RSAEngine;
import org.bouncycastle.crypto.engines.RijndaelEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.signers.RSADigestSigner;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Arrays;
import org.springframework.stereotype.Service;
import tz.co.itrust.services.nida.exceptions.CryptographyException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.Security;

/**
 * Service for cryptographic operations required by NIDA
 * 
 * Provides:
 * - RSA encryption/decryption
 * - Digital signatures
 * - Rijndael (AES-256) encryption/decryption
 * - Key generation
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CryptoService {

    private final KeyManagementService keyManagementService;
    private final SecureRandom secureRandom = new SecureRandom();

    static {
        // Add BouncyCastle provider if not already added
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }

    /**
     * Encrypt data using RSA with NIDA's public key
     */
    public byte[] encryptRSA(byte[] data) {
        try {
            AsymmetricKeyParameter publicKey = keyManagementService.getNidaPublicKey();
            
            AsymmetricBlockCipher cipher = new RSAEngine();
            cipher = new PKCS1Encoding(cipher);
            cipher.init(true, publicKey);
            
            return cipher.processBlock(data, 0, data.length);
            
        } catch (InvalidCipherTextException e) {
            log.error("RSA encryption failed", e);
            throw new CryptographyException("Failed to encrypt data with RSA", e);
        }
    }

    /**
     * Decrypt data using our private key
     */
    public byte[] decryptRSA(byte[] encryptedData) {
        try {
            AsymmetricKeyParameter privateKey = keyManagementService.getOurPrivateKey();
            
            AsymmetricBlockCipher cipher = new RSAEngine();
            cipher = new PKCS1Encoding(cipher);
            cipher.init(false, privateKey);
            
            return cipher.processBlock(encryptedData, 0, encryptedData.length);
            
        } catch (InvalidCipherTextException e) {
            log.error("RSA decryption failed", e);
            throw new CryptographyException("Failed to decrypt data with RSA", e);
        }
    }

    /**
     * Sign data digitally using our private key
     */
    public byte[] signDigitally(byte[] data) {
        try {
            AsymmetricKeyParameter privateKey = keyManagementService.getOurPrivateKey();
            
            RSADigestSigner signer = new RSADigestSigner(new SHA1Digest());
            signer.init(true, privateKey);
            signer.update(data, 0, data.length);
            
            return signer.generateSignature();
            
        } catch (Exception e) {
            log.error("Digital signing failed", e);
            throw new CryptographyException("Failed to sign data digitally", e);
        }
    }

    /**
     * Verify digital signature using NIDA's public key
     */
    public boolean verifySignature(byte[] data, byte[] signature) {
        try {
            AsymmetricKeyParameter publicKey = keyManagementService.getNidaPublicKey();
            
            RSADigestSigner signer = new RSADigestSigner(new SHA1Digest());
            signer.init(false, publicKey);
            signer.update(data, 0, data.length);
            
            return signer.verifySignature(signature);
            
        } catch (Exception e) {
            log.error("Signature verification failed", e);
            throw new CryptographyException("Failed to verify signature", e);
        }
    }

    /**
     * Encrypt data using Rijndael (AES-256) algorithm
     */
    public byte[] encryptRijndael256(String data, byte[] key, byte[] iv) {
        try {
            PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(
                new CBCBlockCipher(new RijndaelEngine(256)), 
                new CustomPadding()
            );
            
            CipherParameters params = new ParametersWithIV(new KeyParameter(key), iv);
            cipher.init(true, params);
            
            byte[] input = data.getBytes(StandardCharsets.UTF_8);
            byte[] output = new byte[cipher.getOutputSize(input.length)];
            
            int len = cipher.processBytes(input, 0, input.length, output, 0);
            cipher.doFinal(output, len);
            
            return output;
            
        } catch (Exception e) {
            log.error("Rijndael encryption failed", e);
            throw new CryptographyException("Failed to encrypt with Rijndael", e);
        }
    }

    /**
     * Decrypt data using Rijndael (AES-256) algorithm
     */
    public byte[] decryptRijndael256(byte[] encryptedData, byte[] key, byte[] iv) {
        try {
            PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(
                new CBCBlockCipher(new RijndaelEngine(256)), 
                new CustomPadding()
            );
            
            CipherParameters params = new ParametersWithIV(new KeyParameter(key), iv);
            cipher.init(false, params);
            
            byte[] output = new byte[cipher.getOutputSize(encryptedData.length)];
            int len = cipher.processBytes(encryptedData, 0, encryptedData.length, output, 0);
            cipher.doFinal(output, len);
            
            return output;
            
        } catch (Exception e) {
            log.error("Rijndael decryption failed", e);
            throw new CryptographyException("Failed to decrypt with Rijndael", e);
        }
    }

    /**
     * Generate Rijndael key from string using SHA-256
     */
    public byte[] generateRijndaelKey(String keyString) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(keyString.getBytes(StandardCharsets.UTF_8));
            return Arrays.copyOf(hash, 32); // 256 bits
            
        } catch (Exception e) {
            log.error("Key generation failed", e);
            throw new CryptographyException("Failed to generate Rijndael key", e);
        }
    }

    /**
     * Generate random string for keys and IVs
     */
    public String generateRandomString(int length) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        
        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(alphabet.length());
            sb.append(alphabet.charAt(randomIndex));
        }
        
        return sb.toString();
    }

    /**
     * Custom padding implementation for compatibility with NIDA
     */
    private static class CustomPadding extends org.bouncycastle.crypto.paddings.ZeroBytePadding {
        
        @Override
        public int addPadding(byte[] in, int inOff) {
            int added = in.length - inOff;
            while (inOff < in.length) {
                in[inOff] = (byte) added;
                inOff++;
            }
            return added;
        }

        @Override
        public int padCount(byte[] in) throws InvalidCipherTextException {
            int count = in.length;
            byte lastByte = 0;
            
            if (count > 0 && lastByte <= 20 && lastByte > 0) {
                lastByte = in[count - 1];
                while (count > 0 && in[count - 1] == lastByte) {
                    count--;
                }
                int processed = in.length - count;
                return lastByte == processed ? processed : 0;
            }
            return 0;
        }
    }
}