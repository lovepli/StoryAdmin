package com.story.storyadmin.basic.dataStruct.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author: lipan
 * @date: 2019-06-12
 * @description:
 *
 * Collections
 * Collections是JDK提供的集合工具类 类似的，Arrays是JDK提供的数组工具类
 * 创建空集合:emptyList / emptySet / emptyMap
 * 创建单元素集合:singleton / singletonList / singletonMap
 * 对List排序:sort
 * 洗牌:suffle
 * 创建不可变集合:unmodifiableList / unmodifiableSet / unmodifiableMap
 * 创建线程安全的集合:synchronizedList / synchronizedSet / synchronizedMap(已不推荐使用)
 */
public class CollectiosnsDemo {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>(Arrays.asList("A", "B", "c"));
        //创建不可变集合
        List<String> readOnlyList = Collections.unmodifiableList(list);
        System.out.println(readOnlyList);
        readOnlyList.add("x");  //不能继续添加
        //[A, B, c]
        //Exception in thread "main" java.lang.UnsupportedOperationException

        //创建线程安全的集合
        List<String> safeList = Collections.synchronizedList(list);


    }
}
