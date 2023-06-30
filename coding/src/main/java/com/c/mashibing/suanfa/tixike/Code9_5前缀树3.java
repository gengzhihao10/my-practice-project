package com.c.mashibing.suanfa.tixike;

import java.util.Arrays;

/*
 题目1，实现一个计数排序，输入的数组里的元素是有一个范围的。
 */
public class Code9_5前缀树3 {

    /*
     * @author gengzhihao
     * @date 2023/6/30 10:19
     * @description 题目1
     * @param arr
     **/
    public static void qs1_process1(int[] arr){
        if (arr == null || arr.length == 0){
            return;
        }

        //1.拿到最大最小值
        int min = arr[0];
        int max = arr[0];
        for (int i = 0; i < arr.length; i++){
            if (arr[i] < min){
                min = arr[i];
            }
            if (arr[i] > max){
                max = arr[i];
            }
        }

        //2，准备help数组，统计arr中数的个数
        int[] help = new int[max-min+1];
        for (int i = 0; i < arr.length; i++){
            help[arr[i]-min]++;
        }

        //3,将help中的元素倒回arr中，使之有序
        int index = 0;
        for (int i = 0; i < help.length; i++){
            int count = help[i];
            int value = i+min;
            while (count != 0){
                arr[index++] = value;
                count--;
            }
        }
    }

    public static void main(String[] args) {
        int testTime = 1000;
        int maxValue = 1000;
        int maxLength = 1000;

        for (int i = 0; i < testTime; i++){
            int[] arr1 = randomArr(maxLength,maxValue);
//            int[] arr1 = {4,2,8,4};
            int[] arr2 = copyArr(arr1);
            int[] arr3 = copyArr(arr1);
//            System.out.println("当前数组为：");
//            printArr(arr1);
            qs1_process1(arr1);
            Arrays.sort(arr2);
            if (!arrEqualsOrNot(arr1,arr2)){
                System.out.println("快速排序错误");
                System.out.println("原数组为：");
                printArr(arr3);
                System.out.println("快速排序后：");
                printArr(arr1);

            }
        }
    }

    //打印数组
    private static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + "  ");
        }
        System.out.println();
    }

    //判断两个数组是否相等
    private static boolean arrEqualsOrNot(int[] arr1, int[] arr2) {
        if (arr1 == null && arr2 != null){
            return false;
        }
        if (arr2 == null && arr1 != null){
            return false;
        }
        if (arr1.length != arr2.length){
            return false;
        }

        for (int i = 0; i < arr1.length; i++){
            if (arr1[i] != arr2[i]){
                return false;
            }
        }
        return true;

    }

    //产生随机数组
    private static int[] copyArr(int[] arr) {
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++){
            result[i] = arr[i];
        }
        return result;
    }

    //产生随机数组
    private static int[] randomArr(int maxLength, int maxValue) {
        int length = 0;
        do {
            length = (int)(Math.random() * maxLength);
        }while (length == 0);

        int[] arr = new int[length];
        for (int i = 0; i<length; i++){
            arr[i] = (int)(Math.random() * maxValue);
        }
        return arr;
    }
}
