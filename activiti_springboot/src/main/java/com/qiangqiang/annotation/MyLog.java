package com.qiangqiang.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义的日志注解
 * @author xiyue
 */
@Target(ElementType.METHOD) //作用在方法上
@Retention(RetentionPolicy.RUNTIME) //运行时有效
public @interface MyLog {
    /**
     * 业务的名称,例如:"修改菜单"
     */
    String value() default "";

}
