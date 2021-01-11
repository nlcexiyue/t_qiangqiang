package com.qiangqiang.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2021/1/7
 * \* Time: 14:03
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */

@Aspect
@Component
public class MyLog {
    private Logger log = LoggerFactory.getLogger(this.getClass());


    @Pointcut(value = "@annotation(com.qiangqiang.annotation.MyLog)")
    public void cutService(){

    }

    @Around(value = "cutService()")
    public void recordLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {


        log.info("proceedingJoinPoint--" + proceedingJoinPoint);
        //获取拦截的方法名
        Signature signature = proceedingJoinPoint.getSignature();
        log.info("signature--" + signature.toString());

        MethodSignature msig = null;
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) signature;
        Object target = proceedingJoinPoint.getTarget();
        Method method = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        String methodName = method.getName();

        //获取操作名称
        com.qiangqiang.annotation.MyLog annotation = method.getAnnotation(com.qiangqiang.annotation.MyLog.class);
        String value = annotation.value();
        log.info("value--" + value.toString());


        //获取方法的参数

        Object[] params = proceedingJoinPoint.getArgs();
        StringBuilder sb = new StringBuilder();
        for (Object param : params) {
            sb.append(param);
            sb.append(" & ");
        }

        log.info("sb--" + sb.toString());

        //返回值
        Object object=proceedingJoinPoint.proceed();
        log.info("返回值--" + object);

    }


//    @AfterReturning(value = "cutService()",returning = "returnValue")
//    public void logResult(Object returnValue){
//        log.info("方法返回值为--" + returnValue.toString());
//    }

}