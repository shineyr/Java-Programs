package com.ria.gradle.algorithms;

import lombok.Data;

/**
 * 专栏地址：https://time.geekbang.org/column/article/41013?code=aJIP6C40Btc-FTIYUuqyl0OWq4qsaCL4GbzhkToY12Y%3D
 * 主题：06 | 链表（上）：如何实现LRU缓存淘汰算法?
 */
public class LRUBasedLinkedList<T> {
    private static final int MAX_CAPACITY = 10;
    private SingleNode<T> headNode;
    private int length;
    private int capacity;

    public LRUBasedLinkedList() {
        this.headNode = new SingleNode<>();
        this.length = 0;
        this.capacity = MAX_CAPACITY;
    }

    public LRUBasedLinkedList(int capacity) {
        this.headNode = new SingleNode<>();
        this.length = 0;
        this.capacity = capacity;
    }

    public void addNode(T element) {
        SingleNode preNode = findPreNode(element);
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
    private void deleteNode(SingleNode preNode){
        SingleNode targetNode = preNode.getNext();
        preNode.setNext(targetNode.getNext());

        targetNode = null;
        --length;
    }

    private void deleteEndNode() {
        if (headNode.getNext() == null) {
            return;
        }
        /**
         * 找到倒数第二个节点
         */
        SingleNode preNode = headNode;
        while(preNode.getNext().getNext() != null) {
            preNode = preNode.getNext();
        }

        SingleNode endNode = preNode.getNext();
        preNode.setNext(null);
        endNode = null;
        --length;
    }

    private void insertNodeAtBegin(T element) {
        SingleNode newNode = new SingleNode(element);
        newNode.setNext(headNode.getNext());
        headNode.setNext(newNode);
        ++length;
    }

    private SingleNode findPreNode(T element) {
        SingleNode curNode = headNode;
        while(curNode.getNext() != null) {
            if(curNode.getNext().getElement() == element) {
                return curNode;
            }
            curNode = curNode.getNext();
        }
        return null;
    }

    public void printList() {
        SingleNode curNode = headNode;
        System.out.print("[ ");
        while (curNode.getNext() != null) {
            System.out.print(curNode.getNext().getElement() + " ");
            curNode = curNode.getNext();
        }
        System.out.println("]");
    }


    @Data
    public class SingleNode<T> {
        private T element;
        private SingleNode next;

        public SingleNode() {
            this.next = null;
        }

        public SingleNode(T element) {
            this.element = element;
            this.next = null;
        }

        public SingleNode(T element, SingleNode next) {
            this.element = element;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LRUBasedLinkedList<Integer> lruList = new LRUBasedLinkedList<>(3);

        Integer[] arrays = {1, 2, 2, 4, 1, 6, 10, 5, 3, 4, 4, 5, 8, 6, 12, 10, 6, 8};
        for (Integer element : arrays) {
            lruList.addNode(element);
            lruList.printList();
        }
    }
}
