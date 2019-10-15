package com.ria.gradle.algorithms.list;

import java.util.Arrays;
import java.util.Collection;

/**
 * 单链表 - 不带哨兵
 */
public class SinglyLinkedList<T extends Comparable> {
    private SinglyNode head;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
    }

    /**
     * 头部插入
     * @param element
     */
    public void addToHead(T element) {
        SinglyNode newNode = new SinglyNode(element);
        newNode.next = head;
        head = newNode;

        ++size;
    }

    /**
     * 尾部插入
     * @param element
     */
    public void addToTail(T element) {
        SinglyNode newNode = new SinglyNode(element);

        if(head == null) {
            head = newNode;
            ++size;
            return;
        }

        SinglyNode p = head;
        while(p.next != null) {
            p = p.next;
        }

        p.next = newNode;
        ++size;
    }

    /**
     * 头部插入集合
     * @param collection
     */
    public void addAllToHead(Collection<T> collection) {
        collection.forEach(element -> addToHead(element));
    }

    /**
     * 尾部插入集合
     * @param collection
     */
    public void addAllToTail(Collection<T> collection) {
        collection.forEach(element -> addToTail(element));
    }

    /**
     * 删除最后一个节点
     * @return
     */
    public T deleteFromTail() {
        return deleteFromTail(1);
    }

    /**
     * 删除倒数第n个节点
     * @param n
     * @return
     */
    public T deleteFromTail(int n) {
        if (n > size) {
            return null;
        }

        SinglyNode first = head;
        int idx = 0;
        while (idx < n) {
            first = first.next;
            ++idx;
        }

        SinglyNode second = head;
        while (first.next != null) {
            second = second.next;
            first = first.next;
        }

        SinglyNode node = second.next;
        second.next = second.next.next;
        T ret = (T) node.value;
        node = null;
        return ret;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        String str = "[ ";
        SinglyNode p = head;
        while (p != null) {
            str = str + p.value + " ";
            p = p.next;
        }
        str += "]";

        return str;
    }

    /**
     * 单链表反转
     */
    public void reverse() {
        if(size <= 1) {
            return ;
        }

        SinglyNode p = head;
        head = null;

        while(p != null) {
            SinglyNode q = p.next; //保存下一个节点，避免丢失

            p.next = head;
            head = p;
            p = q;
        }
    }

    /**
     * 单链表环检测
     */
    public boolean checkCircle() {
        if (size < 1) {
            return false;
        }

        SinglyNode slow = head, fast = head.next;
        while(fast != null && fast.next != null) {
            if (slow == fast) {
                return true;
            }

            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }

    /**
     * 单链表排序
     */
    public void sort() {
        if (this.size <= 1) {
            return;
        }

        SinglyNode node = head;
        head = null;

        while (node != null) {
            SinglyNode tmp = node.next;
            if (head == null) {
                node.next = null;
                head = node;
            } else {
                SinglyNode p = head, pre = null;

                while (p != null) {
                    if (node.compareTo(p) > 0) {
                        pre = p;
                        p = p.next;
                    } else {
                        if (pre == null) {
                            node.next = p;
                            head = node;
                        } else {
                            node.next = p;
                            pre.next = node;
                        }
                        break;
                    }
                }

                if (p == null) {
                    node.next = p;
                    pre.next = node;
                }
            }

            node = tmp;
        }
    }

    /**
     * 合并两个有序单链表
     * @param list
     * @return
     */
    public SinglyLinkedList sortedMerge(SinglyLinkedList list) {
        SinglyLinkedList result = new SinglyLinkedList();
        SinglyNode first = this.head, second = list.head;
        while(first != null && second != null) {
           if (first.compareTo(second) < 0) {
               result.addToTail(first.value);
               first = first.next;
           } else {
               result.addToTail(second.value);
               second = second.next;
           }
        }

        while(first != null) {
            result.addToTail(first.value);
            first = first.next;
        }

        while(second != null) {
            result.addToTail(second.value);
            second = second.next;
        }

        return result;
    }

    /**
     * 求链表中间节点
     * @return
     */
    public T getMiddleNode() {
        if(size < 1) {
            return null;
        } else if (size == 1) {
            return (T) head.value;
        } else {
            SinglyNode slow = head, fast = head;

            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            return (T) slow.value;
        }
    }

    private static class SinglyNode<T extends Comparable<? super T>> implements Comparable<SinglyNode<T>> {
        T value;
        SinglyNode next;

        SinglyNode() {
            this.next = null;
        }

        SinglyNode(T element) {
            this.value = element;
            this.next = null;
        }

        @Override
        public int compareTo(SinglyNode<T> o) {
            return value.compareTo(o.value);
        }
    }


}

 class Test {
    public static void main(String[] args) {
        SinglyLinkedList firstList = new SinglyLinkedList();
        firstList.addAllToHead(Arrays.asList(1,2,1,9,3,4,7,8));
        System.out.println(firstList); // [ 8 7 4 3 9 1 2 1 ]

        firstList.reverse();
        System.out.println(firstList); // [ 1 2 1 9 3 4 7 8 ]

        firstList.sort();
        System.out.println(firstList); // [ 1 1 2 3 4 7 8 9 ]



        boolean isCircled = firstList.checkCircle();
        System.out.println(isCircled); // false

        SinglyLinkedList secondList = new SinglyLinkedList();
        secondList.addAllToTail(Arrays.asList(2,1,4,7,9,2,4,5,6));
        System.out.println(secondList);; // [ 2 1 4 7 9 2 4 5 6 ]

        secondList.sort();
        System.out.println(secondList); // [ 1 2 2 4 4 5 6 7 9 ]

        SinglyLinkedList mergedList = firstList.sortedMerge(secondList);
        System.out.println(mergedList); // [ 1 1 1 2 2 2 3 4 4 4 5 6 7 7 8 9 9 ]


        Integer deletedValue = (Integer) mergedList.deleteFromTail(11);
        System.out.println(deletedValue); // 3
        System.out.println(mergedList); // [ 1 1 1 2 2 2 4 4 4 5 6 7 7 8 9 9 ]

        Integer middleValue = (Integer) mergedList.getMiddleNode();
        System.out.println(middleValue); // 4

        deletedValue = (Integer) mergedList.deleteFromTail();
        System.out.println(deletedValue); // 9
        System.out.println(mergedList); // [ 1 1 1 2 2 2 4 4 4 5 6 7 7 8 9 ]

    }
}
