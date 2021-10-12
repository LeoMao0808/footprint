package org.leo.structures.linear.queue;

import org.leo.structures.linear.stack.LinkedStackFootPrint;

/**
 * 模块名称:队列（先进先出）
 * 最基本的操作也是两个：
 *  入队enqueue()，放一个数据到队列尾部.
 *  出队dequeue()，从队列头部取一个元素.
 *
 * @author xiaochuang.mao
 * @date 2021/5/25 20:00
 */
public class QueueFootPrint {
}
/**
 * 顺序队列(基于数组实现)
 */
class ArrayQueue{
    /**元素数组*/
    private String[] ele;
    /**大小*/
    private int size;
    /**队头下标*/
    private int head;
    /**队尾下标*/
    private int tail;
    ArrayQueue(int capacity){
        ele = new String[capacity];
        size = capacity;
        head = 0;
        tail = 0;
    }
    /**
     *入队
     * @param data 数据
     * @return boolean
     */
    public boolean enqueue(String data){
        //size == tail 标识队列已满
        if (tail == size){
            return false;
        }
        ele[tail] = data;
        tail++;
        return true;
    }

    public boolean enqueueBetter(String data){
        //队尾无空间
        if (tail == size){
            // 队列已满
            if (head == 0) {
                return false;
            }
            //数据搬移head=0,tail = tail-head;
            for (int i = head;i < tail; ++i){
                ele[i - head] = ele[i];
            }
            head = 0;
            tail -= head;
        }
        ele[tail] = data;
        tail++;
        return true;
    }

    /**
     * 出队
     * @return java.lang.String
     */
    public String dequeue() {
        //head == tail 标识队列为空
        if(head == tail ){
            return null;
        }
        String val = ele[head];
        head++;
        return val;
    }
}
class LinkedQueue{
    /**
     * head:队头 tail:队尾
     */
    private Node head = null;
    private Node tail = null;

    /**
     *
     * 入队
     * @param data 数据
     * @return boolean
     */
    private boolean enqueue(int data){
        if (tail == null){
            Node node = new Node(data, null);
            tail = node;
        }else {
            tail.next = new Node(data,null);
            tail = tail.next;
        }
        return true;
    }

    public int dequeue(){
        if (head == null){
            return  -1;
        }
        int val = head.data;
        head = head.next;
        return val;
    }


    static class Node{
        private int data;
        private Node next;
        Node(int data, Node next){
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }

        public Node getNext() {
            return next;
        }
    }
}
