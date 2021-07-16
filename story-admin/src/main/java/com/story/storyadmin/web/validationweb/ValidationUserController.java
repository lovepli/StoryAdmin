package com.story.storyadmin.web.validationweb;

import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.domain.entity.validationentity2.model.UserModel;
import com.story.storyadmin.domain.entity.validationentity2.validator.group.ValidEmail;
import com.story.storyadmin.domain.entity.validationentity2.validator.group.ValidMobile;
import com.story.storyadmin.domain.entity.validationentity2.validator.group.ValidUserName;
import com.story.storyadmin.domain.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
// 参考：https://blog.csdn.net/kuangdaoyizhimei/article/details/111903345
@RestController
@Slf4j
@RequestMapping("/validationuser")
@Validated
public class ValidationUserController {

    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    @GetMapping
    public Result findUser(@NotNull(message = "用户id不能为空")
                           @RequestParam(value = "id",required = false)
                           Integer id) {
        return new Result(true,"根据id查询用户信息成功",new UserModel(), ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
    }

    /**
     * 手机号注册 -- 使用自定义手机号码校验器进行校验
     *
     * @param userModel
     * @return
     */
    @PostMapping("/mobile-regist")
    public Result mobileRegit(@Validated(ValidMobile.class) @RequestBody UserModel userModel) {
        return new Result(true,"手机号注册成功",null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
    }

    /**
     * 邮箱注册
     *
     * @param userModel
     * @return
     */
    @PostMapping("/email-regist")
    public Result emailRegist(@Validated(ValidEmail.class) @RequestBody UserModel userModel) {
        return new Result(true,"邮箱注册成功",null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
    }

    /**
     * 用户名注册
     *
     * @param userModel
     * @return
     */
    @PostMapping("/username-regist")
    public Result userNameRegist(@Validated(ValidUserName.class) @RequestBody UserModel userModel) {
        return new Result(true,"用户名注册成功",null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
    }


}
