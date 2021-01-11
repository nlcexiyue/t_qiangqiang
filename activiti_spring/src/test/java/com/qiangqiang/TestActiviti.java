package com.qiangqiang;

import com.fasterxml.jackson.databind.annotation.JsonTypeResolver;
import org.activiti.engine.RepositoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2021/1/6
 * \* Time: 9:43
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:activiti-spring.xml")
public class TestActiviti {
    @Autowired
    private RepositoryService repositoryService;

    @Test
    public void createTable(){
        repositoryService.createDeployment()
                .addClasspathResource("bpmn/evection.bpmn")
                .name("evection-spring")
                .deploy();
        System.out.println("流程定义部署成功");
    }
}