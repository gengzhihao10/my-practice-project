package com.c.mashibing.suanfa.tixike;

/*
todo
 题目1，
 arr是货币数组，其中的值都是正数。再给定一个正数aim。
 每个值都认为是一张货币，
 即便是值相同的货币也认为每一张都是不同的，
 返回组成aim的方法数
 例如：arr = {1,1,1}，aim = 2
 第0个和第1个能组成2，第1个和第2个能组成2，第0个和第2个能组成2
 一共就3种方法，所以返回3
 使用动态规划解题
 题目2，使用动态转移优化题目1
 */
public class Code22_4返回组成aim的方法数arr是货币数组 {


    /*
     * @author gengzhihao
     * @date 2023/9/12 20:02
     * @description 暴力递归
     * @param arr
     * @param aim
     * @return int
     **/
    public static int coinWays0(int[] arr, int aim){
        if (arr == null || arr.length == 0 || aim <= 0) {
            return -1;
        }

        return process0(arr,aim,0);
    }

    //从arr数组的index位置出发到结束，正好组成rest，有多少种可能性，将其返回
    private static int process0(int[] arr, int rest, int index) {
        if (rest < 0){
            return 0;
        }

        if (index == arr.length){
            return rest == 0 ? 1 : 0;
        }

        int p1 = process0(arr,rest-arr[index],index+1);
        int p2 = process0(arr,rest,index+1);

        return p1+p2;
    }

    //********************************************************************************************

    /*
     * @author gengzhihao
     * @date 2023/9/11 12:05
     * @description 题目1
     * @param arr
     * @param aim
     * @return int
     **/
    public static int coinWays1(int[] arr, int aim){
        if (arr == null || arr.length == 0){
            return aim == 0 ? 1 : 0;
        }

        int N = arr.length;
        int[][] dp = new int[aim+1][N+1];

        dp[0][N] = 1;

        //注意变量不要用错，细节要注意，脑子不要迷糊
        for (int index = N-1; index >= 0; index--){
            for (int rest = 0; rest <= aim; rest++){
                int temp = rest-arr[index];
                int p1 = temp < 0 ? 0 : dp[temp][index+1];
                int p2 = dp[rest][index+1];
                dp[rest][index] = p1 + p2;
            }
        }

//        print(dp);

        return dp[aim][0];
    }


    //****************************************************************************************************************


    /*
     * @author gengzhihao
     * @date 2023/9/11 12:05
     * @description 题目2
     * @param arr
     * @param aim
     * @return int
     **/
    public static int coinWays2(int[] arr, int aim){
        return 0;
    }



    //****************************************************************************************************************

    private static void print(int[][] arr){
        System.out.println("打印二维数组");
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr[i].length; j++){
                System.out.print("  " + arr[i][j]);
            }
            System.out.println();
        }
        System.out.println("打印结束");
    }

    // 为了测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    // 为了测试
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 为了测试
    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = dp(arr, aim);
            int ans2 = coinWays1(arr, aim);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }


    public static int dp(int[] arr, int aim) {
        if (aim == 0) {
            return 1;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest] + (rest - arr[index] >= 0 ? dp[index + 1][rest - arr[index]] : 0);
            }
        }
//        print(dp);
        return dp[0][aim];
    }
}
