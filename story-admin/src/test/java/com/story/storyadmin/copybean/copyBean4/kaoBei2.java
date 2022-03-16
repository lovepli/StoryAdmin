package com.story.storyadmin.copybean.copyBean4;

import java.util.Arrays;

/**
 * @author: 59688
 * @date: 2021/8/11
 * @description: System.arraycopy（浅拷贝）
 * 这个是系统提供的拷贝方式，它是浅拷贝，也就是说对于非基本类型而言，它拷贝的是对象的引用，而不是去新建一个新的对象。通过它的代码我们可以看到，这个方法不是用java语言写的，而是底层用c或者c++实现的，因而速度会比较快。
 *
 * public static native void arraycopy(Object src, int srcPos,Object dest, int destPos,int length);
 * 通过源代码我们可以看到，关键字native说明它不是用java语言写的，而是调用其他语言的代码
 */
//System.arraycopy   相对较快的拷贝方式  native 方法
public class kaoBei2 {

    public static void main(String[] args) {
        // TODO 自动生成的方法存根
        //一维数组的拷贝
        int[] array = {1,2,3,4,5};
        int[] array2 = new int[array.length];
        System.arraycopy(array, 0, array2, 0, array.length);//(被复制的数组，从几号下标开始复制，复制到哪个数组，复制到新数组第几号下标，复制长度)
        System.out.println(Arrays.toString(array2));
        //二维数组的拷贝
        int[][] array1={{1,2,3,5,9},{2,3,36,5,7}};
        int[][] array3=new int[2][5];
        for(int i=0;i<array1.length;i++){
            System.arraycopy(array1[i], 0, array3[i], 0, array1[i].length);
        }
        System.out.println(Arrays.deepToString(array3));
    }
}
