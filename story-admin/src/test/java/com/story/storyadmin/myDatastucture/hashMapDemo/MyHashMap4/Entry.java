package com.story.storyadmin.myDatastucture.hashMapDemo.MyHashMap4;


/**
 * @author: 59688
 * @date: 2021/9/24
 * @description: HashMap的要素之一，单链表的体现就在这里！
 */
public class Entry<K,V> implements MyMap.IEntry<K,V> {
    public K key;
    public V value;
    public Entry<K,V> next;

    public Entry(){}

    public Entry(K key, V value, Entry<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    //@Override
    //public String toString(){
    //    return key + "="+value;
    //}
    //
    //@Override
    //public int hashCode(){
    //  return   Objects.hashCode(key)^Objects.hashCode(value);
    //}
    //
    //@Override
    //public boolean equals(Object o){
    //   if (o == this) return true;
    //   if (o instanceof MyMap.IEntry){
    //       MyMap.IEntry<?,?> e=(MyMap.IEntry<?,?>)o;
    //       if (Objects.equals(key,e.getKey())&& Objects.equals(value,e.getValue())) return true;
    //   }
    //   return false;
    //}

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    //@Override
    //public V setValue( V newValue) {
    //    V oldValue = value;
    //    value =newValue;
    //    return oldValue;
    //}
}
