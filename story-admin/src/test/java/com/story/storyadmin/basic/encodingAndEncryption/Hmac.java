package com.story.storyadmin.basic.encodingAndEncryption;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import java.math.BigInteger;

/**
 * @author: lipan
 * @date: 2019-06-11
 * @description:
 * Hmac:Hash-based Message Authentication Code
 * 基于密钥的消息认证码算法
 * HmacMD5 ≈ md5(secure_random_key, data)
 * Hmac是把Key混入摘要的算法
 * 可以配合MD5、SHA-1等摘要算法
 * 摘要长度和原摘要算法长度相同
 */
public class Hmac {

    public static byte[] hmac(String hmacAlgorithm, SecretKey skey, byte[] input) throws Exception {
        Mac mac = Mac.getInstance(hmacAlgorithm);
        mac.init(skey);
        mac.update(input);
        return mac.doFinal();
    }

    public static void main(String[] args) throws Exception{
        String algorithm = "HmacSHA1";
        //原始数据：
        String data = "helloworld";
        //随机生成一个key:
        KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
        SecretKey skey=keyGen.generateKey();

        //打印key:
        byte[] key = skey.getEncoded();
        System.out.println(String.format("key: %0" + (key.length * 2) + "x", new BigInteger(1, key))); //64字节
        //用这个key计算
        byte[] result = hmac(algorithm, skey, data.getBytes("UTF-8"));
        System.out.println(String.format("Hash: %0" + (result.length * 2) + "x", new BigInteger(1, result))); //20字节


    }
}
