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

    public static void main(String[] args) {
        int array[] = {3,1,5,6,2,8,9,2,4,5,6,8,10};

        new BubbleSort().bubbleSort(array);
        System.out.println(Arrays.toString(array));
    }
}
