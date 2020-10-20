package com.story.storyadmin.web.sysmgr;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.story.storyadmin.common.exception.CustomException;
import com.story.storyadmin.config.mongo.SysLogAnnotation;
import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.constant.Constants;
import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.domain.entity.sysmgr.Inform;
import com.story.storyadmin.domain.entity.sysmgr.LoginLog;
import com.story.storyadmin.domain.entity.sysmgr.User;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.service.sysmgr.LoginLogService;
import com.story.storyadmin.utils.DateUtil;
import com.story.storyadmin.utils.MethodUtil;
import com.story.storyadmin.utils.ruoyiutils.reflect.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * <p>
 * 登录日志 前端控制器
 * </p>
 *
 * @author sunnj
 * @since 2019-07-26
 */
@Api(description = "登录日志")
@RestController
@RequestMapping("/sysmgr/loginlog")
public class LoginLogController {

    private static final Logger logger = LoggerFactory.getLogger(LoginLogController.class);

    @Autowired
    LoginLogService loginLogService;

    /**
     * 分页查询
     * @param loginLog
     * @param pageNo
     * @param limit
     * @return
     */
    @ApiOperation(value = "登录日志" ,  notes="查询登录日志列表")
    @RequiresPermissions("sysmgr.loginlog.query")
    @RequestMapping(value="/list",method = {RequestMethod.POST,RequestMethod.GET})
    public Result list(LoginLog loginLog,
                       @RequestParam(defaultValue = "1")int pageNo,
                       @RequestParam(defaultValue = "10")int limit){
        Result result = new Result();
        Page<LoginLog> page = new Page(pageNo, limit);
        QueryWrapper<LoginLog> eWrapper = new QueryWrapper(loginLog);
        eWrapper.eq("yn_flag","1");
        IPage<LoginLog> list = loginLogService.page(page, eWrapper);
        logger.info("",list.getRecords());
        result.setData(list);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    /**
     * 删除
     * @param user
     * @return
     */
    @SysLogAnnotation
    @ApiOperation(value = "登录日志" ,  notes="删除登录日志列表")
    @RequiresPermissions("sysmgr.loginlog.delete")
    @RequestMapping(value="/delete",method = {RequestMethod.POST})
    public Result dropById(@RequestBody User user){
        Result result ;
        if(user.getId()!=null){
            LoginLog delLog= new LoginLog();
            delLog.setId(user.getId());
            delLog.setYnFlag("0");
            delLog.setEditor(UserContext.getCurrentUser().getAccount());
            delLog.setModifiedTime(Date.from(Instant.now()));
            result=new Result(loginLogService.updateById(delLog),"删除成功",null,ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }else{
            result = new Result(false, "删除失败", null , ResultEnum.PARAMETERS_MISSING.getCode());
        }
        return result;
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @SysLogAnnotation
    @ApiOperation(value = "登录日志" ,  notes="批量删除登录日志列表")
    @RequiresPermissions("sysmgr.loginlog.delete")
    @DeleteMapping(value="/deletes/{ids}")
    public Result dropByIds(@PathVariable Long[] ids){
        Result result ;
        // 删除数组集合，直接删除数据库中的数据
        // loginLogService.deleteLogininforByIds(ids);
        LoginLog delLog= null;
        // 遍历删除
        if(ids!=null || ids.length >0){
            for (Long id:ids){
                delLog= new LoginLog();
                delLog.setId(id);
                delLog.setYnFlag("0");
                delLog.setEditor(UserContext.getCurrentUser().getAccount());
                delLog.setModifiedTime(Date.from(Instant.now()));
                loginLogService.updateById(delLog);
            }
            result=new Result(true,"批量删除成功",null,ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }else{
            result = new Result(false, "批量删除失败", null ,ResultEnum.PARAMETERS_MISSING.getCode());
        }
        return result;
    }
    /**
     * 通过用户名查询登录日志
     * @param userAccount
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "登录日志" ,  notes="通过用户名查询登录日志")
    @RequiresPermissions("sysmgr.loginlog.query")
    @RequestMapping(value = "/querylogsByAccount", method = GET)
    public Result list(@RequestParam(value = "ua", required = false) String userAccount,
                       @RequestParam(value = "page", defaultValue = "1") int pageNum,
                       @RequestParam(value = "size", defaultValue = "10") int pageSize) {
        Result result = new Result();
        LoginLog loginLog=new LoginLog();
        Page<LoginLog> page = new Page(pageNum, pageSize);
        QueryWrapper<LoginLog> eWrapper = new QueryWrapper(loginLog);
        eWrapper.eq("account",userAccount);
        eWrapper.eq("yn_flag","1");
        eWrapper.orderByDesc("login_time");
        IPage<LoginLog> list = loginLogService.page(page, eWrapper);
        result.setData(list);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    @ApiOperation(value = "登录日志" ,  notes="导出登录日志")
    @RequiresPermissions("sysmgr.loginlog.query")
    @GetMapping("/export")
    public Result export(LoginLog loginLog,
                         @RequestParam(defaultValue = "1")int pageNo,
                         @RequestParam(defaultValue = "10")int limit)
    {

        Page<LoginLog> page = new Page(pageNo, limit);
        QueryWrapper<LoginLog> eWrapper = new QueryWrapper(loginLog);
        eWrapper.eq("yn_flag","1");
        IPage<LoginLog> list = loginLogService.page(page, eWrapper);
        List<LoginLog> list2=list.getRecords();
        ExcelUtil<LoginLog> util = new ExcelUtil<LoginLog>(LoginLog.class);
       logger.info("返回的结果为：{}",util.exportExcel(list2, "登陆日志").getMessage());
        return util.exportExcel(list2, "登陆日志");
    }

}
