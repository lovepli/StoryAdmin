package com.story.storyadmin.web.mongoTest;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author: lipan
 * @date: 2020/7/3
 * @description:
 */
@Document(collection="t_student")
public class Student {

    @Id // 指定ID
    private String id;

    @Field("studentName") // 指定域名，覆盖默认
    private String studentName;

    @Field("password") // 指定域名，覆盖默认
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Student(String id, String studentName, String password) {
        this.id = id;
        this.studentName = studentName;
        this.password = password;
    }
}
