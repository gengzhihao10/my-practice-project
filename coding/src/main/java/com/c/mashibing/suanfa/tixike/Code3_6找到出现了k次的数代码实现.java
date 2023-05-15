package com.c.mashibing.suanfa.tixike;


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


    public static void qs1_process1(int[] arr, int K,int M){
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

        System.out.println(result);

    }


    public static void main(String[] args) {
        int[] arr = {-1,-1,5,5,5,4,4,4,7,7,7};
        qs1_process1(arr,2,3);

        double test = 8.9;
        System.out.println((int)test);
    }
}
