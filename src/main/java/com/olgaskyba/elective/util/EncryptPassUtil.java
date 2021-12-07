package com.olgaskyba.elective.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptPassUtil {
    public static String encrypt(String password){
        StringBuffer hashedPassword = new StringBuffer();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] digest = messageDigest.digest();
            String hx;
            for (int i=0;i< digest.length; i++){
                hx = Integer.toHexString(0xFF & digest[i]);
                if (hx.length()==1){
                    hx = "0" +hx;
                }
                hashedPassword.append(hx);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashedPassword.toString();
    }
}
