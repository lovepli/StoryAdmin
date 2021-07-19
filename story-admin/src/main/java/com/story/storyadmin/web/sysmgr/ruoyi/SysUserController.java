package com.story.storyadmin.web.sysmgr.ruoyi;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.constant.sysmgr.ruoyi.UserConstants;
import com.story.storyadmin.constant.sysmgr.ruoyi.page.TableDataInfo;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.ruoyidomain.AjaxResult;
import com.story.storyadmin.ruoyidomain.entity.SysRole;
import com.story.storyadmin.ruoyidomain.entity.SysUser;
import com.story.storyadmin.service.sysmgr.ruoyi.ISysPostService;
import com.story.storyadmin.service.sysmgr.ruoyi.ISysRoleService;
import com.story.storyadmin.service.sysmgr.ruoyi.ISysUserService;
import com.story.storyadmin.utils.ruoyiutils.StringUtils;
import com.story.storyadmin.utils.wind.DateUtils;
import com.story.storyadmin.web.BaseController;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户信息
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/sysmgr/wuser")
public class SysUserController extends BaseController {
    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysPostService postService;

//    @Autowired
//    private TokenService tokenService;

    /**
     * 获取用户列表
     */
    @RequiresPermissions("sysmgr.wuser.list")
    @GetMapping("/list")
    public TableDataInfo list(SysUser user) {
        startPage();
        List<SysUser> list = userService.selectUserList(user);
        return getDataTable(list);
    }

    /**
     * 导出当前用户数据 传递列表数据给前端，前端控制导出
     */
    @RequiresPermissions("sysmgr.wuser.export")
    @GetMapping("/export")
    public AjaxResult export(SysUser user) {
        startPage();
        HashMap<String, Object> response = new HashMap<>();
        try {
            //TemplateExportParams params = new TemplateExportParams("");
            List<SysUser> list = userService.selectUserList(user);
            String title = "用户信息";
            Workbook book = ExcelExportUtil.exportExcel(new ExportParams(
                    title, title, title), SysUser.class, list);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            book.write(bos);
            byte[] bytes = bos.toByteArray();
            String bytesRes = com.story.storyadmin.utils.wind.StringUtils.bytesToHexString2(bytes);
            title = title + "-" + DateUtils.getDateTime();
            response.put("bytes", bytesRes);
            response.put("title", title);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("导出失败", e);
            return AjaxResult.error("导出失败！"); // TODO 补充错误状态码
        }
        return AjaxResult.success("导出成功",response);  // 补充成功态码
    }

//    @RequiresPermissions("sysmgr.user.export")
//    @GetMapping("/export")
//    public AjaxResult export(SysUser user) {
//        List<SysUser> list = userService.selectUserList(user);
//        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
//        return util.exportExcel(list, "用户数据");
//    }


//    @RequiresPermissions("sysmgr.user.import")
//    @PostMapping("/importData")
//    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
//        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
//        List<SysUser> userList = util.importExcel(file.getInputStream());
//        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
//        String operName = loginUser.getUsername();
//        String message = userService.importUser(userList, updateSupport, operName);
//        return AjaxResult.success(message);
//    }

    /**
     * 下载用户导入模板
     *
     * @return
     */
//    @GetMapping("/importTemplate")
//    public AjaxResult importTemplate() {
//        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
//        return util.importTemplateExcel("用户数据");
//    }

    /**
     * 根据用户编号获取详细信息
     */
    @RequiresPermissions("sysmgr.wuser.query")
    @GetMapping(value = {"/", "/{userId}"})
    public AjaxResult getInfo(@PathVariable(value = "userId", required = false) Long userId) {
        AjaxResult ajax = AjaxResult.success();
        List<SysRole> roles = roleService.selectRoleAll();
        ajax.put("roles", SysUser.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        ajax.put("posts", postService.selectPostAll());
        if (StringUtils.isNotNull(userId)) {
            ajax.put(AjaxResult.DATA_TAG, userService.selectUserById(userId));
            ajax.put("postIds", postService.selectPostListByUserId(userId));
            ajax.put("roleIds", roleService.selectRoleListByUserId(userId));
        }
        return ajax;
    }

    /**
     * 新增用户
     */
    @RequiresPermissions("sysmgr.wuser.add")
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysUser user) {
        if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user.getUserName()))) {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，登录账号已存在");
        } else if (UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，手机号码已存在");
        } else if (UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        user.setCreateBy(UserContext.getCurrentUser().getAccount());
//        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        return toAjax(userService.insertUser(user));
    }

    /**
     * 修改用户
     */
    @RequiresPermissions("sysmgr.wuser.update")
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysUser user) {
        userService.checkUserAllowed(user);
        if (UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
        } else if (UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
       // user.setUpdateBy(SecurityUtils.getUsername());
        user.setUpdateBy(UserContext.getCurrentUser().getAccount());
        return toAjax(userService.updateUser(user));
    }

    /**
     * 删除用户
     */
    @RequiresPermissions("sysmgr.wuser.remove")
    @DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds) {
        return toAjax(userService.deleteUserByIds(userIds));
    }

    /**
     * 重置密码
     */
    @RequiresPermissions("sysmgr.wuser.edit")
    @PutMapping("/resetPwd")
    public AjaxResult resetPwd(@RequestBody SysUser user) {
        userService.checkUserAllowed(user);
//        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
//        user.setUpdateBy(SecurityUtils.getUsername());
        user.setUpdateBy(UserContext.getCurrentUser().getAccount());
        return toAjax(userService.resetPwd(user));
    }

    /**
     * 状态修改
     */
    @RequiresPermissions("sysmgr.wuser.edit")
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody SysUser user) {
        userService.checkUserAllowed(user);
//        user.setUpdateBy(SecurityUtils.getUsername());
        user.setUpdateBy(UserContext.getCurrentUser().getAccount());
        return toAjax(userService.updateUserStatus(user));
    }

}
