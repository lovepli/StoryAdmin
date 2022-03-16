package com.story.storyadmin.web;

import com.alibaba.fastjson.JSONObject;
import com.story.storyadmin.config.shiro.security.JwtUtil;
import com.story.storyadmin.constant.SecurityConsts;
import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.domain.entity.sysmgr.User;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.domain.vo.sysmgr.LoginBody;
import com.story.storyadmin.domain.vo.sysmgr.ResourceNode;
import com.story.storyadmin.service.sysmgr.AuthorityService;
import com.story.storyadmin.service.sysmgr.ResourceService;
import com.story.storyadmin.service.sysmgr.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by sunnj
 **/
@Api(description = "登录接口")
@Controller
@RequestMapping(value="/user")
public class LoginController extends BaseController{

    @Autowired
    UserService userService;

    @Autowired
    ResourceService resourceService;

    @Autowired
    AuthorityService authorityService;

    //@Autowired
    //UserService2 userService2;

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
     * 验证码登录 通过jsonObject对象接收入参，解密base64加密的请求参数
     * @param jsonObject
     * @return
     */
    //@ApiOperation(value = "登录接口" ,  notes="登录")
    //@ResponseBody
    //@RequestMapping(value="/login",method = {RequestMethod.POST})
    //public Result login2(HttpServletResponse response, @RequestBody  JSONObject jsonObject) {
    //    // 进行base64解密处理参数
    //    return userService.login3(jsonObject,response);
    //}

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
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        JSONObject json = new JSONObject();

        User user;
        user = userService.findUserByAccount(JwtUtil.getClaim(SecurityUtils.getSubject().getPrincipal().toString(), SecurityConsts.ACCOUNT));
       // logger.info("用户ID:,{}",user.getId());
        //logger.info("登录用户信息:,{}",user.toString());
        json.put("id",user.getId());
        json.put("name", user.getName());
        json.put("erp", user.getErpFlag());
        //用户图像信息， TODO 这个可以开发一个添加用户的时候支持上传用户图像，然后从数据库中查询出用户图像信息
       // json.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        json.put("avatar",user.getAvatar());
        //角色信息
        //json.put("roles",new String[]{"admin"});
        // 根据用户名查询出用户所有的角色，类型为角色名数组
        List<String> roleNameList = (List<String>) userService.findUserRole(user.getName()).getData();
        String[] toBeStored = roleNameList.toArray(new String[roleNameList.size()]);
        json.put("roles",toBeStored);

        //查询菜单
        List<ResourceNode> menus = resourceService.findByUserId(user.getId());
        //查询权限
        List<Object> authorityList = authorityService.findByUserId(user.getId());
        json.put("menus",menus);
        json.put("permissions",authorityList);

        result.setData(json);
        return result;
    }

    /**
     * 配置hsiro默认登录界面地址，前后端分离中登录界面跳转由前端路由控制，后台仅返回json数据
     * @return
     */
    @GetMapping("/noLogin")
    public Result noLogin(){
       return  new Result(false, "当前用户未登录或登录信息已过期", null ,ResultEnum.PARAMETERS_MISSING.getCode());
    }

    /**
     * 错误页面，认证不通过跳转(未授权页面跳转)
     * @return
     */
    @GetMapping("/loginFail")
    public Result loginFail(){
        return  new Result(false, "本地登录认证失败", null ,ResultEnum.PARAMETERS_MISSING.getCode());
    }
}
