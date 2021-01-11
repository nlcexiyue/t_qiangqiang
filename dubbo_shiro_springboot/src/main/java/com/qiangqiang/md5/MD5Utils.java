package com.qiangqiang.md5;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/23
 * \* Time: 15:36
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class MD5Utils {

    public static void main(String[] args) {
        String source = "123456";
        Md5Hash hash = new Md5Hash(source,"MaoZhuXi",2);
        System.out.println(hash);



    }
}