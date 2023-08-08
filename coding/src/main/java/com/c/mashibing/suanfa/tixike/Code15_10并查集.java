package com.c.mashibing.suanfa.tixike;


import org.springframework.util.CollectionUtils;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/*
 题目1，使用map实现一个并查集
 UnionSet(List<V> list);//构造方法
 void union(V a, V b);
 boolean isSameSet(V a, V b);
 */
public class Code15_10并查集 {


    public static class UnionSet1<V>{

        Map<V,V> parentMap;
        Map<V,Integer> sizeMap;

        public UnionSet1() {
        }

        public UnionSet1(List<V> list){
            parentMap = new HashMap<>();
            sizeMap = new HashMap<>();

            if (CollectionUtils.isEmpty(list)){
                return;
            }
            V parent = list.get(0);
            V child;
            V head = parent;
            int size = 0;
            for (V v : list){
                child = v;
                parentMap.put(child,parent);
                parent = v;
                size++;
            }
            sizeMap.put(head,size);
        }

        public void union(V a, V b){
            V head1 = findHead(a);
            V head2 = findHead(b);
            if (head1 == head2){
                return;
            }

            V bigHead = sizeMap.get(head1) >= sizeMap.get(head2) ? head1 : head2;
            V smallHead = bigHead == head1 ? head2 : head1;

            parentMap.put(smallHead,bigHead);
            sizeMap.put(bigHead,sizeMap.get(bigHead) + sizeMap.get(smallHead));
            sizeMap.remove(smallHead);
        }

        private V findHead(V child) {
            Stack<V> stack = new Stack<>();
            V parent = null;

            while (child != parent){
                stack.push(child);
                child = parent;
                parent = parentMap.get(parent);
            }

            while (!stack.isEmpty()){
                parentMap.put(stack.pop(),parent);
            }

            return parent;
        }

        public boolean isSameSet(V a, V b){
            V head1 = findHead(a);
            V head2 = findHead(b);
            return head1 == head2;
        }
    }





}
