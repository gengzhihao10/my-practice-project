package com.c.mashibing.suanfa.tixike;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
 题目1，在无序数组中求第k小的数。
 使用改写快排，时间复杂度o(N)
 题目2，同题目1，
 使用bfprt算法，时间复杂度为o(N)
 */
public class Code30_1在无序数组中求第k小的数 {


    /*
     * @author gengzhihao
     * @date 2023/12/1 11:01
     * @description 题目1
     * @param array
     * @param k
     * @return int
     **/
    public static int minKth1(int[] array, int k) {
        if (array == null || array.length == 0 || k <= 0) {
            return -1;
        }

        return process(array, 0, array.length - 1, k - 1);
    }

    //返回array数组中，排序后，L到R范围上，等于下标为i的数
    private static int process(int[] array, int L, int R, int i) {
        if (L == R){
            return array[L];
        }
        int[] res;
        int num = array[L + (int) (Math.random() * (R - L + 1))];
        res = partition(array, L, R, num);
        if (res[0] <= i && res[1] >= i) {
            return array[i];
        } else if (res[0] > i) {
            return process(array, L, res[0] - 1, i);
        } else {
            return process(array, res[1] + 1, R, i);
        }
    }

    //将array从L到R范围上，将array分为小于num区域，等于num区域，大于num区域。返回等于区域的索引范围
    private static int[] partition(int[] array, int L, int R, int num) {
        int[] res = new int[2];
        //小于区域右边界，大于区域左边界
        int less = L - 1, more = R + 1;
        int cur = L;
        while (cur < more) {
            if (array[cur] > num) {
                swap(array, cur, --more);
            }
            else if (array[cur] < num){
                swap(array,cur++,++less);
            }else {
                cur++;
            }
        }
        res[0] = less + 1;
        res[1] = more - 1;
        return res;
    }

    //将索引x和y位置的数交换位置
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
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new MaxHeapComparator());
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
