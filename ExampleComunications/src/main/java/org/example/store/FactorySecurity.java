package org.example.store;

import java.io.*;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class FactorySecurity {


    public static KeyPair genKeyPair(String algorith){
        //ProcessBuilder processBuilder=new ProcessBuilder("cmd","/c", "dir");
        KeyPair keyPair=null;
        try {
            KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance(algorith);
            keyPair=keyPairGenerator.genKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return keyPair;
    }

    public static X509Certificate makeCertificate(String path){
        X509Certificate certificate=null;
        final File file=new File(path);

        try {
            final FileInputStream fileInputStream=new FileInputStream(path);
            CertificateFactory certificateFactory=CertificateFactory.getInstance("X.509");
            certificate= (X509Certificate) certificateFactory.generateCertificate(fileInputStream);
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return certificate;
    }

    public static void write(String path,byte[] msg){
        File file=new File(path);
        if(file.exists()){
            file.delete();
        }
        try {
            file.createNewFile();
            FileWriter writer=new FileWriter(file);
            for(byte a:msg){
                writer.write(a);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        KeyPair keyPair=genKeyPair("RSA");
        write("privatekey",keyPair.getPrivate().getEncoded());
        write("publicKey",keyPair.getPublic().getEncoded());
        
        System.out.println(new String(keyPair.getPrivate().getEncoded()));
        System.out.println(new String(keyPair.getPublic().getEncoded()));

    }
}
