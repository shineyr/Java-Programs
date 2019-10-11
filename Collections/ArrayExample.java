package com.ria.gradle.collections;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Array of Java
 * https://howtodoinjava.com/java-array/
 */
public class ArrayExample {

    public static void main(String[] args) {
        /**
         * (1) Arrays.toString() to print simple arrays
         */
        String[] array = new String[] {"One", "Two", "Three"};

        System.out.println(array); // [Ljava.lang.String;@61bbe9ba
        System.out.println(Arrays.toString(array)); // [One, Two, Three]

        /**
         * (2) Arrays.deepToString() to print multi-dimensional array
         */
        String[] arr1 = new String[] {"Four", "Five"};
        String[] arr2 = new String[] {"Six", "Seven"};

        String[][] arrayOfArray = new String[][] {arr1, arr2};
        System.out.println(arrayOfArray); // [[Ljava.lang.String;@610455d6
        System.out.println(Arrays.deepToString(arrayOfArray)); // [[Four, Five], [Six, Seven]]

        /**
         * (3) Clone Array
         * arr.clone()
         * Arrays.copyOf(...)
         * System.arraycopy(...)
         */
        String[] names = {"Jeff", "Terry", "Ria"};
        String[] cloneOfNames = names.clone(); //Using arr.clone() method -- Recommended

        String[] copyOfNames = Arrays.copyOf(names, names.length); //Using Arrays.copyOf() method -- most readable

        String[] copyOfNames2 = new String[names.length];
        System.arraycopy(names, 0, copyOfNames2, 0, copyOfNames2.length); //Using System.arraycopy() method -- Equally efficient but less readable

        System.out.println(Arrays.toString(names));
        System.out.println(Arrays.toString(cloneOfNames));
        System.out.println(Arrays.toString(copyOfNames));
        System.out.println(Arrays.toString(copyOfNames2));

        /**
         * (4) Copy Array range
         * Arrays.copyOfRange(...)
         */
        String[] partialNames = Arrays.copyOfRange(names, 0, 2);
        List<String> nameSubList = Arrays.asList(Arrays.copyOfRange(names, 0, 2));

        System.out.println(Arrays.toString(partialNames));
        System.out.println(nameSubList);

        /**
         * (5) Array Union and Intersection
         */
        Integer[] firstArray = {0, 1, 2, 3, 4};
        Integer[] secondArray = {3, 4, 4, 5, 6};
        HashSet<Integer> unionSet = new HashSet();
        unionSet.addAll(Arrays.asList(firstArray));
        unionSet.addAll(Arrays.asList(secondArray));

        System.out.println(unionSet); // [0, 1, 2, 3, 4, 5, 6]

        HashSet<Integer> intersectionSet = new HashSet();
        intersectionSet.addAll(Arrays.asList(firstArray));
        intersectionSet.retainAll(Arrays.asList(secondArray));

        System.out.println(intersectionSet); // [3, 4]

        /**
         * (6) Remove duplicates by using LinkedHashSet
         */
        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>(Arrays.asList(secondArray));
        System.out.println(linkedHashSet);

        Integer[] secondArrayWithoutDuplicates = linkedHashSet.toArray(new Integer[] {});
        System.out.println(Arrays.toString(secondArrayWithoutDuplicates));


    }
}

