package com.c.mashibing.suanfa.tixike;

import java.util.Stack;

/**
 *  题目1，实现一个特殊的栈，在基本功能的基础上，再实现返回栈中最小元素的功能
 *  pop push getMin操作的时间复杂度都是O(1)
 *  设计的栈类型可以使用现成的栈结构
 */
public class Code4_3栈和队列常见的面试题一 {


    /**
     * 题目1
     */
    public static class MinStack{
        private Stack<Integer> basicStack = new Stack<>();
        private Stack<Integer> minStack = new Stack<>();
        private int size = 0;

        public void add(int value){
            basicStack.push(value);
            //minStack为空
            if (minStack.isEmpty()){
                minStack.push(value);
            }
            //minStack不为空
            else {
                //value大于最小值，向minStack压入minStack顶端node
                if (value >= minStack.peek()){
                    minStack.push(minStack.peek());
                }
                //value小于最小值，向minStack压入value
                else {
                    minStack.push(value);
                }
            }
            size++;
        }


        public int poll(){
            if (size == 0){
                throw new RuntimeException("栈空了！");
            }
            minStack.pop();
            return basicStack.pop();
        }

        public int min(){
            return minStack.peek();
        }
    }


    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.add(4);
        minStack.add(5);
        System.out.println(minStack.min());
        minStack.add(3);
        System.out.println(minStack.min());
        minStack.add(6);
        System.out.println(minStack.min());
        System.out.println("----------------");
        System.out.println(minStack.min());
        System.out.println(minStack.poll());

        System.out.println(minStack.min());
        System.out.println(minStack.poll());

        System.out.println(minStack.min());
        System.out.println(minStack.poll());

        System.out.println(minStack.min());
        System.out.println(minStack.poll());



    }
}
