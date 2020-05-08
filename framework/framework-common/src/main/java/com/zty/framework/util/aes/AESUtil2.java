package com.zty.framework.util.aes;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 版本2
 * @author tianyi
 * @date 2019-07-14 19:29
 */
public class AESUtil2 {

    /**
     * 加密
     *
     * @param content  需要加密的内容
     * @param password 加密密码
     * @return
     */
    public static String encrypt(String content, String password) {
        KeyGenerator kgen = null;
        try {
            kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] byteContent = content.getBytes("utf-8");
            byte[] result = cipher.doFinal(byteContent);

             String rt = Base64.encodeBase64String(result); //Version1
//            String rt = new String(result);
            return clean(rt);//通过Base64转码返回

        } catch (NoSuchAlgorithmException | InvalidKeyException
                | NoSuchPaddingException | BadPaddingException
                | UnsupportedEncodingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param content  待解密内容
     * @param password 解密密钥
     * @return
     */
    public static String decrypt(byte[] content, String password) {
        KeyGenerator kgen = null;
        try {
            kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化

            content = Base64.decodeBase64(fill(new String(content)));

            byte[] result = cipher.doFinal(content);
            return new String(result,"utf-8"); // 解密
        } catch (NoSuchAlgorithmException | BadPaddingException
                | IllegalBlockSizeException | NoSuchPaddingException
                | InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String content = "这是明文";
        String password = "woaiguangzhongyi";
        //加密
        System.out.println("加密前：" + content);
        byte[] encryptResult = encrypt(content, password).getBytes();
//        String token = Base64.encodeBase64String(encryptResult);
        System.out.println("加密后：" + new String(encryptResult,"utf-8"));
//        System.out.println("加密后：" + token);
        //解密
        // byte[] decryptResult = decrypt(encryptResult, password);
        String decryptResult = decrypt(encryptResult, password);
        System.out.println("解密后：" + new String(decryptResult));
    }

    /**
     * 清除末尾多余的"="
     * @param key
     * @return
     */
    private static String clean(String key){
        return key.substring(0,key.indexOf('='));  //传输给前端的时候，会丢失这些"="，这里统一处理，使它们必然丢失
    }

    /**
     * 填补末尾少掉的"="
     * @param key
     * @return
     */
    private static String fill(String key){
        int times = 3 - key.length() % 3;  //AES以三个字节为一组，缺少的位补"="
        if (times == 3){
            return key;
        }
        for (int i = 0; i < times; i++){
            key += "=";
        }
        return key;
    }
}
