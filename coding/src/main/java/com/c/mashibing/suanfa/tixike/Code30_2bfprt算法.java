package com.c.mashibing.suanfa.tixike;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
 题目1，在无序数组中求第k小的数。
 使用bfprt算法，时间复杂度o(N)
 */
public class Code30_2bfprt算法 {


    /*
     * @author gengzhihao
     * @date 2023/12/1 11:03
     * @description 题目1
     * @param array
     * @param k
     * @return int
     **/
    public static int minKth1(int[] array, int k){
        if (array == null || array.length == 0 || k < 0 || k > array.length){
            return -1;
        }

        return process(array,0,array.length-1,k-1);
    }

    //返回array中排序后索引为i的数，要求在索引L到R范围中，
    private static int process(int[] array, int L, int R, int i) {
        if (L == R){
            return array[L];
        }

        //bfprt
        int num = bfprt(array,L,R);
        int[] partition = partition(array,L,R,num);
        if (i < partition[0]){
            return process(array,L,partition[0] - 1,i);
        }
        else if (i > partition[1]){
            return process(array,partition[1] + 1,R,i);
        }
        else {
            return array[i];
        }
    }

    //bfprt算法，选出一个相对中位数
    private static int bfprt(int[] array, int L, int R) {
        if(L == R){
            return array[L];
        }
        int nums = R -L + 1;
        int groups = nums / 5 + ((nums % 5 == 0) ? 0 : 1);
        int[] mArray = new int[groups];
        int index = 0;
        for (int i = 0; i < groups; i++){
            int start = L + 5 * i;
            int middle = getMiddle(array,start, Math.min(start + 4, R));
            mArray[index++] = middle;
        }
        return bfprt(mArray,0,groups - 1);
    }

    //将数组array排序后返回中位数,start end对应的是索引
    private static int getMiddle(int[] array, int start, int end) {
        sort(array,start,end);
        return array[(end + start) / 2];
    }

    //排成从大到小
    private static void sort(int[] array, int start, int end) {
        for (int i = end; i >= start; i--){
            for (int j = start; j < i; j++){
                if (array[j] > array[j+1]){
                    swap(array,j,j+1);
                }
            }
        }
    }

    //array中，将索引L到R范围上分为，小于num的区域、等于num的区域、大于num的区域
    // 返回等于区域的左右边界
    private static int[] partition(int[] array, int L, int R, int num) {
        int less = L - 1, more = R + 1;
        int cur = L;
        while (cur < more){
            if (array[cur] < num){
                swap(array,++less,cur++);
            }
            else if (array[cur] > num){
                swap(array,--more,cur);
            }
            else {
                cur++;
            }
        }
        return new int[]{less + 1,more - 1};
    }

    private static void swap(int[] array, int x, int y) {
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }




    //*********************************************************************************************************

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * maxSize) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 1000000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int k = (int) (Math.random() * arr.length) + 1;
            int ans1 = minKth1(arr, k);
            int ans0 = minKth0(arr, k);
            if (ans1 != ans0) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("test finish");
    }

    public static class MaxHeapComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }

    }

    // 利用大根堆，时间复杂度O(N*logK)
    public static int minKth0(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Code30_1在无序数组中求第k小的数.MaxHeapComparator());
        for (int i = 0; i < k; i++) {
            maxHeap.add(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (arr[i] < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.add(arr[i]);
            }
        }
        return maxHeap.peek();
    }
}
