package com.story.storyadmin.copybean.copyBean4;

import java.util.Arrays;

/**
 * @author: 59688
 * @date: 2021/8/11
 * @description: Arrays.copyOf（浅拷贝）
 * 这个方法也是浅拷贝，我们看一下它的源代码就知道了。
 *
 * public static byte[] copyOfRange(byte[] original, int from, int to) {
 *    int newLength = to - from;
 *    if (newLength < 0)
 *        throw new IllegalArgumentException(from + " > " + to);
 *        byte[] copy = new byte[newLength];
 *        System.arraycopy(original, from, copy, 0,
 *               Math.min(original.length - from, newLength));
 *         return copy;
 *     }
 * 实际上它调用的就是System.arraycopy，所以肯定也是浅拷贝。
 */
//Array.copyOf拷贝数组
public class KaoBei3 {
    public static void main(String[] args) {
        // TODO 自动生成的方法存根
        //一维数组的拷贝
        int[] array=new int[4];
        System.out.println(Arrays.toString(array));
        int[] array1 = new int[4];
        array1 = Arrays.copyOf(array,array.length);//将数组array拷贝到数组brr,拷贝长度为array.length
        System.out.println(Arrays.toString(array1));
        //一维数组的拷贝
        int[][] array2={{1,2,3,4},{2,6,7,5}};
        int[][] array3=new int[2][4];
        array3=Arrays.copyOf(array2, array2.length);
        System.out.println(Arrays.deepToString(array3));
    }

}
