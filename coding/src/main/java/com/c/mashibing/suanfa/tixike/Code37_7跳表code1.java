package com.c.mashibing.suanfa.tixike;

/*
todo
 题目1，实现基于跳表的有序表
 */
public class Code37_7跳表code1 {

    public static class SkipListNode{


        public SkipListNode(K k, V v){

        }

        public boolean isKeyLess(K otherKey){

        }

        public boolean isKeyEqual(K otherKey){

        }
    }


    public static class SkipListMap{


        public SkipListMap(){

        }

        private SkipListNode<K, V> mostRightLessNodeInTree(K key){

        }

        private SkipListNode<K, V> mostRightLessNodeInLevel{

        }

        public boolean containsKey(K key){

        }

        public void put(K key, V value){

        }

        public V get(K key){

        }

        public void remove(K key){

        }

        public K firstKey(){

        }

        public K lastKey(){

        }

        public K ceilingKey(K key){

        }

        public K floorKey(K key){

        }

        public int size(){

        }
    }


    // for test
    public static void printAll(SkipListMap<String, String> obj) {
        for (int i = obj.maxLevel; i >= 0; i--) {
            System.out.print("Level " + i + " : ");
            SkipListNode<String, String> cur = obj.head;
            while (cur.nextNodes.get(i) != null) {
                SkipListNode<String, String> next = cur.nextNodes.get(i);
                System.out.print("(" + next.key + " , " + next.val + ") ");
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

