package com.story.storyadmin.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: 59688
 * @date: 2021/3/24
 * @description:
 */
public class IllegalStrFilterUtil {
    
    private static final Logger logger = LoggerFactory.getLogger(IllegalStrFilterUtil.class);

    private static String key = "and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|;|or|-|+";
    private static Set<String> notAllowedKeyWords = new HashSet<String>(0);
    private static String replacedString="INVALID";
    static {
        String keyStr[] = key.split("\\|");
        for (String str : keyStr) {
            notAllowedKeyWords.add(str);
        }
    }

    private static final String REGX = "!|！|@|◎|#|＃|(\\$)|￥|%|％|(\\^)|……|(\\&)|※|(\\*)|×|(\\()|（|(\\))||_|——|(\\+)|＋|(\\|)|§ ";
    //String ss="!|!|@|◎|#|＃|(\\$)|￥|%|％|(\\^)|……|(\\&)|※|(\\*)|×|(\\()|（|(\\))||_|——|(\\+)|＋|(\\|)|§";



    /**
     * 对常见的sql注入攻击进行拦截
     *
     * @param input
     * @return
     * true 表示参数不存在SQL注入风险
     * false 表示参数存在SQL注入风险
     */
    public static Boolean sqlStrFilter(String input) {
        if (input == null || input.trim().length() == 0) {
            return false;
        }
        input = input.toUpperCase();

        if (input.indexOf("DELETE") >= 0 || input.indexOf("ASCII") >= 0 || input.indexOf("UPDATE") >= 0 || input.indexOf("SELECT") >= 0
                || input.indexOf("'") >= 0 || input.indexOf("SUBSTR(") >= 0 || input.indexOf("COUNT(") >= 0 || input.indexOf(" OR ") >= 0
                || input.indexOf(" AND ") >= 0 || input.indexOf("DROP") >= 0 || input.indexOf("EXECUTE") >= 0 || input.indexOf("EXEC") >= 0
                || input.indexOf("TRUNCATE") >= 0 || input.indexOf("INTO") >= 0 || input.indexOf("DECLARE") >= 0 || input.indexOf("MASTER") >= 0) {
            logger.error("该参数存在SQL注入风险：sInput=" + input);
            return false;
        }
        logger.info("通过sql检测");
        return true;
    }

    /**
     * 对非法字符进行检测
     *
     * @param input
     * @return
     * true 表示参数不包含非法字符
     * false 表示参数包含非法字符
     */
    public static Boolean isIllegalStr(String input) {

        if (input == null || input.trim().length() == 0) {
            return false;
        }
        input = input.trim();

        Pattern compile = Pattern.compile(REGX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = compile.matcher(input);
        logger.info("通过字符串检测");
        return matcher.find();
    }


    public  static String cleanXSS(String valueP) {
        // You'll need to remove the spaces from the html entities below
        String value = valueP.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
        value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
        value = value.replaceAll("'", "& #39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        value = value.replaceAll("script", "");
        value = cleanSqlKeyWords(value);
        return value;
    }

    public static String cleanSqlKeyWords(String value) {
        String paramValue = value;
        for (String keyword : notAllowedKeyWords) {
            if (paramValue.length() > keyword.length() + 4
                    && (paramValue.contains(" "+keyword)||paramValue.contains(keyword+" ")||paramValue.contains(" "+keyword+" "))) {
                paramValue = StringUtils.replace(paramValue, keyword, replacedString);
               // logger.error(this.currentUrl + "已被过滤，因为参数中包含不允许sql的关键词(" + keyword + ")"+";参数："+value+";过滤后的参数："+paramValue);
                logger.error("已被过滤，因为参数中包含不允许sql的关键词(" + keyword + ")"+";参数："+value+";过滤后的参数："+paramValue);
            }
        }
        return paramValue;
    }
}
