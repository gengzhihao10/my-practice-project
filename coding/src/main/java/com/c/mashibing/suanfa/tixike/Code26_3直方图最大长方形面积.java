package com.c.mashibing.suanfa.tixike;

import java.util.Stack;

/*
 题目1.
 给定一个非负数组arr，代表直方图
 返回直方图的最大长方形面积
 测试链接：https://leetcode.com/problems/largest-rectangle-in-histogram

 */
public class Code26_3直方图最大长方形面积 {

    /*
     * @author gengzhihao
     * @date 2023/11/8 10:53
     * @description 题目1
     * @param height
     * @return int
     **/
    public static int largestRectangleArea(int[] height){
        if (height == null || height.length == 0){
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        int max = 0;
        for (int i = 0; i < height.length; i++){
            while (!stack.isEmpty() && height[stack.peek()] >= height[i]){
                int popIndex = stack.pop();
                //                    0->2          * ( (1 - 1) -  (-1))
                max = Math.max(max,height[popIndex] * ((i - 1) - (stack.isEmpty() ? -1 : stack.peek())));
            }
            stack.push(i);
        }

        while (!stack.isEmpty()){
            int popIndex = stack.pop();
            max = Math.max(max,height[popIndex] * ((height.length - 1) - (stack.isEmpty() ? -1 : stack.peek())));
        }
        return max;
    }

    public static void main(String[] args) {
        int[] height = {2,1,3,2};
        System.out.println(largestRectangleArea(height));

    }

}
