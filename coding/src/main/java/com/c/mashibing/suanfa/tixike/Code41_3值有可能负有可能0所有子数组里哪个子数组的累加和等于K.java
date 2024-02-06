package com.c.mashibing.suanfa.tixike;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/*
 题目1，
 给定一个整数组成的无序数组arr，值可能正、可能负、可能0
 给定一个整数值K
 找到arr的所有子数组里，哪个子数组的累加和等于K，并且是长度最大的
 返回其长度

 */
public class Code41_3值有可能负有可能0所有子数组里哪个子数组的累加和等于K {


    /*
     * @author gengzhihao
     * @date 2024/2/2 11:21
     * @description 题目1
     * @param arr
     * @param k
     * @return int
     **/
    public static int maxLength(int[] arr, int k){
        if (arr == null || arr.length == 0){
            return 0;
        }

        TreeMap<Integer,Integer> map = new TreeMap<>();
        int pre = 0;
        map.put(pre,-1);
        int ans = 0;
        for (int i = 0; i < arr.length; i++){
            //此时Pre表示arr[0]到arr[i]位置的和
            pre += arr[i];
            //左边无用子数组和为pre - k，右边和为K的子数组，共同组成了index从0到i，和为Pre的数组
            if (map.containsKey(pre - k)){
                ans = Math.max(ans, i - map.get(pre - k));
            }
            //两个前缀和数组的和一样，要留下前缀和数组右边界更短的，因为这样留给满足条件子数组的长度就能更长
            if (!map.containsKey(pre)){
                map.put(pre,i);
            }
        }

        return ans;
    }



    // for test
    public static int right(int[] arr, int K) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (valid(arr, i, j, K)) {
                    max = Math.max(max, j - i + 1);
                }
            }
        }
        return max;
    }

    // for test
    public static boolean valid(int[] arr, int L, int R, int K) {
        int sum = 0;
        for (int i = L; i <= R; i++) {
            sum += arr[i];
        }
        return sum == K;
    }

    // for test
    public static int[] generateRandomArray(int size, int value) {
        int[] ans = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * value) - (int) (Math.random() * value);
        }
        return ans;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 50;
        int value = 100;
        int testTime = 500000;

        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(len, value);
            int K = (int) (Math.random() * value) - (int) (Math.random() * value);
            int ans1 = maxLength(arr, K);
            int ans2 = right(arr, K);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println("K : " + K);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("test end");

    }
}
