package com.c.mashibing.suanfa.tixike;

/*
 题目1，一个数组，以最后一个元素做为目标值，
 要求将数组排序为 小于目标值 | 等于目标值 | 大于目标值 三个部分
 时间复杂度o(N)，空间复杂度o(1)
 返回等于目标值部分的下标数组，如[2,5]，表示等于部分起始下标为2，结尾下标为5
 */
public class Code6_7荷兰国旗题解 {

    /*
     * @author gengzhihao
     * @date 2023/6/10 9:53
     * @description 题目1
     * @param arr
     **/
    public static void qs1_process1(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }

        int left = -1;
        int right = arr.length - 1;
        int cur = 0;
        int end = arr.length - 1;
        while (cur < arr.length){
            if (arr[cur] < arr[end]){
                swap(arr,cur++,++left);
            }
            else if (arr[cur] == arr[end]){
                cur++;
            }
            else if (arr[cur] > arr[end]){
                swap(arr,cur,--right);
            }
        }
        swap(arr,end,right);
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
