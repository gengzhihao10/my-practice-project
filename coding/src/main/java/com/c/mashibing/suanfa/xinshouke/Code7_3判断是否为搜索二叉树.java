package com.c.mashibing.suanfa.xinshouke;

public class Code7_3判断是否为搜索二叉树 {

    //二叉树节点
    public static class Node{
        public int val;
        public Node left;
        public Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    //返回信息
    public static class Info{
        //当前节点为根的树是否为搜索二叉树
        public boolean isBST;
        //当前节点为根的树的最大值
        public int max;
        //当前节点为根的树的最小值
        public int min;

        public Info(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    /*
     * @author gengzhihao
     * @date 2023/4/21 10:07
     * @description 判断是否为搜索二叉树
     * @param root
     * @return boolean
     **/
    public static boolean isValidBST(Node root){
        return process(root).isBST;
    }



    /*
     * @author gengzhihao
     * @date 2023/4/21 10:02
     * @description 返回当前节点是否为搜索二叉树、最大值、最小值等信息
     * @param x
     * @return Info
     **/
    public static Info process(Node x){
        if (x == null){
            return null;
        }

        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        int max = x.val;
        int min = x.val;
        if (leftInfo != null){
            max = Math.max(leftInfo.max, max);
            min = Math.min(leftInfo.min,min);
        }
        if (rightInfo != null){
            max = Math.max(rightInfo.max, max);
            min = Math.min(rightInfo.min,min);
        }

        /**
         * 判断当前节点为搜索二叉树，需要同时满足4个条件
         * 1，左子树不为空，且左子树为搜索二叉树。左子树为空，默认为搜索二叉树
         * 2，右子树不为空，且右子树为搜索二叉树。右子树为空，默认为搜索二叉树
         * 3，左子树不为空时，左子树的最大值＜当前节点的值。如果左子树为空，则不进行判断
         * 4，右子树不为空时，右子树的最小值>当前节点的值。如果右子树为空，则不进行判断
         */
        boolean isBST = true;
        //判断左右子树是否为搜索二叉树
        if (leftInfo != null && !leftInfo.isBST){
            isBST = false;
        }
        if (rightInfo != null && !rightInfo.isBST){
            isBST = false;
        }
        boolean leftMaxLessX = leftInfo == null ? true : (leftInfo.max < x.val);
        boolean rightMinMoreX = rightInfo == null ? true : (rightInfo.min > x.val);
        if (!leftMaxLessX || !rightMinMoreX){
            isBST = false;
        }

        return new Info(isBST,max,min);
    }

}
