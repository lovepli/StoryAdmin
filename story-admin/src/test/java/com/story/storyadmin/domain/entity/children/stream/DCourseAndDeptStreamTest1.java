package com.story.storyadmin.domain.entity.children.stream;

import com.alibaba.fastjson.JSON;

import com.story.storyadmin.domain.entity.children.DScore;
import com.story.storyadmin.mapper.children.*;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DCourseAndDeptStreamTest1 extends TestCase {


    private static final Logger logger = LoggerFactory.getLogger(DCourseAndDeptStreamTest1.class);


    @Autowired
    DCourseMapper dCourseMapper;

    @Autowired
    DStudentMapper dStudentMapper;

    @Autowired
    DScoreMapper dScoreMapper;


    //################1、查找学生##########################

    /**
     * 查询姓“孟”老师的个数
     */
    @Test
    public void find1() {

    }





    /**
     * @description: 1、查询学生的总成绩并进行排名,所有查询操锁逻辑在sql语句处理
     * @author: lipan
     * @date 2021/8/31 8:18 下午
     */
    @Test
    public void Test1() {
        //查询学生的总成绩并进行排名
        List<Map<String, Integer>> list = dScoreMapper.findSumScoreList();
        // 循环输出所有数据
        list.forEach((v) ->{
          System.out.println(JSON.toJSONString(v));
        });

    }

    @Test
    public void Test1_0() {
        //查询学生的总成绩并进行排名
        Map<String, Map<String,Integer>>  map = dScoreMapper.findSumScoreList2();
        // 循环输出所有数据
        System.out.println(JSON.toJSONString(map));
        // 将map转换为list集合,即获取value集合
        List<Map<String,Integer>> list = new ArrayList<>(map.values());
        // 循环输出所有数据
        list.forEach((v) ->{
            System.out.println(JSON.toJSONString(v));
        });

    }

    /*
     * @description:
     * @author: lipan
     * @date 2021/9/1 3:32 下午
     */
    @Test
    public void Test1_1() {
        //查询成绩表所有数据
        List<DScore> list = dScoreMapper.selectList(null);
        // 1、按学号分组   Map<Integer,List<User>> userMap
        Map<String,List<DScore>> maps = list.stream().collect(Collectors.groupingBy(DScore::getId));
        // 2、对分组的数据求和。3、对map集合按照分数进行排序
        Map<String,Integer> map2=new TreeMap<>();//使用TreeMap存储可以把所有存进来的数据进行排序
        maps.forEach(
                (k, v) -> {
                    map2.put(k,v.stream().collect(Collectors.summingInt(DScore::getScore)));
                });

        //输出结果
        map2.forEach((k,v) ->{
            System.out.println("学号："+k+"，总分："+String.valueOf(v));
        });
        //学号：0001，总分：269
        //学号：0002，总分：199
        //学号：0003，总分：161

    }

/*
 * @description:
 * @author: lipan
 * @date 2021/9/1 3:32 下午
 */
    @Test
    public void Test1_2() {
        //查询成绩表所有数据
        List<DScore> list = dScoreMapper.selectList(null);

        // 1、按学号分组   Map<Integer,List<User>> userMap
        Map<String,List<DScore>> maps = list.stream().collect(Collectors.groupingBy(DScore::getId));
        // 2、对分组的数据求和
        Map<String,Integer> map2=new HashMap<>();
        maps.forEach(
                (k, v) -> {
                    map2.put(k,v.stream().collect(Collectors.summingInt(DScore::getScore)));
                });

        // 3、对map集合按照分数进行排序
        // 根据map的value值来排序,使用的是Java8的写法
        // 注意： 如果这里的map的value是User对象，那么user对象需要实现Comparable接口，并重写compareTo方法，自定义比较属性的逻辑规则
        Map<String, Integer>  sortedMap2 = map2.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors
                        .toMap(Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new));

        //输出结果
        sortedMap2.forEach((k,v) ->{
            System.out.println("学号："+k+"，总分："+String.valueOf(v));
        });
        //学号：0003，总分：161
        //学号：0002，总分：199
        //学号：0001，总分：269
    }


    /**
     * @description: 2、查询平均成绩大于60分的学生的学号和平均成绩
     * @author: lipan
     * @date 2021/9/1 3:38 下午
     */
    @Test
    public  void test2(){
        // TODO 这种写法是最好的写法，省略了中间对象，缺点是别人看你代码看不到返回的是什么内容，不友好
        List<Map<String, Integer>> list = dScoreMapper.findAvgScoreList();
        // 循环输出所有数据
        // 循环输出所有数据
        list.forEach((v) ->{
            System.out.println(JSON.toJSONString(v));
        });
    }

    @Test
    public  void test2_0(){
        // TODO 方法返回的是map,最后需要传给前端的是list集合
        Map<String, Map<String,Integer>>  map = dScoreMapper.findAvgScoreList2();
        // 循环输出所有数据
        System.out.println(JSON.toJSONString(map));
        // 将map转换为list集合,即获取value集合
        List<Map<String,Integer>> list = new ArrayList<>(map.values());
        // 循环输出所有数据
        list.forEach((v) ->{
            System.out.println(JSON.toJSONString(v));
        });
    }

    @Test
    public  void test2_1(){
        //查询成绩表所有数据
        List<DScore> list = dScoreMapper.selectList(null);
        // 1、按学号分组   Map<Integer,List<User>> userMap
        Map<String,List<DScore>> maps = list.stream().collect(Collectors.groupingBy(DScore::getId));
        // 2、对分组的数据求平均值，平均值为double类型。3、对map集合按照分数进行排序
        Map<String,Double> map2=new TreeMap<>();//使用TreeMap存储可以把所有存进来的数据进行排序
        maps.forEach(
                (k, v) -> {
                    Double d =null;
                     d=v.stream().collect(Collectors.averagingInt(DScore::getScore));//求平均成绩
                    if (d>60) {
                        map2.put(k,d);
                    }
                });

        //输出结果
        map2.forEach((k,v) ->{
            System.out.println("学号："+k+"，总分："+String.valueOf(v));
        });
        //学号：0001，总分：89.66666666666667
        //学号：0002，总分：66.33333333333333
    }

    /**
     * @description: 查询各学生的年龄（精确到月份）
     * @author: lipan
     * @date 2021/9/1 5:21 下午
     */
    @Test
    public void test3(){

    }

    /**
     * @description: 查询本月过生日的学生
     * @author: lipan
     * @date 2021/9/1 5:38 下午
     */
    @Test
    public void test4(){

    }

    /**
     * @description:  检索"0001"课程分数小于60，按分数降序排列的学生信息
     * @author: lipan
     * @date 2021/9/1 5:39 下午
     */
    @Test
    public void test5(){
        List<Map<String, Object>> list= dScoreMapper.findStudentInfoOrderbyScore();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });

    }

    @Test
    public void test5_1(){
        List<Map<String, Object>> list= dScoreMapper.findStudentInfoOrderbyScore2();

        // 筛选课程id为0001
        // list转换为stream进行筛选之后，将stream再转换为新的list1集合
        List<Map<String, Object>> list1= list.stream().filter(m -> Objects.equals("0001",m.get("courseId"))).collect(Collectors.toList());
        //System.out.println(JSON.toJSONString(list1));

        //课程分数小于60
        List<Map<String, Object>> list2 =list1.stream().filter(m -> Integer.valueOf((Integer) m.get("score")).compareTo(60)<0).collect(Collectors.toList());
      //  System.out.println(JSON.toJSONString(list2));
        //按照分数降序排列
        List<Map<String, Object>> list3=list2.stream().sorted(
                new Comparator<Map<String, Object>>() {
                    @Override
                    public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                        Object obj22=  o2.get("score");
                        Integer va2=Integer.valueOf((Integer)obj22);
                        Object obj11=  o1.get("score");
                        Integer va1=Integer.valueOf((Integer)obj11);
                        int a=va2-va1;
                        return a;
                    }
                }
        ).collect(Collectors.toList());
        //System.out.println(JSON.toJSONString(list3));
        list3.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });

    }

    /**
     * @description: 查询不同老师所教不同课程平均分从高到低显示
     * @author: lipan
     * @date 2021/9/1 5:44 下午
     */
    @Test
    public void test6(){
        List<Map<String, Object>> list= dScoreMapper.findTeacherAndScoreInfoOrderbyScore();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }

    /**
     * @description: 查询课程名称为"数学"，且分数低于60的学生姓名和分数
     * @author: lipan
     * @date 2021/9/1 5:45 下午
     */
    @Test
    public void test7(){
        List<Map<String, Object>> list= dScoreMapper.findStudentAndScoreInfobyCourseName();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }

    /**
     * @description: 查询任何一门课程成绩在70分以上的姓名、课程名称和分数（与上题类似）
     * @author: lipan
     * @date 2021/9/1 5:45 下午
     */
    @Test
    public void test8(){

        List<Map<String, Object>> list= dScoreMapper.findStudentAndScoreInfobyScore();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });

    }

    /**
     * @description: 查询两门及其以上不及格课程的同学的学号，姓名及其平均成绩
     * @author: lipan
     * @date 2021/9/1 5:45 下午
     */
    @Test
    public void test9(){
        List<Map<String, Object>> list= dScoreMapper.findStudentAndAvgScoreInfo();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }


/**
 * @description: 查询不同课程成绩相同的学生的学生编号、课程编号、学生成绩 TODO 自连接复制表查询 ！！
 * @author: lipan
 * @date 2021/9/1 5:45 下午
 */
    @Test
    public void test10(){
        List<Map<String, Object>> list= dScoreMapper.findStudentAndCoAndScoreInfo();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }


    /**
     * @description: 查询课程编号为“0001”的课程比“0002”的课程成绩高的所有学生的学号
     * @author: lipan
     * @date 2021/9/1 5:46 下午
     */
    @Test
    public void test11(){
        List<Map<String, Object>> list= dScoreMapper.findStudentInfoByCourseId();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }


    /**
     * @description: 查询学过编号为“0001”的课程并且也学过编号为“0002”的课程的学生的学号、姓名
     * @author: lipan
     * @date 2021/9/1 5:46 下午
     */
    @Test
    public void test12(){
        List<Map<String, Object>> list= dScoreMapper.findStudentInfoByCourseId2();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }

    /**
     * @description: 查询学过“孟扎扎”老师所教的所有课的同学的学号、姓名
     * @author: lipan
     * @date 2021/9/1 5:47 下午
     */
    @Test
    public void test13(){
        List<Map<String, Object>> list= dScoreMapper.findStudentInfoByTeacherName();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }


    /**
     * @description: 查询没学过"孟扎扎"老师讲授的任一门课程的学生姓名 （与上题类似，"没学过"用not in来实现)  任意一门课，说明孟老师教多门课程
     * @author: lipan
     * @date 2021/9/1 5:47 下午
     */
    @Test
    public void test14(){
        List<Map<String, Object>> list= dScoreMapper.findStudentInfoNotInByTeacherName();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }

/**
 * @description: 查询没学过“孟扎扎”老师课的学生的学号、姓名（与上题类似）
 * @author: lipan
 * @date 2021/9/1 5:47 下午
 */
    @Test
    public void test15(){
        List<Map<String, Object>> list= dScoreMapper.findStudentInfoNotInByTeacherName2();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }

    /**
     * @description: 查询选修“孟扎扎”老师所授课程的学生中成绩最高的学生姓名及其成绩（与上题类似,用成绩排名，用 limit 1得出最高一个）
     * @author: lipan
     * @date 2021/9/1 5:48 下午
     */
    @Test
    public void test16(){
        List<Map<String, Object>> list= dScoreMapper.findStudentInfoInByTeacherName();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }

    /**
     * @description: 查询至少有一门课与学号为“0001”的学生所学课程相同的学生的学号和姓名
     * @author: lipan
     * @date 2021/9/1 5:48 下午
     */
    @Test
    public void test17(){
        List<Map<String, Object>> list= dScoreMapper.findStudentInfoInByCourseId();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }

/**
 * @description: 按平均成绩从高到低显示所有学生的所有课程的成绩以及平均成绩 TODO 这题求各科成绩和各科成绩平均分，分组再利用函数求
 * @author: lipan
 * @date 2021/9/1 5:48 下午
 */
    @Test
    public void test18(){
        List<Map<String, Object>> list= dScoreMapper.findAllAvgScoreAndScore();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });

    }

    /**
     * @description: 查询学生平均成绩及其名次
     * @author: lipan
     * @date 2021/9/1 5:49 下午
     */
    @Test
    public void test19(){

        List<Map<String, Object>> list= dScoreMapper.findAvgScoreAndOrder();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }

    /**
     * @description: 按各科成绩进行排序，并显示排名
     * @author: lipan
     * @date 2021/9/1 5:49 下午
     */
    @Test
    public void test20(){

        List<Map<String, Object>> list= dScoreMapper.findCourseScoreOrder();
        //拓展：各科按成绩进行排序，并显示排名
       // List<Map<String, Object>> list2= dScoreMapper.findCourseScoreOrder2();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }

    /**
     * @description: 查询每门功课成绩最好的前两名学生姓名
     * @author: lipan
     * @date 2021/9/1 5:49 下午
     */
    @Test
    public void test21(){

        List<Map<String, Object>> list= dScoreMapper.findScoreFirstAndSecend22();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });

    }

    /**
     * @description: 查询所有课程的成绩第2名到第3名的学生信息及该课程成绩（与上一题相似）
     * @author: lipan
     * @date 2021/9/1 5:51 下午
     */
    @Test
    public void test22(){
        List<Map<String, Object>> list= dScoreMapper.findCourseScoreOrder3();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });

    }

    /**
     * topN问题
     * 关联自查询
     * 使用union all
     */
    @Test
    public void test222(){
         //使用union all 将每组选出的数据合并到一起
        List<Map<String, Object>> list= dScoreMapper.findCourseScoreOrder3_2();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });

    }
    /**
     * @description: 查询各科成绩前三名的记录（不考虑成绩并列情况）（与上一题相似）
     * @author: lipan
     * @date 2021/9/1 5:52 下午
     */
    @Test
    public void test23(){

        List<Map<String, Object>> list= dScoreMapper.findCourseScoreOrder4();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }









    }