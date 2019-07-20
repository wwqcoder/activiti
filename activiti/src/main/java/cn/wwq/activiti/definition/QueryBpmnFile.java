package cn.wwq.activiti.definition;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.io.IOUtils;

import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * 需求：
 *  * 1.从Activiti的act_ge_bytearray表中读取两个资源文件
 *  * 2.将两个资源文件保存到路径：   G:\Activiti7
 *  *
 *  * 技术方案：
 *  *     1.第一种方式使用actviti的api来实现
 *  *     2.第二种方式：其实就是原理层面，可以使用jdbc的对blob类型，clob类型数据的读取，并保存
 *  *        IO流转换，最好commons-io.jar包可以轻松解决IO操作
 *  *
 * @author: Mr.Wang
 * @create: 2019-07-20 09:35
 **/
public class QueryBpmnFile {

    public static void main(String[] args) throws Exception {

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();

        //得到查询器:ProcessDefinitionQuery对象
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();

        //设置查询条件
        processDefinitionQuery.processDefinitionKey("holiday");
        //执行查询操作,查询出想要的流程定义
        ProcessDefinition processDefinition = processDefinitionQuery.singleResult();
        //通过流程定义信息，得到部署ID
        String deploymentId = processDefinition.getDeploymentId();
        //通过repositoryService的方法,实现读取图片信息及bpmn文件信息(输入流)
        //getResourceAsStream()方法的参数说明：第一个参数部署id,第二个参数代表资源名称
        //processDefinition.getDiagramResourceName() 代表获取png图片资源的名称
        //processDefinition.getResourceName()代表获取bpmn文件的名称

        InputStream pngIs = repositoryService.getResourceAsStream(deploymentId, processDefinition.getDiagramResourceName());
        InputStream bpmnIs = repositoryService.getResourceAsStream(deploymentId, processDefinition.getResourceName());

        //构建出OutputStream流
        FileOutputStream pngOs = new FileOutputStream("G:\\Activiti7\\" + processDefinition.getDiagramResourceName());
        FileOutputStream bpmnOs = new FileOutputStream("G:\\Activiti7\\" + processDefinition.getResourceName());

        //输入流，输出流的转换  commons-io-xx.jar中的方法
        IOUtils.copy(pngIs,pngOs);
        IOUtils.copy(bpmnIs,bpmnOs);

        bpmnOs.close();
        pngOs.close();
        bpmnIs.close();
        pngIs.close();


    }



}
