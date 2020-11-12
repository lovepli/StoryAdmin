package com.story.storyadmin.web.sysmgr;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.story.storyadmin.config.mongo.SysLogAnnotation;
import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.domain.entity.sysmgr.User;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.domain.vo.sysmgr.UserDo;
import com.story.storyadmin.domain.vo.sysmgr.UserPassword;
import com.story.storyadmin.domain.vo.sysmgr.UserRoleVo;
import com.story.storyadmin.ruoyidomain.entity.SysUser;
import com.story.storyadmin.service.sysmgr.ImageFileService;
import com.story.storyadmin.service.sysmgr.UserService;
import com.story.storyadmin.utils.ruoyiutils.StringUtils;
import com.story.storyadmin.utils.wind.DateUtils;
import com.story.storyadmin.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Api(description = "用户管理")
@RestController
@RequestMapping(value="/sysmgr/user")
public class UserController extends BaseController {

    @Autowired
    UserService userService;

    @Autowired
    ImageFileService imageFileService;

    /**
     * 分页查询 用户列表信息
     * @param user
     * @param pageNo
     * @param limit
     * @return
     */
    @ApiOperation(value = "用户信息" ,  notes="查询用户列表信息")
    @RequiresPermissions("sysmgr.user.query")
    @RequestMapping(value="/list",method = {RequestMethod.POST,RequestMethod.GET})
    public Result list(User user,
                       @RequestParam(defaultValue = "1")int pageNo,
                       @RequestParam(defaultValue = "10")int limit){
        Result result = new Result();
        Page<User> page = new Page(pageNo, limit);
        //查询参数对象，查询出有效的，yn_flag为0表示逻辑删除
        QueryWrapper<User> eWrapper = new QueryWrapper(user);
        eWrapper.eq("yn_flag", "1");
        IPage<User> list = userService.page(page, eWrapper);
        logger.info("查询出用户信息:{}",list);
        result.setData(list);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }


    /**
     * 根据Id查询用户信息
     * @param user
     * @return
     */
    @ApiOperation(value = "用户信息" ,  notes="根据Id查询用户信息")
    @RequiresPermissions("sysmgr.user.query")
    @RequestMapping(value="/find",method = {RequestMethod.POST})
    public Result findById(@RequestBody User user){
        User userBean= userService.getById(user.getId());
        //详情信息，隐藏用户的密码显示
        userBean.setPassword("********");

        Result result = new Result();
        result.setData(userBean);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }


    /**
     * 保存
     * @param user
     * @return
     */
    @SysLogAnnotation
    @ApiOperation(value = "用户信息" ,  notes="保存用户信息")
    @RequiresPermissions("sysmgr.user.save")
    @RequestMapping(value="/save",method = {RequestMethod.POST})
    public Result save(@RequestBody User user){
        return userService.persist(user);
    }

    /**
     * 删除 这里的删除是进行修改操作的逻辑删除
     * @param user
     * @return
     */
    @Transactional
    @SysLogAnnotation
    @ApiOperation(value = "用户信息" ,  notes="删除用户信息")
    @RequiresPermissions("sysmgr.user.delete")
    @RequestMapping(value="/delete",method = {RequestMethod.POST})
    public Result dropById(@RequestBody User user){
        Result result ;
        if(user.getId()!=null){
            User delUser= new User();
            delUser.setId(user.getId());
            //将标志置为无效
            delUser.setYnFlag("0");
            delUser.setEditor(UserContext.getCurrentUser().getAccount());
            delUser.setModifiedTime(Date.from(Instant.now()));
            // 根据ID查找用户
            User userInfo=userService.selectUserById(user.getId());
            if (userInfo.getAccount().equals("admin")){
                result = new Result(false, "管理员账号不能修改!", null ,ResultEnum.ACCOUNT_CANNOT_UPDATE.getCode());
            }else {
                // 根据用户名删除用户图片记录
                imageFileService.deleteImage(user.getAvatar());
                result=new Result(userService.updateById(delUser),"删除成功",null,ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
            }
        }else{
            result = new Result(false, "删除失败", null ,ResultEnum.PARAMETERS_MISSING.getCode());
        }
        return result;
    }

    /**
     * 根据用户Id查询角色
     * @param user
     * @return
     */
    @ApiOperation(value = "用户信息" ,  notes="根据用户Id查询角色")
    @RequiresPermissions("sysmgr.user.query")
    @RequestMapping(value="/findUserRole",method = {RequestMethod.POST})
    public Result findUserRole(@RequestBody UserRoleVo user){
        return userService.findUserRole(user.getUserId());
    }

    /**
     * 根据用户名查询所有角色
     * @return
     */
    @ApiOperation(value = "用户信息" ,  notes="根据用户名查询所有角色")
    @RequiresPermissions("sysmgr.user.query")
    @RequestMapping(value="/findRolelistByAccount",method = {RequestMethod.POST,RequestMethod.GET})
    public Result list(@RequestParam(value = "ua") String userName){
        return userService.findUserRole(userName);
    }

    /**
     * 更改/保存用户角色
     * @param userRole
     * @return
     */
    @SysLogAnnotation
    @ApiOperation(value = "用户信息" ,  notes="更改/保存用户角色")
    @RequiresPermissions("sysmgr.user.save")
    @RequestMapping(value="/saveUserRole",method = {RequestMethod.POST})
    public Result saveUserRole(@RequestBody UserRoleVo userRole){
        return userService.saveUserRoles(userRole);
    }

    /**
     * 修改密码
     * @param userPassword
     * @return
     */
    @SysLogAnnotation
    @ApiOperation(value = "用户信息" ,  notes="修改密码")
    @RequiresAuthentication
    @RequestMapping(value="/editpassword",method = {RequestMethod.POST})
    public Result editPassWord(@RequestBody UserPassword userPassword){
        return userService.editPassWord(userPassword);
    }

    /**
     * 查询用户名列表
     * @return
     */
    @ApiOperation(value = "用户信息" ,  notes="查询用户名列表")
    @RequiresPermissions("sysmgr.user.query")
    @RequestMapping(value="/UserNameList",method = {RequestMethod.POST,RequestMethod.GET})
    public Result UserNameList(){
        Result result = new Result();
        // TODO 这里返回的数据不包含ID
       // List<User> list=userService.selectUserNameList();
       // List<User> list=userService.list();
        // 模拟数据
        List<UserDo> list=new ArrayList<>();
        list.add(new UserDo((long) 1,"张三"));
        list.add(new UserDo((long) 2,"李四"));

        logger.info("查询出的用户信息为:{}",list);
        result.setData(list);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    /**
     * user和sysUser不一致
     */
    //@GetMapping("export")
    //@RequiresPermissions("sys:user:export")
    //public String export(SysUser user,
    //                     @RequestParam(defaultValue = "1")int pageNo,
    //                     @RequestParam(defaultValue = "10")int limit) {
    //    HashMap<String, Object> response = new HashMap<>();
    //    try {
    //        TemplateExportParams params = new TemplateExportParams(
    //                "");
    //        //加入条件
    //        QueryWrapper<SysUser> entityWrapper = new QueryWrapper<>();
    //        // 子查询
    //        if (!StringUtils.isNull(user.getDeptId())) {
    //            entityWrapper.eq("dept_id", user.getDeptId());
    //        }
    //        Result result = new Result();
    //        Page<SysUser> page = new Page(pageNo, limit);
    //        IPage<SysUser> list = userService.page(page, entityWrapper);
    //        //Page pageBean = userService.selectPage(getPage(), entityWrapper);
    //        String title = "用户信息";
    //        Workbook book = ExcelExportUtil.exportExcel(new ExportParams(
    //                title, title, title), SysUser.class, pageBean.getRecords());
    //        ByteArrayOutputStream bos = new ByteArrayOutputStream();
    //        book.write(bos);
    //        byte[] bytes = bos.toByteArray();
    //        String bytesRes = com.story.storyadmin.utils.wind.StringUtils.bytesToHexString2(bytes);
    //        title = title + "-" + DateUtils.getDateTime();
    //        response.put("bytes", bytesRes);
    //        response.put("title", title);
    //    } catch (Exception e) {
    //        e.printStackTrace();
    //        return Response.error(ResponseError.NORMAL_ERROR, "导出失败");
    //    }
    //    return Response.toJson(response, "导出成功");
    //}
}
