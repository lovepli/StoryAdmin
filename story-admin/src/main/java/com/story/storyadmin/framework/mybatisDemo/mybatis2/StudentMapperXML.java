package com.story.storyadmin.framework.mybatisDemo.mybatis2;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: 59688
 * @date: 2021/9/28
 * @description:
 */
public class StudentMapperXML {
    public static final  String namespace = "com.story.storyadmin.framework.mybatisDemo.mybatis2.StudentMapper";


    private static Map<String,String> methodSqlMap = new HashMap<>();

    static {
        methodSqlMap.put("findStudentById","select * from student where id = %5");
    }

    /**
     * 这里，为了不牵涉到XML的解析过程，直接提供已经处理完毕的结果。其实就是namespace/statementID/SQL的存储、映射。
     * @param method
     * @return
     */
    public static String getMethodSQL(String method){
        return methodSqlMap.get(method);
    }



}
