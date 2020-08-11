package com.story.storyadmin.web.sysmgr;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.constant.Constants;
import com.story.storyadmin.domain.entity.sysmgr.Att;
import com.story.storyadmin.domain.entity.sysmgr.Inform;
import com.story.storyadmin.domain.entity.sysmgr.User;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.domain.vo.sysmgr.*;
import com.story.storyadmin.service.sysmgr.InformService;
import com.story.storyadmin.utils.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
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
@RequestMapping("/sysmgr/inform")
public class InformController {

    private static final Logger logger = LoggerFactory.getLogger(InformController.class);

    @Autowired
    InformService informService;

    /**
     * 分页查询公告
     * @param
     * @param pageNo
     * @param limit
     * @return
     */
    @ApiOperation(value = "公告" ,  notes="分页查询公告")
    @RequiresPermissions("sysmgr.inform.query")
    @RequestMapping(value="/list",method = {RequestMethod.POST,RequestMethod.GET})
    public Result get(@RequestParam(required = false) Short status,
                      @RequestParam(required = false) String title,
                      @RequestParam(required = false, value = "creator") Long creatorId,
                      @RequestParam(value = "sd", required = false) Long startDate,
                      @RequestParam(value = "ed", required = false) Long endDate,
                      @RequestParam(value = "tf", required = false) Boolean topFirst,
                      @RequestParam(defaultValue = "1") int pageNo,
                      @RequestParam(defaultValue = "10") int limit) {
        Result result = new Result();
        Page<Inform> page = new Page(pageNo, limit);
        // 开始时间和结束时间
        Date startOfCreate = DateUtil.startOfThisDay(startDate);
        Date endOfCreate = DateUtil.startOfNextDay(endDate);
        Inform inform = null;
        inform.setStatus(status);
        inform.setTitle(title);
        inform.setCreator(creatorId);
        inform.setTop(topFirst);

        QueryWrapper<Inform> eWrapper = new QueryWrapper(inform);
        // 设置查询条件 对时间进行判断
        eWrapper.gt("createDate",startOfCreate);
        eWrapper.lt("createDate",endOfCreate);
        IPage<Inform> list = informService.page(page, eWrapper);
        logger.info("查询出公告信息:[]", list.toString());
        result.setData(list);
        result.setResult(true);
        result.setCode(Constants.TOKEN_CHECK_SUCCESS);
        return result;
    }

    /**
     * 根据Id查询公告信息 TODO 这里要从缓存中取出来公告信息!!
     * @param inform
     * @return
     */
    @ApiOperation(value = "公告" ,  notes="查看公告详情")
    @RequiresPermissions("sysmgr.inform.query")
    @RequestMapping(value="/find",method = {RequestMethod.POST})
    public Result findById(@RequestBody Inform inform){
      //  Inform informBean= informService.getById(inform.getId()); // 这个是从数据库中获取
        // 从缓存中获取公告详情
        Inform informBean= informService.get(inform.getId());
        // 判断状态是否已撤销
        if (Objects.equals(informBean.getStatus(), informService.CANCELED)) {
            informBean.setContent(null);
        }
        informBean.setOutdateDate(null);
        informBean.setOutdateOperator(null);
        informBean.setCancelDate(null);
        informBean.setCanceler(null);

        Result result = new Result();
        result.setData(informBean);
        result.setResult(true);
        result.setCode(Constants.TOKEN_CHECK_SUCCESS);
        return result;
    }

    /**
     * 新增公告
     * @param inform
     * @return
     */
    @ApiOperation(value = "公告" ,  notes="新增公告")
    @RequiresPermissions("sysmgr.inform.save")
    @RequestMapping(value = "/save", method = POST)
    public Result save(@RequestBody InformVo inform){
        //使用断言校验判断
        Assert.notNull(inform.getTitle(), "标题不能为空");
        Assert.notNull(inform.getContent(), "内容不能为空");
        // 获取当前登录用户ID
       // inform.setCreator(UserContext.getCurrentUser().getUserId());
        inform.setCreator((long) 1);
        //
        List<Long> attachmentIds = inform.getAttachments();
        if (attachmentIds != null) {
            // 字符串拼接 TODO 这里应该将long转为string存储吧？
            String idList = attachmentIds.stream().map(String::valueOf).collect(Collectors.joining(","));
            inform.setAttchmentList(idList);
        }
        return informService.persist(inform);
    }

    /**
     * 置顶公告
     * @param inform
     * @return
     */
    @ApiOperation(value = "公告" ,  notes="置顶公告")
    @RequiresPermissions("sysmgr.inform.top")
    @RequestMapping(value = "/top",method = {RequestMethod.POST})
    public Result top(@RequestBody Inform inform) {
        return informService.topOrNot(inform, true);
    }

    /**
     * 取消公告的置顶
     * @param inform
     * @return
     */
    @ApiOperation(value = "公告" ,  notes="取消公告的置顶")
    @RequiresPermissions("sysmgr.inform.untop")
    @RequestMapping(value = "/untop",method = {RequestMethod.POST})
    public Result untop(@RequestBody Inform inform) {
        return informService.topOrNot(inform, false);
    }

    /**
     * 撤销公告
     * @param inform
     * @return
     */
    @ApiOperation(value = "公告" ,  notes="撤销公告")
    @RequiresPermissions("sysmgr.inform.cancel")
    @RequestMapping(value = "/cancel", method = POST)
    public Result cancel(@RequestBody Inform inform) {
        return informService.cancel(inform);
    }

    /**
     * 使公告过期
     * @param inform
     * @return
     */
    @ApiOperation(value = "公告" ,  notes="使公告过期")
    @RequiresPermissions("sysmgr.inform.outdate")
    @RequestMapping(value = "/outdate", method = POST)
    public Result outdate(@RequestBody Inform inform) {
        return informService.outdate(inform);
    }

}
