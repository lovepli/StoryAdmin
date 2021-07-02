package com.story.storyadmin.mapper.sysmgr;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.story.storyadmin.domain.entity.bank.NewGeneration;
import com.story.storyadmin.domain.entity.sysmgr.User;
import com.story.storyadmin.domain.entity.sysmgr.UserRole;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author sunnj
 * @since 2018-12-28
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户Id删除角色
     * @param user
     * @return
     */
    int deleteRoleByUserId(UserRole user);

    /**
     * 批量插入
     * @param userRoleList
     */
    void batchInsertUserRole(List<UserRole> userRoleList);

    /**
     * 根据用户Id获取角色
     * @param userId
     * @return
     */
    List<Long> selectRoleByUserId(@Param(value = "userId") Long userId);

    /**
     * 根据用户名名查询所有角色名
     * @param userName
     * @return
     */
    List<String> selectRoleByAccount(@Param(value = "userName") String userName);

    List<User> selectUserNameList();


    //######################################mybatis的几种特殊查询结构####################################3

    /**
     *获取新一代表所有记录，封装为Map<String,User>形式，其中id和名称作为唯一性校验
     * @return
     */
    @MapKey("userUniqueKey")
    Map<String, User> getUserMap();


    /**
     * @MapKey注解表示表中那个字段作为map的key
     * @return
     */
    @MapKey("id")
    Map<Long, User> getUserMap2();

    User getUserMap21();

    List<User> getUserMap211();

    Map<Long, User> getUserMap22();

    List<Map<Long, User>> getUserMap23();

    @MapKey("id")
    Map<Long,Map<Long,Object>> getUserMap3();


}
