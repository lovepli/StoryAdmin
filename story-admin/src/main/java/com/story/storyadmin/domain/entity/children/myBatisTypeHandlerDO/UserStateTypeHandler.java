package com.story.storyadmin.domain.entity.children.myBatisTypeHandlerDO;

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
 * @date: 2021年09月08日 7:56 下午
 * @description:
 * Springboot整合Mybatis使用TypeHandler来转换数据库中的数据
 * TypeHandler转换指定数据库中数据为Enum枚举
 * https://www.cnblogs.com/dyf-stu/p/10162301.html
 */
/* 数据库中的数据类型 */
@MappedJdbcTypes(JdbcType.INTEGER)
/* 转化后的数据类型 */
@MappedTypes(value = UserStateEnum.class)
public class UserStateTypeHandler extends BaseTypeHandler<UserStateEnum> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, UserStateEnum userStateEnum, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i,userStateEnum.getStateCode());
    }

    @Override
    public UserStateEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        int code =resultSet.getInt(s);
        if(code>=0&&code<=5){
            return UserStateEnum.getUserStateByCode(code);
        }
        return null;
    }

    @Override
    public UserStateEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        int code = resultSet.getInt(i);
        if(code>=0&&code<=5){
            return UserStateEnum.getUserStateByCode(code);
        }
        return null;
    }

    @Override
    public UserStateEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        int code = callableStatement.getInt(i);
        if(code>=0&&code<=5){
            return UserStateEnum.getUserStateByCode(code);
        }
        return null;
    }
}
