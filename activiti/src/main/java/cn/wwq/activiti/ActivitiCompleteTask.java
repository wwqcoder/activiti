package cn.wwq.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

/**
 * @author: Mr.Wang
 * @create: 2019-07-19 23:45
 **/
public class ActivitiCompleteTask {

    public static void main(String[] args) {

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        TaskService taskService = processEngine.getTaskService();

        Task task = taskService.createTaskQuery()
                .processDefinitionKey("holiday")
                .taskAssignee("lisi")
                .singleResult();

        taskService.complete(task.getId());

        System.out.println(task.getId());

    }
}
