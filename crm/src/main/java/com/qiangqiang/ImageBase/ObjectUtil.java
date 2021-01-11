package com.qiangqiang.ImageBase;

import org.apache.commons.lang3.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/10/22
 * \* Time: 10:38
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class ObjectUtil {
    public static void main(String[] args) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username", ObjectUtils.NULL);
        System.out.println(ObjectUtils.NULL == map.get("username"));
        System.out.println(ObjectUtils.NULL == map.get("password"));


    }


}