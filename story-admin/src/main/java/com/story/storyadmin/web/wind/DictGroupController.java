package com.story.storyadmin.web.wind;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.domain.entity.wind.DictGroup;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.domain.vo.wind.WDictDto;
import com.story.storyadmin.service.wind.IDictGroupService;
import com.story.storyadmin.utils.wind.StringUtils;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;

@Api(description = "W字典组管理")
@RestController
@RequestMapping("/sys/dict/group")
public class DictGroupController {

    private static final Logger logger = LoggerFactory.getLogger(DictGroupController.class);

    @Autowired
    private IDictGroupService dictGroupService;


    /**
     * 根据页码和每页记录数，以及查询条件动态加载数据
     *
     * @param wDictDto
     * @throws IOException
     */
    @GetMapping(value = "list")
    @RequiresPermissions("sys:dict:group:list")
    public Result list(WDictDto wDictDto,
                       @RequestParam(defaultValue = "1")int pageNo,
                       @RequestParam(defaultValue = "10")int limit){
        Result result = new Result();
        Page<DictGroup> page = new Page(pageNo, limit);
        //查询参数对象，加入条件
        QueryWrapper<DictGroup> entityWrapper = new QueryWrapper<>();
        //查询出有效的，del_flag为1表示逻辑删除
        //entityWrapper.eq("del_flag", "0");
        entityWrapper.orderByDesc( "create_date");
        String keyword = wDictDto.getKeyword();
        if (!StringUtils.isEmpty(keyword)) {
            entityWrapper.like("name", keyword).or().like("code", keyword);
        }
        // 预处理
        IPage<DictGroup> list = dictGroupService.page(page, entityWrapper);
        logger.info("查询出字典信息:{}",list);
        result.setData(list);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    @PostMapping("add")
    @RequiresPermissions("sys:dict:group:add")
    public Result save(@RequestBody DictGroup dictGroup){
        Result result ;
        if(dictGroup.getId()!= null){
            dictGroup.setUpdateDate(Date.from(Instant.now()));
            dictGroup.setUpdateBy(UserContext.getCurrentUser().getAccount());
            dictGroupService.updateById(dictGroup);
            result= new Result(true, "修改成功", dictGroup, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }else{//添加
            //父编码设置为空
            //wDict.setParentCode("");
            //标志为有效
            dictGroup.setDelFlag("0");
            //添加时间
            dictGroup.setCreateDate(Date.from(Instant.now()));
            dictGroup.setCreateBy(UserContext.getCurrentUser().getAccount());
            dictGroupService.save(dictGroup);
            result= new Result(true, "添加成功", dictGroup, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }
        return result;
    }

    @RequiresPermissions("sys:dict:group:delete")
    @PostMapping("/delete")
    public Result dropById(@RequestBody DictGroup dictGroup){
        Result result ;
        if(dictGroup.getId()!=null){
            DictGroup delDictGroup= new DictGroup();
            delDictGroup.setId(dictGroup.getId());
            delDictGroup.setDelFlag("1");
            delDictGroup.setUpdateDate(Date.from(Instant.now()));
            delDictGroup.setUpdateBy(UserContext.getCurrentUser().getAccount());
            result=new Result(dictGroupService.updateById(delDictGroup),"删除成功",null,ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }else{
            result = new Result(false, "删除失败", null , ResultEnum.PARAMETERS_MISSING.getCode());
        }
        return result;
    }

//    @RequestMapping(value = "/forceRefresh", method = RequestMethod.POST)
//    @ResponseBody
//    @RequiresPermissions("sys:dict:force:refresh")
//    public String forceRefresh(HttpServletRequest request, HttpServletResponse response) {
//        try {
//            // DictUtils.clear();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Response.error("字典刷新失败" + e.getMessage());
//        }
//        return Response.ok("字典刷新成功");
//    }

}
