package com.story.storyadmin.service.sysmgr;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.story.storyadmin.domain.entity.sysmgr.User;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.domain.vo.sysmgr.LoginBody;
import com.story.storyadmin.domain.vo.sysmgr.UserPassword;
import com.story.storyadmin.domain.vo.sysmgr.UserRoleVo;
import com.story.storyadmin.domain.vo.sysmgr.UserVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author sunnj
 * @since 2018-12-28
 */
public interface UserService extends IService<User> {

    /**
     * 根据用户账号查询用户详情
     * @param account
     * @return
     */
    User findUserByAccount(String account);

    /**
     * 用户登录
     * @param user
     * @return
     */
    Result login(UserVo user, HttpServletResponse response);

    Result login2(LoginBody user, HttpServletResponse response);

    Result login3(JSONObject jsonObject, HttpServletResponse response);

    /**
     * ERP登录
     * @return
     */
    Result loginErp(HttpServletResponse response);

    /**
     * 保存用户
     * @param user
     * @return
     */
    Result persist(User user);

    /**
     * 获取用户ID角色
     * @param userId
     * @return
     */
    Result findUserRole(Long userId);

    /**
     * 根据用户名查询所有角色
     * @param userName
     * @return
     */
    Result findUserRole(String userName);

    /**
     * 修改用户角色
     * @param userRole
     * @return
     */
    Result saveUserRoles(UserRoleVo userRole);

    /**
     * 修改用户密码
     * @param userPassword
     * @return
     */
    Result editPassWord(UserPassword userPassword);

    /**
     * 根据用户ID查查询user
     * @param id
     * @return
     */
     User selectUserById(Long id);

     List<User> selectUserNameList();

    String findFileInfoDetail(JSONObject jsonObject);


}
