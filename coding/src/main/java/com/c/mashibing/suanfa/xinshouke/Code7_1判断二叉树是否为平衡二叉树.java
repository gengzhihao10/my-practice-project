package com.c.mashibing.suanfa.xinshouke;

import java.util.Map;

public class Code7_1判断二叉树是否为平衡二叉树 {

    //二叉树节点
    public static class Node{
        public int val;
        public Node left;
        public Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    //以某个节点为头的时候，1)整颗树是否平衡 2)整颗树的高度是什么
    public static class Info{
        public boolean isBalanced;
        public int height;

        public Info(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }


    /*
     * @author gengzhihao
     * @date 2023/4/20 11:17
     * @description 判断二叉树是否为平衡二叉树
     * @param root
     * @return boolean
     **/
    public static boolean isBalanced(Node root){
        return process(root).isBalanced;
    }

    /*
     * @author gengzhihao
     * @date 2023/4/20 11:07
     * @description 传入一个节点，返回这个树是否平衡和高度信息
     * @param x
     * @return Info
     **/
    private static Info process(Node x) {
        //节点为空的情况
        if (x == null){
            return new Info(true, 0);
        }

        //递归查询左右子树的平衡和高度信息
        Info leftInfo = process(x.left);
        Info rightinfo= process(x.right);
        //计算高度
        int height = Math.max(leftInfo.height, rightinfo.height) + 1;
        //判断是否平衡
        boolean isBalanced = leftInfo.isBalanced && rightinfo.isBalanced
                && Math.abs(leftInfo.height - rightinfo.height) < 2;
        return new Info(isBalanced,height);
    }
}
