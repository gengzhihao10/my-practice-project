package com.c.mashibing.suanfa.tixike;

/*
todo
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
    public static int c(int n) {
        return 0;
    }















    public static int c1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        return c1(n - 1) + c1(n - 3);
    }

    public static int c2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        int res = 3;
        int pre = 2;
        int prepre = 1;
        int tmp1 = 0;
        int tmp2 = 0;
        for (int i = 4; i <= n; i++) {
            tmp1 = res;
            tmp2 = pre;
            res = res + prepre;
            pre = tmp1;
            prepre = tmp2;
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
    public static int getNum(int n){
        return 0;
    }



















    public static int getNum1(int n) {
        if (n < 1) {
            return 0;
        }
        return process(1, n);
    }

    public static int process(int i, int n) {
        if (i == n - 1) {
            return 2;
        }
        if (i == n) {
            return 1;
        }
        return process(i + 1, n) + process(i + 2, n);
    }

    public static int getNum2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int pre = 1;
        int cur = 1;
        int tmp = 0;
        for (int i = 2; i < n + 1; i++) {
            tmp = cur;
            cur += pre;
            pre = tmp;
        }
        return cur;
    }
}
