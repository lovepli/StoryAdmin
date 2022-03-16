package com.story.storyadmin.framework.JSONParser;

import com.story.storyadmin.framework.JSONParser.parser.Parser;
import com.story.storyadmin.framework.JSONParser.tokenizer.CharReader;
import com.story.storyadmin.framework.JSONParser.tokenizer.TokenList;
import com.story.storyadmin.framework.JSONParser.tokenizer.Tokenizer;

import java.io.IOException;
import java.io.StringReader;

/**
 * 自己动手实现一个简单的JSON解析器 https://segmentfault.com/a/1190000010998941
 * Created by code4wt on 17/9/1.
 */
public class JSONParser {

    private Tokenizer tokenizer = new Tokenizer();

    private Parser parser = new Parser();

    public Object fromJSON(String json) throws IOException {
        CharReader charReader = new CharReader(new StringReader(json));
        TokenList tokens = tokenizer.tokenize(charReader);
        return parser.parse(tokens);
    }
}
