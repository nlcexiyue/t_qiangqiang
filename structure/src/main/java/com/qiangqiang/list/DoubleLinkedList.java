package com.qiangqiang.list;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/29
 * \* Time: 17:20
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class DoubleLinkedList {

    public static void main(String[] args) {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add("11");
        doubleLinkedList.add("22");
        doubleLinkedList.add("33");
        doubleLinkedList.add("44");
//        doubleLinkedList.remove(3);
        doubleLinkedList.add(4,"55");
        for (int i = 0; i < doubleLinkedList.size; i++) {
            Object o = doubleLinkedList.get(i);
            System.out.println(o);
        }
        System.out.println();
        System.out.println();

    }

    /**
     * 保存双链表中的首节点
     */
    private Node headNode;

    /**
     * 保存双链表中的尾节点
     */
    private Node lastNode;

    /**
     * 保存双链表中的节点的个数
     */
    private int size;

    /**
     * 外界获取节点的个数
     */
    public int size() {
        return this.size;
    }


    /**
     * 根据序号插入元素
     */
    public void add(int index ,Object data){
        //1.判断序号是否合法
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("序号不合法");
        }
        Node node = new Node(data);
        //2.在首节点插入的情况
        if(index == 0){

            node.next = headNode;
            headNode.pre = node;
            headNode =  node;
        }

        //3.在末尾插入的情况
        else if(index == size){
            add(data);
            return;
        }



        //4.在0,size之间插入的情况
        else{
            //4.1获取要插入的节点的那个node
            Node curNode = node(index);
            //4.2获取要插入节点的前一个node
            Node preNode = curNode.pre;
            //4.3将前一个节点和新建节点连接
            preNode.next = node;
            node.pre = preNode;
            //4.4将新建节点与curNode连接
            node.next = curNode;
            curNode.pre = node;
        }
        size++;
    }


    /**
     * 根据索引删除
     */
    public void remove(int index) {
        //1.判断序号是否合法
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("序号不合法");
        }
        //2.处理删除节点为0的情况
        if(index == 0){
            //2.1获取headNode的下一个节点
            Node nextNode = headNode.next;
            //2.2设置headNode的next为null
            headNode.next = null;
            //2.3设置nextNode的pre设置为null
            if(nextNode != null){
                nextNode.pre = null;
            }
            //2.4将nextNode设置为headNode
            headNode = nextNode;
        }
        //3.处理删除节点为size情况
        else if (index == size -1){
            //3.1直接设置lastNode的上一个节点设置为lastNode
            lastNode = lastNode.pre;
            //3.2将新的lastNode的next设置为null
            lastNode.next = null;
        }
        //4.处理删除节点在0,size-1的范围内的情况
        else{
            //4.1.获取要删除的节点
            Node delNode = node(index);
            //4.2.获取要删除的节点的前一个节点
            Node preNode = delNode.pre;
            //4.3.获取删除节点的后一个节点
            Node nextNode = delNode.next;
            //4.4.设置删除节点的前一个节点的next指向后一个节点
            preNode.next = nextNode;
            //4.5.设置删除节点的后一个节点pre指向前一个节点
            nextNode.pre = preNode;
            //4.6.删除节点的首节点设置为null
            delNode.pre = null;
            //4.7.删除节点的尾节点设置为null
            delNode.next = null;
        }
        //5.更新size的值
        size--;
    }


    /**
     * 根据序号获取元素
     */
    public Object get(int index) {
        //1.判断序号的合法范围,0到size-1
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("序号不合法");
        }
        //2.获取index对应的node对象
        Node node = node(index);

        return node.data;
    }

    public Node node(int index) {
        //1.如果查找的节点在双链表的前半区,则从前向后查找
        if (index < size / 2) {
            //1.1定义个临时节点,用于存放遍历出来的节点
            Node nextNode = headNode;
            //1.2循环从前往后查找index对应的对象
            for (int i = 0; i < index; i++) {
                nextNode = nextNode.next;
            }
            return nextNode;
        }
        //2.如果查找的节点在后半区,则从后往前查找
        else {
            //2.1还是定义个临时节点
            Node preNode = lastNode;
            //2.2循环从后往前查找index对应的对象
            for (int i = size - 1; i > index; i--) {
                preNode = preNode.pre;
            }
            return preNode;
        }
    }


    /**
     * 添加元素
     */
    public void add(Object data) {
        //1.把需要添加的元素封装成node对象
        Node node = new Node(data);
        //2.处理双链表为空表的情况
        if (headNode == null) {
            //2.1把node节点设置为双联表的首节点
            headNode = node;
            //2.2把node节点设置为双链表的尾节点
            lastNode = node;
        }
        //3.处理双链表不是空表的情况
        else {
            //3.1这里是让尾节点指向node对象
            //这里是这样理解的:node虽然有三个字段,但是尾节点不管指向哪个字段,最终结果都是指向的node这个对象,所以可以理解为尾节点的next直接指向node就行
            lastNode.next = node;
            //3.2设置node的pre为lastNode
            node.pre = lastNode;
            //3.3设置node节点为尾节点
            lastNode = node;
        }
        //更新size的值
        size++;

    }


    private static class Node {
        /**
         * 保存节点中的数据
         */
        private Object data;
        /**
         * 保存上一个节点的地址值
         */
        private Node pre;
        /**
         * 保存下一个节点的地址值
         */
        private Node next;

        public Node(Object data) {
            this.data = data;
        }
    }


}