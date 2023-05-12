package com.c.mashibing.suanfa.xinshouke;

import com.common.utils.LogarithmicUtil;

public class Code8_1归并排序 {

    //递归方法实现
    public static void mergeSort1(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    //arr[L...R]范围上，让这个范围上的数有序
    private static void process(int[] arr, int L, int R) {
        //L==R放在这里而不是上一级的方法中，是作为递归的中止条件出现的
        if (L == R){
            return;
        }
        //等同于mid = (L+R)/2。
        //使用>>1是为了通过位运算得到更快的速度
        //L + ((R - L) >> 1)而不是(L+R)>>1是因为如果L和R如果很大，想加后有可能越界，而L + ((R - L) >> 1不会大于R，就不会越界
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr,mid+1, R);
        merge(arr, L, mid, R);
    }

    //对二分的两个有序数组进行合并
    private static void merge(int[] arr, int L, int M, int R) {
        //L到R有多少个数就准备多长的help数组
        int[] help = new int[R - L + 1];
        int i = 0;
        //两个数组的指针
        int p1 = L;
        int p2 = M + 1;
        //没有数组越界时，进行合并
        while (p1 <= M && p2 <= R){
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        //要么p1越界，要么p2越界。将其中没越界的数组剩余的部分拷贝至help数组
        while (p1 <= M){
            help[i++] = arr[p1++];
        }
        while (p2 <= R){
            help[i++] = arr[p2++];
        }
        //将help数组的值拷贝至原数组对应L-R位置
        for (i = 0; i < help.length; i++){
            arr[L + i] = help[i];
        }
    }

    //非递归版本(循环)的归并排序的实现
    //时间复杂度o(N**logN)
    public static void mergeSort2(int[] arr) {
        if(arr == null || arr.length < 2){
            return;
        }
        int step = 1;
        int N = arr.length;
        //step = arr.length时没有必要进行排序了
        while (step < N){
            int L = 0;
            while (L < N){
                int M = 0;
                //N-1 - L + 1,等价于N-L，也就是计算数组最后一个数N-1到L有多少个数
                // N-L >= step ，意味着左端L到数组最后一个数N-1的成员的个数，大于步长
                // 也就是说这时通过M=L+step-1去计算M不会发生数组的下标越界
                if (N - L >= step){
                    M = L + step - 1;
                }
                else {
                    M = N -1;
                }
                //包含M=L+step-1和N-1两种情况
                if(M == N-1){
                    break;
                }
                int R = 0;
                //(M-1)-(M+1)+1等价于N-1-M
                //类似于上面的M，同样要判断R是否触碰到边界
                if (N - 1 -M >= step){
                    R = M + step;
                }
                else {
                    R = N - 1;
                }

                merge(arr,L,M,R);

                if (R == N-1){
                    break;
                }
                else {
                    L = R + 1;
                }
            }
            //1 如果step > (N/2)，等价于step * 2 > N，也就是说步长step下一次的翻倍会大于数组长度，不满足循环条件，在这里提前打断循环
            //为什么要通过step > (N/2)的方式而不是step * 2 > N，且要提前打断循环这种别扭的方式呢
            //因为N极大时，如接近int最大值2^31-1时，例如N为2^31-2，step为2^30，step翻倍后为2^31，会溢出，
            // 所以要通过上述别扭的方式判断是否大于数组长度
            //2 为什么不是>=，因为除法是地板除，向下取整，
            // 例如N=9，step=4，此时如果满足了4>=9/2就break，会导致最后一个数没有merge排序就结束循环
            if (step > (N / 2)) {
                break;
            }
            else{
                step *= 2;
            }
        }

    }


    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++){
            int[] arr1 = LogarithmicUtil.generateRandomArray(maxSize, maxValue);
            int[] arr2 = LogarithmicUtil.copyArray(arr1);
            mergeSort1(arr1);
            mergeSort2(arr2);
            if (!LogarithmicUtil.isEqual(arr1,arr2)){
                System.out.println("出错了");
                LogarithmicUtil.printArray(arr1);
                LogarithmicUtil.printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }




}
