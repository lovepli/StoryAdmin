package com.story.storyadmin.mapper.sysmgr.expand;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.story.storyadmin.domain.entity.sysmgr.User;
import com.story.storyadmin.domain.entity.sysmgr.UserRole;
import com.story.storyadmin.domain.entity.sysmgr.expand.User2;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author sunnj
 * @since 2018-12-28
 */
public interface UserMapper2 extends BaseMapper<User2> {

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

    List<User2> selectUserNameList();
}
