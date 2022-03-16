package com.story.storyadmin.utils.encryptionUtil.sha1Test;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;

/**
 * @author: lipan
 * @date: 3:48 下午
 * @description: SHA1算法
 * SHA1也是和MD5类似的信息摘要算法，但是它比MD5更加安全。
 */
public class SHA1Demo {

    public String encode(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(str.getBytes("utf-8"));
            byte[] digest = md.digest();
            return byteToHexString(digest);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String byteToHexString(byte[] bytes) {
        return String.valueOf(Hex.encodeHex(bytes));
    }
    public static void main(String[] args) {
        SHA1Demo sha1 = new SHA1Demo();
        String content = "测试test";
        System.out.println(sha1.encode(content));
    }
}
