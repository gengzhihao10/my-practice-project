package com.c.mashibing.suanfa.tixike;

import com.common.utils.LogarithmicUtil;

/*
todo
 题目1，有一个现成的数组，对其进行堆排序。最主要的是要实现对大根堆向上调整heapinsert和向下调整heapify两个操作
 题目2，实现一个堆
 题目3，有一个几乎有序的数组，几乎有序指的是从无序到有序，最多移动k个数，且k远小于数组长度N，对齐进行排序
 使用堆排序，可以做到复杂度为o(N * log(k+1))
 */
public class Code7_9堆排序1 {

    /*
     * @author gengzhihao
     * @date 2023/6/19 10:30
     * @description 题目1
     * 对于任意一个大根堆的数组中下标为i的数，左孩子下标为2*i，右孩子下标为2*i+1，父节点为i/2
     * @param arr
     **/
    public static void qs1_process1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int headSize = 0;
        //调整成大根堆
        while (++headSize <= arr.length) {
            heapInsert(arr, headSize - 1);
        }

    }

    private static void heapInsert(int[] arr, int cur) {
        while (cur != 0) {
            int parent = (cur-1) / 2;
            if (arr[parent] < arr[cur]) {
                swap(arr, parent, cur);
                cur = parent;
            }
            else {
                break;
            }
        }
    }

    public static void heapify(int[] arr, int heapSize){
        int cur = 0;
        while (cur <= heapSize){
            int biggerChild = arr[2 * cur] > arr[2 * cur + 1] ? 2 * cur : 2 * cur + 1;
            if (arr[cur] < arr[biggerChild]){
                swap(arr,cur,biggerChild);
                cur = biggerChild;
            }
            else {
                break;
            }
        }
    }

    private static void swap(int[] arr, int parent, int cur) {
        int temp = arr[parent];
        arr[parent] = arr[cur];
        arr[cur] = temp;
    }


//*****************************************************************************************

    public static void main(String[] args) {
        int testTime = 100000;
        int maxLength = 5;
        int maxValue = 9;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = randomArray(maxLength, maxValue);
//            int[] arr1 = {4,1,8,5};
            int[] arr2 = copyArray(arr1);
            int[] temp = copyArray(arr1);
//            LogarithmicUtil.printArray(temp);
            qs1_process1(arr1);
            bubbleSort(arr2);
            if (!equalOrNot(arr1, arr2)) {
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
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1 == null || arr2 == null) {
            return false;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
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
        int length = (int) (Math.random() * maxLength);
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * maxValue);
        }
        return arr;
    }

    public static void bubbleSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }

        for (int i = arr.length-1; i > 0; i--){
            for (int j =0; j < i; j++){
                if (arr[j] < arr[j+1]){
                    swap(arr,j,j+1);
                }
            }

        }
    }
}