package com.c.mashibing.suanfa.tixike;

/*
 题目1，
 给定一个正数数组arr，请把arr中所有的数分成两个集合
 如果arr长度为偶数，两个集合包含数的个数要一样多
 如果arr长度为奇数，两个集合包含数的个数必须只差一个
 请尽量让两个集合的累加和接近
 返回：
 最接近的情况下，较小集合的累加和

 */
public class Code24_3给定数组分成两个集合要累加和接近返回最接近的情况下较小的集合的累加和 {



    /*
     * @author gengzhihao
     * @date 2023/9/21 9:53
     * @description 暴力递归
     * @param arr
     * @return int
     **/
    public static int right0(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++){
            sum += arr[i];
        }
        sum /= 2;

        //偶数个
        if (arr.length % 2 == 0){
            return process0(arr,0,sum,arr.length/2);
        }
        //奇数个
        return Math.max(process0(arr,0,sum,(arr.length+1)/2),process0(arr,0,sum,arr.length/2));
    }

    //对于数组arr当前索引Index，向右走到头，能凑到的最接近rest 同时正好用了num个数的总和是多少
    private static int process0(int[] arr, int index, int rest, int num) {
        //如果index走到头了，
        // 同时num为0表示已经选完了该选的数了，那么这是一条有效选择返回0。否则为无效选择返回-1
        if (index == arr.length){
            return num == 0 ? 0 : -1;
        }

        int p1 = process0(arr,index+1,rest,num);
        int p2 = -1;
        if (rest - arr[index] >= 0){
            int last = process0(arr,index+1,rest-arr[index],num-1);
            if (last != -1){
                p2 = arr[index] + last;
            }

        }
        return Math.max(p1,p2);
    }


    //*******************************************************************************


    /*
     * @author gengzhihao
     * @date 2023/9/22 11:32
     * @description 题目1
     * @param arr
     * @return int
     **/
    public static int right1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        sum /= 2;

        int N = arr.length;
        int[][][] dp = new int[N+1][sum+1][(N+1)/2 + 1];

        for (int rest = 0; rest <= sum; rest++){
            for (int num = 1; num <= (N+1)/2; num++){
                dp[N][rest][num] = -1;
            }
        }

        for (int index = N-1; index >= 0; index--){
            for (int rest = 0; rest <= sum; rest++){
                for (int num = 0; num <= (N+1)/2; num++){
                    int p1 = process0(arr,index+1,rest,num);
                    int p2 = -1;
                    if (rest - arr[index] >= 0 && num - 1 >= 0){
                        int last = dp[index+1][rest-arr[index]][num-1];
                        if (last != -1){
                            p2 = arr[index] + last;
                        }
                    }
                    dp[index][rest][num] = Math.max(p1,p2);
                }
            }
        }

        if (arr.length % 2 == 0){
            return dp[0][sum][arr.length/2];
        }
        return Math.max(dp[0][sum][(arr.length+1)/2],dp[0][sum][arr.length/2]);
    }


    //**************************************************************************************


    // for test
    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 50;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
            int ans = right(arr);
            int ans0 = right1(arr);
            if (ans != ans0) {
                printArray(arr);
                System.out.println(ans);
                System.out.println(ans0);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }











    public static int right(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        if ((arr.length & 1) == 0) {
            return process(arr, 0, arr.length / 2, sum / 2);
        } else {
            return Math.max(process(arr, 0, arr.length / 2, sum / 2), process(arr, 0, arr.length / 2 + 1, sum / 2));
        }
    }

    // arr[i....]自由选择，挑选的个数一定要是picks个，累加和<=rest, 离rest最近的返回
    public static int process(int[] arr, int i, int picks, int rest) {
        if (i == arr.length) {
            return picks == 0 ? 0 : -1;
        } else {
            int p1 = process(arr, i + 1, picks, rest);
            // 就是要使用arr[i]这个数
            int p2 = -1;
            int next = -1;
            if (arr[i] <= rest) {
                next = process(arr, i + 1, picks - 1, rest - arr[i]);
            }
            if (next != -1) {
                p2 = arr[i] + next;
            }
            return Math.max(p1, p2);
        }
    }
    // for test
    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }


}
