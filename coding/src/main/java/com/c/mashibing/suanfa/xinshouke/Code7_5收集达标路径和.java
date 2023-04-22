package com.c.mashibing.suanfa.xinshouke;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Code7_5收集达标路径和 {

    //二叉树节点
    public static class Node{
        public int val;
        public Node left;
        public Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    //寻找符合预期的所有路径
    public static List<List<Integer>> pathSum(Node root, int sum){
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null){
            return ans;
        }
        ArrayList<Integer> path = new ArrayList<>();
        process(root, path, 0, sum, ans);
        return ans;

    }


    /*
     * @author gengzhihao
     * @date 2023/4/22 9:59
     * @description
     * @param x 当前节点
     * @param path 当前节点所在路径
     * @param preSum 当前节点所在路径的之前节点的和
     * @param sum 预期的和
     * @param ans 所有符合要求的路径集合
     **/
    public static void process(Node x, List<Integer> path, int preSum, int sum, List<List<Integer>> ans){
        //x为叶子结点
        if (x.left == null && x.right == null){
            if (preSum + x.val == sum){
                path.add(x.val);
                ans.add(copy(path));
                //返回前清理现场，供返回父节点后调用
                path.remove(path.size()-1);
            }
            return;
        }

        //x为非叶子节点
        path.add(x.val);
        preSum += x.val;
        if (x.left != null){
            process(x.left, path, preSum, sum, ans);
        }
        if (x.right != null){
            process(x.right, path, preSum, sum, ans);
        }
        path.remove(path.size()-1);

    }

    /*
     * @author gengzhihao
     * @date 2023/4/22 9:51
     * @description 复制出一个新List，使得返回值与入参内容相同但是引用不同
     * @param path
     * @return List<Integer>
     **/
    public static List<Integer> copy(List<Integer> path){
        List<Integer> ans = new ArrayList<>();
        for (Integer num : path){
            ans.add(num);
        }
        return ans;
    }
}
