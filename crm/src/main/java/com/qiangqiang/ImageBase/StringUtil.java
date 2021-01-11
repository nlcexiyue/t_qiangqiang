package com.qiangqiang.ImageBase;

import org.apache.commons.lang3.StringUtils;

import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/10/22
 * \* Time: 11:05
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class StringUtil {



    public static void main(String[] args) {
        String s = "k";
        boolean alpha = StringUtils.isAlphaSpace(s);
        System.out.println(alpha);


    }

}