package org.leo.structures.linear.linked;

import jdk.nashorn.internal.objects.annotations.Where;

import java.util.Objects;

/**
 * 模块名称:链表
 * 问题: 如何使用链表实现LRU淘汰算法?
 * 思路：
 * 维护一个有序单链表，越靠近链表尾部的结点是越早之前访问的。
 * 当有一个新的数据被访问时，从链表头开始顺序遍历链表。
 * 1.如果此数据之前已经被缓存在链表中，遍历得到数据对应的结点，并将其删除，然后再插入到链表的头部。
 * 2.如果此数据没有在缓存链表中，又可以分为两种情况：
 *  2.1如果此时缓存未满，则将此结点直接插入到链表的头部；
 *  2.2如果此时缓存已满，则链表尾结点删除，将新的数据结点插入链表的头部。
 * 这种基于链表的实现思路，缓存访问的时间复杂度为O(n)。
 *
 * 链表（Linked List）:线性表数据结构,通过“指针”将一组零散的内存块串联起来使用.
 * 单链表:每个链表的结点存储数据和记录链上的下一个结点的地址(next),队尾的next=null.
 *   单纯的插入:前节点的next指向插入元素和插入元素的next指向前节点next.(O(1))
 *   单纯的删除:前节点的next指向删除节点的next.(O(1))
 *   查询: 最好情况o(1),最坏情况O(n)所以需要O(n)
 *  循环链表: 结构与单链表相似,不过不同的是尾节点next需要指向头节点.
 *  双向链表: 结构与单链表相似，不过需要多存储前节点地址（prev）。(空间换时间的思想)
 *  删除操作：
 *  1.删除节点中值等于某个给定值的节点:
 *      单链表和双向链表都需要从头节点遍历直到找到值等于给定值的节点然后执行删除操作,查询操作
 *      的时间复杂度为O(n),删除操作为(O(1)),所以总时间复杂度为O(n).
 *  2.删除给定指针指向的节点:
 *       已知删除的节点,需要修改前节点的next指向删除的节点的next,单链表需要从头结点遍历
 *       直到找到节点的next指向删节点,修改前节点的next,所以时间复杂度O(n),
 *       双向链表记录前节点只需要修改前节点的next,所以时间复杂度为O(1).
 *
 *  数组和链表对比:
 *  时间复杂度  链表    数组
 *  插入/删除   O(1)   O(n)
 *  随机查询    O(n)   O(1)
 *  数组:需要连续内存空间,可以借助CPU的缓存机制，预读数组中的数据，所以访问效率更高；大小固定,
 *  扩容需要更大的内存空间把原数组拷贝进去,性能较差。
 *  链表:内存不连续存储，所以对CPU缓存不友好，没办法有效预读；大小只受内存限制，天然支持动态扩容。
 * @author xiaochuang.mao
 * @date 2021/5/24 21:05
 */
public class DoubleLinkedListFootPrint<T> {
    Node node;
    /** head:头结点*/
    private static Node head;
    /** tail:尾节点*/
    private static Node tail;
    /**长度**/
    public static int length;
    /**temp:临时链表*/
    private Node<T> temp;

   static final class Node<T> {
       //data:数据
       private T data;
       //prev:前一节点
       private Node<T> prev;
       //next:后一节点
       private Node<T> next;
       Node(){}
       Node(T data, Node<T> prev, Node<T> next){
        this.data = data;
        this.prev = prev;
        this.next = next;
       }

       @Override
       public boolean equals(Object o) {
           if (this == o) {
               return true;
           }
           if (o == null || getClass() != o.getClass()) {
               return false;
           }
           Node<?> node = (Node<?>) o;
           return Objects.equals(data, node.data) && Objects.equals(prev, node.prev) && Objects.equals(next, node.next);
       }

       @Override
       public int hashCode() {
           return Objects.hash(data, prev, next);
       }
   }
    public DoubleLinkedListFootPrint(){
       head = new Node<T>(null,null,null);
       tail = head;
    }

    public DoubleLinkedListFootPrint(T data){
        head = new Node<T>(data,null,null);
        tail = head;
    }
    /**
     * 长度
     * @return int
     */
    public static int length(){
       return length;
    }
    /**
     * 是否为空
     * @return boolean
     */
    public boolean isEmpty(){
       return length == 0;
    }

    /**
     * 添加到队尾
     * @param data 数据
     */
    public void addTail(T data){
       if (isEmpty()){
           head = new Node<>(data,null,null);
           tail = head;
       } else {
            temp = new Node<>(data,tail,null);
           tail = temp;
       }
       length++;
    }

    /**
     * 添加指定节点后
     * @param data 数据
     * @param node 已有节点
     */
    public void addNodeAfter(T data,Node node){
        temp = new Node<>(data,node,node.next);
        node.next = temp;
        length++;
    }
    /**
     *添加指定节点前
     * @param data 数据
     * @param node 已有节点
     */
    public void addNodeBefore(T data,Node node){
        temp = new Node<>(data,node.prev,node);
        node.prev = temp;
        length++;
    }

    /**
     * 删除指定数据的节点
     * @param data 数据
     */
    public void removeData(T data){
        if (isEmpty() ) {
            throw new IndexOutOfBoundsException("LinkedList is Empty");
        }
        Node removeNode = getNode(data);
        if (removeNode != null){
            removeNode(removeNode);
        }
    }

    /**
     * 删除指定节点
     * @param node 指定节点
     */
    public void removeNode(Node node){
        if (isEmpty() ) {
            throw new IndexOutOfBoundsException("LinkedList is Empty");
        }
        if (isHead(node)){
            head = head.next;
        }
        if (isTail(node)){
            tail = tail.prev;
        }
         node.prev = node.next;
         node.next.prev = node.prev;
         node.prev = null;
         node.next = null;
         length--;
    }
    /**
     * 根据下标删除节点
     * @param index 下标
     */
    public void removeIndex(int index){
        if (isEmpty() ) {
            throw new IndexOutOfBoundsException("LinkedList is Empty");
        }
        Node removeNode = getNode(index);
        removeNode(removeNode);
    }
    /**
     * 根据下标获取节点
     * @param index 下标
     * @return org.leo.structures.linear.LinkedListFootPrint.Node
     */
    public Node getNode(int index){
        int count = 0;
        if(index > length){
            throw new IndexOutOfBoundsException("index is out of Node");
        }
        for(node = head;node != null;node = node.next){
           if (index == count){
               return node;
           }
           count++;
        }
        return null;
    }
    /**
     * 根据数据获取节点
     * @param data 数据
     * @return org.leo.structures.linear.LinkedListFootPrint.Node
     */
    public Node getNode (T data){
        Node<T> node = new Node<T>();
        for(node = head;node != null;node = node.next){
            if (node.data.equals(data)){
                return node;
            }
        }
        return null;
    }

    public static boolean isHead(Node node){
        return head.equals(node);
    }

    public static boolean isTail(Node node) {
        return tail.equals(node);
    }
}
