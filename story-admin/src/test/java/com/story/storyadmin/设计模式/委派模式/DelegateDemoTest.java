package com.story.storyadmin.设计模式.委派模式;

/**
 * @author: lipan
 * @date: 2021/8/22
 * @description:
 */
public class DelegateDemoTest {
    public static void main(String[] args) {
        new Boss().command("架构设计", new Leader());
    }

    /**
     * 生活案列
     * 公司内，老板把任务下发给项目经理，项目经理自己不会去干活，而是把这些任务按照每个人负责的模块，交给对应的开发同事们去开发，大家把任务完成结果告诉项目经理，最后项目经理把结果汇总给老板。
     *
     * 这边是一个非常典型的委派模式的应用场景。
     *
     * 这样我们就把一个生活中委派模式的案例，使用代码实现了。简单否？
     *
     * 上面的案例中，有三个重要的角色：
     *
     * 抽象人物角色IEmployee
     * 具体任务角色：EmployeeA、EmployeeB、EmployeeC
     * 委派这角色：Leader
     */
}
