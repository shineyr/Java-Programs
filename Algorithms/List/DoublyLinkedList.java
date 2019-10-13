package com.ria.gradle.algorithms.list;

import java.util.Arrays;
import java.util.Collection;

/**
 * 双链表 - 不带哨兵节点
 */
public class DoublyLinkedList<T> {
    private DoublyNode head; // 头节点
    private DoublyNode tail; // 尾节点
    private int size;


    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    /**
     * 头部插入
     * @param element
     */
    public void addToHead(T element) {
        DoublyNode newNode = new DoublyNode(null, element, this.head);

        if (head == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.head.prev = newNode;
            this.head = newNode;
        }
        ++size;
    }

    /**
     * 尾部插入
     * @param element
     */
    public void addToTail(T element) {
        DoublyNode newNode = new DoublyNode(this.tail, element, null);

        if(tail == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.tail.next = newNode;
            this.tail = newNode;
        }
        ++size;
    }

    /**
     * 头部插入集合
     * @param collection
     */
    public void addAllToHead(Collection<? extends T> collection) {
        collection.forEach(element -> addToHead(element));
    }

    /**
     * 尾部插入集合
     * @param collection
     */
    public void addAllToTail(Collection<? extends T> collection) {
        collection.forEach(element -> addToTail(element));
    }

    /**
     * 从前向后查找
     * @param element
     * @return
     */
    private DoublyNode findFromHead(T element) {
        if (this.head == null) {
            return null;
        }

        DoublyNode p = this.head;
        while(p != null) {
            if (p.element == element) {
                return p;
            }
            p = p.next;
        }
        return null;
    }

    /**
     * 从后向前查找
     * @param element
     * @return
     */
    private DoublyNode findFromEnd(T element) {
        if (this.tail == null) {
            return null;
        }

        DoublyNode q = this.tail;
        while(q != null) {
            if(q.element == element) {
                return q;
            }

            q = q.prev;
        }

        return null;
    }

    /**
     * 头部删除
     */
    public void removeFromHead(T element) {
        DoublyNode node = findFromHead(element);
        remove(node);
    }

    /**
     * 尾部删除
     * @param element
     */
    public void removeFromTail(T element) {
        DoublyNode node = findFromEnd(element);
        remove(node);
    }

    /**
     * 删除指定节点
     * @param node
     */
    private void remove(DoublyNode node) {
        if(node == null) {
            return;
        }

        if (node == head && node == tail) {
            head = null;
            tail = null;
        } else if (node == head) {
            node.next.prev = null;
            head = node.next;
        } else if (node == tail) {
            node.prev.next = null;
            tail = node.prev;
        } else {
            node.next.prev = node.prev;
            node.prev.next = node.next;
        }

        node = null;
        --size;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        String str = "[ ";
        DoublyNode p = head;
        while (p != null) {
            str = str + p.element + " ";
            p = p.next;
        }
        str += "]";

        return str;
    }

    private class DoublyNode<T> {
        T element;
        DoublyNode prev;
        DoublyNode next;

        DoublyNode() {
            this.prev = null;
            this.next = null;
        }

        DoublyNode(T element) {
            this.element = element;
            this.prev = null;
            this.next = null;
        }

        DoublyNode(DoublyNode prev, T element, DoublyNode next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }


    public static void main(String[] args) {
        DoublyLinkedList<Integer> doublyLinkedList = new DoublyLinkedList<>();

        doublyLinkedList.addAllToTail(Arrays.asList(1,2,3,4,5));
        System.out.println(doublyLinkedList); // [ 1 2 3 4 5 ]

        doublyLinkedList.addAllToHead(Arrays.asList(6,7,8,9,10));
        System.out.println(doublyLinkedList); // [ 10 9 8 7 6 1 2 3 4 5 ]

        doublyLinkedList.addToHead(5);
        System.out.println(doublyLinkedList); // [ 5 10 9 8 7 6 1 2 3 4 5 ]

        doublyLinkedList.removeFromHead(5);
        System.out.println(doublyLinkedList); // [ 10 9 8 7 6 1 2 3 4 5 ]

        doublyLinkedList.addToTail(10);
        System.out.println(doublyLinkedList); // [ 10 9 8 7 6 1 2 3 4 5 10 ]

        doublyLinkedList.removeFromTail(10);
        System.out.println(doublyLinkedList); // [ 10 9 8 7 6 1 2 3 4 5 ]
    }
}
