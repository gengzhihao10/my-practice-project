package com.c.mashibing.suanfa.tixike;

import java.util.*;

/*
 题目1，https://leetcode.com/problems/number-of-islands-ii/
 岛问题，但是岛的位置是一个个位置随机输入的，返回每一步的连通区域的数量
 题目2，上一题，但是m*n远大于输入的k个位置
 */
public class Code16_1leetcode原题547题FreindCircles3 {

    /*
     * @author gengzhihao
     * @date 2023/8/11 9:41
     * @description 题目1
     * @param m 行数
     * @param n 列数
     * @param positions
     * @return List<Integer>
     **/
    public static List<Integer> qs1_process1(int m, int n, int[][] positions){
        List<Integer> res = new ArrayList<Integer>();
        if (m == 0 || n == 0 || positions == null || positions.length == 0){
            res.add(0);
            return res;
        }

        UnionSet1 unionSet1 = new UnionSet1(m*n);
        for (int[] position : positions){
            res.add(process1(position[0],position[1],unionSet1,m,n));
        }
        return res;
    }

    //x第几行，y第几列
    private static Integer process1(int x, int y,UnionSet1 unionSet1,int m,int n) {
        if (x <0 || x>=m || y <0 || y>=n ){
            return 0;
        }
        //输入点的坐标对应的下标为x * n +y
        int index = x * n +y;
        if (unionSet1.sizeArr[index] != 0){
            return unionSet1.getSetNum();
        }

        unionSet1.add(index);
        //上下左右分别为
        if (x-1>0 && x-1<m && y>0 && y<n){
            unionSet1.union(index,(x-1) * n +y);
        }
        if (x+1>0 && x+1<m && y>0 && y<n){
            unionSet1.union(index,(x+1)*n + y);
        }
        if (x>0 && x<m && y-1>0 && y-1<n){
            unionSet1.union(index,x*n+(y-1));
        }
        if (x>0 && x<m && y+1>0 && y+1<n){
            unionSet1.union(index,x*n + (y+1));
        }
        return unionSet1.getSetNum();
    }

    public static class UnionSet1 {

        int[] parentArr;
        int[] sizeArr;
        int[] help;
        int setNum;

        public UnionSet1() {
        }

        public UnionSet1(int length) {
            parentArr = new int[length];
            sizeArr = new int[length];
            help = new int[length];
            setNum = 0;
        }

        public void add(int index){
            parentArr[index] = index;
            sizeArr[index] = 1;
            setNum++;
        }

        public int find(int index){
            int hLentgh = 0;
            while (index != parentArr[index]){
                help[hLentgh++] = index;
                index = parentArr[index];
            }
            for (hLentgh--; hLentgh >= 0; hLentgh--){
                parentArr[hLentgh] = index;
            }
            return index;
        }

        public void union(int index1,int index2){
            int head1 = find(index1);
            int head2 = find(index2);

            if (head1 == head2){
                return;
            }

            int size1 = sizeArr[head1];
            int size2 = sizeArr[head2];

            int bigHead = size1 >= size2 ? head1 : head2;
            int smallHead = head1 == bigHead ? head2 : head1;

            parentArr[smallHead] = bigHead;
            sizeArr[bigHead] += sizeArr[smallHead];
            setNum--;
        }

        public int getSetNum(){
            return setNum;
        }
    }




    //********************************************************************************************************

    /*
     * @author gengzhihao
     * @date 2023/8/14 10:15
     * @description
     * @param m
     * @param n
     * @param positions
     * @return List<Integer>
     **/
    public static List<Integer> qs2_process1(int m, int n, int[][] positions){
        List<Integer> res = new ArrayList<Integer>();
        if (m == 0 || n == 0 || positions == null || positions.length == 0){
            res.add(0);
            return res;
        }

        UnionSet2 unionSet2 = new UnionSet2();
        for (int[] position : positions){
            res.add(process2(position[0],position[1],unionSet2,m,n));
        }
        return res;
    }

    private static Integer process2(int x, int y, UnionSet2 unionSet2,int m,int n) {
        if (x <0 || x>=m || y <0 || y>=n ){
            return 0;
        }
        //输入点的坐标对应的下标为x * n +y
        String cur = buildStr(x,y);
        if (unionSet2.sizeMap.get(cur) != 0){
            return unionSet2.getSetNum();
        }

        unionSet2.add(cur);
        //上下左右分别为
        if (x-1>0 && x-1<m && y>0 && y<n){
            unionSet2.union(cur,buildStr(x-1,y));
        }
        if (x+1>0 && x+1<m && y>0 && y<n){
            unionSet2.union(cur,buildStr(x+1,y));
        }
        if (x>0 && x<m && y-1>0 && y-1<n){
            unionSet2.union(cur,buildStr(x,y-1));
        }
        if (x>0 && x<m && y+1>0 && y+1<n){
            unionSet2.union(cur,buildStr(x,y+1));
        }
        return unionSet2.getSetNum();
    }

    private static String buildStr(int x, int y) {
        return String.valueOf(x) + "-" + String.valueOf(y);
    }


    public static class UnionSet2{

        private Map<String, String> parentMap;
        private Map<String,Integer> sizeMap;
        private int setNum;

        public UnionSet2() {
            parentMap = new HashMap<>();
            sizeMap = new HashMap<>();
            setNum = 0;
        }
        public void add(String node){
            parentMap.put(node,node);
            sizeMap.put(node,1);
            setNum++;
        }


        public int getSetNum(){
            return setNum;
        }

        public String find(String cur){
            String parent = parentMap.get(cur);
            Stack<String> stack = new Stack<>();
            while (parent != cur){
                stack.push(cur);
                cur = parent;
                parent = parentMap.get(parent);
            }
            while (!stack.isEmpty()){
                parentMap.put(stack.pop(),parent);
            }
            return parent;
        }

        public void union(String node1, String node2){
            String head1 = find(node1);
            String head2 = find(node2);
            if (head1 == head2){
                return;
            }

            String bigHead = sizeMap.get(head1) >= sizeMap.get(head2) ? head1 : head2;
            String smallHead = bigHead == head1 ? head2 : head1;

            parentMap.put(smallHead,bigHead);
            sizeMap.put(bigHead, sizeMap.get(bigHead) + sizeMap.get(smallHead));
            sizeMap.remove(smallHead);
            setNum--;
        }
    }


}
