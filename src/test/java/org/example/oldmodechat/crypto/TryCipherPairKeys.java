package org.example.oldmodechat.crypto;

import org.junit.Assert;
import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class TryCipherPairKeys {
    final static String PLAIN_TEXT="Hello World!";
    final static String TRANSFORMATION="RSA/ECB/PKCS1Padding";
    final static String ALGORITHM="RSA";
    final static int SIZE=1024;
    @Test
    public void EncriptWithPublicKey(){
        try {
            KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance(ALGORITHM);
            keyPairGenerator.initialize(SIZE);
            KeyPair keyPair=keyPairGenerator.generateKeyPair();
            byte[] encrypText=useCryp(keyPair.getPublic(),Cipher.ENCRYPT_MODE,PLAIN_TEXT.getBytes(StandardCharsets.UTF_8));
            byte[] decrypText=useCryp(keyPair.getPrivate(), Cipher.DECRYPT_MODE,encrypText);
            Assert.assertEquals(PLAIN_TEXT,new String(decrypText,StandardCharsets.UTF_8));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void EncriptWithPrivateKey(){
        try {
            KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance(ALGORITHM);
            keyPairGenerator.initialize(SIZE);
            KeyPair keyPair=keyPairGenerator.generateKeyPair();
            byte[] encrypText=useCryp(keyPair.getPrivate(),Cipher.ENCRYPT_MODE,PLAIN_TEXT.getBytes(StandardCharsets.UTF_8));
            byte[] decrypText=useCryp(keyPair.getPublic(), Cipher.DECRYPT_MODE,encrypText);
            Assert.assertEquals(PLAIN_TEXT,new String(decrypText,StandardCharsets.UTF_8));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private byte[] useCryp(Key key,int mode,byte[] msg){
        byte[] text=null;
        try {
            Cipher cipher=Cipher.getInstance(TRANSFORMATION);
            cipher.init(mode,key);
            text=cipher.doFinal(msg);
        }catch (Exception e){
            e.printStackTrace();
        }
        return text;
    }
}
