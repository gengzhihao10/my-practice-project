package com.c.mashibing.suanfa.tixike;

import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
 题目1，一个图中，给定出发点，从出发点出发，能到的了的点中，距离是多少，使用迪瑞克斯拉算法
 有向图，可以有环，权重不为负。
 */
public class Code17_19迪瑞克斯拉算法 {

    /*
     * @author gengzhihao
     * @date 2023/8/18 19:48
     * @description 题目1
     * @param from
     * @return HashMap<Node,Integer>
     **/
    public static HashMap<Node, Integer> dijkstra1(Node from){
        if (from == null){
            return null;
        }

        Map<Node,Integer> distanceMap = new HashMap<>();
        distanceMap.put(from,0);
        HashMap<Node,Integer> res = new HashMap<>();
        Node cur = getMinDistanceNodeFromDMapAndNotConfirm(distanceMap,res);
        while (distanceMap.size() != res.size()){
            for (Edge edge : cur.edges){
                Node endNode = edge.end;
                if (!distanceMap.containsKey(endNode)){
                    distanceMap.put(endNode,distanceMap.get(cur) + edge.weight);
                }
                else {
                    distanceMap.put(endNode,Math.min(distanceMap.get(endNode),distanceMap.get(cur) + edge.weight));
                }
            }
            res.put(cur,distanceMap.get(cur));
            cur = getMinDistanceNodeFromDMapAndNotConfirm(distanceMap,res);
        }
        return res;
    }

    private static Node getMinDistanceNodeFromDMapAndNotConfirm(Map<Node, Integer> distanceMap, Map<Node, Integer> res) {
        Node ans = null;
        int minDistance = 0;
        for (Map.Entry<Node,Integer> entry : distanceMap.entrySet()){
            if (res.containsKey(entry.getKey())){
                continue;
            }
            if (minDistance > entry.getValue()){
                minDistance = entry.getValue();
                ans = entry.getKey();
            }
        }
        return ans;
    }

    private static class Node{
        int value;
        int in;
        int out;
        ArrayList<Node> nexts;
        ArrayList<Edge> edges;

        public Node() {
        }

        public Node(int value) {
            this.value = value;
            in = 0;
            out = 0;
            nexts = new ArrayList<>();
            edges = new ArrayList<>();
        }
    }

    private static class Edge{
        int weight;
        Node start;
        Node end;

        public Edge() {
        }

        public Edge(int weight, Node start, Node end) {
            this.weight = weight;
            this.start = start;
            this.end = end;
        }
    }
}
