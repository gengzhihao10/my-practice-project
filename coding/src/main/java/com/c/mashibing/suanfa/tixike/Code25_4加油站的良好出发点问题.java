package com.c.mashibing.suanfa.tixike;

import java.util.LinkedList;

/*
todo
 题目1，加油站的良好出发点问题
 测试链接：https://leetcode.com/problems/gas-station
 */
public class Code25_4加油站的良好出发点问题 {

    /*
     * @author gengzhihao
     * @date 2023/11/1 10:59
     * @description 题目1
     * @param gas
     * @param cost
     * @return int
     **/
    public static int canCompleteCircuit(int[] gas, int[] cost){
        if (gas == null || gas.length == 0 || cost == null || cost.length == 0){
            return -1;
        }
        boolean[] resArr = getResArr(gas,cost);
        for (int i = 0; i < resArr.length; i++){
            if (resArr[i]){
                return i;
            }
        }
        return -1;
    }

    //返回所有加油站是否符合要求的判断
    private static boolean[] getResArr(int[] gas, int[] cost) {
        int N = gas.length;
        int[] temp = new int[N * 2];
        for (int i = 0; i < N; i++){
            temp[i] = gas[i] - cost[i];
            temp[i + N] = temp[i];
        }
        //拿到累加和数组
        for (int i = 1; i < temp.length; i++){
            temp[i] += temp[i-1];
        }

        //初始化最小值窗口
        LinkedList<Integer> minWindow = new LinkedList<>();
        for(int R = 0; R < N; R ++){
            while (!minWindow.isEmpty() && temp[minWindow.peekLast()] >= temp[R]){
                minWindow.pollLast();
            }
            minWindow.offerLast(R);
        }

        //拿到结果数组
        boolean[] res = new boolean[N];
        for (int i = 0; i < N; i++){
            //准备好前缀和
            int pre = i == 0 ? 0 : temp[i-1];
            int min = minWindow.peekFirst();
            //油箱中的油，在达到最小值时是否大于等于0为true
            //小于0则表示在该加油站时油箱里的油为负数，不能继续行使，所以此时为false
            res[i] = (temp[min] - pre) >= 0;

            //更新minWindow
            //更新右边
            while (!minWindow.isEmpty() && temp[minWindow.peekLast()] >= temp[i+N]){
                minWindow.pollLast();
            }
            minWindow.offerLast(i+N);
            //更新左边
            if (minWindow.peekFirst() <= i){
                minWindow.pollFirst();
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] gas = {1,2,3,4,5};
        int[] cost = {3,4,5,1,2};

        int res = canCompleteCircuit(gas,cost);
        System.out.println(res);
    }
}
