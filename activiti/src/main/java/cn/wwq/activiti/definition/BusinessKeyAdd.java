package cn.wwq.activiti.definition;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;

/**
 *
 * 启动流程实例，添加进businessKey
 *本质：act_ru_execution表中的businessKey的字段要存入业务标识
 * @author: Mr.Wang
 * @create: 2019-07-20 09:55
 **/
public class BusinessKeyAdd {
    public static void main(String[] args) {

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();

        //3.启动流程实例,同时还要指定业务标识businessKey  它本身就是请假单的id
        //第一个参数：是指流程定义key
        //第二个参数：业务标识businessKey

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("holiday", "1001");

        System.out.println(processInstance.getBusinessKey());
    }

}
