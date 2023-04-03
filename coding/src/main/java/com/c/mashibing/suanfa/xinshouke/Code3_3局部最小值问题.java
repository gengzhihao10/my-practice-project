package com.c.mashibing.suanfa.xinshouke;

/*
 * @author gengzhihao
 * @date 2023/4/3 11:26
 * @description 有一个数组，无序，但是任意两个不相邻的数不相等
 * 局部最小值定义：
 * [0] < [1] [0]局部最小（[0]左边没数）
 * [N-2]>[N-1] [N-1]局部最小（[N-1]右边没数）
 * [i-1]>[i]<[i+1] [i]局部最小
 * 要求返回一个局部最小值，使用二分法
 **/
public class Code3_3局部最小值问题 {


    /*
     * @author gengzhihao
     * @date 2023/4/3 11:45
     * @description
     * @param arr 整体无序，相邻的数不相等
     * @return int 最小值下标
     **/
    public static int oneMinIndex(int[] arr){
        if (arr == null || arr.length == 0){
            return -1;
        }
        if (arr.length == 1){
            return 0;
        }
        int N = arr.length;
        if (arr[0] < arr[1]){
           return 0;
        }
        if (arr[N-2]>arr[N-1]){
            return N-1;
        }
        int L = 0;
        int R = N-1;
        int mid = -1;

        //当L到R有三个及以上的熟(L,R-1,R)时，走这个逻辑验证才能不会数组下标越界。走完这个逻辑，当L到R只有L和R两个数时，走另外一个逻辑
        while (L < R-1){
            mid = (L+R)/2;

            if (arr[mid] < arr[mid-1] && arr[mid] < arr[mid+1]){
                return mid;
            }

            /**
             * 总共四种情况，
             * 1 arr[mid-1] > arr[mid] < arr[mid+1] 这个是最小值，在上面的判断中已经break
             * 2 arr[mid-1] < arr[mid] > arr[mid+1] 最大值的情况
             * 3 arr[mid-1] > arr[mid] > arr[mid+1] 从左到右上升
             * 4 arr[mid-1] < arr[mid] < arr[mid+1] 从左到右下降
             * 剩下的三种情况，可以通过三个if去判断。
             * 这里我们简化了逻辑，2和3的情况，都是左侧上升，左侧必然有最小值，都需要R=mid-1
             * 4是左侧下降，只可能右侧有最小值，需要L=mid+1。
             * 所以简化成如下代码的逻辑
             */
            if (arr[mid] > arr[mid-1]){
                R = mid-1;
                //加了continue关键字，后面的if前面就不用加else了，不然必须加个else才能满足两个不同条件的排他性
                continue;
            }else {
                L = mid + 1;
                continue;
            }
        }

        return arr[L] < arr[R] ? L : R;
    }

    /*
     * @author gengzhihao
     * @date 2023/4/3 14:18
     * @description 生成随机数组，且相邻数不相等
     * @param maxLen
     * @param maxValue
     * @return int[]
     **/
    public static int[] randomArray(int maxLen, int maxValue){
        int len = (int)(Math.random() * maxLen);
        int[] arr = new int[len];

        if (len > 0){
            arr[0] = (int)(Math.random() * maxLen);
            //i=1时，如果len也为1，则不会进入for循环。如果i的初始值为0会出现下标越界问题
            for (int i = 1; i<len; i++){
                do {
                    arr[i] = (int)(Math.random() * maxLen);
                }
                //如果条件的结果为true，则继续循环，直到arr[i]不等于arr[i-1]
                while (arr[i] == arr[i-1]);
            }

        }
        return arr;
    }

    /*
     * @author gengzhihao
     * @date 2023/4/3 14:23
     * @description 验证是否局部最小
     * @param arr
     * @param minIndex
     * @return boolean
     **/
    public static boolean check(int[] arr, int minIndex){
        if (arr.length == 0){
            return minIndex == -1;
        }

        int left = minIndex -1;
        int right = minIndex +1;
        boolean leftBigger = left >= 0 ? arr[left]>arr[minIndex] : true;
        boolean rightBigger = right < arr.length ? arr[right]>arr[minIndex] : true;
        return leftBigger && rightBigger;
    }

    public static void printArray(int[] arr){
        for (int num : arr){
            System.out.print(num + "  ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        int maxLen = 5;
        int maxValue = 20;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++){
            int[] arr = randomArray(maxLen,maxValue);
            int ans= oneMinIndex(arr);
            printArray(arr);

            if (!check(arr,ans)){
                printArray(arr);
                System.out.println(ans);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
