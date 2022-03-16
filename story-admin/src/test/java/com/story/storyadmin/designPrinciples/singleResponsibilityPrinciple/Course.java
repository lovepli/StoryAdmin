package com.story.storyadmin.designPrinciples.singleResponsibilityPrinciple;

/**
 * @author: lipan
 * @date: 2021/8/18
 * @description:
 *  我们来看代码实例，还是用课程举例，我们的课程有直播课和录播课。直播课不能快进和快退，录播课可以任意地反复观看，功能职责不一样。
 *  首先创建一个Course类。
 */
public class Course {

    public void study(String courseName){
        if("直播课".equals(courseName)){
            System.out.println("不能快进哦");
        }else{
            System.out.println("可以自定义播放速度，已经来回播放");
        }
    }

}
