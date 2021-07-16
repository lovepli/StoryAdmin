package com.story.storyadmin.web.exceptionControllerAdvice;

import org.hibernate.validator.HibernateValidator;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @author: 59688
 * @date: 2021/7/15
 * @description: hibernate的校验模式 参考：https://my.oschina.net/shadowolf/blog/1934934
 * 1、普通模式（默认是这个模式）
 *
 * 　　普通模式(会校验完所有的属性，然后返回所有的验证失败信息)
 *
 * 2、快速失败返回模式
 *
 * 　　快速失败返回模式(只要有一个验证失败，则返回)
 *
 * 配置方式：
 *
 * fail_fast： true 快速失败返回模式 false 普通模式
 */
@Configuration // @Configuration注解多和@Bean注解配合使用，手动注入配置文件到spring容器中，和@Component注解差不多，都是将对象手动注入到spring容器
@EnableAutoConfiguration // 和@ComponentScan注解的功能差不多，@ComponentScan注解是自动扫描添加了@Component注解的组件(对象)，注入到spring容器中，而@EnableAutoConfiguration注解是将添加了@Configuration注解的配置文件（对象）注入到spring容器中
public class ValidateConfig {

    /**
     *  *   @RequestParam 参数校验（一般用在处理Get请求或参数比较少）
     *  *   使用@Valid注解，对RequestParam对应的参数进行注解，是无效的，需要使用@Validated注解来使得验证生效。
     * @return
     */
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        MethodValidationPostProcessor postProcessor = new MethodValidationPostProcessor();
        // 设置validator模式为快速失败返回模式，不添加这一行则默认是普通模式，会返回所有的验证不通过信息集合
        postProcessor.setValidator(validator());
        return postProcessor;
    }

    /**
     * Validator配置
     * @return
     */
    @Bean
    public Validator validator() {
        // hibernate.validator.fail_fast: true 快速失败返回模式，false 普通模式
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .addProperty("hibernate.validator.fail_fast", "true")
                .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        return validator;
    }

    // failFast: true 快速失败返回模式，false 普通模式
    //ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
    //        .configure()
    //        .failFast( true )
    //        .buildValidatorFactory();
    //Validator validator = validatorFactory.getValidator();


}
