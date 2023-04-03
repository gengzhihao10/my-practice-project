package com.c.mashibing.suanfa.xinshouke;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

import static com.c.mashibing.suanfa.xinshouke.Code1_5数组排序.printArray;
import static com.c.mashibing.suanfa.xinshouke.Code3_1有序数组中找到Num.generateRandomArray;

@Slf4j
public class Code3_2有序数组中找到等于num最左的位置 {

    /*
     * @author gengzhihao
     * @date 2023/4/1 11:51
     * @description 找到>=num的最左的位置
     * @param arr 有序数组（从小到大）
     * @param num
     * @return int 位置
     **/
    public static int mostLeftNotLessNumIndex(int[] arr, int num){
        if (arr == null || arr.length == 0){
            return -1;
        }
        int L = 0;
        int R = arr.length -1;
        int mid = (L + R)/2;
        int ans = -1;

        while (L <= R){
            mid = (L + R)/2;
            if (arr[mid] >= num){
                ans = mid;
                R = mid -1;
            }
            else if (arr[mid] < num){
                L = mid + 1;
            }
        }
        return ans;
    }

    public static int test(int[] arr, int num){
        for (int i = 0; i< arr.length; i++){
            if (arr[i] >= num){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int maxLen = 50;
        int maxValue = 1000;
        int testTime = 10000;
        boolean succeed = true;

        for (int i = 0;i<testTime;i++){
            int[] arr = generateRandomArray(maxLen,maxValue);
            Arrays.sort(arr);
            int value = (int) ((maxValue + 1) * Math.random()) - (int)(maxValue * Math.random());

            if (test(arr,value) != mostLeftNotLessNumIndex(arr,value)){
                printArray(arr);
                log.info(value+"");
                log.info(test(arr,value)+"");
                log.info(mostLeftNotLessNumIndex(arr,value)+"");
                succeed = false;
                break;
            }
        }
        log.info(succeed ? "Nice" : "Fucking fucked!");
    }
}
