package com.aes.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
 
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

@Service
public class MyService {
	private static final String SECRET_KEY = "CaNtBrEaK@E$";
	 
    private static SecretKeySpec secretKey;
    private static byte[] key;
 
    public static void setKey(String myKey)
    {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        }
        catch (NoSuchAlgorithmException e) {
        	System.out.println("Error 1"+e);
        }
        catch (UnsupportedEncodingException e) {
        	System.out.println("Error 2"+e);
        }
    }
    public static void main(String[] args) {
		System.out.println(decrypt("SPxbT5+uROtimz1WTSNZgA=="));
	}
 
    public static String encrypt(String strToEncrypt)
    {
        try
        {
            setKey(SECRET_KEY);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        }
        catch (Exception e)
        {
        	System.out.println("Error 3"+e);
        }
        return null;
    }
 
    
    public static String decrypt(String strToDecrypt)
    {
        try
        {
            setKey(SECRET_KEY);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        }
        catch (Exception e)
        {
        	System.out.println("Error 4"+e);
        }
        return "";
    }
}
