package com.story.storyadmin.myDatastucture.tree.BPlusTree2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 59688
 * @date: 2021/10/8
 * @description: B+树 -- Java实现 https://www.cnblogs.com/skyice/p/10624876.html
 */
public class BTree {

    private static final int DEFAULT_T = 2;
    public BTreeNode root;
    /* 根据B树的定义，B树的每个非根节点的关键字数n满足(t - 1) <= n <= (2t - 1) */
    private int t = DEFAULT_T;
    /* 非根节点中最小的键值数 */
    private int minKeySize;
    /* 非根节点中最大的键值数 */
    private int maxKeySize;


    public BTree() {

    }

    public BTree(int t /*传入b树的阶数*/) {
        this();
        this.t = t;
        minKeySize = t / 2;
        maxKeySize = t - 1;
    }

    /**
     * 封装方法，找到当前索引的位置，没有找到时，则返回索引所在的节点中集合的位置，
     * @param node
     * @param parentIndex
     * @param key
     * @return
     */
    public Result searchLeafNode(BTreeNode node, int parentIndex, Integer key) {
        SearchResult searchResult = node.searchKey(key);
        if (node.leaf) {//子节点
            return new Result(node, parentIndex, searchResult);
        } else {
            if (searchResult.found) {
                searchResult.index++;
            }
            return searchLeafNode(node.children.get(searchResult.index), searchResult.index, key);
        }
    }

    /**
     * 插入思路：
     *
     * 找到关键字的位置，找到该节点一定是子节点。
     * 添加了关键字的节点，判断是否满了，满了则进行分裂
     * 子节点分裂时，选取中间值上升为父节点中值，但不从子节点中移除，因为子节点保存关键字的值，非子节点保存仅仅是索引
     * @param key
     * @return
     */
    public boolean insert(Integer key) {
        // 找到子节点
        Result result = searchLeafNode(root, 0, key);
        if (result.found) {//找到该节点,不操作
            return false;
        }
        BTreeNode node = result.node;
        node.addKey(key);
        //判断节点是否满了，满了则进行分割
        if (isFull(node)) {
            split(node.parent, result.parentIndex, node);
        }
        return true;
    }

    //进行分割
    public void split(BTreeNode parentNode, int parentIndex, BTreeNode childNode) {
        //将当前节点一份为二，小的部分将放入到新节点中，自身则成为右节点
        int mid = childNode.size() / 2;
        Integer key = null;
        boolean unLeaf = childNode.children.isEmpty();
        //判断是否为子节点，如果是子节点，索引会放入到右节点中，否则会放入到父节点中
        if (unLeaf) {
            key = childNode.keys.get(mid);
        } else {
            key = childNode.keys.remove(mid);
        }
        //分裂出左节点形成新的节点
        List<Integer> keys = new ArrayList<>();
        for (int i = 0; i < mid; i++) {
            Integer k = childNode.keys.remove(0);
            keys.add(k);
        }
        BTreeNode node = new BTreeNode();
        node.parent = parentNode;
        node.leaf = childNode.children.isEmpty();
        node.keys.addAll(keys);
        node.next = childNode;//节点下一个
        //将孩子节点部分也移动到新节点中
        if (!unLeaf) {
            mid = childNode.children.size() / 2;
            for (int i = 0; i < mid; i++) {
                BTreeNode bTreeNode = childNode.children.remove(0);
                bTreeNode.parent = node;
                node.addChild(bTreeNode);
            }
        }
        //父节点为空时，需要产生一个新节点
        if (parentNode == null) {
            root = new BTreeNode();
            root.leaf = false;
            parentNode = root;
            childNode.parent = parentNode;
            node.parent = parentNode;
            parentNode.children.add(childNode);
        }

        int index = parentNode.addKey(key);
        //前一个指针的下一个指针重新定向
        BTreeNode preNode = parentNode.children.get(parentIndex);
        preNode.next = node;
        //将节点添加到列表中
        parentNode.addChild(node, index);
        if (isFull(parentNode)) {//父节点终索引是否满了，满了，则继续分裂
            split(parentNode.parent, 0, parentNode);
        }
    }

    private boolean isFull(BTreeNode node) {
        return node.size() > maxKeySize;
    }


    /**
     * 删除关键字思路：
     *
     * 找到该节点，如果是为找到，直接返回
     * 找到该节点，出现一定是在子节点中，移除掉后，判断子节点的索引值是否大于最小数，大于则返回，否则需要进行合并。移除的当前节点如果出现在父节点中，也需要移除。会从子节点中选择一个节点进行补充
     * 删除后小于最小数，则先从兄弟节点借，如果兄弟节点借不出，则进行合并
     * 子节点进行合并，不需要移动子孩子
     * 父节点进行合并，需要将孩子节点移动
     * @param key
     * @return
     */
    //删除节点
    public boolean delete(Integer key) {
        //找到该节点
        Result result = searchLeafNode(root, 0, key);
        if (!result.found) {//未找到
            return false;
        }
        //删除的节点数量大于
        BTreeNode node = result.node;
        node.removeKey(key);
        if (node.keys.size() >= minKeySize) {
            if (node.parent.keys.contains(key)) {//父节点中包含该节点
                Integer min = node.keys.get(0);
                node.parent.removeKey(key);
                node.parent.addKey(min);
            }
            return true;
        }
        //删除节点后，不满足情况，则找兄弟节点借
        BTreeNode parent = node.parent;
        if (result.parentIndex != 0 && parent.children.get(result.parentIndex - 1).keys.size() > minKeySize) {//左节点有富余可以借
            BTreeNode left = parent.children.get(result.parentIndex - 1);
            Integer max = left.keys.remove(left.keys.size() - 1);
            //替换节点
            if (parent.keys.contains(key)) {
                parent.removeKey(key);
                parent.addKey(max);
                node.addKey(max);
            } else {
                Integer min = node.keys.get(0);
                parent.removeKey(min);
                parent.addKey(max);
                node.addKey(max);
            }
        } else if (result.parentIndex < parent.children.size() - 1 && parent.children.get(result.parentIndex - 1).keys.size() > minKeySize) {//右节点有富余可以借
            BTreeNode right = parent.children.get(result.parentIndex + 1);
            Integer min = right.keys.remove(0);
            //替换节点
            if (parent.keys.contains(key)) {
                parent.removeKey(key);
                parent.addKey(min);
                node.addKey(min);
            } else {
                Integer max = node.keys.get(node.keys.size() - 1);
                parent.removeKey(max);
                parent.addKey(min);
                node.addKey(min);
            }
        } else {
            //兄弟节点也没有，则进行合并
            node.parent.removeKey(key);
            node.parent.removeKey(node.keys.get(0));
            union(node, result.parentIndex);
        }
        return true;
    }

    public void union(BTreeNode node, int parentIndex) {
        int ch = 0;
        if (parentIndex == 0) {//当前节点是最左节点，则只能找右节点
            ch = 1;
        } else {//否则找左节点
            ch = parentIndex - 1;
        }
        BTreeNode parent = node.parent;
        if (parent == null) {
            return;
        }
        BTreeNode kNode = parent.children.get(ch);
        for (int i = 0; i < node.size(); i++) {
            kNode.addKey(node.keys.get(i));
        }
        parent.removeChild(parentIndex);//移除节点
        //判断上级节点
        if (parent.keys.size() < minKeySize) {
            union(parent);
        }
    }


    public void union(BTreeNode node) {
        if (node.parent == null) {
            return;
        }
        Integer min = node.keys.get(0);
        BTreeNode parent = node.parent;
        //找到当前节点的位置
        Integer index = -1;
        for (int i = parent.keys.size() - 1; i >= 0; i--) {
            if (min > parent.keys.get(i)) {
                index = i;
                break;
            }
        }
        Integer parentValue = null;
        if (index != -1) {
            parentValue = parent.keys.get(index);
        } else {//没有找到则表示当前节点为最左节点
            parentValue = parent.keys.get(index + 1);
        }
        if (index != -1 && parent.children.get(index).keys.size() > minKeySize) {
            //判断左节点是否富余
            BTreeNode left = parent.children.get(index);
            Integer max = left.keys.get(left.size() - 1);
            parent.keys.add(index, max);
            node.addKey(parentValue);
            node.addChild(left.children.get(left.children.size() - 1), 0);
        } else if ((index == -1 && parent.children.get(index + 2).keys.size() > minKeySize) || (index < parent.keys.size() - 1 && parent.children.get(index + 1).keys.size() > minKeySize)) {
            //判断右节点是否富余
            BTreeNode right = parent.children.get(index + 1);
            Integer m = right.keys.get(0);
            parent.keys.add(index, m);
            node.addKey(parentValue);
            node.addChild(right.children.get(0));
        } else {
            //合并
            if (index == -1) {
                //合并到右节点
                BTreeNode right = parent.children.get(index + 2);
                Integer pa = parent.keys.remove(index + 1);
                right.addKey(pa);
                for (int i = 0; i < node.keys.size(); i++) {
                    right.addKey(node.keys.get(i));
                }
                List<BTreeNode> bTreeNodes = new ArrayList<>();
                for (int i = 0; i < node.children.size(); i++) {
                    bTreeNodes.add(node.children.get(i));
                }
                for (int i = 0; i < right.children.size(); i++) {
                    bTreeNodes.add(right.children.get(i));
                }
                right.children = bTreeNodes;
                parent.children.remove(index + 1);//移除该节点
                if (parent.keys.isEmpty()) {//节点为空
                    root = right;
                    return;
                }
            } else {
                //合并到左节点
                //找到父级节点下沉,并将该节点所有添加到左节点中
                BTreeNode left = parent.children.get(index);
                Integer max = parent.keys.remove(index.intValue());
                left.addKey(max);
                for (int i = 0; i < node.keys.size(); i++) {
                    left.addKey(node.keys.get(i));
                }
                for (int i = 0; i < node.children.size(); i++) {
                    left.children.add(node.children.get(i));
                }
                parent.children.remove(index + 1);//移除该节点
                if (parent.keys.isEmpty()) {//节点为空
                    root = left;
                    return;
                }
            }
        }
        //判断上级节点
        if (parent.keys.size() < minKeySize) {
            union(parent);
        }
    }



    private void outPut(BTreeNode node, int index) {
        if (node.leaf) {
            List<Integer> kes = node.keys;
            System.out.println("叶子节点，层级：" + index + ",keys:" + kes);
        } else {
            List<Integer> kes = node.keys;
            System.out.println("层级：" + index + ",keys:" + kes);
            for (int i = 0; i < node.children.size(); i++) {
                outPut(node.children.get(i), index + 1);
            }
        }
    }

    public static void main(String[] args) {
        BTree tree = new BTree(5);
        tree.insert(5);
        tree.insert(8);
        tree.insert(10);
        tree.insert(15);
        tree.insert(16);
        tree.insert(17);
        tree.insert(6);
        tree.insert(9);
        tree.insert(18);
        tree.insert(19);
        tree.insert(20);
        tree.insert(21);
        tree.insert(22);
        tree.insert(7);
        tree.outPut(tree.root, 0);

        System.out.println("---------------------------------------------");

        tree.delete(22);
        tree.delete(15);
        tree.outPut(tree.root, 0);
        System.out.println("---------------------------------------------");
        tree.delete(7);
        tree.outPut(tree.root, 0);
        System.out.println("---------------------------------------------");
    }

}