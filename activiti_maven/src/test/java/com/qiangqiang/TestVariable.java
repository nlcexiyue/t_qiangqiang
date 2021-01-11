package com.qiangqiang;


import com.qiangqiang.entity.Evection;
import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2021/1/5
 * \* Time: 16:23
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class TestVariable {

    /**
     * 流程部署
     */
    @Test
    public void testDeployment(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.createDeployment()
                .name("出差申请流程variables")
                .addClasspathResource("bpmn3/evection_global.bpmn")
                .deploy();

    }






    /**
     * 启动流程时设置变量
     */
    @Test
    public void testStartProcess(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //流程定义的key
        String key = "exevtion-variables";
        //设置流程变量
        Map<String , Object> variables = new HashMap<>();
        Evection evection = new Evection();
        evection.setDays(4d);
        variables.put("evection",evection);

        //设定任务的负责人
        variables.put("assignee0","张三");
        variables.put("assignee1","李四");
        variables.put("assignee2","王五");
        variables.put("assignee3","赵六");
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("exevtion-variables",variables);
    }


    /**
     * 任务办理时设置变量
     *  taskService.complete(id,map);   完成任务时,设置流程变量的值
     */
    @Test
    public void testStartProcess2(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //流程定义的key
        String key = "exevtion-variables";
        //设置流程变量
        Map<String , Object> variables = new HashMap<>();


        //设定任务的负责人
        variables.put("assignee0","张三");
        variables.put("assignee1","李四");
        variables.put("assignee2","王五");
        variables.put("assignee3","赵六");
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("exevtion-variables",variables);
    }
    @Test
    public void completeTask2(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        String key = "exevtion-variables";
        Map<String , Object> variables = new HashMap<>();
        Evection evection = new Evection();
        evection.setDays(2d);
        variables.put("evection",evection);
        Task task = taskService.createTaskQuery()
                .processDefinitionKey(key)
                .taskAssignee("张三")
                .singleResult();
        String id = task.getId();
        taskService.complete(id,variables);

    }



    /**
     * 通过当前流程设置变量
     * 通过流程实例id设置全局变量,该流程实例必须未执行完成
     */
    @Test
    public void testStartProcess3(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //流程定义的key
        String key = "exevtion-variables";
        //设置流程变量
        Map<String , Object> variables = new HashMap<>();


        //设定任务的负责人
        variables.put("assignee0","张三");
        variables.put("assignee1","李四");
        variables.put("assignee2","王五");
        variables.put("assignee3","赵六");
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("exevtion-variables",variables);
    }











    /**
     * 通过当前任务设置变量
     */







    /**
     * 完成个人任务
     */
    @Test
    public void completeTask(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        String key = "exevtion-variables";
        Task task = taskService.createTaskQuery()
                .processDefinitionKey(key)
                .taskAssignee("李四")
                .singleResult();
        String id = task.getId();
        taskService.complete(id);

    }
}