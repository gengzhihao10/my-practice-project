package com.c.mashibing.suanfa.tixike;

import java.util.*;

/*
 题目1，
 想象一下标准的俄罗斯方块游戏，X轴是积木最终下落到底的轴线
 下面是这个游戏的简化版：
 1）只会下落正方形积木
 2）[a,b] -> 代表一个边长为b的正方形积木，积木左边缘沿着X = a这条线从上方掉落
 3）认为整个X轴都可能接住积木，也就是说简化版游戏是没有整体的左右边界的
 4）没有整体的左右边界，所以简化版游戏不会消除积木，因为不会有哪一层被填满。
 给定一个N*2的二维数组matrix，可以代表N个积木依次掉落，
 返回每一次掉落之后的最大高度
 https://leetcode.com/problems/falling-squares/

 */
public class Code32_6线段树实例2积方块问题 {


    public static class SegmentTree{
        private int[] max;
        private int[] update;
        private boolean[] updateOrNot;

        public SegmentTree(int length) {
            int N = length + 1;
            max = new int[N << 2];
            update = new int[N << 2];
            updateOrNot = new boolean[N << 2];
        }

        public int query(int L, int R, int l, int r, int root) {
            if (L <= l && R >= r){
                return max[root];
            }

            int mid = (l + r) / 2;
            pushDown(root,mid - l + 1, r - mid);

            int lm = Integer.MIN_VALUE, rm = Integer.MIN_VALUE;
            if (L <= mid){
                lm = query(L,R,l,mid,root << 1);
            }
            if (R >= mid + 1){
                rm = query(L, R,mid + 1,r, root << 1 | 1);
            }
            return Math.max(lm, rm);
        }

        private void pushDown(int root, int ln, int rn) {
            if (updateOrNot[root]){
                updateOrNot[root << 1] = true;
                update[root << 1] = update[root];
                max[root << 1] = update[root];

                updateOrNot[root << 1 | 1] = true;
                update[root << 1 | 1] = update[root];
                max[root << 1 | 1] = update[root];

                updateOrNot[root] = false;
            }

        }

        public void update(int L, int R, int C, int l ,int r, int root){
            if (L <= l && r <= R){
                max[root] = C;
                updateOrNot[root] = true;
                update[root]= C;
                return;
            }

            int mid = (l + r) / 2;
            pushDown(root,mid - l + 1, r - mid);

            if (L <= mid){
                update(L,R,C,l,mid,root << 1);
            }
            if (R >= mid + 1){
                update(L, R,C,mid + 1,r, root << 1 | 1);
            }
            max[root] = Math.max(max[root << 1], max[root << 1 | 1]);
        }
    }

    /*
     * @author gengzhihao
     * @date 2023/12/12 16:00
     * @description 题目1，先实现没有节省空间和时间的一版
     * @param positions
     * @return List<Integer>
     **/
    public static List<Integer> fallingSquares(int[][] positions){
        if (positions == null || positions.length == 0){
            return null;
        }
        int length = 0;
        for (int[] position : positions){
            length = Math.max(length, position[1] + position[0] - 1);
        }
        SegmentTree segmentTree = new SegmentTree(length);

        int max = 0;
        List<Integer> res = new ArrayList<>();
        int L = 0, R = 0, N = length + 1;
        for (int[] position : positions){
            L = position[0];
            R = position[1] + position[0] - 1;
            int height = position[1] + segmentTree.query(L, R, 1, N, 1);
            max = Math.max(max,height);
            res.add(max);
            segmentTree.update(L,R,height,1,N,1);
        }
        return res;
    }



    public static void main(String[] args) {
        int[][] input = {{100,100},{200,100}};
        System.out.println(fallingSquares(input));;
    }


    //*******************************************************************************************

    /*
     * @author gengzhihao
     * @date 2023/12/13 14:37
     * @description 题目1，使用离散化优化，省时间和空间的优化版本
     * @param positions
     * @return List<Integer>
     **/
    public List<Integer> fallingSquares2(int[][] positions){
        if (positions == null || positions.length == 0){
            return null;
        }
        Map<Integer,Integer> map = getIndexMap(positions);
        int length = map.size();
        SegmentTree segmentTree = new SegmentTree(length);

        int max = 0;
        List<Integer> res = new ArrayList<>();
        int L = 0, R = 0, N = length + 1;
        for (int[] position : positions){
            L = map.get(position[0]);
            R = map.get(position[1] + position[0] - 1);
            int height = position[1] + segmentTree.query(L, R, 1, N, 1);
            max = Math.max(max,height);
            res.add(max);
            segmentTree.update(L,R,height,1,N,1);
        }
        return res;
    }

    private Map<Integer, Integer> getIndexMap(int[][] positions) {
        Set<Integer> set = new TreeSet<>();
        for (int[] position : positions){
            set.add(position[0]);
            set.add(position[0] + position[1] - 1);
        }
        Map<Integer,Integer> map = new HashMap<>();
        int count = 0;
        for (Integer index : set){
            map.put(index,++count);
        }
        return map;
    }
}
