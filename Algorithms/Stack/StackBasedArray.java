package com.ria.gradle.algorithms.stack;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 基于数组实现栈
 */
public class StackBasedOnArray<T> {
    private static int CAPACITY = 10;
    private T[] data;
    private int size;

    public StackBasedOnArray(Class<T> type) {
        this.data = (T[]) Array.newInstance(type, CAPACITY);
        size = 0;
    }

    public int getSize() {
        return this.size;
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

    public T top() {
        if (size == 0) {
            return null;
        } else {
            return data[size-1];
        }
    }
}

class StackBasedOnArrayTest {
    public static void main(String[] args) {
        StackBasedOnArray<Integer> stackBasedOnArray = new StackBasedOnArray(Integer.class);

        Arrays.asList(1,2,3,4,5,6).forEach(element -> stackBasedOnArray.push(Integer.class, element));

        System.out.println("stackBasedArray.size:" + stackBasedOnArray.getSize()); // 6

        System.out.println("stackBasedArray.top(): " + stackBasedOnArray.top()); // 6

        Arrays.asList(1,2,3,4,5,6,7,8,9,0).forEach(element -> stackBasedOnArray.push(Integer.class, element));

        System.out.println("stackBasedArray.size:" +  stackBasedOnArray.getSize()); // 16

        System.out.println("stackBasedArray.top(): " + stackBasedOnArray.top()); // 0


        while (stackBasedOnArray.getSize() > 0) {
            System.out.print(stackBasedOnArray.pop() + " ");
        } // 0 9 8 7 6 5 4 3 2 1 6 5 4 3 2 1

        System.out.println();
        System.out.println("stackBasedArray.size: " + stackBasedOnArray.getSize()); // 0
    }
}
