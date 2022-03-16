package com.story.storyadmin.basic.encodingAndEncryption;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @author: lipan
 * @date: 2019-06-11
 * @description:
 */
public class MD5 {

    public static byte[] toMD5(byte[] input) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");  //这里必须只能填写MD5
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        md.update(input);
        return md.digest();
    }

    public static void main(String[] args) throws Exception{
        String s = "MD5摘要算法测试";
        byte[] r = toMD5(s.getBytes("UTF-8"));
        System.out.println(String.format("%032x",new BigInteger(1,r)));  //这里的 032x 什么意思？  代码32个字节
    }
}
