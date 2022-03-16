package com.story.storyadmin.设计模式.委派模式;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: lipan
 * @date: 2021/8/22
 * @description: 员工有了，那么我们就来定义项目经理Leader。
 */
public class Leader {

    private Map<String, IEmployee> employeeMap = new HashMap<>();
    //既然是项目经历，那他心里，肯定知道每个开发同事擅长的领域是什么
    public Leader() {
        employeeMap.put("数据库设计", new EmployeeA());
        employeeMap.put("架构设计", new EmployeeB());
        employeeMap.put("业务代码", new EmployeeC());
    }

    //leader接收到老板Boss的任务命令后
    public void doing(String command) {
        //项目经理通过任务命令，找到对应的开发同事，
        // 然后把对应任务明给这位同事，这位同事就可以去干活了
        employeeMap.get(command).doing(command);
    }
}
