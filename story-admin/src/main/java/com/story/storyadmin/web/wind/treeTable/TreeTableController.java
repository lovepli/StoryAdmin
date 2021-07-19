package com.story.storyadmin.web.wind.treeTable;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.domain.entity.wind.tree.TreeTable;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.service.wind.ITreeTableService;
import com.story.storyadmin.utils.wind.common.VueTreeHelper;
import com.story.storyadmin.web.wind.BaseBeanController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;


/**
 * All rights Reserved, Designed By www.sunseagear.com
 *
 * @version V1.0
 * @package test.treetable
 * @title: 树形结构表控制器
 * @description: 树形结构表控制器
 * @author: admin
 * @date: 2019-11-13 21:38:32
 * @copyright: www.sunseagear.com Inc. All rights reserved.
 */

@RestController
@RequestMapping("/test/treetable")
public class TreeTableController extends BaseBeanController<TreeTable> {

    @Autowired
    private ITreeTableService treeTableService;

    /**
     * 根据页码和每页记录数，以及查询条件动态加载数据
     *
     * @param
     * @throws IOException
     */
    @RequiresPermissions("test.treetable.list")
    @RequestMapping(value="/list",method = {RequestMethod.POST,RequestMethod.GET})
    public Result list() {
        Result result = new Result();
        //加入条件
        QueryWrapper<TreeTable> entityWrapper = new QueryWrapper<>();
        entityWrapper.eq("del_flag", "0");
        entityWrapper.orderByDesc( "create_date");
        // 预处理
        List<TreeTable> treeNodeList = treeTableService.selectList(entityWrapper);
        List<TreeTable> vueTreeNodes = VueTreeHelper.create().sort(treeNodeList);

        logger.info("查询出:{}",vueTreeNodes);
        result.setData(vueTreeNodes);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;

    }

    //@RequiresPermissions("test.treetable.save")
    //@RequestMapping(value="/save",method = {RequestMethod.POST})
    //public Result save(TreeTable entity, BindingResult result) {
    //    Result result2 ;
    //    this.checkError(entity, result);
    //    if(!StringUtils.isEmpty(entity.getId())){
    //        treeTableService.insertOrUpdate(entity);
    //        result2= new Result(true, "修改成功", null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
    //    }else{//添加
    //        treeTableService.insert(entity);
    //        result2= new Result(true, "添加成功", null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
    //    }
    //    return result2;
    //}


    @RequiresPermissions("test.treetable.save")
    @RequestMapping(value="/add",method = {RequestMethod.POST})
    public Result add(@RequestBody TreeTable entity, BindingResult result) {
        logger.info("查询出TreeTable:{}",entity.toString());
        // 验证错误
        this.checkError(entity, result);
        TreeTable treeTable =new TreeTable();
        treeTable.setId(entity.getId());
        treeTable.setName(entity.getName());
        treeTable.setGeocoding(entity.getGeocoding());
        treeTable.setPostalCode(entity.getPostalCode());
        treeTable.setParentId(entity.getParentId());
        logger.info("查询出ParentId:{}",entity.getParentId());
        treeTable.setRemarks(entity.getRemarks());
        treeTableService.insert(treeTable);
        return new Result(true, "添加成功", null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
    }
    @RequiresPermissions("test.treetable.save")
    @RequestMapping(value="/update",method = {RequestMethod.POST})
    public Result update(@RequestBody TreeTable entity, BindingResult result) {
        // 验证错误
        this.checkError(entity, result);
        treeTableService.insertOrUpdate(entity);
        return new Result(true, "更新成功", null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
    }


    @RequiresPermissions("test.table.query")
    @RequestMapping(value="/find",method = {RequestMethod.POST})
    public Result findById(@RequestBody TreeTable treeTable){ //RequestParam LONG id
        TreeTable treeTable2 = treeTableService.selectById(treeTable.getId());
        Result result = new Result();
        result.setData(treeTable2);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    @RequiresPermissions("test.treetable.delete")
    @RequestMapping(value="/delete",method = {RequestMethod.POST})
    public Result dropById(@RequestBody TreeTable treeTable){
        Result result ;
        // 物理删除
         treeTableService.deleteById(treeTable.getId());
        result=new Result(true,"删除成功",null,ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
 /**
        // 逻辑删除
        if(!StringUtils.isEmpty(treeTable.getId())){
            TreeTable delTable= new TreeTable();
            delTable.setId(treeTable.getId());
            delTable.setDelFlag("1");
            delTable.setUpdateDate(Date.from(Instant.now()));
            delTable.setUpdateBy(UserContext.getCurrentUser().getAccount());
            result=new Result(treeTableService.insertOrUpdate(delTable),"删除成功",null,ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }else{
            result = new Result(false, "删除失败", null , ResultEnum.PARAMETERS_MISSING.getCode());
        }
*/
        return result;
    }



    @RequiresPermissions("test.treetable.delete")
    @PostMapping("/batch/delete")
    public Result dropByIds(@RequestParam("ids") String[] ids){
        Result result ;
//        // 删除数组集合，直接删除数据库中的数据
        List<Serializable> idList = java.util.Arrays.asList(ids);
        treeTableService.deleteBatchIds(idList);
        result=new Result(true,"批量删除成功",null,ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
/**
        // 逻辑删除
        TreeTable delTable= null;
        // 遍历删除
        if(ids!=null || ids.length >0){
            for (String id:ids){
                delTable= new TreeTable();
                delTable.setId(id);
                delTable.setDelFlag("1");
                delTable.setUpdateDate(Date.from(Instant.now()));
                delTable.setUpdateBy(UserContext.getCurrentUser().getAccount());
                treeTableService.insertOrUpdate(delTable);
            }
            result=new Result(true,"批量删除成功",null,ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }else{
            result = new Result(false, "批量删除失败", null ,ResultEnum.PARAMETERS_MISSING.getCode());
        }
 */
        return result;
    }


}
