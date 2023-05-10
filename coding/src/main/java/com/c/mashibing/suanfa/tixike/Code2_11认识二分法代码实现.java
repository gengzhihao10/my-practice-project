package com.c.mashibing.suanfa.tixike;

public class Code2_11认识二分法代码实现 {


    /*
     * @author gengzhihao
     * @date 2023/5/10 9:44
     * @description 在一个有序数组中，找某个数是否存在
     * @param arr
     **/
    public static boolean binarySearch(int[] arr, int target){

        if (arr == null || arr.length == 0){
            return false;
        }

        int L = 0;
        int R = arr.length - 1;
        int mid = 0;

        //数组至少存在两个数的情况下
        while (L <= R){
            mid = L + (R - L) >> 1;
            if (arr[mid] < target){
                L = mid + 1;
            }
            else if (arr[mid] > target){
                R = mid - 1;
            }
            else {
                return true;
            }
        }
        return false;
    }

    /*
     * @author gengzhihao
     * @date 2023/5/10 10:16
     * @description 再一个有序数组中，找到>=某个数最左侧的位置。默认是升序数组
     * @param arr
     * @param target
     * @return int
     **/
    public static int findMoreThanNumLeftIndex(int[] arr, int target){
        //数组为空的情况
        if (arr == null || arr.length == 0){
            return -1;
        }

        int L = 0;
        int R = arr.length - 1;
        int mid = 0;
        int ans = -1;

        while (L <= R){
            mid = L + (R - L) >> 1;

            if (arr[mid] >= target){
                R = mid - 1;
                ans = mid;
            }
            else if (arr[mid] < target){
                L = mid + 1;
            }
        }
        return ans;
    }

    /*
     * @author gengzhihao
     * @date 2023/5/10 10:48
     * @description  在一个有序数组中，找到<=某个数最右侧的位置。默认是升序数组
     * @param arr
     * @param target
     * @return int
     **/
    public static int findLessThanNumRightIndex(int[] arr, int target){
        //数组为空的情况
        if (arr == null || arr.length == 0){
            return -1;
        }

        int L = 0;
        int R = arr.length - 1;
        int mid = 0;
        int ans = -1;

        while (L <= R){
            mid = L + (R - L) >> 1;

            if (arr[mid] <= target){
                L = mid + 1;
                ans = mid;
            }
            else if (arr[mid] > target){
                R = mid - 1;
            }
        }
        return ans;
    }

    /*
     * @author gengzhihao
     * @date 2023/5/10 10:49
     * @description 找到局部最小值
     * @param arr 一个无序数组，特点是相邻两个数不相等
     * @return int
     **/
    public static int findPartMinum(int[] arr){

        if (arr == null || arr.length == 0){
            return -1;
        }
        if (arr.length == 1){
            return 0;
        }
        if (arr[0] < arr[1]){
            return 0;
        }
        if (arr[arr.length -2] > arr[arr.length - 1]){
            return arr.length -1;
        }

        int L = 1;
        int R = arr.length - 2;
        int mid = 0;

        while (L < R){
            mid = L + (R - L)>>1;

            if (arr[mid-1] < arr[mid]){
                R = mid-1;
            }
            else if (arr[mid] > arr[mid+1]){
                L = mid+1;
            }
            else {
                return mid;
            }
        }

        return L;
    }

}
