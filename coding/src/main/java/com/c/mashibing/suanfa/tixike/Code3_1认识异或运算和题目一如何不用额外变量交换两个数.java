package com.c.mashibing.suanfa.tixike;


import com.common.utils.LogarithmicUtil;

/**
 * @author gengzhihao
 * @date 2023/5/11 10:32
 * @description
 * 题目1，如何不使用临时变量交换数组中的两个数
**/

public class Code3_1认识异或运算和题目一如何不用额外变量交换两个数 {


    /*
     * @author gengzhihao
     * @date 2023/5/11 10:21
     * @description 题目1
     * @param arr
     * @param i
     * @param j
     **/
    public static void swapValue(int[] arr, int i, int j){
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void main(String[] args) {
        int[] arr = {0,1,2,3};
        LogarithmicUtil.printArray(arr);
        swapValue(arr,1,2);
        LogarithmicUtil.printArray(arr);
    }
}
