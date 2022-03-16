package com.story.storyadmin.copybean.copyBean4;

import java.util.Arrays;

/**
 * @author: 59688
 * @date: 2021/8/11
 * @description: Object.clone
 * clone()比较特殊，对于对象而言，它是深拷贝，但是对于数组而言，它是浅拷贝。
 */
//clone拷贝数组
public class KaoBei4 {
    public static void main(String[] args) {
        // TODO 自动生成的方法存根
        //一维数组的拷贝
        int[] array1={1,2,3,5,9,8,7};
        int[] array2=new int[array1.length];
        array2=array1.clone();
        System.out.println(Arrays.toString(array2));


        //二维数组的拷贝
        int[][] array3={{1,2,3,4},{5,6,7}};
        int[][] array4=new int[2][];

        for(int i = 0;i < array3.length;i++){
            array4[i] = array3[i].clone();
        }
        for(int i=0;i<array3.length;i++){
            System.out.print(Arrays.toString(array4[i]));
        }
    }
}
