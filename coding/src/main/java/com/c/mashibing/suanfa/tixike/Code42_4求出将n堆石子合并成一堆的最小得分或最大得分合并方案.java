package com.c.mashibing.suanfa.tixike;

/*
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
        if (arr == null || arr.length == 0){
            return 0;
        }

        //preSum[0]:0, preSum[1]:arr[0], preSum[2]:arr[0]、arr[1]
        int[] preSum = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++){
            preSum[i + 1] = preSum[i] + arr[i];
        }

        return process1(preSum,0,arr.length - 1);
    }

    //返回arr数组从其索引start到end对应的数值的合并石子问题的得分
    private static int process1(int[] preSum, int L, int R) {
        if (L == R){
            return 0;
        }

        //计算min的时候，变量初始值最好是最大值，这样不影响值的判断。
        // 如果初始值是0，那么在只有正数的情况下，无法正确得到结果。
        int next = Integer.MAX_VALUE;
        for (int i = L; i < R; i++){
            next = Math.min(next, process1(preSum,L,i) + process1(preSum,i+1,R));
        }

        return next + getSum(preSum,L,R);
    }

    private static int getSum(int[] preSum, int L, int R) {
        return preSum[R + 1] - preSum[L];
    }


    /*
     * @author gengzhihao
     * @date 2024/2/20 11:16
     * @description 动态规划
     * @param arr
     * @return int
     **/
    public static int min2(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }

        //preSum[0]:0, preSum[1]:arr[0], preSum[2]:arr[0]、arr[1]
        int[] preSum = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++){
            preSum[i + 1] = preSum[i] + arr[i];
        }

        int N = arr.length;
        int[][] dp = new int[N][N];
        for (int l = N - 2; l >= 0; l-- ){
            for (int r = l + 1; r <= N - 1; r++){
                int next = Integer.MAX_VALUE;
                for (int i = l; i < r; i++){
                    next = Math.min(next, dp[l][i] + dp[i+1][r]);
                }
                dp[l][r] = next + getSum(preSum,l,r);
            }
        }
        return dp[0][N-1];
    }

    /*
     * @author gengzhihao
     * @date 2024/2/20 11:16
     * @description 基于四边形不等式优化的动态规划
     * @param arr
     * @return int
     **/
    public static int min3(int[] arr){

        if (arr == null || arr.length == 0){
            return 0;
        }

        //preSum[0]:0, preSum[1]:arr[0], preSum[2]:arr[0]、arr[1]
        int[] preSum = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++){
            preSum[i + 1] = preSum[i] + arr[i];
        }

        int N = arr.length;
        int[][] dp = new int[N][N];
        int[][] best = new int[N][N];

        for (int i = 0; i < N - 1; i++){
            best[i][i + 1] = i;
            dp[i][i + 1] = getSum(preSum,i,i+1);
        }

        for (int l = N - 3; l >= 0; l-- ){
            for (int r = l + 2; r <= N - 1; r++){
                int next = Integer.MAX_VALUE;
                int line = 0;
                for (int i = best[l][r-1]; i <= best[l+1][r]; i++){
                    int cur = dp[l][i] + dp[i+1][r];
                    if (cur <= next){
                        next = cur;
                        line = i;
                    }
                }
                best[l][r] = line;
                dp[l][r] = next + getSum(preSum,l,r);
            }
        }
        return dp[0][N-1];
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
//            int[] arr = {1,2,4};
            int ans0 = min0(arr);
            int ans1 = min1(arr);
            int ans2 = min2(arr);
            int ans3 = min3(arr);
            if (ans0 != ans3) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println("ans0: " + ans0);
                System.out.println("ans3: " + ans3);
            }
        }
        System.out.println("测试结束");
    }

    public static void printArray(int[] arr){
        for (int i = 0; i<arr.length; i++){
            System.out.print(arr[i]+"  ");
        }
        System.out.println();
    }

    //******************************************************************************************

    public static int min0(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int N = arr.length;
        int[] s = sum0(arr);
        return process0(0, N - 1, s);
    }

    public static int[] sum0(int[] arr) {
        int N = arr.length;
        int[] s = new int[N + 1];
        s[0] = 0;
        for (int i = 0; i < N; i++) {
            s[i + 1] = s[i] + arr[i];
        }
        return s;
    }


    public static int process0(int L, int R, int[] s) {
        if (L == R) {
            return 0;
        }
        int next = Integer.MAX_VALUE;
        for (int leftEnd = L; leftEnd < R; leftEnd++) {
            next = Math.min(next, process0(L, leftEnd, s) + process0(leftEnd + 1, R, s));
        }
        return next + w0(s, L, R);
    }

    public static int w0(int[] s, int l, int r) {
        return s[r + 1] - s[l];
    }

}
