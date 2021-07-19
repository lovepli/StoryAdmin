package com.story.storyadmin.web.sysmgr;

import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.domain.entity.sysmgr.Authority;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.domain.vo.sysmgr.AuthorityNode;
import com.story.storyadmin.service.sysmgr.AuthorityService;
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

@Api(description = "权限管理")
@RestController
@RequestMapping(value="/sysmgr/authority")
public class AuthorityController extends BaseController {
    @Autowired
    AuthorityService authorityService;

    /**
     * 查询所有权限
     * @return
     */
    @ApiOperation(value = "权限管理" ,  notes="查询所有权限")
    @RequiresPermissions("sysmgr.authority.query")
    @RequestMapping(value="/list",method = {RequestMethod.POST,RequestMethod.GET})
    public Result list(){
        //权限节点，查询获得权限树结构 AuthorityNode表示权限节点对象，返回权限节点集合
        List<AuthorityNode> trees = authorityService.findAll();

        Result result = new Result();
        result.setData(trees);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    /**
     * 保存
     * @param authority
     * @return
     */
    //@SysLogAnnotation
    @ApiOperation(value = "权限管理" ,  notes="保存权限信息")
    @RequiresPermissions("sysmgr.authority.save")
    @RequestMapping(value="/save",method = {RequestMethod.POST})
    public Result save(@RequestBody Authority authority){
        return authorityService.persist(authority);
    }

    /**
     * 删除
     * @param authority
     * @return
     */
    //@SysLogAnnotation
    @ApiOperation(value = "权限管理" ,  notes="删除权限信息")
    @RequiresPermissions("sysmgr.authority.delete")
    @RequestMapping(value="/delete",method = {RequestMethod.POST})
    public Result dropById(@RequestBody Authority authority){
        Result result ;
        if(authority.getId()!=null){
            Authority delAuth= new Authority();
            delAuth.setId(authority.getId());
            //逻辑删除
            delAuth.setYnFlag("0");
            delAuth.setEditor(UserContext.getCurrentUser().getAccount());
            delAuth.setModifiedTime(Date.from(Instant.now()));
            result=new Result(authorityService.updateById(delAuth),"删除成功",null,ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }else{
            result = new Result(false, "删除失败", null , ResultEnum.PARAMETERS_MISSING.getCode());
        }
        return result;
    }
}
