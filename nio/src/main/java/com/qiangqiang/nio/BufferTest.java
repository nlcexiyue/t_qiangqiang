package com.qiangqiang.nio;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 会缓冲区的常用API进行案例实现
 */
public class BufferTest {

    @Test
    public void test04() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(new File("ss"));
        FileChannel channel = fileInputStream.getChannel();

    }


    @Test
    public void test03(){
        //1.创建一个直接内存的缓冲区
        ByteBuffer buffer = ByteBuffer.allocateDirect(10);


        System.out.println(buffer.isDirect());

    }

    @Test
    public void test02() {
        //1.分配一个缓冲区，容量设置为10
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);

        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());

        System.out.println("-----------------");
        //2.put往缓冲区中添加数据
        String name = "xiyue";
        byteBuffer.put(name.getBytes());

        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());


        System.out.println("-----------------");
        byteBuffer.clear();

        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());

        System.out.println("-----------------");


        //3.读取数据
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put("xiyue".getBytes());
        buffer.flip();
        byte[] bytes = new byte[3];
        buffer.get(bytes);
        System.out.println(new String(bytes));

        System.out.println(buffer.position());
        System.out.println(buffer.capacity());
        System.out.println(buffer.limit());

        System.out.println("-----------------");
        //标记此刻这个位置3
        buffer.mark();

        byte[] bytes1 = new byte[2];
        buffer.get(bytes1);
        System.out.println(new String(bytes1));
        System.out.println(buffer.position());
        System.out.println(buffer.capacity());
        System.out.println(buffer.limit());

        System.out.println("-----------------");

        buffer.reset();
        if (buffer.hasRemaining()) {
            System.out.println(buffer.remaining());
        }


    }


    @Test
    public void test01() {
        //1.分配一个缓冲区，容量设置为10
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);

        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());

        System.out.println("-----------------");
        //2.put往缓冲区中添加数据
        String name = "xiyue";
        byteBuffer.put(name.getBytes());

        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());

        System.out.println("-----------------");

        //3.flip方法,将界限limit设置为当前位置,并将当前位置设置为0,可读模式
        byteBuffer.flip();
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());

        System.out.println("-----------------");

        //4.get读取数据
        char c = (char) byteBuffer.get();
        System.out.println(c);
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());

        System.out.println("-----------------");


    }


}
