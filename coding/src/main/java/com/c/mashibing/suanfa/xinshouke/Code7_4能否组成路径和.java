package com.c.mashibing.suanfa.xinshouke;

public class Code7_4能否组成路径和 {

    //二叉树节点
    public static class Node{
        public int val;
        public Node left;
        public Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    public static boolean isSum = false;

    /*
     * @author gengzhihao
     * @date 2023/4/21 11:23
     * @description 能否组成路径和
     * @param root
     * @param sum
     * @return boolean
     **/
    public static boolean hasPathSum(Node root, int sum){
        if (root == null){
            return false;
        }
        //isSum是全局变量，如果之前跑过这段算法并且isSum为true，就需要在这次跑的时候先把isSum先置为false
        isSum = false;
        process(root,0,sum);
        return isSum;
    }

    /*
     * @author gengzhihao
     * @date 2023/4/21 11:23
     * @description 递归寻找路径
     * @param x
     * @param preSum
     * @param sum
     **/
    private static void process(Node x, int preSum, int sum) {
        //判断是否为叶子结点
        if (x.left == null && x.right == null){
            //是否满足条件
            if (x.val + preSum == sum){
                isSum = true;
            }
            //是叶子节点，则返回
            return;
        }

        preSum += x.val;
        if (x.left != null){
            process(x.left,preSum,sum);
        }
        if (x.right != null){
            process(x.right,preSum,sum);
        }
    }
}
