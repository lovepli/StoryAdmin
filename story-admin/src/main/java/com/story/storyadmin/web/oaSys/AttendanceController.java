package com.story.storyadmin.web.oaSys;

import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.domain.entity.oasys.Attendance;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.service.oasys.AttendanceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/oasys/sign")
public class AttendanceController {
    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }


    /**
     * 设置时间
     * @return
     */
    @RequestMapping(value="/getAttendanceTime",method = {RequestMethod.GET})
    @RequiresPermissions("oasys.sign.query")
    public Result getAttendanceTime() {
        Result result = new Result();

        Map<String, String> map = attendanceService.getAttendanceTime();
        List<String> list = new ArrayList<>();
        list.add(map.get("begin"));
        list.add(map.get("end"));

        result.setData(list);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        result.setMessage("获取成功！");
        return result;
    }


    /**
     * 工作时间设置
     * @param begin
     * @param end
     * @return
     */
    @RequestMapping(value="/setAttendanceTime",method = {RequestMethod.GET})
    @RequiresPermissions("oasys.sign.query")
    public Result setAttendanceTime(@RequestParam("begin") String begin, @RequestParam("end") String end) {

        Result result ;
        attendanceService.setAttendanceTime(begin, end);
        result=new Result(true,"设置成功!",null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    /**
     *
     * @return
     */
    @RequestMapping(value="/getAttendance",method = {RequestMethod.GET})
    @RequiresPermissions("oasys.sign.query")
    public Result getAttendance( ) {
        // Long userId=UserContext.getCurrentUser().getUserId();
        Result result ;
        Attendance attendance = attendanceService.getAttendance((long) 1);
        result=new Result(true,"获取成功!",attendance, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    /**
     * 签到记录
     * @return
     */
    @RequestMapping(value="/getAttendances",method = {RequestMethod.GET})
    @RequiresPermissions("oasys.sign.query")
    public Result getAttendances() {
        Result result ;
        // Long userId=UserContext.getCurrentUser().getUserId();
        List<String> attendances = attendanceService.getAttendances((long) 1);
        result=new Result(true,"获取成功!",attendances, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    /**
     * 签到
     * @return
     * @throws ParseException
     */
    @RequestMapping(value="/signIn",method = {RequestMethod.GET})
    @RequiresPermissions("oasys.sign.query")
    public Result signIn() throws ParseException {
        Result result ;
        // Long userId=UserContext.getCurrentUser().getUserId();

        Map<String, String> map = attendanceService.getAttendanceTime();
        LocalTime beginTime = LocalTime.parse(map.get("begin"));
        LocalTime localTime = LocalTime.now();
        if (localTime.isBefore(beginTime.minusHours(1L))) {
            result=new Result(false,"还未到签到时间!",null, ResultEnum.PARAMETERS_MISSING.getCode());

        }
        if (localTime.isAfter(beginTime.plusHours(1L))) {
            result=new Result(false,"已超过签到时间!",null, ResultEnum.PARAMETERS_MISSING.getCode());
        }
        attendanceService.signIn((long) 1);
        result=new Result(true,"签到成功!",null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    /**
     * 签退
     * @return
     * @throws ParseException
     */
    @RequestMapping(value="/signOut",method = {RequestMethod.GET})
    @RequiresPermissions("oasys.sign.query")
    public Result signOut() throws ParseException {

        Result result ;
        // Long userId=UserContext.getCurrentUser().getUserId();

        if (attendanceService.getAttendance((long) 1) == null) {
            result=new Result(false,"您未签到，不能签退!",null, ResultEnum.PARAMETERS_MISSING.getCode());
        }
        Map<String, String> map = attendanceService.getAttendanceTime();
        LocalTime endTime = LocalTime.parse(map.get("end"));
        LocalTime localTime = LocalTime.now();
        if (localTime.isBefore(endTime.minusHours(1L))) {
            result=new Result(false,"还未到签退时间!",null, ResultEnum.PARAMETERS_MISSING.getCode());
        }
        if (localTime.isAfter(endTime.plusHours(1L))) {
            result=new Result(false,"已超过签退时间!",null, ResultEnum.PARAMETERS_MISSING.getCode());
        }
        attendanceService.signOut((long) 1);
        result=new Result(true,"签退成功!",null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }
}
