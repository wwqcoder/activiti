package cn.wwq.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;

/**
 * 启动流程实例:
 * 前提是先已经完成流程定义的部署工作
 * @author: Mr.Wang
 * @create: 2019-07-19 23:24
 **/
public class ActivitiStartInstance {

    public static void main(String[] args) {
        //1.创建ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //2.得到RuntimeService实例
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //创建流程实例，流程定义的key需要知道，
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("holiday");
        //输出实例相关的信息
        System.out.println("流程部署的ID"+processInstance.getDeploymentId());
        System.out.println("流程定义ID"+processInstance.getProcessDefinitionId());
        System.out.println("流程实例的ID"+processInstance.getId());
        System.out.println("活动的ID"+processInstance.getActivityId());


    }
}
