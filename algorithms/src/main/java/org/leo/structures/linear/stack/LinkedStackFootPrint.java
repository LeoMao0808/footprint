package org.leo.structures.linear.stack;

/**
 * 模块名称:链式栈（基于链表实现）
 *
 * @author xiaochuang.mao
 * @date 2021/5/25 20:09
 */
public class LinkedStackFootPrint {
    private Node top;
    /**
     *入栈
     * @param val data
     */
    public void push(int val){
        Node node = new Node(val, null);
        if (top == null){
            top = node;
        }else{
            node.next = top;
            top = node;
        }
    }
    /**
     * 出栈
     * @return int
     */
    public int pop(){
        if (top == null ){
            //栈为空
            return -1;
        }
        int value = top.data;
        top = top.next;
        return value;
    }

    public void printAll() {
        Node p = top;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }



    static class Node{
        private int data;
        private Node next;
        Node(int data,Node next){
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
