package com.c.mashibing.suanfa.tixike;

/*
 题目1，给定一棵二叉树的头节点head
 求以head为头的树中，最小深度是多少？
 本题测试链接 : https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/
 */
public class Code31_4什么是morris遍历 {


    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int x) {
            val = x;
        }
    }


    // 下面的方法是一般解
    public static int minDepth1(TreeNode head) {
        if (head == null) {
            return 0;
        }
        return p(head);
    }

    // 返回x为头的树，最小深度是多少
    public static int p(TreeNode x) {
        if (x.left == null && x.right == null) {
            return 1;
        }
        // 左右子树起码有一个不为空
        int leftH = Integer.MAX_VALUE;
        if (x.left != null) {
            leftH = p(x.left);
        }
        int rightH = Integer.MAX_VALUE;
        if (x.right != null) {
            rightH = p(x.right);
        }
        return 1 + Math.min(leftH, rightH);
    }


    // 下面的方法是morris遍历的解
    public static int minDepth2(TreeNode root){
        if (root == null){
            return 0;
        }
        if (root.left == null && root.right == null){
            return 1;
        }
        TreeNode cur = root;
        TreeNode mostRight = null;

        int curLevel = 0;
        int min = Integer.MAX_VALUE;

        while (cur != null){
            int mostRightLevel = 1;
            mostRight = cur.left;
            //左孩子不为空
            if (mostRight != null){
                while (mostRight.right != null && mostRight.right != cur){
                    mostRightLevel++;
                    mostRight = mostRight.right;
                }
                //第一次到左子树最右孩子
                if (mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    curLevel++;
                    continue;
                }
                //第二次来到最右孩子
                else {
                    if (mostRight.left == null){
                        min = Math.min(min,curLevel);
                    }
                    curLevel -= mostRightLevel;
                    mostRight.right = null;
                }
            }
            //左孩子为空，只能到达一次。
            else {
                curLevel++;
            }
            cur = cur.right;
        }

        //单独计算根节点的最右孩子，这个节点如果是叶子节点无法遍历到第二次
        curLevel = 1;
        mostRight = root;
        while (mostRight.right != null){
            mostRight = mostRight.right;
            curLevel++;
        }
        if (mostRight.left == null){
            min = Math.min(min,curLevel);
        }
        return min;
    }
}
