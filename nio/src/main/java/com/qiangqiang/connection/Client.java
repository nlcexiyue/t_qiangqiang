package com.qiangqiang.connection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * 基于NIO的客户端
 */
public class Client {
    public static void main(String[] args) throws Exception {
        //1.获取通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9999));
        //2.切换成非阻塞模式
        socketChannel.configureBlocking(false);
        //3.分配缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //4.发送数据给服务端
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.print("请说:");
            String nextLine = scanner.nextLine();
            byteBuffer.put(("波妞:"+nextLine).getBytes());
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            byteBuffer.clear();
        }


    }

}
