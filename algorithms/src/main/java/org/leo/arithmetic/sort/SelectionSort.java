package org.leo.arithmetic.sort;

/**
 * 模块名称:选择排序(Selection Sort)
 * 时间复杂度：O(n²)
 * 算法执行过程:选择排序算法的实现思路有点类似插入排序，也分已排序区间和未排序区间。
 * 但是选择排序每次会从未排序区间中找到最小的元素，将其放到已排序区间的末尾。
 * @author xiaochuang.mao
 * @date 2021/5/28 21:52
 */
public class SelectionSort {
    static int[] arrs = {4,5,6,3,2,1};

    public static void sort(int[] arrs,int size) {
        if (size <= 1) {
            return;
        }
        for (int i = 0; i < size -1; i++) {
            int min = i;
            //从第2个元素开始比较,取最小值的下标
            for (int j = i + 1; j < size ; j++) {
                if(arrs[j] < arrs[min]){
                    min = j;
                }
            }
            //交换最小值到i的位置
            int temp = arrs[i];
            arrs[i] = arrs[min];
            arrs[min] = temp;
        }
        for (int arr : arrs) {
            System.out.print(arr+" ");
        }
    }
    public static void main(String[] args) {
        sort(arrs, arrs.length);
    }
}
