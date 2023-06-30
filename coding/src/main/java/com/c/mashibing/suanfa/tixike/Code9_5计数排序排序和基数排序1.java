package com.c.mashibing.suanfa.tixike;

import java.util.Arrays;

/*
 题目1，实现一个基数排序，调用额外空间时使用数组，不使用队列
 */
public class Code9_5计数排序排序和基数排序1 {

    /*
     * @author gengzhihao
     * @date 2023/6/30 11:47
     * @description 题目1
     * @param arr
     **/
    public static void qs1_process1(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }

        //计算最大位数
        int maxBits = 0;
        int temp = 0;
        for (int i = 0; i < arr.length; i++){
            int count = 0;
            temp = arr[i];
            do {
                temp /= 10;
                count++;
            }while (temp != 0);
            maxBits = Math.max(maxBits,count);
        }

        //按位数进行依次循环排序
        int[] help = new int[arr.length];
        for (int bit = 1; bit <= maxBits; bit++){
            //得到等于arr[i]的countEqual数组。
            int[] countEqualBit = new int[10];
            for (int i = 0; i < arr.length; i++){
                countEqualBit[getBit(arr[i],bit)]++;
            }
            int[] countLessAndEqualBit = new int[10];
            //得到小于等于arr[i]的countLessAndEqual[i]
            countLessAndEqualBit[0] = countEqualBit[0];
            for (int i = 1; i < countEqualBit.length; i++){
                countLessAndEqualBit[i] = countLessAndEqualBit[i-1] + countEqualBit[i];
            }

            for (int i = arr.length - 1; i >= 0; i--){
                int value = getBit(arr[i],bit);
                if (countLessAndEqualBit[value] != 0){
                    help[--countLessAndEqualBit[value]] = arr[i];
                }
            }
            //将help数组导入到arr中
            for (int i = 0; i < arr.length; i++){
                arr[i] = help[i];
            }
        }

    }

    //获得对应位bit上的数，最后得到的数，范围为0-9
    private static int getBit(int target, int bit) {
        int result = target % 10;
        for (int i = 1; i < bit; i++){
            target /= 10;
            result = target % 10;
        }
        return result;
    }

    public static void main(String[] args) {
        int testTime = 10000;
        int maxValue = 10000;
        int maxLength = 10000;

        for (int i = 0; i < testTime; i++){
            int[] arr1 = randomArr(maxLength,maxValue);
//            int[] arr1 = {5,4,5};
            int[] arr2 = copyArr(arr1);
            int[] arr3 = copyArr(arr1);
//            System.out.println("当前数组为：");
//            printArr(arr1);
            qs1_process1(arr1);
            Arrays.sort(arr2);
            if (!arrEqualsOrNot(arr1,arr2)){
                System.out.println("基数排序错误");
                System.out.println("原数组为：");
                printArr(arr3);
                System.out.println("正确排序后：");
                printArr(arr2);
                System.out.println("基数排序后：");
                printArr(arr1);
                break;
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
