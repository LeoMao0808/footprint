package org.leo.arithmetic.sort;

import java.util.Arrays;

/**
 * 模块名称:归并排序(Merge Sort)
 * 时间复杂度：O(nlogn)
 * 核心思想：分治思想，将一个大问题分解成小的子问题来解决。小的子问题解决了，
 *          大问题也就解决了。
 * 实现方式:递归
 *  递归公式: mergesort(head...tail) = merge(mergesort(head...p) + mergesort(p+1...tail)
 *  终止条件: head >= tail
 * @author xiaochuang.mao
 * @date 2021/6/2 18:57
 */
public class MergeSort {

    /**
     * 归并排序
     * @param arr 需要排序的数组
     * @param length 数组长度
     */
    public static void mergeSort(int[] arr,int length){
        mergeSortInternally(arr,0,length -1);
    }
    /**
     * 归并递归方法
     * @param arr 需排序数组
     * @param headIndex 首元素下标
     * @param tailIndex 未元素下标
     */
    public static void mergeSortInternally(int[] arr,int headIndex,int tailIndex){
        //终止条件 首下标 >= 末下标
        if (headIndex >= tailIndex){
            return;
        }
        //获取首末中间下标 防止 head + tail 超过 int的范围
        int midIndex = headIndex + (tailIndex - headIndex) / 2 ;
        //将数组分成小数组(分而治之)
        System.out.println("head:"+headIndex +",midIndex:"+midIndex +", tailIndex:" + tailIndex);
        mergeSortInternally(arr,headIndex,midIndex);
        mergeSortInternally(arr,midIndex + 1 ,tailIndex);
        //将分成的小数组arr[h...m]和arr[m+1...t] 排序并合并成arr[h,r]
        System.out.println("head1:"+headIndex +",midIndex1:"+midIndex +", tailIndex1:" + tailIndex);
        merge(arr,headIndex,midIndex,tailIndex);
    }

    public static void merge(int[] arr,int headIndex,int midIndex,int tailIndex){
        int i = headIndex;
        int j = midIndex + 1;
        int k = 0;
        //申请一个大小跟arr[h...t]一样的数组
        int[] temp = new int[ tailIndex - headIndex + 1 ];
        while (i <= midIndex && j <= tailIndex){
            if (arr[i] <= arr[j]){
                temp[k++] = arr[i++];
            }else{
                temp[k++] = arr[j++];
            }
        }
        //判断数组中剩余的数据
        int start = i;
        int end = midIndex;
        if (j <= tailIndex){
            start = j;
            end = tailIndex;
        }
        //将剩余数据拷贝到temp
        while (start <= end){
            temp[k++] = arr[start++];
        }
        // 将tmp中的数组拷贝回a[p...r]
        for (i = 0; i <= tailIndex - headIndex; ++i) {
            arr[headIndex + i] = temp[i];
        }
    }
    /**
     * 哨兵优化合并
     * @param arr 需要排序的数组
     * @param headIndex 首下标
     * @param midIndex 中间下标
     * @param tailIndex 末下标
     */
    private static void mergeBySentry(int[] arr, int headIndex, int midIndex, int tailIndex) {
        int[] leftArr = new int[midIndex - headIndex + 2];
        int[] rightArr = new int[ tailIndex - midIndex + 1];

        //数组分装到left
        for (int i = 0; i <= midIndex - headIndex ; i++) {
            leftArr[i] = arr[headIndex + i];
        }
        // 第一个数组添加哨兵（最大值）
        leftArr[midIndex - headIndex + 1] = Integer.MAX_VALUE;
        //数组分装到right
        for (int i = 0; i <= tailIndex - midIndex ; i++) {
            rightArr[i] = arr[midIndex + 1 + i];
        }
        // 第一个数组添加哨兵（最大值）
        rightArr[tailIndex - midIndex] = Integer.MAX_VALUE;

        int i = 0;
        int j = 0;
        int k = headIndex;
        while (k <= tailIndex) {
            // 当左边数组到达哨兵值时，i不再增加，直到右边数组读取完剩余值，同理右边数组也一样
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }
    }

    public static void main(String[] args) {
        int[] arr  = {11,8,5,6,2,3,12,10,9,7,4,1};
        mergeSort(arr,arr.length);
        System.out.println(Arrays.toString(arr));
    }
}
