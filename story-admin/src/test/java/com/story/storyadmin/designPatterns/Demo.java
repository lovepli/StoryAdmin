package com.story.storyadmin.designPatterns;


import com.story.storyadmin.utils.wind.StringUtils;
import lombok.Data;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Optional;
import java.util.function.Function;


/**
 * @author: lipan
 * @date: 2021/8/18
 * @description: 去除 代码中的if-esle的实践技巧
 */
public class Demo {

    // 完全不必要的Else块
    public void performOperation(int input){
      if (input == 5){
          //do something
      }else {
          return;
      }
    }

    //优化方案 1：提前 return，去除不必要的 else
    //如果 if-else 代码块包含 return 语句，可以考虑通过提前 return，把多余 else 干掉，使代码更加优雅。
    public void performOperation2(int input){
        //if (input != 5){
        //    return;
        //}
        if (input != 5) return;
        //do something
    }

    //2. 价值分配
    //如果您要根据提供的某些输入为变量分配新值，请停止If-Else废话-一种更具可读性的方法。
    public String performOperation3(int input){
        String gender = null;
        if (input == 0){
        gender = "male";
        }else if (input == 1){
            gender = "woman";
        }else {
            gender ="unknown";
        }
        return gender;
    }

    //尽管很简单，但它却很糟糕。首先，If-Else很容易在这里被开关取代。但是，我们可以通过完全删除else来进一步简化此代码。
    //如果不使用else，则我们将剩下干净的可读代码。请注意，我也将样式更改为快速返回而不是单返回语句-如果已经找到正确的值，继续测试一个值根本没有意义。
    public String performOperation4(int input){
        if (input == 0) return "male";
        if (input == 1) return "woman";

        return "unknown";
    }

    //3. 前提条件检查
    //通常，我发现，如果方法提供了无效的值，则继续执行是没有意义的。
    //假设我们从以前就有了DefineGender方法，要求提供的输入值必须始终为0或1。
    //在没有价值验证的情况下执行该方法没有任何意义。因此，在允许方法继续执行之前，我们需要检查一些先决条件。
    //应用保护子句防御性编码技术，您将检查方法的输入值，然后继续执行方法。
    //至此，我们确保仅在值落在预期范围内时才执行主逻辑。
    //现在，IF也已被三目运算符代替，因为不再需要在结尾处默认返回"未知"。
    public String performOperation5(int input){
        if (input <0 )throw new ArithmeticException();
        if(input >1 ) throw new ArithmeticException();
        //do something else
        return input == 0 ? "women":"man";
    }

    //优化方式，针对空异常、数值判断等情况的。示例代码如下所示：
    public void performOperation6(){
        EmpInfo empInfo=new EmpInfo();
        if(null == empInfo){
            throw new NullPointerException("员工信息不能为空！");
        }else {
            if(StringUtils.isEmpty(empInfo.getName())){
                throw new NullPointerException("员工姓名不能为空！");
            }
        }
    }

    //你看，如果我的方法中，有十几个属性需要判断，是不是就需要十几个 if else 去处理？太复杂了，太臃肿了，if else 看的我头都大了。那么该怎么办呢？
   //将上面的代码改成使用 Assert 去处理。这样，方法中的代码是不是就好看多了？把简洁养成个人习惯！
    //然后抛出的异常该怎么处理呢？ControllerAdvice，全局异常处理。拦截所有异常，自己定义状态码，返回给各个接口！
    public void performOperation7(){
        EmpInfo empInfo=new EmpInfo();
        Assert.assertNull("员工信息不能为空!",empInfo);
        Assert.assertNull("员工姓名不能为空!",StringUtils.isEmpty(empInfo.getName()));
    }

    //这段代码带上 else 分支也没关系，照样能优化。还有类似下面的代码，虽然有注释，但是还是太复杂化了。
    public void performOperation8(){
        EmpInfo emp=new EmpInfo();
        if(1==emp.getStatus()){
            //员工在职
            if (30<emp.getAge()){
                //如果年龄大于30，六一儿童节放假半天
            }else {
                //正常考情
            }
        }else {
            //判断是否离职一年以上，发问候函
        }
    }

    //而我的“去掉 if else”或者说简化 if else 的方案如下
    //虽然还存在“if else”，但是看起来逻辑更清晰一些了。分支更少了，代码更简洁了！
    public void performOperation9(){
        EmpInfo emp=new EmpInfo();
        if(1==emp.getStatus()){
            //员工在职
            if (30<emp.getAge()){
                //如果年龄大于30，六一儿童节放假半天
                //todo
                return;
            }
                //正常考勤
                //todo
                 return;
        }
            //判断是否离职一年以上，发问候函
           //todo
    }

    public void performOperation10(int orderStatus){
        String orderStatusDes;
        if(orderStatus == 0){
            orderStatusDes = "订单未支付";
        }else if(orderStatus == 1){
            orderStatusDes = "订单已支付";
        }else if(orderStatus == 2){
            orderStatusDes = "已发货";
        }
    }

    public void performOperation11(int orderStatus){
        String orderStatusDes;
        if(orderStatus == 0){
            orderStatusDes = "订单未支付";
        }else if(orderStatus == 1){
            orderStatusDes = "订单已支付";
        }else if(orderStatus == 2){
            orderStatusDes = "已发货";
        }
    }

    //优化方案 3：使用枚举
    //
    //在某些时候，使用枚举也可以优化 if-else 逻辑分支，按个人理解，它也可以看作一种表驱动方法。
    public void performOperation12(int orderStatus){
        //有了枚举之后，以上 if-else 逻辑分支，可以优化为一行代码：
        String orderStatusDes=OrderStatusEnum.of(orderStatus).getDesc();
    }


    //优化方案 5：使用 Optional
    //
    //有时候 if-else 比较多，是因为非空判断导致的，这时候你可以使用 java8 的 Optional 进行优化。
    public void performOperation13(){
        String str = "jay@huaxiao";
        if (str != null) {
            System.out.println(str);
        } else {
            System.out.println("Null");
        }
    }

    public void performOperation14(){
        Optional<String> strOptional = Optional.of("jay@huaxiao");
        //strOptional.ifPresentOrElse(System.out::println, () -> System.out.println("Null"));
    }

    public void performOperation15(String someParams,String param,String value1,String value2,String value3){
        if (param.equals(value1)) {
            doAction1(someParams);
        } else if (param.equals(value2)) {
            doAction2(someParams);
        } else if (param.equals(value3)) {
            doAction3(someParams);
        }
    }

    //优化方案 6：表驱动法
    //
    //表驱动法，又称之为表驱动、表驱动方法。表驱动方法是一种使你可以在表中查找信息，而不必用很多的逻辑语句（if 或 case）来把它们找出来的方法。
    //
    //以下的 demo，把 map 抽象成表，在 map 中查找信息，而省去不必要的逻辑语句。
    //利用Java 8 的 Lambda 和 Functional Interface来进行重构。
    public void performOperation16(String someParams,String param,String value1,String value2,String value3){
//        Map<?, Function<?> action> actionMappings = new HashMap<>(); // 这里泛型 ? 是为方便演示，实际可替换为你需要的类型
//// 初始化
//        actionMappings.put(value1, (someParams) -> { doAction1(someParams)});
//        actionMappings.put(value2, (someParams) -> { doAction2(someParams)});
//        actionMappings.put(value3, (someParams) -> { doAction3(someParams)});
//// 省略多余逻辑语句
//        actionMappings.get(param).apply(someParams);

    }

    private void doAction1(String someParams){

    }
    private void doAction2(String someParams){

    }
    private void doAction3(String someParams){

    }

}

enum OrderStatusEnum{
    UN_PAID(0,"订单未支付"),PAIDED(1,"订单已支付"),SENDED(2,"已发货"),;
    private int index;
    private String desc;
    public int getIndex(){
        return index;
    }
    public String getDesc(){
        return desc;
    }
    OrderStatusEnum(int index,String desc){
        this.index=index;
        this.desc=desc;
    }
    static OrderStatusEnum of(int orderStatus){
       for (OrderStatusEnum temp:OrderStatusEnum.values()){
           if (temp.getIndex() == orderStatus){
               return temp;
           }
       }
       return null;
    }
}


@Data
class EmpInfo {
    private String name;
    private int status;
    private int age;
}
