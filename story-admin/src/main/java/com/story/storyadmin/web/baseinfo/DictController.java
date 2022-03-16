package com.story.storyadmin.web.baseinfo;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.story.storyadmin.config.mongo.SysLogAnnotation;
import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.constant.enumtype.YNFlagStatusEnum;
import com.story.storyadmin.domain.entity.baseinfo.Dict;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.service.baseinfo.DictService;
import com.story.storyadmin.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author sunnj
 * @since 2019-07-12
 */
@Api(description = "字典管理")
@RestController
@RequestMapping("/baseinfo/dict")
public class DictController extends BaseController {

    @Autowired
    DictService dictService;

    /**
     * 分页查询
     * @param dict
     * @param pageNo
     * @param limit
     * @return
     */
    @ApiOperation(value = "字典" ,  notes="查询字典列表")
    @RequiresPermissions("baseinfo.dict.query")
    @RequestMapping(value="/list",method = {RequestMethod.POST,RequestMethod.GET})
    public Result list(Dict dict,
                       @RequestParam(defaultValue = "1")int pageNo,
                       @RequestParam(defaultValue = "10")int limit){
        Result result = new Result();
        Page<Dict> page = new Page(pageNo, limit);
        QueryWrapper<Dict> eWrapper = new QueryWrapper(dict);
        eWrapper.eq("yn_flag","1");
        IPage<Dict> list = dictService.page(page, eWrapper);
        result.setData(list);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    /**
     * 根据Id查询
     * @param dict
     * @return
     */
    @ApiOperation(value = "字典" ,  notes="根据Id查询字典信息")
    @RequiresPermissions("baseinfo.dict.query")
    @RequestMapping(value="/find",method = {RequestMethod.POST})
    public Result findById(@RequestBody Dict dict){
        Dict rolebean= dictService.getById(dict.getId());
        Result result = new Result();
        result.setData(rolebean);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    /**
     * 保存/修改
     * @param dict
     * @return
     */
    @SysLogAnnotation
    @ApiOperation(value = "字典" ,  notes="保存/修改字典信息")
    @RequiresPermissions("baseinfo.dict.save")
    @RequestMapping(value="/save",method = {RequestMethod.POST})
    public Result save(@RequestBody Dict dict){
        Result result ;
        if(dict.getId()!= null){
            dict.setModifiedTime(Date.from(Instant.now()));
            dict.setEditor(UserContext.getCurrentUser().getAccount());
            dictService.updateById(dict);
            result= new Result(true, "修改成功", dict, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }else{//添加
            //父编码设置为空
            dict.setParentCode("");
            //标志为有效
            dict.setYnFlag(YNFlagStatusEnum.VALID.getCode());
            //添加时间
            dict.setCreatedTime(Date.from(Instant.now()));
            dict.setCreator(UserContext.getCurrentUser().getAccount());
            dictService.save(dict);
            result= new Result(true, "添加成功", dict, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }
        return result;
    }

    /**
     * 删除
     * @param dict
     * @return
     */
    @SysLogAnnotation
    @ApiOperation(value = "字典" ,  notes="删除字典信息")
    @RequiresPermissions("baseinfo.dict.delete")
    @RequestMapping(value="/delete",method = {RequestMethod.POST})
    public Result dropById(@RequestBody Dict dict){
        Result result ;
        if(dict.getId()!=null){
            Dict delDict= new Dict();
            delDict.setId(dict.getId());
            //逻辑删除
            delDict.setYnFlag("0");
            delDict.setEditor(UserContext.getCurrentUser().getAccount());
            delDict.setModifiedTime(Date.from(Instant.now()));
            result=new Result(dictService.updateById(delDict),"删除成功",null,ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }else{
            result = new Result(false, "删除失败", null , ResultEnum.PARAMETERS_MISSING.getCode());
        }
        return result;
    }

    /**
     * 批量保存
     * @param dictList
     * @return
     */
    @SysLogAnnotation
    @ApiOperation(value = "字典" ,  notes="批量保存字典信息")
    @RequiresPermissions("baseinfo.dict.save")
    @RequestMapping(value="/batch_save",method = {RequestMethod.POST})
    public Result batchSave(@RequestBody List<Dict> dictList){
        dictService.batchSave(dictList);
        Result result = new Result();
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        result.setResult(true);
        result.setMessage("批量保存成功");
        return result;
    }
}
