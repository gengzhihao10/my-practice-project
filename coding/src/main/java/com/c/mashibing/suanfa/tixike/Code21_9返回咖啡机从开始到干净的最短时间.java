package com.c.mashibing.suanfa.tixike;

import javax.crypto.Mac;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
 题目1，
 给定一个数组arr，arr[i]代表第i号咖啡机泡一杯咖啡的时间
 给定一个正数N，表示N个人等着咖啡机泡咖啡，每台咖啡机只能轮流泡咖啡
 只有一台洗咖啡杯机器，一次只能洗一个杯子，时间耗费a，洗完才能洗下一杯
 每个咖啡杯也可以自己挥发干净，时间耗费b，咖啡杯可以并行挥发
 假设所有人拿到咖啡之后立刻喝干净，
 返回从开始等到所有咖啡机变干净的最短时间
 三个参数：int[] arr、int N，int a、int b
 暴力递归
 这个问题可以分解为2个部分：
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
        PriorityQueue<Machine> heap = new PriorityQueue<>(new CoffeeMachineComparator());
        for (int costTime : arr){
            heap.offer(new Machine(0,costTime));
        }

        int[] ans = new int[n];
        Machine machine = null;
        for (int i = 0; i < n; i++){
            machine = heap.poll();
            ans[i] = machine.costTime + machine.serverTime;
            machine.serverTime = ans[i];
            heap.offer(machine);
        }

        return process1(ans,a,b,0,0);
    }

    /*
     * @author gengzhihao
     * @date 2023/9/9 9:23
     * @description
     * @param ans 每个人在不考虑清洁咖啡杯的情况下，喝完咖啡的时间点数组(最优解)
     * @param a 洗咖啡杯机器洗干净一杯的时间
     * @param b 咖啡杯挥发干净的时间
     * @param index 到了ans数组中第几个人了
     * @param free 洗咖啡杯机器什么时候可用
     * @return int 每个人都喝完咖啡，并都清洁完，所花费的最短时间
     **/
    private static int process1(int[] ans, int a, int b,int index, int free) {
        if (index == ans.length){
            return 0;
        }

        //可能性1，送入清洁机器中
        int cleanTime1 = Math.max(ans[index],free) + a;
        int other1 = process1(ans,a,b,index+1,cleanTime1);
        int res1 = Math.max(cleanTime1,other1);

        //可能性2，咖啡杯挥发
        int cleanTime2 = ans[index] + b;
        int other2 = process1(ans,a,b,index+1,free);
        int res2 = Math.max(cleanTime2,other2);

        return Math.min(res1,res2);
    }

    //咖啡机
    private static class Machine {
        int serverTime;//能够开始提供服务的时间
        int costTime;//每台咖啡机泡咖啡的时间，固定值

        public Machine() {
        }

        public Machine(int serverTime, int costTime) {
            this.serverTime = serverTime;
            this.costTime = costTime;
        }
    }

    //咖啡机的比较器
    private static class CoffeeMachineComparator implements Comparator<Machine> {
        //costTime + serverTime为咖啡机结束服务的时间
        //哪台咖啡机能最快结束服务的，排在前面
        @Override
        public int compare(Machine o1, Machine o2) {
            return (o1.costTime+o1.serverTime) - (o2.costTime+o2.serverTime);
        }
    }

    //***************************************************************************************

    /*
     * @author gengzhihao
     * @date 2023/9/9 10:01
     * @description 题目2
     * @param arr
     * @param n
     * @param a
     * @param b
     * @return int
     **/
    public static int coffee2(int[] arr, int n, int a, int b){
        PriorityQueue<Machine> heap = new PriorityQueue<>(new CoffeeMachineComparator());
        for (int costTime : arr){
            heap.offer(new Machine(0,costTime));
        }

        int[] ans = new int[n];
        Machine machine = null;
        for (int i = 0; i < n; i++){
            machine = heap.poll();
            ans[i] = machine.costTime + machine.serverTime;
            machine.serverTime = ans[i];
            heap.offer(machine);
        }

        return process2(ans,a,b);
    }

    private static int process2(int[] ans, int a, int b) {

        int N = ans.length;
        int maxFree = 0;
        for (int i = 0; i < N; i++){
           maxFree = Math.max(ans[i],maxFree) + a;
        }

        int[][] res = new int[N+1][maxFree+1];

        for (int index = N-1; index >= 0; index++ ){
            for (int free = 0; free <= maxFree; free++){
                //可能性1，送入清洁机器中
                int cleanTime1 = Math.max(ans[index],free) + a;
                if (cleanTime1 > maxFree){
                    continue;
                }
                int other1 = process1(ans,a,b,index+1,cleanTime1);
                int res1 = Math.max(cleanTime1,other1);

                //可能性2，咖啡杯挥发
                int cleanTime2 = ans[index] + b;
                int other2 = process1(ans,a,b,index+1, free);
                int res2 = Math.max(cleanTime2,other2);

                res[index][free] = Math.min(res1,res2);
            }
        }

        return res[0][0];
    }
}
