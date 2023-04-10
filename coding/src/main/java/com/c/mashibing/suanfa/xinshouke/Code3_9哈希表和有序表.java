package com.c.mashibing.suanfa.xinshouke;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.TreeMap;

@Slf4j
public class Code3_9哈希表和有序表 {

    static class Node{
        int value;

        public Node(int value) {
            this.value = value;
        }
    }


    public static void main(String[] args) {
        //时间复杂度为log(1)，也就是复杂度是一个常数，只是这个常数比较大
        HashMap<String, String> map = new HashMap<>();
        map.put("name","gengzhihao");
        log.info(map.containsKey("name")+"");
        map.remove("name");
        log.info(map.containsKey("name")+"");

        String test1 = "test";
        String test2 = "test";
        log.info(test1.hashCode()+"");
        log.info(test2.hashCode()+"");


        //=========================
        //TreeMap的时间复杂度为Log(N)
        TreeMap<Integer,String> treeMap = new TreeMap();
        treeMap.put(3,"我是3");
        treeMap.put(4,"我是3");
        treeMap.put(5,"我是3");
        treeMap.put(6,"我是3");
        treeMap.put(7,"我是3");
        treeMap.put(8,"我是3");
        log.info(treeMap.containsKey(9)+"");
        treeMap.put(3,"他是3");
        log.info(treeMap.get(3)+"");
        treeMap.remove(3);
        log.info(treeMap.get(3));

        log.info(treeMap.firstKey()+"");
        log.info(treeMap.lastKey()+"");

        //小于等于5 离5最近的key返回
        log.info(treeMap.floorKey(5)+"");
        //大于等于6，离6最近的key返回
        log.info(treeMap.floorKey(6)+"");

        //TreeMap要求Key是可以比较的
        TreeMap<Node,String> treeMap2 = new TreeMap<>();
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        treeMap2.put(node1,"我是node1");
        treeMap2.put(node2,"我是node2");
    }
}
