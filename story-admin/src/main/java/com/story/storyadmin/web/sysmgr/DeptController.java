package com.story.storyadmin.web.sysmgr;

import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.constant.Constants;
import com.story.storyadmin.domain.entity.sysmgr.Dept;
import com.story.storyadmin.domain.entity.sysmgr.Resource;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.domain.vo.sysmgr.DeptNode;
import com.story.storyadmin.domain.vo.sysmgr.ResourceNode;
import com.story.storyadmin.service.sysmgr.DeptService;
import com.story.storyadmin.service.sysmgr.ResourceService;
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

/**
 * @author: lipan
 * @date: 2020/7/31
 * @description:
 */
@Api(description = "部门管理")
@RestController
@RequestMapping("/sysmgr/dept")
public class DeptController {

    @Autowired
    DeptService deptService;

    /**
     * 查询所有菜单
     * @return
     */
    @ApiOperation(value = "所有菜单" ,  notes="查询所有菜单")
    @RequiresPermissions("sysmgr.dept.query")
    @RequestMapping(value="/list",method = {RequestMethod.POST,RequestMethod.GET})
    public Result list(){
        //菜单节点，查询获得菜单树
        List<DeptNode> trees = deptService.findAll();

        Result result = new Result();
        result.setData(trees);
        result.setResult(true);
        result.setCode(Constants.TOKEN_CHECK_SUCCESS);
        return result;
    }

    /**
     * 保存/编辑
     * @param dept
     * @return
     */
    @RequiresPermissions("sysmgr.dept.save")
    @RequestMapping(value="/save",method = {RequestMethod.POST})
    public Result save(@RequestBody Dept dept){
        return deptService.persist(dept);
    }

    /**
     * 删除
     * @param dept 部门对象
     * @return
     */
    @RequiresPermissions("sysmgr.dept.delete")
    @RequestMapping(value="/delete",method = {RequestMethod.POST})
    public Result dropById(@RequestBody Dept dept){
        Result result ;
        if(dept.getId()!=null){
            Dept delRes= new Dept();
            delRes.setId(dept.getId());
            //逻辑删除标志
            delRes.setYnFlag("0");
            delRes.setEditor(UserContext.getCurrentUser().getAccount());
            delRes.setModifiedTime(Date.from(Instant.now()));
            result=new Result(deptService.updateById(delRes),null,null, Constants.TOKEN_CHECK_SUCCESS);
        }else{
            result = new Result(false, "", null ,Constants.PARAMETERS_MISSING);
        }
        return result;
    }

}
