package com.gosun.shop.gosunactiviti.mapper;

import com.gosun.shop.gosunactiviti.domain.UserRoleRelation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserRoleRelationMapper {
    int insert(UserRoleRelation record);

    int insertSelective(UserRoleRelation record);

    List<UserRoleRelation> getUserRoleRelationList();
}