package com.c.mashibing.suanfa.xinshouke;

public class Code3_5什么是时间复杂度 {

/**
 * 时间复杂度表示的是当数据量很大趋近于无穷时，需要操作多少次常数操作。
 * 如整数相加，多大的整数相加都是2^32位二进制相加，时间是一样的。如数组取出数来，这种操作消耗的时间也是差不多的。这种消耗时间差不多的操作称之为常数操作，时间复杂度默认为o(1)。
 * 而如冒泡排序，它的比较的次数为a*n²+b*n(a,b均为常数)，最高项为n²，因此它的时间复杂度就为o(n²)
 * 如二分查找，时间复杂度为o(log2(N))，因为对于N(N=2的m次方，m=log2(N))长度的数组，它需要不停的去二分，不一定是严格意义上的log2(N)次，但是一定是a*log2(N)次，既时间复杂度次为o(log2(N))
 * 时间复杂度为o(logN)，一般都是默认以2为底，既o(log2(N))=o(log(N))
 *
 * 常见的时间复杂度，o(1),o(logN),o(N),o(N*logN),o(N^2),o(N^3),o(N^k),o(2^N),o(3^N),o(k^N),o(N!)
 *
 */

}
