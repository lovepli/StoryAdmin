package com.story.storyadmin.myDatastucture.hashMapDemo.HashMap1;

/**
 * @author: 59688
 * @date: 2021/9/24
 * @description: 手写一个简单的HashMap，搞定挑剔面试官 https://mp.weixin.qq.com/s/pAfqvxhx7R_Tm3Y3VxbKeg
 */
public class MyHashMap<K,V> implements MyMap<K,V>{
//我们参照HashMap设置一个默认的容量capacity和默认的加载因子loadFactor，table就是底层数组，Entry类保存了"K-V"数据，
// next字段表明它可能会是一个链表节点。

    final static int DEFAULT_CAPACITY = 16;
    final static float DEFAULT_LOAD_FACTOR = 0.75f;

    int capacity;
    float loadFactor;
    int size = 0;

    Entry<K,V>[] table;
    class Entry<K, V>{
        K k;
        V v;
        Entry<K,V> next;

        public Entry(K k, V v, Entry<K, V> next){
            this.k = k;
            this.v = v;
            this.next = next;
        }
    }

    /**
     * 构造函数
     */
    public MyHashMap(){
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int capacity, float loadFactor){
        this.capacity = upperMinPowerOf2(capacity);
        this.loadFactor = loadFactor;
        this.table = new Entry[capacity];
    }

    /**
     * 获取大于capacity的最小的2次幂。在HashMap中，开发者采用了更精妙的位运算的方式完成了这个功能，效率比这种方式要更高。
     * 为什么HashMap的capacity一定要是2次幂呢？这是为了方便HashMap中的数组扩容时已存在元素的重新哈希（rehash）考虑的。
     * @param n
     * @return
     */
    private static int upperMinPowerOf2(int n){
        int power = 1;
        while(power <= n){
            power *= 2;
        }
        return power;
    }

    /**
     * put方法中，我们通过传入的K-V值构建一个Entry对象，然后判断它应该被放在数组的那个位置。回想我们之前的论断：
     *
     * 想要提高HashMap的效率，最重要的就是尽量避免生成链表，或者说尽量减少链表的长度
     *
     * 想要达到这一点，我们需要Entry对象尽可能均匀地散布在数组table中，且index不能超过table的长度，很明显，取模运算很符合我们的需求int index = k.hashCode() % table.length。关于这一点，HashMap中也使用了一种效率更高的方法——通过&运算完成key的散列，有兴趣的同学可以查看HashMap的源码。
     *
     * 如果table[index]处已存在元素，说明将要形成链表。我们首先遍历这个链表（长度为1也视作链表），如果存在key与我们存入的key相等，则替换并返回旧值；如果不存在，则将新节点插入链表。插入链表又有两种做法：头插法和尾插法。如果使用尾插法，我们需要遍历这个链表，将新节点插入末尾；如果使用头插法，我们只需要将table[index]的引用指向新节点，然后将新节点的next引用指向原来table[index]位置的节点即可，这也是HashMap中的做法。
     *
     * 如果table[index]处为空，将新的Entry对象直接插入即可。
     * @param k
     * @param v
     * @return
     */
    @Override
    public V put(K k, V v) {
        // 通过hashcode散列
        int index = k.hashCode() % table.length;
        Entry<K, V> current = table[index];
        // 判断table[index]是否已存在元素
        // 是
        if(current != null){
            // 遍历链表是否有相等key, 有则替换且返回旧值
            while(current != null){
                if(current.k == k){
                    V oldValue = current.v;
                    current.v = v;
                    return oldValue;
                }
                current = current.next;
            }
            // 没有则使用头插法
            table[index] = new Entry<K, V>(k, v, table[index]);
            size++;
            return null;
        }
        // table[index]为空 直接赋值
        table[index] = new Entry<K, V>(k, v, null);
        size++;
        return null;
    }

    /**
     * 调用get方法时，我们根据key的hashcode计算它对应的index，然后直接去table中的对应位置查找即可，如果有链表就遍历。
     * @param k
     * @return
     */
    @Override
    public V get(K k) {
        int index = k.hashCode() % table.length;
        Entry<K, V> current = table[index];
        // 遍历链表
        while(current != null){
            if(current.k == k){
                System.out.println("ooooo");
                return current.v;
            }
            current = current.next;
        }
        return null;
    }


    /**
     * 移除某个节点时，如果该key对应的index处没有形成链表，那么直接置为null。如果存在链表，我们需要将目标节点的前驱节点的next引用指向目标节点的后继节点。由于我们的Entry节点没有previous引用，因此我们要基于目标节点的前驱节点进行操作，即：
     *
     * current.next = current.next.next;
     * current代表我们要删除的节点的前驱节点。
     *
     * 还有一些简单的size()、isEmpty()等方法都很简单，这里就不再赘述。现在，我们自定义的MyHashMap基本可以使用了。
     * @param k
     * @return
     */
    @Override
    public V remove(K k) {
        int index = k.hashCode() % table.length;
        Entry<K, V> current = table[index];
        // 如果直接匹配第一个节点
        if(current.k == k){
            table[index] = null;
            size--;
            return current.v;
        }
        // 在链表中删除节点
        while(current.next != null){
            if(current.next.k == k){
                V oldValue = current.next.v;
                current.next = current.next.next;
                size--;
                return oldValue;
            }
            current = current.next;
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }


    @Override
    public void clear() {

    }
}
