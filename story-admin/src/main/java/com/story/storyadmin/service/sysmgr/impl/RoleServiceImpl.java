package com.story.storyadmin.service.sysmgr.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.domain.entity.sysmgr.Role;
import com.story.storyadmin.domain.entity.sysmgr.RoleAuthority;
import com.story.storyadmin.domain.entity.sysmgr.UserRole;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.domain.vo.sysmgr.RoleAuth;
import com.story.storyadmin.mapper.sysmgr.RoleMapper;
import com.story.storyadmin.mapper.sysmgr.UserRoleMapper;
import com.story.storyadmin.service.sysmgr.RoleAuthorityService;
import com.story.storyadmin.service.sysmgr.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author sunnj
 * @since 2018-12-28
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    protected RoleAuthorityService roleAuthorityService;

    @Autowired
    protected UserRoleMapper userRoleMapper;


    @Override
    public List<Role> findRoleByUserId(Long userId) {
        QueryWrapper<UserRole> userRoleWrapper= new QueryWrapper<>();
        userRoleWrapper.eq("user_id",userId);
        userRoleWrapper.eq("yn_flag","1");
        List<UserRole> userRoleList= userRoleMapper.selectList(userRoleWrapper);

        //查询用户角色
        QueryWrapper<Role> roleWrapper= new QueryWrapper<>();
        userRoleWrapper.eq("yn_flag","1");
        roleWrapper.in("id",userRoleList.stream().map(e -> e.getRoleId()).collect(Collectors.toList()));

        List<Role> roleList= baseMapper.selectList(roleWrapper);
        return roleList;
    }

    @Override
    public Result persist(Role role) {
        Result result ;
        Date currentDate= Date.from(Instant.now());
        //修改
        if(role.getId() != null){
            role.setEditor(UserContext.getCurrentUser().getAccount());
            role.setModifiedTime(currentDate);
            baseMapper.updateById(role);
            result= new Result(true, "修改成功", null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }else{//添加
            role.setYnFlag("1");
            role.setEditor(UserContext.getCurrentUser().getAccount());
            role.setCreator(UserContext.getCurrentUser().getAccount());
            role.setCreatedTime(currentDate);
            role.setModifiedTime(currentDate);
            baseMapper.insert(role);
            result= new Result(true, "添加成功", null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }
        return result;
    }

    /**
     * 修改权限
     * @param roleAuth 权限表单对象
     * @return
     */
    @Override
    public Result saveRoleAuths(RoleAuth roleAuth) {
        Date currentDate= Date.from(Instant.now());

        //角色权限关系对象
        RoleAuthority role= new RoleAuthority();
        role.setRoleId(roleAuth.getRoleId());
        role.setModifiedTime(currentDate);
        //根据角色ID删除用角色权限，即先删除先前存在的，后面统一进行添加操作，这样做比全部进行修改操作来的简单粗暴
        roleAuthorityService.deleteAuthByRoleId(role);

        RoleAuthority tempAuth ;
        List<RoleAuthority> authList=new ArrayList<>();
        //将角色权限关系对象RoleAuthority 添加到list集合，方便后面批量插入到数据库
        for(Long authId : roleAuth.getAuthorityIds()){
            tempAuth = new RoleAuthority(roleAuth.getRoleId(),authId);
            tempAuth.setYnFlag("1");
            tempAuth.setEditor(UserContext.getCurrentUser().getAccount());
            tempAuth.setCreator(UserContext.getCurrentUser().getAccount());
            tempAuth.setCreatedTime(currentDate);
            tempAuth.setModifiedTime(currentDate);
            authList.add(tempAuth);
        }
        //批量插入角色权限关系到数据库表
        roleAuthorityService.batchInsert(authList);

        return new Result(true,"修改权限成功",null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
    }

    /**
     * 根据角色ID获取权限
     * @param roleId
     * @return
     */
    @Override
    public Result selectAuthByRoleId(Long roleId) {
        //获取权限ID集
        List<Long> auths= roleAuthorityService.selectAuthByRoleId(roleId);
        return new Result(true,null,auths, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
    }


}
