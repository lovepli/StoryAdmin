package com.story.storyadmin.basic.encodingAndEncryption;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @author: lipan
 * @date: 2019-06-11
 * @description:
 *
 * SHA1
 * SHA-1摘要算法:输出160bits，20bytes
 * 其它摘要算法:
 * SHA-256 SHA-512 RipeMD160
 */
public class SHA {
    public static byte[] toSHA(byte[] input) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-1");  //这里必须只能填写SHA-1
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        md.update(input);
        return md.digest();
    }

    public static void main(String[] args) throws Exception {
        String s = "SHA-1摘要算法测试";
        byte[] r = toSHA(s.getBytes("UTF-8"));
        System.out.println(String.format("%040x",new BigInteger(1,r)));  //这里的 040x 什么意思？  代码40个字节
    }
}
