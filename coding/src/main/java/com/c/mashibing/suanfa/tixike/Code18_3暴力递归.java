package com.c.mashibing.suanfa.tixike;

/*
 题目1，汉诺塔问题。使用递归，打印出从最左移动到最右解决过程。
 题目2，优化递归过程
 */
public class Code18_3暴力递归 {


    /*
     * @author gengzhihao
     * @date 2023/8/21 9:38
     * @description 题目1
     * @param n 汉诺塔层数。层数越高数值越低。如1层为3，2层为2，3层为1
     **/
    public static void left2right(int n){
        if (n == 1){
            System.out.println(n + "从左到右");
            return;
        }
        left2middle(n-1);
        System.out.println(n + "从左到右");
        middle2right(n-1);
    }

    private static void middle2right(int n) {
        if (n == 1){
            System.out.println(n + "从中到右");
            return;
        }
        middle2left(n-1);
        System.out.println(n + "从中到右");
        left2right(n-1);
    }

    private static void middle2left(int n) {
        if (n == 1){
            System.out.println(n + "从中到左");
            return;
        }
        middle2right(n-1);
        System.out.println(n + "从中到左");
        right2left(n-1);
    }

    private static void right2left(int n) {
        if (n == 1){
            System.out.println(n + "从右到左");
            return;
        }
        right2middle(n-1);
        System.out.println(n + "从右到左");
        middle2left(n-1);
    }

    private static void right2middle(int n) {
        if (n == 1){
            System.out.println(n + "从右到中");
            return;
        }
        right2left(n-1);
        System.out.println(n + "从右到中");
        left2middle(n-1);
    }

    private static void left2middle(int n) {
        if (n == 1){
            System.out.println(n + "从左到中");
            return;
        }
        left2right(n-1);
        System.out.println(n + "从左到中");
        right2middle(n-1);
    }

    public static void main(String[] args) {
//        left2right(3);
        qs2_process1(3);
    }



    /*
     * @author gengzhihao
     * @date 2023/8/22 9:32
     * @description 题目2
     * @param n
     **/
    public static void qs2_process1(int n){
        if (n == 1){
            System.out.println(n + "从左到右");
            return;
        }
        String left = "左";
        String middle = "中";
        String right = "右";
        process2(n,left,right,middle);
    }

    private static void process2(int n, String start, String end,String other) {
        if (n == 1){
            System.out.println(n + "从" + start + "到" + end);
            return;
        }
        process2(n-1,start,other,end);
        System.out.println(n + "从" + start + "到" + end);
        process2(n-1,other,end,start);
    }


}
