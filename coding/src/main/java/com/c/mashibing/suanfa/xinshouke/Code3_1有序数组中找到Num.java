package com.c.mashibing.suanfa.xinshouke;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

import static com.c.mashibing.suanfa.xinshouke.Code2_6对数器的使用.*;

@Slf4j
public class Code3_1有序数组中找到Num {


    /*
     * @author gengzhihao
     * @date 2023/4/1 10:44
     * @description 二分查找数组arr是否存在数num
     * 有序数组中找到Num
     * @param arr 数组需要有序
     * @param num
     * @return boolean
     **/
    public static boolean find(int[] arr, int num){
        if (arr == null || arr.length ==0){
            return false;
        }

        int L = 0;
        int R = arr.length -1;
        // arr[0....N-1] num    arr[L....R] num
        while (L <= R){
            int mid = (L + R)/2;
            if (arr[mid] == num){
                return true;
            }
            else if(arr[mid] < num){
                L = mid +1;
            }
            else if (arr[mid] > num){
                R = mid -1;
            }
        }
        return false;
    }

    /*
     * @author gengzhihao
     * @date 2023/4/1 10:58
     * @description 使用遍历的方法测试是否能找到数
     * @param arr 数组需要有序
     * @param num
     * @return boolean
     **/
    public static boolean test(int[] arr, int num){
        for (int cur : arr){
            if (cur == num){
                return true;
            }
        }
        return false;
    }

    public static int[] generateRandomArray(int maxSize, int maxValue){
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++){
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int)(maxValue * Math.random());
        }
        return arr;
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

            if (test(arr,value) != find(arr,value)){
                succeed = false;
                break;
            }
        }
        log.info(succeed ? "Nice" : "Fucking fucked!");
    }
}
