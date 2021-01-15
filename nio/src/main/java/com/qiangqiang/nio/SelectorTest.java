package com.qiangqiang.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class SelectorTest {


    //创建Selector,通过调用Selector.open()方法创建一个Selector
    @Test
    public void test01() throws Exception {

        //1.获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //2.切换非阻塞模式
        serverSocketChannel.configureBlocking(false);
        //3.绑定连接
        serverSocketChannel.bind(new InetSocketAddress(9000));
        //4.获取选择器
        Selector selector = Selector.open();
        //5.将通道注册到选择器,并且指定"监听接收事件"
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //6.轮询式的获取选择器上已经准备就绪的事件
        while(selector.select() > 0){
            System.out.println("轮一轮");
            //7.获取当前选择器中所有注册的选择键(已注册的监听事件)
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while(iterator.hasNext()){
                //8.获取准备就绪的事件
                SelectionKey selectionKey = iterator.next();
                //9.判断具体是什么事件准备就绪,此处是判断是否为接收事件,可看源码
                if(selectionKey.isAcceptable()){
                    //10.若为接收就绪,获取客户端连接通道
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //11.切换非阻塞模式
                    socketChannel.configureBlocking(false);
                    //12.将通道注册到选择器中,选择器要监听它自己要读数据
                    socketChannel.register(selector,SelectionKey.OP_READ);
                }else if(selectionKey.isReadable()){
                    //13.获取当前选择器上的读就绪的状态的通道
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    //14.读取数据
                    int len = 0;
                    while((len = channel.read(byteBuffer))> 0){
                        byteBuffer.flip();
                        System.out.println(new String(byteBuffer.array(),0,len));
                        byteBuffer.clear();
                    }


                }
                //15.上述操作完后,要移除选择键,因为这个事件已经执行完了
                iterator.remove();

            }


        }




    }
}
