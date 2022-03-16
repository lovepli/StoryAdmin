package com.story.storyadmin.myDatastucture.hashMapDemo.MyHashMap5;

/**
 * @author: lipan
 * @date: 2021年09月25日 11:02 下午
 * @description: https://mp.weixin.qq.com/s/FYOF8OUWQLjB58xer45k3A
 */
public class ElephantHashMap {
    /**
     * table的默认初始长度
     */
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    /**
     * 负载因子，当达到16*0.75时触发扩容
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    Entry[] table;
    int size;
    /**
     * 阈值，当达到此值时扩容。threshold=DEFAULT_INITIAL_CAPACITY*DEFAULT_LOAD_FACTOR
     */
    int threshold;
    /**
     * 无参构造器，当然了，指定初始长度和负载因子更好一些。
     */
    public ElephantHashMap() {
        threshold = (int)(DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
        table = new Entry[DEFAULT_INITIAL_CAPACITY];
    }
    //map的存储单元
    static class Entry{
        int hash;
        Object key;
        Object value;
        Entry next;
        public Entry(int hash, Object key, Object value, Entry next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    /**
     * HashCode做一次hash运算。以此更加保证散列性
     */
    static final int hash(Object key) {
        int h;
        //将hashCode右移16位与hashCode做“异或”运算，即高16位^16位（参考jdk8）。如果key为null，固定放到table[0]的位置
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /**
     * 任何一个key，都需要找到hash后对应的哈希桶位置
     */
    static int findBucketIndex(int h , int length){
    // 求余数的算法的结果是一样的。位运算效率高（装逼一点）
     return h  & (length-1);
    }

    /**
     * 添加元素，如果key已存在，则替换并返回旧值
     */
    public Object put(Object key,Object value){
        if(null==key){
            return putNullKey(value);
        }
        int h=hash(key);
        int i = findBucketIndex(h,table.length);
        //如果已经有这个key，则替换
        for (Entry e = table[i]; e != null; e = e.next) {
            Object k;
            if (e.hash == h && ((k = e.key) == key || key.equals(k))) {
                Object oldValue = e.value;
                e.value = value;
                return oldValue;
            }
        }
        addEntry(h, key, value, i);
        return null;
    }
    /**
     * hashMap允许键为空，对这个空键单独处理
     */
    private Object putNullKey(Object value){
        // 如果已经有这个Null的Key，则替换
        for (Entry e = table[0]; e != null; e = e.next) {
            if (e.key == null) {
                Object oldValue = e.value;
                e.value = value;
                return oldValue;
            }
        }
        addEntry(0, null, value, 0);
        return null;
    }

    void addEntry(int hash, Object key, Object value, int bucketIndex){
        Entry e = table[bucketIndex];
        table[bucketIndex] =new Entry(hash,key,value,e);
        size++;
        if(size>threshold){
            resize(table.length*2);
        }
    }

    /**
     * 扩容，传入新容量
     */
    void resize(int newCapacity){
        Entry[] newTable = new Entry[newCapacity];
        //将所有旧元素换到新table中start
        Entry[] oldTable = table;
        //遍历所有的位置
        for (int j = 0; j < oldTable.length; j++){
            Entry e = oldTable[j];
            if(null!=e){
                oldTable[j]=null; //循环结束后，会将oldTable引用全部置空，GC回收
                do{
                    Entry next = e.next; //先记住e的链表下一个是谁
                    int i = findBucketIndex(e.hash,newTable.length);//找到新的哈希桶位置
                    e.next=newTable[i]; //注意这里是循环，newTable[i]上是可能有元素的。将现在newTable[i]上的元素置为e的下一个，
                    newTable[i]=e; //赋值，所以，jdk7中的扩容是链表头插入。
                    e=next; //下一次循环使用
                }while(null!=e);
            }
        }
        table=newTable;
        //将所有旧元素换到新table中end
        threshold=(int)(newCapacity*DEFAULT_LOAD_FACTOR);
    }

    /**
     *     获取元素
     */
    public Object get(Object key){
        int h = hash(key);
        for(Entry e = table[findBucketIndex(h,table.length)];e!=null;e=e.next){
            if(e.hash==h&&(key==e.key||key.equals(e.key))){
                return e.value;
            }
        }
        return null;
    }

    public Object remove(Object key){
        return removeEntryForKey(key);
    }
    private Entry removeEntryForKey (Object key){
        int hash = hash(key.hashCode());
        int i = findBucketIndex(hash,table.length);
        //先定义一个prev表示待删除的元素的上一个
        Entry prev = table[i];
        Entry e=prev;
        while(e!=null){
            //定位出e的下一个元素，当找到我们要删除的元素时，将链表切断，将prev和next连接即可。参考linkedList的删除即可
            Entry next = e.next;
            //在链表上定位到这个元素，先判断hash值是否相等，再判断key的equals是否相等！注意，这里如果==返回true则说明是一个引用，这样可省略equals的判断。
            if(e.hash==hash&&   (e.key==key||(key!=null&&key.equals(e.key))) ){
                size--;//此时已有元素被定位到，注意while外面是判断了hash值相等的情况。hash值相等时候equals不一定相等。
                //这里，还得判断一个东西，即定位到的这个Entry是否是table[i]，因为table[i]上的删除和链表上的删除有区别
                if(prev==e){
                    table[i]=next;//尽管则个next可能为空
                }else{
                    prev.next=next;//如果不是table位置的，则是链表后边的元素，此时，将prev的下一个置为e的next即可，此时，e已被切断,e的前后Entry被连接起来
                }
                return e;
            }
            //上边的if没进去，则执行下一次循环，指针向后移动一位！
            prev=e;
            e=next;
        }
        //直到while结束，如果没找到元素，return null
        return null;
    }



}
