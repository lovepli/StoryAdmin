package com.story.storyadmin.web.sysmgr;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.story.storyadmin.config.mongo.SysLogAnnotation;
import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.domain.entity.sysmgr.Inform;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.domain.vo.sysmgr.*;
import com.story.storyadmin.service.sysmgr.InformService;
import com.story.storyadmin.utils.DateUtil;
import com.story.storyadmin.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Api(description = "系统通知")
@RestController
public class InformController extends BaseController {

    @Autowired
    InformService informService;

    /**
     * 分页查询公告
     *
     * @param
     * @param
     * @param limit
     * @return
     */
    @ApiOperation(value = "公告", notes = "分页查询公告")
    @RequiresPermissions("sysmgr.inform.query")
    @RequestMapping(value = "/sysmgr/inform/list", method = {RequestMethod.POST, RequestMethod.GET})
    public Result get(@RequestParam(required = false) Short status,
                      @RequestParam(required = false) String title,
                      @RequestParam(value = "creatorId", required = false) Long creatorId,
                      @RequestParam(value = "sd", required = false) Long startDate,
                      @RequestParam(value = "ed", required = false) Long endDate,
                      @RequestParam(value = "tf", required = false) Boolean topFirst,
                      @RequestParam(defaultValue = "1") int page,
                      @RequestParam(defaultValue = "10") int limit) {
        Result result = new Result();

        Inform inform = new Inform();
        inform.setStatus(status);
        inform.setTitle(title);
        inform.setCreatorId(creatorId);
        inform.setTop(topFirst);
        //logger.info(inform.toString());
        Page<Inform> InformPage = new Page(page, limit);
        QueryWrapper<Inform> eWrapper = new QueryWrapper(inform);
        // 公告列表条件查询
        if (startDate != null && endDate != null) {
            // 开始时间和结束时间
            Date startOfCreate = DateUtil.startOfThisDay(startDate);
            Date endOfCreate = DateUtil.startOfNextDay(endDate);
            // 设置查询条件 对时间进行判断
            eWrapper.gt("create_date", startOfCreate);
            eWrapper.lt("create_date", endOfCreate);
        }
        // 创建时间倒序排序
        eWrapper.orderByDesc("create_date");
        IPage<Inform> list = informService.page(InformPage, eWrapper);
        // logger.info("查询出公告信息:[]", list.toString());
        result.setData(list);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    /**
     * 查看公告详情 TODO 这里要从缓存中取出来公告信息!!
     * @param id
     * @return
     */
    @ApiOperation(value = "公告", notes = "查看公告详情")
    @RequiresPermissions("sysmgr.inform.query")
    @RequestMapping(value = "/inform/{id}", method = GET)
    public Result findById(@PathVariable Long id) {
        //  Inform informBean= informService.getById(inform.getId()); // 这个是从数据库中获取
        // 从缓存中获取公告详情
        Inform informBean = informService.get(id);
        // 判断状态是否已撤销
        if (Objects.equals(informBean.getStatus(), InformService.CANCELED)) {
            informBean.setContent(null);
        }
        informBean.setOutdateDate(null);
        informBean.setOutdateOperator(null);
        informBean.setCancelDate(null);
        informBean.setCanceler(null);

        Result result = new Result();
        result.setData(informBean);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    /**
     * 新增公告
     *
     * @param inform
     * @return
     */
    @SysLogAnnotation
    @ApiOperation(value = "公告", notes = "新增公告")
    @RequiresPermissions("sysmgr.inform.save")
    @RequestMapping(value = "/sysmgr/inform/save", method = POST)
    public Result save(@RequestBody InformVo inform) {
        //使用断言校验判断
        Assert.notNull(inform.getTitle(), "标题不能为空");
        Assert.notNull(inform.getContent(), "内容不能为空");
        // 获取当前登录用户ID
        Long id= UserContext.getCurrentUser().getUserId();
        // TODO 这里取不到ID
        System.out.println("################上传者的id:"+id);
        inform.setCreatorId(id);

        List<Long> attachmentIds = inform.getAttachments();
        System.out.println("数组长度为："+attachmentIds.size()); // TODO 数组的长度为0？？
        for (Long idd:attachmentIds){
            logger.info("#############添加的附件id为:{}" + idd);
        }
        if (attachmentIds != null) {
            // 字符串拼接 TODO 这里应该将long转为string存储吧？ 这里不能存储到数据库！
            // //获取所有选中行的id组成的字符串，以逗号分隔 前端的处理方式：https://www.jb51.net/article/135616.htm
            String idList = attachmentIds.stream().map(String::valueOf).collect(Collectors.joining(","));
            logger.info("添加的附件id为:{}" + idList);
            inform.setAttchmentList(idList);
        }
        return informService.persist(inform);
    }

    /**
     * 置顶公告
     *
     * @param id
     * @return
     */
    @SysLogAnnotation
    @ApiOperation(value = "公告", notes = "置顶公告")
    @RequiresPermissions("sysmgr.inform.top")
    @RequestMapping(value = "/sysmgr/inform/{id}/top", method = POST)
    public Result top(@PathVariable Long id) {
        return informService.topOrNot(id, true);
    }

    /**
     * 取消公告的置顶
     *
     * @param id
     * @return
     */
    @SysLogAnnotation
    @ApiOperation(value = "公告", notes = "取消公告的置顶")
    @RequiresPermissions("sysmgr.inform.untop")
    @RequestMapping(value = "/sysmgr/inform/{id}/untop", method = POST)
    public Result untop(@PathVariable Long id) {
        return informService.topOrNot(id, false);
    }

    /**
     * 撤销公告
     *
     * @param id
     * @return
     */
    @SysLogAnnotation
    @ApiOperation(value = "公告", notes = "撤销公告")
    @RequiresPermissions("sysmgr.inform.cancel")
    @RequestMapping(value = "/sysmgr/inform/{id}/cancel", method = POST)
    public Result cancel(@PathVariable Long id) {
        return informService.cancel(id);
    }

    /**
     * 使公告过期
     *
     * @param id
     * @return
     */
    @SysLogAnnotation
    @ApiOperation(value = "公告", notes = "使公告过期")
    @RequiresPermissions("sysmgr.inform.outdate")
    @RequestMapping(value = "/sysmgr/inform/{id}/outdate", method = POST)
    public Result outdate(@PathVariable Long id) {
        return informService.outdate(id);
    }

}
