package com.gosun.shop.gosunactiviti.service;

import com.gosun.shop.gosunactiviti.common.MessageVO;
import com.gosun.shop.gosunactiviti.domain.LeaveApplication;
import com.gosun.shop.gosunactiviti.domain.Role;
import com.gosun.shop.gosunactiviti.domain.UserRoleRelation;

import java.util.List;

public interface LeaveService {
    //新增请假单
    MessageVO addLeave(LeaveApplication leaveApplication);
    //获取所有角色
    List<Role> getRoleList();
    //查询角色用户关联关系表，获取所有关联关系
    List<UserRoleRelation> getUserRoleRelationList();
    //根据ID获取用户姓名
    String getUserNameById(Integer userId);
    //根据请假单id获取流程实例id以及请假天数
    LeaveApplication getLeaveApplication(String id);
}
