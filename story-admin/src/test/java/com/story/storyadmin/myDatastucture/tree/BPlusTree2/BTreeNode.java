package com.story.storyadmin.myDatastucture.tree.BPlusTree2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: 59688
 * @date: 2021/10/8
 * @description:
 */
public class BTreeNode {

    public BTreeNode parent;//父节点

    /*以升序方式存储.*/
    public List<Integer> keys;
    /*孩子*/
    public List<BTreeNode> children;

    public boolean leaf;//是否是子节点

    /*子节点中指向下一个节点.*/
    public BTreeNode next;

    public BTreeNode() {
        keys = new ArrayList<>();
        children = new ArrayList<>();
        leaf = false;
    }

    /*返回关键字个数*/
    public int size() {
        return keys.size();
    }

    //该节点中存储的索引是否包含该key值，包含则返回当前索引值，否则返回小于key值的索引
    public SearchResult searchKey(Integer key) {
        int index = Collections.binarySearch(keys, key);
        if (index >= 0) {
            return new SearchResult(index, true);
        } else {
            return new SearchResult(Math.abs(index + 1), false);
        }
    }

    //keys集合是升序排序，这里做了排序的动作，可以直接添加，然后对集合重新排序
    public int addKey(Integer key) {
        SearchResult searchResult = searchKey(key);
        if (!searchResult.found) {
            List<Integer> list = new ArrayList<>(size() + 1);
            for (int i = 0; i < searchResult.index; i++) {
                list.add(keys.get(i));
            }
            list.add(key);
            for (int i = searchResult.index; i < keys.size(); i++) {
                list.add(keys.get(i));
            }
            keys = list;
        }
        return 0;
    }

    //从集合中移除索引
    public void removeKey(Integer key) {
        keys.remove(key);
    }

    //获取子孩子
    public BTreeNode childAt(int index) {
        if (leaf) {
            throw new UnsupportedOperationException("Leaf node doesn't have children.");
        } else {
            return children.get(index);
        }
    }

    //将子孩子添加到集合末尾
    public void addChild(BTreeNode node) {
        children.add(node);
    }

    public void removeChild(int index) {
        children.remove(index);
    }

    //某个位置上的子孩子添加
    public void addChild(BTreeNode child, int index) {
        List<BTreeNode> newChildren = new ArrayList<>();
        int i = 0;
        for (; i < index; ++i) {
            newChildren.add(children.get(i));
        }
        newChildren.add(child);
        for (; i < children.size(); ++i) {
            newChildren.add(children.get(i));
        }
        children = newChildren;
    }
}

