package com.ria.gradle.algorithms.sort;

import java.util.Arrays;

/**
 * 合并排序 - 从下向上
 * 空间复杂度 O(n) - 非原地排序
 * 相同元素相对位置不变 - 稳定排序
 * 最好，最坏，平均复杂度 O(nlogn)
 */
public class MergeSort {
    public void mergeSort(int[] array) {
        int len = array.length;

        if(len <= 1) {
            return;
        }

        mergeSort(array, 0, array.length - 1);
    }

    public void mergeSort(int[] array, int start, int end) {
        if (start >= end || end >= array.length) {
            return;
        }

        int midPos = (start + end) / 2;

        mergeSort(array, start, midPos);
        mergeSort(array, midPos + 1, end);
        merge(array, start, midPos, end);
    }

    public void merge(int[] array, int start, int midPos, int end) {
        // 申请临时数组存储排好序的序列
        int[] tmpArray = new int[end - start + 1];

        int i = start, j = midPos + 1, k = 0;
        while (i <= midPos && j <= end) {
            if (array[i] <= array[j]) {
                tmpArray[k++] = array[i];
                ++i;
            } else {
                tmpArray[k++] = array[j];
                ++j;
            }
        }

        while (i <= midPos) {
            tmpArray[k++] = array[i++];
        }

        while (j <= end) {
            tmpArray[k++] = array[j++];
        }

        // Copy 临时数组至愿数组 start ～ end (tmpArray.length = end - start + 1)
        for (int p=0; p<tmpArray.length; ++p) {
            array[start + p] = tmpArray[p];
        }
    }

    public static void main(String[] args) {
        int array[] = {3,1,5,6,2,8,9,2,4,5,6,8,10};

        new MergeSort().mergeSort(array);
        System.out.println(Arrays.toString(array));
    }
}
