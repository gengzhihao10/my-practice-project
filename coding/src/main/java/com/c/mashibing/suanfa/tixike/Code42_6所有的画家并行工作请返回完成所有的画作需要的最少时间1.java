package com.c.mashibing.suanfa.tixike;

/*
 给定一个整型数组 arr，数组中的每个值都为正数，表示完成一幅画作需要的时间，再 给定 一个整数 num，表示画匠的数量，每个画匠只能画连在一起的画作。所有的画家 并行工作，请 返回完成所有的画作需要的最少时间。
 【举例】
 arr=[3,1,4]，num=2。
 最好的分配方式为第一个画匠画 3 和 1，所需时间为 4。第二个画匠画 4，所需时间 为 4。 因为并行工作，所以最少时间为 4。如果分配方式为第一个画匠画 3，所需时 间为 3。第二个画 匠画 1 和 4，所需的时间为 5。那么最少时间为 5，显然没有第一 种分配方式好。所以返回 4。
 arr=[1,1,1,4,3]，num=3。
 最好的分配方式为第一个画匠画前三个 1，所需时间为 3。第二个画匠画 4，所需时间 为 4。 第三个画匠画 3，所需时间为 3。返回 4。
 leetcode原题
 测试链接：https://leetcode.com/problems/split-array-largest-sum/
 */
public class Code42_6所有的画家并行工作请返回完成所有的画作需要的最少时间1 {


    /*
     * @author gengzhihao
     * @date 2024/2/29 10:17
     * @description 题目1。动态规划，复杂度O(N^2*K)
     * @param nums 画作数组，每个值代表对应索引的画要画完需要的时间
     * @param K 画家数量
     * @return int 能画完这些画的最短时间
     **/
    public static int splitArray1(int[] nums, int K){
        if (K < 0){
            return -1;
        }
        if (nums == null || nums.length == 0){
            return 0;
        }

        int N = nums.length;
        int[] preSum = new int[N+1];
        for(int i = 0; i < N; i++){
            preSum[i + 1] = preSum[i] + nums[i];
        }

        //假设dp[i][j]=cost。表示有nums[0]~nums[i]个画作，由j个画家完成最短需要cost分钟
        int[][] dp = new int[N][K+1];
        //左边边界条件，只有0号画作，j个画师，画完需要多久
        for (int j = 1; j <= K; j++){
            dp[0][j] = nums[0];
        }
        //上边边界条件，有从0到i号画作，只有1个画师，画完要多久
        for (int i = 1; i < N; i++){
            dp[i][1] = getSum(preSum,0,i);
        }

        for (int i = 1; i < N; i++){
            for (int j = 2; j <= K; j++){
                int minCost = Integer.MAX_VALUE;
                //需要遍历找到最小值
                //每次遍历，是给j-1个画师，分配-1~line个画作(-1表示不分配画作)，画完需要preCost；第j个画师画完剩下的画作，需要curCost；取最大值，得到Cost
                //遍历所有可能性(line)，找到cost的最小值
                for (int line = -1; line <= i; line++){
                    int preCost = line == -1 ? 0 : dp[line][j-1];
                    int curCost = line == i ? 0 : getSum(preSum,line + 1,i);
                    int cost = Math.max(preCost,curCost);
                    minCost = Math.min(minCost,cost);
                }
                dp[i][j] = minCost;
            }
        }
        return dp[N-1][K];
    }

    //获取nums数组从索引L到R范围内的和
    private static int getSum(int[] preSum, int L, int R){
        return preSum[R + 1] - preSum[L];
    }

    /*
     * @author gengzhihao
     * @date 2024/3/1 10:39
     * @description 动态规划，基于四边形不等式优化，复杂度O(N*K)
     * @param nums
     * @param K
     * @return int
     **/
    public static int splitArray2(int[] nums, int K){
        if (K < 0){
            return -1;
        }
        if (nums == null || nums.length == 0){
            return 0;
        }

        int N = nums.length;
        int[] preSum = new int[N+1];
        for(int i = 0; i < N; i++){
            preSum[i + 1] = preSum[i] + nums[i];
        }

        //假设dp[i][j]=cost。表示有nums[0]~nums[i]个画作，由j个画家完成最短需要cost分钟
        int[][] dp = new int[N][K+1];
        int[][] best = new int[N][K + 1];
        //左边边界条件，只有0号画作，j个画师，画完需要多久
        for (int j = 1; j <= K; j++){
            dp[0][j] = nums[0];
            best[0][j] = -1;
        }
        //上边边界条件，有从0到i号画作，只有1个画师，画完要多久
        for (int i = 1; i < N; i++){
            dp[i][1] = getSum(preSum,0,i);
            best[i][1] = -1;
        }

        for (int j = 2; j <= K; j++){
            for (int i = N - 1; i >= 1; i--){
                int left = best[i][j-1];
                int right = i == N -1 ? N - 1 : best[i+1][j];
                int minCost = Integer.MAX_VALUE;
                int bestChoise = -1;
                for (int line = left; line <= right; line++){
                    int preCost = line == -1 ? 0 : dp[line][j-1];
                    int curCost = line == i ? 0 : getSum(preSum,line + 1,i);
                    int cost = Math.max(preCost,curCost);
                    if (cost < minCost){
                        minCost = cost;
                        bestChoise = line;
                    }
                    minCost = Math.min(minCost,cost);
                }
                dp[i][j] = minCost;
                best[i][j] = bestChoise;
            }
        }

        return dp[N-1][K];
    }

    /*
     * @author gengzhihao
     * @date 2024/3/1 11:05
     * @description 最优解，使用油桶问题的解法，
     * 复杂度O(log(sum) * K)，其中sum为nums数组的和（long上限为2^64，因此log(sum)最大为64）
     * @param nums
     * @param M
     * @return int
     **/
    public static int splitArray3(int[] nums, int M){
        if (M < 0){
            return -1;
        }
        if (nums == null || nums.length == 0){
            return 0;
        }
        if (nums.length == 1){
            return nums[0] <= M ? nums[0] : -1;
        }


        Long cost = 0L;
        for (int i = 0; i < nums.length; i ++){
            cost += nums[i];
        }
        //L..R表示cost的范围，通过二分法尝试找到能够符合要求的画家数量
        long ans = 0;
        long L = 0;
        long R = cost;

        while (L <= R){
            cost = (L + R) / 2;
            int cur = get(nums, cost);
            if (cur <= M){
                ans = cost;
                R = cost - 1;
            }else {
                L = cost + 1;
            }
        }
        return (int) ans;
    }

    //对于数组nums，总体消耗时间为cost，返回最少需要几个画家
    private static int get(int[] nums, Long cost) {
        for (int i = 0; i < nums.length; i++){
            if (nums[i] > cost){
                return Integer.MAX_VALUE;
            }
        }
        //总体消耗cost，需要的画家数量
        int count = 1;
        //每个画家实际需要消耗多少时间
        long timePerArtist = nums[0];
        for (int i = 1; i < nums.length; i++){
            //没超过cost，就把本次循环里画作需要消耗这个画家多少时间加上
            if (timePerArtist + nums[i] <= cost){
                timePerArtist += nums[i];
            }
            //超过了cost，就需要重新找一个画家
            else {
                timePerArtist = nums[i];
                count++;
            }
        }
        return count;
    }


    //***************************************************************************************************
    public static int[] randomArray(int len, int maxValue) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * maxValue);
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int N = 100;
        int maxValue = 100;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N) + 1;
            int M = (int) (Math.random() * N) + 1;
            int[] arr = randomArray(len, maxValue);
            int ans1 = splitArray1(arr, M);
            int ans2 = splitArray2(arr, M);
            int ans3 = splitArray3(arr, M);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.print("arr : ");
                printArray(arr);
                System.out.println("M : " + M);
                System.out.println("ans1 : " + ans1);
                System.out.println("ans2 : " + ans2);
                System.out.println("ans3 : " + ans3);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
