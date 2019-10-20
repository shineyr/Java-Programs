package com.ria.gradle.algorithms.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * 空间复杂度 O(1) - 原地排序
 * 相同大小数据相对位置不变 - 稳定排序
 * 最好时间复杂度O(n)，最坏O(n^2)，平均O(n^2)
 */
public class BubbleSort {
    public void bubbleSort(int[] array) {
        int len = array.length;
        if (len <= 1) {
            return;
        }

        // 进行N次冒泡，每次冒泡确定尾部元素
        for (int i=0; i<len; ++i) {
            boolean hasSwap = false;
            // 对0 ～ N-i-1进行比较交换
            for(int j=0; j< len-i-1; ++j) {
                if (array[j] > array[j+1]) {
                    int tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                    hasSwap = true;
                }
            }
            // 本次冒泡没有数据交换，说明已排好序
            if (!hasSwap) {
                break;
            }
        }
    }

    /**
     * 第二种冒泡实现思想
     * @param array
     */
    public void bubbleSort2(int[] array) {
        int len = array.length;
        if (len <= 1) {
            return;
        }

        // 每次将i位置的元素，持续与后续元素比较，每次结束i位置即是最小元素
        for (int i=0; i<len; ++i) {
            for (int j=i+1; j<len; ++j) {
                if (array[j] < array[i]) {
                    int tmp = array[j];
                    array[j] = array[i];
                    array[i] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {3,1,5,6,2,8,9,2,4,5,6,8,10};
        new BubbleSort().bubbleSort(array);
        System.out.println(Arrays.toString(array));

        int[] array2 = {3,1,5,6,2,8,9,2,4,5,6,8,10};

        new BubbleSort().bubbleSort2(array2);
        System.out.println(Arrays.toString(array2));
    }
}
