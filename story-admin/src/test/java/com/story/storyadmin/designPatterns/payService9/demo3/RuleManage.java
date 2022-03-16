package com.story.storyadmin.designPatterns.payService9.demo3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: lipan
 * @date: 2021/8/20
 * @description: 规则执行器
 */
public class RuleManage {

    private Map<Integer, List<Rule>> hashMap = new HashMap<>();
    private static final int AND = 1;
    private static final int OR = 0;

    public static RuleManage create() {
        return new RuleManage();
    }


    public RuleManage and(List<Rule> ruleList) {
        hashMap.put(AND, ruleList);
        return this;
    }

    public RuleManage or(List<Rule> ruleList) {
        hashMap.put(OR, ruleList);
        return this;
    }

    public boolean execute(RuleDTO dto) {
        for (Map.Entry<Integer, List<Rule>> item : hashMap.entrySet()) {
            List<Rule> ruleList = item.getValue();
            switch (item.getKey()) {
                case AND:
                    // 如果是 and 关系，同步执行
                    System.out.println("execute key = " + 1);
                    if (!and(dto, ruleList)) {
                        return false;
                    }
                    break;
                case OR:
                    // 如果是 or 关系，并行执行
                    System.out.println("execute key = " + 0);
                    if (!or(dto, ruleList)) {
                        return false;
                    }
                    break;
                default:
                    break;
            }
        }
        return true;
    }

    private boolean and(RuleDTO dto, List<Rule> ruleList) {
        for (Rule rule : ruleList) {
            boolean execute = rule.execute(dto);
            if (!execute) {
                // and 关系匹配失败一次，返回 false
                return false;
            }
        }
        // and 关系全部匹配成功，返回 true
        return true;
    }

    private boolean or(RuleDTO dto, List<Rule> ruleList) {
        for (Rule rule : ruleList) {
            boolean execute = rule.execute(dto);
            if (execute) {
                // or 关系匹配到一个就返回 true
                return true;
            }
        }
        // or 关系一个都匹配不到就返回 false
        return false;
    }

}
