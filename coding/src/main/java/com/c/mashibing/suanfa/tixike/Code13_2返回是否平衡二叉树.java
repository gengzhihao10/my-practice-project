package com.c.mashibing.suanfa.tixike;

/*
 题目1，给定一颗二叉树的头节点head，返回这颗二叉树是不是平衡二叉树
 */
public class Code13_2返回是否平衡二叉树 {

    //二叉树节点
    private static class Node{
        int value;
        Node left;
        Node right;

        public Node() {
        }

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    /*
     * @author gengzhihao
     * @date 2023/7/27 11:28
     * @description 题目1
     * @param root
     * @return boolean
     **/
    public static boolean qs1_process(Node root){
        if (root == null){
            return true;
        }

        return process1(root).isBT;
    }

    private static Res process1(Node root){
        if (root == null){
            return new Res(true,0);
        }
        Res leftRes = process1(root.left);
        Res rightRes = process1(root.right);

        int height = Math.max(leftRes.height,rightRes.height) + 1;
        boolean isBT = true;
        if (!leftRes.isBT || !rightRes.isBT){
            isBT = false;
        }
        if (Math.abs(leftRes.height - rightRes.height) > 1){
            isBT = false;
        }

        return new Res(isBT,height);
    }

    private static class Res{
        boolean isBT;
        int height;

        public Res() {
        }

        public Res(boolean isBT, int height) {
            this.isBT = isBT;
            this.height = height;
        }

    }
}
