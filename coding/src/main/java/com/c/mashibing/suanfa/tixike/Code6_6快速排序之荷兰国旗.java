package com.c.mashibing.suanfa.tixike;

/*
 题目1，一个数组，要求将其排序后，结果为<=目标值的放在左侧，>目标值的放在右侧
 要求时间复杂度o(N)，空间复杂度o(1)
 题目2，类似于题目1，要求将数组分为 小于目标值 | 等于目标值 | 大于目标值 三个部分
 时间、空间复杂度同题目1
 */
public class Code6_6快速排序之荷兰国旗 {

    /*
     * @author gengzhihao
     * @date 2023/6/10 9:02
     * @description 题目1
     * @param arr
     * @param target
     **/
    public static void qs1_process1(int[] arr, int target){
        if (arr == null || arr.length < 2){
            return;
        }

        int left = -1;
        int cur = 0;
        while (cur <arr.length){
            if (arr[cur] <= target){
                swap(arr,cur++,++left);
            }else if (arr[cur] > target){
                cur++;
            }
        }
    }

    //交换值
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /*
     * @author gengzhihao
     * @date 2023/6/10 9:22
     * @description 题目2
     * @param arr
     * @param target
     **/
    public static void qs2_process1(int[] arr, int target){
        if (arr == null || arr.length < 2){
            return;
        }

        int left = -1;
        int right = arr.length;
        int cur = 0;
        while (cur < arr.length){
            if (arr[cur] < target){
                swap(arr,cur++,++left);
            }
            else if (arr[cur] == target){
                cur++;
            }
            else if (arr[cur] > target){
                swap(arr,cur,right--);
            }
        }
    }


}
