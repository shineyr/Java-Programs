package com.ria.gradle.algorithms.queue;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 基于数组实现双端队列
 */
public class QueueBasedOnArray<T> {
    private static int CAPACITY = 5;
    private T[] data;
    private int head;
    private int tail;

    public QueueBasedOnArray(Class<T> type) {
        data = (T[]) Array.newInstance(type, CAPACITY);
        head = 0;
        tail = 0;
    }

    /**
     * 出队列
     */
    public T dequeue() {
        // 队列空
        if (head == tail) {
            return null;
        }

        T item = data[head];
        ++head;
        return item;
    }

    /**
     * 入队列，tail到达队尾时，尝试数据搬移
     */
    public boolean enqueue(T item) {
        // tail已经到队尾
        if (tail == CAPACITY) {
            // 此时队列满
            if (head == 0) {
                return false;
            } else {
                // 将队列中的元素向前移动
                for (int i = head; i < tail; ++i) {
                    data[i-head] = data[i];
                }
                tail = tail - head;
                head = 0;
            }
        }

        data[tail++] = item;
        return true;
    }

    @Override
    public String toString() {
        String result = "[ ";
        for (int i = head; i < tail; ++i) {
            result = result + data[i] + " ";
        }
        result += "]";
        return result;
    }
}

class QueueBasedOnArrayTest {
    public static void main(String[] args) {
        QueueBasedOnArray queueBasedOnArray = new QueueBasedOnArray(Integer.class);
        Arrays.asList(1,2,3,4,5,6,7).forEach(item -> queueBasedOnArray.enqueue(item));
        System.out.println(queueBasedOnArray); // [ 1 2 3 4 5 ]

        System.out.println(queueBasedOnArray.dequeue()); // 1
        System.out.println(queueBasedOnArray.dequeue()); // 2
        System.out.println(queueBasedOnArray.dequeue()); // 3

        System.out.println(queueBasedOnArray); // [ 4 5 ]

        queueBasedOnArray.enqueue(6);
        queueBasedOnArray.enqueue(7);

        System.out.println(queueBasedOnArray); // [ 4 5 6 7 ]

        System.out.println(queueBasedOnArray.dequeue()); // 4
        System.out.println(queueBasedOnArray.dequeue()); // 5
        System.out.println(queueBasedOnArray.dequeue()); // 6

        System.out.println(queueBasedOnArray); // [ 7 ]
    }
}
