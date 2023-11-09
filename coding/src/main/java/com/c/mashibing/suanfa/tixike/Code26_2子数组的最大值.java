package com.c.mashibing.suanfa.tixike;

import com.common.utils.LogarithmicUtil;

import java.util.LinkedList;
import java.util.Stack;

/*
 题目1，
 给定一个只包含正数的数组arr，arr中任何一个子数组sub，
 一定都可以算出(sub累加和 )* (sub中的最小值)是什么，
 那么所有子数组中，这个值最大是多少？
 要求子数组必须是连续的

 */
public class Code26_2子数组的最大值 {

    /*
     * @author gengzhihao
     * @date 2023/11/8 9:50
     * @description 题目1
     * @param arr
     * @return int
     **/
    public static int max2(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }

        //累加和需要借助前缀和数组
        int[] preSum = new int[arr.length];
        preSum[0] = arr[0];
        for (int i = 1; i < arr.length; i++){
            preSum[i] = preSum[i-1] + arr[i];
        }

        Stack<Integer> stack = new Stack<>();
        int max = 0;
        for (int i = 0; i < arr.length; i++){
            //等于的时候，也要结算。所有等于的数的结算结果中，只有最后一个相等的数的结算结果是最大的，也只有这个数的结算结果是正确的。
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]){
                int popIndex = stack.pop();
                max = Math.max(max,arr[popIndex] * (preSum[i - 1] - (stack.isEmpty() ? 0 : preSum[stack.peek()])));
            }
            stack.push(i);
        }

        while (!stack.isEmpty()){
            int popIndex = stack.pop();
            max = Math.max(max,arr[popIndex] * (preSum[arr.length - 1] - (stack.isEmpty() ? 0 : preSum[stack.peek()])));
        }
        return max;
    }


    public static int[] gerenareRondomArray() {
        int[] arr = new int[(int) (Math.random() * 30) + 10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 101);
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTimes = 2000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = gerenareRondomArray();
//            int[] arr = {7,5};
            int res1 = max1(arr);
            int res2 = max2(arr);
            if (res1 != res2) {
                System.out.println("FUCK!");
                LogarithmicUtil.printArray(arr);
                System.out.println(res1);
                System.out.println(res2);
                break;
            }
        }
        System.out.println("test finish");
    }

    public static int max1(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int minNum = Integer.MAX_VALUE;
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                    minNum = Math.min(minNum, arr[k]);
                }
                max = Math.max(max, minNum * sum);
            }
        }
        return max;
    }

}
