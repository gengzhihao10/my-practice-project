package com.c.mashibing.suanfa.tixike;

/*
 题目1，
 arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 每个值都认为是一种面值，且认为张数是无限的。
 返回组成aim的最少货币数
 动态规划
 题目2，同题目1，进行斜率优化
 */
public class Code23_6返回组成aim的最少货币数 {

    /*
     * @author gengzhihao
     * @date 2023/9/16 15:57
     * @description 暴力递归
     * @param arr
     * @param aim
     * @return int
     **/
    public static int minCoins0(int[] arr, int aim){
        if (arr == null || arr.length == 0){
            return aim == 0 ? 0 : Integer.MAX_VALUE;
        }

        return process0(arr,0,aim);
    }

    //数组从index到最后(index为arr.length算走到了最后)，正好能组成rest的最少货币数，将其返回
    //返回Interger.Maxvalue表示是无效解
    private static int process0(int[] arr, int index, int rest) {
        if (index == arr.length){
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }

        int ans = Integer.MAX_VALUE;
        //根据aim为正数这个条件，要求传入的rest>0。但是如果这样，base case就永远无法返回有效解，
        // 所以需要rest>=0，在basecase那里rest==0时返回0
        for (int i = 0; rest - i * arr[index] >= 0; i++){
            int res = process0(arr,index+1,rest - i * arr[index]);
            if (res != Integer.MAX_VALUE){
                //i为index位置所使用的货币数，res为index+1到最后所使用的货币数。综合起来才是从index到最后的货币数
                ans = Math.min(ans,i + res);
            }
        }

        return ans;
    }

    //****************************************************************************************************

    /*
     * @author gengzhihao
     * @date 2023/9/18 10:35
     * @description 题目1
     * @param arr
     * @param aim
     * @return int
     **/
    public static int minCoins1(int[] arr, int aim){
        if (arr == null || arr.length == 0){
            return aim == 0 ? 0 : Integer.MAX_VALUE;
        }

        //申请空白dp表
        int N = arr.length;
        int[][] dp = new int[N+1][aim+1];

        //dp表初始化
        for (int rest = 1; rest <= aim; rest++){
            dp[N][rest] = Integer.MAX_VALUE;
        }

        //计算
        for (int index = N-1; index >= 0; index--){
            for (int rest = 0; rest <= aim; rest++){
                //枚举计算
                dp[index][rest] = Integer.MAX_VALUE;
                for (int i = 0; rest - i * arr[index] >= 0; i++){
                    int res = dp[index+1][rest - i * arr[index]];
                    if (res != Integer.MAX_VALUE){
                        //i为index位置所使用的货币数，res为index+1到最后所使用的货币数。综合起来才是从index到最后的货币数
                        dp[index][rest] = Math.min(dp[index][rest],i + res);
                    }
                }
            }
        }

        //返回
        return dp[0][aim];
    }

    //********************************************************************************************


    /*
     * @author gengzhihao
     * @date 2023/9/18 11:12
     * @description 题目2
     * @param arr
     * @param aim
     * @return int
     **/
    public static int minCoins2(int[] arr, int aim){
        if (arr == null || arr.length == 0){
            return aim == 0 ? 0 : Integer.MAX_VALUE;
        }

        //申请空白dp表
        int N = arr.length;
        int[][] dp = new int[N+1][aim+1];

        //dp表初始化
        for (int rest = 1; rest <= aim; rest++){
            dp[N][rest] = Integer.MAX_VALUE;
        }

        //计算
        for (int index = N-1; index >= 0; index--){
            for (int rest = 0; rest <= aim; rest++){
                //枚举计算、
                dp[index][rest] = Integer.MAX_VALUE;

                //如果(i,r)左侧点，没有越界 且 是有效的，才能参与竞选。之所以要求是有效解，是因为Integer.MaxValue+1会为负数了
                /*
                以后要记住，除了要关注索引Index-某数可能会越界的问题，
                还要关注最大值+某数，最小值-某数
                可能会导致的无效解判定失效的问题
                如Integer.Maxvalue表示无效解，一个非最大值的正数为有效解，通过Math.min可以自然的排除掉maxvalue这个无效解
                一旦maxvalue+某数，会变为负数，此时通过设置最大值筛选无效解的动作就失效了
                 */
                int min = rest - arr[index] >= 0 && dp[index][rest - arr[index]] != Integer.MAX_VALUE
                        ? Math.min((dp[index][rest - arr[index]] + 1), dp[index+1][rest])
                        : dp[index+1][rest];
                //min如果是有效解，可以直接作为结果
                if (min != Integer.MAX_VALUE){
                    dp[index][rest] = min;
                }

//                dp[index][rest] = Integer.MAX_VALUE;
//                if (rest - arr[index] < 0){
//                    dp[index][rest] = dp[index+1][rest];
//                    continue;
//                }
//                dp[index][rest] = Math.min(dp[index][rest],
//                        Math.min(dp[index][rest - arr[index]],dp[index+1][rest]));
            }
        }

        //返回
        return dp[0][aim];
    }


    //***********************************************************************************************************


    // 为了测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        boolean[] has = new boolean[maxValue + 1];
        for (int i = 0; i < N; i++) {
            do {
                arr[i] = (int) (Math.random() * maxValue) + 1;
            } while (has[arr[i]]);
            has[arr[i]] = true;
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
        int maxValue = 30;
        int testTime = 300000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxLen);
            int[] arr = randomArray(N, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = ans(arr, aim);
            int ans2 = minCoins2(arr, aim);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("功能测试结束");
    }

    public static int ans(int[] arr, int aim) {
        return process(arr, 0, aim);
    }

    // arr[index...]面值，每种面值张数自由选择，
    // 搞出rest正好这么多钱，返回最小张数
    // 拿Integer.MAX_VALUE标记怎么都搞定不了
    public static int process(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        } else {
            int ans = Integer.MAX_VALUE;
            for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
                int next = process(arr, index + 1, rest - zhang * arr[index]);
                if (next != Integer.MAX_VALUE) {
                    ans = Math.min(ans, zhang + next);
                }
            }
            return ans;
        }
    }

    //************************************************************************************
}
