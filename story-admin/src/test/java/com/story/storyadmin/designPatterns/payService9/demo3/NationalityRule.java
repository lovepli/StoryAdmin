package com.story.storyadmin.designPatterns.payService9.demo3;

/**
 * @author: lipan
 * @date: 2021/8/20
 * @description: 国家规则
 */
public class NationalityRule extends BaseRule{

    @Override
    protected <T> T convert(RuleDTO dto) {
        NationalityRuleDTO nationalityRuleDto = new NationalityRuleDTO();
        if (dto.getAddress().startsWith(MATCH_ADDRESS_START)) {
            nationalityRuleDto.setNationality(MATCH_NATIONALITY_START);
        }
        return (T) nationalityRuleDto;
    }


    @Override
    protected <T> boolean executeRule(T t) {
        System.out.println("NationalityRule invoke!");
        NationalityRuleDTO nationalityRuleDto = (NationalityRuleDTO) t;
        return nationalityRuleDto.getNationality().startsWith(MATCH_NATIONALITY_START);
    }

}
