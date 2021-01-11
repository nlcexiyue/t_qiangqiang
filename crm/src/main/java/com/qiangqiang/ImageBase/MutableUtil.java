package com.qiangqiang.ImageBase;

import org.apache.commons.lang3.mutable.Mutable;
import org.apache.commons.lang3.mutable.MutableInt;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/10/22
 * \* Time: 10:51
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class MutableUtil {

    public static void main(String[] args) {
        Mutable count = new MutableInt(9);
        System.out.println(((MutableInt) count).getValue());
    }




}