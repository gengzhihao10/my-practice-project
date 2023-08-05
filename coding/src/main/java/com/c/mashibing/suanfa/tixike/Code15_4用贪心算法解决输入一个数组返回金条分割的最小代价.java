package com.c.mashibing.suanfa.tixike;

import com.common.utils.LogarithmicUtil;

import java.util.PriorityQueue;

/*
 题目1，一块金条切成两半，是需要花费和长度数值一样的铜板的。
    比如长度为20的金条，不管怎么切，都要花费20个铜板。 一群人想整分整块金条，怎么分最省铜板?
    例如,给定数组{10,20,30}，代表一共三个人，整块金条长度为60，金条要分成10，20，30三个部分。
    如果先把长度60的金条分成10和50，花费60; 再把长度50的金条分成20和30，花费50;一共花费110铜板。
    但如果先把长度60的金条分成30和30，花费60;再把长度30金条分成10和20， 花费30;一共花费90铜板。
    输入一个数组，返回分割的最小代价。

 */
public class Code15_4用贪心算法解决输入一个数组返回金条分割的最小代价 {


    /*
     * @author gengzhihao
     * @date 2023/8/4 10:20
     * @description 题目1
     * @param target
     * @return int
     **/
    public static int qs1_process1(int[] arr){
        if (arr == null || arr.length == 0 || arr.length == 1){
            return 0;
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for(int element : arr){
            heap.offer(element);
        }

        int newNode;
        int count = 0;
        while (heap.size() > 1){
            newNode = heap.poll() + heap.poll();
            heap.offer(newNode);
            count += newNode;
        }
//        count += heap.poll();
        return count;
    }


    // 纯暴力！
    public static int lessMoney1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process(arr, 0);
    }

    // 等待合并的数都在arr里，pre之前的合并行为产生了多少总代价
    // arr中只剩一个数字的时候，停止合并，返回最小的总代价
    public static int process(int[] arr, int pre) {
        if (arr.length == 1) {
            return pre;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                ans = Math.min(ans, process(copyAndMergeTwo(arr, i, j), pre + arr[i] + arr[j]));
            }
        }
        return ans;
    }

    public static int[] copyAndMergeTwo(int[] arr, int i, int j) {
        int[] ans = new int[arr.length - 1];
        int ansi = 0;
        for (int arri = 0; arri < arr.length; arri++) {
            if (arri != i && arri != j) {
                ans[ansi++] = arr[arri];
            }
        }
        ans[ansi] = arr[i] + arr[j];
        return ans;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 1000;
        int maxSize = 10;
        int maxValue = 10;
        for (int i = 0; i < testTime; i++) {
            System.out.println();
            System.out.println("次数"+i);
            int[] arr = generateRandomArray(maxSize, maxValue);
            System.out.println("输入数组：");
            LogarithmicUtil.printArray(arr);
            System.out.println("方法前");
            int res1 = lessMoney1(arr);
            System.out.println("方法中");
            int res2 = qs1_process1(arr);
            System.out.println("方法后");
            if (res1 != res2) {

                System.out.println("结果1：" + res1);
                System.out.println("结果2：" + res2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("finish!");
    }

}
