package com.c.mashibing.suanfa.tixike;

import com.common.utils.LogarithmicUtil;

import java.util.Arrays;

/*
todo
 题目1，归并排序递归，并写出对数器
 题目2，归并排序循环

 */
public class Code5_1归并排序 {


    /*
     * @author gengzhihao
     * @date 2023/5/24 9:01
     * @description 题目1
     * @param arr
     **/
    public static void qs1_process1(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }

        process1(arr,0,arr.length-1);
    }

    private static void process1(int[] arr, int L, int R) {
        //中止条件
        if (L == R){
            return;
        }

        //返回条件
        int M = L + (R-L)/2;
        process1(arr,L,M);
        process1(arr,M+1,R);
        merge(arr,L,R,M);
    }

    private static void merge(int[] arr, int L, int M, int R) {
        int p1 = L;
        int p2 = M+1;
        int[] help = new int[R-L+1];
        int i = 0;
        while (p1 <= M && p2 <= R){
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M){
            help[i++] = arr[p1++];
        }
        while (p2 <= R){
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++){
            arr[L+i] = help[i];
        }
    }
    //**********************************************************************

    /*
     * @author gengzhihao
     * @date 2023/5/24 10:01
     * @description 题目2
     * @param arr
     **/
    public static void qs2_process1(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }

        int step = 1;
        int N = arr.length;
        int L = 0, M = 0, R = 0;
//        int[] help = new int[N];
        //步长如果大于N，说明在step还没有*2的时候，已经对整个长度为N的数组进行了排序
        while (step < N){
            L = 0;
            //如果左组存在
            while (L < N){
                //如果右组左边界超过了最大下标N-1，break。等同于L+step>N-1 <=> 右组左边界>N,即 M+1>N-1
                if (step > N - 1 - L){
                    break;
                }
                M = L + step -1;
                R = M + Math.min(N - 1 - M,step);
                merge(arr,L,M,R);
                //更新下一次左组的左边界位置
                L = R + 1;
//                LogarithmicUtil.printArray(arr);
            }
            if (step > N/2){
                break;
            }
            step <<= 1;
        }
    }




    //************************************************************************


    public static void main(String[] args) {
        int testTime = 100000;
        int maxLength = 1000;
        int maxValue = 1000;
        for (int i = 0; i < testTime; i++){
            int[] arr1 = randomArray(maxLength,maxValue);
//            int[] arr1 = {4,1,8,5,2,7,9,3};
            int[] arr2 = copyArray(arr1);
            int[] temp = copyArray(arr1);
//            LogarithmicUtil.printArray(temp);
            qs2_process1(arr1);
            Arrays.sort(arr2);
            if (!equalOrNot(arr1,arr2)){
                System.out.println("错误啦");
                System.out.print("原数组：");
                LogarithmicUtil.printArray(temp);
                System.out.print("错误排序后：");
                LogarithmicUtil.printArray(arr1);
                System.out.println();
            }
        }
    }

    private static boolean equalOrNot(int[] arr1, int[] arr2) {
        if (arr1 == null && arr2 == null){
            return true;
        }
        if (arr1 == null || arr2 == null){
            return false;
        }
        if (arr1.length != arr2.length){
            return false;
        }
        for (int i = 0; i<arr1.length; i++){
            if (arr1[i] != arr2[i]){
                return false;
            }
        }
        return true;
    }

    private static int[] copyArray(int[] arr1) {
        int[] result = new int[arr1.length];
        System.arraycopy(arr1, 0, result, 0, arr1.length);
        return result;
    }

    private static int[] randomArray(int maxLength, int maxValue) {
        int length = (int)(Math.random() * maxLength);
        int[] arr = new int[length];
        for (int i = 0; i < length; i++){
            arr[i] = (int)(Math.random() * maxValue);
        }
//        LogarithmicUtil.printArray(arr);
        return arr;
    }
}
