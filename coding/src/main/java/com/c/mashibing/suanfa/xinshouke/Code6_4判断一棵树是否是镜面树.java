package com.c.mashibing.suanfa.xinshouke;

public class Code6_4判断一棵树是否是镜面树 {

    //二叉树节点
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node(int value) {
            this.value = value;
        }
    }


    public static boolean isSymmetric(Node root){
        return isMirror(root,root);
    }

    private static boolean isMirror(Node h1, Node h2) {
        //有一个为空，另外一个不为空，异或结果为true，不是镜像，返回false
        if (h1 == null ^ h2 == null){
            return false;
        }
        //左右都是null，为镜像，返回true
        if (h1 == null && h2 == null){
            return true;
        }
        //递归调用查询是否为镜像
        return h1.value == h2.value && isMirror(h1.left, h2.right) && isMirror(h1.right, h2.left);
    }
}
