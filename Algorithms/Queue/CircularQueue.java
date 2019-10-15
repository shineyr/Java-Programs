package com.ria.gradle.algorithms.queue;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 循环双端队列
 */
public class CircularQueue<T> {
    private static int CAPACITY = 5;
    private T[] data;
    private int head;
    private int tail;

    public CircularQueue(Class<T> type) {
        data = (T[]) Array.newInstance(type, CAPACITY);
        head = 0;
        tail = 0;
    }

    /**
     * 入队列
     * @param item
     * @return
     */
    public boolean enqueue(T item) {
        // 队列满
        if ((tail + 1) % CAPACITY == head) {
            return false;
        }

        data[tail] = item;
        tail = (tail + 1) % CAPACITY;

        return true;
    }

    /**
     * 出队列
     * @return
     */
    public T dequeue() {
        // 队列空
        if (tail == head) {
            return null;
        }

        T item = data[head];

        head = (head + 1) % CAPACITY;
        return item;
    }

    /**
     * 打印队列
     * @return
     */
    @Override
    public String toString() {
        String result = "[ ";
        for (int i = head; i % CAPACITY != tail; i = (i + 1) % CAPACITY) {
            result = result + data[i] + " ";
        }
        result += "]";
        return result;
    }
}

class CircularQueueTest {
    public static void main(String[] args) {
        CircularQueue circularQueue = new CircularQueue(Integer.class);
        Arrays.asList(1,2,3,4,5,6,7).forEach(item -> circularQueue.enqueue(item));
        System.out.println(circularQueue); // [ 1 2 3 4 ]

        System.out.println(circularQueue.dequeue()); // 1
        System.out.println(circularQueue.dequeue()); // 2
        System.out.println(circularQueue.dequeue()); // 3

        System.out.println(circularQueue); // [ 4 ]

        circularQueue.enqueue(6);
        circularQueue.enqueue(7);

        System.out.println(circularQueue); // [ 4 6 7 ]

        System.out.println(circularQueue.dequeue()); // 4
        System.out.println(circularQueue.dequeue()); // 6
        System.out.println(circularQueue.dequeue()); // 7

        System.out.println(circularQueue); // [ ]
    }
}
