package com.c.mashibing.suanfa.xinshouke;

import com.common.utils.SortUtil;

import java.util.Stack;

/**
 * 快速排序
 */
public class Code8_5快排 {



    public static void splitNum1(int[] arr){
        //小于等于区的右边界
        int lessEqualR = -1;
        int index = 0;
        int mostR = arr.length - 1;
        while (index < arr.length){
            if (arr[index] <= arr[mostR]){
                swap(arr,++lessEqualR, index++);
            }else {
                index++;
            }
        }
    }

    public static void splitNum2(int[] arr){
        int N = arr.length;
        //小于区的右边界
        int lessR = -1;
        //大于区的左边界
        int moreL = N - 1;
        int index = 0;
        while (index < moreL){
            if (arr[index] < arr[N - 1]){
                swap(arr,++lessR, index++);
            }
            else if (arr[index] > arr[N - 1]){
                swap(arr, --moreL, index);
            }
            //等于划分值得
            else {
                index++;
            }
        }
        //大于区的第一个位置和划分值交换
        swap(arr,moreL,N-1);
    }

    //交换数组两个元素的值
    private static void swap(int[] arr, int i, int index) {
        int temp = arr[i];
        arr[i] = arr[index];
        arr[index] = temp;
    }

    /*
     * @author gengzhihao
     * @date 2023/4/25 12:15
     * @description arr[L...R]范围上，拿arr[R]做划分值
     * @param arr
     * @param L
     * @param R
     * @return int[] 只包含两个数，为等于区域的起始和结尾坐标。如[5,8]表示等于区域的下标从5到8
     **/
    public static int[] partition(int[] arr, int L, int R) {
        int lessR = L - 1;
        int moreL = R;
        int index = L;
        while (index < moreL){
            if (arr[index] < arr[R]){
                swap(arr,++lessR, index++);
            }
            else if (arr[index] > arr[R]){
                swap(arr, --moreL, index);
            }
            //等于划分值得
            else {
                index++;
            }
        }
        swap(arr,moreL,R);
        return new int[]{lessR+1,moreL};
    }

    //快速排序，递归版本
    public static void quickSort1(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        process1(arr,0, arr.length - 1);
    }

    public static void process1(int[] arr, int L, int R){
        if (L >= R){
            return;
        }

        //L < R
        int[] equalE = partition(arr, L, R);
        //equalE[0]等于区域第一个数下标
        //equalE[1]等于区域最后一个数下标
        process1(arr, L, equalE[0] - 1);
        process1(arr, equalE[1] + 1, R);
    }


    //快速排序
    public static void quickSort2(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }

        Stack<Job> stack = new Stack<>();
        stack.push(new Job(0,arr.length - 1));
        while (!stack.isEmpty()){
            Job cur = stack.pop();
            int L = cur.L;
            int R = cur.R;
            int[] equals = partition(arr, cur.L, cur.R);
            //如果存在小于区域
            if (equals[0] > cur.L){
                stack.push(new Job(cur.L, equals[0] - 1));
            }
            //如果存在大于区域
            if (equals[1] < cur.R){
                stack.push(new Job(equals[1] + 1,cur.R));
            }
        }
    }


    public static class Job{
        public int L;
        public int R;

        public Job(int l, int r) {
            L = l;
            R = r;
        }
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++){
            int[] arr1 = SortUtil.generateRandomArray(maxSize, maxValue);
            int[] arr2 = SortUtil.copyArray(arr1);
//            mergeSort2(arr2);
            quickSort1(arr1);
            quickSort2(arr2);
            if (!SortUtil.isEqual(arr1,arr2)){
                System.out.println("出错了");
                SortUtil.printArray(arr1);
                SortUtil.printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
