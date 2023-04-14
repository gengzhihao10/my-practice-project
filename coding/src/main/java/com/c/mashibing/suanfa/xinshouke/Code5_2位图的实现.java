package com.c.mashibing.suanfa.xinshouke;

import java.sql.SQLOutput;

public class Code5_2位图的实现 {

    //位图
    public static class  BitMap{

        private long[] bits;

        //位图的实现需要知道位图的最大值
        public BitMap(int max) {
            /**
             * (max + 64) >> 6 等同于 (max + 64)/2^6,也就是除64
             * 为什么要加64，是因为0-63需要一个long类型的数来表示
             * 例如max=0,(0+64)/64=1,(0+63)/64=1,其他max以此类推
             */
            bits = new long[(max + 64) >> 6];
        }

        /*
         * @author gengzhihao
         * @date 2023/4/14 10:21
         * @description 将数添加进位图中
         * @param num 小于最大值
         **/
        public void add(int num){
            /**
             * num/64可以知道这个数对应位图中数的下标，
             * 如70，70/64=1,对应位图中下标为1的数
             *
             * 63在二进制上，为后七位都为1，前面都是0的这么一个数，num&63，实际上就是在二进制上只保留了后面7位数，也就是相当于num%64
             * 如70%64=6，也就是第6比特位上的数为1，因此要左移1位，从第0位移动到第6位上
             * 相当于bits[0]为000000...(从第0位到第63位共64个比特位)，bits[1]为00000010000...
             * (上一个long可以表示从0到63共64个数，在第二个Long上，70-64=6，为第6位，和num&63的结果一致)
             *
             * 1L << (num & 63)就是比特位从第0位向右移动6位
             *
             * long的默认值为0，所有bit位都是0，|=意为 或运算 并 赋值，也就是将bits[1]的比特位第6位置位1并赋值给bits[1]
             *
             * 一定要上1L左移，不能是1.因为1L为long型，有64位。而1为int，只有32位，超过32位的向左移动会出现错误。
             */
            bits[num >> 6] |= (1L << (num & 63));
        }

        public void delete(int num){
            /**
             * 对存在的数取反，所在位置1取反为0，其他为1，这时候再与运算原来的位图，要删除的那个数，0&1，得到0，其他位置1&0，得到0，数被删除了
             */
            bits[num >> 6] &= ~(1L << (num & 63));
        }

        public boolean contains(int num){
            return (bits[num >> 6] & (1L << (num & 63) )) != 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(1L<<1);
        System.out.println(70%64);
    }
}
