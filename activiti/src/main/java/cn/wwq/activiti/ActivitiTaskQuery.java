package cn.wwq.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

/**
 * @author: Mr.Wang
 * @create: 2019-07-19 23:34
 **/
public class ActivitiTaskQuery {
    public static void main(String[] args) {

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        TaskService taskService = processEngine.getTaskService();
        //根据流程定义的key,负责人assignee来实现当前用户的任务列表查询
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("holiday")
                .taskAssignee("wangwu")
                .singleResult();

        //任务列表展示
        System.out.println("流程实例ID"+task.getProcessInstanceId());
        System.out.println("任务ID"+task.getId());
        System.out.println("任务负责人"+task.getAssignee());
        System.out.println("任务名称"+task.getName());
    }
}
