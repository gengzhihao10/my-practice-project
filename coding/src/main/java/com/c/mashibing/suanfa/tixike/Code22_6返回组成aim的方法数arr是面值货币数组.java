package com.c.mashibing.suanfa.tixike;

import java.util.HashMap;
import java.util.Map;

/*
 题目1，
 arr是货币数组，其中的值都是正数。再给定一个正数aim。
 每个值都认为是一张货币，
 认为值相同的货币没有任何不同，
 返回组成aim的方法数
 例如：arr = {1,2,1,1,2,1,2}，aim = 4
 方法：1+1+1+1、1+1+2、2+2
 一共就3种方法，所以返回3
 动态规划
 题目2，同题目1，基于动态转移的优化
 */
public class Code22_6返回组成aim的方法数arr是面值货币数组 {

    /*
     * @author gengzhihao
     * @date 2023/9/13 14:44
     * @description 暴力递归
     * @param arr
     * @param aim
     * @return int
     **/
    public static int coinsWay0(int[] arr, int aim){
        if (arr == null || arr.length == 0){
            return  0;
        }

        Info info = getInfo(arr);
        return process0(info.coins,info.zhangs,0,aim);
    }

    /*
     * @author gengzhihao
     * @date 2023/9/14 9:12
     * @description 从index位置出发向后推进，到结束时，有多少可能性，正好凑齐rest
     * @param valueArr 数值代表面值，该数组表示面值有几种
     * @param sizeArr 数值表示在valueArr中对应索引位置的面值有几张
     * @param index 表示valueArr和sizeArr都从index位置向后推进
     * @param rest 需要凑齐的目标
     * @return int 有几种可能性
     **/
    private static int process0(int[] valueArr, int[] sizeArr, int index, int rest) {
        if (index == valueArr.length){
            return rest == 0 ? 1 : 0;
        }

        int sum = 0;
        for (int zhang = 0; (zhang <= sizeArr[index]) && (rest-zhang * valueArr[index] >= 0); zhang++){
            sum += process0(valueArr,sizeArr,index+1,rest-zhang * valueArr[index]);
        }
        return sum;
    }

    //统计arr的面值和对应的张数
    private static Info getInfo(int[] arr) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i : arr){
            if (!map.containsKey(i)){
                map.put(i,1);
            }else {
                map.put(i,map.get(i)+1);
            }
        }
        int[] value = new int[map.size()];
        int[] size = new int[map.size()];
        int i = 0;
        for (Map.Entry<Integer,Integer> entry: map.entrySet()){
            value[i] = entry.getKey();
            size[i++] = entry.getValue();
        }
        return new Info(value,size);
    }

    //对面值的统计信息
    private static class Info{
        int[] coins;//面值大小
        int[] zhangs;//有几张该面值

        public Info() {
        }

        public Info(int[] coins, int[] zhangs) {
            this.coins = coins;
            this.zhangs = zhangs;
        }
    }

    //*************************************************************************************************



    /*
     * @author gengzhihao
     * @date 2023/9/14 9:57
     * @description 题目1
     * @param arr
     * @param aim
     * @return int
     **/
    public static int coinsWay1(int[] arr, int aim){
        if (arr == null || arr.length == 0){
            return  0;
        }

        Info info = getInfo(arr);
        int[] valueArr = info.coins;
        int[] sizeArr = info.zhangs;

        //申请空白dp表
        int N = valueArr.length;
        int[][] dp = new int[N+1][aim+1];
        //index==N这一行初始化
        dp[N][0] = 1;

        for (int index = N-1; index >= 0; index--){
            for (int rest = aim; rest >= 0; rest--){
                int sum = 0;
                for (int zhang = 0; (zhang <= sizeArr[index]) && (rest-zhang * valueArr[index] >= 0); zhang++){
                    sum += dp[index+1][rest-zhang * valueArr[index]];
                }
                dp[index][rest] = sum;
            }
        }
        return dp[0][aim];
    }


    //**********************************************************************************************


    /*
     * @author gengzhihao
     * @date 2023/9/14 10:08
     * @description 题目2
     * @param arr
     * @param aim
     * @return int
     **/
    public static int coinsWay2(int[] arr, int aim){
        if (arr == null || arr.length == 0){
            return  0;
        }

        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] zhangs = info.zhangs;

        //申请空白dp表
        int N = coins.length;
        int[][] dp = new int[N+1][aim+1];
        //index==N这一行初始化
        dp[N][0] = 1;

        for (int index = N-1; index >= 0; index--){
            for (int rest = 0; rest <= aim; rest++){
                dp[index][rest] = dp[index + 1][rest];
                //最大张数+1才是(index,rest)点下方左侧最远点的纵坐标
                if ((rest - (zhangs[index] + 1) * coins[index]) >= 0){
                    dp[index][rest] -= dp[index+1][rest - (zhangs[index] + 1) * coins[index]];
                }
                if ((rest - coins[index] >= 0)){
                    dp[index][rest] += dp[index][rest - coins[index]];
                }
            }
        }
//        print(dp);
        return dp[0][aim];
    }

    //************************************************************************************************

    // 为了测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
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
        int maxLen = 10;
        int maxValue = 20;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = ans(arr, aim);
            int ans2 = coinsWay2(arr, aim);
//            int ans3 = dp2(arr, aim);
            if (ans1 != ans2 ) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
//                System.out.println(ans3);
                break;
            }
        }
        System.out.println("测试结束");
    }

    public static int ans(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] zhangs = info.zhangs;
        int N = coins.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways = 0;
                for (int zhang = 0; zhang * coins[index] <= rest && zhang <= zhangs[index]; zhang++) {
                    ways += dp[index + 1][rest - (zhang * coins[index])];
                }
                dp[index][rest] = ways;
            }
        }
//        print(dp);
        return dp[0][aim];
    }

    private static void print(int[][] arr){
        System.out.println("打印二维数组");
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr[i].length; j++){
                System.out.print("  " + arr[i][j]);
            }
            System.out.println();
        }
        System.out.println("打印结束");
    }
}
