package com.qiangqiang.stack;

public class Stack<T> {
    //采用链表来实现
    //记录首节点
    private Node node;
    private int size;

    /**
     * 保存单链表中的首节点
     */
    private Node headNode;

    /**
     * 保存单链表中的尾节点
     */
    private Node lastNode;

    //构造方法
    public Stack() {
        this.node = new Node(null, null);
        this.size = 0;
    }

    //判断当前栈中元素个数是否为0
    public boolean isEmpty() {
        return size == 0;
    }


    //获取栈中元素的个数
    public int size() {
        return this.size;
    }

    //压栈    //放在单链表的首节点
    public void push(T t) {
        //1.把需要添加的数封装成节点对象
        Node node = new Node(t, null);
        //2.处理单链表为空的情况
        if (headNode == null) {
            //2.1把node节点设置为单链表的首节点
            headNode = node;
            //2.2把node节点设置为单链表的尾节点
            lastNode = node;
        }
        //3.处理单链表不是空表的情况
        else {
            node.next = headNode;
            headNode = node;

        }
        //4.更新size的值
        size++;
    }


    //弹栈
    public T pop() {
        T item = headNode.item;
        headNode = headNode.next;
        size--;
        return item;
    }


    private class Node {
        private T item;
        private Node next;

        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push("11");
        stack.push("22");

        System.out.println();
        stack.pop();
        System.out.println();
    }
}
