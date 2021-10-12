package org.leo.arithmetic.linearsort;

/**
 * 模块名称:桶排序(Bucket sort)
 * 时间复杂度:O(n)
 * 时间复杂度分析：如果要排序的数据有n个，我们把它们均匀地划分到m个桶内，每个桶里就有k=n/m个元素。
 *  每个桶内部使用快速排序，时间复杂度为O(k * logk)。m个桶排序的时间复杂度就是O(m * k * logk)，
 *  因为k=n/m，所以整个桶排序的时间复杂度就是O(n*log(n/m))。当桶的个数m接近数据个数n时，log(n/m)
 *  就是一个非常小的常量，这个时候桶排序的时间复杂度接近O(n)。
 * 算法原理:将要排序的数据分到几个有序的桶里，每个桶里的数据再单独进行排序。
 *  桶内排完序之后，再把每个桶里的数据按照顺序依次取出，组成的序列就是有序。
 *
 * @author xiaochuang.mao
 * @date 2021/6/8 18:51
 */
public class BucketSort {
}
