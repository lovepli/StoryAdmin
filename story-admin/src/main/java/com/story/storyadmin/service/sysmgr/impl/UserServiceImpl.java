package com.story.storyadmin.service.sysmgr.impl;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.story.storyadmin.common.exception.CustomException;
import com.story.storyadmin.config.shiro.ShiroKit;
import com.story.storyadmin.config.shiro.security.JwtProperties;
import com.story.storyadmin.config.shiro.security.JwtToken;
import com.story.storyadmin.config.shiro.security.JwtUtil;
import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.constant.SecurityConsts;
import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.constant.enumtype.YNFlagStatusEnum;
import com.story.storyadmin.domain.entity.sysmgr.ImageFile;
import com.story.storyadmin.domain.entity.sysmgr.LoginLog;
import com.story.storyadmin.domain.entity.sysmgr.User;
import com.story.storyadmin.domain.entity.sysmgr.UserRole;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.domain.vo.sysmgr.*;
import com.story.storyadmin.mapper.sysmgr.UserMapper;
import com.story.storyadmin.service.sysmgr.ImageFileService;
import com.story.storyadmin.service.sysmgr.LoginLogService;
import com.story.storyadmin.service.sysmgr.UserService;
import com.story.storyadmin.utils.JedisUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.*;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author sunnj
 * @since 2018-12-28
 */
@Service  //@Service用于标注业务层组件
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    @Autowired
    JwtProperties jwtProperties;

    @Autowired
    JedisUtils jedisUtils;

    @Autowired
    LoginLogService loginLogService;

    @Autowired
    ImageFileService imageFileService;

    /**
     * 通过账户名查询用户
     *
     * @param account
     * @return
     */
    @Override
    public User findUserByAccount(String account) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //设置查询条件
        wrapper.eq("account", account);
        //1 有效的用户
        wrapper.eq("yn_flag", YNFlagStatusEnum.VALID.getCode());

        // TODO 为什么查询集合，有重名的人？
        List<User> userList = baseMapper.selectList(wrapper);
        // TODO 返回集合第一个元素？
        return userList.size() > 0 ? userList.get(0) : null;
    }

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @Override
    public Result login(UserVo user, HttpServletResponse response) {
        // TODO 最好养成一个习惯，进行日志输出的时候，判断日志级别再打印出来
        if (logger.isInfoEnabled()) {
            logger.info("登录成功！！！");
        }

        //使用断言校验判断 TODO 这里的断言的作用是什么？没有从日志输出来
        Assert.notNull(user.getUsername(), "用户名不能为空");
        Assert.notNull(user.getPassword(), "密码不能为空");

        User userBean = this.findUserByAccount(user.getUsername());

        if (userBean == null) {
            return new Result(false, "用户不存在", null, ResultEnum.PASSWORD_CHECK_INVALID.getCode());
        }

        //ERP账号直接提示账号不存在
        if ("1".equals(userBean.getErpFlag())) {
            return new Result(false, "账号不存在", null, ResultEnum.PASSWORD_CHECK_INVALID.getCode());
        }

        //md5进行密码解码
        String encodePassword = ShiroKit.md5(user.getPassword(), SecurityConsts.LOGIN_SALT);
        if (!encodePassword.equals(userBean.getPassword())) {
            return new Result(false, "用户名或密码错误", null, ResultEnum.PASSWORD_CHECK_INVALID.getCode());
        }

        //账号是否锁定
        if ("0".equals(userBean.getStatus())) {
            return new Result(false, "该账号已被锁定", null, ResultEnum.PASSWORD_CHECK_INVALID.getCode());
        }

        //生成token 返回给前端
        String strToken = this.loginSuccess(userBean.getAccount(),false, response);

        Subject subject = SecurityUtils.getSubject();
        //自定义的token
        AuthenticationToken token = new JwtToken(strToken);
        subject.login(token);

        //登录成功
        return new Result(true, "登录成功", null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
    }

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @Override
    public Result login2(LoginBody user, HttpServletResponse response) {
        // TODO 最好养成一个习惯，进行日志输出的时候，判断日志级别再打印出来
        if (logger.isInfoEnabled()) {
          //  logger.info("登录成功！！！");
        }

        //使用断言校验判断 TODO 这里的断言的作用是什么？没有从日志输出来
        Assert.notNull(user.getUsername(), "用户名不能为空");
        Assert.notNull(user.getPassword(), "密码不能为空");
        Assert.notNull(user.getCode(), "验证码不能为空");

       // logger.info("是否记住我:{}", user.getRememberMe());
        String verifyKey = CAPTCHA_CODE_KEY + user.getUuid();
        String captcha = jedisUtils.get(verifyKey);
       // logger.info("缓存中的验证码:{}", captcha);
        jedisUtils.delKey(verifyKey);
        //logger.info("删除了缓存中的验证码！");

        if (!user.getCode().equalsIgnoreCase(captcha)) {
            return new Result(false, "验证码不正确", null, ResultEnum.CAPTCHA_CHECK_INVALID.getCode());
        }

        // 根据用户名查找用户
        User userBean = this.findUserByAccount(user.getUsername());

        if (userBean == null) {
            return new Result(false, "用户不存在", null,  ResultEnum.USER_CHECK_INVALID.getCode());
        }
        //ERP账号直接提示账号不存在
        if ("1".equals(userBean.getErpFlag())) {
            return new Result(false, "账号不存在", null, ResultEnum.ACCOUNT_CHECK_INVALID.getCode());
        }
        //md5进行密码解码
        String encodePassword = ShiroKit.md5(user.getPassword(), SecurityConsts.LOGIN_SALT);
        if (!encodePassword.equals(userBean.getPassword())) {
            return new Result(false, "用户名或密码错误", null, ResultEnum.PASSWORD_CHECK_INVALID.getCode());
        }
        //账号是否锁定
        if ("0".equals(userBean.getStatus())) {
            return new Result(false, "该账号已被锁定", null, ResultEnum.ACCOUNT_LOCK_INVALID.getCode());
        }
        //生成token 返回给前端
        String strToken = this.loginSuccess(userBean.getAccount(), user.getRememberMe(),response);

        Subject subject = SecurityUtils.getSubject();
        //自定义的token
        AuthenticationToken token = new JwtToken(strToken);
        subject.login(token);

        //登录成功
        return new Result(true, "登录成功", null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
    }

    @Override
    public Result login3(JSONObject jsonObject, HttpServletResponse response) {
        // A、明文方式处理
        //String userName= jsonObject.getString("username");
        //String password= jsonObject.getString("password");
        //String code= jsonObject.getString("code");
        //String uuid= jsonObject.getString("uuid");

        // B、密文方式处理
        String encryptData = jsonObject.getString("encryptData");
        String userName;
        String password;
        String code;
        String uuid;
        String rememberMe;
        if(StringUtils.isEmpty(encryptData)){
            throw new CustomException("请求加密参数不能为空");
        }
        String decryptStr = decode(encryptData);
        logger.info("登录入参校验-解析明文字符串:{}",decryptStr);
        JSONObject requestParam =JSONObject.parseObject(decryptStr);
        if(requestParam !=null){
            userName= requestParam.getString("username");
            password= requestParam.getString("password");
            code= requestParam.getString("code");
            uuid= requestParam.getString("uuid");
            rememberMe= requestParam.getString("rememberMe");
        } else {
            throw new CustomException("解析后的请求参数为空");
        }

        if(StringUtils.isEmpty(userName)){
            throw new CustomException("登录账号不能为空");
        }
        logger.info("登录账号userName：",userName);

        //登录成功
        return new Result(true, "登录成功", null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
    }

    private String decode(String text){
        String result="";
        //倒序处理字符
        for(int i = text.length()-1;i>=0;i--){
            result +=text.charAt(i);
        }
        String decodeString ="";
        try{
            // 通过base64进行解密
            decodeString = new String(Base64.getDecoder().decode(result),"utf-8");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
       // System.out.println(decodeString);
        return  decodeString;
    }


    /**
     * ERP登录
     *
     * @return
     */
    @Override
    public Result loginErp(HttpServletResponse response) {

        //@Todo 待开发
//        User userBean = this.findUserByAccount("admin");
//        if (userBean == null || "0".equals(userBean.getErpFlag())) {
//            //ERP账号不在系统中，或者系统中标志是非ERP账号
//            return new Result(false, "用户未授权", null, Constants.PASSWORD_CHECK_INVALID);
//        }
//        //账号是否锁定
//        if ("0".equals(userBean.getStatus())) {
//            return new Result(false, "该账号已被锁定", null, Constants.PASSWORD_CHECK_INVALID);
//        }
        return new Result(true, "登录成功", null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
    }

    /**
     * 登录后更新缓存，生成token，设置响应头部信息
     *
     * @param account
     * @param rememberMe 是否记住我
     * @param response
     */
    private String loginSuccess(String account,Boolean rememberMe, HttpServletResponse response) {

        //系统当前时间戳
        String currentTimeMillis = String.valueOf(System.currentTimeMillis());

        //生成token
        // JSONObject json = new JSONObject();
        String token = JwtUtil.sign(account, currentTimeMillis,rememberMe);
        //json.put("token", token);

        // TODO 这里不是将jwt token信息存入到redis中进行缓存，而是在redis中设置一个 key-value存储用户登录的时间戳，并设置多久之后的缓存过期时间
        // TODO 如果用户有记住我的选项，则对用户的缓存过期时间加长
        // 更新refreshTokenKey缓存的时间戳  设置缓存key值  记住我
        String refreshTokenKey = SecurityConsts.PREFIX_SHIRO_REFRESH_TOKEN + account;
        // 没有记住我的KEY
        String refreshTokenKeyNoRemeberMe = SecurityConsts.PREFIX_SHIRO_REFRESH_TOKEN + account + "rememberMe";
        // 如果记住我了，并且缓存中同时还存在之前没有记住我状态的没有过期的token，则根据key删除这个token值
        // 如果是没有记住我，并且缓存中同时还存在之前记住我状态的没有过期的token，则根据key删除这个token值
        if (rememberMe){
            if (jedisUtils.exists(refreshTokenKeyNoRemeberMe)){
                jedisUtils.delKey(refreshTokenKeyNoRemeberMe);
            }
            //将系统当前时间戳currentTimeMillis存入redis缓存（并设置失效时间，单位秒 24x60x60x7秒，即7天，这里我们设置的是24小时即一天的过期时间策略）
            jedisUtils.saveString(refreshTokenKey, currentTimeMillis, jwtProperties.getTokenExpireTime() * 60);
        }else {
            if (jedisUtils.exists(refreshTokenKey)){
                jedisUtils.delKey(refreshTokenKey);
            }
            //将系统当前时间戳currentTimeMillis存入redis缓存（并设置失效时间 24*60秒，即24分钟）
            jedisUtils.saveString(refreshTokenKeyNoRemeberMe, currentTimeMillis, jwtProperties.getTokenExpireTime());
        }

        //记录登录日志
        LoginLog loginLog = new LoginLog();
        loginLog.setAccount(account);
        loginLog.setLoginTime(Date.from(Instant.now()));
        loginLog.setContent("登录成功");
        //新增从时候设置为1，删除的时候设置为0
        loginLog.setYnFlag(YNFlagStatusEnum.VALID.getCode());
        loginLog.setCreator(account);
        loginLog.setEditor(account);
        loginLog.setCreatedTime(loginLog.getLoginTime());
        loginLogService.save(loginLog);

        //将jwt token写入header,通过response 返回给前端
        response.setHeader(SecurityConsts.REQUEST_AUTH_HEADER, token);
        response.setHeader("Access-Control-Expose-Headers", SecurityConsts.REQUEST_AUTH_HEADER);

        return token;
    }

    /**
     * 保存/修改
     *
     * @param user
     * @return
     */
    @Override
    public Result persist(User user) {
        Result result = null;
        //当前系统日期
        Date currentDate = Date.from(Instant.now());
        //添加
        if (user.getId() == null) {
            User existUser = this.findUserByAccount(user.getAccount());
            if (existUser != null) {
                //账号已存在
                result = new Result(false, ResultEnum.ACCOUNT_CHECK_USERED.getMsg(), null, ResultEnum.ACCOUNT_CHECK_USERED.getCode());
            } else {
                //保存密码
                if (!StringUtils.isEmpty(user.getPassword())) {
                    //用户密码加密
                    user.setPassword(ShiroKit.md5(user.getPassword(), SecurityConsts.LOGIN_SALT));
                }
                user.setLastPwdModifiedTime(Date.from(Instant.now()));
//                user.setStatus(UserStatusEnum.NORMAL.code());
                // 设置有效标识1，逻辑删除则将这个属性置为无效0
                user.setYnFlag(YNFlagStatusEnum.VALID.getCode());
                //设置创建人为当前系统登录用户
                user.setCreator(UserContext.getCurrentUser().getAccount());
                user.setCreatedTime(currentDate);
                //新增用户
                baseMapper.insert(user);
                result= new Result(true, "添加成功", null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
            }
        } else { //修改
            User userBean = baseMapper.selectById(user.getId());
            if (!user.getAccount().equals(userBean.getAccount())) {
                if (!user.getPassword().equals("******")) {
                    //修改密码
                    user.setPassword(ShiroKit.md5(user.getPassword(), SecurityConsts.LOGIN_SALT));
                    //设置上次修改密码时间为当前时间
                    user.setLastPwdModifiedTime(Date.from(Instant.now()));
                } else {  //不修改密码
                    user.setPassword(userBean.getPassword());
                    user.setLastPwdModifiedTime(userBean.getLastPwdModifiedTime());
                }
                user.setEditor(UserContext.getCurrentUser().getAccount());
                user.setModifiedTime(currentDate);
                //更新用户
                baseMapper.updateById(user);
                result= new Result(true, "修改成功", null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
            } else {
                result= new Result(false, ResultEnum.ACCOUNT_CANNOT_UPDATE.getMsg(), null, ResultEnum.ACCOUNT_CANNOT_UPDATE.getCode());
            }
        }
        return result;
    }

    @Override
    public Result findUserRole(Long userId) {
        List<Long> auths = baseMapper.selectRoleByUserId(userId);
        return new Result(true, null, auths, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
    }

    @Override
    public Result findUserRole(String userName) {
        List<String> roleName = baseMapper.selectRoleByAccount(userName);
        return new Result(true, null, roleName, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
    }

    /**
     * 保存用户角色
     *
     * @param userRole 角色表单对象
     * @return
     */
    @Override
    public Result saveUserRoles(UserRoleVo userRole) {
        Date currentDate = Date.from(Instant.now());

        //用户角色关系对象
        UserRole user = new UserRole();
        user.setUserId(userRole.getUserId());
        user.setModifiedTime(currentDate);
        //根据用户ID删除用户角色，即先删除先前存在的，后面统一进行添加操作，这样做比全部进行修改操作来的简单粗暴
        baseMapper.deleteRoleByUserId(user);

        UserRole tempUserRole;
        List<UserRole> authList = new ArrayList<>();
        //将用户角色关系对象UserRole 添加到list集合，方便后面批量插入到数据库
        for (Long roleId : userRole.getRoleIds()) {
            //懒加载tempUserRole对象
            tempUserRole = new UserRole(userRole.getUserId(), roleId);
            tempUserRole.setYnFlag("1");
            tempUserRole.setEditor(UserContext.getCurrentUser().getAccount());
            tempUserRole.setCreator(UserContext.getCurrentUser().getAccount());
            tempUserRole.setCreatedTime(currentDate);
            tempUserRole.setModifiedTime(currentDate);
            authList.add(tempUserRole);
        }
        //批量插入用户角色关系表
        baseMapper.batchInsertUserRole(authList);

        return new Result(true, null, null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
    }

    /**
     * 修改密码
     *
     * @param userPassword 用户密码和新密码表单对象
     * @return
     */
    @Override
    public Result editPassWord(UserPassword userPassword) {
        //输入的原密码和新密码都不能为空
        if (!StringUtils.isEmpty(userPassword.getPassword()) && !StringUtils.isEmpty(userPassword.getNewPassword())) {

            //直接从应用上下文获取当前登录用户信息
            User user = this.findUserByAccount(UserContext.getCurrentUser().getAccount());
            String encodeNewPassword = ShiroKit.md5(userPassword.getPassword(), SecurityConsts.LOGIN_SALT);
            //判断erp标识 ，如果不是erp账号则可以修改
            if (YNFlagStatusEnum.FAIL.getCode().equals(user.getErpFlag())) {
                //比较原始密码是否相等,左边为用户数据库中的原始密码，右边为你表单提交输入的原密码(自己记住的)
                if (user.getPassword().equals(encodeNewPassword)) {
                    User entity = new User();
                    //修改密码
                    entity.setPassword(ShiroKit.md5(userPassword.getNewPassword(), SecurityConsts.LOGIN_SALT));
                    entity.setEditor(UserContext.getCurrentUser().getAccount());

                    entity.setEditor(UserContext.getCurrentUser().getAccount());
                    entity.setModifiedTime(Date.from(Instant.now()));

                    //设置查询参数对象
                    QueryWrapper<User> wrapper = new QueryWrapper<>();
                    //有效标志为1，对象没有被删除
                    wrapper.eq("yn_flag", "1");
                    wrapper.eq("account", user.getAccount());
                    //修改用户信息 TODO 根据条件查询并修改用户信息
                    baseMapper.update(entity, wrapper);

                    return new Result(true, "修改成功", null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
                } else {
                    //原始密码错误，是你输入的原始密码自己记错了 TODO 后台返回的逻辑错误提示和前台写死的展示错误提示信息，什么情况用哪个，怎么区别？
                    return new Result(false, "原密码错误!", null, ResultEnum.PASSWORD_CHECK_INVALID.getCode());
                }
            } else {
                return new Result(false, ResultEnum.PARAMETERS_MISSING.getMsg(), null, ResultEnum.PARAMETERS_MISSING.getCode());
            }
        }
        return new Result(false, ResultEnum.PARAMETERS_MISSING.getMsg(), null, ResultEnum.PARAMETERS_MISSING.getCode());
    }

    @Override
    public User selectUserById(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public List<User> selectUserNameList() {
        return baseMapper.selectUserNameList();
    }

    /**
     * 在线展示员工上传的照片或pdf信息
     *
     * @param jsonObject
     * @return
     */
    @Override
    public String findFileInfoDetail(JSONObject jsonObject) {
        // 获取请求参数
        String userId = jsonObject.getString("userId");

        if(StringUtils.isEmpty(userId)){
            throw new CustomException(ResultEnum.PARAMETERS_MISSING.getCode(), "前端请求参数userId不能为空");
        }
        // 查找
        QueryWrapper<User> condition = new QueryWrapper<>();
        condition.eq("id", userId);
        User user = baseMapper.selectOne(condition);
        if (user == null){
            return null;
        }


        ImageFile imageFile = imageFileService.selectImageByUrl(user.getAvatar());
        List<String> pathList = new ArrayList<String>();
        if(!StringUtils.isEmpty(imageFile.getLujing())){
            pathList.addAll(Arrays.asList(imageFile.getLujing().split(";")));
        }
        //if(!StringUtils.isEmpty(crjRecord.getPdfPath())){
        //    pathList.addAll(Arrays.asList(crjRecord.getPdfPath().split(";")));
        //}

        //将图片和pdf转base64格式
        List<String> base64ImgList = new ArrayList<String>();
        BASE64Encoder encoder = new BASE64Encoder();
        try{
            for(int i=0; i<pathList.size(); i++) {
                String fileType = pathList.get(i).split("\\.")[1];
                InputStream in = new FileInputStream(pathList.get(i));
                byte[] data = new byte[in.available()];
                in.read(data);
                in.close();
                String base64str = encoder.encode(data);
                String img = null;
                // 文件格式只考虑 pdf 和 一般的图片(jpg/jpeg/png等)
                if(fileType.equals("pdf")) {
                    img = "data:application/"+fileType+";base64,"+base64str;
                }
                else{
                    img = "data:image/"+fileType+";base64,"+base64str;
                }
                base64ImgList.add(img);
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        StringBuilder result = new StringBuilder("");
        for(int i=0;i<base64ImgList.size();i++) {
            result.append(base64ImgList.get(i)).append("&&&");
        }
        return result.toString();
    }


}