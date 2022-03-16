package com.story.storyadmin.domain.entity.children.myBatisTypeHandlerDO;

import com.story.storyadmin.utils.wind.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author: lipan
 * @date: 2021年09月08日 9:08 下午
 * @description:  MyBatis Plus 自动类型转换之TypeHandler
 * https://www.cnblogs.com/zhou-shi-yuan-ISO8859-1/p/14001216.html
 */
@MappedTypes(Set.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class SetTypeHandler extends BaseTypeHandler<Set> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Set parameter, JdbcType jdbcType)
            throws SQLException {
        String param = parameter.toString().replaceAll("\\[|\\]| ", "");
        ps.setString(i, param);
    }

    @Override
    public Set<Integer> getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        String sqlSet = rs.getString(columnName);
        return getSet(sqlSet);
    }

    @Override
    public Set<Integer> getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        String sqlSet = rs.getString(columnIndex);
        return getSet(sqlSet);
    }

    @Override
    public Set<Integer> getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        String sqlSet = cs.getString(columnIndex);
        return getSet(sqlSet);
    }

    private Set<Integer> getSet(String sqlSet) {
        if (StringUtils.isNotBlank(sqlSet)) {
            return Arrays.asList(sqlSet.split(",")).stream()
                    .map(Integer::parseInt)
                    .collect(Collectors.toSet());
        }
        return new HashSet();
    }
}