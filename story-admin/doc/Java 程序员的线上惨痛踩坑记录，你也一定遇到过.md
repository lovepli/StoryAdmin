> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [www.toutiao.com](https://www.toutiao.com/i6950609310854464014/)

Java 程序员的线上惨痛踩坑记录，你也一定遇到过
=========================

原创 2021-04-14 09:00· [一灯架构](/c/user/token/MS4wLjABAAAAPeHh4VHxdSVA9adkZXQymOMDUEIdFFXtSLa_PQdunHs/?source=tuwen_detail)

线上问题年年有，今年特别多。记几次线上惨痛的踩坑记录，希望大家以史为鉴。

* * *

1. 包装类型自动解箱导致空指针异常
   ==================

```
public int getId() {
    Integer id = null;
    return id;
}
```

如果调用上面的方法会发生什么？id 是 Integer 类型，而方法的返回值 int 类型，会自动拆箱转换，由于 id 是 null，转换成 int 类型的时候，就会报 NullPointerException 异常。

无论是《阿里 Java 开发手册》、《代码整洁之道》还是《Effective Java》都建议方法返回值类型尽量写成包装类型，类似 Integer。还有实体类、接收前端传参类、给前端的响应类中的属性都要写成包装类型，避免拆箱出错。

2. 包装类型用 == 判断相等，导致判断不正确
   ========================

先看一段代码运行结果：

```
public class IntegerTest {
    public static void main(String[] args) {
        Integer a = 100;
        Integer b = 100;
        Integer c = 200;
        Integer d = 200;
        System.out.println(a == b); // 输出 true
        System.out.println(c == d); // 输出 false
    }
}
```

很多人会很疑惑，为什么输出的两个结果会不一样？

当给 Integer 类型赋值时，会调用 Integer.valueOf() 方法

```
static final int low = -128;
static final int high = 127;

public static Integer valueOf(int i) {
    if (i >= IntegerCache.low && i <= IntegerCache.high)
        return IntegerCache.cache[i + (-IntegerCache.low)];
    return new Integer(i);
}
```

当 value 值在 - 128 到 127 之间时，会复用缓存。当不在这个区间时，才会创建对象。

而 == 比较的是内存地址，不同的对象的内存地址不相同，所以就出现上述的结果。

Integer 重写了 equals() 方法：

```
public boolean equals(Object obj) {
    if (obj instanceof Integer) {
        return value == ((Integer)obj).intValue();
    }
    return false;
}
```

当使用 equals() 方法时，比较的是 int 值是否相等。

所以，包装类判断是否相等的时候，绝不能用 == 判断，一定要用 equals() 方法判断。

3. Switch 传参是 null 导致空指针异常
   ==========================

猜一下下面代码的运行结果：

```
public class Test {
    public static void main(String[] args) {
        String name = null;
        switch (name) {
            case "yideng":
                System.out.println("一灯");
                break;
            default:
                System.out.println("default");
        }
    }
}
```

你是不是认为会输出 **default**，其实代码会抛出 NullPointerException 异常。

当 switch 比较两个对象是否相等的时候，会调用 name.hashCode() 方法和 name.equals() 方法，因为 name 是 null，结果就抛出了 NullPointerException 异常。

所以调用 switch 方法前，一定要对传参进行判空。

4. 创建 BigDecimal 类型时精度丢失
   ========================

猜一下下面代码的运行结果：

```
public class Test {
    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal(0.1);
        System.out.println(bigDecimal);
    }
}
```

你以为会输出 0.1，其实输出结果是：

```
0.1000000000000000055511151231257827021181583404541015625
```

What？这么一大串是什么东西？

为什么会出现这种情况呢？原因是，当我们用 new BigDecimal(0.1) 创建对象是，会调用 BigDecimal 的这个构造方法：

```
public BigDecimal(double val) {
    this(val,MathContext.UNLIMITED);
}
```

把传参 0.1 当成了 double 类型，double 计算的时候会把数值转换成二进制，而 0.1 转换成二进制是无法除尽的，所以就带了一大串小数位。

当需要创建 BigDecimal 类型时，应该怎么做呢？

可以先把数值转换成字符串类型，再创建 BigDecimal 对象，类似这样：

```
BigDecimal bigDecimal = new BigDecimal(String.valueOf(0.1));
```

又来一个问题，BigDecimal 是怎么解决精度丢失问题？

答案是 BigDecimal 会先把数值乘以 10 的整数倍，去除小数位，转换成 long 类型，然后进行运算，最后把运算结果除以 10 的整数倍。

5. List 转成 Map 时主键重复，导致异常
   =========================

下面代码的分组能成功吗？

```
public class SteamTest {
  
    static class User {
        // 用户ID
        private Integer id;
        // 用户名
        private String name;
    }

    public static void main(String[] args) {
        List<User> users = Arrays.asList(
                new User(1, "Tom"),
                new User(1, "Tony"),
                new User(2, "Jerry")
        );
         // 用户集合按id进行分组
        Map<Integer, User> userMap = users.stream()
                .collect(Collectors.toMap(User::getId, user -> user));
      System.out.println(userMap);
    }
}
```

结果报异常了，Exception in thread "main"  
java.lang.IllegalStateException: Duplicate key SteamTest.User(id=1, name=Tom)

原因是主键冲突，有两个 id=1 的数据，按 id 进行分组时程序就不知道怎么处理了。

可以这样做

```
public class SteamTest {
  
    static class User {
        // 用户ID
        private Integer id;
        // 用户名
        private String name;
    }

    public static void main(String[] args) {
        List<User> users = Arrays.asList(
                new User(1, "Tom"),
                new User(1, "Tony"),
                new User(2, "Jerry")
        );
         // 用户集合按id进行分组，主键冲突的时候，取第一个user
        Map<Integer, User> userMap = users.stream()
                .collect(Collectors.toMap(User::getId, user -> user, (user1, user2) -> user1));
      System.out.println(userMap); // 输出 {1:{"id":1,"name":"Tom"},2:{"id":2,"name":"Jerry"}}
    }
}
```

6. 真假 ArrayList 导致添加异常
   ======================

下面的 add() 方法能添加成功吗？

```
public class Test {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2);
        list.add(3);
    }
}
```

结果是抛异常了，Exception in thread "main"  
java.lang.UnsupportedOperationException

抛出了不支持这个方法的异常，为什么呢？我们看一下 Arrays.asList() 方法的源码：

```
public static <T> List<T> asList(T... a) {
    return new ArrayList<>(a);
}
```

返回了一个 ArrayList，为什么还不能添加成功了？

真相是此 ArrayList 非彼 ArrayList，跟我们常用的 ArrayList 只是重名，这个 ArrayList 只是 Arrays 对象一个内部类，内部并没有实现 add() 方法，所以添加的时候会报错。

这不是明摆着坑人吗？实现了 list 接口，为啥不实现 add() 方法？

其实作者是故意这样设计的，除了没有实现 add() 方法，还没有实现 addAll()、remove()、clear() 等修改方法，目的就是创建后再不让用户修改，这样的集合有什么用呢？

其实在某些不可变场景还是很实用的，比如创建已结束的订单状态集合：

```
List<String> list = Arrays.asList("Failure", "Cancelled","Completed");
```

这种集合一般不会变的，使用过程中也不允许修改，避免出错。

7. 总结
   =====

每一次踩坑，背后都有至少一次的线上问题记录，这些总结都是用教训换来的，不只是自己，其他人肯定也遇到过。我们如何才能避免在以后的开发中再出现类似的问题呢？

*   站在使用者的角度，编写详细的单元测试，打印必要日志，追踪代码执行结果
*   站在创造者的角度，探究框架的架构设计和源码实现，理解作者的意图

* * *

**你在线上还踩过哪些坑？**