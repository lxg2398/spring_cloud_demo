package com.gosun.shop.gosunactiviti.init;

import com.gosun.shop.gosunactiviti.domain.Role;
import com.gosun.shop.gosunactiviti.domain.UserRoleRelation;
import com.gosun.shop.gosunactiviti.service.LeaveService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

/**
 * 初始化部署流程和角色及用户
 */
@Component
public class ActivitiInit implements ApplicationRunner {

    @Autowired(required = false)
    private LeaveService leaveService;

    @Autowired(required = false)
    private RepositoryService repositoryService;

    @Autowired(required = false)
    private IdentityService identityService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //获取流程引擎
        //ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //部署流程
        //获取输入流
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("leaveprocess/leaveProcess.zip");
        ZipInputStream zipInputStream = new ZipInputStream(in);
        Deployment deploy = //processEngines.getRepositoryService()//获取与流程定义和部署对象相关的Service。
                repositoryService.createDeployment()//创建一个部署对象
                .name("leaveProcess")//设置部署名称
                .addZipInputStream(zipInputStream)//通过zip输入流加载要部署的流程定义信息
                .deploy();//完成部署
        System.out.println("部署流程ID："+deploy.getId());

        //创建角色
        //查询角色表，获取所有角色
       List<Role> roleList = leaveService.getRoleList();
       roleList.forEach(role -> {
            Group group = identityService.newGroup(role.getId() + "");
            group.setName(role.getRoleName());
            identityService.saveGroup(group);
       });

        //查询角色用户关联关系表，获取所有关联关系
        List<UserRoleRelation> userRoleRelationList = leaveService.getUserRoleRelationList();
        //创建用户,建立用户和角色的关联关系
        userRoleRelationList.forEach(userRoleRelation -> {
            identityService.saveUser(identityService.newUser(userRoleRelation.getUserId()+""));
            identityService.createMembership(userRoleRelation.getUserId()+"", userRoleRelation.getRoleId()+"");
        });


        System.out.println("添加组织机构成功");
    }
}
