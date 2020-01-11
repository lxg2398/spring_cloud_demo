package com.gosun.shop.gosunactiviti.mapper;

import com.gosun.shop.gosunactiviti.domain.LeaveApplication;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LeaveApplicationMapper {
    int deleteByPrimaryKey(String id);

    int insert(LeaveApplication record);

    int insertSelective(LeaveApplication record);

    LeaveApplication selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LeaveApplication record);

    int updateByPrimaryKey(LeaveApplication record);

}