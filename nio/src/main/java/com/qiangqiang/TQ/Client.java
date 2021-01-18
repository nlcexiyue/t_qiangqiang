package com.qiangqiang.TQ;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 客户端
 */
public class Client {
    //定义客户端相关属性
    private Selector selector;
    private static final int port1 = 9999;
    private SocketChannel socketChannel;

    //初始化客户端信息

    public Client() {
        try {
            this.selector = Selector.open();
            this.socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",port1));
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
            System.out.println("客户端准备完成");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Client client = new Client();
        //定义一个线程专门监听服务端发送过来的线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                client.readInfo();

            }
        }).start();
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.print("波妞:");
            String s = scanner.nextLine();

            client.sendToServer(s);
        }


    }

    public  void sendToServer(String s) {
        try {
            socketChannel.write(ByteBuffer.wrap(("波妞说"+s).getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void readInfo() {

            try{
                while(selector.select() > 0){
                    //获取选择键
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while(iterator.hasNext()){
                        SelectionKey selectionKey = iterator.next();
                        if(selectionKey.isReadable()){
                            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                            socketChannel.read(byteBuffer);
                            System.out.println(new String(byteBuffer.array()).trim());

                        }

                        iterator.remove();
                    }

                }
            }catch(Exception e){
                e.printStackTrace();
            }


    }


}
