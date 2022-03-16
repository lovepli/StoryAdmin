package com.story.storyadmin.web.interceptorTestController;

import com.story.storyadmin.Interceptor.demo2.AccessLimit;
import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.domain.vo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 59688
 * @date: 2021/8/2
 * @description:
 */
@RestController
@RequestMapping(value="/fangshua")
public class FangshuaController {

    @AccessLimit(seconds = 5, maxCount = 5, needLogin = false)
    @RequestMapping(value="/test",method = {RequestMethod.POST,RequestMethod.GET})
    public Result fangshua() {
        System.out.println("请求成功!");
        return new Result(true, "请求成功！", null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
    }
}