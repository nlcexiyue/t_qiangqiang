package com.qiangqiang.Zookeeper;

import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;

import java.io.UnsupportedEncodingException;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/2
 * \* Time: 16:13
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class CustomSerializer implements ZkSerializer {

    public static final String chatSet = "utf-8";

    /**
     * 序列化
     * @param o
     * @return
     * @throws ZkMarshallingError
     */
    @Override
    public byte[] serialize(Object o) throws ZkMarshallingError {
        byte[] bytes = new byte[0];
        try {
            bytes = String.valueOf(o).getBytes(chatSet);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return bytes;
    }


    /**
     * 反序列化
     * @param bytes
     * @return
     * @throws ZkMarshallingError
     */
    @Override
    public Object deserialize(byte[] bytes) throws ZkMarshallingError {
        String s = null;
        try {
            s = new String(bytes, chatSet);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }
}