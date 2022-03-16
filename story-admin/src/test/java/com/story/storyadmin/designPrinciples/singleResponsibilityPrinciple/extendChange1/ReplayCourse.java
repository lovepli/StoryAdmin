package com.story.storyadmin.designPrinciples.singleResponsibilityPrinciple.extendChange1;

/**
 * @author: lipan
 * @date: 2021/8/19
 * @description: ReplayCourse重播或录像课程：
 */
public class ReplayCourse {
    public void study(String courseName){
        System.out.println("看录像，可以随便切换播放速度，以及来回播放");
    }
}
