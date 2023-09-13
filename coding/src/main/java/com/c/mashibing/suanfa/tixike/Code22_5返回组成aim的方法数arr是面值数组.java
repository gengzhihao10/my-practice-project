package com.c.mashibing.suanfa.tixike;

/*
 题目1，
 arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 每个值都认为是一种面值，且认为张数是无限的。
 返回组成aim的方法数
 例如：arr = {1,2}，aim = 4
 方法如下：1+1+1+1、1+1+2、2+2
 一共就3种方法，所以返回3
 动态规划
 题目2，使用动态转移优化题目1
 */
public class Code22_5返回组成aim的方法数arr是面值数组 {



    /*
     * @author gengzhihao
     * @date 2023/9/13 9:16
     * @description 暴力递归
     * @param arr
     * @param aim
     * @return int
     **/
    public static int coinsWay0(int[] arr, int aim) {
        if (arr == null || arr.length == 0){
            return aim == 0 ? 1 : 0;
        }

        return process0(arr,aim,0);
    }

    private static int process0(int[] arr, int rest, int index) {
        if (index == arr.length){
            return rest == 0 ? 1 : 0;
        }

        int sum = 0;
        for (int i = rest; i >= 0 ; i -= arr[index]){
            sum += process0(arr, i,index+1);
        }

        return sum;
    }

    //**************************************************************************************************


    /*
     * @author gengzhihao
     * @date 2023/9/13 9:30
     * @description 题目1
     * @param arr
     * @param aim
     * @return int
     **/
    public static int coinsWay1(int[] arr, int aim){
        if (arr == null || arr.length == 0){
            return aim == 0 ? 1 : 0;
        }


        int N = arr.length;
        int[][] dp = new int[aim+1][N+1];

        dp[0][N] = 1;

        for (int index = N-1; index >= 0; index--){
            for (int rest = 0; rest <= aim; rest++){
                int sum = 0;
                for (int i = rest; i >= 0 ; i -= arr[index] ){
                    sum += dp[i][index+1];
                }
                dp[rest][index] = sum;
            }
        }

        return dp[aim][0];
    }


    //***********************************************************************************************


    /*
     * @author gengzhihao
     * @date 2023/9/13 9:41
     * @description 题目2
     * @param arr
     * @param aim
     * @return int
     **/
    public static int coinsWay2(int[] arr, int aim){
        if (arr == null || arr.length == 0){
            return aim == 0 ? 1 : 0;
        }


        int N = arr.length;
        int[][] dp = new int[aim+1][N+1];

        dp[0][N] = 1;

        for (int index = N-1; index >= 0; index--){
            for (int rest = 0; rest <= aim; rest++){
                int i = rest-arr[index];
                //nnd，差个括号也会出问题，
                // 根据Java运算符优先级，加法大于三目，所以如果没有括号，会先计算dp[i][index] + dp[rest][index+1]，再计算三目
                dp[rest][index] = (i < 0 ? 0 : dp[i][index]) + dp[rest][index+1];
            }
        }

//        print(dp);
        return dp[aim][0];
    }


    //***************************************************************************************


    // 为了测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        boolean[] has = new boolean[maxValue + 1];
        for (int i = 0; i < N; i++) {
            do {
                arr[i] = (int) (Math.random() * maxValue) + 1;
            } while (has[arr[i]]);
            has[arr[i]] = true;
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
    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = test(arr, aim);
            int ans2 = coinsWay2(arr, aim);
//            int ans3 = dp2(arr, aim);
            if (ans1 != ans2 ) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
//                System.out.println(ans3);
                break;
            }
        }
        System.out.println("测试结束");
    }

    public static int test(int[] arr, int aim) {
        if (arr == null || arr.length == 0){
            return aim == 0 ? 1 : 0;
        }
        return process(arr, 0, aim);
    }

    // arr[index....] 所有的面值，每一个面值都可以任意选择张数，组成正好rest这么多钱，方法数多少？
    public static int process(int[] arr, int index, int rest) {
        if (index == arr.length) { // 没钱了
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
            ways += process(arr, index + 1, rest - (zhang * arr[index]));
        }
        return ways;
    }
}
