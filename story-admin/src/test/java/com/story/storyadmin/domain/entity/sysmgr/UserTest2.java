package com.story.storyadmin.domain.entity.sysmgr;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.story.storyadmin.mapper.sysmgr.UserMapper;
import com.story.storyadmin.service.sysmgr.UserService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import junit.framework.TestCase;
import org.apache.ibatis.annotations.MapKey;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest2 extends TestCase {

    private static final Logger logger = LoggerFactory.getLogger(UserTest2.class);

    @Autowired
    UserMapper userMapper;

    //##########################################mybatis 返回参数类型#########################################

    /**
     * mybatis 返回参数类型--map类型
     */

    @Test
    public void Test1() {
        Map<String, User> map = userMapper.getUserMap();
        for (Map.Entry<String, User> entry : map.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }

    @Test
    public void Test2() {
        Map<Long, User> map = userMapper.getUserMap2();
        for (Map.Entry<Long, User> entry : map.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }

    @Test
    public void Test21() {
     User user = userMapper.getUserMap21();
        System.out.println(user);
    }

    @Test
    public void Test211() {
        List<User> userList = userMapper.getUserMap211();
        userList.forEach(System.out::println);
    }

    @Test
    public void Test22() {
        Map<Long, User> map = userMapper.getUserMap22();
        for (Map.Entry<Long, User> entry : map.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }

    @Test
    public void Test23() {
        List<Map<Long, User>> maps = userMapper.getUserMap23();
        maps.forEach(System.out::println);
    }

    @Test
    public void Test3() {
        Map<Long,Map<Long,Object>> map = userMapper.getUserMap3();
        for (Map.Entry<Long, Map<Long, Object>> entry : map.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }

    /**
     * 利用map的特性，查询出来key相同（ID相同）的数据会覆盖掉
     */
    @Test
    public void getUserMap4() {
        Map<Long,Map<Long,Object>> map = userMapper.getUserMap4();
        //for (Map.Entry<Long, Map<Long, Object>> entry : map.entrySet()) {
        //    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        //}
        System.out.println(JSON.toJSONString(map));
    }

    /**
     * mybaits的主键插入返回
     */
    @Test
    public void insertTest(){
       long l1= userMapper.insertUser(new User("账户1","张三","1171205514@qq.com"));
        System.out.println("插入返回的主键为："+l1); //1 TODO 有问题？？
       long l2= userMapper.insertUser2(new User(333l,"账户2","李四","1171205514@qq.com"));
        System.out.println("插入返回的主键为："+l2);//1
    }

    /**
     * mybatis的Example条件对象使用
     *https://blog.csdn.net/biandous/article/details/65630783
     */

    public void testExample(){
        // 初始化example对象
        //UserExample example = new UserExample(Brand.class);
        //Example.Criteria criteria = example.createCriteria();
    }

    //##########################################mybatis 请求参数类型#########################################
    /**
     * mybatis 请求参数类型
     */

    /**
     * 方法1：顺序传参法
     * #{}里面的数字代表你传入参数的顺序。
     * 这种方法不建议使用，sql层表达不直观，且一旦顺序调整容易出错。
     */
    @Test
    public void Test4() {
        User user = userMapper.selectUserByNameAndId("lipan",8l);
        logger.info("查询出的用户信息为：{}",user.toString());
    }

    /**
     * 方法2：@Param注解传参法
     * #{}里面的名称对应的是注解 @Param括号里面修饰的名称。
     * 这种方法在参数不多的情况还是比较直观的，推荐使用
     */
    @Test
    public void Test41() {
        User user = userMapper.selectUserByNameAndId2("lipan",8l);
        logger.info("查询出的用户信息为：{}",user.toString());
    }

    /**
     * 方法3：Map传参法
     * Mybatis底层就是将入参转换成Map，入参传Map当然也行，此时#{key}中的key就对应Map中的key。
     * #{}里面的名称对应的是 Map里面的key名称。
     * 这种方法适合传递多个参数，且参数易变能灵活传递的情况。
     */
    @Test
    public void Test42() {
        Map<String,Object> userMap=new HashMap<>();
        userMap.put("name","lipan");
        userMap.put("id",8l);
        User user = userMapper.selectUserMapByNameAndId(userMap);
        logger.info("查询出的用户信息为：{}",user.toString());
    }

    /**
     * 方法4：Java Bean传参法
     * 多个参数可以使用实体类封装，此时对应的key就是属性名称，注意一定要有get方法。
     * #{}里面的名称对应的是 User类里面的成员属性。
     * 这种方法很直观，但需要建一个实体类，扩展不容易，需要加属性，看情况使用。
     */
    @Test
    public void Test43() {
        User userBean =new User();
        userBean.setName("lipan");
        userBean.setId(8l);
        User user = userMapper.selectUserBeanByNameAndId(userBean);
        logger.info("查询出的用户信息为：{}",user.toString());
    }

    /**
     * 方法5：Java Bean传参法 传递一个JsonObject对象
     *#{}里面的名称对应的是 JSONObject对像里面的value。
     * 这种方法适合传递多个参数，且参数易变能灵活传递的情况。综合了方法3和方法4的优点
     */
    @Test
    public void Test44() {
        JSONObject jsonObject =new JSONObject();
        jsonObject.put("name","lipan");
        jsonObject.put("id",8l);
        User user = userMapper.selectJsonObjectByNameAndId(jsonObject);
        logger.info("查询出的用户信息为：{}",user.toString());
    }

    //##########################################mybatis 动态sql#########################################

    /**
     * mybatis的动态sql
     * 动态 SQL 是 MyBatis 的强大特性之一。顾名思义，就是会动的SQL，即是能够灵活的根据某种条件拼接出完整的SQL语句。这种类似于MySQL中的case when then else then end....这种语法，能够根据某种条件动态的拼接出需要的SQL。
     * 常用的标签
     * if
     * choose、when、otherwise
     * where
     * foreach
     * set
     * sql
     * include
     */

    /**
     * 动态标签--if
     */
    @Test
    public void Test6() {
        User userBean =new User();
        //userBean.setName("lipan"); // name设置为null
        userBean.setEmail("pli38546@gmail.com");
        userBean.setAccount("lovepli");
        User user = userMapper.selectUserListByNameOrEmail(userBean);
        logger.info("查询出的用户信息为：{}",user.toString());
    }

    /**
     * 在 INSERT 动态插入中使用 if 标签  ,非空的才能插入
     * 我们插入数据库中的一条记录， 不是每一个字段都有值的， 而是动态变化的。在这时候使用 if 标签， 可帮我们解决这个问题。
     */
    @Test
    public void Test6_1() {
        User userBean =new User();
        userBean.setName("lipan"); // name设置为null
        userBean.setEmail("pli38546@gmail.com");
        userBean.setAccount("lovepli");
        int i =userMapper.insertUserListByNameOrEmail(userBean);
        logger.info("是否插入数据：{}",i);
    }

    /**
     * 动态标签--set
     * 在 update 动态插入中使用 if 标签 ，非空的进行更新
     * 使用 set+if 标签修改后，如果某项为 null 则不进行更新，而是保持数据库原值。
     */
    @Test
    public void Test62() {
        User userBean =new User();
        userBean.setId(8l);
        //userBean.setName("lipan"); // name设置为null
        userBean.setEmail("pli38546@gmail.com");
        userBean.setAccount("lovepli");
        int i = userMapper.updateUserByNameOrEmail(userBean);
        logger.info("是否修改数据：{}",i);
    }

    /**
     * 动态标签--choose、when、otherwise
     *
     * MyBatis 提供了 choose 元素，按顺序判断 when 中的条件出否成立，如果有一个成立，则 choose 结束。
     * 当 choose 中所有 when 的条件都不满则时，则执行 otherwise 中的 sql。
     * 类似于 Java 的 switch 语句，choose 为 switch，when 为 case，otherwise 则为default。
     */
    @Test
    public void Test61() {
        User userBean =new User();
        //userBean.setName("lipan"); // name设置为null
        //userBean.setEmail("pli38546@gmail.com");// email设置为null
        //userBean.setAccount("lovepli");// acount设置为null
        User user = userMapper.selectUserListByNameOrEmail2(userBean);
        logger.info("查询出的用户信息为：{}",user.toString());
    }

    /**
     * 动态标签--foreach
     *
     * foreach是用来对集合的遍历，这个和Java中的功能很类似。通常处理SQL中的in语句。
     * foreach 元素的功能非常强大，它允许你指定一个集合，声明可以在元素体内使用的集合项（item）和索引（index）变量。它也允许你指定开头与结尾的字符串以及集合项迭代之间的分隔符。这个元素也不会错误地添加多余的分隔符
     * 你可以将任何可迭代对象（如 List、Set 等）、Map 对象或者数组对象作为集合参数传递给 foreach。当使用可迭代对象或者数组时，index 是当前迭代的序号，item 的值是本次迭代获取到的元素。当使用 Map 对象（或者 Map.Entry 对象的集合）时，index 是键，item 是值。
     *
     *
     * foreach 标签可以对数组， Map 或实现 Iterable 接口。
     *
     * foreach 中有以下几个属性：
     * collection: 必填， 集合/数组/Map的名称.
     * item: 变量名。即从迭代的对象中取出的每一个值
     * index: 索引的属性名。当迭代的对象为 Map 时， 该值为 Map 中的 Key.
     * open: 循环开头的字符串
     * close: 循环结束的字符串
     * separator: 每次循环的分隔符
     *
     * 其他的比较好理解， collection 中的值应该怎么设定呢？
     * 跟接口方法中的参数相关。
     * 1. 只有一个数组参数或集合参数
     * 默认情况：集合collection=list， 数组是collection=array
     * 推荐：使用 @Param 来指定参数的名称， 如我们在参数前@Param("ids")， 则就填写 collection=ids
     * 2. 多参数
     * 多参数请使用 @Param 来指定， 否则SQL中会很不方便
     * 3. 参数是Map
     * 指定为 Map 中的对应的 Key 即可。其实上面的 @Param 最后也是转化为 Map 的。
     * 4. 参数是对象
     * 使用属性.属性即可。
     */

    /**
     * 数组传参
     */
    @Test
    public void Test5() {
        Long[] longs=new Long[]{1l,2l,3l,8l};
        List<User> userList = userMapper.selectUserArrayByIds(longs);
        userList.forEach(System.out::println);
    }

    /**
     * 集合传参
     */
    @Test
    public void Test51() {
        List<Long> idList =new ArrayList<>();
        idList.add(8l);
        idList.add(1l);
        List<User> userList = userMapper.selectUserList(idList);
        userList.forEach(System.out::println);
    }

    /**
     * foreach 实现批量插入
     */
    @Test
    public void Test52() {
        List<User> userList =new ArrayList<>();
        userList.add(new User("lovepli","lipan","71206666@qq.com"));
        userList.add(new User("lovepli2","lipan2","71206666@qq.com"));
        int i=userMapper.insertUserLists(userList);
    }

    /**
     * foreach 实现批量更新
     */
    @Test
    public void Test53() {
        List<User> userList =new ArrayList<>();
        userList.add(new User(1l,"lovepli","lipan","71206666@qq.com"));
        userList.add(new User(2l,"lovepli2","lipan2","71206666@qq.com"));
       int i =userMapper.updateUserLists(userList);
    }





    /**
     * 动态标签--sql
     * 在实际开发中会遇到许多相同的SQL，比如根据某个条件筛选，这个筛选很多地方都能用到，我们可以将其抽取出来成为一个公用的部分，
     * 这样修改也方便，一旦出现了错误，只需要改这一处便能处处生效了，此时就用到了<sql>这个标签了。
     * 当多种类型的查询语句的查询字段或者查询条件相同时，可以将其定义为常量，方便调用。为求 <select> 结构清晰也可将 sql 语句分解。
     *
     * 动态标签--include
     * 这个标签和<sql>是天仙配，是共生的，include用于引用sql标签定义的常量。比如引用上面sql标签定义的常量，如下：
     */
    @Test
    public void Test64() {
        User userBean = new User();
        userBean.setId(8l);
        //userBean.setName("lipan"); // name设置为null
        userBean.setEmail("pli38546@gmail.com");
        userBean.setAccount("lovepli");
        User user = userMapper.selectAll(userBean);
        logger.info("查询出的用户信息为：{}",user.toString());
    }

    /**
     * 动态标签--trim
     * set 和 where 其实都是 trim 标签的一种类型， 该两种功能都可以使用 trim 标签进行实现。
     *
     * 1-1、trim 来表示 where
     * 如以上的 where 标签， 我们也可以写成
     * <trim prefix="where" prefixOverrides="AND |OR">
     * </trim>
     * 表示当 trim 中含有内容时， 添加 where， 且第一个为 and 或 or 时， 会将其去掉。而如果没有内容， 则不添加 where。
     *
     * 1-2、 trim 来表示 set
     * 相应的， set 标签可以如下表示
     * <trim prefix="SET" suffixOverrides=",">
     * </trim>
     * 表示当 trim 中含有内容时， 添加 set， 且最后的内容为 , 时， 会将其去掉。而没有内容， 不添加 set
     *
     * 1-3、trim 的几个属性
     * prefix: 当 trim 元素包含有内容时， 增加 prefix 所指定的前缀
     * prefixOverrides: 当 trim 元素包含有内容时， 去除 prefixOverrides 指定的 前缀
     * suffix: 当 trim 元素包含有内容时， 增加 suffix 所指定的后缀
     * suffixOverrides：当 trim 元素包含有内容时， 去除 suffixOverrides 指定的后缀
     *
     */
    @Test
    public void Test65() {

    }

    /**
     * 动态标签--bind
     * bind 标签是通过 OGNL 表达式去定义一个上下文的变量， 这样方便我们使用。
     *
     */
    @Test
    public void Test66() {
    List<User> userList= userMapper.selectUserByNameLike("lipan");
    userList.forEach(System.out::println);
    }

    /**
     * 扩展：
     * Mybatis中如何避免魔数
     * 开过阿里巴巴开发手册的大概都知道代码中是不允许出现魔数的，何为魔数？简单的说就是一个数字，一个只有你知道，别人不知道这个代表什么意思的数字。
     * 通常我们在Java代码中都会定义一个常量类专门定义这些数字。
     */
    @Test
    public void Test7() {
        User userBean = new User();
        userBean.setYnFlag("0"); //删除状态
        User user = userMapper.selectUseByYnFlag(userBean);
        logger.info("查询出的用户信息为：{}",user.toString());
    }

    /**
     * 扩展：
     * Mybatis中如何引用其他XML中的SQL片段
     * 实际开发中你可能遇到一个问题，比如这个resultMap或者这个<sql>片段已经在另外一个xxxMapper.xml中已经定义过了，此时当前的xml还需要用到，难不成我复制一份？
     * 小白什么也不问上来就复制了，好吧，后期修改来了，每个地方都需要修改了
     *
     * 其实Mybatis中也是支持引用其他Mapper文件中的SQL片段的。其实很简单，比如你在com.xxx.dao.xxMapper这个Mapper的XML中定义了一个SQL片段如下：
     * <sql id="Base_Column_List">
     *     ID,MAJOR,BIRTHDAY,AGE,NAME,HOBBY
     * </sql>
     * 此时我在com.xxx.dao.PatinetMapper中的XML文件中需要引用，如下：
     *   <include refid="com.xxx.dao.xxMapper.Base_Column_List"></include>
     * 如此简单，类似于Java中的全类名。
     * <select>标签中的resultMap同样可以这么引用，和上面引用的方式一样，不再赘述了。
     */
    @Test
    public void Test8() {

    }

    //##########################################mybatis 关联查询#########################################
    // 使用<resultMap> 标签以及<association>和<collection> 子标签，进行关联查询

    @Test
    public void Test9() {

    }














}