> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [blog.csdn.net](https://blog.csdn.net/wuseyukui/article/details/81164207)

JSR-303 与 hibernate-validator
=============================

Spring3 支持 JSR-303 验证框架，JSR-303 是 Java EE 6 中的一项子规范，叫做 BeanValidation，官方参考实现是 hibernate-validator（与 Hibernate ORM 没有关系），JSR 303 用于对 Java Bean 中的字段的值进行验证。

hibernate-validator 实现了 JSR-303 规范，并扩展了一些注解，提供了一套比较完善、便捷的验证实现方式。

常用验证：

*   @Null 限制只能为 null
*   @NotNull 限制必须不为 null
*   @AssertFalse 限制必须为 false
*   @AssertTrue 限制必须为 true
*   @DecimalMax(value) 限制必须为一个不大于指定值的数字
*   @DecimalMin(value) 限制必须为一个不小于指定值的数字
*   @Digits(integer,fraction) 限制必须为一个小数，且整数部分的位数不能超过 integer，小数部分的位数不能超过 fraction
*   @Future 限制必须是一个将来的日期
*   @Max(value) 限制必须为一个不大于指定值的数字
*   @Min(value) 限制必须为一个不小于指定值的数字
*   @Past 限制必须是一个过去的日期
*   @Pattern(value) 限制必须符合指定的正则表达式
*   @Size(max,min) 限制字符长度必须在 min 到 max 之间
*   @Past 验证注解的元素值（日期类型）比当前时间早
*   @NotEmpty 验证注解的元素值不为 null 且不为空（字符串长度不为 0、集合大小不为 0）
*   @NotBlank 验证注解的元素值不为空（不为 null、去除首位空格后长度为 0），不同于 @NotEmpty，@NotBlank 只应用于字符串且在比较时会去除字符串的空格
*   @Email 验证注解的元素值是 Email，也可以通过正则表达式和 flag 指定自定义的 email 格式

spring-boot-starter-web 包里面有 hibernate-validator 包，所以如果开发 web 就不需要重复添加 spring-boot-starter-validation 依赖了。但如果没用 web 依赖时候想要实现 Bean 验证，则只要单单加入 spring-boot-starter-validation 依赖即可。  
spring-boot-starter-web 依赖关系：

```
<dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
        </dependency>
        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-validator</artifactId>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
        </dependency>
    </dependencies>
```

spring-boot-starter-validation 依赖关系：

```
<dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.tomcat.embed</groupId>
            <artifactId>tomcat-embed-el</artifactId>
        </dependency>
        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-validator</artifactId>
        </dependency>
    </dependencies>
```

常用示例
====

User.java

```
@Data // Lombok注解，可以使我们不用再在代码里手动加get、set、toString、equals和hashCode等方法
public class User {

    @NotBlank(message = "用户名不能为空")
    private String name;

    @NotBlank(message = "年龄不能为空")
    @Range(min = 0, max = 120, message = "年龄只能从0-120岁")
    private String age;

    // 如果是空，则不校验，如果不为空，则校验
    @Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$", message = "出生日期格式不正确")
    private String birthday;

}
```

一、校验 bean（参数封装成对象）
------------------

**1、 POST 接口 + @Valid + BindingResult 验证**示例：

```
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 创建用户
     * @requestBody可以将请求体中的JSON字符串绑定到相应的bean上
     * BindingResult是验证不通过的结果集合
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public String postUser(@RequestBody @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }
            return "error";
        }
        return "success";
    }

}
```

json 格式请求参数：  
![](https://img-blog.csdn.net/2018072314320446?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3d1c2V5dWt1aQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

输出结果：

```
出生日期格式不正确
用户名不能为空
年龄只能从0-120岁
```

**2、GET 接口 + @Valid + BindingResult 验证**示例：

```
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 创建用户
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    @ResponseBody
    public String postUser(@RequestBody @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }
            return "error";
        }
        return "success";
    }

}
```

请求参数：  
![](https://img-blog.csdn.net/20180723144756248?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3d1c2V5dWt1aQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

输出结果：

```
用户名不能为空
年龄只能从0-120岁
出生日期格式不正确
```

补充：可以加多个 @Valid 和 BindingResult，如下：

```
public void test()(
    @RequestBody @Valid Model demo1, BindingResult result, 
    @RequestBody @Valid Model demo2, BindingResult result2) {
    }
```

**3、 @Validated + 全局捕获异常处理（最佳实践）**

测试时：使用 @Valid + 全局捕获异常处理也可以，具体区别有待研究。

当使用了 @Validated + @RequestBody 注解但是没有在绑定的数据对象后面跟上 Errors 类型的参数声明的话，Spring MVC 框架会抛出 MethodArgumentNotValidException 异常。

```
/**
     * 创建用户
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public String postUser(@RequestBody @Validated User user) {
        return "success";
    }
```

全局捕获异常处理

```
@ControllerAdvice
@Component
public class GlobalExceptionHandler{

    private static final Logger logger = LoggerFactory.getLogger(ExceptionhandlerController.class);

    /**
     * 处理所有不可知的异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    Result handleException(Exception e) {
        logger.error(e.getMessage(), e);
        return Result.error(CodeMsg.SERVER_ERROR);
    }

    ......

    /**
     * 处理实体字段校验不通过异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result validationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        StringBuilder builder = new StringBuilder();

        for (FieldError error : fieldErrors) {
            builder.append( "\n" + error.getDefaultMessage());
        }
        logger.error(builder.toString());
        return Result.error(CodeMsg.BIND_ERROR);
    }

}
```

json 格式请求参数：  
![](https://img-blog.csdn.net/2018072314320446?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3d1c2V5dWt1aQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

运行结果：

```
2018-07-23 14:31:35.706 ERROR 5432 --- [nio-8080-exec-2] c.h.m.e.GlobalExceptionHandler      : 
用户名不能为空
出生日期格式不正确
年龄只能从0-120岁
```

二、@RequestParam 参数校验（一般用在处理 Get 请求或参数比较少）
-----------------------------------------

使用 @Valid 注解，对 RequestParam 对应的参数进行注解，是无效的，需要使用 @Validated 注解来使得验证生效。

示例：

1、创建 MethodValidationPostProcessor 的 Bean

```
@Configuration
@EnableAutoConfiguration
public class FactoryConfig {

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

}
```

2、方法所在的 Controller 上加注解 @Validated

```
@Controller
@RequestMapping("/user")
@Validated
public class UserController {

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public String getUser(
            /** 如果只有少数参数，直接把参数写到Controller层，然后在Controller层进行验证就可以了 */
            @Min(value = 1, message = "id最小只能1")
            @Max(value = 99, message = "id最大只能99")
            @RequestParam(name = "userid", required = true) Integer userid) {
        return userid+"";
    }

}
```

请求参数：

```
http://localhost:8080/user/get?userid=10000
```

运行结果：

```
javax.validation.ConstraintViolationException: null
    at org.springframework.validation.beanvalidation.MethodValidationInterceptor.invoke(MethodValidationInterceptor.java:147) ~[spring-context-4.3.12.RELEASE.jar:4.3.12.RELEASE]
    at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179) ~[spring-aop-4.3.12.RELEASE.jar:4.3.12.RELEASE]
    at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:673) ~[spring-aop-4.3.12.RELEASE.jar:4.3.12.RELEASE]
```

可以看到：验证不通过时，抛出了 **ConstraintViolationException** 异常，可以使用全局捕获异常处理。

```
@ControllerAdvice
@Component
public class GlobalExceptionHandler{

    private static final Logger logger = LoggerFactory.getLogger(ExceptionhandlerController.class);

    /**
     * 处理所有不可知的异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    Result handleException(Exception e) {
        logger.error(e.getMessage(), e);
        return Result.error(CodeMsg.SERVER_ERROR);
    }

    ......

    /**
     * 处理@RequestParam校验不通过异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public Result validationError(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        StringBuilder builder = new StringBuilder();
        for (ConstraintViolation<?> item : violations) {
            builder.append( "\n" + item.getMessage());
        }

        logger.error(builder.toString());
        return Result.error(CodeMsg.BIND_ERROR);
    }

}
```

运行结果：

```
2018-07-23 14:32:31.376 ERROR 5432 --- [nio-8080-exec-4] c.h.m.e.GlobalExceptionHandler      : 
id最大只能99
```

hibernate 的校验模式
===============

官方文档：[https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#section-provider-specific-settings](https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#section-provider-specific-settings)

1、普通模式（默认是这个模式）

普通模式 (会校验完所有的属性，然后返回所有的验证失败信息)

2、快速失败返回模式

快速失败返回模式 (只要有一个验证失败，则返回)

配置方式：

fail_fast： true 快速失败返回模式 false 普通模式

```
@Configuration
@EnableAutoConfiguration
public class ValidateConfig {

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        MethodValidationPostProcessor postProcessor = new MethodValidationPostProcessor();
        // 设置validator模式为快速失败返回模式
        postProcessor.setValidator(validator());
        return postProcessor;
    }

    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .addProperty("hibernate.validator.fail_fast", "true")
                .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        return validator;
    }

}
```

model 校验
========

```
@RequestMapping("/demo")
    @ResponseBody
    public String demo() {
        User user = new User();
        user.setName("hykk");
        user.setAge("222");
        user.setBirthday("112133");
        Set<ConstraintViolation<User>> violationSet = validator.validate(user);
        for (ConstraintViolation<User> model : violationSet) {
            System.out.println("校验：" + model.getMessage());
        }
        return "demo";
    }
```

请求结果：

```
校验：年龄只能从0-120岁
```

自定义验证器
======

bean：

```
public class User {
    ......
        @IsMobile(message = "手机号码格式错误")
        private String mobile;
    ......

}
```

接口：

```
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {IsMobileValidator.class })
public @interface  IsMobile {

    boolean required() default true;

    String message() default "";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
```

校验规则：

```
public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {

    public static final String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";

    private boolean required = false;

    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (required) {
            return isMobile(value);
        } else {
            if (StringUtils.isEmpty(value)) {
                return true;
            } else {
                return isMobile(value);
            }
        }
    }

    private boolean isMobile(String phone) {
        if (phone.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            return m.matches();
        }
    }

}
```

测试：

```
@RequestMapping("/demo")
    @ResponseBody
    public String demo() {
        User user = new User();
        user.setName("Tom");
        user.setAge("22");
        user.setMobile("123123123");
        Set<ConstraintViolation<User>> violationSet = validator.validate(user);
        for (ConstraintViolation<User> model : violationSet) {
            System.out.println("校验：" + model.getMessage());
        }
        return "demo";
    }
```

运行结果：

```
校验：手机号码格式错误
```

分组校验
====

分组
--

有这样一种场景，新增用户的时候，不需要验证主键 id（因为系统生成）；修改的时候需要验证主键 id，这时候可以用到 validator 的分组验证功能。

首先创建两个分组接口：

```
// 代表 新增 分组
public interface AddGroup {
}

// 代表 更新 分组
public interface UpdateGroup {
}
```

Bean 字段指定需要验证的分组

```
@Data
public class User {

    @NotNull(message = "主键id不能为空", groups = {UpdateGroup.class})
    private Long id;

    @NotBlank(message = "用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String name;

    @NotBlank(message = "年龄不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @Range(min = 0, max = 120, message = "年龄只能从0-120岁", groups = {AddGroup.class, UpdateGroup.class})
    private String age;

    // 如果是空，则不校验，如果不为空，则校验
    @Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$", message = "出生日期格式不正确", groups = {AddGroup.class, UpdateGroup.class})
    private String birthday;

}
```

### 示例一：

```
/**
     * 创建用户
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public String postUser(@RequestBody @Validated(AddGroup.class)  User user) {
        return "create success";
    }
```

请求参数：  
![](https://img-blog.csdn.net/20180724150608927?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3d1c2V5dWt1aQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)  
运行结果：

```
// 验证通过
```

### 示例二：

```
/**
     * 更新用户
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String updateUser(@RequestBody @Validated(UpdateGroup.class)  User user) {
        return "update success";
    }
```

请求参数：  
![](https://img-blog.csdn.net/20180724150703721?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3d1c2V5dWt1aQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)  
运行结果：

```
2018-07-24 14:59:47.178 ERROR 13320 --- [nio-8080-exec-4] c.h.m.e.GlobalExceptionHandler      : 
主键id不能为空
```

组序列
---

除了按组指定是否验证之外，还可以指定组的验证顺序，前面组验证不通过的，后面组不进行验证：  
指定组的序列（Group1 > Group2 > Default）

```
public interface Group1 {
}
public interface Group2 {
}
public interface Group3 {
}
```

指定组序列

```
@GroupSequence({Group1.class, Group2.class, Group3.class, Default.class})
public interface GroupOrder {

}
```

校验顺序 id > age > name > birthday

```
@Data
public class User {

    @NotNull(message = "主键id不能为空", groups = {Group1.class})
    private Long id;

    @NotBlank(message = "用户名不能为空", groups = {Group3.class})
    private String name;

    @NotBlank(message = "年龄不能为空", groups = {Group2.class})
    @Range(min = 0, max = 120, message = "年龄只能从0-120岁", groups = {Group2.class})
    private String age;

    // 如果是空，则不校验，如果不为空，则校验
    @Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$", message = "出生日期格式不正确")
    private String birthday;

}
```

```
/**
     * 更新用户
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String updateUser(@RequestBody @Validated(GroupOrder.class)  User user) {
        return "update success";
    }
```

请求参数：

```
{"id":"1","name":"","age":121,"birthday":"20000-11-120"}
```

运行结果：

```
2018-07-24 15:18:20.079 ERROR 12336 --- [nio-8080-exec-3] c.h.m.e.GlobalExceptionHandler      : 
年龄只能从0-120岁
```