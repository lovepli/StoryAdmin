package com.story.storyadmin.web.wind.table;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.domain.entity.wind.Table;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.domain.vo.wind.TableDto;
import com.story.storyadmin.service.wind.ITableService;
import com.story.storyadmin.utils.wind.StringUtils;
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
 * @package com.sunseagear.wind.modules.sys.controller
 * @title: 操作日志控制器
 * @description: 操作日志控制器 * @date: 2018-09-30 15:53:25
 * @copyright: 2018 www.sunseagear.com Inc. All rights reserved.
 */

@RestController
@RequestMapping("test/table/table")
@RequiresPermissions("test:table:table")
public class TableController{

    private static final Logger logger = LoggerFactory.getLogger(TableController.class);

    @Autowired
    private ITableService tableService;

    /**
     * 根据页码和每页记录数，以及查询条件动态加载数据
     *
     * @param
     * @throws IOException
     */
    @PostMapping(value = "list")
    @RequiresPermissions("test:table:table:list")
    public Result list(TableDto tableDto,
                       @RequestParam(defaultValue = "1")int pageNo,
                       @RequestParam(defaultValue = "10")int limit){

        Result result = new Result();
        Page<Table> page = new Page(pageNo, limit);
        //查询参数对象，加入条件
        QueryWrapper<Table> entityWrapper = new QueryWrapper<>();
        //查询出有效的，del_flag为1表示逻辑删除
        //entityWrapper.eq("del_flag", "0");
        entityWrapper.orderByDesc( "create_date");

        String title = tableDto.getTitle();
        if (!StringUtils.isEmpty(title)) {
            entityWrapper.eq("title", title);
        }
        Integer level = tableDto.getLevel();
        if (!StringUtils.isEmpty(title)) {
            entityWrapper.eq("level", level);
        }
        String type = tableDto.getType();
        if (!StringUtils.isEmpty(type)) {
            entityWrapper.eq("type", type);
        }
        // 预处理
        IPage<Table> list = tableService.page(page, entityWrapper);
        logger.info("查询出字典信息:{}",list);
        result.setData(list);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    @PostMapping("add")
    @RequiresPermissions("test:table:table:add")
    public Result save(@RequestBody Table table){
        Result result ;
        if(table.getId()!= null){
            table.setUpdateDate(Date.from(Instant.now()));
            table.setUpdateBy(UserContext.getCurrentUser().getAccount());
            tableService.updateById(table);
            result= new Result(true, "修改成功", table, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }else{//添加
            //父编码设置为空
            //wDict.setParentCode("");
            //标志为有效
            table.setDelFlag("0");
            //添加时间
            table.setCreateDate(Date.from(Instant.now()));
            table.setCreateBy(UserContext.getCurrentUser().getAccount());
            tableService.save(table);
            result= new Result(true, "添加成功", table, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }
        return result;
    }


    @PostMapping("delete/{id}")
    @RequiresPermissions("test:table:table:delete")
    public Result dropById(@RequestBody Table table){
        Result result ;
        if(table.getId()!=null){
            Table delTable= new Table();
            delTable.setId(table.getId());
            delTable.setDelFlag("1");
            delTable.setUpdateDate(Date.from(Instant.now()));
            delTable.setUpdateBy(UserContext.getCurrentUser().getAccount());
            result=new Result(tableService.updateById(delTable),"删除成功",null,ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }else{
            result = new Result(false, "删除失败", null , ResultEnum.PARAMETERS_MISSING.getCode());
        }
        return result;
    }


//    @GetMapping("detail/{id}")
//    @RequiresPermissions("test:table:table:detail")
//    public String detail(@PathVariable("id") String id) {
//        Table tableEntity = tableService.selectById(id);
//        tableEntity.setContent(StringEscapeUtils.unescapeHtml4(tableEntity.getContent()));
//        return Response.successJson(tableEntity);
//    }

    @PostMapping("batch/delete")
    @RequiresPermissions("test:table:table:delete")
    public Result dropByIds(@RequestParam("ids") Long[] ids){
        Result result ;
        // 删除数组集合，直接删除数据库中的数据
        // dictService.deleteBatchIds(ids);
        Table delTable= null;
        // 遍历删除
        if(ids!=null || ids.length >0){
            for (Long id:ids){
                delTable= new Table();
                delTable.setId(id);
                delTable.setDelFlag("1");
                delTable.setUpdateDate(Date.from(Instant.now()));
                delTable.setUpdateBy(UserContext.getCurrentUser().getAccount());
                tableService.updateById(delTable);
            }
            result=new Result(true,"批量删除成功",null,ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }else{
            result = new Result(false, "批量删除失败", null ,ResultEnum.PARAMETERS_MISSING.getCode());
        }
        return result;
    }

}
