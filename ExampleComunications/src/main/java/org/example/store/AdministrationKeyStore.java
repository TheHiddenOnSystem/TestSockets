package org.example.store;

import java.io.*;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

/**
 * This class represent an object to administration KeyStore and Configuration
 */
public class AdministrationKeyStore {
    private KeyStore keyStore;
    public static String CERTIFICATE_ALIAS="default_certificate";


    /**
     *
     * @param pathCertificate path hold a file with certificate
     */
    public AdministrationKeyStore(String pathCertificate) {

    }


    public KeyStore getKeyStore() {
        return keyStore;
    }

}
