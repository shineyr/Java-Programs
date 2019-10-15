package com.ria.gradle.algorithms.queue;

import java.util.Arrays;

/**
 * 基于单链表的双端队列
 */
public class QueueBasedOnLinkedList<T> {
    private SinglyNode head;
    private SinglyNode tail;

    public QueueBasedOnLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public T dequeue() {
        //队列空
        if (head == null) {
            return null;
        }

        SinglyNode node = head;
        T item = (T) node.value;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        node = null;

        return item;
    }

    public void enqueue(T item) {
        SinglyNode node = new SinglyNode(item);
        //队列空
        if (tail == null) {
            tail = node;
            head = node;
        } else {
            tail.next = node;
            tail = tail.next;
        }
    }

    @Override
    public String toString() {
        String result = "[ ";
        SinglyNode p = head;
        while (p != tail.next) {
            result = result + p.value + " ";
            p = p.next;
        }
        result += "]";
        return result;
    }

    private static class SinglyNode<T> {
        T value;
        SinglyNode next;

        SinglyNode(T value) {
            this.value = value;
            this.next = null;
        }
    }
}

class QueueBasedOnLinkedListTest {
    public static void main(String[] args) {
        QueueBasedOnLinkedList queueBasedOnLinkedList = new QueueBasedOnLinkedList();
        Arrays.asList(1,2,3,4,5).forEach(item -> queueBasedOnLinkedList.enqueue(item));

        System.out.println(queueBasedOnLinkedList); // [ 1 2 3 4 5 ]

        System.out.println(queueBasedOnLinkedList.dequeue()); // 1
        System.out.println(queueBasedOnLinkedList.dequeue()); // 2
        System.out.println(queueBasedOnLinkedList.dequeue()); // 3

        System.out.println(queueBasedOnLinkedList); // [ 4 5 ]

        queueBasedOnLinkedList.enqueue(6);
        queueBasedOnLinkedList.enqueue(7);

        System.out.println(queueBasedOnLinkedList); // [ 4 5 6 7 ]

        System.out.println(queueBasedOnLinkedList.dequeue()); // 4
        System.out.println(queueBasedOnLinkedList.dequeue()); // 5
        System.out.println(queueBasedOnLinkedList.dequeue()); // 6

        System.out.println(queueBasedOnLinkedList); // [ 7 ]
    }
}
