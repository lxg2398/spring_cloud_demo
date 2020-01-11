package com.gosun.shop.gosunactiviti.testactiviti.receivetask;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

/**
 * 接收活动任务节点
 */
public class ReceiveTaskTest {

	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

	/**部署流程定义（从zip）*/
	@Test
	public void deploymentProcessDefinition_zip(){
		//获取输入流
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("diagrams/receiveTask.zip");
		ZipInputStream zipInputStream = new ZipInputStream(in);
		Deployment deployment = processEngine.getRepositoryService()//与流程定义和部署对象相关的Service
				.createDeployment()//创建一个部署对象
				.name("接收活动任务")//添加部署的名称
				.addZipInputStream(zipInputStream)//
				.deploy();//完成部署
		System.out.println("部署ID："+deployment.getId());//
		System.out.println("部署名称："+deployment.getName());//
	}
	
	/**启动流程实例+设置流程变量+获取流程变量+向后执行一步*/
	@Test
	public void startProcessInstance(){
		//流程定义的key
		String processDefinitionKey = "receiveTask";
		ProcessInstance pi = processEngine.getRuntimeService()//与正在执行的流程实例和执行对象相关的Service
						.startProcessInstanceByKey(processDefinitionKey);//使用流程定义的key启动流程实例，默认是按照最新版本的流程定义启动
		System.out.println("流程实例ID:"+pi.getId());//流程实例ID    101
		System.out.println("流程定义ID:"+pi.getProcessDefinitionId());//流程定义ID   helloworld:1:4

		/**查询执行对象ID
		 *
		 * 以下这两个方法一起才能确定唯一一个执行对象
		 * .processInstanceId(pi.getId())
		 * .activityId("_3")
		 *
		 * */
		Execution execution1 = processEngine.getRuntimeService()//
						.createExecutionQuery()//创建执行对象查询
						.processInstanceId(pi.getId())//使用流程实例ID查询
						.activityId("_3")//使用当前活动的id查询，id对应receiveTask.bpmn文件中的活动节点id的属性值
						.singleResult();
		
		/**使用流程变量设置当日销售额，用来传递业务参数*/
		processEngine.getRuntimeService()//此处不能使用任务Service
						.setVariable(execution1.getId(), "汇总当日销售额", 21000);
		
		/**向后执行一步，使用trigger给流程引擎信号，告诉他当前任务已经完成了，可以往后执行，使得流程继续执行*/
		processEngine.getRuntimeService().trigger(execution1.getId());
						//.signal(execution1.getId());
		
		/**查询执行对象ID*/
		Execution execution2 = processEngine.getRuntimeService()//
						.createExecutionQuery()//创建执行对象查询
						.processInstanceId(pi.getId())//使用流程实例ID查询
						.activityId("_4")//当前活动的id，对应receiveTask.bpmn文件中的活动节点id的属性值
						.singleResult();
		
		/**从流程变量中获取汇总当日销售额的值*/
		Integer value = (Integer)processEngine.getRuntimeService()
						.getVariable(execution2.getId(), "汇总当日销售额");
		System.out.println("给老板发送短信：金额是："+value);
		/**向后执行一步，使用trigger给流程引擎信号，告诉他当前任务已经完成了，可以往后执行，使得流程继续执行*/
		processEngine.getRuntimeService().trigger(execution1.getId());
						//.signal(execution2.getId()); //这个方法在activiti6 不存在了


		// 9查询流程状态
		ProcessInstance processInstance = processEngine.getRuntimeService()
				.createProcessInstanceQuery()
				.processInstanceId(pi.getId())
				.singleResult();
		if(processInstance==null){
			System.out.println("流程正常执行！！！，已经结束了");
		}
		
	}
	
	
}
