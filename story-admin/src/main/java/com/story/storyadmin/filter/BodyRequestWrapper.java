package com.story.storyadmin.filter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * https://www.jianshu.com/p/7e55801fa5d1?utm_campaign=maleskine&utm_content=note&utm_medium=seo_notes&utm_source=recommendation
 */
public class BodyRequestWrapper extends HttpServletRequestWrapper {

    private static final Logger logger = LoggerFactory.getLogger(BodyRequestWrapper.class);

    private static String key = "and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|;|or|-|+";
    private static Set<String> notAllowedKeyWords = new HashSet<String>(0);
    private static String replacedString="INVALID";
    static {
        String keyStr[] = key.split("\\|");
        for (String str : keyStr) {
            notAllowedKeyWords.add(str);
        }
    }

    private byte[] body;

    public BodyRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        if(readBytes(request.getReader(), "utf-8") == null){
         logger.info("接口参数为null");
        }
        else {
            // TODO 没有进入执行？？？ 对于问号传参形式的API，这里不知道为什么获取不到参数？？
         logger.info("接口参数为不为null");
        //由于request并没有提供现成的获取json字符串的方法，所以我们需要将body中的流转为字符串
        String json = new String(readBytes(request.getReader(), "utf-8"));
            logger.info("接口参数为为"+json);
        body = getData(json).getBytes();

          //String result= getData2(request); // 没有执行？？
          //logger.info("请求参数："+result);
        }

    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    /**
     * 在使用@RequestBody注解的时候，其实框架是调用了getInputStream()方法，所以我们要重写这个方法
     * @return
     * @throws IOException
     */
    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);

        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return bais.read();
            }
        };
    }

    private String getData(String json){
        logger.info("json字符串："+json);
        String data = null;
        data= cleanXSS(json);
        //data.trim().replace("INVALID","");
        logger.info("过滤后的字符串："+data);
        return data;
    }

    private String getData2(HttpServletRequest request){
         //获取后台直接postman测试调用接口收到的数据
        Map parametersMap = request.getParameterMap();
        // 获取遍历form-data表单提交输入的内容
        Iterator it = parametersMap.entrySet().iterator();
        String data=null;
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String[] value = (String[]) entry.getValue();
            //for (int i = 0; i < value.length; i++) {
            //    logger.info("输入的参数："+value[i]);
            //    data= cleanXSS(value[i]);
            //}
            data= cleanXSS(value.toString());
        }
        logger.info("1111过滤后的参数："+data);
        return data;
    }

    /***
     * 通过BufferedReader和字符编码集转换成byte数组
     *@parambr
     *@paramencoding
     *@return*@throwsIOException
     * */

    private byte[] readBytes(BufferedReader br,String encoding) throws IOException{
        String str= null,retStr="";
        while ((str = br.readLine()) != null) {
            retStr+=str;
        }if(StringUtils.isNotBlank(retStr)) {
            return retStr.getBytes(Charset.forName(encoding));
        }return null;
    }

    private  String cleanXSS(String valueP) {
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

    private  String cleanSqlKeyWords(String value) {
        String paramValue = value;
        for (String keyword : notAllowedKeyWords) {
            if (paramValue.length() > keyword.length() + 4
                    && (paramValue.contains(" "+keyword)||paramValue.contains(keyword+" ")||paramValue.contains(" "+keyword+" "))) {
                paramValue = org.springframework.util.StringUtils.replace(paramValue, keyword, replacedString);
                // logger.error(this.currentUrl + "已被过滤，因为参数中包含不允许sql的关键词(" + keyword + ")"+";参数："+value+";过滤后的参数："+paramValue);
                logger.error("已被过滤，因为参数中包含不允许sql的关键词(" + keyword + ")"+";参数："+value+";过滤后的参数："+paramValue);
            }
        }
        return paramValue;
    }



}