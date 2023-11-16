package com.c.mashibing.suanfa.tixike;

/*
todo
 题目1，
 第一年农场有1只成熟的母牛A，往后的每年：
 1）每一只成熟的母牛都会生一只母牛
 2）每一只新出生的母牛都在出生的第三年成熟
 3）每一只母牛永远不会死
 返回N年后牛的数量

 */
public class Code27_3返回n年后牛的数量 {


    /*
     * @author gengzhihao
     * @date 2023/11/16 11:30
     * @description 题目1
     * @param n
     * @return int
     **/
    public static int s(int n) {
        return 0;
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


}
