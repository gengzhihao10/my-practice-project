package com.c.mashibing.suanfa.tixike;

import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;

/*
 题目1，有一颗多叉树，想要按照二叉树的序列化方式得到的结果，再通过二叉树的反序列化的方式能够得到原有的多叉树
 */
public class Code12_6EncodeNAryTreeToBinaryTree1 {

    //多叉树节点
    private static class Node{
        char value;
        List<Node> nexts;

        public Node() {
        }

        public Node(char value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    //二叉树节点
    private static class TreeNode {
        char value;
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        public TreeNode(char value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    /*
     * @author gengzhihao
     * @date 2023/7/20 14:16
     * @description 题目1，
     * @param root
     * @return String
     **/
    public static TreeNode qs1_serialize(Node root){
        if (root == null){
            return null;
        }
        TreeNode newRoot = serialize(root);
        return newRoot;
    }

    private static TreeNode serialize(Node root) {
        if (root == null){
            return null;
        }
        TreeNode head = new TreeNode(root.value);
        TreeNode cur = null;
        if (CollectionUtils.isEmpty(root.nexts)){
            return head;
        }
        for (Node node : root.nexts){
            if (cur == null){
                cur = serialize(node);
                head.left = cur;
                continue;
            }
            cur.right = serialize(node);
            cur = cur.right;
        }
        return head;
    }

    /*
     * @author gengzhihao
     * @date 2023/7/22 10:13
     * @description 题目1，二叉树变多叉树
     * @param root
     * @return Node
     **/
    public static Node qs1_deserialize(TreeNode root){
        if (root == null){
            return null;
        }

        return desirialize(root);
    }


    private static Node desirialize(TreeNode root) {
        if (root == null){
            return null;
        }
        List<Node> children = new LinkedList<>();
        Node head = new Node(root.value);
        if (root.left == null){
            return head;
        }
        children.add(desirialize(root.left));
        TreeNode cur = root.left.right;
        while (cur != null){
            children.add(desirialize(cur));
            cur = cur.right;
        }
        head.nexts = children;

        return head;
    }

    public static void main(String[] args) {
        Node a = new Node('a');
        Node b = new Node('b');
        Node c = new Node('c');
        Node d = new Node('d');
        Node e = new Node('e');
        Node f = new Node('f');
        Node g = new Node('g');
        Node h = new Node('h');
        Node i = new Node('i');
        Node j = new Node('j');
        Node k = new Node('k');
        Node l = new Node('l');
        Node m = new Node('m');
        Node n = new Node('n');
        a.nexts = new LinkedList<>();
        a.nexts.add(b);
        a.nexts.add(c);
        a.nexts.add(d);
        a.nexts.add(e);

        b.nexts = new LinkedList<>();
        b.nexts.add(f);

        c.nexts = new LinkedList<>();
        c.nexts.add(g);
        c.nexts.add(h);

        d.nexts = new LinkedList<>();
        d.nexts.add(i);
        d.nexts.add(j);
        d.nexts.add(k);
        d.nexts.add(l);

        e.nexts = new LinkedList<>();
        e.nexts.add(m);
        e.nexts.add(n);

        //b下面的f没有序列化出来
        TreeNode newRoot = qs1_serialize(a);

         System.out.println("test");

         Node newRoot2 = qs1_deserialize(newRoot);
        System.out.println("test2");
    }
}
