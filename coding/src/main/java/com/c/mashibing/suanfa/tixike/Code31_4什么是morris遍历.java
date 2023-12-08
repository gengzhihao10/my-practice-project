package com.c.mashibing.suanfa.tixike;

/*
todo
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
    public static int minDepth2(TreeNode head){
        return 0;
    }
}
