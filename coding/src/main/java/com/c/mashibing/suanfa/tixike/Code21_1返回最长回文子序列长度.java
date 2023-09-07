package com.c.mashibing.suanfa.tixike;

/*
 题目1，
 给定一个字符串str，返回这个字符串的最长回文子序列长度
 比如 ： str = “a12b3c43def2ghi1kpm”
 最长回文子序列是“1234321”或者“123c321”，返回长度7
 暴力递归
 题目2，基于严格位置依赖的动态规划
 题目3，基于记忆化搜素的动态规划，小优化，优化的常数时间
 */
public class Code21_1返回最长回文子序列长度 {

    /*
     * @author gengzhihao
     * @date 2023/9/6 10:22
     * @description 题目1
     * @param s
     * @return int
     **/
    public static int lpsl1(String s){
        if (s == null || s.length() == 0){
            return 0;
        }

        return process1(s.toCharArray(),0,s.length()-1);
    }

    //返回从L到R下标对应的字符中最长的回文子序列的长度
    private static int process1(char[] chars, int L, int R) {
        if (L == R){
            return 1;
        }
        if (L == R-1){
            return chars[L] == chars[R] ? 2 : 1;
        }

        //下面3种，是基于L和R位置一定不一样的情况下的3种可能性
        int p1 = process1(chars,L+1,R-1);
        int p2 = process1(chars,L,R-1);
        int p3 = process1(chars,L+1,R);
        //下面的1种，是基于L和R位置如果一样的可能性
        int p4 = chars[L] == chars[R] ? 2 + process1(chars,L+1,R-1) : 0;

        return Math.max(Math.max(p1,p2),Math.max(p3,p4));
    }

    /*
     * @author gengzhihao
     * @date 2023/9/7 9:53
     * @description 题目2
     * @param s
     * @return int
     **/
    public static int lpsl2(String s){
        if (s == null || s.length() == 0){
            return 0;
        }

        //1 申请空白表
        int N = s.length();
        int[][] ans = new int[N][N];
        char[] chars = s.toCharArray();

        //2 初始化空白表
        ans[N-1][N-1] = 1;
        for (int L = 0; L < N-1; L++){
            ans[L][L] = 1;
            ans[L][L+1] = chars[L] == chars[L+1] ? 2 : 1;
        }

        //3 计算
        for (int L = N-2; L > 0; L--){
            for (int R = L + 2; R < N; R++){
                int p1 = ans[L+1][R-1];
                int p2 = ans[L][R-1];
                int p3 = ans[L+1][R];
                int p4 = chars[L] == chars[R] ? 2 + ans[L+1][R-1] : 0;
                ans[L][R] = Math.max(Math.max(p1,p2),Math.max(p3,p4));
            }
        }

        //4 返回结果
        return ans[0][N-1];
    }


    /*
     * @author gengzhihao
     * @date 2023/9/7 10:12
     * @description 题目3
     * @param s
     * @return int
     **/
    public static int lpsl3(String s){
        if (s == null || s.length() == 0){
            return 0;
        }

        //1 申请空白表
        int N = s.length();
        int[][] ans = new int[N][N];
        char[] chars = s.toCharArray();

        //2 初始化空白表
        ans[N-1][N-1] = 1;
        for (int L = 0; L < N-1; L++){
            ans[L][L] = 1;
            ans[L][L+1] = chars[L] == chars[L+1] ? 2 : 1;
        }

        //3 计算
        for (int L = N-2; L > 0; L--){
            for (int R = L + 2; R < N; R++){
                int p2 = ans[L][R-1];
                int p3 = ans[L+1][R];
                int p4 = chars[L] == chars[R] ? 2 + ans[L+1][R-1] : 0;
                ans[L][R] = Math.max(p2,Math.max(p3,p4));
            }
        }

        //4 返回结果
        return ans[0][N-1];
    }
}
