package com.qiangqiang;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2021/1/5
 * \* Time: 9:10
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class ActivitiBusinessKey {

    //关联businessKey
    @Test
    public void addBusinessKey() {
        //1.获取引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //2.获取runtimeService
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //3.启动流程的过程中,添加 businessKey
        //这个businessKey就是要关联的业务id,后续可以关联表去查询出流程的详细信息
        //因为单单是在流程中是不包含业务信息的,比如,一个请假流程,如果到了总经理审批的时候, 总经理从流程实例中根本就看不到任何关于该请假的信息,不知道是谁请的假
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myEvection", "1001");
        //4.就可以输出相关联 的业务的表的id了
        System.out.println("关联的业务表:" + processInstance.getBusinessKey());


    }


    //假如到月底我不做处理出差申请了,那么就把所有的出差审批都给挂起,月初了我再全部激活
    @Test
    public void suspendAllProcessInstance() {
        //1.获取引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //2.获取repositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //3.查询流程定义, 获取流程定义的查询对象来查询
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("myEvection")
                .singleResult();
        //4.查询当前流程定义的实例是否都是挂起状态
        //获取当前实例是否是挂起状态,对应的表中字段1为true,2位false
        boolean suspended = processDefinition.isSuspended();
        //5.获取流程定义id
        String processDefinitionId = processDefinition.getId();
        //6.如果是挂起状态,改为激活状态
        if (suspended) {
            repositoryService.activateProcessDefinitionById(processDefinitionId, true, null);
            System.out.println(processDefinitionId + "已激活");

        } else {
            //7.如果是激活状态,则改为挂起状态
            //参数1是流程定义的id,参数2是是否要激活,参数3是激活时间
            repositoryService.suspendProcessDefinitionById(processDefinitionId, true, null);
            System.out.println(processDefinitionId + "已挂起");
        }

    }

    //单人的流程实例的挂起和激活
    @Test
    public void suspendOneProcessInstance(){
        //1.获取引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //2.获取runtimeService
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //3.获取流程实例的id
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId("10001")
                .singleResult();
        //4.获取流程实例的状态
        boolean suspended = processInstance.isSuspended();
        //5.获取流程实例id
        String id = processInstance.getId();
        System.out.println("流程实例id:" + id);

        if (suspended){
            runtimeService.activateProcessInstanceById(id);
            System.out.println("流程实例id:" + id +"激活");
        }else{
            runtimeService.suspendProcessInstanceById(id);
            System.out.println("流程实例id:" + id +"挂起");
        }

    }


    /**
     * 在用uel表达式来
     * 启动流程实例
     */
    @Test
    public void startProcessTest() {
        //1.创建processEngine
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        ProcessEngine processEngine = configuration.buildProcessEngine();
        //2.获取runtimeService
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //2.2这里要定义assignee这些变量
        Map<String , Object> variables = new HashMap<>();
        variables.put("assignee0","张三1");
        variables.put("assignee1","李四1");
        variables.put("assignee2","王五1");
        variables.put("assignee3","赵六1");


        //3.根据流程定义时候添加的id来启动流程实例
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("myEvection-uel",variables);
        //4.输出内容
        System.out.println("流程定义id:" + instance.getProcessDefinitionId());
        System.out.println("流程实例id:" + instance.getId());
        System.out.println("当前活动id:" + instance.getActivityId());
        System.out.println();


    }

    /**
     * 流程定义部署
     */
    @Test
    public void testCreateEngine() {
        //1.创建processEngine
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        ProcessEngine processEngine = configuration.buildProcessEngine();
//        //2.获取资源管理类
        RepositoryService repositoryService = processEngine.getRepositoryService();
//        //3.使用repositoryService进行流程的部署，定义一个流程的名字，把bpmn和png部署到数据库中
        Deployment deploy = repositoryService.createDeployment()
                .name("出差申请流程vatiable")     //流程的名字
                .addClasspathResource("bpmn3/evection_global.bpmn")     //添加资源文件
                .deploy();
//        //4.输出部署信息
        System.out.println("流程部署id" + deploy.getId());
        System.out.println("流程部署名字" + deploy.getName());

    }











}