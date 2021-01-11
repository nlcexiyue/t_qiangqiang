package com.qiangqiang;

import com.qiangqiang.entity.Evection;
import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//组任务
public class TestCandidate {
    /**
     * 流程部署
     */
    @Test
    public void testDeployment(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.createDeployment()
                .name("出差申请流程variables")
                .addClasspathResource("bpmn4/evection.bpmn")
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
        String key = "myEvection-zu";
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(key);
    }

    /**
     * 查询组任务
     */
    @Test
    public void findGroupTaskList(){
        //流程定义的key
        String key = "myEvection-zu";
        //任务候选人
        String candidateUser = "wangwu";
        //获取引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //获取taskService
        TaskService taskService = processEngine.getTaskService();
        //查询组任务
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey(key)
                .taskCandidateUser(candidateUser)   //根据候选人来查询任务
                .list();

        for (Task task : taskList) {
            System.out.println("------------------");
            System.out.println("流程实例id:" + task.getProcessInstanceId());
            System.out.println("任务id:"+ task.getId());
            System.out.println("任务负责人:"+ task.getAssignee());
        }
    }

    /**
     * 拾取任务
     */
    @Test
    public void claimTask(){
        //获取引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //获取taskService
        TaskService taskService = processEngine.getTaskService();
        //当前任务的id
        String taskId = "5002";
        //任务候选人
        String candidateUser = "wangwu";
        //查询任务
        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .taskCandidateUser(candidateUser)
                .singleResult();
        if (task != null){
            //拾取任务
            taskService.claim(taskId,candidateUser);
            System.out.println("taskId" + taskId + "用户拾取完成");

        }
    }


    /**
     * 归还任务
     */
    @Test
    public void assigneeToGroupTask(){
        //获取引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //获取taskService
        TaskService taskService = processEngine.getTaskService();
        //当前任务的id
        String taskId = "5002";
        //任务候选人
        String assignee = "wangwu";
        //查询任务
        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .taskAssignee(assignee)     //这里同拾取任务不同,这里要用负责人来查询
                .singleResult();
        if (task != null){
            //归还任务
            //就是把task表中的assignee字段设置为空
            taskService.setAssignee(taskId,null);
            System.out.println("taskId" + taskId + "用户归还完成");

        }
    }

    /**
     * 任务交接,就是任务获取后直接把task表的assignee字段改成别人
     */
    @Test
    public void assigneeToCandidateUser(){

        //获取引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //获取taskService
        TaskService taskService = processEngine.getTaskService();
        //当前任务的id
        String taskId = "5002";
        //任务候选人
        String assignee = "wangwu";

        String candidateUser = "lisi";
        //查询任务
        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .taskAssignee(assignee)     //这里同拾取任务不同,这里要用负责人来查询
                .singleResult();
        if (task != null){
            //归还任务
            //就是把task表中的assignee字段设置为空
            taskService.setAssignee(taskId,candidateUser);
            System.out.println("taskId" + taskId + "用户交接完成");

        }


    }





    /**
     * 完成个人任务
     */
    @Test
    public void completeTask(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        String key = "myEvection-zu";
        Task task = taskService.createTaskQuery()
                .processDefinitionKey(key)
                .taskAssignee("wangwu")
                .singleResult();
        String id = task.getId();
        taskService.complete(id);

    }


}
