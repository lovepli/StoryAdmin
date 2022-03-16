package com.story.storyadmin.web.sysmgr;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.story.storyadmin.config.mongo.SysLogAnnotation;
import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.domain.entity.sysmgr.User;

import com.story.storyadmin.domain.entity.validationentity.*;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.domain.vo.sysmgr.UserDo;
import com.story.storyadmin.domain.vo.sysmgr.UserPassword;
import com.story.storyadmin.domain.vo.sysmgr.UserRoleVo;
import com.story.storyadmin.service.sysmgr.ImageFileService;
import com.story.storyadmin.service.sysmgr.UserService;
import com.story.storyadmin.validator.group2.GroupA;
import com.story.storyadmin.validator.group2.GroupB;
import com.story.storyadmin.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.*;

/**
 * @RestController:
 * 用于标注控制层组件 (如 struts 中的 action)，包含 @Controller 和 @ResponseBody；
 * @Controller:
 * 用于标注是控制层组件，需要返回页面时请用 @Controller 而不是 @RestController；
 * @ResponseBody:
 * 表示该方法的返回结果直接写入 HTTP response body 中，一般在异步获取数据时使用，在使用 @RequestMapping 后，返回值通常解析为跳转路径，
 * 加上 @responsebody 后返回结果不会被解析为跳转路径，而是直接写入 HTTP response body 中；比如异步获取 json 数据，加上 @responsebody 后，会直接返回 json 数据；
 * @RequestMapping:
 * RequestMapping 是一个用来处理请求地址映射的注解，可用于类或方法上。用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径；
 * 该注解有六个属性:
 * params: 指定 request 中必须包含某些参数值是，才让该方法处理。
 * headers: 指定 request 中必须包含某些指定的 header 值，才能让该方法处理请求。
 * value: 指定请求的实际地址，指定的地址可以是 URI Template 模式
 * method: 指定请求的 method 类型， GET、POST、PUT、DELETE 等
 * consumes: 指定处理请求的提交内容类型（Content-Type），如 application/json,text/html;
 * produces: 指定返回的内容类型，仅当 request 请求头中的 (Accept) 类型中包含该指定类型才返回。
 */
@Api(description = "用户管理")
@RestController
@RequestMapping(value="/sysmgr/user")
@Validated
public class UserController extends BaseController {

    @Autowired
    private Validator validator;

    /**
     * @AutoWired:
     * byType 方式。把配置好的 Bean 拿来用，完成属性、方法的组装，它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作；
     * 当加上（required=false）时，就算找不到 bean 也不报错；
     */
    @Autowired
    UserService userService;

    @Autowired
    ImageFileService imageFileService;

    /**
     * 分页查询 用户列表信息
     * @RequestParam註解:
     * 用在方法的参数前面。相当于 request.getParameter()；
     *
     * @param user
     * @param pageNo
     * @param limit
     * @return
     */
    @ApiOperation(value = "用户信息" ,  notes="查询用户列表信息")
    @RequiresPermissions("sysmgr.user.query")
    @RequestMapping(value="/list",method = {RequestMethod.POST,RequestMethod.GET})
    public Result list(User user,
                       @RequestParam(defaultValue = "1")int pageNo,
                       @RequestParam(defaultValue = "10")int limit){
        Result result = new Result();
        Page<User> page = new Page(pageNo, limit);
        //查询参数对象，查询出有效的，yn_flag为0表示逻辑删除
        QueryWrapper<User> eWrapper = new QueryWrapper(user);
        eWrapper.eq("yn_flag", "1");
        IPage<User> list = userService.page(page, eWrapper);
        logger.info("查询出用户信息:{}",list.getRecords().toString());
        result.setData(list);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }


    /**
     * 根据Id查询用户信息
     * @param user
     * @return
     */
    @ApiOperation(value = "用户信息" ,  notes="根据Id查询用户信息")
    @RequiresPermissions("sysmgr.user.query")
   @RequestMapping(value="/find",method = {RequestMethod.POST}) // @RequestMapping注解的6个参数
   // @RequestMapping(value="/find",method = {RequestMethod.POST,RequestMethod.GET},consumes = "application/json",produces = "application/json",params ="myparam=myvalue",headers = "Refer=http://www.baidu.com/")
    public Result findById(@RequestBody User user){
        User userBean= userService.getById(user.getId());
        //详情信息，隐藏用户的密码显示
        userBean.setPassword("********");

        Result result = new Result();
        result.setData(userBean);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }


    /**
     * 保存
     * @param user
     * @return
     *
     *  @Valid 使用JSR 303进行校验!!
     *  验证不通过时，抛出了MethodArgumentNotValidException异常，可以使用全局捕获异常处理。
     */
    @SysLogAnnotation
    @ApiOperation(value = "用户信息" ,  notes="保存用户信息")
    @RequiresPermissions("sysmgr.user.save")
    @RequestMapping(value="/save",method = {RequestMethod.POST})
    public Result save(@Valid @RequestBody User user){
        return userService.persist(user);
    }

    /**
     * 根据用户名查询所有角色
     *  如果只有少数参数，直接把参数写到Controller层，然后在Controller层进行验证就可以了  @NotNull(message = "用户名不能为空")
     *  验证不通过时，抛出了ConstraintViolationException异常，可以使用全局捕获异常处理。
     * @return
     */
    @ApiOperation(value = "用户信息" ,  notes="根据用户名查询所有角色")
    @RequiresPermissions("sysmgr.user.query")
    @RequestMapping(value="/findRolelistByAccount",method = {RequestMethod.POST,RequestMethod.GET})
    public Result list(@NotNull(message = "用户名不能为空") @RequestParam(value = "ua",required = false) String userName){
        return userService.findUserRole(userName);
    }





    /**
     * 删除 这里的删除是进行修改操作的逻辑删除
     * @param user
     * @return
     */
    @Transactional
    //@SysLogAnnotation
    @ApiOperation(value = "用户信息" ,  notes="删除用户信息")
    @RequiresPermissions("sysmgr.user.delete")
    @RequestMapping(value="/delete",method = {RequestMethod.POST})
    public Result dropById(@RequestBody User user){
        Result result ;
        if(user.getId()!=null){
            User delUser= new User();
            delUser.setId(user.getId());
            //将标志置为无效
            delUser.setYnFlag("0");
            delUser.setEditor(UserContext.getCurrentUser().getAccount());
            delUser.setModifiedTime(Date.from(Instant.now()));
            // 根据ID查找用户
            User userInfo=userService.selectUserById(user.getId());
            if (userInfo.getAccount().equals("admin")){
                result = new Result(false, "管理员账号不能修改!", null ,ResultEnum.ACCOUNT_CANNOT_UPDATE.getCode());
            }else {
                // 根据用户名删除用户图片记录
                imageFileService.deleteImage(user.getAvatar());
                result=new Result(userService.updateById(delUser),"删除成功",null,ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
            }
        }else{
            result = new Result(false, "删除失败", null ,ResultEnum.PARAMETERS_MISSING.getCode());
        }
        return result;
    }

    /**
     * 根据用户Id查询角色
     * @param user
     * @return
     */
    @ApiOperation(value = "用户信息" ,  notes="根据用户Id查询角色")
    @RequiresPermissions("sysmgr.user.query")
    @RequestMapping(value="/findUserRole",method = {RequestMethod.POST})
    public Result findUserRole(@RequestBody UserRoleVo user){
        return userService.findUserRole(user.getUserId());
    }


    /**
     * 更改/保存用户角色
     * @param userRole
     * @return
     */
    @SysLogAnnotation
    @ApiOperation(value = "用户信息" ,  notes="更改/保存用户角色")
    @RequiresPermissions("sysmgr.user.save")
    @RequestMapping(value="/saveUserRole",method = {RequestMethod.POST})
    public Result saveUserRole(@RequestBody UserRoleVo userRole){
        return userService.saveUserRoles(userRole);
    }

    /**
     * 修改密码
     * @param userPassword
     * @return
     */
    @SysLogAnnotation
    @ApiOperation(value = "用户信息" ,  notes="修改密码")
    @RequiresAuthentication
    @RequestMapping(value="/editpassword",method = {RequestMethod.POST})
    public Result editPassWord(@RequestBody UserPassword userPassword){
        return userService.editPassWord(userPassword);
    }

    /**
     * 查询用户名列表
     * @return
     */
    @ApiOperation(value = "用户信息" ,  notes="查询用户名列表")
    @RequiresPermissions("sysmgr.user.query")
    @RequestMapping(value="/UserNameList",method = {RequestMethod.POST,RequestMethod.GET})
    public Result UserNameList(){
        Result result = new Result();
        // TODO 这里返回的数据不包含ID
       // List<User> list=userService.selectUserNameList();
       // List<User> list=userService.list();
        // 模拟数据
        List<UserDo> list=new ArrayList<>();
        list.add(new UserDo((long) 1,"张三"));
        list.add(new UserDo((long) 2,"李四"));

        logger.info("查询出的用户信息为:{}",list);
        result.setData(list);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    /**
     * 在线展示员工上传的照片或pdf信息
     *
     * @param jsonObject
     * @return
     */
    @ApiOperation(value = "用户信息" ,  notes="在线展示员工上传的照片或pdf信息")
    @RequiresPermissions("sysmgr.user.query")
    @PostMapping("/findFileInfoDetail")
    public Result findFileInfoDetail(@RequestBody JSONObject jsonObject){
        String data = userService.findFileInfoDetail(jsonObject);
        //Map<String, Object> map = new HashMap<>();
        //map.put("result", data);
        return  new Result(true, null, data ,ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
    }

    //在控制器初始化时注册属性编辑器，实现String转Date日期格式转化
    // （也可以再每个controller中注册属性编辑器，也可以在BaseController中注册，其他controller继承BaseController,那么所有controller都继承这个方法）
    //@InitBinder
    //public void initBuilder(WebDataBinder binder){
    //    // 注册自定义编辑器
    //    binder.registerCustomEditor(Date.class,new DateEditor());
    //}

//##################################################################################################################################

    /**
     * 测试hibernate的校验模式--快速失败模式 (只要有一个验证失败，则返回)
     * https://my.oschina.net/shadowolf/blog/1934934
     * @param
     * @return
     */
    //@RequiresPermissions("sysmgr.user.save")
    //@RequestMapping(value="/save",method = {RequestMethod.POST})
    public Result saveTest(){
        ValidateDemo1 userModel=new ValidateDemo1();
        userModel.setAddress("");
        userModel.setAge(35);
        userModel.setEmail("176712");
        userModel.setName("");
        userModel.setPassword("12345");
        userModel.setMobile("17671226463");
        userModel.setContacts(new ArrayList<String>(){{add("111");add("222");add("333");}});

        Set<ConstraintViolation<ValidateDemo1>> violationSet = validator.validate(userModel);
        for (ConstraintViolation<ValidateDemo1> model : violationSet) {
            System.out.println("校验：" + model.getMessage());
        }
        return new Result(true, "保存成功！", null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
    }

    /**
     * 对象级联校验 测试
     * 对象内部包含另一个对象作为属性，属性上加 @Valid，可以验证作为属性的对象内部的验证
     */
    //@RequiresPermissions("sysmgr.user.save")
    //@RequestMapping(value="/save",method = {RequestMethod.POST})
    public Result saveTest2(){
        ValidateDemo2 demo2 = new ValidateDemo2();
        demo2.setList(new ArrayList<String>(){{add("111");add("222");add("333");}});

        ValidateDemo3 demo3 = new ValidateDemo3();
        demo3.setExtField("22");
        demo3.setEmail("111");
        demo2.setDemo3(demo3);
        Set<ConstraintViolation<ValidateDemo2>> violationSet = validator.validate(demo2);
        for (ConstraintViolation<ValidateDemo2> model : violationSet) {
            System.out.println("校验：" + model.getMessage());
        }
        return new Result(true, "保存成功！", null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
    }

    /**
     * 分组校验 -- 只验证GroupA和GroupB的分组
     * @return
     */
    //@RequiresPermissions("sysmgr.user.save")
    //@RequestMapping(value="/save",method = {RequestMethod.POST})
    public Result saveTest3(){
        GroupUser p = new GroupUser();
        /**GroupA验证不通过*/
        p.setUserId(-12);
        /**GroupA验证通过*/
        //p.setUserId(12);
        p.setUserName("a");
        p.setAge(110);
        p.setSex(5);
        Set<ConstraintViolation<GroupUser>> validate = validator.validate(p, GroupA.class, GroupB.class);
        for (ConstraintViolation<GroupUser> item : validate) {
            System.out.println("校验："+item);
        }
        return new Result(true, "保存成功！", null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
    }


    /**
     * 分组校验 -- 只验证GroupA和GroupB的分组-2 待测试
     * @param p
     * @param result
     * @return
     */
    //@RequiresPermissions("sysmgr.user.save")
    //@RequestMapping(value="/save",method = {RequestMethod.POST})
    public Result saveTest4(@Validated({GroupA.class, GroupB.class}) GroupUser p, BindingResult result){
        if(result.hasErrors()){
            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError error : allErrors) {
                System.out.println("校验："+error);
            }
        }
        return new Result(true, "保存成功！", null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
    }


    /**
     * 测试自定义校验器 --大小写校验器
     * @return
     */
    //@RequiresPermissions("sysmgr.user.save")
    //@RequestMapping(value="/save",method = {RequestMethod.POST})
    public Result saveTest5(){
        CaseModeDemo demo = new CaseModeDemo();
        demo.setUserName("userName");
        Set<ConstraintViolation<CaseModeDemo>> validate = validator.validate(demo);
        for (ConstraintViolation<CaseModeDemo> dem : validate) {
            System.out.println("校验："+dem.getMessage());
        }
        return new Result(true, "保存成功！", null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
    }



}
