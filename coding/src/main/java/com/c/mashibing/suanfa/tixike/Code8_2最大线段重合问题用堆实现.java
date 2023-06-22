package com.c.mashibing.suanfa.tixike;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
todo
 题目1，给定很多线段，每个线段都有两个数组[start, end]
 表示线段开始位置和结束位置，左右都是闭区间
 规定：
 1) 线段的开始和结束位置一定都是整数值
 2) 线段重合区域的长度必须>=1
 返回线段最多重合区域中，包含了几条线段
 */
public class Code8_2最大线段重合问题用堆实现 {

    //线段
    public static class Line{
        int start;
        int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static class StartComparator implements Comparator<Line> {

        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }
    }

    /*
     * @author gengzhihao
     * @date 2023/6/22 9:22
     * @description 题目1
     * @param arr
     * @return int
     **/
    public static int qs1_process1(int[][] arr){
        if (arr == null || arr.length < 2){
            return 0;
        }
        //1,将数组通过排序变得有序(根据线段的start排序)
        Line[] lines = new Line[arr.length];
        for (int i = 0; i < arr.length; i++) {
            lines[i] = new Line(arr[i][0], arr[i][1]);
        }
        Arrays.sort(lines, new StartComparator());

        //2,记录最大值
        int max = 0;
        //heap默认是小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (Line line : lines) {
            //2.1 将heap中小于start的数都弹出来
            while (!heap.isEmpty() && heap.peek() < line.start) {
                heap.poll();
            }
            //2.2 heap中剩余的end都是大于当前线段start的end，保存数量
            max = Math.max(max, heap.size());
            //2.3 将当前线段的end加入到heap中
            heap.offer(line.end);
        }
        return max+1;
    }



    //题目1 对数器
    public static void main(String[] args) {
        int testTime = 10000;
        int maxValue = 10000;
        int maxLength = 10000;

//        for (int i = 0; i < testTime ; i++){
//            int[][] arr1 = generateArray(maxValue,maxLength);
            int[][] arr1 = {{1,9},{0,7},{8,9}};
            int[][] arr2 = copyarr(arr1);
            int[][] arr3 = copyarr(arr1);

            int max1 = qs1_process1(arr1);
            int max2 = getMax(arr2);
            if (max1 != max2){
                System.out.println("出错了");
                printArray(arr3);
                System.out.println("max1为 "+max1);
                System.out.println("max2为 "+max2);
            }
//        }
    }

    private static void printArray(int[][] arr) {
        for (int i = 0; i < arr.length; i++){
            System.out.print("[" + arr[i][0] + " " + arr[i][1] + "], ");
        }
        System.out.println();
    }

    //题目1的题解
    private static int getMax(int[][] lines) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < lines.length; i++) {
            min = Math.min(min, lines[i][0]);
            max = Math.max(max, lines[i][1]);
        }
        int cover = 0;
        for (double p = min + 0.5; p < max; p += 1) {
            int cur = 0;
            for (int i = 0; i < lines.length; i++) {
                if (lines[i][0] < p && lines[i][1] > p) {
                    cur++;
                }
            }
            cover = Math.max(cover, cur);
        }
        return cover;
    }

    //复制二维数组
    private static int[][] copyarr(int[][] arr) {
        int[][] result = new int[arr.length][2];

        for (int i = 0; i < arr.length; i++){
            result[i][0] = arr[i][0];
            result[i][1] = arr[i][1];
        }
        return result;
    }

    //产生的数组是二维数组
    private static int[][] generateArray(int maxValue, int maxLength) {
        int lentgh = (int)(Math.random() * maxLength)+3;
        int v1,v2;
        int[][] arr = new int[lentgh][2];
        for (int i = 0; i < lentgh; i++){
            do {
                v1 = (int)(Math.random() * maxValue);
                v2 = (int)(Math.random() * maxValue);
            }while (v1 >= v2);
            arr[i][0] = v1;
            arr[i][1] = v2;
        }
        return arr;
    }
}
