package com.c.mashibing.suanfa.tixike;

/*
todo
 题目1，
 给定一个数组arr，和两个整数a和b（a<=b）
 求arr中有多少个子数组，累加和在[a,b]这个范围上
 返回达标的子数组数量

 */
public class Code38_2给定一个数组arr和两个整数a和b {

    /*
     * @author gengzhihao
     * @date 2024/1/15 11:46
     * @description 题目1
     * @param nums
     * @param lower
     * @param upper
     * @return int
     **/
    public static int countRangeSum2(int[] nums, int lower, int upper){

    }


    public static class SBTNode{


        public SBTNode(long k){

        }
    }


    public static class SizeBalancedTreeSet{



        private SBTNode rightRotate(SBTNode cur){

        }

        private SBTNode leftRotate(SBTNode cur){

        }

        private SBTNode maintain(SBTNode cur){

        }

        private SBTNode add(SBTNode cur, long key, boolean contains){

        }

        public void add(long sum){

        }

        public long lessKeySize(long key){

        }
    }



    //*******************************************************************************


    public static int countRangeSum1(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] sums = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            sums[i + 1] = sums[i] + nums[i];
        }
        return countWhileMergeSort(sums, 0, n + 1, lower, upper);
    }

    private static int countWhileMergeSort(long[] sums, int start, int end, int lower, int upper) {
        if (end - start <= 1) {
            return 0;
        }
        int mid = (start + end) / 2;
        int count = countWhileMergeSort(sums, start, mid, lower, upper)
                + countWhileMergeSort(sums, mid, end, lower, upper);
        int j = mid, k = mid, t = mid;
        long[] cache = new long[end - start];
        for (int i = start, r = 0; i < mid; ++i, ++r) {
            while (k < end && sums[k] - sums[i] < lower) {
                k++;
            }
            while (j < end && sums[j] - sums[i] <= upper) {
                j++;
            }
            while (t < end && sums[t] < sums[i]) {
                cache[r++] = sums[t++];
            }
            cache[r] = sums[i];
            count += j - k;
        }
        System.arraycopy(cache, 0, sums, start, t - start);
        return count;
    }

    //*********************************************************************************

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static int[] generateArray(int len, int varible) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * varible);
        }
        return arr;
    }

    public static void main(String[] args) {
        int len = 200;
        int varible = 50;
        for (int i = 0; i < 10000; i++) {
            int[] test = generateArray(len, varible);
            int lower = (int) (Math.random() * varible) - (int) (Math.random() * varible);
            int upper = lower + (int) (Math.random() * varible);
            int ans1 = countRangeSum1(test, lower, upper);
            int ans2 = countRangeSum2(test, lower, upper);
            if (ans1 != ans2) {
                printArray(test);
                System.out.println(lower);
                System.out.println(upper);
                System.out.println(ans1);
                System.out.println(ans2);
            }
        }

    }
}
