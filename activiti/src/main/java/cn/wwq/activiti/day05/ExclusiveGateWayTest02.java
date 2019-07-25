package cn.wwq.activiti.day05;

import cn.wwq.activiti.day04.Holiday;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Mr.Wang
 * @create: 2019-07-25 22:03
 *  测试排他网关的存在的意义
 *  *      1.测试排他网关时的情况
 *  *          两个分条条件：holiday.num>=1
 *  *                      holiday.num>3
 *
 *  结论： 只会执行一条，ID值较小的。
 **/
public class ExclusiveGateWayTest02 {

    //完成任务
    @Test
    public void complete(){
        //1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        //2.得到TaskService对象
        TaskService taskService = processEngine.getTaskService();

        //3.查询当前用户的任务
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("holidayExclusive")
                .taskAssignee("zhaoliu")
                .singleResult();

        //4.处理任务,结合当前用户任务列表的查询操作的话,任务ID:task.getId()
        if(task!=null){
            taskService.complete(task.getId());
            System.out.println("用户任务执行完毕...");
        }
    }

    //2.启动实例
    @Test
    public void processInstance(){
        //1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        //2.得到RunService对象
        RuntimeService runtimeService = processEngine.getRuntimeService();

        Holiday holiday = new Holiday();
        holiday.setNum(4F);
        Map<String,Object> map = new HashMap<>();
        map.put("holiday",holiday);//流程变量赋值

        //3.创建流程实例  流程定义的key需要知道 holiday
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("holidayExclusive",map);


        //4.输出实例的相关信息
        System.out.println("流程定义ID"+processInstance.getProcessDefinitionId());//holiday:1:4
        System.out.println("流程实例ID"+processInstance.getId());//2501
    }

    //1.部署流程定义
    @Test
    public void deployment(){
        //1.创建ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        //2.得到RepositoryService实例
        RepositoryService repositoryService = processEngine.getRepositoryService();

        //3.进行部署
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("diagrams/holiday6.bpmn")  //添加bpmn资源
                .name("请假申请单流程")
                .deploy();

        //4.输出部署的一些信息
        System.out.println(deployment.getName());
        System.out.println(deployment.getId());
    }
}
