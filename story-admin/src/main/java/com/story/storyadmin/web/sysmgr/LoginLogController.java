package com.story.storyadmin.web.sysmgr;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.story.storyadmin.config.mongo.SysLogAnnotation;
import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.constant.Constants;
import com.story.storyadmin.domain.entity.sysmgr.Inform;
import com.story.storyadmin.domain.entity.sysmgr.LoginLog;
import com.story.storyadmin.domain.entity.sysmgr.User;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.service.sysmgr.LoginLogService;
import com.story.storyadmin.utils.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;

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
        result.setData(list);
        result.setResult(true);
        result.setCode(Constants.TOKEN_CHECK_SUCCESS);
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
            result=new Result(loginLogService.updateById(delLog),null,null,Constants.TOKEN_CHECK_SUCCESS);
        }else{
            result = new Result(false, "", null ,Constants.PARAMETERS_MISSING);
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
        result.setCode(Constants.TOKEN_CHECK_SUCCESS);
        return result;
    }

}
