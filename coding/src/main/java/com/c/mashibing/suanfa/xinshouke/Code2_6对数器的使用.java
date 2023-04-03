package com.c.mashibing.suanfa.xinshouke;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Code2_6对数器的使用 {


    /*
     * @author gengzhihao
     * @date 2023/4/1 8:58
     * @description 产生随机数组
     * @param maxLen 最大长度
     * @param maxValue 最大值
     * @return int[]
     **/
    public static int[] lenRandomValueRandom(int maxLen,int maxValue){
        int len = (int)(Math.random()*maxLen);
        int[] ans = new int[len];
        for (int i = 0; i< len;i++){
            ans[i] = (int)(Math.random()*maxValue);
        }
        return ans;
    }

    /*
     * @author gengzhihao
     * @date 2023/4/1 8:58
     * @description 返回新生成的一样的随机数组
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
     * @date 2023/4/1 9:06
     * @description 判断数组是否有序（从大到小）
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

//    /*
//     * @author gengzhihao
//     * @date 2023/4/1 9:03
//     * @description 判断两个等长的数组的值是否完全一致。但是有可能两个数组都错了，所以要判断数组是否有序，使用isSorted()方法
//     * @param arr1
//     * @param arr2
//     * @return boolean
//     **/
//    public static boolean equalValues(int[] arr1, int[] arr2){
//        for (int i = 0; i < arr1.length; i++){
//            if (arr1[i] != arr2[i]){
//                return  false;
//            }
//        }
//        return true;
//    }


    /**
     * 通过这种方式，可以用暴力的方式测出各种各样的情景
     * 用于比对算法实现结果是否正确的程序，就是对数器
     * @param args
     */
    public static void main(String[] args) {
        int maxLen = 50;
        int maxValue = 1000;
        int testTime = 10000;
        for (int i = 0;i<testTime;i++){
            int[] arr1 = lenRandomValueRandom(maxLen,maxValue);
            int[] temp = copyArray(arr1);
            Code1_5数组排序.selectSort(arr1);
//            Code1_5数组排序.bublleSort(arr2);
            if (!isSorted(arr1)){
                for(int j = 0; j < temp.length; j++){
                    System.out.print(temp[j] + "  ");
                }
                System.out.println();
                log.info("选择排序错了");
            }
        }
    }
}
