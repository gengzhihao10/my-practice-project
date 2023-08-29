package com.c.mashibing.suanfa.tixike;

import java.util.Stack;

/*
 题目1，给你一个栈，请你逆序这个栈，
 不能申请额外的数据结构，只能使用递归函数，如何实现
 */
public class Code18_11给你一个栈请你逆序这个栈不能申请额外的数据结构只能使用递归函数如何实现 {


    /*
     * @author gengzhihao
     * @date 2023/8/28 10:11
     * @description 题目1
     * @param stack
     **/
    public static void qs1_process1(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }

        int res = process(stack);
        qs1_process1(stack);
        stack.push(res);
    }

    //将栈底元素弹出来，上面的元素依次掉到栈底
    private static int process(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        }
        int bottom = process(stack);
        stack.push(result);
        return bottom;
    }
}
