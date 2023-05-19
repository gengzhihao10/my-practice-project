package com.c.mashibing.suanfa.tixike;


import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * todo
 *  题目1，双向链表实现队列
 *  题目2，双向链表实现栈
 *  题目3，数组实现栈
 *  题目4，数组实现队列。google常考第一题
 */
public class Code4_2栈和队列 {

    /**
     * 题目1，双向链表实现的队列
     */
    public static class Queue1{

        private List<Integer> list = new LinkedList();
        private int size = 0;

        public int poll(){
            if (size == 0){
                throw new RuntimeException("队列为空！");
            }
            size--;
            return list.remove(0);

        }

        public void add(int value){
            list.add(value);
            size++;
        }
    }

    /**
     * 题目2，双向链表实现栈
     */
    public static class Stack1{

        private List<Integer> list = new LinkedList();
        private int size;


        public void add(int value){
            list.add(value);
            size++;
        }

        public int poll(){
            if (size == 0){
                throw new RuntimeException("栈为空！");
            }
            return list.remove(--size);
        }
    }

    /**
     * 题目3，数组实现栈
     */
    public static class Stack2{

        private int[] arr ;
        //最大容量
        private int limit;
        //当前容量
        private int size = 0;

        public Stack2(int limit) {
            this.limit = limit;
            arr = new int[limit];
        }

        public void add(int value){
            if (size >= limit){
                throw new RuntimeException("栈满了！");
            }
            arr[size++] = value;
        }

        public int poll(){
            if (size == 0){
                throw new RuntimeException("栈空了！");
            }
            return arr[--size];
        }
    }

    /**
     * 题目4，用数组实现队列
     */
    public static class Queue2{

        private int[] arr;
        private int size = 0;
        private int start = 0, end = 0;
        private int limit;

        public Queue2(int limit) {
            this.limit = limit;
            arr = new int[limit];
        }

        public void add(int value){
            if (size >= limit){
                throw new RuntimeException("队列满了！");
            }
            size++;

            end = nextIndex(end);
            arr[end] = value;
        }

        public int poll(){
            if (size >= limit){
                throw new RuntimeException("队列满了！");
            }
            size--;

            int result = arr[start];
            start = nextIndex(start);
            return result;
        }

        //计算数组中下一个index的值
        private int nextIndex(int cur){
            return cur + 1 == size ? 0 : cur + 1;
        }
    }

    public static void main(String[] args) {
        Queue q = new LinkedList<>();


//        List<Integer> list = new LinkedList();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        System.out.println(list.remove(2));
//        System.out.println(list.remove(2));

        Queue2 queue = new Queue2(10);
//        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        queue.add(4);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }
}
