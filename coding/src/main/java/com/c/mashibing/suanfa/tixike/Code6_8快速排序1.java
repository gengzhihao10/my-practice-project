package com.c.mashibing.suanfa.tixike;

import java.util.Stack;

/*
TODO
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
    public static void qs1_process(int[] arr){
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
        while (cur < end){
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
        swap(arr,end,right++);

        //递归行为
        if (left >= 0){
            process1(arr,L,left);
        }
        if (right <= arr.length){
            process1(arr,right,R);
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
            int left = task.L - 1;
            int right = task.R;
            int cur = task.L;
            int end = task.R;
            swap(arr,R,L+(int)((R-L)*Math.random()));
            while (cur < end){
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
            if (left >= 0){
                stack.push(new Task(0, left));
            }
            if (right <= arr.length){
                stack.push(new Task(right, R));
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

    }
}
