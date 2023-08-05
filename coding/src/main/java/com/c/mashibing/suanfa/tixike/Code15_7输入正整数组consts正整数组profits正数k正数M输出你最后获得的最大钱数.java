package com.c.mashibing.suanfa.tixike;

import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
 题目1，输入: 正数数组costs、正数数组profits、正数K、正数M
    costs[i]表示i号项目的花费
    profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
    K表示你只能串行的最多做k个项目
    M表示你初始的资金
    说明: 每做完一个项目，马上获得的收益，可以支持你去做下一个项目。不能并行的做项目。
    输出：你最后获得的最大钱数。
 */
public class Code15_7输入正整数组consts正整数组profits正数k正数M输出你最后获得的最大钱数 {

    /*
     * @author gengzhihao
     * @date 2023/8/5 10:05
     * @description 题目1
     * @param cost costs[i]表示i号项目的花费
     * @param profits profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
     * @param k K表示你只能串行的最多做k个项目
     * @param M M表示你初始的资金
     * @return int 输出：你最后获得的最大钱数。
     **/
    public static int qs1_process1(int[] cost, int[] profits, int k, int M){
        if (cost == null || profits == null || cost.length == 0 || profits.length == 0 || k <= 0 || M <= 0 || cost.length != profits.length){
            return 0;
        }

        //小根堆
        PriorityQueue<Project> costHeap = new PriorityQueue<>(new CostComparator());
        //大根堆
        PriorityQueue<Project> profitsHeap = new PriorityQueue<>(new ProfitsComparator());

        for (int i = 0; i < cost.length; i++){
            costHeap.offer(new Project(cost[i],profits[i]));
        }

        Project project;
        //k次循环
        for (int i = 0; i < k; i++){
            while (!costHeap.isEmpty() && costHeap.peek().cost <= M){
                profitsHeap.offer(costHeap.poll());
            }
            if (profitsHeap.isEmpty()) {
                return M;
            }
            project = profitsHeap.poll();
            M += project.profit;
        }

        return M;
    }

    private static class CostComparator implements Comparator<Project>{
        @Override
        public int compare(Project o1, Project o2) {
            return o1.cost-o2.cost;
        }
    }

    private static class ProfitsComparator implements Comparator<Project>{
        @Override
        public int compare(Project o1, Project o2) {
            return o2.profit-o1.profit;
        }
    }



    private static class Project{
        int cost;
        int profit;

        public Project() {
        }

        public Project(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }
}
