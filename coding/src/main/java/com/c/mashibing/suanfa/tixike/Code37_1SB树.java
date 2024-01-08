package com.c.mashibing.suanfa.tixike;

/*
todo
 题目1，实现基于Size Balance Tree(SBT，SB树)的有序表
 */
public class Code37_1SB树 {

    public static class SBTNode{

    }

    public static class SizeBalancedTreeMap{


        private SBTNode<K, V> rightRotate(SBTNode<K, V> cur){

        }

        private SBTNode<K, V> leftRotate(SBTNode<K, V> cur){

        }

        private SBTNode<K, V> maintain(SBTNode<K, V> cur){

        }

        //找到k值==key的节点
        private SBTNode<K, V> findLastIndex(K key){

        }

        //找到其节点的k值<=Key且最接近Key的节点
        private SBTNode<K, V> findLastNoSmallIndex(K key){

        }

        //找到其节点的k值>=Key且最接近Key的节点
        private SBTNode<K, V> findLastNoBigIndex(K key){

        }

        private SBTNode<K, V> add(SBTNode<K, V> cur, K key, V value){

        }

        private SBTNode<K, V> delete(SBTNode<K, V> cur, K key){

        }

        private SBTNode<K, V> getIndex(SBTNode<K, V> cur, int kth){

        }

        public int size(){

        }

        public boolean containsKey(K key){

        }

        public void put(K key, V value){

        }

        public void remove(K key){

        }

        public K getIndexKey(int index){

        }

        public V getIndexValue(int index){

        }

        public V get(K key){

        }

        public K firstKey(){

        }

        public K lastKey(){

        }

        //找到其节点的k值>=Key且最接近Key的节点的k值
        public K floorKey(K key){

        }

        //找到其节点的k值<=Key且最接近Key的节点的k值
        public K ceilingKey(K key){

        }


        //*******************************************************

        // for test
        public static void printAll(SBTNode<String, Integer> head) {
            System.out.println("Binary Tree:");
            printInOrder(head, 0, "H", 17);
            System.out.println();
        }

        // for test
        public static void printInOrder(SBTNode<String, Integer> head, int height, String to, int len) {
            if (head == null) {
                return;
            }
            printInOrder(head.r, height + 1, "v", len);
            String val = to + "(" + head.key + "," + head.value + ")" + to;
            int lenM = val.length();
            int lenL = (len - lenM) / 2;
            int lenR = len - lenM - lenL;
            val = getSpace(lenL) + val + getSpace(lenR);
            System.out.println(getSpace(height * len) + val);
            printInOrder(head.l, height + 1, "^", len);
        }

        // for test
        public static String getSpace(int num) {
            String space = " ";
            StringBuffer buf = new StringBuffer("");
            for (int i = 0; i < num; i++) {
                buf.append(space);
            }
            return buf.toString();
        }

        public static void main(String[] args) {
            SizeBalancedTreeMap<String, Integer> sbt = new SizeBalancedTreeMap<String, Integer>();
            sbt.put("d", 4);
            sbt.put("c", 3);
            sbt.put("a", 1);
            sbt.put("b", 2);
            // sbt.put("e", 5);
            sbt.put("g", 7);
            sbt.put("f", 6);
            sbt.put("h", 8);
            sbt.put("i", 9);
            sbt.put("a", 111);
            System.out.println(sbt.get("a"));
            sbt.put("a", 1);
            System.out.println(sbt.get("a"));
            for (int i = 0; i < sbt.size(); i++) {
                System.out.println(sbt.getIndexKey(i) + " , " + sbt.getIndexValue(i));
            }
            printAll(sbt.root);
            System.out.println(sbt.firstKey());
            System.out.println(sbt.lastKey());
            System.out.println(sbt.floorKey("g"));
            System.out.println(sbt.ceilingKey("g"));
            System.out.println(sbt.floorKey("e"));
            System.out.println(sbt.ceilingKey("e"));
            System.out.println(sbt.floorKey(""));
            System.out.println(sbt.ceilingKey(""));
            System.out.println(sbt.floorKey("j"));
            System.out.println(sbt.ceilingKey("j"));
            sbt.remove("d");
            printAll(sbt.root);
            sbt.remove("f");
            printAll(sbt.root);

        }
    }

}
