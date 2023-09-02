package com.c.mashibing.suanfa.tixike;

/*
 题目1，
 给定两个长度都为N的数组weights和values，
 weights[i]和values[i]分别代表 i号物品的重量和价值。
 给定一个正数bag，表示一个载重bag的袋子，
 你装的物品不能超过这个重量。
 返回你能装下最多的价值是多少?
 暴力递归方法实现
 题目2，使用动态规划方法实现
 */
public class Code20_1背包问题 {

    // 所有的货，重量和价值，都在w和v数组里
    // 为了方便，其中没有负数
    // bag背包容量，不能超过这个载重
    // 返回：不超重的情况下，能够得到的最大价值
    public static int qs1_process1(int[] w, int[] v, int bag){
        if (w == null || w.length == 0 || v == null || v.length != w.length || bag < 0){
            return -1;
        }
        return process1(w,v,0,bag);
    }

    //返回：从index处开始，到结束时，背包剩余容量rest能装的下的最大价值
    private static int process1(int[] weight, int[] value, int index, int rest) {
        if (rest < 0){
            return -1;
        }
        if (index == weight.length){
            return 0;
        }

        int pos1 = process1(weight,value,index+1,rest);
        if (rest-weight[index] < 0){
            return pos1;
        }
        int pos2 = value[index] + process1(weight,value,index+1,rest-weight[index]);
        return Math.max(pos1,pos2);
    }

    //****************************************************************************************

    /*
     * @author gengzhihao
     * @date 2023/9/2 10:27
     * @description 题目2
     * @param w
     * @param v
     * @param bag
     * @return int
     **/
    public static int qs2_process1(int[] w, int[] v, int bag){
        if (w == null || w.length == 0 || v == null || v.length != w.length || bag < 0){
            return -1;
        }
        int N = w.length + 1;
        int M = bag+1;
        int[][] ans = new int[N][M];
        for (int index = N-2; index >= 0; index--){
            for (int rest = 0; rest < M; rest++){
                int pos1 = ans[index+1][rest];
                int pos2 = 0;
                if (rest-w[index] >= 0){
                    pos2 = v[index] + ans[index+1][rest-w[index]];
                }
                ans[index][rest] = Math.max(pos1,pos2);
            }
        }
        return ans[0][bag];
    }

    public static void main(String[] args) {
        int[] weights = { 3, 2, 4, 7, 3, 1, 7 };
        int[] values = { 5, 6, 3, 19, 12, 4, 2 };
        int bag = 15;
        int res1 = qs1_process1(weights,values,bag);
        System.out.println(res1);
        int res2 = qs2_process1(weights,values,bag);
        System.out.println(res2);
    }

}
