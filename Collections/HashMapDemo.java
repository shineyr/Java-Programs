package com.ria.gradle.collections;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Learn Java HashMap (https://howtodoinjava.com/java-hashmap/)
 *
 * Class HashMap<K,V>
 * java.lang.Object
 *      java.util.AbstractMap<K,V>
 *              java.util.HashMap<K,V>
 *
 * Type Parameters:
 *      K - the type of keys maintained by this map
 *      V - the type of mapped values
 *
 * All Implemented Interfaces:
 *      Serializable, Cloneable, Map<K,V>
 *
 * Direct Known Subclasses:
 *      LinkedHashMap, PrinterStateReasons
 */
public class HashMapDemo {

    /**
     * == Ways to iterate HashMap and the performance ==
     *
     * 1) Using enrtySet() in for each loop
     *
     * for (Map.Entry<String,Integer> entry : testMap.entrySet()) {
     *     entry.getKey();
     *     entry.getValue();
     * }
     *
     * 2) Using keySet() in for each loop
     *
     * for (String key : testMap.keySet()) {
     *     testMap.get(key);
     * }
     *
     * 3) Using enrtySet() and iterator
     *
     * Iterator<Map.Entry<String,Integer>> itr1 = testMap.entrySet().iterator();
     * while(itr1.hasNext()) {
     *     Map.Entry<String,Integer> entry = itr1.next();
     *     entry.getKey();
     *     entry.getValue();
     * }
     *
     * 4) Using keySet() and iterator
     *
     * Iterator itr2 = testMap.keySet().iterator();
     * while(itr2.hasNext()) {
     *     String key = itr2.next();
     *     testMap.get(key);
     * }
     *
     * == Output ==
     * Using entrySet() in for-each loop : 34
     * Using keySet() in for-each loop : 39
     * Using entrySet() and iterator : 28
     * Using keySet() and iterator : 36
     */
    public void comparePerformanceToIterateHashMap() {
        HashMap<String,Integer> testMap = new HashMap<String,Integer>();
        for(int i=0; i< 10_00_000; i++)
        {
            testMap.put("key_" + i, i);
        }

        long startTime = Calendar.getInstance().getTimeInMillis();
        //First way using entrySet in for-each loop
        for (Map.Entry<String,Integer> entry : testMap.entrySet()) {
            entry.getKey();
            entry.getValue();
        }

        System.out.println("Using entrySet() in for-each loop : " + (Calendar.getInstance().getTimeInMillis() - startTime));

        startTime = Calendar.getInstance().getTimeInMillis();
        //Second way using keySet() in for-each loop
        for (String key : testMap.keySet()) {
            testMap.get(key);
        }

        System.out.println("Using keySet() in for-each loop : " + (Calendar.getInstance().getTimeInMillis() - startTime));

        startTime = Calendar.getInstance().getTimeInMillis();
        //Third way using Iterator on entrySet() in while loop
        Iterator<Map.Entry<String,Integer>> itr1 = testMap.entrySet().iterator();
        while(itr1.hasNext())
        {
            Map.Entry<String,Integer> entry = itr1.next();
            entry.getKey();
            entry.getValue();
        }

        System.out.println("Using entrySet() and iterator : " + (Calendar.getInstance().getTimeInMillis() - startTime));

        startTime = Calendar.getInstance().getTimeInMillis();
        //Third way using Iterator on keySet() in while loop
        Iterator<String> itr2 = testMap.keySet().iterator();
        while(itr2.hasNext())
        {
            String key = itr2.next();
            testMap.get(key);
        }

        System.out.println("Using keySet() and iterator : " + (Calendar.getInstance().getTimeInMillis() - startTime));
    }

    public static void main(String[] args) {

        /**
         * [1] Add key-value -  put(...)
         */
        HashMap<Integer, String> map = new HashMap();
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");

        System.out.println(map);

        /**
         * [2] Get value by key - get(...)
         */
        String value = map.get(3);
        System.out.println(value);

        /**
         * [3] Remove pair by key - remove(...)
         */
        map.remove(3);
        System.out.println(map);

        /**
         * [4] Iterate a map
         * The iterators of this class are fail-fast and if any structure modification is done after creation of iterator,
         * it will throw ConcurrentModificationException.
         */
        Iterator<Integer> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Integer key = iterator.next();
            String val = map.get(key);

            System.out.println("The key is :: " + key + ", and value is :: " + val );
        }

        Iterator<Entry<Integer, String>> entryIterator = map.entrySet().iterator();

        while (entryIterator.hasNext())
        {
            Entry<Integer, String> entry = entryIterator.next();

            System.out.println("The key is :: " + entry.getKey() + ", and value is :: " + entry.getValue() );
        }

        /**
         * [5] Make object as the key of HashMap
         */
        HashMap<Account, String> objectMap = new HashMap<>();
        Account a = new Account(1, "A");
        Account b = new Account(2, "B");
        objectMap.put(a, a.getHolderName());
        objectMap.put(b, b.getHolderName());
        System.out.println(objectMap); // {Account(accountNumber=1, holderName=A)=A, Account(accountNumber=2, holderName=B)=B}

        //Change the keys state so hash map should be calculated again
        a.setHolderName("Defaulter");
        b.setHolderName("Bankrupt");

        System.out.println(objectMap.get(a)); // A
        System.out.println(objectMap.get(b)); // B

        Account c = new Account(1, "C");
        objectMap.put(c, c.getHolderName());

        System.out.println(objectMap.get(c)); // C


        /**
         * [6] Synchronize HashMap - ConcurrentHashMap, Collections.synchronizedMap
         */

        ConcurrentHashMap<Integer, String> concurrentHashMap = new ConcurrentHashMap<>();
        //Put require no synchronization
        concurrentHashMap.put(1, "A");
        concurrentHashMap.put(2, "B");

        //Get require no synchronization
        concurrentHashMap.get(1);

        // Using synchronized block is advisable
        Iterator<Entry<Integer, String>> iter = concurrentHashMap.entrySet().iterator();
        synchronized (concurrentHashMap) {
            while (iter.hasNext()) {
                System.out.println(iter.next());
            }
        }

        Map<Integer, String> synchronizedMap = Collections.synchronizedMap(new HashMap<>());
        //Put require no synchronization
        synchronizedMap.put(1, "A");
        synchronizedMap.put(2, "B");

        //Get require no synchronization
        synchronizedMap.get(1);

        Iterator<Entry<Integer, String>> itr = synchronizedMap.entrySet().iterator();

        //Using synchronized block is advisable
        synchronized (synchronizedMap) {
            while(itr.hasNext()) {
                System.out.println(itr.next());
            }
        }


        /**
         * [7] Merge two maps - not handle duplicate keys
         */
        //map 1
        HashMap<Integer, String> map1 = new HashMap<>();

        map1.put(1, "A");
        map1.put(2, "B");
        map1.put(3, "C");
        map1.put(5, "E");

        //map 2
        HashMap<Integer, String> map2 = new HashMap<>();

        map2.put(1, "G");   //It will replace the value 'A'
        map2.put(2, "B");
        map2.put(3, "C");
        map2.put(4, "D");   //A new pair to be added

        //Merge maps
        map1.putAll(map2);

        System.out.println(map1);

        /**
         * [8] Java 8 merge two maps - handle duplicate keys
         */
        //map 3
        HashMap<Integer, String> map3 = new HashMap<>();

        map3.put(1, "A");
        map3.put(2, "B");
        map3.put(3, "C");
        map3.put(5, "E");

        //map 4
        HashMap<Integer, String> map4 = new HashMap<>();

        map4.put(1, "G");   //It will replace the value 'A'
        map4.put(2, "B");
        map4.put(3, "C");
        map4.put(4, "D");   //A new pair to be added

        //Merge maps
        map4.forEach(
                (k, v) -> map3.merge( k, v, (v1, v2) -> v1.equalsIgnoreCase(v2) ? v1 : v1 + "," + v2)
        );

        System.out.println(map3);

        /**
         * [9] The way to iterate over HashMap
         */
        new HashMapDemo().comparePerformanceToIterateHashMap();

        /**
         * [10] Clone HashMap
         */
        HashMap<Integer, Account> accountHashMap = new HashMap<>();
        accountHashMap.put(1, new Account(1, "A"));
        accountHashMap.put(2, new Account(2, "B"));
        System.out.println("accountHashMap = " + accountHashMap);

        // hallow clone
        HashMap<Integer, Account> clonedMap = (HashMap<Integer, Account>) accountHashMap.clone();
        System.out.println("clonedMap = " + clonedMap);

        clonedMap.get(1).setHolderName("Changed A");

        //Verify content changed of both maps
        System.out.println("accountHashMap = " + accountHashMap);
        System.out.println("clonedMap = " + clonedMap);


        // Deep copy by using Google Gson
        Gson gson = new Gson();
        String json = gson.toJson(accountHashMap);

        Type type = new TypeToken<HashMap<Integer, Account>>(){}.getType();
        HashMap<Integer, Account> deepCopiedMap = gson.fromJson(json, type);

        // Changes DO NOT reflect in other map
        deepCopiedMap.get(1).setHolderName("Changed again A");

        System.out.println("accountHashMap = " + accountHashMap);
        System.out.println("deepCopiedMap = " + deepCopiedMap);
    }

}

@Data
@AllArgsConstructor
class Account {
    private int accountNumber;
    private String holderName;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + accountNumber;

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Account other = (Account) obj;
        if (accountNumber != other.accountNumber)
            return false;
        return true;
    }
}
