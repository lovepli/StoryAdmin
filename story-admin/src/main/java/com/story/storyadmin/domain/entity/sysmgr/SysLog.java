package com.story.storyadmin.domain.entity.sysmgr;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
//@AllArgsConstructor
@Document(collection="syslog") //把一个java类声明为mongodb的文档，可以通过collection参数指定这个类对应的文档。//添加 @Document ，该 bean save 到 mongo 的 syslog collection
public class SysLog {

    @Id
    private String id;
    /**
     * 账户名
     */
    private String account;
    /**
     * ip地址
     */
    private String ip;
  /**
   * 请求方式
   * @Field 注解的作用：代表一个字段，可以不加，不加的话默认以参数名为列名。
   * <p>给映射存储到 mongodb 的字段取别名 在 java bean 中字段名为 requestMethod，存储到 mongo 中 key 为 requestmethod
   */
  @Field("requestmethod")
  private String requestMethod;
    /**
     * 访问url
     */
    private String url;
    /**
     * 访问uri
     */
    private String uri;
    /**
     * 类名
     */
    private String clazz;

    /**
     * 方法名
     */
    @Field("methodname")
    private String methodName;

    /**
     *系统当前时间
     */
    @Field("visittime")
    private Date visitTime;
    /**
     * 请求耗时
     */
    @Field("spendtime")
    private Long spendTime;
    /**
     * 请求参数
     */
    private String params;

    public SysLog(){}

    /**
     * 构造函数
     * @param id
     * @param account
     * @param ip
     * @param requestMethod
     * @param url
     * @param uri
     * @param clazz
     * @param methodName
     * @param visitTime
     * @param spendTime
     * @param params
     */
    public SysLog(String id,String account,String ip,String requestMethod,String url,String uri
        ,String clazz,String methodName,Date visitTime,Long spendTime,String params){
        this.id=id;
        this.account= account;
        this.ip=ip;
        this.requestMethod=requestMethod;
        this.url=url;
        this.uri=uri;
        this.clazz= clazz;
        this.methodName= methodName;
        this.visitTime=visitTime;
        this.spendTime=spendTime;
        this.params= params;
    }

    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-ddHH:mm:ss")
    private Date startDate;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-ddHH:mm:ss")
    private Date endDate;
}