package com.common.utils;


/**
 * @author gengzhihao
 * @date 2023/4/22 11:24
 * @description 数组排序工具类，用于测试算法
**/

public class SortUtil {

    /*
     * @author gengzhihao
     * @date 2023/4/22 11:23
     * @description 产生随机数组
     * @param maxSize 最大有多少容量
     * @param maxValue 最大值
     * @return int[]
     **/
    public static int[] generateRandomArray(int maxSize, int maxValue){
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++){
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int)(maxValue * Math.random());
        }

        return arr;
    }

    /*
     * @author gengzhihao
     * @date 2023/4/22 11:23
     * @description 打印数组
     * @param arr
     **/
    public static void printArray(int[] arr){
        for (int num : arr){
            System.out.print(num + "  ");
        }
        System.out.println();
    }

    /*
     * @author gengzhihao
     * @date 2023/4/22 11:23
     * @description 复制一个新数组返回
     * @param arr
     * @return int[]
     **/
    public static int[] copyArray(int[] arr){
        int[] ans = new int[arr.length];
        for (int i = 0;i<arr.length;i++){
            ans[i] = arr[i];
        }
        return ans;
    }

    /*
     * @author gengzhihao
     * @date 2023/4/22 11:25
     * @description 判断数组是否为升序
     * @param arr
     * @return boolean
     **/
    public static boolean isSorted(int[] arr){
        if (arr.length<2){
            return true;
        }
        int max = arr[0];
        for (int i = 1; i< arr.length; i++){
            if (max>arr[i]){
                return false;
            }
            max=Math.max(max,arr[i]);
        }
        return true;

    }

    /*
     * @author gengzhihao
     * @date 2023/4/22 11:28
     * @description 判断两个数组是否相等
     * @param arr1
     * @param arr2
     * @return boolean
     **/
    public static boolean isEqual(int[] arr1, int[] arr2){
        if (arr1 == null && arr2 == null){
            return true;
        }
//        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)){
        if (arr1 == null ^ arr2 == null){
            return false;
        }

        if (arr1.length != arr2.length){
            return false;
        }
        for (int i = 0; i< arr1.length; i++){
            if (arr1[i] != arr2[i]){
                return false;
            }
        }
        return true;
    }


    public static void swapValue(int[] array, int i, int j) {
        int middle = array[i];
        array[i] = array[j];
        array[j] = middle;
        return;
    }
}
