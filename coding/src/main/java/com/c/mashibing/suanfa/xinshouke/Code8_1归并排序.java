package com.c.mashibing.suanfa.xinshouke;

import com.common.utils.LogarithmicUtil;

public class Code8_1归并排序 {

    //递归方法实现
    public static void mergeSort1(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    //arr[L...R]范围上，让这个范围上的数有序
    private static void process(int[] arr, int L, int R) {
        //L==R放在这里而不是上一级的方法中，是作为递归的中止条件出现的
        if (L == R){
            return;
        }
        //等同于mid = (L+R)/2。
        //使用>>1是为了通过位运算得到更快的速度
        //L + ((R - L) >> 1)而不是(L+R)>>1是因为如果L和R如果很大，想加后有可能越界，而L + ((R - L) >> 1不会大于R，就不会越界
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr,mid+1, R);
        merge(arr, L, mid, R);
    }

    //对二分的两个有序数组进行合并
    private static void merge(int[] arr, int L, int M, int R) {
        //L到R有多少个数就准备多长的help数组
        int[] help = new int[R - L + 1];
        int i = 0;
        //两个数组的指针
        int p1 = L;
        int p2 = M + 1;
        //没有数组越界时，进行合并
        while (p1 <= M && p2 <= R){
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        //要么p1越界，要么p2越界。将其中没越界的数组剩余的部分拷贝至help数组
        while (p1 <= M){
            help[i++] = arr[p1++];
        }
        while (p2 <= R){
            help[i++] = arr[p2++];
        }
        //将help数组的值拷贝至原数组对应L-R位置
        for (i = 0; i < help.length; i++){
            arr[L + i] = help[i];
        }
    }


    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++){
            int[] arr1 = LogarithmicUtil.generateRandomArray(maxSize, maxValue);
            int[] arr2 = LogarithmicUtil.copyArray(arr1);
            mergeSort1(arr1);
            mergeSort2(arr2);
            if (!LogarithmicUtil.isEqual(arr1,arr2)){
                System.out.println("出错了");
                LogarithmicUtil.printArray(arr1);
                LogarithmicUtil.printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }


}
