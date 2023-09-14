package com.c.mashibing.suanfa.tixike;

/*
todo
 题目1，
 给定5个参数，N，M，row，col，k
 表示在N*M的区域上，醉汉Bob初始在(row,col)位置
 Bob一共要迈出k步，且每步都会等概率向上下左右四个方向走一个单位
 任何时候Bob只要离开N*M的区域，就直接死亡
 返回k步之后，Bob还在N*M的区域的概率

 */
public class Code22_7返k步之后Bob还在NM区域的概率 {


    /*
     * @author gengzhihao
     * @date 2023/9/13 14:42
     * @description 暴力递归
     * @param row Bob所在行
     * @param col Bob所在列
     * @param k 有k步可以走
     * @param N 生存区域为第0~N-1行
     * @param M 生存区域为第0~M-1列
     * @return double Bob k步走完的生存概率
     **/
    public static double livePosibility0(int row, int col, int k, int N, int M) {
        if (row < 0 || col < 0 || k < 0 || N < 0 || M < 0) {
            return -1;
        }
        return process0(row, col, k, N, M) / Math.pow(4, k);
    }


    private static double process0(int row, int col, int k, int N, int M) {
        //踏出生存区域，直接死亡，不可能生存
        if (row < 0 || row == N || col < 0 || col == M) {
            return 0;
        }
        //当前点在生存区域中，如果k为0，说明生存下来了
        if (k == 0) {
            return 1;
        }

        return process0(row + 1, col, k - 1, N, M)
                + process0(row, col + 1, k - 1, N, M)
                + process0(row - 1, col, k - 1, N, M)
                + process0(row, col - 1, k - 1, N, M);
    }

    //*******************************************************************************************

    /*
     * @author gengzhihao
     * @date 2023/9/14 11:37
     * @description 题目2
     * @param row
     * @param col
     * @param k
     * @param N
     * @param M
     * @return double
     **/
    public static double livePosibility1(int row, int col, int k, int N, int M) {
        if (row < 0 || col < 0 || k < 0 || N < 0 || M < 0) {
            return -1;
        }

        //申请空白dp表
        int[][][] dp = new int[N][M][k + 1];
        //初始化dp表
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j][0] = 1;
            }
        }

        //计算
        for (int k1 = 1; k1 <= k; k1++) {
            for (int row1 = 0; row1 < N; row1++) {
                for (int col1 = 0; col1 < M; col1++) {
                    dp[row1][col1][k1] = getVaue(dp,row1+1,col1,k1-1,N,M)
                            + getVaue(dp,row1-1,col1,k1-1,N,M)
                            + getVaue(dp,row1,col1+1,k1-1,N,M)
                            + getVaue(dp,row1,col1-1,k1-1,N,M);
                }
            }
        }
        return dp[row][col][k] / Math.pow(4,k);
    }

    //将递归时跳出生存区域的情况，在这里包括进来
    private static int getVaue(int[][][] dp,int row,int col,int k,int N,int M){
        if (row < 0 || row == N || col < 0 || col == M) {
            return 0;
        }
        return dp[row][col][k];
    }

    //*************************************************************************************

    public static void main(String[] args) {
        System.out.println(livePosibility1(6, 6, 10, 50, 50));
        System.out.println(ans(6, 6, 10, 50, 50));
    }

    public static double ans(int row, int col, int k, int N, int M) {
        return (double) process(row, col, k, N, M) / Math.pow(4, k);
    }

    // 目前在row，col位置，还有rest步要走，走完了如果还在棋盘中就获得1个生存点，返回总的生存点数
    public static long process(int row, int col, int rest, int N, int M) {
        if (row < 0 || row == N || col < 0 || col == M) {
            return 0;
        }
        // 还在棋盘中！
        if (rest == 0) {
            return 1;
        }
        // 还在棋盘中！还有步数要走
        long up = process(row - 1, col, rest - 1, N, M);
        long down = process(row + 1, col, rest - 1, N, M);
        long left = process(row, col - 1, rest - 1, N, M);
        long right = process(row, col + 1, rest - 1, N, M);
        return up + down + left + right;
    }
}
