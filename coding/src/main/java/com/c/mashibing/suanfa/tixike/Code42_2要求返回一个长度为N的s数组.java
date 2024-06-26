package com.c.mashibing.suanfa.tixike;

/*
 题目1，
 把题目一中提到的，
 min{左部分累加和，右部分累加和}，定义为S(N-1)，也就是说：
 S(N-1)：在arr[0…N-1]范围上，做最优划分所得到的min{左部分累加和，右部分累加和}的最大值
 现在要求返回一个长度为N的s数组，
 s[i] =在arr[0…i]范围上，做最优划分所得到的min{左部分累加和，右部分累加和}的最大值
 得到整个s数组的过程，做到时间复杂度O(N)
 */
public class Code42_2要求返回一个长度为N的s数组 {


    /*
     * @author gengzhihao
     * @date 2024/2/20 10:43
     * @description 题目1
     * @param arr
     * @return int[]
     **/
    public static int[] bestSplit3(int[] arr){
        if (arr == null || arr.length == 0){
            return new int[0];
        }

        //计算前缀和数组，pre[i]表示arr[0]到arr[i-1]的累加和
        // 通过前缀和的相减计算得到每一个以index=0开始的子数组的累加和
        int[] preSum = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++){
            preSum[i + 1] = preSum[i] + arr[i];
        }

        //结果数组
        int[] ans = new int[arr.length];
//        ans[0] = Math.min(getSum(preSum,1,preSum.length-1), arr[0]);
        int line = 0;
        int cur = 0, next = 0;
        //end为子数组的右边界
        for (int end = 1; end < arr.length; end++){
            while (line < arr.length - 1){
                cur = Math.min(getSum(preSum,0,line),getSum(preSum,line + 1,end));
                next = Math.min(getSum(preSum,0,line + 1), getSum(preSum,line + 2,end));
                if (cur <= next){
                    line++;
                }else {
                    break;
                }
            }
            ans[end] = cur;
        }
        return ans;
    }

    private static int getSum(int[] preSum, int L, int R){
        return preSum[R + 1] - preSum[L];
    }

//***********************************************************************************************************

    public static int[] bestSplit1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }
        int N = arr.length;
        int[] ans = new int[N];
        ans[0] = 0;
        for (int range = 1; range < N; range++) {
            for (int s = 0; s < range; s++) {
                int sumL = 0;
                for (int L = 0; L <= s; L++) {
                    sumL += arr[L];
                }
                int sumR = 0;
                for (int R = s + 1; R <= range; R++) {
                    sumR += arr[R];
                }
                ans[range] = Math.max(ans[range], Math.min(sumL, sumR));
            }
        }
        return ans;
    }

    // 求原来的数组arr中，arr[L...R]的累加和
    public static int sum(int[] sum, int L, int R) {
        return sum[R + 1] - sum[L];
    }

    public static int[] bestSplit2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }
        int N = arr.length;
        int[] ans = new int[N];
        ans[0] = 0;
        int[] sum = new int[N + 1];
        for (int i = 0; i < N; i++) {
            sum[i + 1] = sum[i] + arr[i];
        }
        for (int range = 1; range < N; range++) {
            for (int s = 0; s < range; s++) {
                int sumL = sum(sum, 0, s);
                int sumR = sum(sum, s + 1, range);
                ans[range] = Math.max(ans[range], Math.min(sumL, sumR));
            }
        }
        return ans;
    }

    public static int[] randomArray(int len, int max) {
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = (int) (Math.random() * max);
        }
        return ans;
    }

    public static boolean isSameArray(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }
        int N = arr1.length;
        for (int i = 0; i < N; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int N = 20;
        int max = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N);
            int[] arr = randomArray(len, max);
            int[] ans1 = bestSplit1(arr);
            int[] ans2 = bestSplit2(arr);
            int[] ans3 = bestSplit3(arr);
            if (!isSameArray(ans1, ans2) || !isSameArray(ans1, ans3)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }
}
