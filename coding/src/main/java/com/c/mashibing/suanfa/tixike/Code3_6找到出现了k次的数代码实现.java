package com.c.mashibing.suanfa.tixike;


import com.common.utils.LogarithmicUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author gengzhihao
 * @date 2023/5/12 12:24
 * @description
 * 题目1，一个数组中有一种数出现了K次，其他数都出现了M次，M>1, K<M，
 * 找到出现了K次的数，要求，额外空间复杂度O(1)，时间复杂度O(N)
 * todo
 *  1，写出题目1的对数器和经典写法
 *  2，题目2，一个数组中，有一个数不知道出现了几次，其他数出现了M次，M>1，
 *  想要知道不知道次数的数出现次数是否为K次。要求，额外空间复杂度O(1)，时间复杂度O(N)
 *  3，写出题目2的对数器
**/

public class Code3_6找到出现了k次的数代码实现 {


    /*
     * @author gengzhihao
     * @date 2023/5/16 9:00
     * @description 题目1的第一种做法，使用位运算
     * @param arr
     * @param K
     * @param M
     **/
    public static int qs1_process1(int[] arr, int K,int M){
        //准备工作，准备好数组
        int[] resultArr  = new int[32];
        //1，将所有数的第j位累加到数组resultArr[j]中
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < 32; j++){
                resultArr[j] += (arr[i]>>j) & 1;
            }
        }

        //2，假设出现M次的数在第j位上总共出现过b次1，数组中第j位上的数为nj
        // 只可能有两种情况。【1】，当出现K次的数在第j位上的数为0时，nj=b*M。当出现K次的数在第j位上的数为1时，nj=K+b*M。
        //又因为K<M，也就是说，当nj%M不为0时，出现K次的数在第j位上的数必为1
        // 根据数组中记录的状态信息，通过第J位是否为M的整数倍，判断出现K次的数的第j位是否为1
        //为1则对result第j位进行赋值1的操作
        int result = 0;
        for (int j = 0; j < 32; j++){
            if (resultArr[j] % M != 0){
                result |= 1<<j;
            }
        }

//        System.out.println(result);
        return result;

    }

    /*
     * @author gengzhihao
     * @date 2023/5/16 8:39
     * @description 题目1的经典写法，用哈希表
     * @param arr
     * @param K
     * @param M
     **/
    public static int qs1_process2(int[] arr, int K, int M){

        Map<Integer,Integer> map = new HashMap<>();
        //1，建立词频表
        for (int i = 0; i<arr.length; i++){
            if (map.containsKey(arr[i])){
                map.put(arr[i],map.get(arr[i])+1);
            }else {
                map.put(arr[i],1);
            }
        }
        int ans = arr[0];
        Set<Integer> keySet = map.keySet();
        for (int temp : keySet) {
            ans = map.get(ans) < map.get(temp) ? ans : temp;
        }
//        System.out.println(ans);
        return ans;
    }


//    public static void main(String[] args) {
//        int testTime = 1000000;
//        int maxValue = 100;
//        int maxFrequece = 5;
//        int otherNumMaxType = 3;
//        int otherNumType = (int)(Math.random() * otherNumMaxType);
//        int K,M;
//        do {
//            K = (int)(Math.random() * (maxFrequece-1)) + 1;
//            M = (int)(Math.random() * (maxFrequece-1)) + 1;
//        }while ( !( K < M && M > 1));
//
//        System.out.println("测试开始");
//        for (int i = 0; i < testTime; i++){
//            int[] arr = randomArr(K,M,otherNumType,maxValue);
//            int ans1 = qs1_process1(arr,K,M);
//            int ans2 = qs1_process2(arr,K,M);
//
//            if (ans1 != ans2){
//                System.out.println("错误啦");
//                LogarithmicUtil.printArray(arr);
//                System.out.println(" k: "+K+" ,M: "+M+" ,otherNumType: "+otherNumType);
//                System.out.println("ans1: " + ans1);
//                System.out.println("ans2: " + ans2);
//            }
//
//        }
//        System.out.println("测试结束");
//
//
//    }

    private static int[] randomArr(int k, int m, int otherNumType,int maxValue) {
        //准备空白数组
        int arrLength = k + m * otherNumType;
        int[] arr = new int[arrLength];
        int curIndex = 0;

        //给数组赋值
        int value = randomValue(maxValue);
        for (int i = 0; i < k;i++){
            arr[curIndex++] = value;
        }

        for (int i = 0; i < otherNumType; i++){
            value = randomValue(maxValue);
            for (int j = 0; j< m; j++){
                arr[curIndex++] = value;
            }
        }

        //随机交换位置
        for (int i = 0; i< arrLength; i++){
            int randomIndex = (int)(Math.random() * arrLength);
            int temp = arr[i];
            arr[i] = arr[randomIndex];
            arr[randomIndex] = temp;
        }
//        System.out.println("产生随机数组如下：");
//        LogarithmicUtil.printArray(arr);
        return arr;
    }

    private static int randomValue(int maxValue) {
        return Math.random() > 0.5 ? (int)(Math.random() * maxValue) : -(int)(Math.random() * maxValue);
    }

//*************************************************************************************************

    /*
     * @author gengzhihao
     * @date 2023/5/16 11:11
     * @description 题目2
     * @param arr
     * @param K
     * @param M
     * @return boolean 返回true表示为K次，false表示不为K次
     **/
    public static boolean qs2_process1(int[] arr, int K,int M){
        int[] resultArr  = new int[32];
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < 32; j++){
                resultArr[j] += (arr[i]>>j) & 1;
            }
        }

        boolean result = true;
        for (int j = 0; j < 32; j++){
            //fixme K对应的数为0时，出现问题

            if (resultArr[j] % M != 0 && resultArr[j] % M != K){
                result = false;
                return result;
            }
        }

        if (result){
            int count = 0;
            for (int i = 0; i < arr.length; i++){
                if (arr[i] == 0){
                    count++;
                }
            }
            if (count != K){
                return false;
            }
        }

//        System.out.println(result);
        return result;

    }

    /*
     * @author gengzhihao
     * @date 2023/5/16 11:19
     * @description 题目2
     * @param arr
     * @param K
     * @param M
     * @return boolean 返回true表示为K次，false表示不为K次
     **/
    public static boolean qs2_process2(int[] arr, int K, int M){

        Map<Integer,Integer> map = new HashMap<>();
        //1，建立词频表
        for (int i = 0; i<arr.length; i++){
            if (map.containsKey(arr[i])){
                map.put(arr[i],map.get(arr[i])+1);
            }else {
                map.put(arr[i],1);
            }
        }
        int ans = arr[0];
        Set<Integer> keySet = map.keySet();
        for (int temp : keySet) {
            ans = map.get(ans) < map.get(temp) ? ans : temp;
        }
//        System.out.println(ans);
        return map.get(ans) == K;
    }

    public static void main(String[] args) {
        int testTime = 1000000;
        int maxValue = 100;
        int maxFrequece = 5;
        int otherNumMaxType = 3;
        int otherNumType = (int)(Math.random() * otherNumMaxType);
        int K,M;
        do {
            K = (int)(Math.random() * (maxFrequece-1)) + 1;
            M = (int)(Math.random() * (maxFrequece-1)) + 1;
        }while ( !( K < M && M > 1));
        int times = (int)(Math.random() * (maxFrequece-1)) + 1;

        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++){
            int[] arr = randomArr(times,M,otherNumType,maxValue);
            boolean ans1 = qs2_process1(arr,K,M);
            boolean ans2 = qs2_process2(arr,K,M);

            if (ans1 != ans2){
                System.out.println("错误啦");
                LogarithmicUtil.printArray(arr);
                System.out.println("times: "+times+ ", k: "+K+" ,M: "+M+" ,otherNumType: "+otherNumType);
                System.out.println("ans1: " + ans1);
                System.out.println("ans2: " + ans2);
                System.out.println();
            }

        }
        System.out.println("测试结束");
    }



}
