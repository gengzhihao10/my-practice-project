package com.c.mashibing.suanfa.tixike;

import java.util.ArrayList;

/*
todo
 题目1，拓扑排序，给定指定的图的结构，统计点次，点次高的点，拓扑序靠前
 题目2，同上一题，统计深度，深度高的点，拓扑序靠前
 */
public class Code17_11拓扑排序的练习 {


    public static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {

        return null;
    }
}
