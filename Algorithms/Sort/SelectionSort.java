package com.ria.gradle.algorithms.sort;

import java.util.Arrays;

/**
 * 选择排序
 * 空间复杂度 O(1) - 原地排序
 * 相同大小数据相对位置改变 - 不稳定排序
 * 最好时间复杂度O(n^2)，最坏O(n^2)，平均O(n^2)
 */
public class SelectionSort {
    public void selectionSort(int[] array) {
        int len = array.length;
        if (len <= 1) {
            return;
        }

        // 进行n次选择
        for(int i=0; i<len; ++i) {
            int minPos = i;

            // 从无序组找到最小元素下标
            for(int j=i+1; j<len; ++j) {
                if(array[j] < array[minPos]) {
                    minPos = j;
                }
            }

            // 交换
            int tmp = array[i];
            array[i] = array[minPos];
            array[minPos] = tmp;
        }
    }

    public static void main(String[] args) {
        int array[] = {3,1,5,6,2,8,9,2,4,5,6,8,10};

        new SelectionSort().selectionSort(array);
        System.out.println(Arrays.toString(array));
    }
}
