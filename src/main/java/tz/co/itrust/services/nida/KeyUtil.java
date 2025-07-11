package tz.co.itrust.services.nida;


import org.apache.commons.lang3.Validate;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.paddings.ZeroBytePadding;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.util.PrivateKeyFactory;
import org.bouncycastle.crypto.util.PublicKeyFactory;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public  class KeyUtil {
    public static AsymmetricKeyParameter loadPublicKey(InputStream is) {
        X509CertificateHolder spki = (X509CertificateHolder)readPemObject(is);

        try {
            return PublicKeyFactory.createKey(spki.getSubjectPublicKeyInfo());
        } catch (IOException var3) {
            throw new RuntimeException("Cannot create public key object based on input data", var3);
        }
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
            Validate.notNull(is, "Input data stream cannot be null", new Object[0]);
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


    public static class MyPadder extends ZeroBytePadding {
        public int addPadding(byte[] in, int inOff) {
            int added;
            for(added = in.length - inOff; inOff < in.length; ++inOff) {
                in[inOff] = (byte)added;
            }

            return added;
        }

        public int padCount(byte[] in) throws InvalidCipherTextException {
            int count = in.length;
            byte lastByte = 0;
            if (count > 0 && lastByte <= 20 && lastByte > 0) {

                for(lastByte = in[count - 1]; count > 0 && in[count - 1] == lastByte; --count) {
                }

                int processed = in.length - count;
                return lastByte == processed ? processed : 0;
            } else {
                return 0;
            }
        }
    }
}
