package com.c.mashibing.suanfa.tixike;

/*
 题目1，二叉树结构如下定义
     class Node{
        V value;
        Node left;
        Node right;
        Node parent;
    }
 给你二叉树中的某个节点，返回该节点的后继结点
 */
public class Code12_9求树的最大宽度找后继结点 {

    //二叉树节点
    private static class Node{
        int value;
        Node left;
        Node right;
        Node parent;

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
     * @date 2023/7/26 9:19
     * @description 题目1
     * @param root
     * @return Node
     **/
    public static Node qs1_process(Node node){
        if (node == null){
            return null;
        }

        //后继结点，优先查找右子树的最左侧子节点
        if (node.right != null){
            return getChild(node.right);
        }
        else if (node.parent != null){
            return getParent(node);
        }
        //既没有父节点，又没有右子树，说明是没有右子树的根节点，是没有后继结点的
        return null;
    }

    private static Node getParent(Node child) {
        Node parent = child.parent;
        while (parent != null && parent.right == child){
            child = parent;
            parent = parent.parent;
        }
        return parent;
    }


    private static Node getChild(Node parent) {
        Node cur = parent;
        while (cur.left != null){
            cur = cur.left;
        }
        return cur;
    }


    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
//        n2.right = n5;
        n2.parent = n1;
        n3.parent = n1;
        n4.parent = n2;
//        n5.parent = n2;

        Node res = qs1_process(n2);
        System.out.println(res);
    }
}
