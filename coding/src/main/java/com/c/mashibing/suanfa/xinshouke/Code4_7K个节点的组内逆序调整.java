package com.c.mashibing.suanfa.xinshouke;

/**
 * 开始 上难度了。这道题难的不在于算法，而在于coding的边界控制能力
 */
public class Code4_7K个节点的组内逆序调整 {

    static class ListNode<V>{
        V value;
        ListNode<V> next;

        public ListNode(V value) {
            this.value = value;
        }
    }

    /*
     * @author gengzhihao
     * @date 2023/4/12 9:37
     * @description 返回从当前组start节点开始计数的第K个节点
     * @param start group的开始节点
     * @param k 组内的数量
     * @return ListNode 下一组头节点的前一个节点
     **/
    public static ListNode getGroupEnd(ListNode start, int k){
        //start != null，是为了防止剩下的数量不够K个，返回null，表示剩下的已经不够K个
        while (--k != 0 && start != null){
            start = start.next;
        }
        return start;
    }

    /*
     * @author gengzhihao
     * @date 2023/4/12 10:21
     * @description 将从start起到end截止的一组节点反转，并且传入的start节点的next指向下一组节点的start节点
     * @param start
     * @param end
     **/
    public static void reverse(ListNode start, ListNode end){
        end = end.next;
        ListNode pre = null;
        ListNode cur = start;
        ListNode next = null;
        while (cur != end){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        start.next = end;
    }

    /*
     * @author gengzhihao
     * @date 2023/4/12 10:34
     * @description 以K个节点为一组，反转组内链表，不够一组的不反转。
     * 形如a->b->c(第一组end) -> d->e->f -> g,k=3,反转为c->b->a -> f->e->d -> g
     * @param head
     * @param k
     * @return ListNode
     **/
    public static ListNode reverseGroup(ListNode head, int k){
        ListNode start = head;
        ListNode end = getGroupEnd(start,k);
        //一组都凑不齐的，不反转，直接返回旧的head
        if (end == null){
            return head;
        }
        //第一组凑齐了，以后的返回head一定是第一组的end
        head = end;
        reverse(start,end);
        //上一组的结尾节点
        ListNode lastEnd = start;
        while (lastEnd.next != null){
            start = lastEnd.next;
            end = getGroupEnd(start,k);
            if (end == null){
                return head;
            }
            reverse(start,end);
            lastEnd.next = end;
            lastEnd = start;
        }
        return head;
    }
}
