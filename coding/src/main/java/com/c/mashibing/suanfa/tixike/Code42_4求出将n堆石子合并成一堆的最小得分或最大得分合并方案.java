package com.c.mashibing.suanfa.tixike;

/*
todo
 题目1，
 摆放着n堆石子。现要将石子有次序地合并成一堆
 规定每次只能选相邻的2堆石子合并成新的一堆，
 并将新的一堆石子数记为该次合并的得分
 求出将n堆石子合并成一堆的最小得分（或最大得分）合并方案
 */
public class Code42_4求出将n堆石子合并成一堆的最小得分或最大得分合并方案 {

    /*
     * @author gengzhihao
     * @date 2024/2/20 11:15
     * @description 暴力递归
     * @param arr
     * @return int
     **/
    public static int min1(int[] arr){
        return 0;
    }


    /*
     * @author gengzhihao
     * @date 2024/2/20 11:16
     * @description 动态规划
     * @param arr
     * @return int
     **/
    public static int min2(int[] arr){
        return 0;
    }

    /*
     * @author gengzhihao
     * @date 2024/2/20 11:16
     * @description 基于四边形不等式优化的动态规划
     * @param arr
     * @return int
     **/
    public static int min3(int[] arr){
        return 0;
    }

    public static int[] randomArray(int len, int maxValue) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * maxValue);
        }
        return arr;
    }

    public static void main(String[] args) {
        int N = 15;
        int maxValue = 100;
        int testTime = 1000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N);
            int[] arr = randomArray(len, maxValue);
            int ans1 = min1(arr);
            int ans2 = min2(arr);
            int ans3 = min3(arr);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }

}
