package cn.wwq.activiti.definition;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;

/**
 *
 * 删除已经部署的流程定义
 * @author: Mr.Wang
 * @create: 2019-07-20 09:17
 **/
public class DeleteProcessDefinition {
    /**
     * 注意事项：
     *     1.当我们正在执行的这一套流程没有完全审批结束的时候，此时如果要删除流程定义信息就会失败
     *     2.如果公司层面要强制删除,可以使用repositoryService.deleteDeployment("1",true);
     *     //参数true代表级联删除，此时就会先删除没有完成的流程结点，最后就可以删除流程定义信息  false的值代表不级联
     *
     * @param args
     */
    public static void main(String[] args) {

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();

        repositoryService.deleteDeployment("12501",true);
    }

}
