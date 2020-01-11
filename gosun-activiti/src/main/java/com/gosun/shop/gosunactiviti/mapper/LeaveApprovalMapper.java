package com.gosun.shop.gosunactiviti.mapper;

import com.gosun.shop.gosunactiviti.domain.LeaveApproval;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LeaveApprovalMapper {
    int deleteByPrimaryKey(String id);

    int insert(LeaveApproval record);

    int insertSelective(LeaveApproval record);

    LeaveApproval selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LeaveApproval record);

    int updateByPrimaryKey(LeaveApproval record);
}