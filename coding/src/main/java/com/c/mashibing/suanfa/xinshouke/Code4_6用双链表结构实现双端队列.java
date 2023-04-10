package com.c.mashibing.suanfa.xinshouke;

public class Code4_6用双链表结构实现双端队列 {

    //双链表
    public static class Node<V> {
        public V value;
        public Node<V> last;
        public Node<V> next;

        public Node(V v) {
            value = v;
            last = null;
            next = null;
        }
    }

    public static class MyDeque<V> {
        private Node<V> head;
        private Node<V> tail;
        private int size;

        public MyDeque() {
            head = null;
            tail = null;
            size = 0;
        }

        //从头部加
        public void pushHead(V value){
            Node<V> cur = new Node<>(value);
            if (head == null){
                head = cur;
                tail = cur;
            }else {
                cur.next = head;
                head.last = cur;
                head = cur;
            }
            size++;
        }

        //从尾部加
        public void pushTail(V value){
            Node<V> cur = new Node<>(value);
            if (head == null){
                head = cur;
                tail = cur;
            }else {
                tail.next = cur;
                cur.last = tail;
                tail = cur;
            }
            size++;
        }

        public V pollHead(){
            V ans = null;
            //如果已经是空的了，就不应该让size继续--，而应直接返回null
            if (head == null){
                return ans;
            }
            size--;
            ans = head.value;
            if (head == tail){
                head =null;
                tail = null;
            }else {
                head = head.next;
                //这一步一定要做，使得之前的节点不可达进而被jvm回收。如果不做，则会内存泄露
                head.last = null;
            }
            return ans;
        }

        public V pollTail(){
            V ans = null;
            if (head == null){
                return ans;
            }
            size--;
            ans = tail.value;
            if (head == tail){
                head = null;
                tail = null;
            }else {
                tail = tail.last;
                tail.last = null;
            }
            return ans;
        }

        public V peekHead(){
            V ans = null;
            if (head != null){
                ans = head.value;
            }
            return ans;
        }

        public V peekTail(){
            V ans = null;
            if (tail != null){
                ans = tail.value;
            }
            return ans;
        }


    }
}
