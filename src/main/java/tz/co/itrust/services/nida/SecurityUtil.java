package tz.co.itrust.services.nida;

import lombok.extern.flogger.Flogger;
import org.bouncycastle.crypto.*;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.Security;

public class SecurityUtil {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

//		this.itrustPrivKeyPath = ((URL)Objects.requireNonNull(this.getClass().getClassLoader().getResource("IMAAN.key"))).getFile();
//		this.nidaPubKeyPath = ((URL)Objects.requireNonNull(this.getClass().getClassLoader().getResource("NIDACIGSecurity.pem"))).getFile();

    public byte[] decryptRSA(byte[] encData) {
        byte[] hexEncodedCipher = null;

        try {
            Security.addProvider(new BouncyCastleProvider());
            String initialString = "-----BEGIN RSA PRIVATE KEY-----\nMIIEowIBAAKCAQEAvB7FPcxwD1cNztQAj1BEiW+SVEqDMnlsglQRyCfqtPPFm8PD\nuKoGv3VAEkjd1hpkr7acrF8IgBH3HhH8S/qt00sBn160FkT5ps8aywKXwGl3oK/f\nJ1dLC1U/FmidZBwLxIuvO9+W3hKOFH6Qe6tb7GsrSg3FvrxRa2s5/lrJ/XAae6Bh\ne3s2jVq3hMcE1iQg0EQFGOYlQq482VKj+nel0UB5ZkfcLDatmavVae23RrTut1ul\noCrHlf1Y2m42UJKsEfJFDvrJdNr5bs/8fxN7iWN9HgQSbcIjntW1lFo3sPuEu1mP\n6k7mrrTxyPC3Rq0p6yP8JWLTcCwX6NbG6hgWkwIDAQABAoIBACepMRpzVJjXWLDn\nX2rXmGuFW0tsHjbxBKCBivIxADnAP0Q0xLnVFvEcjjKMrEBsdhfZnzR0IL2ni/sJ\n/bAjHKy0XpY0CDG9vwidF1//nvDRC4iUK68Gp2qvQUq/KZemlhCNBpfP7uXI8a1+\ndYWtPgcdHRZSywMIfv3vsF1g1L8am5PS5jAVdmhIfPFDMx06S0GT1vK4T9jbi5yx\n9QGVaDlE8INsIz59fhfl1VYP6naFGYYCULLS3U41jZNXMP8whESVYfOHYkQI4lqU\ngBS4WIYAGYZV/VCu5pj8tZfLnS1TSR+knqUamRwYSPKf2CdGKwxT6sDOkwCBinf6\nS5wFRKkCgYEA7gXH7ftxL7aLaPSMTEFSznwXb+eZONYuVi4+GNSaqTgqRG6VnQu6\n3ZuulBxxgwADe7XlgOIX89Vp+oVsEvuXnZ+JTQK0eN7KBJesP2nUWwcXxpT/Dvpe\ndO3QynhiXaXKPwsbStX/lEm0e+VAh7WX/MZRbI09ITeuCyCGaFdZwl8CgYEAylQd\nq9NF4ZpMsxT164GwdnErJXo9cTXMSGrCZXh9cSA+qEdMcSP9g/cGLmM7dT7TsCCS\npRUpq28l7Zw74htP9hlyDmTsqX5r/sy/hwh0Vai6xy8J388isyil7wJglyi8Y9Du\nZAarN142mSZRgymUdQEesMhsxdJbRgYqGTLrYE0CgYEAnLkRu5EkJdZ8VM8w0cTx\nUSREClrkeJgOSpCIMrFKZMGmkwh9Wrquf9xSRxOzah5ILNzIEOTOAK806M+RKa6f\nVhoFvb9aNXv8aKm1sMtuF8HD1e2lQ6d3KmasY6SAoEjZskkN32iosGsXe7ynkLPx\nL28ljeQxq/2Ni6YO2gUqBOECgYBiPgHCQu31o4SGmDDoz6oteLnokrhu1h6BM0V9\nG8pdObjy4NvfPAiHIVUhBRID1iPXq5lJC0OWeHvEKk5xda2X47ccAilgC4DI0gZV\nvOcwCKPYv6BdyighMiWQLmlUPHVtSi/W5d4RHnAIYTDHLVNmlMqgMFGQpJmpIKkk\nXzMnKQKBgBifqdcgxtlI8hzdoZGfVWYuoDN9hrVTz7EKmISCtlXHY0y5LlFIDPsv\n5B+d8XT5k/Z6A07o7amimZuTDvha5jLU1AmUZ9NhqTkmmdTRzghR5vg5Zletjcdd\n6oWWqwOYCk3zhhSiGuKLeJIX7syN/scyUq1drDNMHEsyU0OeWJ8D\n-----END RSA PRIVATE KEY-----\n";
            InputStream fStream = new ByteArrayInputStream(initialString.getBytes());
            AsymmetricKeyParameter privateKey = KeyUtil.loadPrivateKey(fStream);
            fStream.close();
            AsymmetricBlockCipher ei = new RSAEngine();
            AsymmetricBlockCipher e = new PKCS1Encoding(ei);
            e.init(false, privateKey);
            hexEncodedCipher = e.processBlock(encData, 0, encData.length);
        } catch (Exception var8) {
            logger.error(var8.getMessage(), var8);
        }

        return hexEncodedCipher;
    }

    public byte[] encryptRSA(byte[] data) {
        byte[] hexEncodedCipher = null;

        try {
            Security.addProvider(new BouncyCastleProvider());
            String initialString = "-----BEGIN CERTIFICATE-----\n" +
                    "MIIF2jCCBMKgAwIBAgITXwAAAfB7mshObgOoZgAAAAAB8DANBgkqhkiG9w0BAQUF\n" +
                    "ADBTMRIwEAYKCZImiZPyLGQBGRYCdHoxEjAQBgoJkiaJk/IsZAEZFgJnbzEVMBMG\n" +
                    "CgmSJomT8ixkARkWBW5pZHJuMRIwEAYDVQQDEwlOSURBU3ViQ0EwHhcNMjQxMDAz\n" +
                    "MDkxNTIyWhcNMjYxMDAzMDkxNTIyWjCBpDELMAkGA1UEBhMCVFoxDDAKBgNVBAgT\n" +
                    "A0RTTTEMMAoGA1UEBxMDRFNNMTAwLgYDVQQKEydOQVRJT05BTCBJREVOVElGSUNB\n" +
                    "VElPTiBBVVRIT1JJVFkoTklEQSkxDTALBgNVBAsTBE5JREExGDAWBgNVBAMTD05J\n" +
                    "REFDSUdTZWN1cml0eTEeMBwGCSqGSIb3DQEJARYPaW5mb0BuaWRhLmdvLnR6MIIB\n" +
                    "IjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmkFvbONSZ//kTxL5CGtV01oI\n" +
                    "5PHn8DAq5zQN5U8GTL9VjH6jGA7OXIhvZSK1OC+xes3p37evJFvQWDeRVpgm2Wan\n" +
                    "EYy8+KJ+2XZ7ElGsoRFrblaU9M4/sveLJykV6MYn7aNZ/kPMfv2GCKhyJOlQb3BC\n" +
                    "MIHYDC9ai6CfklF5u/JM1D7ZFcZ5pXoI6BFQwsz0IFn9f1GWWyAuiGFKZTErFLmY\n" +
                    "Bi5yOKamlSa9M/4TYcbzNynZ0sBygfNwoolXXTdGaJrc2Eidy9IL0sB48dUg+7yD\n" +
                    "IUDTE+2AHPfjU9f6ffQipljsyFzd/AtxzGaytia3AEwA9gryIe6qQ2tCnYrCxQID\n" +
                    "AQABo4ICUzCCAk8wHQYDVR0OBBYEFICx3djDDEVwD35653wqDutasNLFMB8GA1Ud\n" +
                    "IwQYMBaAFM/tytAMqpoZP0yz1qwtixYS5tGUMIHKBgNVHR8EgcIwgb8wgbyggbmg\n" +
                    "gbaGgbNsZGFwOi8vL0NOPU5JREFTdWJDQSxDTj1OQ0tNUzAyLENOPUNEUCxDTj1Q\n" +
                    "dWJsaWMlMjBLZXklMjBTZXJ2aWNlcyxDTj1TZXJ2aWNlcyxDTj1Db25maWd1cmF0\n" +
                    "aW9uLERDPW5pZHJuLERDPWdvLERDPXR6P2NlcnRpZmljYXRlUmV2b2NhdGlvbkxp\n" +
                    "c3Q/YmFzZT9vYmplY3RDbGFzcz1jUkxEaXN0cmlidXRpb25Qb2ludDCBvgYIKwYB\n" +
                    "BQUHAQEEgbEwga4wgasGCCsGAQUFBzAChoGebGRhcDovLy9DTj1OSURBU3ViQ0Es\n" +
                    "Q049QUlBLENOPVB1YmxpYyUyMEtleSUyMFNlcnZpY2VzLENOPVNlcnZpY2VzLENO\n" +
                    "PUNvbmZpZ3VyYXRpb24sREM9bmlkcm4sREM9Z28sREM9dHo/Y0FDZXJ0aWZpY2F0\n" +
                    "ZT9iYXNlP29iamVjdENsYXNzPWNlcnRpZmljYXRpb25BdXRob3JpdHkwDgYDVR0P\n" +
                    "AQH/BAQDAgWgMD0GCSsGAQQBgjcVBwQwMC4GJisGAQQBgjcVCIe5vneE4tIshfmX\n" +
                    "P4f8qy29qG+BPIa9k3aCncB6AgFkAgEFMBMGA1UdJQQMMAoGCCsGAQUFBwMCMBsG\n" +
                    "CSsGAQQBgjcVCgQOMAwwCgYIKwYBBQUHAwIwDQYJKoZIhvcNAQEFBQADggEBAIi2\n" +
                    "Dax1fmgIYNbi+JZ1y8kviU/PwANGKxsbT6mFFWcjNCkj+RTC1fqZN7gczeeATIsa\n" +
                    "2R+PLTpUST1KlgV6wBn4zgeDV6dRz6LY2oQv5aECm6sGnDfpbcXS/AmUkb6R4p0Y\n" +
                    "dkEoe6AeOTur9OdrLU5ljJ8GWu+t/VVbcHOdzOpPQDP0wPgdJ40HmX/dNbltWphJ\n" +
                    "AZRtO9MY1P6vQNi6e386jt/0IyW9Q3mcZ+J7gA8t7KUsdsNCrqKaKeFrk4uiTEqC\n" +
                    "JGNviBfvj6ivi1pX3N9f+WVidtDld9kHNWN4woPuFv5NyJDhuUNf4Qrr0q8HwRi4\n" +
                    "oVg+ohelA7WcK4JNwnI=\n" +
                    "-----END CERTIFICATE-----\n";
            InputStream prvKeyInpStream = new ByteArrayInputStream(initialString.getBytes());
            AsymmetricKeyParameter publicKey = Utils.loadPublicKey(prvKeyInpStream);
            prvKeyInpStream.close();
            AsymmetricBlockCipher ei = new RSAEngine();
            AsymmetricBlockCipher e = new PKCS1Encoding(ei);
            e.init(true, publicKey);
            hexEncodedCipher = e.processBlock(data, 0, data.length);
        } catch (InvalidCipherTextException | IOException var7) {
            System.out.println(var7);
            var7.printStackTrace();
        }

        return hexEncodedCipher;
    }

    public byte[] signDigitally(byte[] messageBytes) {
        try {
            String initialString = "-----BEGIN RSA PRIVATE KEY-----\nMIIEowIBAAKCAQEAvB7FPcxwD1cNztQAj1BEiW+SVEqDMnlsglQRyCfqtPPFm8PD\nuKoGv3VAEkjd1hpkr7acrF8IgBH3HhH8S/qt00sBn160FkT5ps8aywKXwGl3oK/f\nJ1dLC1U/FmidZBwLxIuvO9+W3hKOFH6Qe6tb7GsrSg3FvrxRa2s5/lrJ/XAae6Bh\ne3s2jVq3hMcE1iQg0EQFGOYlQq482VKj+nel0UB5ZkfcLDatmavVae23RrTut1ul\noCrHlf1Y2m42UJKsEfJFDvrJdNr5bs/8fxN7iWN9HgQSbcIjntW1lFo3sPuEu1mP\n6k7mrrTxyPC3Rq0p6yP8JWLTcCwX6NbG6hgWkwIDAQABAoIBACepMRpzVJjXWLDn\nX2rXmGuFW0tsHjbxBKCBivIxADnAP0Q0xLnVFvEcjjKMrEBsdhfZnzR0IL2ni/sJ\n/bAjHKy0XpY0CDG9vwidF1//nvDRC4iUK68Gp2qvQUq/KZemlhCNBpfP7uXI8a1+\ndYWtPgcdHRZSywMIfv3vsF1g1L8am5PS5jAVdmhIfPFDMx06S0GT1vK4T9jbi5yx\n9QGVaDlE8INsIz59fhfl1VYP6naFGYYCULLS3U41jZNXMP8whESVYfOHYkQI4lqU\ngBS4WIYAGYZV/VCu5pj8tZfLnS1TSR+knqUamRwYSPKf2CdGKwxT6sDOkwCBinf6\nS5wFRKkCgYEA7gXH7ftxL7aLaPSMTEFSznwXb+eZONYuVi4+GNSaqTgqRG6VnQu6\n3ZuulBxxgwADe7XlgOIX89Vp+oVsEvuXnZ+JTQK0eN7KBJesP2nUWwcXxpT/Dvpe\ndO3QynhiXaXKPwsbStX/lEm0e+VAh7WX/MZRbI09ITeuCyCGaFdZwl8CgYEAylQd\nq9NF4ZpMsxT164GwdnErJXo9cTXMSGrCZXh9cSA+qEdMcSP9g/cGLmM7dT7TsCCS\npRUpq28l7Zw74htP9hlyDmTsqX5r/sy/hwh0Vai6xy8J388isyil7wJglyi8Y9Du\nZAarN142mSZRgymUdQEesMhsxdJbRgYqGTLrYE0CgYEAnLkRu5EkJdZ8VM8w0cTx\nUSREClrkeJgOSpCIMrFKZMGmkwh9Wrquf9xSRxOzah5ILNzIEOTOAK806M+RKa6f\nVhoFvb9aNXv8aKm1sMtuF8HD1e2lQ6d3KmasY6SAoEjZskkN32iosGsXe7ynkLPx\nL28ljeQxq/2Ni6YO2gUqBOECgYBiPgHCQu31o4SGmDDoz6oteLnokrhu1h6BM0V9\nG8pdObjy4NvfPAiHIVUhBRID1iPXq5lJC0OWeHvEKk5xda2X47ccAilgC4DI0gZV\nvOcwCKPYv6BdyighMiWQLmlUPHVtSi/W5d4RHnAIYTDHLVNmlMqgMFGQpJmpIKkk\nXzMnKQKBgBifqdcgxtlI8hzdoZGfVWYuoDN9hrVTz7EKmISCtlXHY0y5LlFIDPsv\n5B+d8XT5k/Z6A07o7amimZuTDvha5jLU1AmUZ9NhqTkmmdTRzghR5vg5Zletjcdd\n6oWWqwOYCk3zhhSiGuKLeJIX7syN/scyUq1drDNMHEsyU0OeWJ8D\n-----END RSA PRIVATE KEY-----\n";
            InputStream prvKeyInpStream = new ByteArrayInputStream(initialString.getBytes());
            AsymmetricKeyParameter privKey = Utils.loadPrivateKey(prvKeyInpStream);
            RSADigestSigner signer = new RSADigestSigner(new SHA1Digest());
            signer.init(true, privKey);
            signer.update(messageBytes, 0, messageBytes.length);
            prvKeyInpStream.close();
            return signer.generateSignature();
        } catch (CryptoException | DataLengthException | IOException var6) {
            throw new RuntimeException("Cannot generate RSA signature. " + var6.getMessage(), var6);
        }
    }

    public boolean verifySignature(byte[] messageBytes, byte[] signature) {
        try {
            String initialString = "-----BEGIN CERTIFICATE-----\n" +
                    "MIIF2jCCBMKgAwIBAgITXwAAAfB7mshObgOoZgAAAAAB8DANBgkqhkiG9w0BAQUF\n" +
                    "ADBTMRIwEAYKCZImiZPyLGQBGRYCdHoxEjAQBgoJkiaJk/IsZAEZFgJnbzEVMBMG\n" +
                    "CgmSJomT8ixkARkWBW5pZHJuMRIwEAYDVQQDEwlOSURBU3ViQ0EwHhcNMjQxMDAz\n" +
                    "MDkxNTIyWhcNMjYxMDAzMDkxNTIyWjCBpDELMAkGA1UEBhMCVFoxDDAKBgNVBAgT\n" +
                    "A0RTTTEMMAoGA1UEBxMDRFNNMTAwLgYDVQQKEydOQVRJT05BTCBJREVOVElGSUNB\n" +
                    "VElPTiBBVVRIT1JJVFkoTklEQSkxDTALBgNVBAsTBE5JREExGDAWBgNVBAMTD05J\n" +
                    "REFDSUdTZWN1cml0eTEeMBwGCSqGSIb3DQEJARYPaW5mb0BuaWRhLmdvLnR6MIIB\n" +
                    "IjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmkFvbONSZ//kTxL5CGtV01oI\n" +
                    "5PHn8DAq5zQN5U8GTL9VjH6jGA7OXIhvZSK1OC+xes3p37evJFvQWDeRVpgm2Wan\n" +
                    "EYy8+KJ+2XZ7ElGsoRFrblaU9M4/sveLJykV6MYn7aNZ/kPMfv2GCKhyJOlQb3BC\n" +
                    "MIHYDC9ai6CfklF5u/JM1D7ZFcZ5pXoI6BFQwsz0IFn9f1GWWyAuiGFKZTErFLmY\n" +
                    "Bi5yOKamlSa9M/4TYcbzNynZ0sBygfNwoolXXTdGaJrc2Eidy9IL0sB48dUg+7yD\n" +
                    "IUDTE+2AHPfjU9f6ffQipljsyFzd/AtxzGaytia3AEwA9gryIe6qQ2tCnYrCxQID\n" +
                    "AQABo4ICUzCCAk8wHQYDVR0OBBYEFICx3djDDEVwD35653wqDutasNLFMB8GA1Ud\n" +
                    "IwQYMBaAFM/tytAMqpoZP0yz1qwtixYS5tGUMIHKBgNVHR8EgcIwgb8wgbyggbmg\n" +
                    "gbaGgbNsZGFwOi8vL0NOPU5JREFTdWJDQSxDTj1OQ0tNUzAyLENOPUNEUCxDTj1Q\n" +
                    "dWJsaWMlMjBLZXklMjBTZXJ2aWNlcyxDTj1TZXJ2aWNlcyxDTj1Db25maWd1cmF0\n" +
                    "aW9uLERDPW5pZHJuLERDPWdvLERDPXR6P2NlcnRpZmljYXRlUmV2b2NhdGlvbkxp\n" +
                    "c3Q/YmFzZT9vYmplY3RDbGFzcz1jUkxEaXN0cmlidXRpb25Qb2ludDCBvgYIKwYB\n" +
                    "BQUHAQEEgbEwga4wgasGCCsGAQUFBzAChoGebGRhcDovLy9DTj1OSURBU3ViQ0Es\n" +
                    "Q049QUlBLENOPVB1YmxpYyUyMEtleSUyMFNlcnZpY2VzLENOPVNlcnZpY2VzLENO\n" +
                    "PUNvbmZpZ3VyYXRpb24sREM9bmlkcm4sREM9Z28sREM9dHo/Y0FDZXJ0aWZpY2F0\n" +
                    "ZT9iYXNlP29iamVjdENsYXNzPWNlcnRpZmljYXRpb25BdXRob3JpdHkwDgYDVR0P\n" +
                    "AQH/BAQDAgWgMD0GCSsGAQQBgjcVBwQwMC4GJisGAQQBgjcVCIe5vneE4tIshfmX\n" +
                    "P4f8qy29qG+BPIa9k3aCncB6AgFkAgEFMBMGA1UdJQQMMAoGCCsGAQUFBwMCMBsG\n" +
                    "CSsGAQQBgjcVCgQOMAwwCgYIKwYBBQUHAwIwDQYJKoZIhvcNAQEFBQADggEBAIi2\n" +
                    "Dax1fmgIYNbi+JZ1y8kviU/PwANGKxsbT6mFFWcjNCkj+RTC1fqZN7gczeeATIsa\n" +
                    "2R+PLTpUST1KlgV6wBn4zgeDV6dRz6LY2oQv5aECm6sGnDfpbcXS/AmUkb6R4p0Y\n" +
                    "dkEoe6AeOTur9OdrLU5ljJ8GWu+t/VVbcHOdzOpPQDP0wPgdJ40HmX/dNbltWphJ\n" +
                    "AZRtO9MY1P6vQNi6e386jt/0IyW9Q3mcZ+J7gA8t7KUsdsNCrqKaKeFrk4uiTEqC\n" +
                    "JGNviBfvj6ivi1pX3N9f+WVidtDld9kHNWN4woPuFv5NyJDhuUNf4Qrr0q8HwRi4\n" +
                    "oVg+ohelA7WcK4JNwnI=\n" +
                    "-----END CERTIFICATE-----\n";
            InputStream pubKeyInpStream = new ByteArrayInputStream(initialString.getBytes());
            AsymmetricKeyParameter publKey = KeyUtil.loadPublicKey(pubKeyInpStream);
            RSADigestSigner signer = new RSADigestSigner(new SHA1Digest());
            signer.init(false, publKey);
            signer.update(messageBytes, 0, messageBytes.length);
            boolean isValidSignature = signer.verifySignature(signature);
            pubKeyInpStream.close();
            return isValidSignature;
        } catch (Exception var8) {
            var8.printStackTrace();
            throw new RuntimeException(var8.getMessage());
        }
    }

    public byte[] encryptRijndael256(String data, byte[] key, byte[] iv) {
        try {
            logger.error("encryiping ==================");
            logger.error(data);
            PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new RijndaelEngine(256)), new KeyUtil.MyPadder());
            CipherParameters ivAndKey = new ParametersWithIV(new KeyParameter(key), iv);
            cipher.init(true, ivAndKey);
            byte[] input = data.getBytes();
            byte[] cipherText = new byte[cipher.getOutputSize(input.length)];
            int cipherLength = cipher.processBytes(input, 0, input.length, cipherText, 0);
            cipher.doFinal(cipherText, cipherLength);
            return cipherText;
        } catch (Exception var9) {
            throw new RuntimeException(var9);
        }
    }

    public byte[] decryptRijndael256(byte[] encData, byte[] key, byte[] iv) {
        try {
            PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new RijndaelEngine(256)), new KeyUtil.MyPadder());
            CipherParameters ivAndKey = new ParametersWithIV(new KeyParameter(key), iv);
            cipher.init(false, ivAndKey);
            byte[] plainText = new byte[cipher.getOutputSize(encData.length)];
            int cipherLength = cipher.processBytes(encData, 0, encData.length, plainText, 0);
            cipher.doFinal(plainText, cipherLength);
            return plainText;
        } catch (Exception var8) {
            throw new RuntimeException(var8);
        }
    }

    public byte[] generateRijdaelKey(String key) {
        Object var2 = null;

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] sha256Bytes = digest.digest(key.getBytes(StandardCharsets.UTF_8));
            int keySize = 32;
            return Arrays.copyOf(sha256Bytes, keySize);
        } catch (Exception var6) {
            logger.error(var6.getMessage(),var6);
            throw new RuntimeException(var6.getMessage());
        }
    }

    public String generateRandomString(int length) {
        String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom RANDOM = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);

        for(int i = 0; i < length; ++i) {
            int randomIndex = RANDOM.nextInt(ALPHABET.length());
            sb.append(ALPHABET.charAt(randomIndex));
        }

        return sb.toString();
    }
}
