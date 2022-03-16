//package com.story.storyadmin.domain.entity.sysmgr;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.story.storyadmin.mapper.sysmgr.UserMapper;
//import com.story.storyadmin.service.sysmgr.UserService;
//import junit.framework.TestCase;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//import java.util.Map;
//import java.util.function.Consumer;
//import java.util.stream.Collectors;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class UserTest1 extends TestCase {
//
//    private static final Logger logger = LoggerFactory.getLogger(UserTest1.class);
//
//    @Autowired
//    UserService userService;
//
//    @Autowired
//    UserMapper userMapper;
//
//
//    @Test
//    public void Test1() {
//        // 查询出所有数据
//        List<User> list = userMapper.selectList(null);
//       // assertEquals(5, list.size());
//       // list.forEach(System.out::println);
//
//        // 将查询的结果集按照状态status分组,状态为0的在一个List<User>集合，状态为1的在一个List<User>集合
//        Map<String,List<User>> userMap =list.stream().
//                collect(Collectors.groupingBy(User::getStatus));
//        for (Map.Entry<String, List<User>> entry : userMap.entrySet()) {
//            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//        }
//        // 使用lambda表达式遍历
//        userMap.entrySet().forEach(
//                new Consumer<Map.Entry<String, List<User>>>() {
//                    @Override
//                    public void accept(Map.Entry<String, List<User>> stringListEntry) {
//                        System.out.println("Key = " + stringListEntry.getKey() + " Value =  " + stringListEntry.getValue());
//                    }
//                });
//        //System.out.println(JSON.toJSONString(userMap));
//    }
//
//    @Test
//    public void Test2() {
//        // 模糊查询并返回特定的几个字段
//        QueryWrapper<User> wrapper =new QueryWrapper<>();
//        wrapper.select("id","name","email").likeRight("name","li");
//        List<Map<String,Object>> maps =userMapper.selectMaps(wrapper);
//        maps.forEach(System.out::println);
//    }
//
//
//
//
//}