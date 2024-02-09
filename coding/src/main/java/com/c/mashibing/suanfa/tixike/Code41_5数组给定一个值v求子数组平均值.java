package com.c.mashibing.suanfa.tixike;

/*
 题目1，
 给定一个数组arr，给定一个值v
 求子数组平均值小于等于v的最长子数组长度

 */
public class Code41_5数组给定一个值v求子数组平均值 {


    /*
     * @author gengzhihao
     * @date 2024/2/8 9:57
     * @description 题目1
     * @param arr
     * @param v
     * @return int
     **/
    public static int ways3(int[] arr, int v){
        if (arr == null || arr.length == 0 ){
            return 0;
        }

        //求平均值为v的子数组最长有多少，等价于每个arr[i]都减去v，平均值为0的子数组最长有多少
        //等价于sum为0的子数组最长有多少
        for (int i = 0; i < arr.length; i++){
            arr[i] -= v;
        }

        int[] minSum = new int[arr.length];
        int[] minSumEnd = new int[arr.length];
        minSum[arr.length-1] = arr[arr.length-1];
        minSumEnd[arr.length-1] = arr.length-1;
        for (int i = arr.length - 2; i >= 0; i--){
            if (minSum[i + 1] < 0){
                minSum[i] = arr[i] + minSum[i + 1];
                minSumEnd[i] = minSumEnd[i + 1];
            }else {
                minSum[i] = arr[i];
                minSumEnd[i] = i;
            }
        }

        int end = 0;
        int ans = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++){
            while (end < arr.length && sum + minSum[end] < 0){
                sum += minSum[end];
                end = minSumEnd[end];
            }
            ans = Math.max(ans,end - i);
            if (end > i){
                sum -= arr[i];
            }else {
                end++;
            }
        }
        return ans;
    }
}
