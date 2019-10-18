package com.ria.gradle.collections;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Learn HashSet (https://howtodoinjava.com/java/collections/java-hashset/)
 * public class HashSet<E> extends AbstractSet<E>
 *                 implements Set<E>, Cloneable, Serializable
 */
public class HashSetDemo {

    public static void main(String[] args) {
        /**
         * [1] Add elements
         */
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("A");
        hashSet.add("B");
        hashSet.add("C");

        System.out.println(hashSet);

        /**
         * [2] Check if element exists
         */
        System.out.println(hashSet.contains("D"));

        /**
         * [3] Remove elements
         */
        System.out.println(hashSet.remove("D"));

        /**
         * [4] Iterate HashSet
         */
        Iterator<String> iterator = hashSet.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        /**
         * [5] Convert HashSet to Array
         */
        String[] values = new String[hashSet.size()];
        hashSet.toArray(values);
        System.out.println(Arrays.toString(values));

        /**
         * [6] Java 8 Stream
         */
        List<String> list = hashSet.stream().collect(Collectors.toList());
        System.out.println(list);

        /**
         * [7] TreeSet which is based on Red-Black tree
         */
        TreeSet<String> treeSet = new TreeSet<>(hashSet);
        System.out.println(treeSet);
    }
}
