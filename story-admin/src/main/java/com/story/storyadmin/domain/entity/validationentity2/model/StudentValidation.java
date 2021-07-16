package com.story.storyadmin.domain.entity.validationentity2.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


/*
    @NotNull    验证对象是否不为null, 无法查检长度为0的字符串
    @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
    @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
    注意：
      @NotEmpty 用在集合类上面
　　　　@NotBlank 用在String上面
　　　　@NotNull  用在基本类型上
*/
public class StudentValidation {

    @NotNull(message = "用户ID不能为空")
    private Long userID;

    @Range(min = 1,max = 1000,message = "排序号范围为1到1000")
    private Integer listIndex;

    @NotEmpty(message = "地址不能为空")
    private List<String> addressID;

    @NotBlank(message = "备注不能为空")
    @Length(max = 50,message = "字符串长度最大不能超过50")
    private String comment;

    public StudentValidation() {
    }
    public Long getUserID() {
        return userID;
    }
    public void setUserID(Long userID) {
        this.userID = userID;
    }
    public List getAddressID() {
        return addressID;
    }
    public void setAddressID(List addressID) {
        this.addressID = addressID;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    @Override
    public String toString() {
        return "Order{" +
                "userID=" + userID +
                ", addressID=" + addressID +
                ", comment='" + comment + '\'' +
                '}';
    }
}

