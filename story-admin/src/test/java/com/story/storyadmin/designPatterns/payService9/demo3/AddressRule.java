package com.story.storyadmin.designPatterns.payService9.demo3;

/**
 * @author: lipan
 * @date: 2021/8/20
 * @description: 地址规则
 */
public class AddressRule extends BaseRule{

    @Override
    public boolean execute(RuleDTO dto) {
        System.out.println("AddressRule invoke!");
        return dto.getAddress().startsWith(MATCH_ADDRESS_START);
    }

}
