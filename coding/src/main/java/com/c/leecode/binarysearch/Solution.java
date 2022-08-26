package com.c.leecode.binarysearch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Solution {

    /**
     * @author gengzhihao
     * @date 2022/8/25 20:10
     * @description 二分查找
     * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
     **/
    public static int search(int[] nums, int target) {
        int left = 0;
        int middle = 0;
        int right = nums.length - 1;

        if ( nums == null || nums.length ==0  || left > right || target < nums[left] || target > nums[right]) {
            return -1;
        }

        if (nums.length == 1 && nums[0] == target){
            return 0;
        }

        while (left < right) {
            middle = (left + right) / 2;
            if (nums[middle] == target) {
                return middle;
            }
            if (nums[middle] < target) {
                left = middle;
            }
            if (nums[middle] > target) {
                right = middle;
            }
            if ((left == right || right - left == 1) && nums[left] != target && nums[right] != target){
                return -1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {5};
        int target = 5;
//        int target = 2;
        log.info("二分查找返回结果为 {}", search(nums,target));;
    }


}
