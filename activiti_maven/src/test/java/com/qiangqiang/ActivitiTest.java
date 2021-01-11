package com.qiangqiang;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.zip.ZipInputStream;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2021/1/4
 * \* Time: 14:53
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class ActivitiTest {
    @Test
    public void testCreateEngine() {
        //1.创建processEngine
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        ProcessEngine processEngine = configuration.buildProcessEngine();
//        //2.获取资源管理类
//        RepositoryService repositoryService = processEngine.getRepositoryService();
//        //3.使用repositoryService进行流程的部署，定义一个流程的名字，把bpmn和png部署到数据库中
//        Deployment deploy = repositoryService.createDeployment()
//                .name("出差申请流程")     //流程的名字
//                .addClasspathResource("bpmn/evection.bpmn")     //添加资源文件
//                .addClasspathResource("bpmn/evection.png")  //添加资源文件
//                .deploy();
//        //4.输出部署信息
//        System.out.println("流程部署id" + deploy.getId());
//        System.out.println("流程部署名字" + deploy.getName());

    }

    /**
     * 启动流程实例
     */
    @Test
    public void startProcessTest() {
        //1.创建processEngine
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        ProcessEngine processEngine = configuration.buildProcessEngine();
        //2.获取runtimeService
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //3.根据流程定义时候添加的id来启动流程实例
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("myProcess-listen");
        //4.输出内容
        System.out.println("流程定义id:" + instance.getProcessDefinitionId());
        System.out.println("流程实例id:" + instance.getId());
        System.out.println("当前活动id:" + instance.getActivityId());
        System.out.println();


    }


    /**
     * 个人任务查询功能
     * 出差申请多人都可以提交,那么查询的结果是多个
     */
    @Test
    public void findPersonTaskListTest() {
        //1.获取processEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //2.获取TaskService
        TaskService taskService = processEngine.getTaskService();
        //3.根据条件去流程的key和任务的负责人来查询任务
        List<Task> list = taskService.createTaskQuery()
//                .processDefinitionKey("myEvection")     //流程key
                .taskAssignee("jerry")               //流程负责人
                .taskId("12502")                    //act_ru_task的表的主键id
                .list();

        //4.输出
        for (Task task : list) {
            System.out.println("流程实例id:" + task.getProcessInstanceId());
            System.out.println("流程任务id:" + task.getId());
            System.out.println("任务负责人:" + task.getAssignee());
            System.out.println("任务名称:" + task.getName());

        }
    }


    /**
     * 完成个人任务
     */
    @Test
    public void completeTaskTest() {
        //1.获取流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //2.获取能够处理的service
        TaskService taskService = processEngine.getTaskService();
        //3.根据任务id完成任务
//        taskService.complete("7502");
        //获取jack对应的任务
        Task task = taskService.createTaskQuery()
//                .processDefinitionKey("myEvection-uel")
                .processDefinitionId("myProcess-listen:1:47503")
                .taskAssignee("张三三")
                .singleResult();
        String id = task.getId();
        System.out.println("流程实例id:" + task.getProcessInstanceId());
        System.out.println("流程任务id:" + task.getId());
        System.out.println("任务负责人:" + task.getAssignee());
        System.out.println("任务名称:" + task.getName());
        taskService.complete(id);

    }


    /**
     * 打包部署bpmn和png,用zip包部署
     */
    @Test
    public void deployProcessByZip() {
        //1.获取流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //2.获取repositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //3.进行流程部署

        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("bpmn1/evection.zip");
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);

        Deployment deploy = repositoryService.createDeployment()
                .addZipInputStream(zipInputStream)
                .deploy();


        System.out.println("流程部署id" + deploy.getId());
        System.out.println("流程部署名字" + deploy.getName());


    }

    /**
     * 查询流程定义
     */
    @Test
    public void queryProcessDefinition() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //获取repositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();

        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();

        //查询当前所有的流程定义,返回的是流程定义信息的集合
        //processDefinitionKey(流程定义的key)
        //orderByProcessDefinitionVersion()进行排序
        //list()查询出所有的内容
        List<ProcessDefinition> definitionList = processDefinitionQuery.processDefinitionKey("myEvection")
                .orderByProcessDefinitionVersion()
                .desc()
                .list();

        for (ProcessDefinition processDefinition : definitionList) {
            System.out.println("流程定义id:" + processDefinition.getId());
            System.out.println("流程定义名称:" + processDefinition.getName());
            System.out.println("流程定义的key:" + processDefinition.getKey());
            System.out.println("流程部署id:" + processDefinition.getDeploymentId());
        }


    }


    /**
     * 删除流程部署信息
     * 但是不会删除历史信息表中的数据
     */
    @Test
    public void deleteDeploy() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        String deploymentId = "1";
        repositoryService.deleteDeployment(deploymentId);

    }


    /**
     * 删除流程部署信息,关联删除,这样可以在流程已经开始的情况下,直接中断流程删除部署信息
     */
    @Test
    public void deleteDeployBreak() {

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        String deploymentId = "107501";
        //这个方法就是关联删除的,在流程没有走完的情况下,也可以删除
        repositoryService.deleteDeployment(deploymentId, true);
    }


    /**
     * 下载资源文件
     * 使用repositoryService进行辅助下载
     */
    @Test
    public void downloadDeployment() {
        //1.获取引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //2.获取RepositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //3.获取查询对象ProcessDefinitionQuery去查询出来部署id去关联下载
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("myEvection")
                .singleResult();
        //4.通过流程定义信息,获取部署id
        String deploymentId = processDefinition.getDeploymentId();
        //从流程定义表中获取png图片的目录和名字
        String pngName = processDefinition.getDiagramResourceName();
        String bpmnName = processDefinition.getResourceName();
        //5.通过部署id去查询出来资源
        InputStream pngInputStream = repositoryService.getResourceAsStream(deploymentId, pngName);
        InputStream bpmnInputStream = repositoryService.getResourceAsStream(deploymentId, bpmnName);

        //后边就是普通的下载代码了

    }


    /**
     * 历史流程信息查看
     */
    @Test
    public void findHistory() {
        //查询actinst表
        //1.获取引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        HistoryService historyService = processEngine.getHistoryService();
        HistoricActivityInstanceQuery instanceQuery = historyService.createHistoricActivityInstanceQuery();
        //这样就把2501的所有数据放在instanceQuery中了
        instanceQuery.processInstanceId("2501");
        //也可以根据这个来查询
//        instanceQuery.processDefinitionId("myEvection:1:4");
        //增加排序操作
        instanceQuery.orderByHistoricActivityInstanceStartTime().asc();
        //查询所有内容
        List<HistoricActivityInstance> list = instanceQuery.list();

        System.out.println("历史信息:"+list.size());
        for (HistoricActivityInstance historicActivityInstance : list) {

            System.out.println("历史信息:"+historicActivityInstance.getActivityId());
            System.out.println("历史信息:"+historicActivityInstance.getActivityName());
        }



    }


}