package com.story.storyadmin.web.sysmgr;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.constant.Constants;
import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.domain.entity.sysmgr.SysLog;
import com.story.storyadmin.domain.entity.sysmgr.User;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.service.sysmgr.SysLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

/**
 * <p>
 * 登录日志 前端控制器
 * </p>
 *
 * @author sunnj
 * @since 2019-07-26
 */
@Api(description = "系统日志")
@RestController
@RequestMapping("/sysmgr/syslog")
public class SysLogController {

    @Autowired
    SysLogService sysLogService;

    /**
     * 分页查询，从MongoDB数据库中取出系统日志
     * @param sysLog
     * @param pageNo
     * @param limit
     * @return
     */
    @ApiOperation(value = "系统日志" ,  notes="查询系统日志列表")
    @RequiresPermissions("sysmgr.syslog.query")
    @RequestMapping(value="/list",method = {RequestMethod.POST,RequestMethod.GET})
    public Result list(SysLog sysLog,
                       @RequestParam(defaultValue = "1")int pageNo,
                       @RequestParam(defaultValue = "10")int limit){
        Result result = new Result();
        Page<SysLog> page = new Page(pageNo, limit);
        IPage<SysLog> list = sysLogService.findPage(page, sysLog);
        result.setData(list);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    /**
     * 根据Id查询
     * @param sysLog
     * @return
     */
    @ApiOperation(value = "系统日志" ,  notes="根据Id查询系统日志")
    @RequiresPermissions("sysmgr.syslog.query")
    @RequestMapping(value="/find",method = {RequestMethod.POST})
    public Result findById(@RequestBody SysLog sysLog){
        SysLog userBean= sysLogService.findById(sysLog.getId());
        Result result = new Result();
        result.setData(userBean);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    /**
     * 删除系统操作日志 待开发...
     */

}
