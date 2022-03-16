package com.story.storyadmin.copybean.copyBean4;

/**
 * @author: 59688
 * @date: 2021/8/11
 * @description: 对象拷贝
 * 首先讲一下对象的拷贝，它是深拷贝，大家可以用对象去测试一下。下面我们看一下它的源代码：
 * protected native Object clone() throws CloneNotSupportedException;
 * 这里也有native关键字，所以也是底层的c语言实现的。
 *
 * 还要注意的是，这里修饰符是protected，也就是说，我们创建了一个Object类以后，是不能直接调用这个clone（）方法的，因为protected关键字只允许同一个包内的类和它的子类调用，
 * 所以我们声明一个object类时，肯定不是同一个包内，所以就不能去调用它。要调用这个方法，就需要我们写一个类，然后声明实现cloneable接口就好了，不需要去显示地声明继承于object，
 * 因为java中的类如果不显示说明父类的话，默认父类就是object。然后我们继承这个方法：
 *  public Object clone() throws CloneNotSupportedException {
 *         // TODO Auto-generated method stub
 *         return super.clone();
 *     }
 * 这里需要是，为了能够在不同包内去调用这个方法，我们需要把这个权限升级为public。现在我们就可以调用这个类的clone()方法去拷贝我们的类了。
 *
 *
 * 数组拷贝
 * 对于数组而言，它不是简单的将引用赋值为另外一个数组引用，而是创建一个新的数组。
 * 但是我们知道，对于数组本身而言，它它的元素是对象的时候，本来数组每个元素中保存的就是对象的引用，
 * 所以，拷贝过来的数组自然而言也是对象的引用，所以对于数组对象元素而言，它又是浅拷贝。我们用以下代码验证一下
 */
class Aby implements Cloneable{
    public int i;
    public Aby(int i) {
        this.i = i;
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return super.clone();
    }
}

public class Solution {

    public static void main(String[] args) throws CloneNotSupportedException {
        Aby aby1 = new Aby(1);
        Aby aby2 = (Aby) aby1.clone();
        aby1.i = 2;
        System.out.println(aby1.i); //2
        System.out.println(aby2.i); //1

        Aby[] arr = {aby1,aby2};

        Aby[] arr2 = arr.clone();
        arr2[0].i = 3;
        System.out.println(arr[0].i);   //3
        System.out.println(arr2[0].i);  //3
    }
}