package com.ria.gradle.algorithms.sort;

import java.util.Arrays;

/**
 * 快速排序
 *
 */
public class QuickSort {
    public void quickSort(int[] array) {
        int len = array.length;
        if (len <= 1) {
            return;
        }

        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int start, int end) {
        if (start >= end || end >= array.length) {
            return;
        }

        int pos = partition(array, start, end);
        quickSort(array, start, pos-1);
        quickSort(array, pos + 1, end);

    }

    /**
     * A[p…i-1] 的元素都是小于 pivot 的，为已处理区间；A[i...r-1]是未处理区间
     * 每次从未处理区间取一个元素与pivot对比，如果小于pivot，将其加入到已处理区间的尾部即A[i]的位置，i后移。
     */
    private int partition(int[] array, int start, int end) {
        if (start >= end || end >= array.length) {
            return -1;
        }

        int pivot = array[end];
        int i = start; // 未处理区间的开始
        for(int j=start; j<end; ++j) {
            // 从未处理区间的第一个元素
            if (array[j] < pivot) {
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
                i++;
            }
        }

        int tmp = array[end];
        array[end] = array[i];
        array[i] = tmp;

        return i;
    }

    public static void main(String[] args) {
        int array[] = {3,1,5,6,2,8,9,2,4,5,6,8,10};

        new QuickSort().quickSort(array);
        System.out.println(Arrays.toString(array));
    }
}
