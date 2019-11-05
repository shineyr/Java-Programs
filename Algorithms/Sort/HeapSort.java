package com.ria.gradle.algorithms.sort;

import java.util.Arrays;

/**
 * 堆排序
 * 空间复杂度 O(1) - 原地排序
 * 相同大小数据相对位置改变 - 不稳定排序
 * 时间复杂度平均O(nlogn)
 */
public class HeapSort {

    /**
     * 利用大顶堆，从小到大排序
     * @param array
     */
    public void maxHeapSort(int[] array) {
        buildMaxHeap(array);

        for (int i=array.length-1; i>=0; --i) {
            // 将堆顶元素与最后一个元素交换位置
            swap(array, i, 0);

            // 将剩下元素堆化
            maxHeapify(array, i, 0);
        }
    }

    /**
     * 建造大顶堆
     * @param array
     */
    private void buildMaxHeap(int[] array) {
        int heapSize = array.length;

        if (heapSize <= 1) {
            return;
        }

        // (arr.length - 1) / 2 为最后一个叶子节点的父节点
        // 也就是最后一个非叶子节点，依次堆化直到根节点
        for (int i=(heapSize-1)/2; i>=0; --i) {
            maxHeapify(array, heapSize, i);
        }
    }

    /**
     * 大顶堆堆化，从下向上
     * @param array
     * @param end
     * @param idx
     */
    private void maxHeapify(int[] array, int end, int idx) {
        while (true) {
            int maxPos = idx;

            if (2 * idx + 1 < end && array[2 * idx + 1] > array[idx]) {
                maxPos = 2*idx+1;
            }

            if (2 * idx + 2 < end && array[2 * idx + 2] > array[maxPos]) {
                maxPos = 2*idx+2;
            }

            if (maxPos == idx) {
                break;
            }

            swap(array, idx, maxPos);
            idx = maxPos;
        }
    }

    /**
     * 交换i，j位置的元素
     * @param array
     * @param i
     * @param j
     */
    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    /**
     * 利用小顶堆，从大到小排序
     * @param array
     */
    public void minHeapSort(int[] array) {
        buildMinHeap(array);

        for(int i=array.length - 1; i>=0; --i) {
            swap(array, i, 0);
            minHeapify(array, i, 0);
        }
    }

    /**
     * 建造小顶堆
     * @param array
     */
    private void buildMinHeap(int[] array) {
        int heapSize = array.length;

        if (heapSize <= 1) {
            return;
        }

        for (int i=(heapSize-1)/2; i>=0; --i) {
            minHeapify(array, heapSize, i);
        }
    }

    /**
     * 小顶堆堆化
     * @param array
     * @param end
     * @param idx
     */
    private void minHeapify(int[] array, int end, int idx) {
        while (true) {
            int minPos = idx;

            if (2 * idx + 1 < end && array[2 * idx + 1] < array[idx]) {
                minPos = 2 * idx + 1;
            }

            if (2 * idx + 2 < end && array[2 * idx + 2] < array[minPos]) {
                minPos = 2 * idx + 2;
            }

            if (minPos == idx) {
                break;
            }

            swap(array, idx, minPos);

            idx = minPos;
        }
    }
}

class HeapSortTest {
    public static void main(String[] args) {
        int[] array = new int[] {3,4,5,6,7,1,9,10,23,15};

        // 大顶堆从小到大排序
        new HeapSort().maxHeapSort(array);
        System.out.println(Arrays.toString(array));


        new HeapSort().minHeapSort(array);
        System.out.println(Arrays.toString(array));
    }
}
