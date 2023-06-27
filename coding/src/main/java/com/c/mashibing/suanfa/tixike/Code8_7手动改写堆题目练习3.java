package com.c.mashibing.suanfa.tixike;

import java.util.*;

import com.c.mashibing.suanfa.tixike.Code8_6手动改写堆题目练习2.EnhancedHeap;

/*
todo
 题目1，一对arr[i]和op[i]就代表一个事件：
 用户号为arr[i]，op[i] == T就代表这个用户购买了一件商品
 op[i] == F就代表这个用户退货了一件商品
 现在你作为电商平台负责人，你想在每一个事件到来的时候，
 都给购买次数最多的前K名用户颁奖。
 所以每个事件发生后，你都需要一个得奖名单（得奖区）
 具体规则见PPT
 */
public class Code8_7手动改写堆题目练习3 {

    //顾客
    public static class Customer{
        int id;
        int count;
        int time;

        public Customer(int id, int count, int time) {
            this.id = id;
            this.count = count;
            this.time = time;
        }
    }

    //得奖区比较器
    public static class RewardComparator implements Comparator<Customer> {
        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.count != o2.count ? o1.count - o2.count : o1.time - o2.time;
        }
    }

    //候选区比较器
    public static class CandidateComparator implements Comparator<Customer> {
        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.count != o2.count ? o2.count - o1.count : o1.time - o2.time;
        }
    }


    /*
     * @author gengzhihao
     * @date 2023/6/27 8:40
     * @description 题目1
     * @param arr i时刻发生行为的顾客id的数组
     * @param op i时刻发生行为(true购买 or false退货)的数组
     * @param k 得奖区最大容量
     * @return List<List<Customer>>
     **/
    public static List<List<Customer>> qs1_process1(int[] arr,boolean[] op,int k){
        if (arr == null || arr.length == 0 || op == null || op.length == 0 || arr.length != op.length){
            return null;
        }

        EnhancedHeap<Customer> rewardHeap = new EnhancedHeap<>(new RewardComparator());
        EnhancedHeap<Customer> candidateHeap = new EnhancedHeap<>(new CandidateComparator());
        //key 为 count不为0的顾客的id，value 为 该顾客
        Map<Integer,Customer> custmers = new HashMap<>();
        List<List<Customer>> result = new ArrayList<>();
        for (int i = 0; i < arr.length; i++){
            process1(rewardHeap,candidateHeap,custmers,result,arr,op,k,i);
        }
        return result;
    }

    //处理逻辑
    private static void process1(EnhancedHeap<Customer> rewardHeap, EnhancedHeap<Customer> candidateHeap,Map<Integer,Customer> custmers, List<List<Customer>> result, int[] arr, boolean[] op, int k,int i ) {
        //该顾客count为0，且发生退货行为
        if (!custmers.containsKey(arr[i]) && op[i] == false){
            //fixme 这里的复杂度应该是n平方了，这是为什么，看一下老师代码
            result.add(getRewardCustomers(rewardHeap));
            return;
        }
        //如果count不为0的顾客列表中没有当前顾客，先把这个顾客添加进customers中
        if (!custmers.containsKey(arr[i])){
            custmers.put(arr[i],new Customer(arr[i],1,0));
        }
        //customers中有当前顾客
        else {
            if (op[i]){
                custmers.get(arr[i]).count++;
            }
            else {
                custmers.get(arr[i]).count--;
            }
        }

        //对得奖区和候选区进行处理
        if (op[i]){
            if (rewardHeap.size() < k){

            }
        }


        return null;
    }

    //拿到获奖区的所有顾客
    private static List<Customer> getRewardCustomers(EnhancedHeap<Customer> rewardHeap) {
        List<Customer> customers = new ArrayList<>();
        while (!rewardHeap.isEmpty()){
            rewardHeap.getAllElements();
        }
        return customers;
    }


}
