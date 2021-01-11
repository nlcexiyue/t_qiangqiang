package com.qiangqiang.ImageBase;

import org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.Objects;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/10/22
 * \* Time: 10:54
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class EqualBuild {

    private String id;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EqualBuild that = (EqualBuild) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(id, that.id)
                .isEquals();
//        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}