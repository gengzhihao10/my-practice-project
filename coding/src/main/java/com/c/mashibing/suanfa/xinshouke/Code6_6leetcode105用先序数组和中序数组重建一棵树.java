package com.c.mashibing.suanfa.xinshouke;

import java.util.HashMap;

public class Code6_6leetcode105用先序数组和中序数组重建一棵树 {

    //二叉树节点
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node(int value) {
            this.value = value;
        }
    }

    /*
     * @author gengzhihao
     * @date 2023/4/19 11:15
     * @description 使用先序遍历数组和中序遍历数组重建二叉树
     * @param pre
     * @param in
     * @return Node
     **/
    public static Node buildTree(int[] pre,int[] in){
        //数组不能为空，长度不能不相等
        if (pre == null || in == null || pre.length != in.length){
            return null;
        }
        //调用递归函数
        return f(pre,0,pre.length-1,in,0,in.length-1);
    }

    /*
     * @author gengzhihao
     * @date 2023/4/19 11:17
     * @description 有一棵树，先序结果是pre[L1,...,R1],中序结果是in[L2,...,R2]
     * 请建出整颗树返回头节点
     * @param pre
     * @param L1
     * @param R1
     * @param in
     * @param L2
     * @param R2
     * @return Node
     **/
    public static Node f(int[] pre,int L1, int R1, int[] in, int L2, int R2){
        //防止左侧或者右侧为空树的情况
        if (L1 > R1){
            return null;
        }

        Node head = new Node(pre[L1]);
        //先序数组和中序数组只有一个时，直接返回新的二叉树节点
        if (L1 == R1){
            return head;
        }

        int find = L2;
        //找到中序数组中的头节点
        while (in[find] != pre[L1]){
            find++;
        }
        head.left = f(pre,L1 + 1,L1 + find - L2, in, L2,find - 1);
        head.right = f(pre,L1 + find - L2 + 1, R1, in, find + 1, R2);
        return head;
    }



    public static Node buildTree2(int[] pre,int[] in){
        //数组不能为空，长度不能不相等
        if (pre == null || in == null || pre.length != in.length){
            return null;
        }
        HashMap<Integer, Integer> valueIndexMap = new HashMap<>();
        for (int i = 0; i < in.length; i++){
            valueIndexMap.put(in[i],i);
        }

        //调用递归函数
        return f2(pre,0,pre.length-1,in,0,in.length-1,valueIndexMap);
    }

    /*
     * @author gengzhihao
     * @date 2023/4/19 11:56
     * @description 2为之前的优化
     * 时间复杂度为o(N)。因为每次调用f2都会生成一个节点，二叉树最终有多少(N)个节点，就生成了多少(N)次节点，复杂度为o(N)
     * @param pre
     * @param L1
     * @param R1
     * @param in
     * @param L2
     * @param R2
     * @param valueIndexMap
     * @return Node
     **/
    public static Node f2(int[] pre,int L1, int R1, int[] in, int L2, int R2, HashMap<Integer,Integer> valueIndexMap){
        //防止左侧或者右侧为空树的情况
        if (L1 > R1){
            return null;
        }

        Node head = new Node(pre[L1]);
        //先序数组和中序数组只有一个时，直接返回新的二叉树节点
        if (L1 == R1){
            return head;
        }

        //找到中序数组中的头节点。
        //省掉了每次调用中序数组遍历的过程
        int find = valueIndexMap.get(pre[L1]);

        head.left = f2(pre,L1 + 1,L1 + find - L2, in, L2,find - 1,valueIndexMap);
        head.right = f2(pre,L1 + find - L2 + 1, R1, in, find + 1, R2, valueIndexMap);
        return head;
    }
}
