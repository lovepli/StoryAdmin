package com.story.storyadmin.basic.RegularExpression;

/**
 * 匹配规则
 * 正则表达式匹配规则
 * 精确匹配
 * \d:0-9 \w:a-z，A-Z，0-9，_ \s:空格，Tab键 \D:非\d
 * \W:非\w
 * \S:非\s *:任意个字符 +:至少一个字符 ?:0个或1个字符 {n}:n个字符 {m,n}:m-n个字符 {n,}:至少n个字符
 */
public class RegularDemo2 {

	public static boolean isValidTel(String s) {
		return s.matches("\\d{3,4}\\-\\d{6,8}");
	}

}
