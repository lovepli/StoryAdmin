package com.story.storyadmin.web.wind;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.story.storyadmin.common.exception.CustomException;
import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.domain.entity.wind.WDict;
import com.story.storyadmin.utils.MethodUtil;
import com.story.storyadmin.utils.wind.DictUtils;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.domain.vo.wind.WDictDto;
import com.story.storyadmin.service.wind.IDictService;
import com.story.storyadmin.utils.wind.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;

@Api(description = "W字典管理")
@RestController
@RequestMapping(value="/sys/dict")
public class WDictController {

    private static final Logger logger = LoggerFactory.getLogger(WDictController.class);

    @Autowired
    private IDictService dictService;

    /**
     * 字典
     *
     * @return
     */
    @RequiresPermissions("sysmgr.dict.query")
    @GetMapping(value = "")
    public Result get() {
        Result result ;
        try {
            System.out.println("############"+DictUtils.getDict());
            //放入数据字典
            result= new Result(true, "获取成功", DictUtils.getDict(), ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(ResultEnum.UNKNOWN_EXCEPTION.getCode(), "获取失败", MethodUtil.getLineInfo());
        }
        return result;
    }

    /**
     * 根据页码和每页记录数，以及查询条件动态加载数据
     *
     * @param
     * @throws IOException
     */
    @ApiOperation(value = "字典信息" ,  notes="查询字典列表信息")
    @RequiresPermissions("sysmgr.dict.query")
    @RequestMapping(value="/list",method = {RequestMethod.POST,RequestMethod.GET})
    public Result list(WDictDto wDictDto,
                       @RequestParam(defaultValue = "1")int pageNo,
                       @RequestParam(defaultValue = "10")int limit){
        Result result = new Result();
        Page<WDict> page = new Page(pageNo, limit);
        //查询参数对象，加入条件
        QueryWrapper<WDict> entityWrapper = new QueryWrapper<>();
        //查询出有效的，del_flag为1表示逻辑删除
        //entityWrapper.eq("del_flag", "0");
        entityWrapper.orderByAsc( "sort");

        String keyword = wDictDto.getKeyword();
        Long gid = wDictDto.getGid();
        if (gid !=null && !StringUtils.isEmpty(keyword)) {
            entityWrapper.eq("gid", gid).and(i -> i.like("label", keyword).or().like("value", keyword));
        } else if (gid !=null) {
            entityWrapper.eq("gid", gid);
        }
        // 预处理
        IPage<WDict> list = dictService.page(page, entityWrapper);
        logger.info("查询出字典信息:{}",list);
        result.setData(list);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    /**
     * 保存
     * @param wDict
     * @return
     */
    @RequiresPermissions("sysmgr.dict.save")
    @RequestMapping(value="/save",method = {RequestMethod.POST})
    public Result save(@RequestBody WDict wDict){
        Result result ;
        if(wDict.getId()!= null){
            wDict.setUpdateDate(Date.from(Instant.now()));
            wDict.setUpdateBy(UserContext.getCurrentUser().getAccount());
            dictService.updateById(wDict);
            result= new Result(true, "修改成功", wDict, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }else{//添加
            //父编码设置为空
            //wDict.setParentCode("");
            //标志为有效
            wDict.setDelFlag("0");
            //添加时间
            wDict.setCreateDate(Date.from(Instant.now()));
            wDict.setCreateBy(UserContext.getCurrentUser().getAccount());
            dictService.save(wDict);
            result= new Result(true, "添加成功", wDict, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }
        return result;
    }


    @RequiresPermissions("sys.dict.delete")
    @RequestMapping(value="/delete",method = {RequestMethod.POST})
    public Result dropById(@RequestBody WDict wDict){
        Result result ;
        if(wDict.getId()!=null){
            WDict delDict= new WDict();
            delDict.setId(wDict.getId());
            delDict.setDelFlag("1");
            delDict.setUpdateDate(Date.from(Instant.now()));
            delDict.setUpdateBy(UserContext.getCurrentUser().getAccount());
            result=new Result(dictService.updateById(delDict),"删除成功",null,ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
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
    @RequiresPermissions("sysmgr.dict.delete")
    @PostMapping("/batch/delete")
    public Result dropByIds(@RequestParam("ids") Long[] ids){
        Result result ;
        // 删除数组集合，直接删除数据库中的数据
       // dictService.deleteBatchIds(ids);
        WDict delDict= null;
        // 遍历删除
        if(ids!=null || ids.length >0){
            for (Long id:ids){
                delDict= new WDict();
                delDict.setId(id);
                delDict.setDelFlag("1");
                delDict.setUpdateDate(Date.from(Instant.now()));
                delDict.setUpdateBy(UserContext.getCurrentUser().getAccount());
                dictService.updateById(delDict);
            }
            result=new Result(true,"批量删除成功",null,ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }else{
            result = new Result(false, "批量删除失败", null ,ResultEnum.PARAMETERS_MISSING.getCode());
        }
        return result;
    }

}
