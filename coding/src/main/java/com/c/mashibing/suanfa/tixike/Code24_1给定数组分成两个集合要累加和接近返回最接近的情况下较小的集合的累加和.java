package com.c.mashibing.suanfa.tixike;

/*
 题目1，
 给定一个正数数组arr，
 请把arr中所有的数分成两个集合，尽量让两个集合的累加和接近
 返回：
 最接近的情况下，较小集合的累加和
 */
public class  Code24_1给定数组分成两个集合要累加和接近返回最接近的情况下较小的集合的累加和 {


    /*
     * @author gengzhihao
     * @date 2023/9/21 9:40
     * @description 暴力递归
     * @param arr
     * @return int
     **/
    public static int right0(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++){
            sum += arr[i];
        }
        sum /= 2;

        return process0(arr,0,sum);
    }


    //对于数组arr当前索引Index，向右走到头，能凑到的最接近rest的数是多少
    private static int process0(int[] arr, int index, int rest) {
        if (index == arr.length){
            return 0;
        }

        int p1 = process0(arr,index+1,rest);
        int p2 = 0;
        if (rest-arr[index] >= 0){
            p2 = arr[index] + process0(arr,index+1,rest-arr[index]);

        }
        return Math.max(p1,p2);
    }


    //*******************************************************************************


    /*
     * @author gengzhihao
     * @date 2023/9/22 10:19
     * @description 题目1
     * @param arr
     * @return int
     **/
    public static int right1(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++){
            sum += arr[i];
        }
        sum /= 2;

        int N = arr.length;
        int[][] dp = new int[N+1][sum+1];

        for (int index = N-1; index >= 0; index--){
            for (int rest = 0; rest <= sum; rest++){
                int p1 = dp[index+1][rest];
                int p2 = 0;
                if (rest-arr[index] >= 0){
                    p2 = arr[index] + dp[index+1][rest-arr[index]];

                }
                dp[index][rest] = Math.max(p1,p2);
            }
        }

        return dp[0][sum];
    }






    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 50;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
            int ans = right(arr);
            int ans0 = right1(arr);
            if (ans != ans0) {
                printArray(arr);
                System.out.println(ans);
                System.out.println(ans0);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }

    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static int right(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return process(arr, 0, sum / 2);
    }

    // arr[i...]可以自由选择，请返回累加和尽量接近rest，但不能超过rest的情况下，最接近的累加和是多少？
    public static int process(int[] arr, int i, int rest) {
        if (i == arr.length) {
            return 0;
        } else { // 还有数，arr[i]这个数
            // 可能性1，不使用arr[i]
            int p1 = process(arr, i + 1, rest);
            // 可能性2，要使用arr[i]
            int p2 = 0;
            if (arr[i] <= rest) {
                p2 = arr[i] + process(arr, i + 1, rest - arr[i]);
            }
            return Math.max(p1, p2);
        }
    }
}
