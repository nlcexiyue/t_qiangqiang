//package com.qiangqiang.config;
//
//import org.activiti.engine.delegate.DelegateTask;
//import org.activiti.engine.delegate.TaskListener;
//
///**
// * \* Created with IntelliJ IDEA.
// * \* @author: xiyue
// * \* Date: 2021/1/5
// * \* Time: 14:05
// * \* To change this template use File | Settings | File Templates.
// * \* Description:监听器,需要实现一个TaskListener接口
// * \
// */
//public class MyTaskListener implements TaskListener {
//
//
//    /**
//     * 指定负责人
//     * @param delegateTask
//     */
//    @Override
//    public void notify(DelegateTask delegateTask) {
//        //先判断当前事件触发的时候是哪个任务     是创建任务 并且是 create事件
//        if("创建申请".equals(delegateTask.getName()) && "create".equals(delegateTask.getEventName())){
//            delegateTask.setAssignee("张三三");
//        }
//
//
//    }
//}