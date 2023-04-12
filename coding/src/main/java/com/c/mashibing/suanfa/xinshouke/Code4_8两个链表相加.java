package com.c.mashibing.suanfa.xinshouke;

/**
 * 一个链表，从头到尾表示从低位到高位
 * 形如4->5->1(154)，1->2->3(321)，相加后得到5->7->4(475)
 */
public class Code4_8两个链表相加 {

    //链表节点
    static class ListNode{
        int value;
        ListNode next;

        public ListNode(int value) {
            this.value = value;
        }
    }

    /*
     * @author gengzhihao
     * @date 2023/4/12 11:27
     * @description 返回链表长度
     * @param head
     * @return int
     **/
    public static int listLength(ListNode head){
        int len = 0;
        while (head != null){
            len++;
            head = head.next;
        }
        return len;
    }

    public static ListNode addTwoNumbers(ListNode head1, ListNode head2){
        int len1 = listLength(head1);
        int len2 = listLength(head2);

        //l表示较长(long)的链表的头
        ListNode l = len1 >= len2 ? head1 : head2;
        //s表示较短(short)的链表的头
        ListNode s = l == head1 ?head2 :head1;

        ListNode curL = l;
        ListNode curS = s;
        ListNode last = curL;
        //carry表示进位
        int carry = 0;
        //curNum为两个节点的值相加再加进位
        int curNum = 0;
        //1.短链表不为null，进入第一阶段，长短链表都不为null的情况
        while (curS != null){
            curNum = curL.value + curS.value + carry;
            curL.value = (curNum % 10);
            carry = curNum / 10;
            //curL,curS最后都会走到null，所以需要用last来记录长链表最后的节点，如果进入第三阶段有新的进位节点，则使得最后的last节点的next指向新的进位节点
            last= curL;
            curL = curL.next;
            curS = curS.next;
        }
        //2.从上一个while里出来意味着上一个while结束了，进入第二阶段，只有长链表的部分的情况
        while (curL != null){
            curNum = curL.value + carry;
            curL.value = (curNum % 10);
            carry = curNum / 10;
            last = curL;
            curL = curL.next;
        }
        //3.如果最后的进位不为0，进入第三阶段，new出来一个新的最高位的链表节点
        if (carry != 0){
            last.next = new ListNode(1);
        }
        return l;
    }
}
