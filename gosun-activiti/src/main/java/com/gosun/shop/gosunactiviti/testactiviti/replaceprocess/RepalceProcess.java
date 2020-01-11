package com.gosun.shop.gosunactiviti.testactiviti.replaceprocess;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

/**
 * 测试同一key的流程部署多次，流程如何走
 */
public class RepalceProcess {

    //获取流程引擎
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();


    /**
     * 部署流程定义(zip)
     */
    @Test
    public void deployProcessDefinition_2(){
        //获取输入流
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("diagrams/Replace.zip");
        ZipInputStream zipInputStream = new ZipInputStream(in);
        Deployment deploy = processEngine.getRepositoryService()//获取与流程定义和部署对象相关的Service。
                .createDeployment()//创建一个部署对象
                .name("replaceProcess")//设置部署名称
                .addZipInputStream(zipInputStream)//通过zip输入流加载要部署的流程定义信息
                .deploy();//完成部署
        System.out.println("部署流程ID："+deploy.getId());

    }


    /**
     * 启动流程实例
     */
    @Test
    public void startProcessDefinition(){
        /**
         * 使用key的方式启动实例，（当同一流程被部署多次的时候）默认是启动最新部署的流程
         */
        String key = "helloWorld"; //这个key的值，就是表act_re_procdef中key字段的值，也是testActiviti.bpmn文件中定义的id的属性值。
        ProcessInstance pi = processEngine.getRuntimeService()//获取与正在执行的流程实例和执行对象相关的Service。
                .startProcessInstanceByKey(key);//通过流程定义的key 启动实例
        System.out.println("执行对象ID："+pi.getId());
        System.out.println("流程实例ID："+pi.getProcessInstanceId());

        System.out.println("流程定义ID："+pi.getProcessDefinitionId());

    }


    /**
     * 查询当前人的个人任务列表
     */
    @Test
    public void findPersonalTask(){
        String assignee = "张三";
        List<Task> list = processEngine.getTaskService()//获取与正在执行的任务管理相关的Service。
                .createTaskQuery()//创建任务查询对象。
                .taskAssignee(assignee)//指定办理人，查询个人任务
                .list();

        if (list != null && list.size() > 0){
            for (Task task: list) {
                System.out.println("任务ID："+task.getId());
                System.out.println("任务名称："+task.getName());
                System.out.println("任务创建时间："+task.getCreateTime());
                System.out.println("任务ID："+task.getAssignee());
                System.out.println("流程实例ID："+task.getProcessInstanceId());
                System.out.println("执行对象ID："+task.getExecutionId());
                System.out.println("流程定义ID："+task.getProcessDefinitionId());

                System.out.println("==============================================");
            }
        }

    }


    /**
     * 完成我的任务
     */
    @Test
    public void completePersonalTask(){

        String taskId = "15005";
        processEngine.getTaskService()//获取与正在执行的任务管理相关的Service。
                .complete(taskId);
        System.out.println("完成任务ID：" + taskId);
    }
}
