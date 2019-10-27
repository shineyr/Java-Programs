package com.ria.gradle.algorithms.hash;

/**
 * 散列表实现，基于链式法解决索引冲突
 */
public class MyHashMap<K, V> {
    /**
     * 散列表初始容量
     */
    private final static int DEFAULT_CAPACITY = 8;

    /**
     * 散列表装载因子
     */
    private final static double LOAD_FACTOR = 0.75;

    private Entry<K, V>[] elements;

    /**
     * 散列表实际元素个数
     */
    private int size;

    /**
     * 索引
     */
    private int usedIndex;

    public MyHashMap() {
        this.elements = (Entry<K, V>[]) new Entry[DEFAULT_CAPACITY];
    }

    public void put(K key, V value) {
        int index = hash(key);
        if (elements[index] == null) {
            /**
             * 创建一个哨兵节点
             */
            elements[index] = new Entry<>(null, null, null);
        }

        Entry<K, V> head = elements[index];

        /**
         * 当前链还没有元素
         */
        if (head.next == null) {
            head.next = new Entry(key, value, null);
            ++size;
            ++usedIndex;

            /**
             * 如果当前已用索引超过装载容量，扩容
             */
            if (usedIndex >= elements.length * LOAD_FACTOR) {
                resize();
            }
        } else {
            Entry p = head;
            while (p.next != null) {
                p = p.next;
                /**
                 * 若新元素key已存在，覆盖value
                 */
                if (p.key == key) {
                    p.value = value;
                    return;
                }
            }
            /**
             * 将新元素插入链表头部
             */

            head.next = new Entry(key, value, head.next);
        }
    }


    /**
     * 扩容
     */
    private void resize() {
        Entry<K, V>[] oldElements = elements;
        elements = (Entry<K, V>[]) new Entry[(oldElements.length << 1)];

        usedIndex = 0;
        for (int i=0; i<oldElements.length; ++i) {
            if (oldElements[i] == null) {
                continue;
            } else {
                Entry<K, V> p = oldElements[i];
                while (p.next != null) {
                    p = p.next;

                    int newIndex = hash(p.key);
                    if (elements[newIndex] == null) {
                        elements[newIndex] = new Entry<>(null, null, null);
                        ++usedIndex;
                    }
                    elements[newIndex].next = new Entry<>(p.key, p.value, elements[newIndex].next);
                }
            }
        }
    }

    /**
     * 从散列表删除一个元素
     * @param key
     */
    public void remove(K key) {
        int index = hash(key);

        if (elements[index] == null) {
            return;
        }

        Entry p = elements[index], pre;
        while (p.next != null) {
            pre = p;
            p = p.next;

            if (p.key == key) {
                pre.next = p.next;
                --size;

                if (elements[index].next == null) {
                    --usedIndex;
                    elements[index] = null;
                }
            }
        }

    }

    /**
     * 从散列表获取元素
     */
    public V get(K key) {
        int index = hash(key);

        Entry p = elements[index];
        if (p == null || p.next == null) {
            return null;
        }

        while (p.next != null) {
            p = p.next;
            if (p.key == key) {
                return (V) p.value;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String str = "[ ";
        for (int i=0; i<elements.length; ++i) {
            if (elements[i] == null || elements[i].next == null) {
                continue;
            }

            Entry p = elements[i];
            while (p.next != null) {
                p = p.next;
                str = str + p.toString() + " ";
            }
        }
        str += "]";
        return str;
    }

    /**
     * 计算Key的哈希索引
     * @param key
     * @return
     */
    private int hash(Object key) {
        int h;
        return (key == null) ? 0 : ((h = key.hashCode()) ^ (h >>> 16)) % elements.length;
    }

    private static class Entry<K, V> {
        K key;
        V value;

        Entry<K, V> next;

        Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public String toString() {
            return "<" + key + ", " + value + ">";
        }
    }
}

class HashMapTest {
    public static void main(String[] args) {
        MyHashMap<String, String> hashMap = new MyHashMap<>();
        hashMap.put("A", "1");
        hashMap.put("B", "2");
        System.out.println(hashMap); // [ <A, 1> <B, 2> ]

        System.out.println(hashMap.get("A"));
        hashMap.put("A", "3");
        System.out.println(hashMap.get("A"));

        hashMap.put("C", "4");
        hashMap.put("D", "5");
        hashMap.put("E", "6");
        hashMap.put("F", "7");
        hashMap.put("G", "8");
        hashMap.put("H", "9");
        System.out.println(hashMap); // [ <A, 3> <B, 2> <C, 4> <D, 5> <E, 6> <F, 7> <G, 8> <H, 9> ]

        System.out.println(hashMap.get("H"));

        hashMap.remove("H");
        System.out.println(hashMap.get("H"));

        System.out.println(hashMap); // [ <A, 3> <B, 2> <C, 4> <D, 5> <E, 6> <F, 7> <G, 8> ]
    }
}
