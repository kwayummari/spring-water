package tz.co.itrust.services.nida;

import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.encodings.PKCS1Encoding;
import org.bouncycastle.crypto.engines.RSAEngine;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.signers.RSADigestSigner;
import org.bouncycastle.crypto.util.PrivateKeyFactory;
import org.bouncycastle.crypto.util.PublicKeyFactory;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.springframework.stereotype.Component;

import java.io.*;
import java.security.SecureRandom;
import java.security.Security;

@Component
public class Utils {
   public static AsymmetricKeyParameter loadPublicKey(InputStream is) {
      X509CertificateHolder certHolder = (X509CertificateHolder)readPemObject(is);

      try {
         AsymmetricKeyParameter publicKey = PublicKeyFactory.createKey(certHolder.getSubjectPublicKeyInfo());
         return publicKey;
      } catch (IOException var3) {
         throw new RuntimeException("Cannot create public key object based on input data", var3);
      }
   }

   public static String generateRandomString(int length) {
      String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789";
      SecureRandom RANDOM = new SecureRandom();
      StringBuilder sb = new StringBuilder(length);

      for(int i = 0; i < length; ++i) {
         int randomIndex = RANDOM.nextInt(ALPHABET.length());
         sb.append(ALPHABET.charAt(randomIndex));
      }

      return sb.toString();
   }

   public static AsymmetricKeyParameter loadPrivateKey(InputStream is) {
      PEMKeyPair keyPair = (PEMKeyPair)readPemObject(is);
      PrivateKeyInfo pki = keyPair.getPrivateKeyInfo();

      try {
         return PrivateKeyFactory.createKey(pki);
      } catch (IOException var4) {
         throw new RuntimeException("Cannot create private key object based on input data", var4);
      }
   }

   private static Object readPemObject(InputStream is) {
      try {
         InputStreamReader isr = new InputStreamReader(is, "UTF-8");
         PEMParser pemParser = new PEMParser(isr);
         Object obj = pemParser.readObject();
         if (obj == null) {
            throw new Exception("No PEM object found");
         } else {
            return obj;
         }
      } catch (Throwable var4) {
         throw new RuntimeException("Cannot read PEM object from input data", var4);
      }
   }

   public byte[] signDigitally(byte[] messageBytes, String keyPath) {
      try {
         FileInputStream prvKeyInpStream = new FileInputStream(keyPath);
         AsymmetricKeyParameter privKey = loadPrivateKey(prvKeyInpStream);
         RSADigestSigner signer = new RSADigestSigner(new SHA1Digest());
         signer.init(true, privKey);
         signer.update(messageBytes, 0, messageBytes.length);
         prvKeyInpStream.close();
         return signer.generateSignature();
      } catch (CryptoException | DataLengthException | IOException var6) {
         throw new RuntimeException("Cannot generate RSA signature. " + var6.getMessage(), var6);
      }
   }

   public byte[] encryptRSA(byte[] data, String keyFilePath) {
      byte[] hexEncodedCipher = null;

      try {
         Security.addProvider(new BouncyCastleProvider());
         FileInputStream fStream = new FileInputStream(new File(keyFilePath));
         AsymmetricKeyParameter publicKey = loadPublicKey(fStream);
         fStream.close();
         AsymmetricBlockCipher e = new RSAEngine();
         AsymmetricBlockCipher eC = new PKCS1Encoding(e);
         eC.init(true, publicKey);
         hexEncodedCipher = eC.processBlock(data, 0, data.length);
      } catch (InvalidCipherTextException | IOException var7) {
         System.out.println(var7);
         var7.printStackTrace();
      }

      return hexEncodedCipher;
   }
}
