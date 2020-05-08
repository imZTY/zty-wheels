package com.zty.framework.util.md5;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

	private static final String KEY_MD5 = "MD5";
	 
    public static String encrypt(String content) throws NoSuchAlgorithmException {
        // System.out.println("ORGIN:"+inputStr);
        BigInteger bigInteger=null;
 
        try {
            MessageDigest md = MessageDigest.getInstance(KEY_MD5);
            byte[] inputData = content.getBytes();
            md.update(inputData);
            bigInteger = new BigInteger(md.digest());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        // System.out.println("MD5  DE:" + bigInteger.toString(16));
        return bigInteger.toString(16);
    }
 
    public static void main(String args[])
    {
        try {
             String inputStr = "123";
             encrypt(inputStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
 
    }

}
