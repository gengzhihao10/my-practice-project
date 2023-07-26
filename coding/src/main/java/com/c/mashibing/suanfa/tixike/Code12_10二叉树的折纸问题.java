package com.c.mashibing.suanfa.tixike;

/*
 题目1，一张纸条，n次折纸后出现凹折痕和凸折痕，现折纸N次，打印折痕方向的顺序
 */
public class Code12_10二叉树的折纸问题 {

    /*
     * @author gengzhihao
     * @date 2023/7/26 9:36
     * @description 题目1
     * @param N
     **/
    public static void qs1_process(int N){
        if (N <= 0){
            return;
        }

        process(1,N,false);
    }

    private static void process(int i, int N, boolean dir) {
        if (i > N){
            return;
        }

        process(i+1, N, false);
        System.out.println(dir ? "凸" : "凹");
        process(i+1,N,true);
    }


    public static void main(String[] args) {
        qs1_process(3);
    }
}
