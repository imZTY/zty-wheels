package com.zty.framework.util.aes;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author tianyi
 * @date 2018-11-30 23:10
 * @version V1.0
 * @desc AES 加密工具类
 */
public class AESUtil {

    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";//默认的加密算法
    private static final String SECRECT = "woaiguangzhongyi";
    /**
     * AES 加密操作
     *
     * @param content 待加密内容
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content) {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);// 创建密码器

            byte[] byteContent = content.getBytes("utf-8");

            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(SECRECT));// 初始化为加密模式的密码器

            byte[] result = cipher.doFinal(byteContent);// 加密

            String rt = Base64.encodeBase64String(result);

            return clean(rt);//通过Base64转码返回
        } catch (Exception ex) {
            Logger.getLogger(AESUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
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

    /**
     * AES 解密操作
     *
     * @param content
     * @return
     */
    public static String decrypt(String content) throws UnsupportedEncodingException, NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException {
        //预处理，确保格式合法
        content = fill(content);

        try {

            //实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(SECRECT));

            //执行操作
            byte[] result = cipher.doFinal(Base64.decodeBase64(content));

            /**
             * 网上搜的方案，不可用
             */
//            if (null == content || content.length() == 0) {
//                throw new NullPointerException("内容为空");
//            }
//            SecretKeySpec key2 = null;
//            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
//            random.setSeed(content.getBytes());
//            try {
//                KeyGenerator kgen = KeyGenerator.getInstance("AES");
//                kgen.init(128, random);
//                SecretKey secretKey = kgen.generateKey();
//                byte[] enCodeFormat = secretKey.getEncoded();
//                key2 = new SecretKeySpec(enCodeFormat, "AES");
//            } catch (NoSuchAlgorithmException ex) {
//                throw new NoSuchAlgorithmException();
//            }
//            //return ;
//            byte[] result = key2.getEncoded();

            String rt = new String(result, "utf-8");

            System.out.println(rt);

            return rt;
        } catch (Exception e) {
            Logger.getLogger(AESUtil.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            throw e;
        }


    }

    /**
     * 生成加密秘钥
     *
     * @return
     */
    private static SecretKeySpec getSecretKey(final String password) {
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;

        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);

            //AES 要求密钥长度为 128
            kg.init(128, new SecureRandom(password.getBytes()));

            //生成一个密钥
            SecretKey secretKey = kg.generateKey();

            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);// 转换为AES专用密钥
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AESUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static void main(String[] args) throws InvalidKeyException, BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException {
        String s = "这是明文";

        System.out.println("s:" + s);

        String s1 = AESUtil.encrypt(s);
        System.out.println("s1:" + s1);

        try {
            System.out.println("s2:"+AESUtil.decrypt(s1));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


    }

}
