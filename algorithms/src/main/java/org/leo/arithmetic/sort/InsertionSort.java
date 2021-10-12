package org.leo.arithmetic.sort;

/**
 * 模块名称:插入排序(Insertion Sort)
 * 时间复杂度：O(n²)
 * 算法执行过程:将数组中的数据分为两个区间，已排序区间和未排序区间。
 * 插入算法的核心思想是取未排序区间中的元素，在已排序区间中找到合适的插入位置将其插入，
 * 并保证已排序区间数据一直有序。重复这个过程，直到未排序区间中元素为空，算法结束
 *
 * @author xiaochuang.mao
 * @date 2021/5/28 21:12
 */
public class InsertionSort {
    static int[] arrs = {4,5,6,3,2,1};

    public static void sort(int[] arrs,int size){
        if (size <= 1) {
            return;
        }
        for (int i = 1; i < size ; i++) {
            int val = arrs[i];
            int j = i -1;
            for (;j >= 0;--j){
                if(arrs[j] > val){
                    arrs[j+1] =arrs[j];
                }else {
                    break;
                }
            }
            arrs[j+1] = val;
        }

        for (int arr : arrs) {
            System.out.print(arr+" ");
        }

    }

    public static void main(String[] args) {
        sort(arrs, arrs.length);
    }
}
