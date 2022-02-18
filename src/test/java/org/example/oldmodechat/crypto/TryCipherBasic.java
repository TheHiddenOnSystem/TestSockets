package org.example.oldmodechat.crypto;

import org.junit.Assert;
import org.junit.Test;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class TryCipherBasic {
    final static String PLAIN_TEXT="Hello World!";
    final static String TRANSFORMATION="AES/ECB/PKCS5Padding";
    final static String ALGORITHM="AES";
    final static int SIZE=128;

    @Test
    public void InitBasic(){
        try {
            KeyGenerator keyGenerator=KeyGenerator.getInstance(ALGORITHM);

            SecretKey key= keyGenerator.generateKey();
            byte[] textEncript=useCryp(key,PLAIN_TEXT.getBytes(),Cipher.ENCRYPT_MODE);
            System.out.println(textEncript);
            byte[] textplain=useCryp(key,textEncript,Cipher.DECRYPT_MODE);
            Assert.assertEquals(PLAIN_TEXT,new String(textplain));

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private byte[] useCryp(SecretKey key,byte[] msg,int mode){
        byte[] returMessage=null;
        try {
            Cipher cipher=Cipher.getInstance(TRANSFORMATION);
            cipher.init(mode,key);
            returMessage=cipher.doFinal(msg);
        }catch (Exception e){
            e.printStackTrace();
        }

        return returMessage;
    }


}
