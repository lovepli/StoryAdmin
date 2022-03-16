package com.story.storyadmin.domain.entity.children.myBatisTypeHandlerDO;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
/**
 * @author: lipan
 * @date: 2021年09月08日 9:32 下午
 * @description:
 */
@MappedTypes({JSONObject.class})
@MappedJdbcTypes({JdbcType.VARCHAR})
public class FastJsonTypeHandler implements TypeHandler<JSONObject>{

    @Override
    public JSONObject getResult(ResultSet rs, String columnName) throws SQLException {
        String string = rs.getString(columnName);
        JSONObject json = JSONObject.parseObject(string);
        return json;
    }

    @Override
    public JSONObject getResult(ResultSet rs, int columnIndex) throws SQLException {
        String string = rs.getString(columnIndex);
        JSONObject json = JSONObject.parseObject(string);
        return json;
    }

    //@param cs 当前的CallableStatement执行后的CallableStatement ![输入图片说明](https://static.oschina.net/uploads/img/201709/05153912_SK1d.png "在这里输入图片标题")
    @Override
    public JSONObject getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String string = cs.getString(columnIndex);
        JSONObject json = JSONObject.parseObject(string);
        return json;
    }

    /**
     * 用于定义在Mybatis设置参数时该如何把Java类型的参数转换为对应的数据库类型
     * @param ps 当前的PreparedStatement对象
     * @param i 当前参数的位置
     * @param parameter 当前参数的Java对象
     * @param jdbcType 当前参数的数据库类型
     * @throws SQLException
     */
    @Override
    public void setParameter(PreparedStatement ps, int i, JSONObject parameter, JdbcType jdbcType) throws SQLException {
        if(parameter == null){
            ps.setString(i, null);
            return;
        }
        String json = JSON.toJSONString(parameter);
        ps.setString(i, json);

    }

}
