package com.qiangqiang.list;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/29
 * \* Time: 15:19
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class SingleLinkedList {

    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add("11");
        singleLinkedList.add("22");
        singleLinkedList.add("33");
        singleLinkedList.add("44");

        singleLinkedList.add(0,"55555");
//        for (int i = 0; i < singleLinkedList.size; i++) {
//            System.out.println(singleLinkedList.get(i));
//        }
        System.out.println();



    }













    /**
     * 保存单链表中的首节点
     */
    private Node headNode;

    /**
     * 保存单链表中的尾节点
     */
    private Node lastNode;

    /**
     * 保存单链表中节点的个数
     */
    private int size;


    /**
     * 单链表的插入操作
     */
    public void add(int index ,Object element){
        //1.判断序号是否合法
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("索引越界");
        }
        //2.将需要插入的对象封装为Node节点对象
        Node node = new Node(element);
        //3.首节点插入的情况
        if(index == 0){
            //3.0将首节点设置为node的next
            node.next = headNode;
            //3.1将node设置为首节点
            headNode = node;
        }

        //4.在尾节点插入的情况
        if(index == size){
            //4.1lastNode的next设置为插入节点
            lastNode.next = node;
            //4.3将插入节点设置为lastNode
            lastNode = node;
        }

        //5.在首节点和尾节点之间插入的情况
        else {
            //5.1.获取插入对象的前一个节点
            Node preNode = node(index - 1);
            //5.2获取preNode的后一个节点
            Node nextNode = preNode.next;
            //5.3.将前一个节点的next设置为插入对象
            preNode.next = node;
            //5.4.将插入节点的next设置为nextNode
            node.next = nextNode;
        }

        size++;

    }




    /**
     * 根据序号删除元素
     */
    public void remove(int index){
        //1.判断序号是否合法
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("索引越界");
        }
        //2.处理删除节点为首节点的情况
        if(index == 0){
            //2.1获得删除节点的后一个节点
            Node nextNode = headNode.next;
            //2.2设置next为null
            headNode.next = null;
            //2.3设置nextNode为首节点
            headNode = nextNode;
        }
        //3.处理删除节点为尾节点的情况
        else if(index == size- 1){
            //3.1获取删除节点的前一个节点
            Node preNode = node(index - 1);
            //3.2设置next为null
            preNode.next = null;
            //3.3设置单链表的尾节点
            lastNode = preNode;
        }
        //4.处理删除节点在中间的情况
        else{
            //4.1.获得index-1对应的节点对象
            Node preNode = node(index - 1);
            //4.2.获得index+1对应的节点对象
            Node nextNode = preNode.next.next;
            //4.3.获得删除节点并设置next为nill
            preNode.next.next = null;
            //4.4.设置preNode的next值为nextNode
            preNode.next = nextNode;

        }

        //5.更新size 的值
        size--;


    }



    /**
     * 获取元素的操作
     */
    public Object get(int index) {
        //1.判断序号是否合法
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("索引越界");
        }
        //2.根据序号获取对应的节点对象
        Node node = node(index);
        Object data = node.data;
        return data;
    }

    /**
     * 根据序号获得对应的节点对象
     *
     * @param index 序号
     * @return 序号对应的节点对象
     */
    private Node node(int index) {
        //1.定义一个临时节点来保存每一步遍历操作得到的节点
        Node tempNode = headNode;
        //2.循环获取index对应的节点对象
        for (int i = 0; i < index; i++) {
            //一层一层获取,赋值
            tempNode = tempNode.next;
        }
        //3.返回
        return tempNode;


    }

    /**
     * 添加元素
     */
    public void add(Object element) {
        //1.把需要添加的数封装成节点对象
        Node node = new Node(element);
        //2.处理单链表为空的情况
        if (headNode == null) {
            //2.1把node节点设置为单链表的首节点
            headNode = node;
            //2.2把node节点设置为单链表的尾节点
            lastNode = node;
        }
        //3.处理单链表不是空表的情况
        else {
            //3.1让lastNode指向node节点
            lastNode.next = node;
            //3.2更新lastNode的值
            lastNode = node;
        }
        //4.更新size的值
        size++;
    }


    /**
     * 公共的方法,用于外界获取单链表的长度
     *
     * @return
     */
    public int size() {
        return this.size;
    }

    /**
     * 节点类
     */
    private static class Node {
        /**
         * 保存节点中的数据
         */
        private Object data;

        /**
         * 保存指向下一个节点的地址值
         */
        private Node next;

        /**
         * 生成的构造方法
         *
         * @param data
         */
        public Node(Object data) {
            this.data = data;
        }
    }


}