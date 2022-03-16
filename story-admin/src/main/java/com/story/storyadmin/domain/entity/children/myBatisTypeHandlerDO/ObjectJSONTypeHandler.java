package com.story.storyadmin.domain.entity.children.myBatisTypeHandlerDO;

import com.google.gson.Gson;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author: lipan
 * @date: 2021年09月08日 4:01 下午
 * @description: SpringBoot中使用Mybatis的TypeHandler简单案例及源码简析
 * https://blog.csdn.net/weixin_42721350/article/details/116357474
 */

@MappedJdbcTypes(JdbcType.VARCHAR)//代表对应的数据库中字段的类型，json类型本质上也是字符串
@MappedTypes({PersonDO.class})//代表要转换的JavaBean对象
public class ObjectJSONTypeHandler extends BaseTypeHandler<PersonDO> {

    //Java对象与JSON格式的互换借助Google的Gson完成
    private Gson gson = new Gson();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, PersonDO parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, gson.toJson(parameter));
    }

    @Override
    public PersonDO getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return gson.fromJson(rs.getString(columnName), PersonDO.class);
    }

    @Override
    public PersonDO getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return gson.fromJson(rs.getString(columnIndex), PersonDO.class);
    }

    @Override
    public PersonDO getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return gson.fromJson(cs.getString(columnIndex), PersonDO.class);
    }
}

