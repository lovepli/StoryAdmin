> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s/FYOF8OUWQLjB58xer45k3A)

手写 HashMap
==========

> 1. 个人一直认为，一篇优秀的文章应该篇幅较小，但本文还是没有做到。 2. 关于 hashMap 的诸如线程安全否？可否为 null？不是我们这篇文章讨论的 3. 当真正了解了 HashMap 的存储结构后，对它的增删改查的实现原理理解会事半功倍。

本文主要是深入内部实现原理，并自行手写一个 mini 版的实现。相信大家阅读这个 mini 版的源码后，再去解读 jdk 源码会易如反掌。完整代码放到了 github 上，在公众号回复 github 可获得地址。 看过之前文章的小伙伴们，我们了解了 ArrayList 适合快速查找，LinkedList 适合随机插入。点击下边的传送门直达：

[小象带你手写 ArrayList](http://mp.weixin.qq.com/s?__biz=MzU3NDgwOTc0MA==&mid=2247483663&idx=1&sn=b18577cbf8d8aedca53aff4aeec85976&chksm=fd2df2ecca5a7bfa84aee1dc8583501fee22aac094d802145e4739e3d3ee2ef7184d654e4c58&scene=21#wechat_redirect)

[手写 LinkedList](http://mp.weixin.qq.com/s?__biz=MzU3NDgwOTc0MA==&mid=2247483672&idx=1&sn=323f403b53cf5c52c95253c40d174e84&chksm=fd2df2fbca5a7bedd59f1be0ec2b5767c9c9c564a6f33b5eaafc4edb54b878ac73203be1c0e8&scene=21#wechat_redirect)

那么，有没有一种好的数据结构，同时满足这 2 个优点呢？答案就是 HashMap！

### 什么是数组 + 链表

我们知道，ArrayList 基于数组实现，LinkedList 基于链表实现。而 HashMap 就是数组 + 单链表实现的。即数组中存放的是链表元素！只不过这个链表仅有后继节点，没有前驱节点。故称之为单链表。 看下面这张图，Entry（jdk1.8 以后这个叫 Node）即链表的节点。

![](https://mmbiz.qpic.cn/mmbiz_png/ekOCfHDH2ZNE8pTgZcw0ts5B2PeOlN03porgY7T3kJ9AiblgWKVoYtVFu2iaS5Tld48EMBnZLjHxG2wy3bzttcRA/640?wx_fmt=png)

### 开始手写，定义数据结构

```
/**
 * @author Elephant
 *    
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
}
```

1. 很长的定义，看不懂？没关系，我们来慢慢解释，table 数组是 Entry 类型的。构造器中，先给数组默认 16 个长度，有一个负载因子需要解释下，如果存储的元素过多，会使单链表的长度过长，而影响 HashMap 的查询性能。所以当元素达到一定比例的时候，对数组进行扩容。这个负载因子就是比例。即默认 16*0.75=12，当元素个数达到此值时触发扩容。jdk 的构造函数中提供对此值与初始容量大小进行修改。在此申明一下。 2.Entry 内部类，没什么好说的，单链表即 Map 的存储单元。

### 散列

1. 散列：散列算法不同 jdk 均提供了不同的实现，我们参照了 jdk8 中的将 hashCode 的高 16 位与低 16 位做异或运算，得到一个随机性很强的数字。

```
/**
 * HashCode做一次hash运算。以此更加保证散列性
 */
static final int hash(Object key) {
    int h;
    //将hashCode右移16位与hashCode做“异或”运算，即高16位^16位（参考jdk8）。如果key为null，固定放到table[0]的位置
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}
```

### 寻址

hashMap 初始容量是 16，为什么不是 10 呢？从这您可以找到答案。 hashMap 采用了类似求余的算法，对 hash 值与数组的长度进行取余。余数作为数组下标位置。但是，hashMap 没有用求余，而是采用了效率更高的位 “与” 运算。 我们假设经过 hash 函数计算出的 hash 值是 555，那么我们将它放到数组的哪个位置呢？看下面这个图：

![](https://mmbiz.qpic.cn/mmbiz_png/ekOCfHDH2ZNE8pTgZcw0ts5B2PeOlN03tBnUxGE6xuFicYDCcsZeoTkhMNZmI8E0ibNIyaTXuaDNefIyg91sohjA/640?wx_fmt=png)

555➗16＝34 余 11，而我们做与运算得到的二进制数 1011 正好为 11。 假如数组的长度不是 2 的 N 次幂，那么与运算后的值将有某些位置恒为 0，那么我们的数组某些下标将永远不会有元素存在。因此，再 jdk 的实现中，扩容都是长度 * 2，如果初始长度指定不为 2 的 N 次幂，将自动调整为最靠近的 2 的 N 次幂。

1.  `/**`

2.  `* 任何一个key，都需要找到hash后对应的哈希桶位置`

3.  `*/`

4.  `static int findBucketIndex(int h , int length){`

5.   `// 求余数的算法的结果是一样的。位运算效率高（装逼一点）`

6.   `return h  & (length-1);`

7.  `}`


### 新增元素 put

代码篇幅较长，但难度不算太大。注释中对关键地方做了说明。对于任意给定的键值对，对 key 进行 hash 计算，以保证散列存储，hashMap 允许的 value 为空，允许一个 key 为 null。我们对 null 的 key 单独处理，findBucketIndex 为寻址 hash 桶的位置，即确定数组的下标。下文详细解释

```
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
```

我们注意到 addEntry(h, key, value, i) 这个方法是真正添加操作的方法。看一下它的代码如下: 1. 首先根据 hash 桶的位置定位到数组下标，然后将该数组已存在的元素 e 置为新增元素的 next 节点。我们是采用的头插入法。即从链表头部插入。

```
void addEntry(int hash, Object key, Object value, int bucketIndex){
    Entry e = table[bucketIndex];
    table[bucketIndex] =new Entry(hash,key,value,e);
    size++;
    if(size>threshold){
        resize(table.length*2);
    }
}
```

### 扩容

如果元素数量大于了阈值 threshold，触发扩容，看一下扩容的详细操作，将旧数组中所有元素重新计算位置，并放入了新数组的位置。jdk8 的扩容操作与此大不相同，有兴趣的小伙伴自行阅读。

```
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
```

### 查找元素 get

get 方法相对简单，对 key 进行 hash 运算后定位到数组下标，如果仅有一个元素，则返回，如果是链表，则遍历链表直到 equals 相等。因此，业界有句话：HashCode 相等，equals 不一定相等，反之则成立！从此处可以得到印证。代码如下：

```
/**
 *     获取元素
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
```

### 删除

删除方法无非就是根据 key，找到对应元素，如果数组仅有一个元素，则直接删除，如果是一个链表，则切断链表，将元素移除。实现细节已经给出详细注释：如下

```
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
        if(e.hash==hash&&   (e.key==key||(key!=null&&key.equals(e.key))) ){
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
```

### 后记

测试的代码我就不贴出了，可能会有一些小 bug。大家可直接拿走源码进行测试与阅读。 公众号中可以获取小象的 github 链接。从上边可以拿到所有小象公众号中的源码。

———————END———————

本篇文章对你有帮助，请点击右下角的好看。欢迎分享给你们的好友，转发是对小象最大的鼓励。

**喜欢文章的猿们，欢迎关注****小象 java 馆****。收看更多精彩内容。**

![](https://mmbiz.qpic.cn/mmbiz_png/ekOCfHDH2ZOQnJdHY2KtvDOkwLzsDnrZKE1orwOb0ZWyElrM66rbUicnOXuIyuS7nbdCXPcIVqXWArN2aw7mO4A/640?wx_fmt=png)