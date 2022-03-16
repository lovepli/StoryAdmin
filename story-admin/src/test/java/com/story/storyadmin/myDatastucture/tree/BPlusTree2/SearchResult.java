package com.story.storyadmin.myDatastucture.tree.BPlusTree2;

/**
 * @author: 59688
 * @date: 2021/10/8
 * @description:
 */
public class SearchResult {

    public int index;//索引所在集合的位置

    public boolean found;//是否找到索引

    public SearchResult() {

    }

    public SearchResult(int index, boolean found) {
        this.index = index;
        this.found = found;
    }
}
