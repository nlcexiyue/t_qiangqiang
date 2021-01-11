package com.qiangqiang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/18
 * \* Time: 16:26
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExplainResult implements Serializable {
    private int id;

    private String selectType;

    private String table;

    private String partitions;

    private String type;

    private String possibleKeys;

    private String key;

    private String keyLen;

    private String ref;

    private int rows;

    private int filtered;

    private String Extra;

}