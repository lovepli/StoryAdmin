package com.story.storyadmin.utils.stringMethordUtil;

import com.story.storyadmin.utils.bank.PageUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: lipan
 * @date: 4:16 下午
 * @description:
 */
public class ArraysDemo {


    /**
     * 01、创建数组
     * 使用 Arrays 类创建数组可以通过以下三个方法：
     *
     * copyOf，复制指定的数组，截取或用 null 填充
     * copyOfRange，复制指定范围内的数组到一个新的数组
     * fill，对数组进行填充
     */
    public void copyOf(){
        String[] intro = new String[] { "沉", "默", "王", "二" };
        String[] revised = Arrays.copyOf(intro, 3);
        String[] expanded = Arrays.copyOf(intro, 5);
        System.out.println(Arrays.toString(revised));
        System.out.println(Arrays.toString(expanded));
    }

    public void copyOfRange(){
        String[] intro = new String[] { "沉", "默", "王", "二" };
        String[] abridgement = Arrays.copyOfRange(intro, 0, 3);
        String[] abridgementExpanded = Arrays.copyOfRange(intro, 0, 6);
        System.out.println(Arrays.toString(abridgement));
        System.out.println(Arrays.toString(abridgementExpanded));
        }

        public void fill(){
            String[] stutter = new String[4];
            Arrays.fill(stutter, "沉默王二");
            System.out.println(Arrays.toString(stutter));
        }

    /**
     * 02、比较数组
     */

    public void equals(){
        String[] intro = new String[] { "沉", "默", "王", "二" };
        boolean result = Arrays.equals(new String[] { "沉", "默", "王", "二" }, intro);
        System.out.println(result);
        boolean result1 = Arrays.equals(new String[] { "沉", "默", "王", "三" }, intro);
        System.out.println(result1);
    }

    public void hashcode(){
        String[] intro = new String[] { "沉", "默", "王", "二" };
        System.out.println(Arrays.hashCode(intro));
        System.out.println(Arrays.hashCode(new String[] { "沉", "默", "王", "二" }));
    }

    /**
     * 03、数组排序
     */
    public void sort(){
        String[] intro1 = new String[] { "chen", "mo", "wang", "er" };
        String[] sorted = Arrays.copyOf(intro1, 4);
        Arrays.sort(sorted);
        System.out.println(Arrays.toString(sorted));
    }

    /**
     * 04、数组检索
     */
    public void binarySearch(){
        String[] intro1 = new String[] { "chen", "mo", "wang", "er" };
        String[] sorted = Arrays.copyOf(intro1, 4);
        Arrays.sort(sorted);
        int exact = Arrays.binarySearch(sorted, "wang");
        System.out.println(exact);
        int caseInsensitive = Arrays.binarySearch(sorted, "Wang", String::compareToIgnoreCase);
        System.out.println(caseInsensitive);
    }

    /**
     * 05、数组转流
     */

    public void stream(){
        String[] intro = new String[] { "沉", "默", "王", "二" };
        System.out.println(Arrays.stream(intro).count());
        System.out.println(Arrays.stream(intro, 1, 2).count());
    }

    /**
     * 06、打印数组
     */
    public void tostring(){
        String[] intro = new String[] { "沉", "默", "王", "二" };
        System.out.println(Arrays.toString(intro));
    }

    /**
     * 07、数组转 List
     * TODO 不过需要注意的是，Arrays.asList() 返回的是 java.util.Arrays.ArrayList，并不是  java.util.ArrayList，
     * 它的长度是固定的，无法进行元素的删除或者添加。
     */
    public void arraysToList(){
        String[] intro = new String[] { "沉", "默", "王", "二" };
        List<String> rets = Arrays.asList(intro);
        System.out.println(rets.contains("二"));

        //要想操作元素的话，需要多一步转化，转成真正的 java.util.ArrayList：
        List<String> rets1 = new ArrayList<>(Arrays.asList(intro));
        rets1.add("三");
        rets1.remove("二");
    }

    /**
     *  要想操作元素的话，需要多一步转化，转成真正的 java.util.ArrayList：
     */
    public void arraysToList2(){
        String[] intro = new String[] { "沉", "默", "王", "二" };
        List<String> rets1 = new ArrayList<>(Arrays.asList(intro));
        rets1.add("三");
        rets1.remove("二");
    }

    /**
     * 08、setAll
     * Java 8 新增了 setAll() 方法，它提供了一个函数式编程的入口，可以对数组的元素进行填充：
     */

    public void setAll(){
        int[] array = new int[10];
        Arrays.setAll(array, i -> i * 10);
        System.out.println(Arrays.toString(array));
        //i 就相当于是数组的下标，值从 0 开始，到 9 结束，那么 i * 10 就意味着值从 0 * 10 开始，到 9 * 10 结束，
        //可以用来为新数组填充基于原来数组的新元素。
    }

    /**
     * 09、parallelPrefix
     * parallelPrefix() 方法和 setAll() 方法一样，也是 Java 8 之后提供的，提供了一个函数式编程的入口，
     * 通过遍历数组中的元素，将当前下标位置上的元素与它之前下标的元素进行操作，然后将操作后的结果覆盖当前下标位置上的元素。
     */
    public void parallelPrefix(){
        int[] arr = new int[] { 1, 2, 3, 4};
        Arrays.parallelPrefix(arr, (left, right) -> left + right);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 上面代码中有一个 Lambda 表达式（(left, right) -> left + right），是什么意思呢？上面这段代码等同于：
     */
    public void parallelPrefix2(){
        int[] arr = new int[]{1, 2, 3, 4};
        Arrays.parallelPrefix(arr, (left, right) -> {
            System.out.println(left + "，" + right);
            return left + right;
        });
        System.out.println(Arrays.toString(arr));
    }
}
