package com.c.mashibing.suanfa.tixike;

/*
 题目1，
 给定一个数N，想象只由0和1两种字符，组成的所有长度为N的字符串
 如果某个字符串,任何0字符的左边都有1紧挨着,认为这个字符串达标
 返回有多少达标的字符串
 题目2，
 用1*2的瓷砖，把N*2的区域填满
 返回铺瓷砖的方法数

 */
public class Code27_4返回有多少达标的字符串 {
    /*
     * @author gengzhihao
     * @date 2023/11/16 10:21
     * @description 题目1
     * @param n
     * @return int
     **/
    public static int s(int n) {
        if (n <= 0) {
            return -1;
        }
        if (n <= 3) {
            return n;
        }

        int[][] base = {
                {1, 1},
                {1, 0}
        };

        //获取系数矩阵的n-2次方
        int[][] powerBase = getPower(base,n-2);

        return 2 * powerBase[0][0] + powerBase[1][0];
    }

    //返回input矩阵的n次方
    private static int[][] getPower(int[][] input, int n) {

        //结果矩阵初始化为单位矩阵1
        int[][] res = new int[input.length][input[0].length];
        for (int i = 0; i < res.length; i++){
            res[i][i] = 1;
        }

        for (; n != 0; n >>= 1){
            if ((n & 1) != 0){
                res = matrix(res,input);
            }
            input = matrix(input,input);
        }

        return res;
    }

    //返回2个矩阵相乘的结果
    private static int[][] matrix(int[][] input1, int[][] input2) {

        int m = input1.length;
        int n = input1[0].length;
        int j = input2[0].length;
        int[][] res = new int[m][j];

        for (int m1 = 0; m1 < m; m1++){
            for (int j1 = 0; j1 < j; j1++){
                for (int n1 = 0; n1 < n; n1++){
                    res[m1][j1] += input1[m1][n1] * input2[n1][j1];
                }
            }
        }
        return res;
    }


    public static int s1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        return s1(n - 1) + s1(n - 2);
    }

    public static int s2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        int res = 2;
        int pre = 1;
        int tmp = 0;
        for (int i = 3; i <= n; i++) {
            tmp = res;
            res = res + pre;
            pre = tmp;
        }
        return res;
    }


    //**************************************************************************

    /*
     * @author gengzhihao
     * @date 2023/11/16 11:27
     * @description 题目2
     * @param n
     * @return int
     **/
    public static int f(int n) {
        if (n <= 0) {
            return -1;
        }
        if (n <= 2) {
            return 1;
        }

        int[][] base = {
                {1, 1},
                {1, 0}
        };

        //获取系数矩阵的n-2次方
        int[][] powerBase = getPower(base,n-2);

        return powerBase[0][0] + powerBase[1][0];
    }


    public static int f1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return f1(n - 1) + f1(n - 2);
    }

    public static int f2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int res = 1;
        int pre = 1;
        int tmp = 0;
        for (int i = 3; i <= n; i++) {
            tmp = res;
            res = res + pre;
            pre = tmp;
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println("s: " + s(10));
        System.out.println("s1: " + s1(10));

        System.out.println("f: " + f(10));
        System.out.println("f1: " + f1(10));
    }
}
