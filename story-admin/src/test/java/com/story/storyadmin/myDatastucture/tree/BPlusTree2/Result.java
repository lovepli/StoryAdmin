package com.story.storyadmin.myDatastucture.tree.BPlusTree2;

/**
 * @author: 59688
 * @date: 2021/10/8
 * @description:
 */
public  class Result extends SearchResult {

    public BTreeNode node;//当前节点，索引值没有找到，则为要插入的节点

    public int parentIndex;//当前节点在父级节点的位置

    public Result(BTreeNode node, int index, int parentIndex, boolean found) {
        super(index, found);
        this.node = node;
        this.parentIndex = parentIndex;
    }

    public Result(BTreeNode node, int parentIndex, SearchResult searchResult) {
        super(searchResult.index, searchResult.found);
        this.node = node;
        this.parentIndex = parentIndex;
    }
}