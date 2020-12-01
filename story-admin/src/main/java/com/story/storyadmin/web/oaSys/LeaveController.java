package com.story.storyadmin.web.oaSys;


import com.github.pagehelper.PageInfo;
import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.domain.entity.oasys.Leave;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.service.oasys.LeaveService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/oasys/leave")
public class LeaveController {

    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @RequestMapping(value="/askLeave",method = {RequestMethod.POST})
    @RequiresPermissions("oasys.leave.query")
    public Result askLeave( @RequestBody Leave leave) {
        Result result ;
        // leave.setUserId(UserContext.getCurrentUser().getUserId());
        leave.setUserId((long) 1);
        leaveService.askLeave(leave);
        result=new Result(true,"提交成功!",null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    @RequestMapping(value="/checkLeave",method = {RequestMethod.POST})
    @RequiresPermissions("oasys.leave.query")
    public Result checkLeave(@RequestBody Leave leave) {
        Result result ;
        leaveService.checkLeave(leave);
        result=new Result(true,"提交成功!",null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    @RequestMapping(value="/getLeaves",method = {RequestMethod.GET})
    @RequiresPermissions("oasys.leave.query")
    public Result getLeaves(@RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
                                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        Result result ;
        PageInfo<Leave> pageInfo = leaveService.getLeaves(pageNumber, pageSize);
        result=new Result(true,"获取成功!",pageInfo, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }
}
