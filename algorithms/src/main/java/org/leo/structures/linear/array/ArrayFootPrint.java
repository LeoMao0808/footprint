package org.leo.structures.linear.array;

/**
 * 模块名称:数组
 * 问题 :为什么数组要从0开始编号，而不是从1开始呢？
 *  答 :
 *      1.减少一次减法指令
 *        当i从0开始,寻址公式为 arr[i]_address = base_address + i * data_type_size
 *       当i从1开始,寻址公式为 arr[i]_address = base_address + (i-1) * data_type_size
 *      2. 历史原因,C语言设计者用0开始计数数组下标,后续语言效仿C语言,减少学习成本.
 *
 * 数组（Array）:线性表数据结构,用一组连续的内存空间，来存储一组具有相同类型的数据。
 *
 * 随机访问的寻址公式: arr[i]_address = base_address + i * data_type_size;
 * 1. base_address:数组分配内存块的首地址.
 * 2. data_type_size: 数组存储类型的字节长度. 例如:int类型data_type_size = 4
 *
 * 查询:数组根据下标随机访问的时间复杂度为O(1).
 * 插入:
 *  1.有序数组: 最好情况插入尾部为O(1),最坏情况插入头部需要移动
 *             i~n的元素时间复杂度为O(N),平均复杂度O(n).
 *  2.无序数组: i~n不移动,将i位置的元素放置队尾,新元素直接插入i位置,
 *             特定场景下时间复杂度为O(1)
 *删除:与插入才操作类似,最好O(1),最坏O(n),平均O(n),在不追求数组中数据的连续性的情况下
 *      可以只标记元素被删除,当数组没有空间存储数据时,执行真正的删除,可以减少数据搬移操作.
 *
 * @author xiaochuang.mao
 * @date 2021/5/23 16:10
 */
public class ArrayFootPrint {
}
