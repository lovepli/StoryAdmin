package com.story.storyadmin.utils.encryptionUtil.md5Test;

import java.security.MessageDigest;

/**
 * @author: lipan
 * @date: 3:41 下午
 * @description: MD5算法
 *
 * 准确来讲，MD5不是一种加密算法，而是一种摘要算法，MD5能将明文输出为128bits的字符串，这个字符串是无法再被转换成明文的。
 * 网上一些MD5解密网站也只是保存了一些字符串对应的md5串，通过已经记录的md5串来找出原文。
 *
 * 我做过的几个项目中经常见到MD5用在加密上的场景。比如对密码的加密，生成一个密码后，使用MD5生成一个128位字符串保存在数据库中，
 * 用户输入密码后也先生成MD5串，再去数据库里比较。因此我们在找回密码时是无法得到原来的密码的，因为明文密码根本不会被保存。
 */
public class MD5Demo {

    /**
     * 生成MD5
     * @param str
     * @return
     */
    public String encode(String str) {
        byte[] result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes("UTF-8"));
            result = md.digest();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return parseByte2HexStr(result);
    }

    /**
     * 将二进制转换成十六进制
     *
     * @param buf
     * @return
     */
    private String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        MD5Demo md5=new MD5Demo();
        String content = "测试test";
        System.out.println(md5.encode(content));
    }
}
