package cn.wwq.activiti;

import org.activiti.engine.RepositoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: Mr.Wang
 * @create: 2019-07-27 10:23
 **/

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:activiti-spring.xml")
public class ActivitiSpringIntegrationTest {

    @Autowired
    RepositoryService repositoryService;

    @Test
    public void testDeploymentObj(){
        System.out.println("部署对象："+repositoryService);
    }
}
