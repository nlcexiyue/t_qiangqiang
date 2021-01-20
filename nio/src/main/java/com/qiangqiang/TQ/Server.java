package com.qiangqiang.TQ;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * 服务器
 */
public class Server {
    //定义成员属性
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private static final int port = 9999;

    //构造方法
    public Server() {
        try {
            this.selector = Selector.open();
            this.serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(port));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 接收当客户端的信息,转发给其他客户端通道
     */
    //SelectorKey中的read方法中,要有义务的逻辑处理
    public void readClientData(SelectionKey selectionKey){
        SocketChannel socketChannel = null;
        try{
            //可以反向代理的获取连接到服务端的客户端通道
            socketChannel = (SocketChannel) selectionKey.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int len = 0;
            while ((len = socketChannel.read(byteBuffer)) > 0) {
                byteBuffer.flip();


                String msg = new String(byteBuffer.array(), 0, byteBuffer.remaining());
                System.out.println("接收到了信息:" +msg);
                
                //把这个消息推送给全部客户端接收
                //把自己的通道也传入参数,是要确认是否要发回给自己,或者是不是要排除自己
                sendMsgToAllClient(msg,socketChannel);
                

            }
            
            
            
        }catch(Exception e){
            try {
                System.out.println("有人下线了"+socketChannel.getRemoteAddress());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            //如果客户端是离线状态,是读不出来数据的,抛异常
            //这里就可以把这个事件取消掉
            selectionKey.cancel();
            //关闭通道
            try {
                socketChannel.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        
        
    }
    
    //把这个消息推送给全部客户端channel接收
    public void sendMsgToAllClient(String msg, SocketChannel socketChannel) {
        System.out.println("服务端开始转发" +Thread.currentThread().getName());
        for (SelectionKey key : selector.keys()) {
            Channel channel = key.channel();
            if(channel instanceof SocketChannel && channel != socketChannel){
                //将信息封装到缓冲区
                ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
                try {
                    ((SocketChannel)channel).write(byteBuffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        
        
    }

    
    

    //将原来的代码逻辑中的listen监听接收的功能抽取出来
    //就是while(selector.select() > 0)中的代码
    public void listen(){
        try{
            while(selector.select() > 0){
                //获取选择键
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while(iterator.hasNext()){
                    SelectionKey selectionKey = iterator.next();
                    if(selectionKey.isAcceptable()){
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector,SelectionKey.OP_READ);


                    }else if(selectionKey.isReadable()){
                        readClientData(selectionKey);
                    }

                    iterator.remove();
                }

            }
        }catch(Exception e){
            e.printStackTrace();
        }



    }



    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.listen();

    }


}
