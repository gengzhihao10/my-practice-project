package com.c.mashibing.suanfa.tixike;

/*
 假设有排成一行的N个位置，记为1~N，N 一定大于或等于 2
 开始时机器人在其中的M位置上(M 一定是 1~N 中的一个)
 如果机器人来到1位置，那么下一步只能往右来到2位置；
 如果机器人来到N位置，那么下一步只能往左来到 N-1 位置；
 如果机器人来到中间位置，那么下一步可以往左走或者往右走；
 规定机器人必须走 K 步，最终能来到P位置(P也是1~N中的一个)的方法有多少种
 给定四个参数 N、M、K、P，返回方法数。
 题目2，基于二维数组缓存的动态规划
 题目3，基于二维数组直接计算的动态规划
 */
public class Code19_2假设有拍成一行的N个位置 {

    /*
     * @author gengzhihao
     * @date 2023/8/28 11:28
     * @description 题目1
     * @param N 有从1到N的座位
     * @param start 机器人的起始位置
     * @param aim 机器人的目标位置
     * @param K 机器人能走几步
     * @return int 机器人从start走到aim，一共走K步，能有几条路径
     **/
    public static int qs1_process1(int N, int start, int aim, int K) {
        return process1(N,start,aim,K);
    }

    //机器人走的每一步的情况
    private static int process1(int N, int cur, int aim, int rest) {
        if (rest == 0){
            if (cur == aim){
                return 1;
            }else {
                return 0;
            }
        }
        if (cur == 1){
            return process1(N,2,aim,rest-1);
        }
        if (cur == N){
            return process1(N,N-1,aim,rest-1);
        }
        return process1(N,cur-1,aim,rest-1) + process1(N,cur+1,aim,rest-1);
    }


    /*
     * @author gengzhihao
     * @date 2023/8/30 8:54
     * @description 题目2
     * @param N
     * @param start
     * @param aim
     * @param K
     * @return int
     **/
    public static int qs2_process1(int N, int start, int aim, int K) {
        int[][] ans = new int[N+1][K+1];
        for (int i = 0; i <= N; i++){
            for (int j = 0; j <= K; j++){
                ans[i][j] = -1;
            }
        }
        return process2(N,start,aim,K,ans);
    }

    //机器人走的每一步的情况
    private static int process2(int N, int cur, int aim, int rest,int[][] ans) {
        if (ans[cur][rest] != -1){
            return ans[cur][rest];
        }

        int res = -1;
        if (rest == 0){
//            if (cur == aim){
//                return 1;
//            }else {
//                return 0;
//            }
            res = cur == aim ? 1 : 0;
        }
        else if (cur == 1){
            res =  process2(N,2,aim,rest-1,ans);
        }
        else if (cur == N){
            res =  process2(N,N-1,aim,rest-1,ans);
        }else {
            res = process2(N,cur-1,aim,rest-1,ans) + process2(N,cur+1,aim,rest-1,ans);
        }
        ans[cur][rest] = res;
        return res;
    }


    /*
     * @author gengzhihao
     * @date 2023/8/30 9:15
     * @description 题目3
     * @param N
     * @param start
     * @param aim
     * @param K
     * @return int
     **/
    public static int qs3_process1(int N, int start, int aim, int K){
        int[][] ans = new int[N+1][K+1];
        ans[aim][0] = 1;

        for (int j = 1; j <= K; j++){
            ans[1][j] = ans[2][j-1];
            for (int i = 2; i <= N-1; i++){
                ans[i][j] = ans[i-1][j-1] + ans[i+1][j-1];
            }
            ans[N][j] = ans[N-1][j-1];
        }
//        printArr(ans);
        return ans[start][K];
    }

    private static void printArr(int[][] ans) {
        System.out.println();
        for (int i = 0; i < ans.length; i++){
            for (int j = 0; j < ans[i].length;j++){
                System.out.print("  " + ans[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println(qs1_process1(5, 2, 4, 6));
        System.out.println(qs2_process1(5, 2, 4, 6));
        System.out.println(qs3_process1(5, 2, 4, 6));
    }
}
