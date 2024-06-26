package com.c.mashibing.suanfa.tixike;

/*
 题目1，
 给定一个非负数组arr，长度为N，
 那么有N-1种方案可以把arr切成左右两部分
 每一种方案都有，min{左部分累加和，右部分累加和}
 求这么多方案中，min{左部分累加和，右部分累加和}的最大值是多少？
 整个过程要求时间复杂度O(N)
 */
public class Code42_1求这么多方案中min左部分累加和右部分累加和的最大值是多少 {


    /*
     * @author gengzhihao
     * @date 2024/2/20 10:42
     * @description 题目1
     * @param arr
     * @return int
     **/
    public static int bestSplit2(int[] arr){
        if (arr == null || arr.length <= 1){
            return 0;
        }

        int ans = 0;
        int sumAll = 0;
        //计算数组累加和
        for (int i = 0; i < arr.length; i++){
            sumAll += arr[i];
        }
        int sumLeft = 0;
        int sumRight = 0;
        for (int i = 0; i < arr.length; i++){
            sumLeft += arr[i];
            sumRight = sumAll - sumLeft;
            ans = Math.max(ans, Math.min(sumLeft, sumRight));
        }
        return ans;
    }


    /*
     * @author gengzhihao
     * @date 2024/2/20 10:43
     * @description 暴力解
     * @param arr
     * @return int
     **/
    public static int bestSplit1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int N = arr.length;
        int ans = 0;
        for (int s = 0; s < N - 1; s++) {
            int sumL = 0;
            for (int L = 0; L <= s; L++) {
                sumL += arr[L];
            }
            int sumR = 0;
            for (int R = s + 1; R < N; R++) {
                sumR += arr[R];
            }
            ans = Math.max(ans, Math.min(sumL, sumR));
        }
        return ans;
    }


    public static int[] randomArray(int len, int max) {
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = (int) (Math.random() * max);
        }
        return ans;
    }

    public static void main(String[] args) {
        int N = 20;
        int max = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N);
            int[] arr = randomArray(len, max);
            int ans1 = bestSplit1(arr);
            int ans2 = bestSplit2(arr);
            if (ans1 != ans2) {
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }
}
