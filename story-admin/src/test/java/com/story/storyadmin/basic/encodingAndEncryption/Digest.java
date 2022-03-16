package com.story.storyadmin.basic.encodingAndEncryption;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @author: lipan
 * @date: 2019-06-11
 * @description:
 */
public class Digest {
    public static byte[] digest(String hashAlgorithm, byte[] input) { //传入摘要算法的名称
        MessageDigest md;
        try {
            md = MessageDigest.getInstance(hashAlgorithm);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        md.update(input);
        return md.digest();
    }

    public static void main(String[] args) throws Exception{
        String s = "Java摘要算法测试";
        byte[] input = s.getBytes("UTF-8");
        //标准算法名称：
        byte[] r1 = digest("MD5", input);
        System.out.println(r1.length + ":" + String.format("%0" + (r1.length * 2) + "x", new BigInteger(1, r1))); //r1.length 代表字节数

        byte[] r2 = digest("SHA-1", input);
        System.out.println(r2.length + ":" + String.format("%0" + (r2.length * 2) + "x", new BigInteger(1, r2)));

        byte[] r3 = digest("SHA-256", input);
        System.out.println(r3.length + ":" + String.format("%0" + (r3.length * 2) + "x", new BigInteger(1, r3)));




    }
}
