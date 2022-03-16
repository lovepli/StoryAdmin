package com.story.storyadmin.basic.xmlAndjson.jacksonSample;

import java.util.Collections;
import java.util.List;

//import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
//import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * 使用Jackson:
 * 开源的XML读写工具
 * 可在XML和JavaBean之间转换
 * API接口简单
 * 可定制序列化
 */

/**
 * XML子元素
 */
public class Channel {
	public String title;
	public String description;
	public String language;
	public int ttl;
	public String category;

	//@JacksonXmlProperty(localName = "item")
	//@JacksonXmlElementWrapper(useWrapping = false)
	public List<Item> items = Collections.emptyList();

}
