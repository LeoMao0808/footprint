package org.leo.arithmetic.sort;

import java.util.Arrays;

/**
 * 模块名称:快速排序(QuickSort)
 * 时间复杂度:O(nlogn)
 * 核心思想: 先确定分区p.遍历数组,小于p放在左边,大于p放在右边,分为三个部分 head...p-1 ,p,p+1...tail
 * 据分治、递归的处理思想，可以用递归排序下标从p到q-1之间的数据和下标从q+1到r之间的数据，直到区间缩小为1.
 * 实现方式:递归
 *  递归公式: quicksort(head...tail) = quicksort(head...p-1) + quciksort(p+1...tail)
 *  终止条件: head >= tail
 * 归并算法与快速排序区别
 * 1.归并是先处理子问题在合并,快排是先分区,在处理子问题.
 * 2.归并和快排都是稳定排序算法(稳定算法:经某种算法,相同的两个数据顺序不发生改变.)
 *  归并是时间复杂度稳定为O(nlogn)的算法,
 *  快排时间虽然最坏情况下的时间复杂度是O(n2)[几率小,且可以通过选择分区来改变]，但是平均情况下时间复杂度都是O(nlogn),
 *  归并是非原地算法[空间复杂度为O(n)],而快排是原地算法[空间福再度为O(1)].
 *
 * @author xiaochuang.mao
 * @date 2021/6/8 15:31
 */
public class QuickSort {

    public static void quickSort(int[] arr,int length){
        quickSortInternally(arr,0,length - 1);
    }

    public static void quickSortInternally(int[] arr,int headIndex,int tailIndex){
        //递归必备:终止条件
        if (headIndex >= tailIndex){
            return;
        }
        //确定分区下标
        int partitionIndex = partition(arr, headIndex, tailIndex);
        quickSortInternally(arr, headIndex, partitionIndex - 1 );
        quickSortInternally(arr, partitionIndex + 1, tailIndex);
    }

    public static int partition(int[] arr,int headIndex,int tailIndex){
        //分区下标选定数组的末尾
        int partitionIndex =  arr[tailIndex];
        int i = headIndex;
        for (int j = headIndex; j < tailIndex ; j++) {
            if (arr[j] < partitionIndex){
                if (i == j){
                    i++;
                }else{
                    int temp = arr[i];
                    arr[i++] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        //这里需要把分区点的位置移动分割左右两边的数组 左小于 右大于
        int temp = arr[i];
        arr[i] = arr[tailIndex];
        arr[tailIndex] = temp;
        System.out.println("i="+i);
        return i;
    }

    public static void main(String[] args) {
        int[] arr  = {11,8,5,6,2,3,12,10,9,7,4,1};
        quickSort(arr,arr.length);
        System.out.println(Arrays.toString(arr));
    }

}
