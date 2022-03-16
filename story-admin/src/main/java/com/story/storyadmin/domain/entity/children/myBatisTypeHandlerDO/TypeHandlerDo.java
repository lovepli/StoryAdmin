package com.story.storyadmin.domain.entity.children.myBatisTypeHandlerDO;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;


import com.story.storyadmin.enumerator.globalEnum.GenderEnum;
import lombok.Data;
import lombok.ToString;
import org.apache.ibatis.type.JdbcType;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: lipan
 * @date: 2021年09月08日 2:34 下午
 * @description:
 */
@Data
@ToString
@TableName(value = "test_type_handler",autoResultMap = true)
public class TypeHandlerDo extends Model<TypeHandlerDo> {

    private static final  long serialVersionUID =1L;

    @TableId(value = "id",type = IdType.AUTO)
    private int id;

    @TableField(value = "create_time")
    private LocalDateTime createTime;

    //将属性作为json字符串保存到数据库
    @TableField(value = "details",typeHandler = ObjectJSONTypeHandler.class,jdbcType = JdbcType.VARCHAR)
    private PersonDO personDO;

    //枚举类型存到数据库的是数字，返回的可以是文字
    @TableField(value = "user_state",typeHandler = UserStateTypeHandler.class,jdbcType = JdbcType.INTEGER)
    private UserStateEnum userStateEnum;

    //这里我们定义一个 list 类型转 varchar（逗号隔开的字符串）的类型处理器，即 list [1,2,3] ==》varchar 1,2,3
    @TableField(value = "ids",typeHandler = SetTypeHandler.class,jdbcType = JdbcType.VARCHAR)
    private Set<Integer> ids;

    // contact 是一个 Map，但会自动转成 JSON 字符串存到数据库
    //@TableField(value = "contact",typeHandler = FastJsonTypeHandler.class,jdbcType = JdbcType.VARCHAR)
    //private Map<String, String> contact;

    @TableField(value = "contact",typeHandler = FastJsonTypeHandler.class,jdbcType = JdbcType.VARCHAR)
    private JSONObject contact;

    //这里我们定义一个 list 类型转 varchar（逗号隔开的字符串）的类型处理器，即 list [1,2,3] ==》varchar 1,2,3
    //@TableField(typeHandler = ListTypeHandler.class)
    //private List<String> contact2;


    //mybatis自动填充知识点
    //@TableField(value = "operation_type",fill = FieldFill.INSERT_UPDATE)
    //private String operationType;
    //
    //@TableField(value = "operation_time",fill = FieldFill.INSERT_UPDATE)
    //private Date operationTime;

    // mybatis通用枚举的使用
    @TableField(value = "gender")
    private GenderEnum gender;

    /**
     * mybatis自动填充知识点
     * 更新时间
     */
    //@TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    //private Long updateTime;
    //
    //@TableField(value = "create_time2", fill = FieldFill.INSERT_UPDATE)
    //private Long createTime2;

}
