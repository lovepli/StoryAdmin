package com.story.storyadmin.web.wind;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.domain.entity.wind.Organization;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.domain.vo.wind.OrganizationDto;
import com.story.storyadmin.service.wind.IOrganizationService;
import com.story.storyadmin.utils.wind.StringUtils;
import com.story.storyadmin.utils.wind.common.VueTreeHelper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;


@RestController
@RequestMapping(value="/sysmgr/organization")
public class OrganizationController extends BaseBeanController<Organization> {

    @Autowired
    private IOrganizationService organizationService;


    /**
     * 根据页码和每页记录数，以及查询条件动态加载数据
     *
     * @param
     * @param
     * @throws IOException
     */
    @GetMapping(value = "list")
    @RequiresPermissions("sysmgr.organization.list")
    public Result list(OrganizationDto organizationDto) {
        Result result = new Result();
        QueryWrapper<Organization> entityWrapper = new QueryWrapper<Organization>();
       // entityWrapper.eq("tenant_id", UserUtils.getTenantId());
        //加入条件
        String keyword = organizationDto.getKeyword();
        if (!StringUtils.isEmpty(keyword)) {
            entityWrapper.like("name", keyword);
        }
        List<Organization> treeNodeList = organizationService.selectList(entityWrapper);
        List<Organization> vueTreeNodes = VueTreeHelper.create().sort(treeNodeList);
        logger.info("查询出:{}",vueTreeNodes);
        result.setData(vueTreeNodes);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    @PostMapping("add")
    @RequiresPermissions("sysmgr.organization.add")
    public Result add(@RequestBody Organization entity, BindingResult result) {
        // 验证错误
        this.checkError(entity, result);
//        entity.setTenantId(UserUtils.getTenantId());
        organizationService.insert(entity);
        return new Result(true, "添加成功", null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
    }


    @PostMapping("update")
    @RequiresPermissions("sysmgr.organization.update")
    public Result update(@RequestBody Organization entity, BindingResult result) {
        // 获取到了用户参数，但是添加逻辑出现错误，sql语句失败
        logger.info("##############"+entity.toString());
        // 验证错误
        this.checkError(entity, result);
        organizationService.insertOrUpdate(entity);
        return new Result(true, "更新成功", null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
    }


    @PostMapping("delete/{id}")
    @RequiresPermissions("sysmgr.organization.delete")
    public Result delete(@PathVariable("id") String id) {
        organizationService.deleteById(id);
        return new Result(true,"删除成功",null,ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
    }

    @PostMapping("batch/delete")
    @RequiresPermissions("sysmgr.organization.delete")
    public Result batchDelete(@RequestParam("ids") String[] ids) {
        List<Serializable> idList = java.util.Arrays.asList(ids);
        organizationService.deleteBatchIds(idList);
        return new Result(true,"删除成功",null,ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
    }
}
