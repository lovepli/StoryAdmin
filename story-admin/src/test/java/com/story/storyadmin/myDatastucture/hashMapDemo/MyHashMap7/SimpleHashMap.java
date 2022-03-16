package com.story.storyadmin.myDatastucture.hashMapDemo.MyHashMap7;

import freemarker.template.utility.NullArgumentException;
import java.io.Serializable;
import java.util.*;

/**
 * 模仿HashMap 手写一个自己的简单HashMap
 * 手写HashMap（数组+链表;可扩容;指定table最大长度）
 * 看过HashMap后，心血来潮，凭感觉写了这个“青春版”HashMap。
 * 虽然只有数组+链表，但是有那么个形状了。
 * 简单测试已经是没什么问题，后续会改进。
 * @param <K>
 * @param <V>
 */
public class SimpleHashMap<K,V> extends AbstractMap<K,V> implements Map<K,V> , Cloneable , Serializable {

    private static final long serialVersionUID = 1L ;

    private transient int size ;

    private transient long modCount ;

    private transient Node<K,V> table[] ;

    private static final int DEFAULT_INITIAL_CAPACITY = 1 << 4 ;

    private static final int MAX_CAPACITY = 1 << 30 ;

    private static final float DEFAULT_LOAD_FACTOR = 0.75f ;

    private int maxCapacity ;

    private final float loadFactor ;

    private int threshold ;

    private transient Set<K> keySet ;

    private transient Set<Map.Entry<K,V>> entrySet ;

    //----------------------------Constructor---------------------------------

    public SimpleHashMap(){
        this(DEFAULT_INITIAL_CAPACITY , DEFAULT_LOAD_FACTOR , MAX_CAPACITY) ;
    }

    public SimpleHashMap(int initialCapacity){
        this(initialCapacity , DEFAULT_LOAD_FACTOR , MAX_CAPACITY) ;
    }

    public SimpleHashMap(float loadFactor){
        this(DEFAULT_INITIAL_CAPACITY , loadFactor , MAX_CAPACITY) ;
    }

    public SimpleHashMap(int initialCapacity , int maxCapacity){
        this(initialCapacity , DEFAULT_LOAD_FACTOR , maxCapacity) ;
    }

    public SimpleHashMap(int initialCapacity , float loadFactor , int maxCapacity){
        if(initialCapacity < 0){
            throw new IllegalArgumentException("initialCapacity is illegal data") ;
        }
        if (maxCapacity < initialCapacity || maxCapacity > MAX_CAPACITY){
            maxCapacity = MAX_CAPACITY ;
        }
        if (loadFactor <=0 || Float.isNaN(loadFactor)){
            throw new IllegalArgumentException("loadFactor is illegal data") ;
        }
        this.size = 0 ;
        this.threshold = twoPower(initialCapacity) ;
        this.maxCapacity = twoPower(maxCapacity) ;
        this.loadFactor = loadFactor ;
        this.modCount = 0 ;
    }


    //----------------------------Constructor Over---------------------------------


    //----------------------------Node---------------------------------
    static class Node<K,V> implements Map.Entry<K,V>{

        final int hash ;
        final K key ;
        V value ;
        Node<K,V> next ;

        public Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V value) {
            V oldVal = this.value ;
            this.value = value ;
            return oldVal ;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this){
                return true ;
            }
            if (o instanceof Map.Entry){
                Map.Entry<?,?> m  = (Map.Entry<?,?>)o ;
                if (Objects.equals(this.key , m.getKey()) &&
                        Objects.equals(this.value , m.getValue())){
                    return true ;
                }
            }
            return false;
        }

        /**
         * key的hash和value的hash做异或运算
         * @return
         */
        @Override
        public int hashCode() {
            return Objects.hashCode(this.key) ^ Objects.hashCode(this.value);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "hash=" + hash +
                    ", key=" + key +
                    ", value=" + value +
                    ", next=" + next +
                    '}';
        }
    }

    //----------------------------Node Over---------------------------------

    /**
     * 找到大于cap的最小2的次幂数
     * @param size
     * @return
     */
    private static int twoPower(int size) {
        int n = DEFAULT_INITIAL_CAPACITY ;
        while(n < size){
            n = n << 1 ;
        }
        return n ;
    }

    private Node<K,V>[] resize(){

        Node<K,V>[] oldTable = this.table ;
        int oldCap = this.table == null ? 0 : this.table.length ;
        int oldThr = threshold ;

        int newCap = 0 ;
        int newThr = 0 ;

        //确定newCap、newThr
        if (oldCap > 0){
            //table已经最大了
            if (oldCap >= maxCapacity){
                this.threshold = maxCapacity ;
                return oldTable ;
            }
            else if ((newCap = oldCap << 1) <= maxCapacity){
                newThr = oldThr << 1 ;
            }
        } else{
            newCap = oldThr ;
            newThr = (int)(newCap*loadFactor) ;
        }

        if (newThr == 0){
            int t = (int)(newCap*this.loadFactor) ;
            newThr = t >= maxCapacity ? maxCapacity : t ;
        }

        //创建新的数组
        Node<K,V>[] newTable = (Node<K,V>[]) new Node[newCap] ;

        this.table = newTable ;
        this.threshold = newThr ;

        //如果oldTable不为空
        if (oldTable != null && oldTable.length != 0){
            //新数组和旧数组传递数据
            transfer(newTable , oldTable) ;
        }
        return this.table ;
    }

    /**
     * 新旧数组交接数据
     * @param newTable
     * @param oldTable
     * @return
     */
    private Node<K,V>[] transfer(Node<K,V>[] newTable, Node<K,V>[] oldTable) {
        Node<K,V>[] nT = newTable ;
        Node<K,V>[] oT = oldTable ;
        if (oT != null && nT != null && nT.length == (oT.length << 1)){
            int l = oT.length ;
            for (int i=0 ; i<l ; i++){
                Node<K,V> e ;
                if ((e = oT[i]) == null){
                    continue ;
                }

                if (e.next == null){
                    //单元素
                    nT[e.hash & (l-1)] = e ;
                }else{
                    //链表
                    //低位链表
                    Node<K,V> loHead = null , loTail = null ;
                    //高位链表
                    Node<K,V> hiHead = null , hiTail = null ;
                    //链表分离
                    Node<K,V> pre = e ;
                    while(pre != null){
                        if ((pre.hash&l) == 0){
                            if (loHead == null){
                                loHead = pre ;
                            }else{
                                loTail.next = pre ;
                            }
                            loTail = pre ;
                        }else{
                            if (hiHead == null){
                                hiHead = pre ;
                            }else{
                                hiTail.next = pre ;
                            }
                            hiTail = pre ;
                        }
                        pre = pre.next ;
                    }

                    if (loTail != null){
                        loTail.next = null ;
                        newTable[i] = loHead ;
                    }
                    if (hiTail != null){
                        hiTail.next = null ;
                        newTable[i+l] = hiHead ;
                    }
                }
            }
        }
        return newTable ;
    }


    @Override
    public int size() {
        return this.size ;
    }


    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return getNode(hash(key) , key) != null ;
    }

    @Override
    public boolean containsValue(Object value) {
        Node<K,V>[] tab ;
        Node<K,V> e ;
        V v ;
        if (value == null || (tab = this.table) == null || tab.length == 0){
            return false ;
        }
        int l = this.table.length ;
        for (int i=0 ; i<l ; i++){
            e = tab[i] ;
            while(e != null){
                if ((v = e.value) == value || v.equals(value)){
                    return true ;
                }
                e = e.next ;
            }
        }
        return false ;
    }

    @Override
    public V get(Object key) {
        Node<K,V> e = getNode(hash(key) , key);
        return  e == null ? null : e.value ;
    }

    /**
     * 根据key获取Node
     * @param hash
     * @param key
     * @return
     */
    private Node<K,V> getNode(int hash, Object key) {

        if (hash == -1){
            return null ;
        }
        if (this.table == null || this.table.length == 0){
            return null ;
        }
        int l = this.table.length ;
        Node<K,V> e = this.table[hash & (l-1) ] ;
        Node<K,V> r = null ;
        while(e != null){
            if (e.hash == hash && (e.key == key || e.key.equals(key))){
                r = e ;
                break ;
            }
            e = e.next ;
        }
        return r ;
    }

    @Override
    public V put(K key, V value) {
        return putVal(hash(key) ,key , value);
    }

    /**
     * 计算hash，扰动函数，前16位和后16位作异或运算
     * @param key
     * @return
     */
    private int hash(Object key) {
        int h = key.hashCode() ;
        return key == null ? -1 : h ^ (h>>>16);
    }

    /**
     *
     * @param hash
     * @param key
     * @param value
     * @return
     */
    private V putVal(int hash , K key, V value) {

        if (key == null || value == null){
            throw new NullArgumentException("key or value is null") ;
        }

        Node<K,V>[] tab;
        Node<K,V> p ;
        int n = 0 ;
        int i = 0 ;
        if ((tab = this.table) == null || (n = tab.length) == 0){
            //bug已修改
            n = (tab = resize()).length ;
        }
        //tab位置没有节点
        if ((p = tab[i = (hash & (n-1))]) == null){
            tab[i] =  newNode(hash , key , value , null)  ;
        }else {
            Node<K,V> e = null;
            K k = null ;
            //首节点
            if (p.hash == hash && (k = p.key) == key && k.equals(key)){
                e = p ;
            }
            //链表插入
            else if (p instanceof Node){
                for (int binCount = 1 ; ; binCount++){
                    if ((e = p.next) == null){
                        p.next = newNode(hash,key,value,null) ;
                        break ;
                    }
                    if (p.hash == hash && (k = p.key) == key && k.equals(key)){
                        break ;
                    }
                    p = e ;
                }
            }
            // 如果e是被替换掉的值 返回
            if (e != null){
                V oldValue = e.value ;
                e.value = value ;
                return oldValue ;
            }
        }
        addCount(1L , this.modCount) ;
        if (++size > threshold){
            resize() ;
        }
        return null ;
    }

    /**
     *
     * @param l
     * @param modCount
     */
    private void addCount(long l, long modCount) {
        modCount += l ;
    }

    /**
     * 创建新的节点
     * @param hash
     * @param key
     * @param value
     * @param next
     * @return
     */
    private Node<K, V> newNode(int hash, K key, V value, Node<K,V> next) {
        return new Node<>(hash , key , value , next);
    }

    @Override
    public V remove(Object key) {
        Node<K,V> e = removeNode(hash(key) , key ) ;
        return e == null ? null : e.value ;
    }

    /**
     * 移除节点
     * @param hash
     * @param key
     * @return
     */
    private Node<K,V> removeNode(int hash, Object key ) {
        Node<K,V>[] tab ;
        int l ;
        if ((tab = this.table) != null && (l = tab.length) != 0){
            int i ;
            Node<K,V> e , p = null , node = null ;
            if ((e = tab[i = (hash & (l-1))]) == null){
                return null ;
            }
            if (e.hash == hash && (e.key == key || e.key.equals(key))){
                node = e ;
            }else{
                p = e ;
                while(p.next != null){
                    //System.out.println("{"+p.next.key+" "+key+" "+(p.next.key.equals(key))+" "+p.next.hash+" "+hash+" "+(p.next.hash == hash)+"}");
                    if (p.next.hash == hash && (p.next.key == key || p.next.key.equals(key))){
                        node = p.next ;
                        break ;
                    }
                    p = p.next ;
                }
            }

            if (node != null){
                if (e == node){
                    tab[i] = node.next ;
                }else{
                    p.next = node.next ;
                }
                --this.size ;
                addCount(1L ,this.modCount);
            }
            return node ;
        }
        return null ;
    }

    @Override
    public void putAll(Map<? extends K , ? extends V> m) {
        if (m == null || m.size() == 0){
            return ;
        }
        if (m.size() + this.size > maxCapacity){
            throw new IllegalArgumentException("map's size is very big to give rise to add unsuccessfully") ;
        }
        for (Map.Entry<? extends K , ? extends V> entry : m.entrySet()) {
            putVal(hash(entry.getKey()) , entry.getKey() , entry.getValue()) ;
        }
    }

    @Override
    public void clear() {
        int l ;
        Node<K,V>[] tab ;
        Node<K,V> e ;
        //帮助GC
        if ((tab = this.table) == null || (l = tab.length) == 0){
            return ;
        }
        for (int i=0 ; i<l ; i++){
            if ((e = tab[i]) == null){
                continue ;
            }else{
                tab[i] = null ;
                Node<K,V> next ;
                while(e != null){
                    next = e.next ;
                    e.next = null ;
                    e = next ;
                }
            }
        }
        this.size = 0 ;
        addCount(1L , this.modCount) ;
    }

    /**
     * 需要改进
     * @return
     */
    @Override
    public Set keySet() {
        Set<K> set = null ;
        Node<K,V>[] tab ;
        Node<K,V> e ;
        int l ;
        if ((tab = this.table) == null || (l = tab.length) == 0){
            return set ;
        }
        set = new HashSet<>() ;

        for (int i=0 ; i<l ; i++){
            e = tab[i] ;
            while(e != null){
                set.add(e.key) ;
                e = e.next ;
            }
        }
        return set ;
    }


    @Override
    public Collection<V> values() {
        Collection<V> collection = null ;
        Node<K,V>[] tab ;
        Node<K,V> e ;
        int l ;
        if ((tab = this.table) == null || (l = tab.length) == 0){
            return collection ;
        }
        collection = new ArrayList<>() ;
        for (int i=0 ; i<l ; i++){
            e = tab[i] ;
            while(e != null){
                collection.add(e.value) ;
                e = e.next ;
            }
        }
        return collection ;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Map.Entry<K,V>> set = null ;
        Node<K,V>[] tab ;
        Node<K,V> e ;
        int l ;

        if ((tab = this.table) == null || (l = tab.length) == 0){
            return set ;
        }
        set = new HashSet<>() ;
        for (int i=0 ; i<l ; i++){
            e = tab[i] ;
            while(e != null){
                set.add(e) ;
                e = e.next ;
            }
        }
        return set ;
    }

    /**
     * 数据量过大可能会崩溃
     * @return
     */
//    public String toString() {
//        Node<K,V> e ;
//        Node<K,V>[] tab = this.table ;
//        int l = tab.length ;
//        StringBuilder result = new StringBuilder();
//        for (int i=0 ; i<l ; i++){
//            if ((e = tab[i]) != null){
//                while(e != null){
//                    result.append(e.toString()+"\n") ;
//                    e = e.next ;
//                }
//                result.append("---------table["+i+"]结束-----------"+"\n");
//            }
//        }
//        return result.toString() ;
//    }
}


