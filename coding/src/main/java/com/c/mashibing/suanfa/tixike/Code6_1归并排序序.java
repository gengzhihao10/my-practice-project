package com.c.mashibing.suanfa.tixike;

/*
 题目1，给定一个数组arr，两个整数lower和upper，
 返回arr中有多少个子数组的累加和在[lower, upper]的范围上
 求sum数组右组－左组有多少个子数组符合要求
 lower < sum[R] - sum[L] < upper //sum[L]表示左组中的元素，sum[R]表示右组中的元素
 -lower > sum[L] - sum[R] > -upper
 sum[R] - lower > sum[L] > sum[R] - upper
 sum[R] - upper < sum[L] < sum[R] - lower
       newLower < sum[L] < newUpper
 解决答题和对数器的bug
 */
public class Code6_1归并排序序 {

    /*
     * @author gengzhihao
     * @date 2023/6/6 9:29
     * @description 题目1
     * @param arr 要求都是正数
     * @param lower
     * @param upper
     **/
    public static int qs1_process1(int[] arr, int lower, int upper){
        if (arr == null || arr.length == 0){
            return 0;
        }
        //计算得到求和数组
        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++){
            sum[i] = sum[i - 1] + arr[i];
        }

        return process1(sum,lower,upper,0,arr.length-1);
    }

    //主体方法，通过归并排序的方式，得到结果
    private static int process1(int[] sum, int lower, int upper,int L,int R) {
        //如果只有一个元素
        if (L == R){
            return (sum[L] >= lower && sum[L] <= upper) ? 1 : 0;
        }
        int M = L + (R-L)/2;
        return process1(sum,lower,upper,L,M)
                +process1(sum,lower,upper,M+1,R)
                +merge(sum,lower,upper,L,R,M);

    }

    //1,计算得到符合要求元素的个数，2，进行归并排序的merge
    private static int merge(int[] sum, int lower, int upper, int L, int R,int M) {
        //1,计算得到符合要求元素的个数
        int windowL = L;
        int windowR = L;
        int result = 0;
        for (int i = M+1; i <= R; i++){
            int newLower = sum[i]-upper;
            int newUpper = sum[i]-lower;
            //形成一个左闭右开的[newLower,newUpper)的范围
            while (windowL <= M && sum[windowL] < newLower){
                windowL++;
            }
            while (windowR <= M && sum[windowR]<= newUpper){
                windowR++;
            }
            result += windowR - windowL;
        }

        //2, 进行merge
        int p1 = L;
        int p2 = M+1;
        int[] help = new int[R-L+1];
        int i = 0;
        while (p1 <= M && p2 <= R){
            help[i++] = sum[p1] < sum[p2] ? sum[p1++] : sum[p2++];
        }

        while (p1 <= M){
            help[i++] = sum[p1++];
        }

        while (p2 <= R){
            help[i++] = sum[p2++];
        }

        for (i = 0; i < R-L; i++){
            sum[L+i] = help[i];
        }

        return result;
    }


    //对数器
    public static void main(String[] args) {
        int testTime = 1000;
        int maxArrLength = 20;
        int maxValue = 100;
        for (int i = 0; i < testTime; i++){
            int[] arr1 = generateArr(maxArrLength,maxValue);
//            int[] arr1 = {6,9,9};
            int[] arr2 = copyArr(arr1);
            int[] arr3 = copyArr(arr1);
            int arrSum = getArrSum(arr1);
            int lower = (int)(Math.random()*arrSum);
            int upper = (int)(Math.random()*arrSum);
//            int lower = 7;
//            int upper = 12;
            if (lower > upper){
                int temp = lower;
                lower = upper;
                upper = temp;
            }
            int result1 = qs1_process1(arr1,lower,upper);
            int result2 = getSumNum(arr2,lower,upper);
            if (result1 != result2){
                System.out.println("出错啦");
                System.out.print("原数组为 ");
                printArr(arr3);
                System.out.println("lower "+lower + " upper "+upper);
                System.out.println("result1 "+result1 + " result2 " + result2);
            }
        }
    }

    //返回符合题目1要求的个数
    private static int getSumNum(int[] arr, int lower, int upper) {
        int result = 0;
        for (int i = 0; i < arr.length; i++){
            int sum = 0;
            for (int j = i; j < arr.length; j++){
                sum += arr[j];
                if (sum >= lower && sum <= upper){
                    result++;
                }
            }
        }
        return result;
    }

    //复制数组并返回
    private static int[] copyArr(int[] arr) {
        int[] result = new int[arr.length];
        for (int i = 0; i<arr.length; i++){
            result[i] = arr[i];
        }
        return result;
    }

    //产生随机数组
    private static int[] generateArr(int maxArrLength, int maxValue) {
        int length = 0;
        do {
            length = (int)(Math.random()*maxArrLength);
        }while (length == 0);

        int[] arr = new int[length];
        for (int i = 0; i<length; i++){
            arr[i] = (int)(Math.random()*maxValue);
        }
        return arr;
    }

    //求数组arr的整体累加和
    private static int getArrSum(int[] arr) {
        if (arr == null || arr.length == 0){
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++){
            sum += arr[i];
        }
        return sum;
    }

    public static void printArr(int[] arr){
        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
