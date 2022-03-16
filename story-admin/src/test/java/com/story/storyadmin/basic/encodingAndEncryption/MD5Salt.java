package com.story.storyadmin.basic.encodingAndEncryption;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @author: lipan
 * @date: 2019-06-11
 * @description:
 *
 * MD5
 *
 * 摘要算法
 * 摘要算法 / 哈希算法 / 数字指纹 / Hash / Digest
 * 计算任意长度数据的摘要，输出固定长度
 * 相同的输入始终得到相同的输出
 * 不同的输入尽量得到不同的输出
 *
 * 碰撞
 * 两个不同的输入得到了相同的输出
 *
 * Hash算法的安全性:
 * 碰撞率低
 * 不能猜测输出
 * 输入的任意一个bit的变化会造成输出完全不同
 * 很难从输出反推输入(只能依靠暴力穷举)
 *
 * MD5摘要算法
 * 验证原始数据是否被篡改
 * 存储用户口令
 * 需要防止彩虹表攻击
 */
public class MD5Salt {

    public static byte[] toMD5Salt(byte[] input) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        md.update(input);
        return md.digest();
    }

    public static void main(String[] args) throws Exception {
        String password = "helloword";
        String salt = "Random salt";
        byte[] r = MD5Salt.toMD5Salt((salt + password).getBytes("UTF-8"));
        System.out.println(String.format("%032x",new BigInteger(1,r)));
    }
}
