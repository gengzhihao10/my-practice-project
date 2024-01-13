package com.c.mashibing.suanfa.tixike;

import java.util.ArrayList;

/*
 题目1，实现基于跳表的有序表
 */
public class Code37_7跳表code1 {

    //跳表节点
    public static class SkipListNode<K extends Comparable<K>, V>{
        //索引表示level层数，不同索引对应的Node对应该节点在不同的层跳到不同的节点上。
        private ArrayList<SkipListNode<K, V>> nextNodes;
        private K key;
        private V value;

        public SkipListNode(K k, V v){
            this.key = k;
            this.value = v;
            nextNodes = new ArrayList<>();
        }

        //key < otherKey true
        //key >= otherKey false
        public boolean isKeyLess(K otherKey){
            return otherKey != null && (key == null || (key.compareTo(otherKey) < 0));
        }

        public boolean isKeyEqual(K otherKey){
            return (key == null && otherKey == null ) ||
                    (key != null && otherKey != null && key.compareTo(otherKey) == 0);
        }
    }


    //跳表
    public static class SkipListMap<K extends Comparable<K>,V>{
        private static final double PRIORITY= 0.5;
        private int size;
        private SkipListNode<K,V> head;
        private int maxLevel;

        public SkipListMap(){
            size = 0;
            maxLevel = 0;
            head = new SkipListNode<>(null,null);
        }

        // 从最高层开始，一路找下去，
        // 最终，找到第0层的<key的最右的节点
        private SkipListNode<K, V> mostRightLessNodeInTree(K key){
            if (key == null){
                return null;
            }

            int level = maxLevel;
            SkipListNode<K,V> pre = head;
            while (level >= 0){
                pre = mostRightLessNodeInLevel(key,pre,level);
                level--;
            }
            return pre;
        }

        // 在level层里，如何往右移动
        // 现在来到的节点是cur，来到了cur的level层，在level层上，找到<key最后一个节点并返回
        private SkipListNode<K, V> mostRightLessNodeInLevel(K key,SkipListNode<K, V> cur,int level){
            if (key == null){
                return null;
            }

            SkipListNode<K,V> pre = cur;
            while (cur != null && cur.isKeyLess(key)){
                pre = cur;
                cur = cur.nextNodes.get(level);
            }
            return pre;
        }

        public boolean containsKey(K key){
            if (key == null){
                return false;
            }

            SkipListNode<K,V> find = mostRightLessNodeInTree(key);
            SkipListNode<K,V> next = find.nextNodes.get(0);
            return next != null && next.isKeyEqual(key);
        }

        public void put(K key, V value){
            if (key == null || value == null){
                return;
            }

            //包含key，只修改value
            if (containsKey(key)){
                SkipListNode<K,V> ans = mostRightLessNodeInTree(key).nextNodes.get(0);
                ans.value = value;
                return;
            }
            //不包含key，要新增
            size++;
            //roll出新节点层数
            int newNodeLevel = 0;
            while (Math.random() < PRIORITY){
                newNodeLevel++;
            }
            //如果maxLevel < newNodeLevel，将head提高到newNodeLevel层
            while (maxLevel < newNodeLevel){
                head.nextNodes.add(null);
                maxLevel++;
            }
            SkipListNode<K,V> newNode = new SkipListNode<>(key,value);
            //跳表从左上到右下，逐层构造nextNode关系
            SkipListNode<K,V> pre = head;
            int level = maxLevel;
            while (level >= 0){
                pre = mostRightLessNodeInLevel(key,pre,level);
                if (level <= newNodeLevel){
                    newNode.nextNodes.set(level,pre.nextNodes.get(level));
                    pre.nextNodes.set(level,newNode);
                }
                level--;
            }
        }

        public V get(K key){
            if (key == null){
                return null;
            }

            SkipListNode<K,V> pre = mostRightLessNodeInTree(key);
            SkipListNode<K,V> cur = pre.nextNodes.get(0);
            return cur != null && cur.isKeyEqual(key) ? cur.value : null;
        }

        public void remove(K key){
            if (key == null || !containsKey(key)){
                return;
            }

            size--;
            SkipListNode<K,V> pre = head;
            SkipListNode<K,V> cur = null;
            int level = maxLevel;
            while (level >= 0){
                pre = mostRightLessNodeInLevel(key,pre,level);
                cur = pre.nextNodes.get(level);
                if (cur != null && cur.isKeyEqual(key)){
                    pre.nextNodes.set(level,cur.nextNodes.get(level));
                }
                //如果这一层只有head这一个节点，就删除这一层
                if (level != 0 && pre == head && pre.nextNodes.get(level) == null){
                    pre.nextNodes.remove(level);
                    maxLevel--;
                }
                level--;
            }
        }

        public K firstKey(){
            SkipListNode<K,V> find = head.nextNodes.get(0);
            return find == null ? null : find.key;
        }

        public K lastKey(){
            int level = maxLevel;
            SkipListNode<K,V> cur = head;
            SkipListNode<K,V> next = null;
            while (level >= 0){
                next = cur.nextNodes.get(level);
                while (next != null){
                    cur = next;
                    next = next.nextNodes.get(level);
                }
                level--;
            }
            return cur.key;
        }

        //>= key
        public K ceilingKey(K key){
            if (key == null){
                return null;
            }

            SkipListNode<K,V> pre = mostRightLessNodeInTree(key);
            SkipListNode<K,V> find = pre.nextNodes.get(0);
            return find != null ? find.key : null;
        }

        // <= key
        public K floorKey(K key){
            if (key == null){
                return null;
            }

            SkipListNode<K,V> pre = mostRightLessNodeInTree(key);
            SkipListNode<K,V> find = pre.nextNodes.get(0);
            return find != null && find.isKeyEqual(key) ? find.key : pre.key;
        }

        public int size(){
            return size;
        }
    }


    // for test
    public static void printAll(SkipListMap<String, String> obj) {
        for (int i = obj.maxLevel; i >= 0; i--) {
            System.out.print("Level " + i + " : ");
            SkipListNode<String, String> cur = obj.head;
            while (cur.nextNodes.get(i) != null) {
                SkipListNode<String, String> next = cur.nextNodes.get(i);
                System.out.print("(" + next.key + " , " + next.value + ") ");
                cur = next;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        SkipListMap<String, String> test = new SkipListMap<>();
        printAll(test);
        System.out.println("======================");
        test.put("A", "10");
        printAll(test);
        System.out.println("======================");
        test.remove("A");
        printAll(test);
        System.out.println("======================");
        test.put("E", "E");
        test.put("B", "B");
        test.put("A", "A");
        test.put("F", "F");
        test.put("C", "C");
        test.put("D", "D");
        printAll(test);
        System.out.println("======================");
        System.out.println(test.containsKey("B"));
        System.out.println(test.containsKey("Z"));
        System.out.println(test.firstKey());
        System.out.println(test.lastKey());
        System.out.println(test.floorKey("D"));
        System.out.println(test.ceilingKey("D"));
        System.out.println("======================");
        test.remove("D");
        printAll(test);
        System.out.println("======================");
        System.out.println(test.floorKey("D"));
        System.out.println(test.ceilingKey("D"));


    }

}

