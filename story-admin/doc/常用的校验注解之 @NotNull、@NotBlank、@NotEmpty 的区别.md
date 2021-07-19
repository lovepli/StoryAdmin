实体字段校验 @NotNull、@NotEmpty、@NotBlank
1.@NotNull
不能为 null，但可以为 empty，一般用在 Integer 类型等的基本数据类型的封装类的非空校验上，而且被其标注的字段可以使用 @size、@Max、@Min 对字段数值进行大小的控制

2.@NotEmpty
不能为 null，且长度必须大于 0，一般用在集合类上或者数组上

3.@NotBlank
只能作用在接收的 String 类型上，注意是只能，不能为 null，而且调用 trim() 后，长度必须大于 0即：必须有实际字符


注意
在使用 @NotBlank 等注解时，一定要和 @valid 一起使用，否则 @NotBlank 不起作用。
一个 BigDecimal 的字段使用字段校验标签应该为 @NotNull。
在使用 @Length 一般用在 String 类型上可对字段数值进行最大长度限制的控制。
在使用 @Range 一般用在 Integer 类型上可对字段数值进行大小范围的控制。


java注解校验参数分两种方式，一种是Java框架自带的Javax-valation参数注解，一种是从hibernate框架剥离的hibernate-validator注解

1、常用的校验注解
javax.validation.constraints.xxx

注解	说明
@Null	被注释的元素必须为null
@NotNull	被注释的元素不能为null
@AssertTrue	被注释的元素必须为true
@AssertFalse	被注释的元素必须为false
@Min(value)	被注释的元素必须是一个数字，其值必须大于等于指定的最小值
@Max(value)	被注释的元素必须是一个数字，其值必须小于等于指定的最大值
@DecimalMin(value)	被注释的元素必须是一个数字，其值必须大于等于指定的最小值
@DecimalMax(value)	被注释的元素必须是一个数字，其值必须小于等于指定的最大值
@Size(max,min)	被注释的元素的大小必须在指定的范围内。
@Digits(integer,fraction)	被注释的元素必须是一个数字，其值必须在可接受的范围内
@Past	被注释的元素必须是一个过去的日期
@Future	被注释的元素必须是一个将来的日期
@Pattern(value)	被注释的元素必须符合指定的正则表达式。
@Email	被注释的元素必须是电子邮件地址
@Length	被注释的字符串的大小必须在指定的范围内
@NotEmpty	被注释的字符串必须非空
@Range	被注释的元素必须在合适的范围内

2、常用的校验注解依赖： hibernate-validator
org.hibernate.validator.constraints.xxx
其中已弃用的注解：@Email, @NotEmpty, @NotBlank

优点及注解说明
validator的优点:
解耦，数据的校验与业务逻辑进行分离，降低耦合度
规范的校验方式，减少参数校验所带来的繁琐体力活
简洁代码，提高代码的可读性
Validation constraint

注解	说明
@AssertFalse	被注释的元素必须为 false
@AssertTrue	被注释的元素必须为 true
@DecimalMin(value)	被注释的元素必须是一个数字，其值必须大于等于指定的最小值
@DecimalMax(value)	被注释的元素必须是一个数字，其值必须小于等于指定的最大值
@Digits (integer, fraction)	被注释的元素必须是一个数字，其值必须在可接受的范围内
@Email	被注释的元素必须是电子邮箱地址
@Future	被注释的元素必须是一个将来的日期
@Length(min=,max=)	被注释的字符串的大小必须在指定的范围内
@Min(value)	被注释的元素必须是一个数字，其值必须大于等于指定的最小值
@Max(value)	被注释的元素必须是一个数字，其值必须小于等于指定的最大值
@Negative	该值必须小于0
@NegativeOrZero	该值必须小于等于0
@Null	被注释的元素必须为 null
@NotNull	被注释的元素必须不为 null
@NotBlank(message =)	验证字符串非null，且长度必须大于0
@NotEmpty	被注释的字符串的必须非空
@Past	被注释的元素必须是一个过去的日期
@Pattern(regex=,flag=)	被注释的元素必须符合指定的正则表达式
@Positive	该值必须大于0
@PositiveOrZero	该值必须大于等于0
@Range(min=,max=,message=)	被注释的元素必须在合适的范围内
@Size(max=, min=)	数组大小必须在[min,max]这个区间
@URL(protocol=,host,port)	检查是否是一个有效的URL，如果提供了protocol，host等，则该URL还需满足提供的条件
@Valid	该注解主要用于字段为一个包含其他对象的集合或map或数组的字段，或该字段直接为一个其他对象的引用，这样在检查当前对象的同时也会检查该字段所引用的对象



附 @JsonFormat
有时使用 @JsonFormat 注解时，查到的时间可能会比数据库中的时间少八个小时，这是由于时区差引起的，JsonFormat 默认的时区是 Greenwich Time， 默认的是格林威治时间，而我们是在东八区上，所以时间会比实际我们想得到的时间少八个小时。需要在后面加上一个时区,如下示例：
@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
private Date date;




原文链接：https://blog.csdn.net/weixin_49770443/article/details/109772162
其他校验参考：
https://blog.csdn.net/lupengfei1009/article/details/88137181
https://blog.csdn.net/kuangdaoyizhimei/article/details/111903345