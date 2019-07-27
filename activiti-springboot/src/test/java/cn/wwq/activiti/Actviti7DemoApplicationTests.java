package cn.wwq.activiti;

import org.activiti.api.process.model.ProcessDefinition;
import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.model.Task;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.runtime.TaskRuntime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: Mr.Wang
 * @create: 2019-07-27 13:02
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class Actviti7DemoApplicationTests {

    @Autowired
    private ProcessRuntime processRuntime;
    @Autowired
    private TaskRuntime taskRuntime;
    @Autowired
    private SecurityUtil securityUtil;


    /**
     //     * 启动流程实例
     //     */
    @Test
    public void testStartProcess() {
        securityUtil.logInAs("salaboy");
        ProcessInstance pi = processRuntime.start(ProcessPayloadBuilder.start().withProcessDefinitionKey("myProcess_1")
                .build());
        System.out.println("流程实例ID：" + pi.getId());
    }

    /**
     //     * 查看流程定义
     //     */
    @Test
    public void contextLoads() {
        securityUtil.logInAs("ryandawsonuk");
        Page<ProcessDefinition> processDefinitionPage = processRuntime.processDefinitions(Pageable.of(0, 10));
        System.out.println("可用的流程定义数量：" + processDefinitionPage.getTotalItems());
        for (ProcessDefinition pd : processDefinitionPage.getContent()) {
            System.out.println("流程定义：" + pd);
        }
    }

    /**
     * 查询任务，并完成自己的任务
     */
    @Test
    public void testTask() {
        securityUtil.logInAs("erdemedeiros");
        Page<Task> taskPage = taskRuntime.tasks(Pageable.of(0, 10));

        if (taskPage.getTotalItems() > 0){
            for (Task task : taskPage.getContent()) {
                //拾取任务
                taskRuntime.claim(TaskPayloadBuilder.claim().withTaskId(task.getId()).build());
                System.out.println("任务："+task);
                //完成任务
                taskRuntime.complete(TaskPayloadBuilder.complete().withTaskId(task.getId()).build());
            }
        }

        Page<Task> taskPage2=taskRuntime.tasks(Pageable.of(0,10));
        if (taskPage2.getTotalItems()>0){
            System.out.println("任务："+taskPage2.getContent());
        }



    }



}
