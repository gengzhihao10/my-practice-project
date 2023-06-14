package com.c.mashibing.suanfa.tixike;

import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

/*
 题目1，快速排序，递归版本
 逻辑类似于荷兰国旗
 题目2，快速排序，非递归版本
 对数器
 */
public class Code6_8快速排序1 {

    /*
     * @author gengzhihao
     * @date 2023/6/10 10:56
     * @description 题目1
     * @param arr
     **/
    public static void qs1_process1(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }

        process1(arr,0,arr.length-1);
    }

    //返回等于范围的L和R构成的2个元素的数组
    private static int[] process1(int[] arr, int L, int R) {
        //中止条件
        if (L == R){
            return new int[]{L,R};
        }
        if (L >= R){
            return new int[]{-1,-1};
        }



        //处理
        int left = L - 1;
        int right = R;
        int cur = L;
        int end = R;
        //将数组最后一个元素和随机一个元素进行替换
        swap(arr,R,L+(int)((R-L)*Math.random()));

        /**
         * 数组从L..R分为了四个部分
         * left及其左侧，为小于end的部分
         * left+1到cur-1，为等于end的部分
         * cur到right-1，为未知的部分
         * right及其右侧(不包括end)，为大于end的部分
         * 当未知区域消失时，即是小于、等于、大于区(不包括end，需要swap)划分结束之时
         * 所以，当未知区域的左边界和大于区域左边界重合时
         * 即，当cur==right，划分逻辑结束
         *
         * 执行划分逻辑后，对end与right进行swap，此时
         * 等于区的范围变为left+1到right
         * 大于区的范围变为right +1及右侧
         */
        while (right != cur ){
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

        //递归行为
        if (left >= L){
            process1(arr,L,left);
        }
        if (right+1 <= R){
            process1(arr,right+1,R);
        }
        return new int[]{left+1, right-1};

    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

//********************************************************************************************


    /*
     * @author gengzhihao
     * @date 2023/6/10 11:20
     * @description 题目2
     * @param arr
     **/
    public static void qs2_process1(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }

        process2(arr,0,arr.length-1);
    }

    private static void process2(int[] arr, int L, int R) {
        //先产生一个任务
        Stack<Task> stack = new Stack<>();
        stack.push(new Task(L,R));

        //进行循环
        while (!stack.isEmpty()){
            Task task = stack.pop();
            //对应process1里的中止条件
            if (task.L < task.R){
                int left = task.L - 1;
                int right = task.R;
                int cur = task.L;
                int end = task.R;
                swap(arr,task.R,task.L+(int)((task.R-task.L)*Math.random()));
                while (cur != right){
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
                //小于区的右边界left如果小于L，则不存在小于区
                if (left >= task.L){
                    stack.push(new Task(task.L, left));
                }
                //同理，大于区的左边界right+1如果大于R，不存在大于区
                if (right + 1 <= task.R ){
                    stack.push(new Task(right+1, task.R));
                }
            }
        }

    }

    //要进行的任务，L和R表示数组的索引范围
    static class Task{
        int L;
        int R;

        public Task(int l, int r) {
            L = l;
            R = r;
        }
    }

//*****************************************************************************************************

    public static void main(String[] args) {
        int testTime = 1000;
        int maxValue = 1000;
        int maxLength = 1000;

        for (int i = 0; i < testTime; i++){
            int[] arr1 = randomArr(maxLength,maxValue);
//            int[] arr1 = {4,2,8,4};
            int[] arr2 = copyArr(arr1);
            int[] arr3 = copyArr(arr1);
//            System.out.println("当前数组为：");
//            printArr(arr1);
            qs1_process1(arr1);
            Arrays.sort(arr2);
            if (!arrEqualsOrNot(arr1,arr2)){
                System.out.println("快速排序错误");
                System.out.println("原数组为：");
                printArr(arr3);
                System.out.println("快速排序后：");
                printArr(arr1);

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
