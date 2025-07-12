package tz.co.itrust.services.nida.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.util.PrivateKeyFactory;
import org.bouncycastle.crypto.util.PublicKeyFactory;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import tz.co.itrust.services.nida.config.NidaProperties;
import tz.co.itrust.services.nida.exceptions.KeyManagementException;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Service for managing cryptographic keys
 * 
 * Loads and provides:
 * - Our private key for signing and decryption
 * - NIDA's public key for encryption and signature verification
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class KeyManagementService {

    private final NidaProperties nidaProperties;
    private final ResourceLoader resourceLoader;

    private AsymmetricKeyParameter ourPrivateKey;
    private AsymmetricKeyParameter nidaPublicKey;

    @PostConstruct
    public void loadKeys() {
        log.info("Loading cryptographic keys...");
        try {
            loadOurPrivateKey();
            loadNidaPublicKey();
            log.info("All cryptographic keys loaded successfully");
        } catch (Exception e) {
            log.error("Failed to load cryptographic keys", e);
            throw new KeyManagementException("Failed to initialize key management", e);
        }
    }

    public AsymmetricKeyParameter getOurPrivateKey() {
        if (ourPrivateKey == null) {
            throw new KeyManagementException("Private key not loaded");
        }
        return ourPrivateKey;
    }

    public AsymmetricKeyParameter getNidaPublicKey() {
        if (nidaPublicKey == null) {
            throw new KeyManagementException("NIDA public key not loaded");
        }
        return nidaPublicKey;
    }

    private void loadOurPrivateKey() {
        try {
            String keyPath = nidaProperties.getSecurity().getPrivateKeyPath();
            log.debug("Loading private key from: {}", keyPath);
            
            Resource resource = resourceLoader.getResource(keyPath);
            if (!resource.exists()) {
                throw new KeyManagementException("Private key file not found: " + keyPath);
            }

            try (InputStreamReader reader = new InputStreamReader(resource.getInputStream());
                 PEMParser pemParser = new PEMParser(reader)) {
                
                Object keyObject = pemParser.readObject();
                if (keyObject == null) {
                    throw new KeyManagementException("No PEM object found in private key file");
                }

                if (keyObject instanceof PEMKeyPair) {
                    PEMKeyPair keyPair = (PEMKeyPair) keyObject;
                    PrivateKeyInfo privateKeyInfo = keyPair.getPrivateKeyInfo();
                    ourPrivateKey = PrivateKeyFactory.createKey(privateKeyInfo);
                } else if (keyObject instanceof PrivateKeyInfo) {
                    ourPrivateKey = PrivateKeyFactory.createKey((PrivateKeyInfo) keyObject);
                } else {
                    throw new KeyManagementException("Unsupported private key format: " + keyObject.getClass());
                }
                
                log.info("Private key loaded successfully");
            }
        } catch (IOException e) {
            throw new KeyManagementException("Failed to load private key", e);
        }
    }

    private void loadNidaPublicKey() {
        try {
            String certPath = nidaProperties.getSecurity().getCertificatePath();
            log.debug("Loading NIDA public key from certificate: {}", certPath);
            
            Resource resource = resourceLoader.getResource(certPath);
            if (!resource.exists()) {
                throw new KeyManagementException("Certificate file not found: " + certPath);
            }

            try (InputStreamReader reader = new InputStreamReader(resource.getInputStream());
                 PEMParser pemParser = new PEMParser(reader)) {
                
                Object certObject = pemParser.readObject();
                if (certObject == null) {
                    throw new KeyManagementException("No PEM object found in certificate file");
                }

                if (certObject instanceof X509CertificateHolder) {
                    X509CertificateHolder certHolder = (X509CertificateHolder) certObject;
                    nidaPublicKey = PublicKeyFactory.createKey(certHolder.getSubjectPublicKeyInfo());
                } else {
                    throw new KeyManagementException("Unsupported certificate format: " + certObject.getClass());
                }
                
                log.info("NIDA public key loaded successfully from certificate");
            }
        } catch (IOException e) {
            throw new KeyManagementException("Failed to load NIDA public key", e);
        }
    }
}