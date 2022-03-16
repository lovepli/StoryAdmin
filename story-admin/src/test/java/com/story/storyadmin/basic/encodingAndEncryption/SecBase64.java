package com.story.storyadmin.basic.encodingAndEncryption;

import java.util.Base64;

/**
 * Base64编码
 * Base64是编码算法，不是加密算法
 * Base64编码的目的是把任意二进制数据编码为文本(长度增加1/3)
 * 其它编码:Base32，Base48，Base58
 */
public class SecBase64 {

	public static void main(String[] args) throws Exception {
		String original = "Hello\u00ff编码测试";
		//String b64 = Base64.getEncoder().encodeToString(original.getBytes("UTF-8"));
       // String b64 = Base64.getEncoder().withoutPadding().encodeToString(original.getBytes("UTF-8"));  //withoutPadding()去掉末尾的== 不影响解码
        String b64 = Base64.getUrlEncoder().withoutPadding().encodeToString(original.getBytes("UTF-8"));  //.getUrlEncoder()实现Url的base64编码
		System.out.println(b64);
		String ori = new String(Base64.getUrlDecoder().decode(b64), "UTF-8");
		System.out.println(ori);


	}
}
