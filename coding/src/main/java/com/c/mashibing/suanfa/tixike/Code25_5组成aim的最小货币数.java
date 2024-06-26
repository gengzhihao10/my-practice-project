package com.c.mashibing.suanfa.tixike;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/*
 题目1，arr是货币数组，其中的值都是正数。再给定一个正数aim。
 每个值都认为是一张货币，
 返回组成aim的最少货币数
 注意：
 因为是求最少货币数，所以每一张货币认为是相同或者不同就不重要了

 */
public class Code25_5组成aim的最小货币数 {

    /*
     * @author gengzhihao
     * @date 2023/11/6 9:41
     * @description 题目1
     * @param arr
     * @param aim
     * @return int
     **/
    public static int dp3(int[] arr, int aim){
        if (aim == 0) {
            return 0;
        }
        // 得到info时间复杂度O(arr长度)
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] zhangs = info.zhangs;
        int N = coins.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 0;
        for (int j = 1; j <= aim; j++) {
            dp[N][j] = Integer.MAX_VALUE;
        }

        for(int i = N -1; i >= 0; i--){
            for(int mod = 0; mod < Math.min(coins[i],aim+1);mod++){
                //小 -> 大
                LinkedList<Integer> minWindow = new LinkedList<>();
                minWindow.offer(mod);
                for (int j = mod; j <= aim; j+=coins[i]){
                    while (!minWindow.isEmpty() &&
                            ((dp[i+1][minWindow.peekLast()] == Integer.MAX_VALUE )||
                                    (dp[i+1][minWindow.peekLast()] +  compensate(j,minWindow.peekLast(),coins[i]) >= dp[i+1][j]))){
                        minWindow.pollLast();
                    }
                    minWindow.offerLast(j);
                    int overdue = j - coins[i] * (zhangs[i] + 1);
                    if (minWindow.peekFirst() == overdue){
                        minWindow.pollFirst();
                    }
                    if (minWindow.peekFirst() == Integer.MAX_VALUE){
                        dp[i][j] = Integer.MAX_VALUE;
                    }else {
                        dp[i][j] = dp[i+1][minWindow.peekFirst()] + compensate(j,minWindow.peekFirst(),coins[i]);
                    }
                }
            }
        }
        return dp[0][aim];
    }

    private static int compensate(int aim, int i, int coin) {
        return (aim - i)/coin;
    }


    // 为了测试
    public static int[] randomArray(int N, int maxValue) {
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    // 为了测试
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 为了测试
    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 300;
        int testTime = 300000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxLen);
            int[] arr = randomArray(N, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans0 = minCoins(arr, aim);
            int ans1 = dp1(arr, aim);
            int ans2 = dp2(arr, aim);
            int ans3 = dp3(arr, aim);
            if (ans0 != ans3) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans0);
                System.out.println(ans3);
                break;
            }
        }
        System.out.println("功能测试结束");

//        System.out.println("==========");
//
//        int aim = 0;
//        int[] arr = null;
//        long start;
//        long end;
//        int ans2;
//        int ans3;
//
//        System.out.println("性能测试开始");
//        maxLen = 30000;
//        maxValue = 20;
//        aim = 60000;
//        arr = randomArray(maxLen, maxValue);
//
//        start = System.currentTimeMillis();
//        ans2 = dp2(arr, aim);
//        end = System.currentTimeMillis();
//        System.out.println("dp2答案 : " + ans2 + ", dp2运行时间 : " + (end - start) + " ms");
//
//        start = System.currentTimeMillis();
//        ans3 = dp3(arr, aim);
//        end = System.currentTimeMillis();
//        System.out.println("dp3答案 : " + ans3 + ", dp3运行时间 : " + (end - start) + " ms");
//        System.out.println("性能测试结束");
//
//        System.out.println("===========");
//
//        System.out.println("货币大量重复出现情况下，");
//        System.out.println("大数据量测试dp3开始");
//        maxLen = 20000000;
//        aim = 10000;
//        maxValue = 10000;
//        arr = randomArray(maxLen, maxValue);
//        start = System.currentTimeMillis();
//        ans3 = dp3(arr, aim);
//        end = System.currentTimeMillis();
//        System.out.println("dp3运行时间 : " + (end - start) + " ms");
//        System.out.println("大数据量测试dp3结束");
//
//        System.out.println("===========");
//
//        System.out.println("当货币很少出现重复，dp2比dp3有常数时间优势");
//        System.out.println("当货币大量出现重复，dp3时间复杂度明显优于dp2");
//        System.out.println("dp3的优化用到了窗口内最小值的更新结构");
    }

    public static int minCoins(int[] arr, int aim) {
        return process(arr, 0, aim);
    }

    public static int process(int[] arr, int index, int rest) {
        if (rest < 0) {
            return Integer.MAX_VALUE;
        }
        if (index == arr.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        } else {
            int p1 = process(arr, index + 1, rest);
            int p2 = process(arr, index + 1, rest - arr[index]);
            if (p2 != Integer.MAX_VALUE) {
                p2++;
            }
            return Math.min(p1, p2);
        }
    }

    // dp1时间复杂度为：O(arr长度 * aim)
    public static int dp1(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 0;
        for (int j = 1; j <= aim; j++) {
            dp[N][j] = Integer.MAX_VALUE;
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int p1 = dp[index + 1][rest];
                int p2 = rest - arr[index] >= 0 ? dp[index + 1][rest - arr[index]] : Integer.MAX_VALUE;
                if (p2 != Integer.MAX_VALUE) {
                    p2++;
                }
                dp[index][rest] = Math.min(p1, p2);
            }
        }
        return dp[0][aim];
    }

    public static class Info {
        public int[] coins;
        public int[] zhangs;

        public Info(int[] c, int[] z) {
            coins = c;
            zhangs = z;
        }
    }

    public static Info getInfo(int[] arr) {
        HashMap<Integer, Integer> counts = new HashMap<>();
        for (int value : arr) {
            if (!counts.containsKey(value)) {
                counts.put(value, 1);
            } else {
                counts.put(value, counts.get(value) + 1);
            }
        }
        int N = counts.size();
        int[] coins = new int[N];
        int[] zhangs = new int[N];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            coins[index] = entry.getKey();
            zhangs[index++] = entry.getValue();
        }
        return new Info(coins, zhangs);
    }

    // dp2时间复杂度为：O(arr长度) + O(货币种数 * aim * 每种货币的平均张数)
    public static int dp2(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }
        // 得到info时间复杂度O(arr长度)
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] zhangs = info.zhangs;
        int N = coins.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 0;
        for (int j = 1; j <= aim; j++) {
            dp[N][j] = Integer.MAX_VALUE;
        }
        // 这三层for循环，时间复杂度为O(货币种数 * aim * 每种货币的平均张数)
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                for (int zhang = 1; zhang * coins[index] <= aim && zhang <= zhangs[index]; zhang++) {
                    if (rest - zhang * coins[index] >= 0
                            && dp[index + 1][rest - zhang * coins[index]] != Integer.MAX_VALUE) {
                        dp[index][rest] = Math.min(dp[index][rest], zhang + dp[index + 1][rest - zhang * coins[index]]);
                    }
                }
            }
        }
        return dp[0][aim];
    }
}
