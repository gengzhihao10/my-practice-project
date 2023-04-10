package com.c.mashibing.suanfa.xinshouke;

public class Code4_4用单链表结构实现队列 {

    //单链表节点
    static class Node<V>{
        V value;
        Node<V> next;

        public Node(V value) {
            this.value = value;
        }
    }

    //队列
    public static class  MyQueue<V> {
        private Node<V> head;
        private Node<V> tail;
        private int size;

        public MyQueue() {
            this.head = null;
            this.tail = null;
            this.size = 0;
        }

        public boolean isEmpty(){
            return size == 0;
        }

        public int size(){
            return size;
        }

        //以队列的形式接受某一个值进入队列里面
        public void offer(V value){
            Node<V> cur = new Node<V>(value);
            if (tail == null){
                head = cur;
                tail = cur;
            }else {
                tail.next = cur;
                tail = cur;
            }
            size++;
        }

        //移除并弹出第一个值
        public V poll(){
            V ans = null;
            if (head != null){
                ans = head.value;
                head = head.next;
                size--;
            }
            if (head == null){
                tail = null;
            }
            return ans;
        }

        //查看第一个元素，不会弹出值
        public V peek(){
            V ans = null;
            if (head != null){
                ans = head.value;
            }
            return ans;
        }
    }


    //栈
    public static class MyStack<V> {
        private Node<V> head;
        private int size;

        public MyStack() {
            head = null;
            size = 0;
        }

        public boolean isEmpty(){
            return size == 0;
        }

        public int size(){
            return size;
        }

        public void push(V value){
            Node<V> cur = new Node<>(value);
            if (head == null){
                head = cur;
            }else {
                cur.next = head;
                head = cur;
            }
            size++;
        }

        public V pop(){
            V ans = null;
            if (head != null){
                ans = head.value;
                head = head.next;
                size--;
            }
            return ans;
        }

        public V peek(){
            return head != null ? head.value : null;
        }
    }
}
