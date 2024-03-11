package com.c.mashibing.suanfa.tixike;

import java.util.Arrays;

/*
todo
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
     * @description
     * @param arr
     * @param num
     * @return int
     **/
    public static int min1(int[] arr, int num){
        return 0;
    }

    public static int min2(int[] arr, int num){
        return 0;
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
            int[] arr = randomSortedArray(len, maxValue);
            int num = (int) (Math.random() * N) + 1;
            int ans1 = min1(arr, num);
            int ans2 = min2(arr, num);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println(num);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");

    }
}
