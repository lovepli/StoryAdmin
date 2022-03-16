package com.story.storyadmin.Interceptor.demo2;


import com.alibaba.fastjson.JSON;
import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.domain.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;


/**
 * 自定义接口防刷
 */
@Component
public class FangshuaInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisServer redisService;

    //@Autowired
    //UserContext userContext;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //判断请求是否属于方法的请求
        if(handler instanceof HandlerMethod){

            if(UserContext.getCurrentUser().getAccount() == null){
              //  render(response,CodeMsg.SESSION_ERROR);
                render(response,ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
                return false;
            }


            HandlerMethod hm = (HandlerMethod) handler;

            //获取方法中的注解,看是否有该注解
            AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
            if(accessLimit == null){
                return true;
            }
            int seconds = accessLimit.seconds();
            int maxCount = accessLimit.maxCount();
            boolean login = accessLimit.needLogin();
            String key = request.getRequestURI();
            //如果需要登录
            if(login){
                //获取登录的session进行判断
                //.....
               // key+=""+"1";  //这里假设用户是1,项目中是动态获取的userId
                key += "_" + UserContext.getCurrentUser().getUserId();
            }

            //从redis中获取用户访问的次数
            AccessKey ak = AccessKey.withExpire(seconds);
            Integer count = redisService.get(ak,key,Integer.class);
            if(count == null){
                //第一次访问
                redisService.set(ak,key,1);
            }else if(count < maxCount){
                //加1
                redisService.incr(ak,key);
            }else{
                //超出访问次数,将结果要返回给浏览器
                render(response, ResultEnum.TOKEN_CHECK_SUCCESS.getCode()); //这里的CodeMsg是一个返回参数
                return false;
            }
        }

        return true;

    }
    //向浏览器发送JSON格式数据
    private void render(HttpServletResponse response, Integer cm)throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        String str  = JSON.toJSONString(new Result(false,"超出访问次数!",null, cm));
        out.write(str.getBytes("UTF-8"));
        out.flush();
        out.close();
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
