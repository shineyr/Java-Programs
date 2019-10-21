package com.ria.gradle.algorithms.search;

/**
 * 跳表（不含重复元素）实现，以及基于二分查找的搜索
 */
public class SkipList {
    private static final int MAX_LEVEL = 16;

    private Node head;

    private int level;

    public SkipList() {
        head = new Node(); //带头链表
        this.level = 1;
    }

    /**
     * 在跳表中寻找等于指定元素的节点
     * @param target
     * @return
     */
    public Node find(int target) {
        Node p = head;
        for (int i=level-1; i>=0; --i) {
            while (p.nextNodesOnLevel[i] != null && p.nextNodesOnLevel[i].value < target) {
                p = p.nextNodesOnLevel[i];
            }
        }

        if (p.nextNodesOnLevel[0] != null && p.nextNodesOnLevel[0].value == target) {
            return p.nextNodesOnLevel[0];
        }

        return null;
    }

    /**
     * 在跳表中插入一个元素，同时插入随机索引层
     * @param value
     */
    public void insert(int value) {
        int level =  randomMaxLevel();

        Node newNode = new Node(value, level);
        Node[] preNodesOnLevel = new Node[level];
        for (int i=0; i<level; ++i) {
            preNodesOnLevel[i] = head;
        }

        Node p = head;
        for (int i=level-1; i>=0; --i) {
            while (p.nextNodesOnLevel[i] != null && p.nextNodesOnLevel[i].value < value) {
                p = p.nextNodesOnLevel[i];
            }
            preNodesOnLevel[i] = p;
        }

        for (int i=0; i<level; ++i) {
            newNode.nextNodesOnLevel[i] = preNodesOnLevel[i].nextNodesOnLevel[i];
            preNodesOnLevel[i].nextNodesOnLevel[i] = newNode;
        }

        if (this.level < level) {
            this.level = level;
        }
    }

    @Override
    public String toString() {
        String str = "[ ";
        Node p = head;
        while(p.nextNodesOnLevel[0] != null) {
            str = str + p.nextNodesOnLevel[0].value + " ";
            p = p.nextNodesOnLevel[0];
        }

        str += "]";

        return str;
    }

    /**
     * 在跳表中删除等于指定元素的节点
     * @param value
     */
    public void delete(int value) {
        Node[] preNodesOnLevel = new Node[level];

        Node p = head;
        for(int i=level-1; i>=0; --i) {
            while(p.nextNodesOnLevel[i] != null && p.nextNodesOnLevel[i].value < value) {
                p = p.nextNodesOnLevel[i];
            }
            preNodesOnLevel[i] = p;
        }

        if (p.nextNodesOnLevel[0] != null && p.nextNodesOnLevel[0].value == value) {
            for (int i=0; i<level; ++i) {
                preNodesOnLevel[i].nextNodesOnLevel[i] = preNodesOnLevel[i].nextNodesOnLevel[i].nextNodesOnLevel[i];
            }
        }

        while (this.level > 1 && head.nextNodesOnLevel[this.level] == null) {
            this.level--;
        }
    }

    // 理论来讲，一级索引中元素个数应该占原始数据的 50%，二级索引中元素个数占 25%，三级索引12.5% ，一直到最顶层。
    // 因为这里每一层的晋升概率是 50%。对于每一个新插入的节点，都需要调用 randomLevel 生成一个合理的层数。
    // 该 randomLevel 方法会随机生成 1~MAX_LEVEL 之间的数，且 ：
    //        50%的概率返回 1
    //        25%的概率返回 2
    //      12.5%的概率返回 3 ...
    private int randomMaxLevel() {
        int level = 1;

        while (Math.random() < 0.5 && level < MAX_LEVEL)
            level += 1;
        return level;
    }

    static class Node {
        int value;
        int maxLevel; // 节点将建立索引的最高层
        Node[] nextNodesOnLevel; // 节点在索引层的下一元素

        public Node() {
            nextNodesOnLevel = new Node[MAX_LEVEL];
        }

        public Node(int value, int maxLevel) {
            this.value = value;
            this.maxLevel = maxLevel;
            this.nextNodesOnLevel = new Node[maxLevel];
        }
    }
}

class SkipListTest {
    public static void main(String[] args) {
        SkipList skipList = new SkipList();
        for (int i=0; i<10; ++i) {
            skipList.insert(i);
        }
        System.out.println(skipList.toString()); // [ 0 1 2 3 4 5 6 7 8 9 ]

        SkipList.Node node = skipList.find(5);

        skipList.delete(5); // [ 0 1 2 3 4 6 7 8 9 ]

        System.out.println(skipList.toString());
    }
}

