> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [www.toutiao.com](https://www.toutiao.com/i6832996956051079687/)

面试官：你知道 Java8 有哪些新特性吗？
======================

2020-06-01 08:01· [一灯架构](/c/user/token/MS4wLjABAAAAPeHh4VHxdSVA9adkZXQymOMDUEIdFFXtSLa_PQdunHs/?source=tuwen_detail)

面试中经常被问到这个问题，虽然我们也都大致知道 java8 有哪些新特性，但是突然被问到，回答的总是不全面、没有条理性，看完这篇问题，就再也不用担心了。

1. Lambda 表达式
   =============

它允许我们把函数当成参数传递给方法, Lambda 表达式由 -> 由箭头符号和语句块组成，参数列表由逗号分隔

```
Arrays.asList("a", "b", "c").forEach(e -> System.out.println(e));
// 求和
Integer sum = Stream.of(1, 2, 3, 4, 5).reduce(0, (a, b) -> a + b);
```

```
new Thread(() -> {
    System.out.println("Hello world.")
}).start();
```

2. 函数式接口
   ========

什么样的函数能简写成 Lambda 表达式呢？

必须是函数式接口（内部只有一个抽象函数的接口），一般带有 **@FunctionalInterface** 注解

```
@FunctionalInterface
public interface Runnable {
    public abstract void run();
}
```

3. 接口的默认方法和静态方法
   ===============

```
public interface Map<K, V> {

    V get(Object key);
    V put(K key, V value);

    // 接口新增静态方法
    public static <K extends Comparable<? super K>, V> Comparator<Map.Entry<K, V>> comparingByKey() {
        return (Comparator<Map.Entry<K, V>> & Serializable)
                (c1, c2) -> c1.getKey().compareTo(c2.getKey());
    }

    // 默认方法用default修饰，不强制子类实现，可以被子类继承和重写
    default V getOrDefault(Object key, V defaultValue) {
        V v;
        return (((v = get(key)) != null) || containsKey(key))
                ? v
                : defaultValue;
    }
    
}
```

4. 方法引用
   =======

类似 `String::length` 的语法形式叫做方法引用，可以和 Lambda 表达式配合使用，使代码更简洁。

![](https://p3-tt.byteimg.com/origin/pgc-image/25b4180642d74726859f0d757a8bc3e5?from=pc)

5. 重复注解
   =======

```
@Target(ElementType.TYPE) // 使用范围在类、接口和枚举
@Retention(RetentionPolicy.RUNTIME) // 生命周期在运行时期
public @interface Authorities {
    // 用来存储Authority注解数组
    Authority[] value();
}

// 使用重复注解
@Repeatable(Authorities.class)
public @interface Authority {
    String role();
}

@Authority(role = "User")
@Authority(role = "Admin")
public class UserController {
    public static void main(String[] args) {
        // 使用Authority注解时，无需感知Authorities的存在
        Authority[] annotations = UserController.class.getAnnotationsByType(Authority.class);
        for (Authority annotation : annotations) {
            System.out.println(annotation.role());
        }
    }
}
```

6. 更好的类型推断
   ==========

```
Arrays.asList("a", "b", "c").forEach((String e) -> System.out.println(e));
//可以简写成
Arrays.asList("a", "b", "c").forEach(e -> System.out.println(e));
```

编译器能推断出 e 是 String 类型

7. 拓宽注解的应用场景
   ============

```
public enum ElementType {
    /**
     * 用在类型变量声明语句
     *
     * @since 1.8
     */
    TYPE_PARAMETER,
    /**
     * 用在任何地方
     *
     * @since 1.8
     */
    TYPE_USE
}
```

8. Optional 非空判断
   ================

引入 Optional 避免 NullPointerException 和层层判空操作，代码更简洁。

```
// 以前为了避免NPE，需要层层判空
public String getColor(User user) {
    if (user != null) {
        if (user.getCar() != null) {
            if (user.getCar().getColor() != null) {
                return user.getCar().getColor();
            }
        }
    }
    return "red";

// 引入Optional后
public String getColor(Optional<User> optUser) {
    return optUser.map(User::getCar)
            .map(Car::getColor)
            .orElse("red");
}
```

9. Streams 流
   ============

```
Streams流是对集合操作的增强，配合Lambda使代码更简洁高效
中间操作：
map、flatMap、filter、distinct、sorted、peek、limit、skip
终止操作：
forEach、forEachOrdered、toArray、reduce、collect、
min、max、count、anyMatch、allMatch、noneMatch、
findFirst、findAny、iterator
```

10. 新的日期类型
    ==========

新的日期类型在 java.time 包下，可以更优雅安全的处理日期

```
LocalDate：只包含日期，比如：2020-10-01
LocalTime：只包含时间，比如：10:01:01
LocalDateTime：包含日期和时间，比如：2020-10-01 10:01:01
Instant：时间戳
Duration：持续时间，时间差
Period：时间段
ZoneOffset：时区偏移量，比如：+8:00
ZonedDateTime：带时区的时间
Clock：时钟，比如获取目前上海的时间
```

11. 并行流
    =======

parallelStream 可以并行处理一批任务，内部使用的是 ForkJoinPool 线程池，线程池大小默认是处理器数量，可以指定  
-Djava.util.concurrent.ForkJoinPool.common.parallelism=N

```
Arrays.asList(1,2,3,4,5,6,7,8,9)
        .parallelStream()
        .forEach(System.out::println);
```

12. 优化更好的并发性
    ============

*   优化了 HashMap、ConcurrentHashMap 的底层逻辑 (增加红黑树)
*   使用 StampedLock 替代 ReadWriteLock
*   功能强大的 Future，CompletableFuture
*   java.util.concurrent.atomic 包新增

DoubleAccumulator

DoubleAdder

LongAccumulator

LongAdder

13. 命令行工具 jdeps
    ===============

jdeps 可以展示包层级和类层级的 Java 类依赖关系

```
> jdeps spring-core-5.2.4.RELEASE.jar
spring-core-5.2.4.RELEASE.jar -> 
/Library/Java/JavaVirtualMachines/jdk1.8.0_251.jdk/Contents/Home/jre/lib/rt.jar
   org.springframework.asm (spring-core-5.2.4.RELEASE.jar)
      -> java.io                                            
      -> java.lang                                          
      -> java.lang.reflect                                  
      -> java.util                                          
      -> java.util.regex                                    
   org.springframework.cglib (spring-core-5.2.4.RELEASE.jar)
   ...
```

14. JVM 新特性
    ===========

JVM 移除了 **PermGen** 空间，取而代之的是 **Metaspace**，

相应的 JVM 参数 **-XX:PermSize** 和 **-XX:MaxPermSize** 已被替换为 **-XX:MetaSpaceSize** 和 **-XX:MaxMetaspaceSize**