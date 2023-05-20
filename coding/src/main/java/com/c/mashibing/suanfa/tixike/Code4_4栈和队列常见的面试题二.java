package com.c.mashibing.suanfa.tixike;

import java.util.*;

/**
 *  题目1，如何用栈结构实现队列结构
 *  题目2，如何用队列结构实现栈结构
 */
public class Code4_4栈和队列常见的面试题二 {

    /**
     * 题目1
     */
    public static class Queue1{

        private Stack<Integer> stack1 = new Stack<>();
        private Stack<Integer> stack2 = new Stack<>();

        public void add(int value){
            stack1.push(value);
        }

        public int poll(){
            if (stack2.isEmpty()){
                exchangeValue(stack1,stack2);
            }
            return stack2.pop();

        }

        //把1中的数据，交换到空的2中
        private void exchangeValue(Stack<Integer> stack1, Stack<Integer> stack2) {
            int length = stack1.size();
            for (int i = 0; i< length;i++){
                stack2.push(stack1.pop());
            }
        }

    }

    /**
     * 题目2
     */
    public static class Stack1{

        private Queue<Integer> queue1 = new LinkedList();
        private Queue<Integer> queue2 = new LinkedList();

        public void add(int value){
            queue1.offer(value);
        }

        public int remove(){
            if (queue1.isEmpty()){
                throw new RuntimeException("栈为空");
            }
            exportValue();
            int ans = queue1.poll();
            recoverValue();
            return ans;
        }

        //将1中的数值导出到2中，仅保留1中最后一个元素
        private void exportValue() {
            while (!queue1.isEmpty()){
                if (queue1.size() == 1){
                    break;
                }
                queue2.offer(queue1.poll());
            }
        }

        //将2中的数据恢复至1中
        private void recoverValue() {
            while (!queue2.isEmpty()){
                queue1.offer(queue2.poll());
            }
        }


    }


    public static void main(String[] args) {
//        Queue queue = new LinkedList<>();
//        queue.offer()
//
//        Stack stack = new Stack();
//        stack.push(1);
//        stack.add(1);
//
//        List list = new ArrayList<>();
//
//        //集合
//        list.add(1);
//        list.add(2);
//        list.add(3);
//
//        //Iterator迭代器
//        //1、获取迭代器
//        Iterator iter = list.iterator();
//        //2、通过循环迭代
//        //hasNext():判断是否存在下一个元素
//        while(iter.hasNext()){
//            //如果存在，则调用next实现迭代
//            //Object-->Integer-->int
//            int j=(int)iter.next();  //把Object型强转成int型
//            System.out.println(j);
//        }

//        Stack1 stack1 = new Stack1();
//        stack1.add(1);
//        stack1.add(2);
//        stack1.add(3);
//        System.out.println(stack1.remove());
//        stack1.add(4);
//        stack1.add(5);
//        System.out.println(stack1.remove());
//        System.out.println(stack1.remove());
//        System.out.println(stack1.remove());
//        stack1.add(6);
//        System.out.println(stack1.remove());
//        System.out.println(stack1.remove());


        Queue1 queue1 = new Queue1();
        queue1.add(1);
        queue1.add(2);
        queue1.add(3);
        System.out.println(queue1.poll());
        queue1.add(4);
        queue1.add(5);
        System.out.println(queue1.poll());
        queue1.add(6);
        System.out.println(queue1.poll());
        System.out.println(queue1.poll());
        System.out.println(queue1.poll());
        System.out.println(queue1.poll());




    }
}
