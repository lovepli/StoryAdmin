> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [www.toutiao.com](https://www.toutiao.com/i6944148979739410955/)

你不知道的 HashMap 高阶用法，开发效率提升一倍
===========================

原创 2021-03-30 09:00· [一灯架构](/c/user/token/MS4wLjABAAAAPeHh4VHxdSVA9adkZXQymOMDUEIdFFXtSLa_PQdunHs/?source=tuwen_detail)

HashMap 在工作中使用非常频繁，其实在 JDK1.8 的时候新增一些更高阶的用法，熟练使用这些方法可以大大提升开发效率，写出更简洁优美的代码。

* * *

1. get 方法指定返回默认值（getOrDefault）
   ==============================

```
Map<String, String> map = new HashMap<>();
map.put("key1", "value1");
String value1 = map.getOrDefault("key1", "defaultValue");
System.out.println(value1); // 输出 value1
// 当不存在key时，返回指定默认值
String value2 = map.getOrDefault("key2", "defaultValue");
System.out.println(value2); // 输出 defaultValue
```

2. 当 key 不存在时才执行 put 方法（putIfAbsent）
   ====================================

```
Map<String, String> map = new HashMap<>();
map.put("key1", "value1");
map.putIfAbsent("key1", "newValue");
String value = map.get("key1");
System.out.println(value); // 输出 value1
```

3. 当 key 存在时才执行 put 方法（replace）
   ===============================

```
Map<String, String> map = new HashMap<>();
map.put("key1", "value1");
map.replace("key1", "newValue");
map.replace("key2", "value2");
System.out.println(JSON.toJSONString(map)); //输出 {"key1":"newValue"}
```

4. 当 value 不存在时重新计算（computeIfAbsent）
   ====================================

有这样一个常见的应用场景，当一批用户按年龄分组之后，新用户怎么加入到分组中？

通常我们这样做的：

```
public class MapTest {
  
    static class User {
        // 年龄
        private Integer age;
        // 姓名
        private String name;
    }

    public static void main(String[] args) {
        // key是年龄，value是用户集合
        Map<Integer, List<User>> map = new HashMap<>();
        // 来一个新用户
        User user = new User(18, "yideng");
        List<User> users = map.get(user.getAge());
        // 如果没有这个年龄段的用户，就需要创建一个集合
        if (users == null) {
            users = new ArrayList<>();
        }
        users.add(user);
        map.put(user.getAge(), users);
    }

}
```

还是老套解决办法，一点都不优雅吧？JDK1.8 后你可以这样做了

```
public static void main(String[] args) {
    // key是年龄，value是用户集合
    Map<Integer, List<User>> map = new HashMap<>();
    // 来一个新用户
    User user = new User(18, "Yideng");
    // 如果没有这个年龄段的用户，直接创建一个集合
    List<User> users = map.computeIfAbsent(user.getAge(), k -> new ArrayList<>());
    users.add(user);
```

5. 当 value 存在时重新计算（computeIfPresent）
   ====================================

```
public class MapTest {

    static class User {
        // 年龄
        private Integer age;
        // 姓名
        private String name;
    }

    public static void main(String[] args) {
        // key是年龄，value是用户
        Map<Integer, User> map = new HashMap<>();
        map.put(18, new User(18,"Yideng"));
        // 当存在key时，就重新计算value并赋值
        map.computeIfPresent(18, (k, v) -> {
            v.setName("一灯");
            return v;
        });
        System.out.println(map); // 输出 {18:{"age":18,"name":"一灯"}}
    }

}
```

这个方法还有很多其他的应用场景，你认真想一下？

6. 合并新旧两个值（merge）
   =================

又有这样一个常见的应用场景，当一批用户按年龄分组之后，一批新用户怎么加入到分组中？

我们可以这样做的：

```
public class MapTest {

    static class User {
        // 年龄
        private Integer age;
        // 姓名
        private String name;
    }

    public static void main(String[] args) {
        // key是年龄，value是用户集合
        Map<Integer, List<User>> map = new HashMap<>();
        List<User> users1 = new ArrayList<>();
        users1.add(new User(18, "yideng"));
        map.put(18, users1);
        
        List<User> users2 = new ArrayList<>();
        users2.add(new User(18, "一灯"));
      
        // 如果key存在，就合并两个集合
        map.merge(18, users2, (oldV, v) -> {
            oldV.addAll(v);
            return oldV;
        });
        System.out.println(map); 
		// 输出 {18:[{"age":18,"name":"yideng"},{"age":18,"name":"一灯"}]}
    }

}
```

你还知道 HashMap 的哪些高效率用法？