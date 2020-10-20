package com.story.storyadmin.web.sysmgr;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.story.storyadmin.config.mongo.SysLogAnnotation;
import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.domain.entity.sysmgr.Backup;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.service.sysmgr.BackupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;

/**
 * <p>
 * DB备份表 前端控制器
 * </p>
 *
 * @author sunnj
 * @since 2019-09-10
 */
@Api(description = "系统备份")
@RestController
@RequestMapping("/sysmgr/backup")
public class BackupController {

    @Autowired
    BackupService backupService;

    /**
     * 分页查询
     * @param backup
     * @param pageNo
     * @param limit
     * @return
     */
    @ApiOperation(value = "系统备份" ,  notes="查询系统备份列表")
    @RequiresPermissions("sysmgr.backup.query")
    @RequestMapping(value="/list",method = {RequestMethod.POST,RequestMethod.GET})
    public Result list(Backup backup,
                       @RequestParam(defaultValue = "1")int pageNo,
                       @RequestParam(defaultValue = "10")int limit){
        Result result = new Result();
        Page<Backup> page = new Page(pageNo, limit);
        QueryWrapper<Backup> eWrapper = new QueryWrapper(backup);
        eWrapper.eq("yn_flag","1");
        IPage<Backup> list = backupService.page(page, eWrapper);
        result.setData(list);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }


    /**
     * 根据Id查询
     * @param backup
     * @return
     */
    @ApiOperation(value = "系统备份" ,  notes="根据Id查询系统备份信息")
    @RequiresPermissions("sysmgr.backup.query")
    @RequestMapping(value="/find",method = {RequestMethod.POST})
    public Result findById(@RequestBody Backup backup){
        Backup backupBean= backupService.getById(backup.getId());
        Result result = new Result();
        result.setData(backupBean);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    /**
     * 删除
     * @param backup
     * @return
     */
    @SysLogAnnotation
    @ApiOperation(value = "系统备份" ,  notes="删除系统备份信息")
    @RequiresPermissions("sysmgr.backup.delete")
    @RequestMapping(value="/delete",method = {RequestMethod.POST})
    public Result dropById(@RequestBody Backup backup){
        Result result ;
        if(backup.getId()!=null){
            Backup delBackup= new Backup();
            delBackup.setId(backup.getId());
            delBackup.setYnFlag("0");
            delBackup.setEditor(UserContext.getCurrentUser().getAccount());
            delBackup.setModifiedTime(Date.from(Instant.now()));
            result=new Result(backupService.updateById(delBackup),"删除成功",null,ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }else{
            result = new Result(false, "删除失败", null , ResultEnum.PARAMETERS_MISSING.getCode());
        }
        return result;
    }
}
