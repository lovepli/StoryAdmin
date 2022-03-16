package com.story.storyadmin.myDatastucture.tree.twoTree;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author: 59688
 * @date: 2021/10/8
 * @description: 二叉查找树 Java实现  https://www.cnblogs.com/skyice/p/10618303.html
 */
public class Node {
    public int index;//关键字段
    public String data;//值
    public Node leftNode;//左节点
    public Node rightNode;//右节点

    public Node root;//节点

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public Node findNode(int key){
        Node current = root;
        while(current.index != key){
            if(key < current.index){//左节点
                current = current.leftNode;
            }else{//右节点
                current = current.rightNode;
            }
            if(current == null){
                return null;
            }
        }
        return current;
    }

    public void insertNode(int key,String value){
        Node node = new Node();
        node.index = key;
        node.data = value;

        if(root == null){
            root = node;
            return;
        }
        //找到插入节点的位置
        Node parent = root;
        Node current = root;
        while(true){
            parent = current;
            if(key == current.index){
                return;
            }
            if(key < current.index){//左节点
                current = current.leftNode;
                if(current == null){//当前节点已经是叶子结点了
                    parent.leftNode = node;
                    return;
                }
            }else{
                current = current.rightNode;
                if(current == null){
                    parent.rightNode = node;
                    return;
                }
            }
        }
    }

    public void inOrder(Node localRoot) {
        if (localRoot != null) {
            inOrder(localRoot.leftNode);
            System.out.println("索引：" + localRoot.index + ",值：" + localRoot.data);
            inOrder(localRoot.rightNode);
        }
    }

    public boolean delete(int key) {
        Node current = root;
        Node parent = root;
        boolean isLeftChild = true;
        //找到被删除的节点，并标识该节点是否为左节点
        while (current.index != key) {
            parent = current;
            if (key < current.index) {
                isLeftChild = true;
                current = current.leftNode;
            } else {
                isLeftChild = false;
                current = current.rightNode;
            }
            if (current == null) {
                return false;
            }
        }
        //第一种情况，删除节点为子节点
        if (current.leftNode == null && current.rightNode == null) {
            if (current == root) {
                root = null;
            } else {
                if (isLeftChild) {
                    parent.leftNode = null;
                } else {
                    parent.rightNode = null;
                }
            }
        } else if ((current.leftNode != null && current.rightNode == null) || (current.leftNode == null && current.rightNode != null)) {
            //第二中情况，删除节点只包含一个子节点，则将子节点移动动当前节点中
            if (current.rightNode == null) {//删除的节点的左节点有值，右节点为空
                if (root == current) {
                    root = current.leftNode;
                } else {
                    if (isLeftChild) {
                        parent.leftNode = current.leftNode;
                    } else {
                        parent.rightNode = current.leftNode;
                    }
                }
            } else {//删除的节点的右节点有值，左节点为空
                if (root == current) {
                    root = current.rightNode;
                } else {
                    if (isLeftChild) {
                        parent.leftNode = current.rightNode;
                    } else {
                        parent.rightNode = current.rightNode;
                    }
                }
            }
        } else if (current.leftNode != null && current.rightNode != null) {
            //第三种情况，删除节点中有左右两个节点
            //找到后继节点
            Node processer = processer(current);
            if (current == root) {//删除是根节点，则
                root = processer;
            } else {
                if (isLeftChild) {
                    parent.leftNode = processer;
                } else {
                    parent.rightNode = processer;
                }
            }
            //选中的节点的左节点与删除节点的左节点相连
            processer.leftNode = current.leftNode;
        }
        return true;
    }

    //找到后继节点
    private Node processer(Node delNode) {
        Node parent = delNode;
        Node success = delNode;
        Node current = delNode.rightNode;
        while (current != null) {
            // 后继节点父节点首先保存后继节点的状态
            parent = current;
            success = current;
            // 后继节点 不断的向左更新
            current = current.leftNode;
        }
        // 假如我们找到的后继节点不直接是 要删除节点的右节点  而是在其右节点那条子树上面最小的一个节点
        if (success != delNode.rightNode) {
            //后继节点的父节点断开其与后继节点左边的引用，重新连接上后继节点的右子节点（因为后继节点是没有左子节点的，锁以要保存之前树的状态，还要把后继节点的右子节点处理一下，不管 其存在不存在）
            parent.leftNode = success.rightNode;
            // 这时候后继节点的右边已经空了 上一条语句已经将其给了自己父节点的左子节点    然后让后继节点的右边 连接要删除节点的右子树
            success.rightNode = delNode.rightNode;
        }
        return success;
    }
}