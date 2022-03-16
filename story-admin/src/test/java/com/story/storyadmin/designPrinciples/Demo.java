package com.story.storyadmin.designPrinciples;

/**
 * @author: lipan
 * @date: 2021/8/18
 * @description: 软件设计的七大原则
 */
public class Demo {

    /**
     * Robert C.Martin
     * 一个可维护性（Maintainability）较低的软件设计，通常由于以下4个原因造成
     *
     * 1、过于僵化(Rigidity)：设计难以修改
     *
     * 2、过于脆弱(Fragility)：设计易遭到破坏（需要修改的时候，容易牵一发而动全身，不该受到影响的代码也被迫的破坏掉）
     *
     * 3、牢固性(Immobility)：复用率低（当想使用一些功能时会发现里面的某些代码不是他想要的，想把这些代码去掉时，发现没办法去掉，原因是代码耦合度太高了）
     *
     * 4、粘度过高(Viscosity)：难以做正确事情（维护的过程中想进行修改某些代码，但是发现没有办法进行修改，原因就是粘度太高）
     *
     * PeterCoad
     * 一个好的系统设计应该具备如下三个特性：
     *
     * 可扩展性（Extendibility）
     * 灵活性（Flexibility）
     * 可插入性（Pluggability）
     * 面向对象设计原则和设计模式也是对系统进行合理重构，重构是在不改变软件现有功能的基础上，通过调整代码改善软件的质量、性能，使其程序的设计模式和架构更趋合理性，提高软件的扩展性和维护性。
     *
     * 软件设计七大原则
     * 开闭原则
     * 依赖倒置原则
     * 单一职责原则
     * 接口隔离原则
     * 迪米特法则
     * 里氏替换原则
     * 合成复用原则
     *
     * PS：七种原则并不是孤立存在的，他们相互依赖，相互补充。
     */
}