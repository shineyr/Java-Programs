package com.ria.gradle.algorithms.sort;

import java.util.Arrays;

/**
 * 插入排序
 * 空间复杂度 O(1) - 原地排序
 * 相同大小数据相对位置不变 - 稳定排序
 * 最好时间复杂度O(n)，最坏O(n^2)，平均O(n^2)
 */
public class InsertionSort {
     public void insertionSort(int[] array) {
         int len = array.length;

         if (len <= 1) {
             return;
         }

         // 从第二个元素开始，为每个元素寻找插入位置
         for (int i=1; i<len; ++i) {
             int value = array[i];
             // 从有序组从尾到头遍历
             int j = i - 1;
             for (; j>=0; --j) {
                 if (array[j] > value) {
                     array[j+1] = array[j];
                 } else {
                     break;
                 }
             }
             array[j+1] = value;
         }
     }

     public static void main(String[] args) {
         int array[] = {3,1,5,6,2,8,9,2,4,5,6,8,10};

         new InsertionSort().insertionSort(array);
         System.out.println(Arrays.toString(array));
     }
}
