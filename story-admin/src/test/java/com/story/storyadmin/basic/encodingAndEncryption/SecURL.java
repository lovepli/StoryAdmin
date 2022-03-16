package com.story.storyadmin.basic.encodingAndEncryption;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author: lipan
 * @date: 2019-06-11
 * @description:
 *
 * URL编码
 * URL编码是编码算法，不是加密算法
 * URL编码的目的是把任意文本数据编码为%前缀表示的文本
 */
public class SecURL {
    public static void main(String[] args) throws Exception{
        String original = "URL 参数";
        String encoded = URLEncoder.encode(original, "UTF-8");
        System.out.println(encoded);
        String ori = new String(URLDecoder.decode(encoded, "UTF-8"));
        System.out.println(ori);

        //URL+%E5%8F%82%E6%95%B0  URL=不变 空格变为 + 中文按utf-8变为百分号的16进至
        //URL 参数
    }
}
