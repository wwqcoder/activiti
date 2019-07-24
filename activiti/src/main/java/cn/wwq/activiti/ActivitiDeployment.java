package cn.wwq.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

/**
 * @author: Mr.Wang
 * @create: 2019-07-19 23:09
 * 流程部署
 **/
public class ActivitiDeployment {
    public static void main(String[] args) {
        //1.创建ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //2.得到RepositoryService实例
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //3.开始部署
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("diagrams/myholiday2.bpmn")
                .addClasspathResource("diagrams/myholiday2.png")
                .name("请假申请流程单")
                .deploy();
        //4.输出部署的一些信息
        System.out.println(deployment.getName());
        System.out.println(deployment.getId());
    }
    public static void main1(String[] args) {

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();

        InputStream is = ActivitiDeployment.class.getClassLoader().getResourceAsStream("diagrams/holiday.zip");

        //将inputstream转为ZIPInputstream

        ZipInputStream zipInputStream = new ZipInputStream(is);
        Deployment deployment = repositoryService.createDeployment()
                .addZipInputStream(zipInputStream)
                .name("请假申请流程")
                .deploy();
        //输出部署的一些信息
        System.out.println(deployment.getId());
        System.out.println(deployment.getName());
    }
}
