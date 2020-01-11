package com.gosun.shop.gosunactiviti.service.impl;

import com.gosun.shop.gosunactiviti.common.CommonVariable;
import com.gosun.shop.gosunactiviti.common.MessageVO;
import com.gosun.shop.gosunactiviti.domain.LeaveApplication;
import com.gosun.shop.gosunactiviti.domain.Role;
import com.gosun.shop.gosunactiviti.domain.User;
import com.gosun.shop.gosunactiviti.domain.UserRoleRelation;
import com.gosun.shop.gosunactiviti.mapper.LeaveApplicationMapper;
import com.gosun.shop.gosunactiviti.mapper.RoleMapper;
import com.gosun.shop.gosunactiviti.mapper.UserMapper;
import com.gosun.shop.gosunactiviti.mapper.UserRoleRelationMapper;
import com.gosun.shop.gosunactiviti.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveServiceImpl implements LeaveService {


    @Autowired(required = false)
    private RoleMapper roleMapper;

    @Autowired(required = false)
    private UserRoleRelationMapper userRoleRelationMapper;

    @Autowired(required = false)
    private LeaveApplicationMapper leaveApplicationMapper;

    @Autowired(required = false)
    private MessageVO messageVO;

    @Autowired(required = false)
    private UserMapper userMapper;



    /**
     * 新增请假单
     * @param leaveApplication
     */
    @Override
    public MessageVO addLeave(LeaveApplication leaveApplication) {
        try {
            leaveApplicationMapper.insert(leaveApplication);
            messageVO.setCode(CommonVariable.SUCCESS_CODE);
            messageVO.setData(leaveApplication.getId());
            return messageVO;
        }catch (Exception e){
           messageVO.setCode(CommonVariable.FAIL_CODE);
           messageVO.setMessage(e.getMessage());
           return messageVO;
        }

    }

    /**
     * 获取所有角色
     * @return
     */
    @Override
    public List<Role> getRoleList() {
        return roleMapper.getRoleList();
    }

    /**
     * 查询角色用户关联关系表，获取所有关联关系
     * @return
     */
    @Override
    public List<UserRoleRelation> getUserRoleRelationList() {
        return userRoleRelationMapper.getUserRoleRelationList();
    }

    /**
     * 根据id获取姓名
     * @param userId
     * @return
     */
    @Override
    public String getUserNameById(Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user != null){
            return user.getName();
        }else {
            return null;
        }
    }

    /**
     * 根据请假单id获取流程实例id以及请假天数
     * @param id
     * @return
     */
    @Override
    public LeaveApplication getLeaveApplication(String id) {
        return leaveApplicationMapper.selectByPrimaryKey(id);
    }
}
