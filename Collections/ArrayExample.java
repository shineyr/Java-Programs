package com.ria.java.collections;

import java.util.Arrays;

/**
 * Array of Java
 * https://howtodoinjava.com/java-array/
 */
public class ArrayExample {

    public static void main(String[] args) {
        // (1) Arrays.toString() to print simple arrays
        String[] array = new String[] {"One", "Two", "Three"};

        System.out.println(array); // [Ljava.lang.String;@61bbe9ba
        System.out.println(Arrays.toString(array)); // [One, Two, Three]

        // (2) Arrays.deepToString() to print multi-dimensional array
        String[] arr1 = new String[] {"Four", "Five"};
        String[] arr2 = new String[] {"Six", "Seven"};

        String[][] arrayOfArray = new String[][] {arr1, arr2};
        System.out.println(arrayOfArray); // [[Ljava.lang.String;@610455d6
        System.out.println(Arrays.deepToString(arrayOfArray)); // [[Four, Five], [Six, Seven]]
    }
}
