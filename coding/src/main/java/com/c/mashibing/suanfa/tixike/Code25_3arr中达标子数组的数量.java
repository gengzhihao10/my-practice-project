package com.c.mashibing.suanfa.tixike;

import java.util.LinkedList;

/*
 题目1，给定一个整形数组arr,和一个整数num，
 某个arr中的子数组sub，如果想达标，必须满足：
 sub中最大值-sub中最小值<=num
 返回arr中达标子数组的数量
 */
public class Code25_3arr中达标子数组的数量 {

    /*
     * @author gengzhihao
     * @date 2023/10/31 14:59
     * @description 题目1
     * @param arr
     * @param sum
     * @return int
     **/
    private static int num(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0){
            return 0;
        }

        LinkedList<Integer> maxWindow = new LinkedList<>();
        LinkedList<Integer> minWindow = new LinkedList<>();

        int total = 0;
        int R = 0;
        for (int L = 0; L < arr.length; L++){
            //R向右移动，直到不符合要求为止。也就是更新maxWindow minWindow右侧的数据
            while (R < arr.length){
                while (!maxWindow.isEmpty() && arr[maxWindow.peekLast()] <= arr[R]){
                    maxWindow.pollLast();
                }
                maxWindow.offerLast(R);
                while (!minWindow.isEmpty() && arr[minWindow.peekLast()] >= arr[R]){
                    minWindow.pollLast();
                }
                minWindow.offerLast(R);

                if (arr[maxWindow.peekFirst()] - arr[minWindow.peekFirst()] <= sum){
                    R++;
                }else {
                    break;
                }
            }

            total += (R - L);

            //更新maxWindow minWindow左侧的数据，防止在下一次循环中过期
            if (maxWindow.peekFirst() <= L){
                maxWindow.pollFirst();
            }
            if (minWindow.peekFirst() <= L){
                minWindow.pollFirst();
            }
        }
        return total;
    }

    // 暴力的对数器方法
    public static int right(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }
        int N = arr.length;
        int count = 0;
        for (int L = 0; L < N; L++) {
            for (int R = L; R < N; R++) {
                int max = arr[L];
                int min = arr[L];
                for (int i = L + 1; i <= R; i++) {
                    max = Math.max(max, arr[i]);
                    min = Math.min(min, arr[i]);
                }
                if (max - min <= sum) {
                    count++;
                }
            }
        }
        return count;
    }

    // for test
    public static int[] generateRandomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * (maxLen + 1));
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 200;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxLen, maxValue);
            int sum = (int) (Math.random() * (maxValue + 1));
            int ans1 = right(arr, sum);
            int ans2 = num(arr, sum);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println("sum: " + sum);
                System.out.println("ans1: " + ans1);
                System.out.println("ans2: " + ans2);
                break;
            }
        }
        System.out.println("测试结束");

    }


}
