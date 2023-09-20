package com.c.mashibing.suanfa.tixike;

/*
 题目1，
 给定一个正数n，求n的裂开方法数，
 规定：后面的数不能比前面的数小
 比如4的裂开方法有：
 1+1+1+1、1+1+2、1+3、2+2、4
 5种，所以返回5

 */
public class Code23_9返回裂开的数的种类 {


    /*
     * @author gengzhihao
     * @date 2023/9/19 9:51
     * @description 暴力递归
     * @param n
     * @return int
     **/
    public static int ways0(int n){
        if (n < 0){
            return 0;
        }
        if (n == 0){
            return 1;
        }
        //n从1开始裂开的方法数
        return process0(1,n);
    }

    //pre为前一个裂开的数，再次裂开不能小于pre；cur是前一次裂开后剩下的数，再次裂开不能大于cur
    //返回cur至少从pre开始裂开的总共的方法数
    private static int process0(int pre, int cur) {
        if (pre > cur){
            return cur == 0 ? 1 : 0;
        }
        if (pre == cur){
            return 1;
        }

        int res = 0;
        for (int i = pre; i <= cur; i++){
            res += process0(i,cur-i);
        }

        return res;
    }

    //******************************************************************


    /*
     * @author gengzhihao
     * @date 2023/9/20 10:58
     * @description 题目1
     * @param n
     * @return int
     **/
    public static int ways1(int n){
        if (n < 0){
            return 0;
        }
        if (n == 0){
            return 1;
        }

        int[][] dp = new int[n+1][n+1];

        for (int pre = 1; pre <= n; pre++){
            dp[pre][0] =1;
            dp[pre][pre] = 1;
        }
        dp[0][0] = 1;

        for (int pre = n; pre >= 1; pre--){
            for (int cur = pre + 1; cur <= n; cur++){
                for (int i = pre; i <= cur; i++){
                    dp[pre][cur] += dp[i][cur-i];
                }
            }
        }

        return dp[1][n];
    }



    /*
     * @author gengzhihao
     * @date 2023/9/20 11:24
     * @description 题目2
     * @param n
     * @return int
     **/
    public static int ways2(int n){
        if (n < 0){
            return 0;
        }
        if (n == 0){
            return 1;
        }

        int[][] dp = new int[n+1][n+1];

        for (int pre = 1; pre <= n; pre++){
            dp[pre][0] =1;
            dp[pre][pre] = 1;
        }
        dp[0][0] = 1;

        for (int pre = n; pre >= 1; pre--){
            for (int cur = pre + 1; cur <= n; cur++){
                dp[pre][cur] = dp[pre][cur-pre] + dp[pre + 1][cur];
            }
        }

        return dp[1][n];
    }

//************************************************************************************************************

    public static void main(String[] args) {
        int times = 100;
        int maxInput = 30;
        for (int i = 0; i < times; i++){
            int input = (int)(Math.random() * maxInput);
            int res1 = ways(input);
            int res2 = ways2(input);
            if (res1 != res2){
                System.out.println("oops!");
                System.out.println("input : " + input);
                System.out.println("res1 : " + res1);
                System.out.println("res2 : " + res2);
                break;
            }
        }
    }

    // n为正数
    public static int ways(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return process(1, n);
    }

    // 上一个拆出来的数是pre
    // 还剩rest需要去拆
    // 返回拆解的方法数
    public static int process(int pre, int rest) {
        if (rest == 0) {
            return 1;
        }
        if (pre > rest) {
            return 0;
        }
        int ways = 0;
        for (int first = pre; first <= rest; first++) {
            ways += process(first, rest - first);
        }
        return ways;
    }

}
