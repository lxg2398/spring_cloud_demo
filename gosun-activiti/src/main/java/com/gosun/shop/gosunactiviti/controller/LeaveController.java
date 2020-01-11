package com.gosun.shop.gosunactiviti.controller;

import com.alibaba.fastjson.JSON;
import com.gosun.shop.gosunactiviti.common.CommonVariable;
import com.gosun.shop.gosunactiviti.common.MessageVO;
import com.gosun.shop.gosunactiviti.domain.LeaveApplication;
import com.gosun.shop.gosunactiviti.service.LeaveService;
import com.gosun.shop.gosunactiviti.utils.ActivitiUtil;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 请假接口
 */
@RestController
@RequestMapping("leave")
public class LeaveController {


    @Autowired(required = false)
    private LeaveService leaveService;

    @Autowired(required = false)
    private RuntimeService runtimeService;

    @Autowired(required = false)
    private TaskService taskService;

    @Autowired(required = false)
    private MessageVO messageVO;


    /**
     * 提交请假申请
     * @param leaveApplication
     * @return
     */
    @PostMapping("addLeave")
    public String addLeave (@RequestBody LeaveApplication leaveApplication){
        //根据用户id获取用户名称
        String userName = leaveService.getUserNameById(leaveApplication.getUserId());
        //存入Map集合中
        Map<String, Object> varUserName = new HashMap<String, Object>();
        varUserName.put("userName",userName);
        //获取key启动流程
        String key = leaveApplication.getClass().getSimpleName();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key,varUserName);
        //获取流程实例id
        String pii = processInstance.getProcessInstanceId();
        leaveApplication.setProcessInstanceId(pii);
        //通过uuid生成请假单id
        String id = UUID.randomUUID().toString();
        leaveApplication.setId(id);
        //设置流程状态
        leaveApplication.setProcessStatus(CommonVariable.APPROVAL_STATUS);
        //设置创建时间及更新时间
        Date date = new Date();
        leaveApplication.setCreateTime(date);
        leaveApplication.setUpdateTime(date);
        //将返回值转为json
        MessageVO messageVO = leaveService.addLeave(leaveApplication);
        String s = JSON.toJSONString(messageVO);
        return s;
    }

    /**
     * 发起人审核
     * @param id
     * @return
     */
    @GetMapping("sponsorReview")
    public String sponsorReview(String id){
         //根据请假单id获取流程实例id以及请假天数
        LeaveApplication leaveApplication = leaveService.getLeaveApplication(id);
        //存入Map集合中
        Map<String, Object> varLeaveDays = new HashMap<String, Object>();
        varLeaveDays.put("leaveDays",leaveApplication.getLeaveDays());
        //获取当前任务对象
        Task task = taskService.createTaskQuery().processInstanceId(leaveApplication.getProcessInstanceId()).active().singleResult();
        System.out.println("任务ID："+task.getId());
        System.out.println("任务名称："+task.getName());
        System.out.println("任务创建时间："+task.getCreateTime());
        System.out.println("任务ID："+task.getAssignee());
        System.out.println("流程实例ID："+task.getProcessInstanceId());
        System.out.println("执行对象ID："+task.getExecutionId());
        System.out.println("流程定义ID："+task.getProcessDefinitionId());

        System.out.println("==============================================");
        try {
            taskService.complete(task.getId(),varLeaveDays);
            messageVO.setCode(CommonVariable.SUCCESS_CODE);
            String s = JSON.toJSONString(messageVO);
            return s;
        } catch (Exception e){
            messageVO.setCode(CommonVariable.FAIL_CODE);
            messageVO.setMessage(e.getMessage());
            String s = JSON.toJSONString(messageVO);
            return s;
        }
    }


    /**
     * 部门经理审核
     * @param id
     * @return
     */
    @GetMapping("departmentManagerReview")
    public String departmentManagerReview(String id){
        return completeTask(id);

    }

    /**
     * 总经理审核
     * @param id
     * @return
     */
    @GetMapping("generalManagerReview")
    public String generalManagerReview(String id){

        //根据请假单id获取流程实例id
        return completeTask(id);

    }


    /**
     * 人事审核
     * @param id
     * @return
     */
    @GetMapping("personnelReview")
    public String personnelReview(String id){

        //根据请假单id获取流程实例id
        return completeTask(id);

    }

    private String completeTask(String id) {
        //根据请假单id获取流程实例id
        LeaveApplication leaveApplication = leaveService.getLeaveApplication(id);

        //获取当前任务对象
        Task task = taskService.createTaskQuery().processInstanceId(leaveApplication.getProcessInstanceId()).active().singleResult();
        System.out.println("任务ID：" + task.getId());
        System.out.println("任务名称：" + task.getName());
        System.out.println("任务创建时间：" + task.getCreateTime());
        System.out.println("任务ID：" + task.getAssignee());
        System.out.println("流程实例ID：" + task.getProcessInstanceId());
        System.out.println("执行对象ID：" + task.getExecutionId());
        System.out.println("流程定义ID：" + task.getProcessDefinitionId());

        System.out.println("==============================================");

        try {
            taskService.complete(task.getId());
            messageVO.setCode(CommonVariable.SUCCESS_CODE);
            String s = JSON.toJSONString(messageVO);
            return s;
        } catch (Exception e) {
            messageVO.setCode(CommonVariable.FAIL_CODE);
            messageVO.setMessage(e.getMessage());
            String s = JSON.toJSONString(messageVO);
            return s;
        }
    }
}
