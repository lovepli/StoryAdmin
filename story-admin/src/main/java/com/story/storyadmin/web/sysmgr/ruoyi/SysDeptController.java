package com.story.storyadmin.web.sysmgr.ruoyi;


import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.domain.entity.sysmgr.ruoyi.SysDept;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.service.sysmgr.ruoyi.ISysDeptService;
import com.story.storyadmin.utils.ruoyiutils.StringUtils;
import com.story.storyadmin.web.BaseController;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 部门信息
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/sysmgr/rydept")
public class SysDeptController extends BaseController {

    @Autowired
    private ISysDeptService deptService;

    /**
     * 获取部门列表
     */
    @RequiresPermissions("sysmgr.rydept.list")
    @RequestMapping(value="/list",method = {RequestMethod.POST,RequestMethod.GET})
    public Result list(SysDept dept) {
        Result result = new Result();
        List<SysDept> depts = deptService.selectDeptList(dept);
        result.setData(depts);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    /**
     * 查询部门列表（排除节点）
     */
    @RequiresPermissions("sysmgr.rydept.list")
    @GetMapping("/list/exclude/{deptId}")
    public Result excludeChild(@PathVariable(value = "deptId", required = false) Long deptId) {
        Result result = new Result();
        List<SysDept> depts = deptService.selectDeptList(new SysDept());
        Iterator<SysDept> it = depts.iterator();
        while (it.hasNext()) {
            SysDept d = (SysDept) it.next();
            if (d.getDeptId().intValue() == deptId
                    || ArrayUtils.contains(StringUtils.split(d.getAncestors(), ","), deptId + "")) {
                it.remove();
            }
        }
        result.setData(depts);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    /**
     * 根据部门编号获取详细信息
     */
    @RequiresPermissions("sysmgr.rydept.query")
    @GetMapping(value = "/{deptId}")
    public Result getInfo(@PathVariable Long deptId) {
        Result result = new Result();
        result.setData(deptService.selectDeptById(deptId));
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    /**
     * 获取部门下拉树列表
     */
    @RequiresPermissions("sysmgr.rydept.query")
    @GetMapping("/treeselect")
    public Result treeselect(SysDept dept) {
        Result result = new Result();
        List<SysDept> depts = deptService.selectDeptList(dept);
        result.setData(deptService.buildDeptTreeSelect(depts));
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    /**
     * 加载对应角色部门列表树
     */
    @RequiresPermissions("sysmgr.rydept.query")
    @GetMapping(value = "/roleDeptTreeselect/{roleId}")
    public Result roleDeptTreeselect(@PathVariable("roleId") Long roleId) {
        Result result = new Result();
        List<SysDept> depts = deptService.selectDeptList(new SysDept());
        Map<String,Object> map=new  HashMap<>();
        map.put("checkedKeys", deptService.selectDeptListByRoleId(roleId));
        map.put("depts", deptService.buildDeptTreeSelect(depts));
        result.setData(map);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    /**
     * 新增部门
     */
    @RequiresPermissions("sysmgr.rydept.add")
    @PostMapping
    public Result add(@Validated @RequestBody SysDept dept) {
        Result result = null;
        if ("1".equals(deptService.checkDeptNameUnique(dept))) {
            result = new Result(false, "新增部门'" + dept.getDeptName() + "'失败，部门名称已存在", null, ResultEnum.ACCOUNT_CHECK_USERED.getCode());
        }
        dept.setCreateBy(UserContext.getCurrentUser().getAccount());
        deptService.insertDept(dept);
        result= new Result(true, "添加成功", null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    /**
     * 修改部门
     */
    @RequiresPermissions("sysmgr.rydept.edit")
    @PutMapping
    public Result edit(@Validated @RequestBody SysDept dept) {
        Result result = null;
        if ("1".equals(deptService.checkDeptNameUnique(dept))) {
            result = new Result(false, "修改部门'" + dept.getDeptName() + "'失败，部门名称已存在", null, ResultEnum.ACCOUNT_CHECK_USERED.getCode());
        } else if (dept.getParentId().equals(dept.getDeptId())) {
            result = new Result(false, "修改部门'" + dept.getDeptName() + "'失败，上级部门不能是自己", null, ResultEnum.ACCOUNT_CHECK_USERED.getCode());
        } else if (StringUtils.equals("1", dept.getStatus())
                && deptService.selectNormalChildrenDeptById(dept.getDeptId()) > 0) {
            result = new Result(false, "该部门包含未停用的子部门！", null, ResultEnum.ACCOUNT_CHECK_USERED.getCode());
        }
        dept.setCreateBy(UserContext.getCurrentUser().getAccount());
        deptService.updateDept(dept);
        result= new Result(true, "修改成功", null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    /**
     * 删除部门
     */
    @RequiresPermissions("sysmgr.rydept.delete")
    @DeleteMapping("/{deptId}")
    public Result remove(@PathVariable Long deptId) {
        Result result = null;
        if (deptService.hasChildByDeptId(deptId)) {
            result = new Result(false, "存在下级部门,不允许删除", null, ResultEnum.ACCOUNT_CHECK_USERED.getCode());
        }
        if (deptService.checkDeptExistUser(deptId)) {
            result = new Result(false, "部门存在用户,不允许删除", null, ResultEnum.ACCOUNT_CHECK_USERED.getCode());
        }
        deptService.deleteDeptById(deptId);
        result= new Result(true, "删除成功", null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }
}
