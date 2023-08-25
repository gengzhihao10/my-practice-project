package com.c.mashibing.suanfa.tixike;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
 题目1，类似17_19第一题。
 使用加强堆，优化其中的getMinDistanceNodeFromDMapAndNotConfirm方法
 将时间复杂度从o(N)降为o(log(N))
 */
public class Code18_1迪瑞克斯拉算法优化1 {


    /*
     * @author gengzhihao
     * @date 2023/8/24 17:22
     * @description 题目1
     * @param from
     * @return HashMap<Integer>
     **/
    public static HashMap<Node,Integer> qs1_process1(Node from,int size){
        if (from == null){
            return null;
        }

        HashMap<Node,Integer> map = new HashMap<>();
        EnhancedHeap heap = new EnhancedHeap(size);
        heap.addOrUpdate(from,0);
        while (!heap.isEmpty()){
            Record record = heap.pop();
            Node cur = record.node;
            int distance = record.distance;
            for (Edge edge : cur.edges){
                heap.addOrUpdate(edge.end,distance+edge.weight);
            }
            map.put(cur,distance);
        }
        return map;
    }

    //加强堆pop返回对象
    private static class Record{
        Node node;
        int distance;

        public Record() {
        }

        public Record(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    /*
    加强堆
     */
    private static class EnhancedHeap{
        HashMap<Node,Integer> distanceMap;//距离表
        Node[] nodes;//加强堆的数组
        Map<Node,Integer> indexMap;//反向索引表.-1表示进来过但是被弹出了
        int size;//容量

        public EnhancedHeap(int size) {
            this.size = size;
            nodes = new Node[size];
            distanceMap = new HashMap<>();
            indexMap = new HashMap<>();
        }

        public boolean isEmpty(){
            return size == 0;
        }

        public void addOrUpdate(Node node, int newDistance) {
            //如果不存在
            if (!indexMap.containsKey(node)){
                nodes[size] = node;
                int index = heapInsert(size++);
                indexMap.put(node,index);
                distanceMap.put(node,newDistance);
            }
            //存在，且没有被弹出
            else if (indexMap.containsKey(node) && indexMap.get(node) != -1){
                distanceMap.put(node,Math.min(distanceMap.get(node),newDistance));
                int index = heapInsert(indexMap.get(node));
                indexMap.put(node,index);
            }
            //存在过，被弹出了，忽略
        }

        private void swap(Integer i, int j) {
            Node tempNode = nodes[i];
            nodes[i] = nodes[j];
            nodes[j] = tempNode;
            indexMap.put(nodes[i],j);
            indexMap.put(nodes[j],i);
        }

        //向上调整
        private int heapInsert(int index) {
            int parent = (index - 1)/2;
            while (parent >= 0){
                if (distanceMap.get(nodes[parent]) <= distanceMap.get(nodes[index])){
                    break;
                }
                index = parent;
                parent = (index - 1)/2;
            }
            return index;
        }

        public Record pop() {
            swap(0,size-1);
            Record record = new Record(nodes[size-1],distanceMap.get(nodes[size-1]));
            nodes[--size] = null;
            int index = heapify(0);
            indexMap.put(record.node,index);
            return record;
        }

        //向下调整
        private int heapify(int index) {
            int smallChild = distanceMap.get(nodes[index * 2 + 1]) < distanceMap.get(nodes[index * 2 +2])
                    ? index * 2 + 1 : index * 2 + 2;
            while (smallChild < size){
                if (distanceMap.get(nodes[index]) <= distanceMap.get(nodes[smallChild])){
                    break;
                }
                index = smallChild;
                smallChild = distanceMap.get(nodes[smallChild * 2 + 1]) < distanceMap.get(nodes[smallChild * 2 +2])
                        ? smallChild * 2 + 1 : smallChild * 2 + 2;
            }
            return index;
        }
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

        public Edge(int weight) {
            this.weight = weight;
        }
    }
}
