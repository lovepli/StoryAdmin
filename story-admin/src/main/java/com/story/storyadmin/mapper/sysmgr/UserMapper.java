package com.story.storyadmin.mapper.sysmgr;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.story.storyadmin.domain.entity.sysmgr.User;
import com.story.storyadmin.domain.entity.sysmgr.UserRole;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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
@Repository //@Repository用于标注数据访问组件，即 DAO 组件
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

    @MapKey("id")
    Map<Long,Map<Long,Object>> getUserMap4();

    // 插入主键返回
    Long insertUser(User user);
    Long insertUser2(User user);

    /**
     * TODO 能不能在sql层面利用分组查询，根据停启用状态分组，状态相同的user放在一个list中，答案是不能
     * @return
     */
    //@MapKey("status")
    //Map<String,List<Object>> getUserMap5();

    // mybatis的主键插入返回



    //##########################################mybatis 请求参数类型#########################################

    User selectUserByNameAndId(String name, Long id);

    User selectUserByNameAndId2(@Param("name") String name, @Param("id") Long id);

    User selectUserMapByNameAndId(Map<String,Object> params);

    User selectUserBeanByNameAndId(User user);

    User selectJsonObjectByNameAndId(@Param("jsonObject") JSONObject jsonObject);

    //##########################################mybatis 动态sql#########################################

    List<User> selectUserArrayByIds( Long[] userIds);

    List<User> selectUserList(List<Long> userList);

    User selectUserListByNameOrEmail(User user);

    List<User> selectUserByNameLike(@Param("name") String name);

    User selectUserListByNameOrEmail2(User user);

    User selectUserListByNameOrEmail3(User user);

    int insertUserListByNameOrEmail(User user);

    int updateUserByNameOrEmail(User user);

    int insertUserLists(List<User> userLists);

    int updateUserLists(@Param("userLists") List<User> userLists);

    int deleteUserLists(@Param("ids") List<Long> ids);

    User selectAll(User user);

    User selectUseByYnFlag(User user);








}
