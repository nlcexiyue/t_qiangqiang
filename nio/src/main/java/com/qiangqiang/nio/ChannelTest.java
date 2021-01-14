package com.qiangqiang.nio;


import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *  通道的测试类
 */
public class ChannelTest {
    @Test
    public void copy() throws Exception {
        FileInputStream fileInputStream = new FileInputStream("data.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("data1.txt");
        //1.构建通道
        FileChannel fileInputStreamChannel = fileInputStream.getChannel();
        FileChannel fileOutputStreamChannel = fileOutputStream.getChannel();

        //2.配置缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while(true){
            //每次先清空缓冲区
            byteBuffer.clear();

            int read = fileInputStreamChannel.read(byteBuffer);
            if(read == -1){
                break;
            }
            //切换成可读模式
            byteBuffer.flip();
            fileOutputStreamChannel.write(byteBuffer);

        }
        //关闭通道
        fileInputStreamChannel.close();
        fileOutputStreamChannel.close();

    }




    @Test
    public void test02() throws IOException {
        //1.定义一个文件字节流与源文件想通
        FileInputStream fileInputStream = new FileInputStream("data.txt");
        //2.需要得到文件字节输入流的文件通道
        FileChannel fileChannel = fileInputStream.getChannel();
        //3.定义一个缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //4.通过缓冲区把数据加载出来
        fileChannel.read(byteBuffer);
        //5.读取出缓冲区的数据并输入即可
        byteBuffer.flip();
        String s = new String(byteBuffer.array(), 0, byteBuffer.remaining());
        System.out.println(s);

    }


    @Test
    public void test01() throws IOException {
        //1.获取一个字节输出流通向目标文件
        FileOutputStream fileOutputStream = new FileOutputStream("data.txt");
        //2.得到字节输出流对应的通道
        FileChannel fileChannel = fileOutputStream.getChannel();
        //3.分配缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("nlcexiyue".getBytes());
        //4.把缓冲区切换成写出模式
        byteBuffer.flip();
        fileChannel.write(byteBuffer);
        System.out.println("写出到文件中");

    }
}
