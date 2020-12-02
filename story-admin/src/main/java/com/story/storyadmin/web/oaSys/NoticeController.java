package com.story.storyadmin.web.oaSys;

import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.domain.entity.oasys.Notice;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.service.oasys.NoticeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/oasys/notice")
public class NoticeController {

    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @RequestMapping(value="/getNotices",method = {RequestMethod.GET})
    //@RequiresPermissions("oasys.notice.query")
    @RequiresPermissions("sysmgr.user.query") // 作为一个通用权限，因为没有单独页面
    public Result getNotices() {
        Result result ;
        //User user = new User();
        String username= "张三";
        List<Notice> notices = noticeService.getNotices(username);
        result=new Result(true,"获取成功!",notices, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    @RequestMapping(value="/markRead",method = {RequestMethod.POST})
    @RequiresPermissions("sysmgr.user.query")
    public Result markRead(@RequestBody Integer[] ids) {
        noticeService.markRead(ids);
        Result result ;
        noticeService.markRead(ids);
        result=new Result(true,"成功标记已读!",null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }
}
