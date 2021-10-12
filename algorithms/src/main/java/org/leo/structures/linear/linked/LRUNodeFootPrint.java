package org.leo.structures.linear.linked;

import java.util.Objects;
import java.util.Scanner;

/**
 * 模块名称:LRU算法链表实现
 * 思路: 查询的数据插入链表头部,淘汰链表队尾的数据
 *      查询: 1.数据存在,遍历查询该节点并设置为头节点
 *           2. 数据不存在: 2.1:链表空间未满,插入头结点
 *                        2.2: 链表空间已满,删除尾节点,并将数据插入到头结点
 * @author xiaochuang.mao
 * @date 2021/5/31 20:09
 */
public class LRUNodeFootPrint<T> {
    /**
     * 默认表容量
     */
    private static final Integer  DEFAULT_CAPACITY = 10;

    /**
     * 表容量
     */
    private Integer capacity;

    /**
     * 头结点
     */
    private Node<T> head;

    /**
     * 链表长度
     */
    private Integer length;

    public LRUNodeFootPrint() {
        this.capacity = DEFAULT_CAPACITY;
        head = new Node<T>();
        this.length = 0;
    }

    public LRUNodeFootPrint(Integer capacity) {
        this.capacity = capacity;
        this.head = new Node<T>();
        this.length = 0;
    }

    /**
     * 获取节点
     * @param data 当前节点数据
     * @return org.leo.structures.linear.linked.LRUNodeFootPrint.Node<T>
     */
    public Node<T> getNode(T data){
        Node<T> temp = head ;
        while (temp.getNext() != null ){
            if (data.equals(temp.getNext().getElement())){
                return temp;
            }
            temp = temp.getNext();
        }
        return null;
    }

    public void add(T data){
        Node<T> preNode = getNode(data);
        //存在链表中
        if ( preNode != null ){
            //设置当前节点为头结点
            removeNextNode(preNode);
            addHeadNode(data);
        } else {
            //链表长度已满
            if (this.length >= this.capacity){
                removeTailNode();
                --length;
            }else{//链表长度未满,插入到头结点
                addHeadNode(data);
            }
        }
    }

    /**
     * 删除当前节点的下节点
     * @param preNode 当前节点
     */
    public void removeNextNode(Node<T> preNode){
        Node<T> temp = preNode.getNext();
        preNode.setNext(temp.getNext());
        temp = null;
        length--;
    }

    /**
     * 删除尾节点
     */
    public void removeTailNode(){
        Node<T> temp = head;
        //空链表
        if (temp.next == null){
            return;
        }
        //倒数第二个
        while(temp.next.next != null){
            temp = temp.next;
        }
        temp.setNext(null);
        temp.next.setElement(null);
        temp.next = null;
    }

    public void addHeadNode(T data){
        Node<T> next = head.getNext();
        head.setNext(new Node<T>(data,next));
        ++length;
    }

    private void printAll() {
        Node<T> node = head.getNext();
        while (node != null) {
            System.out.print(node.getElement() + ",");
            node = node.getNext();
        }
        System.out.println();
    }

    public static class Node<T> {

        private T element;

        private Node<T> next;

        public Node(T element) {
            this.element = element;
        }

        public Node(T element, Node<T> next) {
            this.element = element;
            this.next = next;
        }

        public Node() {
            this.next = null;
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LRUNodeFootPrint<String> node = new LRUNodeFootPrint<>(3);
        try( Scanner scanner = new Scanner(System.in);){
            while (true) {
                node.add(scanner.nextLine());
                node.printAll();
            }
        }catch(Exception e){
         e.printStackTrace();
        }
        System.out.println("test".equals(args[0]));
        System.out.println(Objects.equals("test",args[0]));

    }
}
