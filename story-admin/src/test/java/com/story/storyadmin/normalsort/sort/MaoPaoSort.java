package com.story.storyadmin.normalsort.sort;

//import static java.util.Arrays.swap;


import java.util.Arrays;

/**
 * @author: lipan
 * @date: 2019-06-27
 * @description:
 *
 * 冒泡排序
 * 冒泡排序算法的运作如下:
 *  比较相邻的元素。如果第一个比第二个大，就交换他们两个。
 *  对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。
 *  针对所有的元素重复以上的步骤，除了最后一个。
 *  持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
 *
 */
public class MaoPaoSort {

    public static int [] selectSort_1(int arr[]) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tem = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tem;
                }
            }
        }
        return arr;
    }



    public static void main(String[] args) {
        int [] ints={5,1,1,2,0,0};
        System.out.println(Arrays.toString(selectSort_1(ints)));

    }

}