package com.c.mashibing.suanfa.tixike;

public class Code2_3选择排序冒泡排序 {

    //选择排序
    public static void selectSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }

        for (int i = 0; i < arr.length; i++){
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++){
                minIndex = arr[minIndex] <= arr[j] ? minIndex : j;
            }
            swap(arr,i,minIndex);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //冒泡排序
    public static void bubbleSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }

        for (int i = arr.length-1; i > 0; i--){
            for (int j =0; j < i; j++){
                if (arr[j] > arr[j+1]){
                    swap(arr,j,j+1);
                }
            }

        }
    }
}
