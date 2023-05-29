package com.c.mashibing.suanfa.tixike;

/*
todo
 题目1，有一个数组，统计数组元素中，在元素右侧*2还小于元素的个数
 例如，[2,7,1,4,8,3]，对于7来说，符合要求的有1，3.
 */
public class Code5_7BiggerThanRightTwice {

    /*
     * @author gengzhihao
     * @date 2023/5/29 10:49
     * @description 题目1
     * @param arr
     * @return int
     **/
    public static int qs1_process1(int[] arr){
        if (arr == null || arr.length < 2){
            return 0;
        }

        return process1(arr,0,arr.length-1);
    }

    //返回arr[L-R]上符合条件的元素个数
    private static int process1(int[] arr, int L, int R) {
        //中止条件
        if (R-L == 1){
            return 0;
        }

        //递归行为
        int M = L + (R-L)>>1;
        return process1(arr,L,M) + process1(arr,M+1,R) + merge(arr,L,M,R);
    }

    private static int merge(int[] arr, int L, int M, int R) {
        int p1 = M;
        int p2 = R;
        int[] help = new int[R-L+1];
        int i = R-L;

        //遍历左组，看右组范围上有多少符合条件的数
        //求L-R范围内，符合条件元素的个数sum
        int sum = 0;
        while (p1 >= L && p2 >= M+1){
            if (arr[p1] > 2 * arr[p2]){
                sum += p2 - M;
                p1--;
                p2 = R;
            }else {
                p2--;
            }
        }

        //使L-R范围内数组变得有序
        while (p1 >= L && p2 >= M+1){
            help[i--] = arr[p1] < arr[p2] ? arr[p2--]: arr[p1--];
        }

        while (p1 >= L){
            help[i--] = arr[p1--];
        }

        while (p2 >= M+1){
            help[i--] = arr[p2--];
        }

        for (i = 0; i < help.length; i++){
            arr[L+i] = help[i];
        }

        return sum;

    }
}
