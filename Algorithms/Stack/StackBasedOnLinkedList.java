package com.ria.gradle.algorithms.stack;

import java.util.Arrays;

/**
 * 基于链表实现栈
 */
public class StackBasedOnLinkedList<T> {
    SinglyNode head;
    int size;

    StackBasedOnLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public void push(T element) {
        SinglyNode newNode = new SinglyNode(element);
        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        ++size;
    }

    public T top() {
        if (head == null) {
            return null;
        }

        return (T) head.value;
    }

    public T pop() {
        if (head == null) {
            return null;
        }

        SinglyNode tmp = head;
        head = head.next;
        T result = (T) tmp.value;
        tmp = null;

        --size;

        return result;
    }

    private class SinglyNode<T> {
        T value;
        SinglyNode next;

        SinglyNode(T value) {
            this.value = value;
            this.next = null;
        }
    }


    public static void main(String[] args) {
        StackBasedOnLinkedList<Integer> stackBasedOnLinkedList = new StackBasedOnLinkedList();

        Arrays.asList(1,2,3,4,5,6).forEach(element -> stackBasedOnLinkedList.push(element));

        System.out.println("stackBasedOnLinkedList.size:" + stackBasedOnLinkedList.size); // 6

        System.out.println("stackBasedOnLinkedList.top(): " + stackBasedOnLinkedList.top()); // 6

        Arrays.asList(1,2,3,4,5,6,7,8,9,0).forEach(element -> stackBasedOnLinkedList.push(element));

        System.out.println("stackBasedOnLinkedList.size:" +  stackBasedOnLinkedList.size); // 16

        System.out.println("stackBasedOnLinkedList.top(): " + stackBasedOnLinkedList.top()); // 0

        while (stackBasedOnLinkedList.size > 0) {
            System.out.print(stackBasedOnLinkedList.pop() + " ");
        } // 0 9 8 7 6 5 4 3 2 1 6 5 4 3 2 1

        System.out.println();
        System.out.println("stackBasedOnLinkedList.size: " + stackBasedOnLinkedList.size); // 0
    }
}
