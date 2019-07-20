package cn.wwq.activiti.definition;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;

/**
 * 全部流程实例挂起与激活
 * @author: Mr.Wang
 * @create: 2019-07-20 10:08
 **/
public class SuspendProcessInstance2 {
    public static void main(String[] args) {
        //1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        //2.得到RuntimeService
        RuntimeService runtimeService = processEngine.getRuntimeService();

        //3.查询流程实例的对象
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId("17501")
                .singleResult();

        //4.得到当前流程定义的实例是否都为暂停状态
        boolean suspended = processInstance.isSuspended();
        String processInstanceId = processInstance.getId();
        //5.判断
        if (suspended){
            //说明是暂停，就可以激活操作
            runtimeService.activateProcessInstanceById(processInstanceId);
            System.out.println("流程定义："+processInstanceId+"激活");
        }else {
            runtimeService.suspendProcessInstanceById(processInstanceId);
            System.out.println("流程定义："+processInstanceId+"挂起");
        }
    }

}
