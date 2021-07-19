package com.story.storyadmin.web.sysmgr;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.story.storyadmin.config.mongo.SysLogAnnotation;
import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.domain.entity.sysmgr.Role;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.domain.vo.sysmgr.RoleAuth;
import com.story.storyadmin.service.sysmgr.RoleService;
import com.story.storyadmin.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.List;
@Api(description = "角色管理")
@RestController
@RequestMapping(value="/sysmgr/role")
public class RoleColtroller extends BaseController {

    @Autowired
    RoleService roleService;

    /**
     * 查询用户角色列表
     * @param role
     * @param pageNo
     * @param limit
     * @return
     */
    @ApiOperation(value = "角色" ,  notes="查询用户角色列表")
    @RequiresPermissions("sysmgr.role.query")
    @RequestMapping(value="/list",method = {RequestMethod.POST,RequestMethod.GET})
    public Result list(Role role,
                       @RequestParam(defaultValue = "1")int pageNo,
                       @RequestParam(defaultValue = "10")int limit){
        Result result = new Result();
        Page<Role> page = new Page(pageNo, limit);
        QueryWrapper<Role> eWrapper = new QueryWrapper(role);
        eWrapper.eq("yn_flag","1");
        IPage<Role> list = roleService.page(page, eWrapper);
        result.setData(list);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    /**
     * 根据Id查询
     * @param role
     * @return
     */
    @ApiOperation(value = "角色" ,  notes="根据Id查询用户角色")
    @RequiresPermissions("sysmgr.role.query")
    @RequestMapping(value="/find",method = {RequestMethod.POST})
    public Result findById(@RequestBody Role role){
        Role rolebean= roleService.getById(role.getId());
        Result result = new Result();
        result.setData(rolebean);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    /**
     * 保存/修改角色信息
     * @param role
     * @return
     */
    //@SysLogAnnotation
    @ApiOperation(value = "角色" ,  notes="保存/修改角色信息")
    @RequiresPermissions("sysmgr.role.save")
    @RequestMapping(value="/save",method = {RequestMethod.POST})
    public Result save(@RequestBody Role role){
        return roleService.persist(role);
    }

    /**
     * 删除
     * @param role
     * @return
     */
    @SysLogAnnotation
    @ApiOperation(value = "角色" ,  notes="删除角色信息")
    @RequiresPermissions("sysmgr.role.delete")
    @RequestMapping(value="/delete",method = {RequestMethod.POST})
    public Result dropById(@RequestBody Role role){
        Result result ;
        if(role.getId()!=null){
            Role delRole= new Role();
            delRole.setId(role.getId());
            //逻辑删除
            delRole.setYnFlag("0");
            delRole.setEditor(UserContext.getCurrentUser().getAccount());
            delRole.setModifiedTime(Date.from(Instant.now()));
            Role userRole= roleService.getById(role.getId());
            if (userRole.getName().equals("admin")){
                result = new Result(false, "管理员账号不能修改!", null , ResultEnum.ACCOUNT_CANNOT_UPDATE.getCode());
            }else{
                result=new Result(roleService.updateById(delRole),"删除成功",null,ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
            }
        }else{
            result = new Result(false, "删除失败", null , ResultEnum.PARAMETERS_MISSING.getCode());
        }
        return result;
    }

    /**
     * 授权，对角色进行添加/修改权限
     * @param roleAuth
     * @return
     */
    //@SysLogAnnotation
    @ApiOperation(value = "角色" ,  notes="授权，对角色进行添加/修改权限")
    @RequiresPermissions("sysmgr.role.save")
    @RequestMapping(value="/modifyAuth",method = {RequestMethod.POST})
    public Result dropById(@RequestBody RoleAuth roleAuth){
        return roleService.saveRoleAuths(roleAuth);
    }

    /**
     * 获取角色的权限
     * @param roleAuth
     * @return
     */
    @ApiOperation(value = "角色" ,  notes="获取角色的权限")
    @RequiresPermissions("sysmgr.role.query")
    @RequestMapping(value="/findRoleAuth",method = {RequestMethod.POST})
    public Result findRoleAuth(@RequestBody RoleAuth roleAuth){
        return roleService.selectAuthByRoleId(roleAuth.getRoleId());
    }


    /**
     * 获取所有角色
     * @return
     */
    @ApiOperation(value = "角色" ,  notes="获取所有角色")
    @RequiresPermissions("sysmgr.role.query")
    @RequestMapping(value="/listall",method = {RequestMethod.POST,RequestMethod.GET})
    public Result list(){
        QueryWrapper<Role> eWrapper = new QueryWrapper();
        eWrapper.eq("yn_flag","1");
        List<Role> list = roleService.list(eWrapper);

        Result result = new Result();
        result.setData(list);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

}
