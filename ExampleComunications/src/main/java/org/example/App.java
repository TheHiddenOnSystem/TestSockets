package org.example;


import com.sun.security.auth.callback.TextCallbackHandler;

import jdk.security.jarsigner.JarSigner;

import javax.net.ssl.TrustManagerFactory;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.*;
import java.security.cert.Certificate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App 
{
    public static final String pathKeyStore="keypair/client.keystore";
    public static final String PASSWD="123456";
    public static final String GENERATOR_ALGORITHM="DSA";
    public static final String GENERATOR_CERTIFICATE="x.509";
    public static final String GENERATOR_SIGNATURE="SHA256withDSA";
    public static KeyStore loadKeyStoreWithCustomConfig(){
        KeyStore keyStore=null;
        try {

            keyStore=KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(new KeyStore.LoadStoreParameter() {
                @Override
                public KeyStore.ProtectionParameter getProtectionParameter() {
                    final TextCallbackHandler textCallbackHandler=new TextCallbackHandler();
                    final PasswordCallback passwordCallback=new PasswordCallback("No tengas miedo",false);
                    try {
                        textCallbackHandler.handle(new Callback[]{
                                passwordCallback
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (UnsupportedCallbackException e) {
                        e.printStackTrace();
                    }
                    return new KeyStore.CallbackHandlerProtection(textCallbackHandler);
                }
            });

        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return keyStore;
    }
    public static KeyStore loadFileKeyStoreTries(FileInputStream path){
        KeyStore keyStore=null;
        try {
            keyStore=KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(path,PASSWD.toCharArray());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return keyStore;
    }
    public static KeyPair makekeyPair(){
        KeyPair keyPair=null;
        try {
            KeyPairGenerator keyGenerator=KeyPairGenerator.getInstance(GENERATOR_ALGORITHM);
            keyGenerator.initialize(1024);
            keyPair=keyGenerator.genKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return keyPair;
    }
    public static void main( String[] args ) throws FileNotFoundException {
        KeyStore keyStore=loadFileKeyStoreTries(new FileInputStream(pathKeyStore));


        p1(keyStore);
        KeyPair keyPair=makekeyPair();
        System.out.println(keyPair);
        System.out.println(keyPair.getPublic());
        System.out.println(keyPair.getPrivate());
        Signature signature1=null;
        Signature signature2=null;
        byte[] sign1=null;

        boolean verificacion=false;
        try {
            signature1=Signature.getInstance(GENERATOR_SIGNATURE);
            signature1.initSign(keyPair.getPrivate());
            signature1.update(PASSWD.getBytes(StandardCharsets.UTF_8));
            sign1=signature1.sign();

            signature2=Signature.getInstance(GENERATOR_SIGNATURE);
            signature2.initVerify(keyPair.getPublic());
            signature2.update(PASSWD.getBytes(StandardCharsets.UTF_8));
            verificacion=signature2.verify(sign1);


            CertificateFactory certificateFactory=CertificateFactory.getInstance("X.509");
            PKIXParameters params=new PKIXParameters(keyStore);
            
            CertPathBuilder cpb = CertPathBuilder.getInstance("PKIX");
            PKIXRevocationChecker rc = (PKIXRevocationChecker)cpb.getRevocationChecker();
            rc.setOptions(EnumSet.of(PKIXRevocationChecker.Option.PREFER_CRLS));
            params.addCertPathChecker(rc);
            CertPathBuilderResult cpbr = cpb.build(params);




        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (CertPathBuilderException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        System.out.println(signature1);
        System.out.println(signature2);
        System.out.println(new String(sign1,StandardCharsets.UTF_8));
        System.out.println(verificacion);
        Certificate certificate=null;







    }
    public static void p1(KeyStore keyStore){

        System.out.println(keyStore);
        Enumeration<String> aliasEnum= null;
        try {
            aliasEnum = keyStore.aliases();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }

        int count =1;
        while (aliasEnum.hasMoreElements()){
            final String alias= aliasEnum.nextElement();
            System.out.println("Numero de Alias: "+count);
            try {

                Certificate certificate=keyStore.getCertificate(alias);
                System.out.println(new String(Base64.getEncoder().encode(certificate.getEncoded())));
            } catch (KeyStoreException e) {
                e.printStackTrace();
            } catch (CertificateEncodingException e) {
                e.printStackTrace();
            }
            count++;
        }
    }

}
