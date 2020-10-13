package com.story.storyadmin.web;

import com.alibaba.fastjson.JSONObject;
import com.story.storyadmin.config.shiro.security.JwtUtil;
import com.story.storyadmin.constant.Constants;
import com.story.storyadmin.constant.SecurityConsts;
import com.story.storyadmin.domain.entity.sysmgr.User;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.domain.vo.sysmgr.LoginBody;
import com.story.storyadmin.domain.vo.sysmgr.ResourceNode;
import com.story.storyadmin.domain.vo.sysmgr.UserVo;
import com.story.storyadmin.service.sysmgr.AuthorityService;
import com.story.storyadmin.service.sysmgr.ResourceService;
import com.story.storyadmin.service.sysmgr.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by sunnj
 **/
@Api(description = "登录接口")
@Controller
@RequestMapping(value="/user")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;

    @Autowired
    ResourceService resourceService;

    @Autowired
    AuthorityService authorityService;

    @Value("${project.domain}")
    String domain;

    /**
     * 登录
     * @param user
     * @return
     */
    /**
    @ApiOperation(value = "登录接口" ,  notes="登录")
    @ResponseBody
    @RequestMapping(value="/login",method = {RequestMethod.POST})
    public Result login(HttpServletResponse response, @RequestBody UserVo user) {
        return userService.login(user,response);
    }
    */

    /**
     * 验证码登录
     * @param user
     * @return
     */
    @ApiOperation(value = "登录接口" ,  notes="登录")
    @ResponseBody
    @RequestMapping(value="/login",method = {RequestMethod.POST})
    public Result login(HttpServletResponse response, @RequestBody LoginBody user) {
        return userService.login2(user,response);
    }

    /**
     * erp登录验证ticket，生成本地token，由本地来管理token生命周期
     * @return
     */
    @ApiOperation(value = "登录接口" ,  notes="erp登录验证ticket")
    @ResponseBody
    @RequestMapping(value="/valid_erp",method = {RequestMethod.POST,RequestMethod.GET})
    public Result loginErp(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", domain);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        return userService.loginErp(response);
    }

    /**
     * 获取登录用户基础信息
     * @return
     */
    @RequiresAuthentication
    @ResponseBody
    @ApiOperation(value = "登录接口" ,  notes="获取登录用户基础信息")
    @RequestMapping(value="/info",method = {RequestMethod.POST,RequestMethod.GET})
    public Result info(){
        Result result = new Result();
        result.setResult(true);
        result.setCode(Constants.TOKEN_CHECK_SUCCESS);
        JSONObject json = new JSONObject();

        User user;
        user = userService.findUserByAccount(JwtUtil.getClaim(SecurityUtils.getSubject().getPrincipal().toString(), SecurityConsts.ACCOUNT));

        json.put("name", user.getName());
        json.put("erp", user.getErpFlag());
        //用户图像信息， TODO 这个可以开发一个添加用户的时候支持上传用户图像，然后从数据库中查询出用户图像信息
       // json.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        json.put("avatar",user.getAvatar());
        //角色信息
        json.put("roles",new String[]{"admin"});

        //查询菜单
        List<ResourceNode> menus = resourceService.findByUserId(user.getId());
        //查询权限
        List<Object> authorityList = authorityService.findByUserId(user.getId());

        json.put("menus",menus);
        json.put("auth",authorityList);

        result.setData(json);
        return result;
    }

}
