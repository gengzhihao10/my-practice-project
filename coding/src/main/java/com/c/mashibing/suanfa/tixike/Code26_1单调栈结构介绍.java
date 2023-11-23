package com.c.mashibing.suanfa.tixike;


import javax.print.attribute.IntegerSyntax;
import java.util.ArrayList;
import java.util.Stack;

/*
 题目1，数组arr中没有重复值，实现一个单调栈
 题目2，数组arr中有重复值，实现一个单调栈
 */
public class Code26_1单调栈结构介绍 {

    /*
     * @author gengzhihao
     * @date 2023/11/6 11:47
     * @description 题目1
     * @param arr
     * @return int[][]
     **/
    public static int[][] getNearLessNoRepeat(int[] arr){
        if (arr == null || arr.length == 0){
            return null;
        }

        int[][] res = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();

        //遍历数组，填充stack
        for (int i = 0; i < arr.length; i++){
            //stack栈顶的索引对应的数值是否大于arr[i]，满足条件就该弹出来并且结算了
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]){
                int popIndex = stack.pop();
                //左侧小于popIndex对应数值的数的索引
                if (!stack.isEmpty()){
                    res[popIndex][0] = stack.peek();
                }else {
                    res[popIndex][0] = -1;
                }
                //右侧小于popIndex对应数值的数的索引
                res[popIndex][1] = i;
            }
            stack.add(i);
        }

        //把stack剩下的索引对应的数值都结算
        while (!stack.isEmpty()){
            int popIndex = stack.pop();
            //左侧小于popIndex对应数值的数的索引
            if (!stack.isEmpty()){
                res[popIndex][0] = stack.peek();
            }else {
                res[popIndex][0] = -1;
            }
            //右侧小于popIndex对应数值的数的索引
            res[popIndex][1] = -1;
        }
        return res;
    }

    /*
     * @author gengzhihao
     * @date 2023/11/6 11:47
     * @description 题目2
     * @param arr
     * @return int[][]
     **/
    public static int[][] getNearLess(int[] arr){
        if (arr == null || arr.length == 0){
            return null;
        }

        int[][] res = new int[arr.length][2];
        Stack<ArrayList<Integer>> stack = new Stack<>();

//        LogarithmicUtil.printArray(arr);
//        System.out.println("进入for循环");
        //遍历数组，填充stack
        for (int i = 0; i < arr.length; i++){
            //stack不为空
            while (!stack.isEmpty()){
                //stack栈顶的索引对应的数值是否大于arr[i]，满足条件就该弹出来并且结算了
                if ( arr[stack.peek().get(0)] > arr[i]){
                    ArrayList<Integer> popList = stack.pop();
                    for (int popIndex : popList){
                        //左侧小于popIndex对应数值的数的索引
                        if (!stack.isEmpty()){
                            //注意，结算的时候，拿的是list末尾的索引，才是最靠近索引i的索引值
                            res[popIndex][0] = stack.peek().get(stack.peek().size() - 1);
                        }else {
                            res[popIndex][0] = -1;
                        }
                        //右侧小于popIndex对应数值的数的索引
                        res[popIndex][1] = i;
                    }
                }
                //stack栈顶的索引对应的数值是否小于arr[i]，满足条件就break，中止循环
                else {
                    break;
                }
            }

            //stack栈顶的索引对应的数值是否等于arr[i]，满足条件就加到栈顶list里
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]){
                stack.peek().add(i);
            }
            //不等于，就正常new一个list加到栈顶
            else {
                ArrayList<Integer> newList = new ArrayList<>();
                newList.add(i);
                stack.add(newList);
            }
        }
//        System.out.println("离开for循环");

        while (!stack.isEmpty()){
            ArrayList<Integer> popList = stack.pop();
            for (int popIndex : popList){
                //左侧小于popIndex对应数值的数的索引
                if (!stack.isEmpty()){
                    res[popIndex][0] = stack.peek().get(stack.peek().size() - 1);
                }else {
                    res[popIndex][0] = -1;
                }
                //右侧小于popIndex对应数值的数的索引
                res[popIndex][1] = -1;
            }
        }
        return res;
    }

    // for test
    public static int[] getRandomArrayNoRepeat(int size) {
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        for (int i = 0; i < arr.length; i++) {
            int swapIndex = (int) (Math.random() * arr.length);
            int tmp = arr[swapIndex];
            arr[swapIndex] = arr[i];
            arr[i] = tmp;
        }
        return arr;
    }

    // for test
    public static int[] getRandomArray(int size, int max) {
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return arr;
    }

    // for test
    public static int[][] rightWay(int[] arr) {
        int[][] res = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            int leftLessIndex = -1;
            int rightLessIndex = -1;
            int cur = i - 1;
            while (cur >= 0) {
                if (arr[cur] < arr[i]) {
                    leftLessIndex = cur;
                    break;
                }
                cur--;
            }
            cur = i + 1;
            while (cur < arr.length) {
                if (arr[cur] < arr[i]) {
                    rightLessIndex = cur;
                    break;
                }
                cur++;
            }
            res[i][0] = leftLessIndex;
            res[i][1] = rightLessIndex;
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[][] res1, int[][] res2) {
        if (res1.length != res2.length) {
            return false;
        }
        for (int i = 0; i < res1.length; i++) {
            if (res1[i][0] != res2[i][0] || res1[i][1] != res2[i][1]) {
                return false;
            }
        }

        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int size = 10;
        int max = 20;
        int testTimes = 2000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = getRandomArrayNoRepeat(size);
            int[] arr2 = getRandomArray(size, max);
//            int[] arr2 = {1,11,1,5};
//            if (!isEqual(getNearLessNoRepeat(arr1), rightWay(arr1))) {
//                System.out.println("Oops!");
//                printArray(arr1);
//                break;
//            }
            if (!isEqual(getNearLess(arr2), rightWay(arr2))) {
                System.out.println("Oops!");
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
