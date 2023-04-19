package com.c.mashibing.suanfa.xinshouke;

public class Code6_3二叉树基本概念和判断两棵树是否结构相同 {

    //二叉树节点
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }


    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        pre(head);
        System.out.println("==========");
        in(head);
        System.out.println("==========");
        pos(head);
        System.out.println("==========");

    }

    //先序遍历-递归
    private static void pre(Node head) {
        if (head == null){
            return;
        }
        System.out.println(head.value);
        pre(head.left);
        pre(head.right);
    }

    //中序遍历-递归
    private static void in(Node head) {
        if (head == null){
            return;
        }
        in(head.left);
        System.out.println(head.value);
        in(head.right);
    }

    //后续便利-递归
    private static void pos(Node head) {
        if (head == null){
            return;
        }
        pos(head.left);
        pos(head.right);
        System.out.println(head.value);
    }

    //递归序
    //第几次打印当前节点，决定了是先序遍历(第1次来到当前节点就打印)、中序遍历(第2次回到节点才打印)、还是后序遍历(第3次打印)
    public static void f(Node head){
        if (head == null){
            return;
        }
        //第1次来到当前节点
        f(head.left);
        //第2次回到当前节点
        f(head.right);
        //第3次回到当前节点
    }

    //判断两棵树的结构是否相同
    public static boolean isSameTree(Node p, Node q){
        //只有当q和q有一个为空，一个不为空，异或结果为true，返回false
        //两个都为空或都不为空，则异或结果为false，继续向下走
        if (p == null ^ q == null){
            return false;
        }
        //两个都为空树，也是相同的结构
        if (p == null ^ q == null){
            return true;
        }
        return p.value == q.value && isSameTree(p.left, q.left) && isSameTree(p.right,q.right);
    }

}
