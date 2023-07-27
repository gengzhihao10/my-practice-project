package com.c.mashibing.suanfa.tixike;

/*
todo
 题目1，判断一颗二叉树是否为搜索二叉树
 */
public class Code13_3是否搜索二叉树 {

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

    public static boolean qs1_process(Node root){
        if (root == null){
            return true;
        }

        return process1(root).isBST;
    }

    private static Res process1(Node root){
        if (root == null){
            return null;
        }
        Res leftRes = process1(root.left);
        Res rightRes = process1(root.right);

        if (leftRes == null && rightRes == null){
            return new Res(true,root.value,root.value);
        }

        int min,max;
        if (leftRes != null && rightRes == null){
            min = Math.min(leftRes.min,root.value);
            max = Math.max(leftRes.max,root.value);
        }
        else if (rightRes != null && leftRes == null){
            min = Math.min(rightRes.min,root.value);
            max = Math.max(rightRes.max,root.value);
        }else {
            min = Math.min(root.value,Math.min(leftRes.min,rightRes.min));
            max = Math.max(root.value,Math.max(leftRes.max,rightRes.max));
        }
        boolean isBST = true;
        if ((leftRes != null && !leftRes.isBST) || (rightRes != null && !rightRes.isBST)){
            isBST = false;
        }
        if ((leftRes != null && leftRes.max >= root.value) || (rightRes != null && rightRes.min <= root.value)){
            isBST = false;
        }

        return new Res(isBST,max,min);
    }

    private static class Res{
        boolean isBST;
        int max;
        int min;

        public Res() {
        }

        public Res(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }
}
