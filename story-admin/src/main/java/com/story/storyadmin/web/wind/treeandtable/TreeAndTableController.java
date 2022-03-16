package com.story.storyadmin.web.wind.treeandtable;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.domain.entity.wind.TreeAndTable;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.domain.vo.wind.TreeAndTableDto;
import com.story.storyadmin.service.wind.ITreeAndTableService;
import com.story.storyadmin.utils.wind.StringUtils;
import com.story.storyadmin.web.BaseController;
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
 * @package test.treeandtable
 * @title: 左树右表控制器
 * @description: 左树右表控制器
 * @author: admin
 * @date: 2019-11-13 21:24:49
 * @copyright: www.sunseagear.com Inc. All rights reserved.
 */

@RestController
@RequestMapping("/test/treeandtable")
public class TreeAndTableController  extends BaseController {

    @Autowired
    private ITreeAndTableService treeAndTableService;

    /**
     * 根据页码和每页记录数，以及查询条件动态加载数据
     *
     * @param
     * @throws IOException
     */
    @RequestMapping(value="/list",method = {RequestMethod.POST,RequestMethod.GET})
    @RequiresPermissions("test.treeandtable.list")
    public Result list(TreeAndTableDto treeAndTableDto,
                       @RequestParam(defaultValue = "1")int pageNo,
                       @RequestParam(defaultValue = "10")int limit){
        //加入条件
        Result result = new Result();
        Page<TreeAndTable> page = new Page(pageNo, limit);
        //查询参数对象，加入条件
        QueryWrapper<TreeAndTable> entityWrapper = new QueryWrapper<>();
        entityWrapper.eq("del_flag", "0");
        entityWrapper.orderByDesc( "create_date");
        String name = treeAndTableDto.getName();
        if (!StringUtils.isEmpty(name)) {
            entityWrapper.eq("name", name);
        }
        String type = treeAndTableDto.getType();
        if (!StringUtils.isEmpty(type)) {
            entityWrapper.eq("type", type);
        }
        String areaId = treeAndTableDto.getAreaId();
        if (!StringUtils.isEmpty(areaId)) {
            entityWrapper.eq("area_id", areaId);
        }
        // 预处理
        IPage<TreeAndTable> list = treeAndTableService.page(page, entityWrapper);
        logger.info("查询出字典信息:{}",list);
        result.setData(list);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    @RequiresPermissions("test.treeandtable.save")
    @RequestMapping(value="/save",method = {RequestMethod.POST})
    public Result save(@RequestBody TreeAndTable treeAndTable){
        Result result ;
        if(treeAndTable.getId()!= null){
            treeAndTable.setUpdateDate(Date.from(Instant.now()));
            treeAndTable.setUpdateBy(UserContext.getCurrentUser().getAccount());
            treeAndTableService.updateById(treeAndTable);
            result= new Result(true, "修改成功", treeAndTable, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }else{//添加
            //标志为有效
            treeAndTable.setDelFlag("0");
            //添加时间
            treeAndTable.setCreateDate(Date.from(Instant.now()));
            treeAndTable.setCreateBy(UserContext.getCurrentUser().getAccount());
            treeAndTableService.save(treeAndTable);
            result= new Result(true, "添加成功", treeAndTable, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }
        return result;
    }


    @RequiresPermissions("test.treeandtable.delete")
    @RequestMapping(value="/delete",method = {RequestMethod.POST})
    public Result dropById(@RequestBody TreeAndTable treeAndTable){
        Result result ;
        if(treeAndTable.getId()!=null){
            TreeAndTable delTable= new TreeAndTable();
            delTable.setId(treeAndTable.getId());
            delTable.setDelFlag("1");
            delTable.setUpdateDate(Date.from(Instant.now()));
            delTable.setUpdateBy(UserContext.getCurrentUser().getAccount());
            result=new Result(treeAndTableService.updateById(delTable),"删除成功",null,ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }else{
            result = new Result(false, "删除失败", null , ResultEnum.PARAMETERS_MISSING.getCode());
        }
        return result;
    }

    @RequiresPermissions("test.treeandtable.query")
    @RequestMapping(value="/find",method = {RequestMethod.POST})
    public Result findById(@RequestBody TreeAndTable treeAndTable){ //RequestParam LONG id
        TreeAndTable treeAndTable2 = treeAndTableService.getById(treeAndTable.getId());
        Result result = new Result();
        result.setData(treeAndTable2);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    @RequiresPermissions("test.treeandtable.delete")
    @PostMapping("/batch/delete")
    public Result dropByIds(@RequestParam("ids") Long[] ids){
        Result result ;
        // 删除数组集合，直接删除数据库中的数据
        // dictService.deleteBatchIds(ids);
        TreeAndTable delTable= null;
        // 遍历删除
        if(ids!=null || ids.length >0){
            for (Long id:ids){
                delTable= new TreeAndTable();
//                delTable.setId(id);
                delTable.setDelFlag("1");
                delTable.setUpdateDate(Date.from(Instant.now()));
                delTable.setUpdateBy(UserContext.getCurrentUser().getAccount());
                treeAndTableService.updateById(delTable);
            }
            result=new Result(true,"批量删除成功",null,ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }else{
            result = new Result(false, "批量删除失败", null ,ResultEnum.PARAMETERS_MISSING.getCode());
        }
        return result;
    }
}
