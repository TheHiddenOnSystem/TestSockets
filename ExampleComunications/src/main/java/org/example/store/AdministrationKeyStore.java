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
        generateCertificate(pathCertificate);
    }

    /**
     * Scan path and if file exist load certificate and if not exist create file with certificate
     * @param pathCertificate path hold a file with certificate
     */
    private void generateCertificate(String pathCertificate){
        try {
            keyStore=KeyStore.getInstance(KeyStore.getDefaultType());
            CertificateFactory certificateFactory= CertificateFactory.getInstance("X.509");
            if(!new File(pathCertificate).exists()){
                new File(pathCertificate).createNewFile();
                FileInputStream fileInputStream=new FileInputStream(pathCertificate);
                keyStore.setCertificateEntry(CERTIFICATE_ALIAS,certificateFactory.generateCertificate(fileInputStream));
            }else{
                keyStore.setCertificateEntry(CERTIFICATE_ALIAS,certificateFactory.generateCertificate(new FileInputStream(pathCertificate)));
            }
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
    }
    public KeyStore getKeyStore() {
        return keyStore;
    }

}
