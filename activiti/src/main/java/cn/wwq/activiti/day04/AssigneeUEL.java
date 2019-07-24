package cn.wwq.activiti.day04;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Mr.Wang
 * @create: 2019-07-24 21:15
 **/
public class AssigneeUEL {

    public static void main(String[] args) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        RuntimeService runtimeService = processEngine.getRuntimeService();

        //3.设置assignee的取值   用户可以在界面上设置流程的执行人
        Map<String,Object> map = new HashMap<>();
        map.put("assignee0","zhangsan");
        map.put("assignee1","lishi");
        map.put("assignee2","wangwu");

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("holiday2", map);

        System.out.println(processEngine.getName());


    }
}
