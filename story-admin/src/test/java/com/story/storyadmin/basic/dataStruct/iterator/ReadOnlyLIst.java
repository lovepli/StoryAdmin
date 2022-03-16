package com.story.storyadmin.basic.dataStruct.iterator;

import java.util.Iterator;


/**
 * @author: lipan
 * @date: 2019-06-12
 * @description:
 */
public class ReadOnlyLIst<E> implements Iterable<E> {  //自定义实现Iterable<E> 接口的类，可以在程序中直接使用for循环遍历对象

    E[] array;  //泛型数组

    @SafeVarargs
    public ReadOnlyLIst(E... array) {
        this.array = array;
    }

    @Override
    public Iterator<E> iterator() {
        return new ReadOnlyIterator();
    }

    class ReadOnlyIterator implements Iterator<E>{  //内部类ReadOnlyIterator实现Iterator<E>

        int index = 0;

        @Override
        public boolean hasNext() {
            return index < ReadOnlyLIst.this.array.length;  //ReadOnlyLIst.this
        }

        @Override
        public E next() {
            E e = array[index];
            index++;
            return e;
        }
    }
}
