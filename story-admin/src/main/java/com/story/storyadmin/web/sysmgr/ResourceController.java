package com.story.storyadmin.web.sysmgr;

import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.domain.entity.sysmgr.Resource;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.domain.vo.sysmgr.ResourceNode;
import com.story.storyadmin.service.sysmgr.ResourceService;
import com.story.storyadmin.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Api(description = "菜单管理")
@RestController
@RequestMapping(value="/sysmgr/resource")
public class ResourceController extends BaseController {
    @Autowired
    ResourceService resourceService;

    /**
     * 查询所有菜单
     * @return
     */
    @ApiOperation(value = "所有菜单" ,  notes="查询所有菜单")
    @RequiresPermissions("sysmgr.resource.query")
    @RequestMapping(value="/list",method = {RequestMethod.POST,RequestMethod.GET})
    public Result list(){
        //菜单节点，查询获得菜单树
        List<ResourceNode> trees = resourceService.findAll();

        Result result = new Result();
        result.setData(trees);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    /**
     * 保存/编辑
     * @param resource
     * @return
     */
    //@SysLogAnnotation
    @ApiOperation(value = "所有菜单" ,  notes="保存/编辑菜单信息")
    @RequiresPermissions("sysmgr.resource.save")
    @RequestMapping(value="/save",method = {RequestMethod.POST})
    public Result save(@RequestBody Resource resource){
        return resourceService.persist(resource);
    }

    /**
     * 删除
     * @param resource 菜单对象
     * @return
     */
    //@SysLogAnnotation
    @ApiOperation(value = "所有菜单" ,  notes="删除菜单信息")
    @RequiresPermissions("sysmgr.resource.delete")
    @RequestMapping(value="/delete",method = {RequestMethod.POST})
    public Result dropById(@RequestBody Resource resource){
        Result result ;
        if(resource.getId()!=null){
            Resource delRes= new Resource();
            delRes.setId(resource.getId());
            //逻辑删除标志
            delRes.setYnFlag("0");
            delRes.setEditor(UserContext.getCurrentUser().getAccount());
            delRes.setModifiedTime(Date.from(Instant.now()));
            result=new Result(resourceService.updateById(delRes),"删除成功",null,ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }else{
            result = new Result(false, "删除失败", null , ResultEnum.PARAMETERS_MISSING.getCode());
        }
        return result;
    }
}
