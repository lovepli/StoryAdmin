> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [my.oschina.net](https://my.oschina.net/shadowolf/blog/1934934)

### 1. 参数校验

* * *

*   [官网地址](https://www.oschina.net/action/GoToLink?url=http%3A%2F%2Fhibernate.org%2Fvalidator%2Fdocumentation%2F)

*   spring-boot-starter-web 包里面有 hibernate-validator 包，不需要引用 hibernate validator 依赖。


### 2. hibernate validator 校验 demo

* * *

#### 1. 导入包

```
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.Pattern;
```

#### 2. demo

```
@Getter
@Setter
@NoArgsConstructor
public class DemoModel {
    @NotBlank(message="用户名不能为空")
    private String userName;

    @NotBlank(message="年龄不能为空")
    @Pattern(regexp="^[0-9]{1,2}$",message="年龄不正确")
    private String age;

    @AssertFalse(message = "必须为false")
    private Boolean isFalse;
    
    @Pattern(regexp="^[0-9]{4}-[0-9]{2}-[0-9]{2}$",message="出生日期格式不正确")
    private String birthday;
}
```

#### 3. 结果返回

```
@RequestMapping("/demo2")
public void demo2(@RequestBody @Valid DemoModel demo, BindingResult result){
    if(result.hasErrors()){
        for (ObjectError error : result.getAllErrors()) {
            System.out.println(error.getDefaultMessage());
        }
    }
}
```

#### 4. 传入参数

```
{"userName":"dd","age":120,"isFalse":true,"birthday":"21010-21-12"}
```

#### 5. 输出结果

```
出生日期格式不正确
必须为false
年龄不正确
```

### 3. hibernate 的校验模式

* * *

#### 1. 普通模式 (默认为该模式)

*   会校验所有属性，然后返回所有的验证失败信息。

#### 2. 快速失败返回模式

*   只要有一个校验失败则返回。

#### 3. 设置方式

```
// failFast: true 快速失败返回模式，false 普通模式
ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
        .configure()
        .failFast( true )
        .buildValidatorFactory();
Validator validator = validatorFactory.getValidator();
```

```
// hibernate.validator.fail_fast: true 快速失败返回模式，false 普通模式
ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
        .configure()
        .addProperty( "hibernate.validator.fail_fast", "true" )
        .buildValidatorFactory();
Validator validator = validatorFactory.getValidator();
```

### 4. hibernate 的校验

* * *

*   配置 hibernate Validator 为快速返回模式：

    ```
    @Configuration
    public class ValidatorConfiguration {
        @Bean
        public Validator validator(){
            ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
                    .configure()
                    .addProperty( "hibernate.validator.fail_fast", "true" )
                    .buildValidatorFactory();
            Validator validator = validatorFactory.getValidator();
    
            return validator;
        }
    }
    ```


#### 1. 请求参数校验

*   验证请求参数时，在 @RequestBody DemoModel demo 之间加注解 [@Valid](https://my.oschina.net/u/3652407)，然后在后面加 BindindResult 即可；多个参数，可以添加多个 [@Valid](https://my.oschina.net/u/3652407) 和 BindindResult。

```
public void test()(@RequestBody @Valid DemoModel demo, BindingResult result)
public void test()(@RequestBody @Valid DemoModel demo, BindingResult result,@RequestBody @Valid DemoModel demo2, BindingResult result2)
```

```
@RequestMapping("/demo2")
public void demo2(@RequestBody @Valid DemoModel demo, BindingResult result){
    if(result.hasErrors()){
        for (ObjectError error : result.getAllErrors()) {
            System.out.println(error.getDefaultMessage());
        }
    }
}
```

#### 2. GET 参数校验 (@RequestParam 参数校验)

1.  controller

    ```
    @RequestMapping(value = "/demo3", method = RequestMethod.GET)
    public void demo3(@RequestParam(name = "grade", required = true) int grade,@RequestParam(name = "classroom", required = true) int classroom) {
        System.out.println(grade + "," + classroom);
    }
    ```

    *   使用 [@Valid](https://my.oschina.net/u/3652407) 注解对 RequestParam 对应的参数时无效的，需要使用 @Validated 注解来使验证生效。
2.  MethodValidationPostProcessor 的 Bean

    ```
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        
        return new MethodValidationPostProcessor();
    }
    ```

3.  或者可对 MethodValidationPostProcessor 进行设置 Validator

    *   此时不是使用 Validator 进行验证，Validator 的配置不起作用

    ```
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        MethodValidationPostProcessor postProcessor = new MethodValidationPostProcessor();
        
        postProcessor.setValidator(validator());
        return postProcessor;
    }
    
    @Bean
    public Validator validator(){
        ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
                .configure()
                .addProperty( "hibernate.validator.fail_fast", "true" )
                .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();
    
        return validator;
    }
    ```

4.  方法所在的 Controller 上加注解 @Validated

    ```
    @RequestMapping("/validation")
    @RestController
    @Validated
    public class ValidationController {
        
        @RequestMapping(value = "/demo3", method = RequestMethod.GET)
        public void demo3(@Range(min = 1, max = 9, message = "年级只能从1-9")
                          @RequestParam(name = "grade", required = true)
                          int grade,
                          @Min(value = 1, message = "班级最小只能1")
                          @Max(value = 99, message = "班级最大只能99")
                          @RequestParam(name = "classroom", required = true)
                          int classroom) {
            System.out.println(grade + "," + classroom);
        }
    }
    ```

5.  返回验证信息提示

    *   验证不通过，抛出来 ConstraintViolationException 异常，使用统一捕获异常处理

    ```
    @ControllerAdvice
    @Component
    public class GlobalExceptionHandler {
    
        @ExceptionHandler
        @ResponseBody
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public String handle(ValidationException exception) {
            if(exception instanceof ConstraintViolationException){
                ConstraintViolationException exs = (ConstraintViolationException) exception;
    
                Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
                for (ConstraintViolation<?> item : violations) {
    　　　　　　　　　　
                    System.out.println(item.getMessage());
                }
            }
            return "bad request, " ;
        }
    }
    ```

6.  验证

    ```
    http:
    ```


#### 3. model 校验

1.  model

    ```
    @Data
    public class Demo2 {
        @Length(min = 5, max = 17, message = "length长度在[5,17]之间")
        private String length;
    
        
        @Size(min = 1, max = 3, message = "size在[1,3]之间")
        private String age;
    
        @Range(min = 150, max = 250, message = "range在[150,250]之间")
        private int high;
    
        @Size(min = 3,max = 5,message = "list的Size在[3,5]")
        private List<String> list;
    }
    ```

2.  校验

    ```
    @Autowired
    private Validator validator;
    
    @RequestMapping("/demo3")
    public void demo3(){
        Demo2 demo2 = new Demo2();
        demo2.setAge("111");
        demo2.setHigh(150);
        demo2.setLength("ABCDE");
        demo2.setList(new ArrayList<String>(){{add("111");add("222");add("333");}});
        Set<ConstraintViolation<Demo2>> violationSet = validator.validate(demo2);
        for (ConstraintViolation<Demo2> model : violationSet) {
            System.out.println(model.getMessage());
        }
    }
    ```


#### 4. 对象级联校验

*   对象内部包含另一个对象作为属性，属性上加 @Valid，可以验证作为属性的对象内部的验证

1.  demo

    ```
    @Data
    public class Demo2 {
        @Size(min = 3,max = 5,message = "list的Size在[3,5]")
        private List<String> list;
    
        @NotNull
        @Valid
        private Demo3 demo3;
    }
    
    @Data
    public class Demo3 {
        @Length(min = 5, max = 17, message = "length长度在[5,17]之间")
        private String extField;
    }
    ```

2.  校验

    ```
    @Autowired
    private Validator validator;
    
    @RequestMapping("/demo3")
    public void demo3(){
        Demo2 demo2 = new Demo2();
        demo2.setList(new ArrayList<String>(){{add("111");add("222");add("333");}});
    
        Demo3 demo3 = new Demo3();
        demo3.setExtField("22");
        demo2.setDemo3(demo3);
        Set<ConstraintViolation<Demo2>> violationSet = validator.validate(demo2);
        for (ConstraintViolation<Demo2> model : violationSet) {
            System.out.println(model.getMessage());
        }
    }
    ```


#### 5. 分组校验

1.  校验接口

    ```
    public interface GroupA {
    }
    
    public interface GroupB {
    }
    ```

2.  demo

    ```
    @Data
    public class Person {
        @NotBlank
        @Range(min = 1,max = Integer.MAX_VALUE,message = "必须大于0",groups = {GroupA.class})
        
        private Integer userId;
        @NotBlank
        @Length(min = 4,max = 20,message = "必须在[4,20]",groups = {GroupB.class})
        
        private String userName;
        @NotBlank
        @Range(min = 0,max = 100,message = "年龄必须在[0,100]",groups={Default.class})
        
        private Integer age;
        @Range(min = 0,max = 2,message = "性别必须在[0,2]",groups = {GroupB.class})
        
        private Integer sex;
    }
    ```

    *   GroupA 校验字段 userId
    *   GroupB 校验字段 userName、sex
    *   Default 校验字段 age(Default 使 Validator 自带的默认分组)
3.  验证

    *   只验证 GroupA 和 GroupB 的分组，以下示例代码

    ```
    @RequestMapping("/demo5")
    public void demo5(){
        Person p = new Person();
        
        p.setUserId(-12);
        
        
        p.setUserName("a");
        p.setAge(110);
        p.setSex(5);
        Set<ConstraintViolation<Person>> validate = validator.validate(p, GroupA.class, GroupB.class);
        for (ConstraintViolation<Person> item : validate) {
            System.out.println(item);
        }
    }
    ```

    ```
    @RequestMapping("/demo6")
    public void demo6(@Validated({GroupA.class, GroupB.class}) Person p, BindingResult result){
        if(result.hasErrors()){
            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError error : allErrors) {
                System.out.println(error);
            }
        }
    }
    ```

4.  组序列

    *   指定组的验证顺序，前面组验证不通过，后面组不验证

    ```
    @GroupSequence({GroupA.class, GroupB.class, Default.class})
    public interface GroupOrder {
    }
    ```


### 5. 自定义校验器

* * *

#### 1. 大小写校验器

```
public enum CaseMode {
    UPPER,
    LOWER;
}


@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckCaseValidator.class)
@Documented
public @interface CheckCase {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    CaseMode value();
}


public class CheckCaseValidator implements ConstraintValidator<CheckCase, String> {
    private CaseMode caseMode;
    public void initialize(CheckCase checkCase) {
        this.caseMode = checkCase.value();
    }

    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return true;
        }

        if (caseMode == CaseMode.UPPER) {
            return s.equals(s.toUpperCase());
        } else {
            return s.equals(s.toLowerCase());
        }
    }
}
```

#### 2. Demo

```
public class Demo{
    @CheckCase(value = CaseMode.LOWER,message = "userName必须是小写")
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
```

#### 3. Validator 配置

```
@Bean
public Validator validator(){
    ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
            .configure()
            .addProperty( "hibernate.validator.fail_fast", "true" )
            .buildValidatorFactory();
    Validator validator = validatorFactory.getValidator();

    return validator;
}
```

#### 4. 校验测试

```
@RequestMapping("/demo4")
public void demo4(){
    Demo demo = new Demo();
    demo.setUserName("userName");
    Set<ConstraintViolation<Demo>> validate = validator.validate(demo);
    for (ConstraintViolation<Demo> dem : validate) {
        System.out.println(dem.getMessage());
    }
}
```

### 6. 常见的注解

* * *

<table><thead><tr><th>No.</th><th>注解</th><th>解释</th></tr></thead><tbody><tr><td>01</td><td>@Null</td><td>检查该字段为空</td></tr><tr><td>02</td><td>@NotNull</td><td>不能为 null</td></tr><tr><td>03</td><td>@NotBlank</td><td>不能为空，检查时会将空格忽略</td></tr><tr><td>04</td><td>@NotEmpty</td><td>不能为空，这里的空是指空字符串</td></tr><tr><td>05</td><td>@AssertTrue</td><td>用于 boolwan 字段，只能为 true</td></tr><tr><td>06</td><td>@AssertFalse</td><td>用于 boolwan 字段，只能为 false</td></tr><tr><td>07</td><td>@CreditCardNumber</td><td>对信用卡进行一个大致的校验</td></tr><tr><td>08</td><td>@DecimalMin(value)</td><td>数值类型，只能小于或等于 value</td></tr><tr><td>09</td><td>@DecimalMax(value)</td><td>数值类型，只能大于或等于 value</td></tr><tr><td>10</td><td>@Digits(integer=2,fraction=20)</td><td>限制必须为一个小数，整数部分位数不能超过 integer，小数部分位数不能超过 fraction</td></tr><tr><td>11</td><td>@Email</td><td>检查是否是一个有效的 email 地址</td></tr><tr><td>12</td><td>@Past</td><td>检查该字段的日期是否属于过去的日期</td></tr><tr><td>13</td><td>@Future</td><td>检查该字段的日期是否属于将来的日期</td></tr><tr><td>14</td><td>@Length(min=,max=)</td><td>检查该字段的长度是否在 min 和 max 之间，只能用于字符串</td></tr><tr><td>15</td><td>@Size(min=,max=)</td><td>检查该字段的 size 是否在 min 和 max 之间，可以是字符串、数组、集合、map 等</td></tr><tr><td>16</td><td>@Min(value)</td><td>小于等于 value</td></tr><tr><td>17</td><td>@Max(value)</td><td>大于等于 value</td></tr><tr><td>18</td><td>@URL(protocol=,host,port) 检查是否是一个有效的 URL，如果提供来 protocol，host 等，则该 url 还需满足提供的条件</td><td></td></tr><tr><td>19</td><td>@Valid</td><td>该注解只要用于字段为一个包含其他对象的集合或 map 或数组的字段，或该字段直接为一个其他对象的引用 (这样在检查当前对象的同时也会检查该字段所引用的对象)</td></tr></tbody></table>

### 7. 参考文档

* * *

[参考文档](https://www.oschina.net/action/GoToLink?url=http%3A%2F%2Fdocs.jboss.org%2Fhibernate%2Fvalidator%2F4.2%2Freference%2Fzh-CN%2Fhtml_single%2F%23validator-usingvalidator)