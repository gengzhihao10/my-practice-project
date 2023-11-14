package com.c.mashibing.suanfa.tixike;

/*
 题目1，
 给定一个二维数组matrix，其中的值不是0就是1，
 返回全部由1组成的子矩形数量
 测试链接：https://leetcode.com/problems/count-submatrices-with-all-ones
 */
public class Code26_5全部由1组成的子矩形数量 {


    /*
     * @author gengzhihao
     * @date 2023/11/11 9:50
     * @description 题目1
     * @param mat
     * @return int
     **/
    public static int numSubmat(int[][] mat){
        if (mat == null || mat.length == 0 || mat[0].length == 0){
            return 0;
        }

        int[] height = new int[mat[0].length];
        int nums = 0;

        for (int i = 0; i < mat.length; i++){
            for (int j = 0; j < mat[i].length; j++){
                height[j] = mat[i][j] == 0 ? 0 : height[j] + 1;
            }
            nums += getNum(height);
        }

        return nums;
    }

    //拿到以第i行为底的直方图里有多少的子矩形
    private static int getNum(int[] height) {
        int num = 0;
        int[] stack = new int[height.length];
        int index = -1;

        for (int i = 0; i < height.length; i++){
            while (index != -1 && height[stack[index]] >= height[i]){
                int popIndex = stack[index--];
                //大于才会计算。等于，就不计算，到了最后一个等于的元素，会在清算阶段直接计算。如果每个等于元素都计算，会有重复面积
                if (height[popIndex] > height[i]){
                    int l = i - 1 - (index == -1 ? -1 : stack[index]);
                    int h = height[popIndex] - Math.max(height[i], index == -1 ? 0 : height[stack[index]]);
                    num += h * count(l);
                }
            }
            stack[++index] = i;
        }

        //清算阶段
        while (index != -1){
            int popIndex = stack[index--];
            int l = height.length - 1 - (index == -1 ? -1 : stack[index]);
            int h = height[popIndex] - (index == -1 ? 0 : height[stack[index]]);
            num += h * count(l);
        }

        return num;
    }

    private static int count(int n) {
        return (n * (n + 1)) / 2;
    }
}
