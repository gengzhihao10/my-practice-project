package com.c.mashibing.suanfa.tixike;

/*
 题目1，
 给定3个参数，N，M，K
 怪兽有N滴血，等着英雄来砍自己
 英雄每一次打击，都会让怪兽流失[0~M]的血量
 到底流失多少？每一次在[0~M]上等概率的获得一个值
 求K次打击之后，英雄把怪兽砍死的概率
 动态规划，剪枝
 题目2，同题目1，基于动态转移的优化
 */
public class Code23_1返回k次打击后英雄把怪兽砍死的几率 {



    /*
     * @author gengzhihao
     * @date 2023/9/15 9:25
     * @description 暴力递归，不剪枝
     * @param N 怪兽初始血量为N
     * @param M 伤害范围为0~M
     * @param K 有K次伤害
     * @return double K次伤害后，怪兽死亡的概率
     **/
    public static double right0(int N, int M, int K){
        if ( N <= 0 || M <= 0 || K <= 0){
            return 0;
        }
        return process0(K,N,M) /  Math.pow(M+1,K);
    }


    private static double process0(int rest, int hp, int M) {
        //base case
        if (rest == 0){
            return (hp <= 0) ? 1 : 0;
        }
        //剪枝
        if (hp <= 0){
            return Math.pow(M+1,rest);
        }

        int sum = 0;
        for (int i = 0; i <= M; i++){
            sum += process0(rest-1,hp-i,M);
        }

        return sum;
    }


    //*********************************************************************************************

    /*
     * @author gengzhihao
     * @date 2023/9/16 9:29
     * @description 题目1
     * @param N
     * @param M
     * @param K
     * @return double
     **/
    public static double right1(int N, int M, int K){
        if ( N <= 0 || M <= 0 || K <= 0){
            return 0;
        }

        int[][] dp = new int[K+1][N+1];

        dp[0][0] = 1;

        for (int rest = 1; rest <= K; rest++){
            for (int hp = 0; hp <= N; hp++){
                int sum = 0;
                for (int i = 0; i <= M; i++){
                    if (hp-i <= 0){
                        sum += Math.pow(M+1,rest-1);
                    }
                    else {
                        sum += dp[rest-1][hp-i];
                    }
                }
                dp[rest][hp] = sum;
            }
        }

        return dp[K][N] /  Math.pow(M+1,K);
    }

    //*******************************************************************************************************


    /*
     * @author gengzhihao
     * @date 2023/9/16 9:59
     * @description 题目2
     * @param N
     * @param M
     * @param K
     * @return double
     **/
    public static double right2(int N, int M, int K){
        if ( N <= 0 || M <= 0 || K <= 0){
            return 0;
        }

        int[][] dp = new int[K+1][N+1];

        dp[0][0] = 1;

        for (int rest = 1; rest <= K; rest++){
            dp[rest][0] = (int)Math.pow(M+1,rest);
            for (int hp = 1; hp <= N; hp++){
                dp[rest][hp] += dp[rest][hp-1];
                dp[rest][hp] += dp[rest-1][hp];
                //dp[rest-1][hp-M-1]
                if (hp-M-1 <= 0){
                    dp[rest][hp] -= Math.pow(M+1,rest-1);
                }else {
                    dp[rest][hp] -= dp[rest-1][hp-M-1];
                }
            }
        }

        return dp[K][N] /  Math.pow(M+1,K);
    }


    //*******************************************************************************************************


    public static double right(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        long all = (long) Math.pow(M + 1, K);
        long kill = process(K, M, N);
        return (double) ((double) kill / (double) all);
    }

    // 怪兽还剩hp点血
    // 每次的伤害在[0~M]范围上
    // 还有times次可以砍
    // 返回砍死的情况数！
    public static long process(int times, int M, int hp) {
        if (times == 0) {
            return hp <= 0 ? 1 : 0;
        }
        if (hp <= 0) {
            return (long) Math.pow(M + 1, times);
        }
        long ways = 0;
        for (int i = 0; i <= M; i++) {
            ways += process(times - 1, M, hp - i);
        }
        return ways;
    }

    public static void main(String[] args) {
        int NMax = 10;
        int MMax = 10;
        int KMax = 10;
        int testTime = 200;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * NMax);
            int M = (int) (Math.random() * MMax);
            int K = (int) (Math.random() * KMax);
            double ans = right(N, M, K);
            double ans2 = right2(N, M, K);
            if (ans != ans2) {
                System.out.println("Oops!");
                System.out.println("K " + K);
                System.out.println("M " + M);
                System.out.println("N " + N);

                System.out.println("ans " + ans);
                System.out.println("ans2 " + ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
