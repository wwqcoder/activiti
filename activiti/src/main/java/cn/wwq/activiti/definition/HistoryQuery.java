package cn.wwq.activiti.definition;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;

import java.util.List;

/**
 * 需求：
 *    查看历史数据
 *
 * @author: Mr.Wang
 * @create: 2019-07-20 09:59
 **/
public class HistoryQuery {

    public static void main(String[] args) {

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        HistoryService historyService = processEngine.getHistoryService();

        //得到HistoricActivitiInstanceQuery对象
        HistoricActivityInstanceQuery historicActivityInstanceQuery = historyService.createHistoricActivityInstanceQuery();

        //设置流程实例的id
        historicActivityInstanceQuery.processInstanceId("17501");

        List<HistoricActivityInstance> list = historicActivityInstanceQuery.orderByHistoricActivityInstanceStartTime()
                .asc()
                .list();

        for (HistoricActivityInstance instance : list) {
            System.out.println(instance.getActivityId());
            System.out.println(instance.getActivityName());
            System.out.println(instance.getProcessDefinitionId());
            System.out.println(instance.getProcessInstanceId());
            System.out.println("=============================");
        }
    }
}
