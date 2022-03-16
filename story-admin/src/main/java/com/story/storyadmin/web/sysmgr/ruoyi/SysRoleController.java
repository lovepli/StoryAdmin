package com.story.storyadmin.web.sysmgr.ruoyi;

import com.story.storyadmin.constant.sysmgr.ruoyi.UserConstants;
import com.story.storyadmin.constant.sysmgr.ruoyi.page.TableDataInfo;
import com.story.storyadmin.ruoyidomain.AjaxResult;
import com.story.storyadmin.ruoyidomain.entity.SysRole;
import com.story.storyadmin.service.sysmgr.ruoyi.ISysRoleService;
import com.story.storyadmin.service.sysmgr.ruoyi.ISysUserService;
import com.story.storyadmin.utils.ruoyiutils.SecurityUtils;
import com.story.storyadmin.web.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色信息
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/sysmgr/wrole")
public class SysRoleController extends BaseController
{
    @Autowired
    private ISysRoleService roleService;

    //@Autowired
    //private TokenService tokenService;
    //
    //@Autowired
    //private SysPermissionService permissionService;
    
    @Autowired
    private ISysUserService userService;

    @RequiresPermissions("sysmgr.wrole.list")
    @GetMapping("/list")
    public TableDataInfo list(SysRole role)
    {
        startPage();
        List<SysRole> list = roleService.selectRoleList(role);
        return getDataTable(list);
    }

    //@RequiresPermissions("sysmgr.wrole.export")
    //@GetMapping("/export")
    //public AjaxResult export(SysRole role)
    //{
    //    List<SysRole> list = roleService.selectRoleList(role);
    //    ExcelUtil<SysRole> util = new ExcelUtil<SysRole>(SysRole.class);
    //    return util.exportExcel(list, "角色数据");
    //}

    /**
     * 根据角色编号获取详细信息
     */
    @RequiresPermissions("sysmgr.wrole.query")
    @GetMapping(value = "/{roleId}")
    public AjaxResult getInfo(@PathVariable Long roleId)
    {
        return AjaxResult.success(roleService.selectRoleById(roleId));
    }

    /**
     * 新增角色
     */
    @RequiresPermissions("sysmgr.wrole.add")
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysRole role)
    {
        if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role)))
        {
            return AjaxResult.error("新增角色'" + role.getRoleName() + "'失败，角色名称已存在");
        }
        else if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(role)))
        {
            return AjaxResult.error("新增角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }
        role.setCreateBy(SecurityUtils.getUsername());
        return toAjax(roleService.insertRole(role));

    }

    /**
     * 修改保存角色
     */
    @RequiresPermissions("sysmgr.wrole.edit")
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysRole role)
    {
        roleService.checkRoleAllowed(role);
        if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role)))
        {
            return AjaxResult.error("修改角色'" + role.getRoleName() + "'失败，角色名称已存在");
        }
        else if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(role)))
        {
            return AjaxResult.error("修改角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }
        role.setUpdateBy(SecurityUtils.getUsername());
        
        if (roleService.updateRole(role) > 0)
        {
            //// 更新缓存用户权限
            //LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
            //if (StringUtils.isNotNull(loginUser.getUser()) && !loginUser.getUser().isAdmin())
            //{
            //    loginUser.setPermissions(permissionService.getMenuPermission(loginUser.getUser()));
            //    loginUser.setUser(userService.selectUserByUserName(loginUser.getUser().getUserName()));
            //    tokenService.setLoginUser(loginUser);
            //}
            return AjaxResult.success();
        }
        return AjaxResult.error("修改角色'" + role.getRoleName() + "'失败，请联系管理员");
    }

    /**
     * 修改保存数据权限
     */
    @RequiresPermissions("sysmgr.wrole.edit")
    @PutMapping("/dataScope")
    public AjaxResult dataScope(@RequestBody SysRole role)
    {
        roleService.checkRoleAllowed(role);
        return toAjax(roleService.authDataScope(role));
    }

    /**
     * 状态修改
     */
    @RequiresPermissions("sysmgr.wrole.edit")
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody SysRole role)
    {
        roleService.checkRoleAllowed(role);
        role.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(roleService.updateRoleStatus(role));
    }

    /**
     * 删除角色
     */
    @RequiresPermissions("sysmgr.wrole.remove")
    @DeleteMapping("/{roleIds}")
    public AjaxResult remove(@PathVariable Long[] roleIds)
    {
        return toAjax(roleService.deleteRoleByIds(roleIds));
    }

    /**
     * 获取角色选择框列表
     */
    @RequiresPermissions("sysmgr.wrole.query")
    @GetMapping("/optionselect")
    public AjaxResult optionselect()
    {
        return AjaxResult.success(roleService.selectRoleAll());
    }
}
