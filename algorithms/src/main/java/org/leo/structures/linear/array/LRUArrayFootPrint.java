package org.leo.structures.linear.array;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 模块名称:LRU基于数组实现
 * 思路: 查询的数据插入数组头部,淘汰数组队尾的数据
 *      查询: 1.数据存在,遍历查询删除该节点并设置为队首
 *           2. 数据不存在: 2.1:数组空间未满,插入队首
 *                        2.2: 数组空间已满,删除队尾,并将数据插入到队首
 * @author xiaochuang.mao
 * @date 2021/6/1 19:18
 */
public class LRUArrayFootPrint<T> {
    /**
     * 默认表容量
     */
    private static final Integer  DEFAULT_CAPACITY = 10;
    /**
     * 容器
     */
    private T[] ele;
    /**
     * 容量
     */
    private Integer capacity;
    /**
     * 缓存map
     */
    private Map<T,Integer> holder;
    /**
     * 容器元素计数
     */
    private Integer count;

    public LRUArrayFootPrint() {
        this(DEFAULT_CAPACITY);
    }
    public LRUArrayFootPrint(Integer capacity){
        this.capacity = capacity;
        this.ele = (T[]) new Object[capacity];
        this.holder = new HashMap<T,Integer>(capacity);
        this.count = 0;
    }

    public void add(T data){
        if (data == null){
            throw new RuntimeException("参数异常！");
        }
        Integer index = holder.get(data);
        if (index == null){
                if (isFull()){
                    removeAndCache(data);
                }else{
                    cache(data,count);
                }
        }else{
            update(index);
        }

    }

    public void update(Integer index){
        T temp = ele[index];
        rightShift(index);
        ele[0] = temp;
        holder.put(temp,0);
    }

    /**
     * 缓存满的情况，踢出后，再缓存到数组头部
     * @param object 数据
     */
    public void removeAndCache(T object) {
        T key = ele[--count];
        holder.remove(key);
        cache(object, ele.length);
    }

    /**
     * 缓存数据到头部，但要先右移
     * @param object 数据
     * @param end 数组右移的边界
     */
    public void cache(T object, int end) {
        rightShift(end);
        ele[0] = object;
        holder.put(object, 0);
        count++;
    }

    /**
     * end左边的数据统一右移一位
     * @param end
     */
    private void rightShift(int end) {
        for (int i = end - 1; i >= 0; i--) {
            ele[i + 1] = ele[i];
            holder.put(ele[i], i + 1);
        }
    }

    public boolean isFull(){
        return this.count.equals(capacity);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ele.length; i++) {
            if (ele[i] != null) {
                sb.append(ele[i]);
                sb.append(" ");
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        LRUArrayFootPrint<String> lruArrayFootPrint = new LRUArrayFootPrint<>();
        Scanner sc = new Scanner(System.in);
        while (true){
            lruArrayFootPrint.add(sc.nextLine());
            System.out.println(lruArrayFootPrint.toString());
        }
    }
}
