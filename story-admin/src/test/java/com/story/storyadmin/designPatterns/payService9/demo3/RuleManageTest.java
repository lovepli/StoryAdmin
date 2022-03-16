package com.story.storyadmin.designPatterns.payService9.demo3;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author: lipan
 * @date: 2021/8/20
 * @description: 自定义规则执行器 https://www.cnblogs.com/ding-dang/p/14693052.html
 * 使用规则执行器代替 if else 判断
 */
public class RuleManageTest {

    @Test
    public void execute() {
        //规则执行器
        //优点：比较简单，每个规则可以独立，将规则，数据，执行器拆分出来，调用方比较规整
        //缺点：数据依赖公共传输对象 dto

        //1. 定义规则  init rule
        NationalityRule nationalityRule = new NationalityRule();
        AddressRule addressRule = new AddressRule();

        //2. 构造需要的数据 create dto
        RuleDTO dto = new RuleDTO();
        dto.setAge(5);
        dto.setAddress("北京");

        //3. 通过以链式调用构建和执行 rule execute
        boolean ruleResult = RuleManage
                .create()
                .and(Arrays.asList(nationalityRule, addressRule))
                //.or(Arrays.asList(addressRule, nationalityRule))
                .execute(dto);
        System.out.println("this student rule execute result :" + ruleResult);
    }


    /**
     * 总结
     * 优点
     * 比较简单，每个规则可以独立，将规则，数据，执行器拆分出来，调用方比较规整
     * Rule 模板类中定义 convert 方法做参数的转换这样可以能够，为特定 rule 需要的场景数据提供拓展
     * 缺点
     * 上下 rule 有数据依赖性，如果直接修改公共传输对象 dto 这样设计不是很合理，建议提前构建数据
     */
}


