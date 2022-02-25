package org.example.store;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

/**
 * This a configuration SSL to generate objects
 *
 * Reference Example properties:
 *
 * javax.net.ssl.trustStore="example"
 * javax.netssl.trustStorePassword="example"
 *
 */
public class FactorySecurity {


    private KeyStore keyStore;
    private SSLContext sslContext;
    private KeyPairGenerator keyPairGenerator;


    /**
     *
     * @param keyStore keystore certificates server
     * @param passwd password keystore
     * @param model SSL type mode
     * @param keyPairGenerator can null
     */
    public FactorySecurity(KeyStore keyStore,String passwd,String model,KeyPairGenerator keyPairGenerator) {
        this.keyStore = keyStore;
        this.keyPairGenerator=keyPairGenerator;
        sslContext=sslContext(keyStore,passwd,model,null);
    }


    public KeyStore getKeyStore() {
        return keyStore;
    }

    public SSLContext getSslContext() {
        return sslContext;
    }

    public KeyPairGenerator getKeyPairGenerator() {
        return keyPairGenerator;
    }


    /**
     * Generate SSLContext to use connections
     * @param keyStore keyStore if load key
     * @param passwd passwd keyStore
     * @param modeSSL type algorithm SSLContext
     * @param secureRandom can null
     * @return SSLContext with init()
     */
    public static SSLContext sslContext(KeyStore keyStore,String passwd,String modeSSL,SecureRandom secureRandom){
        SSLContext sslContext=null;
        try {
            final TrustManagerFactory trustManagerFactory=TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            final KeyManagerFactory keyManagerFactory=KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(keyStore,passwd.toCharArray());
            sslContext=SSLContext.getInstance(modeSSL);
            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(),secureRandom);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        return sslContext;
    }


    public static KeyPair genKeyPair(String algorith){
        //ProcessBuilder processBuilder=new ProcessBuilder("cmd","/c", "dir");
        KeyPair keyPair=null;
        try {
            KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance(algorith);
            keyPairGenerator.initialize(2048);
            keyPair=keyPairGenerator.genKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return keyPair;
    }

    public static Certificate genCertificate(PrivateKey privateKey){
        Certificate certificate=null;
        try {
            CertificateFactory certificateFactory=CertificateFactory.getInstance("X.509");
            certificate=certificateFactory.generateCertificate(new ByteArrayInputStream(privateKey.getEncoded()));
        } catch (CertificateException e) {
            e.printStackTrace();
        }
        return certificate;
    }
}
