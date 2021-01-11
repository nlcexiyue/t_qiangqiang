package com.qiangqiang;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.junit.Test;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2021/1/4
 * \* Time: 10:04
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */

public class TestCreate {
    @Test
    public void testCreateEngine(){
        //调用ProcessEngines生成processEngine
        //getDefaultProcessEngine会默认从resource的activiti.cfg.xml读取
//        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //自定义方式创建,配置的文件名字可以自定义
        //bean的名字也可以自定义
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        ProcessEngine processEngine = configuration.buildProcessEngine();
        //资源管理类
        RepositoryService repositoryService = processEngine.getRepositoryService();



    }
}