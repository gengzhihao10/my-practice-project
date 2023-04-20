package com.c.mashibing.suanfa.xinshouke;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Code7_1leetcode102二叉树按层遍历并收集节点 {

    //二叉树节点
    public static class Node{
        public int val;
        public Node left;
        public Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    /*
     * @author gengzhihao
     * @date 2023/4/20 9:40
     * @description 二叉树按层遍历，每一层节点从左到右作为一个小链表，加入到大链表中，返回大链表
     * @param root
     * @return List<List<Integer>>
     **/
    public List<List<Integer>> levelOrderBottom(Node root){
        List<List<Integer>> ans = new LinkedList<>();
        if (root == null){
            return ans;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        //队列为空时，说明没有子树加进来了，结束循环
        while (!queue.isEmpty()){
            //按照队列的容量进行遍历。size要在这里定好了，不能在for中i<queue.size()，因为for中会添加新的和去掉旧的，容量在变化
            int size = queue.size();
            List<Integer> curAns = new LinkedList<>();
            for (int i = 0; i < size; i++){
                //将队列中的节点弹出
                Node curNode = queue.poll();
                curAns.add(curNode.val);
                //将当前节点的左右子树按从左到右的顺序加入队列中
                if (curNode.left != null){
                    queue.add(curNode.left);
                }
                if (curNode.right != null){
                    queue.add(curNode.right);
                }
            }
            //将生产好的一层节点的链表加入到总链表中
            ans.add(0, curAns);
        }
        return ans;
    }






    public static void main(String[] args) {


    }
}
