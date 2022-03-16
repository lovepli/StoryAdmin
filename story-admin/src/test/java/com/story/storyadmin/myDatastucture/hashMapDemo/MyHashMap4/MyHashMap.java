package com.story.storyadmin.myDatastucture.hashMapDemo.MyHashMap4;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author: 59688
 * @date: 2021/9/24
 * @description: 《数据结构与算法分析》--自己手写HashMap https://mp.weixin.qq.com/s/1MncavAbI4suP9avf_UZyg
 */
public class MyHashMap<K,V> implements MyMap<K,V>{
    private static final int DEFAULT_SIZE=1<<4; //16
    private static final  float DEFAULT_LOAD_SIZE = 0.75f; //负载因子
    private int defaultInitSize; //table的默认初始长度
    private  float defaultLoadSize; //默认负载因子
    private  int entrySize; //默认的entry数组的数量

    // map的存储单元 Entry单链表数组
    private Entry<K,V>[] table = null;

    /**
     * 构造函数
     * 构造方法有什么好说的呢？
     * 仔细观察下，你会发现，其实这里使用到了“门面模式”。这里的2个构造方法其实指向的是同一个，但是对外却暴露了2个“门面”！
     */
    public MyHashMap(){
        this(DEFAULT_SIZE,DEFAULT_LOAD_SIZE);
    }

    /**
     * 构造器中，先给数组默认16个长度，有一个负载因子需要解释下，如果存储的元素过多，会使单链表的长度过长，而影响HashMap的查询性能。
     * 所以当元素达到一定比例的时候，对数组进行扩容。这个负载因子就是比例。即默认16*0.75=12，当元素个数达到此值时触发扩容。
     * jdk的构造函数中提供对此值与初始容量大小进行修改。在此申明一下。
     * @param defaultInitSize
     * @param defaultLoadSize
     */
    public MyHashMap(int defaultInitSize,float defaultLoadSize){
        this.defaultInitSize = defaultInitSize;
        this.defaultLoadSize = defaultLoadSize;
        table = new Entry[this.defaultInitSize];
    }

    /**
     * get方法相对简单，对key进行hash运算后定位到数组下标，如果仅有一个元素，则返回，如果是链表，则遍历链表直到equals相等。
     * 因此，业界有句话：HashCode相等，equals不一定相等，反之则成立！从此处可以得到印证。
     * @param key
     * @return
     */
    @Override
    public V get(K key) {
       int index= hash(key) &(defaultInitSize -1);
       if (table[index] ==null){
           return null;
       }else {
           Entry<K,V> e =table[index];
           do{
               if (e.getKey().equals(key)){
                   return e.getValue();
               }
               e=e.next;
           }while (e!=null);
       }
       return null;
    }

    /**
     * 对于任意给定的键值对，对key进行hash计算，以保证散列存储，hashMap允许的value为空，允许一个key为null。
     * 我们对null的key单独处理，   int index= hash(key) &(defaultInitSize -1); 为寻址hash桶的位置，即确定数组的下标。
     *
     * 关于put部分方法的实现如下所示：从函数的实现可以看出resize是一个比较消耗性能的部分。
     * @param key
     * @param value
     * @return
     */
    @Override
    public V put(K key, V value) {
        //数组元素个数达到16*0.75=12此值时触发扩容
        if (entrySize>=defaultInitSize*defaultLoadSize){
            // 扩容操作
            resize(defaultInitSize*2);
        }
        // 寻址
        //任何一个key，都需要找到hash后对应的哈希桶位置(寻址hash桶的位置，即确定数组的下标)
        //求余数的算法的结果是一样的。位运算效率高（装逼一点）
        int index= hash(key) &(defaultInitSize -1);
        //hashMap允许键为空，对这个空键单独处理
        if (table[index] == null){
            table[index] = new Entry<K,V>(key,value,null);
            entrySize++;
        }else {
            Entry<K,V> tempEntry =table[index];
            Entry<K,V> e=tempEntry;
            while (e!=null){
                if (key == e.getKey() || e.getKey().equals(key)){
                    e.value = value;
                  //  e.setValue(value); // 也可以不定义这个方法赋值，直接e.value = value
                    break;
                }
                e=e.next;
            }
            table[index] =new Entry<K,V>(key,value,tempEntry);
            entrySize++;
        }
        return null;
    }

    /**
     * 在上述的截图里面，读者会看到一个叫做hash的函数，这是我自己封装的一个关于hash值计算的函数，可以通过输入的key自动算出相应的hash值。以及一个rehash函数和resize函数都是用于提供扩容数组的功能。
     *
     * 关于hash函数里面的>>>和^操作符，可能大多数人在平时编程里面接触的不多，在这里我简单提及一下：
     *
     * <<          左移运算符，num << 1,相当于num乘以2
     *
     * >>          右移运算符，num >> 1,相当于num除以2
     *
     * >>>         无符号右移，忽略符号位，空位都以0补齐
     *
     *  如:
     *
     * a=00110111,则a>>2=00001101,a>>>2=00001101,
     *
     * b=11010011,则b>>2=11110100,b>>>2=00110100
     * @param key
     * @return
     */
    //自定义hash算法 散列： HashCode做一次hash运算。以此更加保证散列性
    private int hash(Object key){
        int hashCode = Objects.hashCode(key);
        hashCode ^=(hashCode>>>20) ^(hashCode>>>12);
        return hashCode^(hashCode>>>7)^(hashCode>>>4);
    }
    //重新扩容，核心在rehash里面
    private void resize(int i){
       Entry[] newTable=new Entry[i];
       this.defaultInitSize =i;
       this.entrySize = 0;
        System.out.println("newTable size:--------->"+newTable.length);
        //对扩容对数组进行重新hash
        rehash(newTable);
    }

    //重新hash
    private void rehash(Entry<K,V>[] newTable){
        List<Entry<K,V>> entryList =new ArrayList<>();
        for (Entry<K,V> e:table){
            while (e!=null){
                entryList.add(e);
                e=e.next;
            }
        }
        if (newTable.length>0){
            table = newTable;
        }
        for (Entry<K,V> entry:entryList){
            put(entry.getKey(),entry.getValue());
        }
    }

    /**
     * 寻址 https://mp.weixin.qq.com/s/FYOF8OUWQLjB58xer45k3A
     * hashMap初始容量是16，为什么不是10呢？从这您可以找到答案。
     * hashMap采用了类似求余的算法，对hash值与数组的长度进行取余。余数作为数组下标位置。但是，hashMap没有用求余，而是采用了效率更高的位“与”运算。
     * 我们假设经过hash函数计算出的hash值是555，那么我们将它放到数组的哪个位置呢？
     */

}
