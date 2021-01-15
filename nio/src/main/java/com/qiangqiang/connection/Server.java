package com.qiangqiang.connection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * NIO非阻塞通信下的服务端
 */
public class Server {
    public static void main(String[] args) throws Exception {
        //1.获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //2.切换为非阻塞模式
        serverSocketChannel.configureBlocking(false);
        //3.绑定连接的端口
        serverSocketChannel.bind(new InetSocketAddress(9999));
        //4.获取选择器Selector
        Selector selector = Selector.open();
        //5.将通道都注册到选择器上去,,并且开始监听事件,事件都在选择键中
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //6.使用Selector选择器轮询已经就绪好的事件
        while (selector.select() > 0) {
            System.out.println("轮一轮");
            //7.获取选择器中所有注册的通道中已经就绪好的事件
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                //8.提取当前事件
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()) {
                    //10.直接获取当前接入的客户端通道,获取当前接入的客户端通道
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //11.拿到通道就要切换为非阻塞模式,不然使用NIO的意义何在?
                    socketChannel.configureBlocking(false);
                    //12.将客户端通道注册到选择器上
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

                //上面处理完一个事件就要在循环中移除掉这个事件
                iterator.remove();

            }


        }


    }


}
