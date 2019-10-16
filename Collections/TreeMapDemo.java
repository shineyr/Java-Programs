package com.ria.gradle.collections;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Learn Java TreeMap (https://howtodoinjava.com/java/collections/treemap-class/)
 *
 * TreeMap in Java is used to store key-value pairs very similar to HashMap class.
 * Difference is that TreeMap provides an efficient way to store key/value pairs in sorted order.
 * It is a red-Black tree based NavigableMap implementation.
 */
public class TreeMapDemo {
    public static void main(String[] args) {
        /**
         * [1] Add key-value -  put(...)
         */
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(1, "A");
        treeMap.put(2, "B");
        treeMap.put(4, "D");
        treeMap.put(3, "C");

        System.out.println(treeMap);

        /**
         * [2] Get value by key - get(...)
         */
        System.out.println(treeMap.get(5));
        System.out.println(treeMap.getOrDefault(5, "E"));

        /**
         * [3] Iterator
         */
        Iterator<Integer> iterator =  treeMap.keySet().iterator();

        while(iterator.hasNext()) {
            Integer key = iterator.next();
            System.out.println("Key: " + key + ", Value: " + treeMap.get(key));
        }


        /**
         * [4] Use Comparator
         */
        TreeMap<Integer, String> treeMapWithComparator = new TreeMap<>(Collections.reverseOrder());
        treeMapWithComparator.put(1, "A");
        treeMapWithComparator.put(2, "B");
        treeMapWithComparator.put(4, "D");
        treeMapWithComparator.put(3, "C");

        System.out.println(treeMapWithComparator);

        /**
         * [5] Concurrency
         */
        Map<Integer, String> syncTreeMap = Collections.synchronizedSortedMap(new TreeMap<>());

        syncTreeMap.put(1,  "A");
        syncTreeMap.put(2,  "B" );
        syncTreeMap.put(3,  "C");

        System.out.println(syncTreeMap);
    }


}
