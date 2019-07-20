package cn.wwq.activiti.definition;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;

import java.util.List;

/**
 *
 *  查询流程定义信息
 * @author: Mr.Wang
 * @create: 2019-07-20 09:11
 **/
public class QueryProcessDefinition {
    public static void main(String[] args) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();

        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();

        List<ProcessDefinition> list = processDefinitionQuery.processDefinitionKey("holiday")
                .orderByProcessDefinitionVersion()
                .desc()
                .list();

        for (ProcessDefinition processDefinition : list) {
            System.out.println("流程定义ID："+processDefinition.getId());
            System.out.println("流程定义名称："+processDefinition.getName());
            System.out.println("流程定义的Key："+processDefinition.getKey());
            System.out.println("流程定义的版本号："+processDefinition.getVersion());
            System.out.println("流程部署的ID:"+processDefinition.getDeploymentId());
        }

    }
}
