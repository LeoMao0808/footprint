package org.leo.arithmetic.sort;

/**
 * 模块名称:冒泡排序（Bubble Sort）
 * 时间复杂度：O(n²)
 * 算法执行过程:冒泡排序只会操作相邻的两个数据。每次冒泡操作都会对相邻的两个元素进行比较，
 * 看是否满足大小关系要求。如果不满足就让它俩互换。一次冒泡会让至少一个元素移动到它应该在的位置，
 * 重复n次，就完成了n个数据的排序工作。
 *
 * ques: 一组数据4，5，6，3，2，1，从小到大进行排序.
 *
 * @author xiaochuang.mao
 * @date 2021/5/28 20:06
 */
public class BubbleSort {
    static int[] arrs = {4,5,6,3,2,1};

    public static void sort(int[] arrs,int size){
        if (size <= 1){
            return;
        }
        for (int i = 0; i <  size; ++i) {
            // 提前退出冒泡循环的标志位
            boolean flag = false;
            for (int j = 0; j < size - i -1; ++j) {
                if (arrs[j] > arrs[j+1]){
                    int temp = arrs[j];
                    arrs[j] = arrs[j+1];
                    arrs[j+1] = temp;
                    flag = true;
                }
            }
            if(!flag){
                break;
            }
        }
        for (int arr : arrs) {
            System.out.print(arr+" ");
        }
    }

    public static void main(String[] args) {
        sort(arrs,arrs.length);
    }
}
