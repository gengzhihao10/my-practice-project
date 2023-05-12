package com.c.mashibing.suanfa.tixike;

import com.common.utils.LogarithmicUtil;

public class Code2_4插入排序 {


    /*
     * @author gengzhihao
     * @date 2023/5/8 11:26
     * @description  插入排序
     * @param arr
     **/
    public static void insertSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }


        int curIndex = 0;
        for (int i = 1; i < arr.length; i++ ){
            curIndex = i;

            while (arr[curIndex - 1] < arr[curIndex]){
                LogarithmicUtil.swapValue(arr,i-1,i);
                curIndex--;
            }
        }
    }


    public static void main(String[] args) {

    }
}
