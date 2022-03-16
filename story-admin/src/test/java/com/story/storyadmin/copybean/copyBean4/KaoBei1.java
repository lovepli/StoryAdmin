package com.story.storyadmin.copybean.copyBean4;
import java.util.Arrays;


/**
 * @author: 59688
 * @date: 2021/8/11
 * @description: Java中的拷贝方式，你知道几种？ https://mp.weixin.qq.com/s/Z-L24YpWVN_hGjVgvUxijw
 *
 * java数组拷贝主要有四种方法，分别是
 * 1、循环赋值，
 * 2、System.arraycopy(),
 * 3、Arrays.copyOf()(或者Arrays.copyOfRange),
 * 4、clone()方法。
 *
 * 1、循环拷贝
 * 循环拷贝其实没什么好说的啦，就是用一个for循环进行元素的逐个拷贝，是浅拷贝，拷贝速度比较慢；
 */
//for循环拷贝数组
public class KaoBei1 {//一维数组的拷贝
    public static void main(String[] args) {
        // TODO 自动生成的方法存根
        int[] array1={1,2,3,4,5,6,7};
        int[] array2=new int[array1.length];
        for(int i = 0;i < array1.length;i++){
            array2[i] = array1[i];
        }
        System.out.println(Arrays.toString(array2));
        //二维数组的拷贝
        int[][] array3= {{1,2,3},{4,5,6}};
        int[][] array4 = new int[2][3];

        for(int i = 0;i < array3.length;i++){
            for(int j = 0;j < array3[i].length;j++){
                array4[i][j] = array3[i][j];
            }
        }
        System.out.println(Arrays.deepToString(array4));
    }
}