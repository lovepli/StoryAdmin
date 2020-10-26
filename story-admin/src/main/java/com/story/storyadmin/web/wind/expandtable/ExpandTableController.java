package com.story.storyadmin.web.wind.expandtable;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.domain.entity.wind.ExpandTable;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.service.wind.IExpandTableService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;



/**
 * All rights Reserved, Designed By www.sunseagear.com
 *
 * @version V1.0
 * @package test.expandtable
 * @title: 商品信息控制器
 * @description: 商品信息控制器
 * @author: admin
 * @date: 2019-11-13 15:02:00
 * @copyright: www.sunseagear.com Inc. All rights reserved.
 */

@RestController
@RequestMapping("test/expandtable/expandtable")
public class ExpandTableController  {

    private static final Logger logger = LoggerFactory.getLogger(ExpandTableController.class);
    @Autowired
    private IExpandTableService expandTableService;

    /**
     * 根据页码和每页记录数，以及查询条件动态加载数据
     *
     * @param
     * @throws IOException
     */
    @PostMapping(value = "list")
    @RequiresPermissions("test:expandtable:expandtable:list")
    public Result list(ExpandTable expandTable,
                       @RequestParam(defaultValue = "1")int pageNo,
                       @RequestParam(defaultValue = "10")int limit) throws IOException {
        Result result = new Result();
        Page<ExpandTable> page = new Page(pageNo, limit);
        //查询参数对象，加入条件
        //加入条件
        QueryWrapper<ExpandTable> entityWrapper = new QueryWrapper<>();
        entityWrapper.orderByDesc( "create_date");
        //查询出有效的，del_flag为1表示逻辑删除
        //entityWrapper.eq("del_flag", "0");


        // 预处理
        IPage<ExpandTable> list = expandTableService.page(page, entityWrapper);
        logger.info("查询出字典信息:{}",list);
        result.setData(list);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    @PostMapping("save")
    @RequiresPermissions("test:expandtable:expandtable:save")
    public Result save(@RequestBody ExpandTable expandTable){
        Result result ;
        if(expandTable.getId()!= null){
            expandTable.setUpdateDate(Date.from(Instant.now()));
            expandTable.setUpdateBy(UserContext.getCurrentUser().getAccount());
            expandTableService.updateById(expandTable);
            result= new Result(true, "修改成功", expandTable, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }else{//添加
            //父编码设置为空
            //wDict.setParentCode("");
            //标志为有效
            expandTable.setDelFlag("0");
            //添加时间
            expandTable.setCreateDate(Date.from(Instant.now()));
            expandTable.setCreateBy(UserContext.getCurrentUser().getAccount());
            expandTableService.save(expandTable);
            result= new Result(true, "添加成功", expandTable, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }
        return result;
    }



    @PostMapping("delete/{id}")
    @RequiresPermissions("test:expandtable:expandtable:delete")
    public Result dropById(@RequestBody ExpandTable expandTable){
        Result result ;
        if(expandTable.getId()!=null){
            ExpandTable delDict= new ExpandTable();
            delDict.setId(expandTable.getId());
            delDict.setDelFlag("1");
            delDict.setUpdateDate(Date.from(Instant.now()));
            delDict.setUpdateBy(UserContext.getCurrentUser().getAccount());
            result=new Result(expandTableService.updateById(delDict),"删除成功",null,ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }else{
            result = new Result(false, "删除失败", null , ResultEnum.PARAMETERS_MISSING.getCode());
        }
        return result;
    }

    @PostMapping("batch/delete")
    @RequiresPermissions("test:expandtable:expandtable:delete")
    public Result dropByIds(@RequestParam("ids") Long[] ids){
        Result result ;
        // 删除数组集合，直接删除数据库中的数据
        // dictService.deleteBatchIds(ids);
        ExpandTable delexpandTable= null;
        // 遍历删除
        if(ids!=null || ids.length >0){
            for (Long id:ids){
                delexpandTable= new ExpandTable();
                delexpandTable.setId(id);
                delexpandTable.setDelFlag("1");
                delexpandTable.setUpdateDate(Date.from(Instant.now()));
                delexpandTable.setUpdateBy(UserContext.getCurrentUser().getAccount());
                expandTableService.updateById(delexpandTable);
            }
            result=new Result(true,"批量删除成功",null,ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }else{
            result = new Result(false, "批量删除失败", null ,ResultEnum.PARAMETERS_MISSING.getCode());
        }
        return result;
    }
}
