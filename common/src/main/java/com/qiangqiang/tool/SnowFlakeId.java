package com.qiangqiang.tool;

import io.shardingsphere.core.keygen.DefaultKeyGenerator;

import java.util.Random;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/17
 * \* Time: 11:31
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class SnowFlakeId {
    private static final int SEED = 255;

    private static DefaultKeyGenerator defaultKeyGenerator = new DefaultKeyGenerator();

    static {
        DefaultKeyGenerator.setWorkerId(new Random().nextInt(SEED));
    }

    /**
     * 生成流水号
     * @return string
     */
    public static long generateId() {
        return defaultKeyGenerator.generateKey().longValue();
    }

}