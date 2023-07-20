package com.c.mashibing.suanfa.tixike;

import java.util.List;

/*
todo
 题目1，有一颗多叉树，想要按照二叉树的序列化方式得到的结果，再通过二叉树的反序列化的方式能够得到原有的多叉树
 */
public class Code12_6EncodeNAryTreeToBinaryTree1 {

    //多叉树节点
    private static class Node{
        int value;
        List<Node> nexts;

        public Node() {
        }

        public Node(int value) {
            this.value = value;
        }
    }

    /*
     * @author gengzhihao
     * @date 2023/7/20 14:16
     * @description 题目1，序列化
     * @param root
     * @return String
     **/
    public static String qs1_serialize(Node root){
        if (root == null){
            return null;
        }


    }


}
