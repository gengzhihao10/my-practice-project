package com.c.mashibing.suanfa.tixike;

/*
todo
 题目1，
 给定一个数组arr，arr[i]代表第i号咖啡机泡一杯咖啡的时间
 给定一个正数N，表示N个人等着咖啡机泡咖啡，每台咖啡机只能轮流泡咖啡
 只有一台咖啡机，一次只能洗一个杯子，时间耗费a，洗完才能洗下一杯
 每个咖啡杯也可以自己挥发干净，时间耗费b，咖啡杯可以并行挥发
 假设所有人拿到咖啡之后立刻喝干净，
 返回从开始等到所有咖啡机变干净的最短时间
 三个参数：int[] arr、int N，int a、int b
 暴力递归
 这个问题可以分解为2个问题：
 第1部分：在没有洗咖啡问题的基础上，得到所有人喝咖啡的最短时间（以最后一个人或者咖啡机来计算结束时间）
 这是一个贪心问题
 第2部分：在得到了一个以index为几号喝咖啡的人，value为喝完咖啡时间点的数组后，得到在这个数组的基础上，所有咖啡杯洗干净的最短时间（以最后一个咖啡杯计算时间）
 这是一个动态规划(这一题中使用暴力递归解)
 两个问题加起来一起解
 题目2，同问题1，其中第2部分问题使用业务对应模型的动态规划
 */
public class Code21_9返回咖啡机从开始到干净的最短时间 {


    /*
     * @author gengzhihao
     * @date 2023/9/8 9:49
     * @description 题目1
     * @param arr 数组arr代表每一个咖啡机冲一杯咖啡的时间，每个咖啡机只能串行的制造咖啡。
     * @param n 现在有n个人需要喝咖啡，只能用咖啡机来制造咖啡。
     * @param a 洗杯子的机器洗完一个杯子时间为a
     * @param b 任何一个杯子自然挥发干净的时间为b
     * @return int 假设时间点从0开始，返回所有人喝完咖啡并洗完咖啡杯的全部过程结束后，至少来到什么时间点。
     **/
    public static int coffee1(int[] arr, int n, int a, int b){
        return 0;
    }
}
