package com.c.mashibing.suanfa.tixike;

import java.util.Arrays;

/*
 题目1，
 一条直线上有居民点，邮局只能建在居民点上。给定一个有序正数数组arr，每个值表示 居民点的一维坐标，再给定一个正数 num，表示邮局数量。选择num个居民点建立num个 邮局，使所有的居民点到最近邮局的总距离最短，返回最短的总距离
 【举例】
 arr=[1,2,3,4,5,1000]，num=2。
 第一个邮局建立在 3 位置，第二个邮局建立在 1000 位置。那么 1 位置到邮局的距离 为 2， 2 位置到邮局距离为 1，3 位置到邮局的距离为 0，4 位置到邮局的距离为 1， 5 位置到邮局的距 离为 2，1000 位置到邮局的距离为 0。这种方案下的总距离为 6， 其他任何方案的总距离都不会 比该方案的总距离更短，所以返回6

 */
public class Code43_2所有的居民点到最近邮局的总距离最短返回 {


    /*
     * @author gengzhihao
     * @date 2024/3/11 18:00
     * @description 动态规划
     * @param arr
     * @param num
     * @return int
     **/
    public static int min1(int[] arr, int num) {
        if (arr == null || arr.length == 0 || num < 1) {
            return 0;
        }

        int N = arr.length;
        //以L...R居民点范围内有一个邮局的情况下的最优距离和，作为二维数组的值
        //以L，R作为坐标
        int[][] cost = new int[N + 1][N + 1];
        //cost[0][0]到cost[N-1][N-1]这条对角线，默认为0即可。因为从位置i到位置i有1个居民点，cost为0
        for (int L = 0; L < N; L++) {
            for (int R = L + 1; R < N; R++) {
                cost[L][R] = cost[L][R - 1] + arr[R] - arr[(L + R) >> 1];
            }
        }

        //这个dp[i][j]的试法为左侧有j-1个邮局，这些邮局管了左侧居民点，管的居民点数量不定，左侧j-1个邮局管的居民点的cost之和+第J个邮局管的右侧居民点的cost，枚举出最优的
        int[][] dp = new int[N][num + 1];
        for (int i = 0; i < N; i++) {
            dp[i][1] = cost[0][i];
        }
//        for (int j = 0; j < num; j++) {
//            dp[0][j] = 0;
//        }



        for (int i = 1; i < N; i++) {
            //j代表邮局数，i代表arr[0]到arr[i]范围，其中有0~i个居民点，如果邮局数(j)>=居民点数(i+1)，那么cost之和应该为0；否则(j<i+1 or j <= i)正常枚举计算cost之和
            for (int j = 2; j <= Math.min(i, num); j++) {
                int val = Integer.MAX_VALUE;
                //枚举
                for (int left = 0; left <= i; left++) {
                    val = Math.min(val, dp[left][j - 1] + cost[left + 1][i]);
                }
                dp[i][j] = val;
            }
        }
//        System.out.println("cost: ");
//        print(cost);
//        System.out.println("dp: ");
//        print(dp);
        //返回arr 0到N-1范围内有num个邮局，最优cost之和是多少
        return dp[N - 1][num];
    }

    private static void print(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            System.out.println();
            for (int j = 0; j < dp[i].length; j++) {
                System.out.print(" " + dp[i][j]);
            }
        }
        System.out.println();
    }

    /*
     * @author gengzhihao
     * @date 2024/3/13 12:06
     * @description 基于四边形不等式优化的动态规划
     * @param arr
     * @param num
     * @return int
     **/
    public static int min2(int[] arr, int num) {
        if (arr == null || arr.length == 0 || num < 1) {
            return 0;
        }

        int N = arr.length;
        //以L...R居民点范围内有一个邮局的情况下的最优距离和，作为二维数组的值
        //以L，R作为坐标
        int[][] cost = new int[N + 1][N + 1];
        //cost[0][0]到cost[N-1][N-1]这条对角线，默认为0即可。因为从位置i到位置i有1个居民点，cost为0
        for (int L = 0; L < N; L++) {
            for (int R = L + 1; R < N; R++) {
                cost[L][R] = cost[L][R - 1] + arr[R] - arr[(L + R) >> 1];
            }
        }

        //这个dp[i][j]的试法为左侧有j-1个邮局，这些邮局管了左侧居民点，管的居民点数量不定，左侧j-1个邮局管的居民点的cost之和+第J个邮局管的右侧居民点的cost，枚举出最优的
        int[][] dp = new int[N][num + 1];
        //左侧部分j-1个邮局和第j个邮局负责范围的分割线，取j-1个邮局负责arr索引范围的右边界为分割线
        int[][] line = new int[N][num + 1];

        for (int i = 0; i < N; i++) {
            dp[i][1] = cost[0][i];
            line[i][1] = -1;

        }
//        for (int j = 0; j < num; j++){
//            dp[0][j] = 0;
//            line[0][j] = -1;
//        }


        for (int j = 2; j <= num; j++){
            //i从j+1开始，是因为居民点数i要大于邮局数j，dp值才会非0.否则为0
            for (int i = N-1; i >= j; i--){
                int val = Integer.MAX_VALUE;
                int down = line[i][j-1];
                int up = i == N - 1 ? N-1 : line[i+1][j];
                //枚举，
                for (int leftEnd = down; leftEnd <= up; leftEnd++) {
//                    System.out.println("left:" + leftEnd + "  j-1: " + j + "  line: " + line.length + "  " + line[0].length + "   dp: " + dp.length + "  " + dp[0].length);
                    int leftCost = leftEnd == -1 ? 0 : dp[leftEnd][j-1];
                    int curCost = leftEnd == N - 1 ? 0 : cost[leftEnd + 1][i];
                    int total = leftCost + curCost;
                    //<= or < ?
                    if(total <= val){
                        val = total;
                        line[i][j] = leftEnd;
                    }
                }
                dp[i][j] = val;
            }
        }
        //返回arr 0到N-1范围内有num个邮局，最优cost之和是多少
        return dp[N - 1][num];
    }


    // for test
    public static int[] randomSortedArray(int len, int range) {
        int[] arr = new int[len];
        for (int i = 0; i != len; i++) {
            arr[i] = (int) (Math.random() * range);
        }
        Arrays.sort(arr);
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int N = 30;
        int maxValue = 100;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N) + 1;
//            int len = 3;
            int[] arr = randomSortedArray(len, maxValue);
            int num = (int) (Math.random() * N) + 1;
            int ans0 = min0(arr, num);
            int ans1 = min1(arr, num);
            int ans2 = min2(arr, num);
            if (ans0 != ans2) {
                System.out.println("arr: ");
                printArray(arr);
                System.out.println("num : " + num);
                System.out.println("ans0 : " + ans0);
                System.out.println("ans2 : " + ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");

    }


    public static int min0(int[] arr, int num) {
        if (arr == null || num < 1 || arr.length < num) {
            return 0;
        }
        int N = arr.length;
        int[][] w = new int[N + 1][N + 1];
        for (int L = 0; L < N; L++) {
            for (int R = L + 1; R < N; R++) {
                w[L][R] = w[L][R - 1] + arr[R] - arr[(L + R) >> 1];
            }
        }
        int[][] dp = new int[N][num + 1];
        for (int i = 0; i < N; i++) {
            dp[i][1] = w[0][i];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 2; j <= Math.min(i, num); j++) {
                int ans = Integer.MAX_VALUE;
                for (int k = 0; k <= i; k++) {
                    ans = Math.min(ans, dp[k][j - 1] + w[k + 1][i]);
                }
                dp[i][j] = ans;
            }
        }
        return dp[N - 1][num];
    }
}
