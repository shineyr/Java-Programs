package com.ria.gradle.algorithms.stack;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 基于数组实现栈
 */
public class StackBasedArray<T> {
    private static int CAPACITY = 10;
    private T[] data;
    private int size;

    public StackBasedArray(Class<T> type) {
        this.data = (T[]) Array.newInstance(type, CAPACITY);
        size = 0;
    }

    public void push(Class<T> type, T element) {
        if (size < CAPACITY) {
            data[size] = element;
            ++size;
        } else {
            // 扩容
            CAPACITY *= 2;
            T[] oldData = data;

            data = (T[]) Array.newInstance(type, CAPACITY);
            for(int i=0; i<size; ++i) {
                data[i] = oldData[i];
            }
            data[size++] = element;
        }
    }

    public T pop() {
        if (size == 0) {
            return null;
        } else {
            return data[--size];
        }
    }

    public static void main(String[] args) {
        StackBasedArray<Integer> stackBasedArray = new StackBasedArray(Integer.class);

        Arrays.asList(1,2,3,4,5,6).forEach(element -> stackBasedArray.push(Integer.class, element));

        System.out.println("stackBasedArray.size:" + stackBasedArray.size);

        Arrays.asList(1,2,3,4,5,6,7,8,9,0).forEach(element -> stackBasedArray.push(Integer.class, element));

        System.out.println("stackBasedArray.size:" +  stackBasedArray.size);

        while (stackBasedArray.size > 0) {
            System.out.print(stackBasedArray.pop() + " ");
        } // 0 9 8 7 6 5 4 3 2 1 6 5 4 3 2 1

        System.out.println();
        System.out.println("stackBasedArray.size: " + stackBasedArray.size); // 0
    }
}
