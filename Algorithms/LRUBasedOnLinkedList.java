package com.ria.gradle.algorithms.list;

import lombok.Data;

/**
 * 专栏地址：https://time.geekbang.org/column/article/41013?code=aJIP6C40Btc-FTIYUuqyl0OWq4qsaCL4GbzhkToY12Y%3D
 * 主题：06 | 链表（上）：如何实现LRU缓存淘汰算法?
 */
public class LRUBasedOnLinkedList<T> {
    private static final int MAX_CAPACITY = 10;
    private SinglyNode<T> head;
    private int length;
    private int capacity;

    public LRUBasedOnLinkedList() {
        this.head = new SinglyNode<>();
        this.length = 0;
        this.capacity = MAX_CAPACITY;
    }

    public LRUBasedOnLinkedList(int capacity) {
        this.head = new SinglyNode<>();
        this.length = 0;
        this.capacity = capacity;
    }

    public void add(T element) {
        SinglyNode preNode = findPreNode(element);
        if (preNode != null) {
            deleteNode(preNode);
        } else {
            if (this.length >= this.capacity) {
                deleteEndNode();
            }
        }
        insertNodeAtBegin(element);
    }

    /**
     * Delete the node by using the previous node
     * @param preNode
     */
    private void deleteNode(SinglyNode preNode){
        SinglyNode targetNode = preNode.next;
        preNode.next = targetNode.next;

        targetNode = null;
        --length;
    }

    private void deleteEndNode() {
        if (head.next == null) {
            return;
        }
        /**
         * 找到倒数第二个节点
         */
        SinglyNode preNode = head;
        while(preNode.next.next != null) {
            preNode = preNode.next;
        }

        SinglyNode endNode = preNode.next;
        preNode.next = null;
        endNode = null;
        --length;
    }

    private void insertNodeAtBegin(T element) {
        SinglyNode newNode = new SinglyNode(element);
        newNode.next = head.next;
        head.next = newNode;
        ++length;
    }

    private SinglyNode findPreNode(T element) {
        SinglyNode curNode = head;
        while(curNode.next != null) {
            if(curNode.next.element == element) {
                return curNode;
            }
            curNode = curNode.next;
        }
        return null;
    }

    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }

        SinglyNode curNode = head;
        String str = "[ ";

        while (curNode.next != null) {
            str = str + curNode.next.element + " ";
            curNode = curNode.next;
        }
        str += "]";

        return str;
    }

    @Data
    private class SinglyNode<T> {
        T element;
        SinglyNode next;

        SinglyNode() {
            this.next = null;
        }

        SinglyNode(T element) {
            this.element = element;
            this.next = null;
        }

        SinglyNode(T element, SinglyNode next) {
            this.element = element;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LRUBasedOnLinkedList<Integer> lruList = new LRUBasedOnLinkedList<>(3);

        Integer[] arrays = {1, 2, 2, 4, 1, 6, 10, 5, 3, 4, 4, 5, 8, 6, 12, 10, 6, 8};
        for (Integer element : arrays) {
            lruList.add(element);
            System.out.println(lruList);
        }

        /** output
            [ 1 ]
            [ 2 1 ]
            [ 2 1 ]
            [ 4 2 1 ]
            [ 1 4 2 ]
            [ 6 1 4 ]
            [ 10 6 1 ]
            [ 5 10 6 ]
            [ 3 5 10 ]
            [ 4 3 5 ]
            [ 4 3 5 ]
            [ 5 4 3 ]
            [ 8 5 4 ]
            [ 6 8 5 ]
            [ 12 6 8 ]
            [ 10 12 6 ]
            [ 6 10 12 ]
            [ 8 6 10 ]

         */
    }
}
