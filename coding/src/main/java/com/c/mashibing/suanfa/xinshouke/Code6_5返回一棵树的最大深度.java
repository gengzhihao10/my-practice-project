package com.c.mashibing.suanfa.xinshouke;

public class Code6_5返回一棵树的最大深度 {

    //二叉树节点
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node(int value) {
            this.value = value;
        }
    }

    //返回一棵树的最大深度
    public static int maxDepth(Node root){
        if (root == null){
            return 0;
        }
        return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
    }
}
