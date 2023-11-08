package com.c.mashibing.suanfa.tixike;

/*
todo
 题目1，
 给定一个只包含正数的数组arr，arr中任何一个子数组sub，
 一定都可以算出(sub累加和 )* (sub中的最小值)是什么，
 那么所有子数组中，这个值最大是多少？
 要求子数组必须是连续的

 */
public class Code26_2子数组的最大值 {

    /*
     * @author gengzhihao
     * @date 2023/11/8 9:50
     * @description 题目1
     * @param arr
     * @return int
     **/
    public static int max2(int[] arr){
        return 0;
    }


    public static int[] gerenareRondomArray() {
        int[] arr = new int[(int) (Math.random() * 20) + 10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 101);
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTimes = 2000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = gerenareRondomArray();
            if (max1(arr) != max2(arr)) {
                System.out.println("FUCK!");
                break;
            }
        }
        System.out.println("test finish");
    }

    public static int max1(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int minNum = Integer.MAX_VALUE;
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                    minNum = Math.min(minNum, arr[k]);
                }
                max = Math.max(max, minNum * sum);
            }
        }
        return max;
    }

}
