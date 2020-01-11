package com.gosun.shop.activiti.junit;


import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;

public class TestActiviti {

    /**
     * 方式一：手动设置创建信息
     * 创建流程引擎，以及创建工作流的28张数据表
     */
    @Test
    public void createTable(){

        ProcessEngineConfiguration sopec = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();

        //设置数据库连接信息
        sopec.setJdbcDriver("com.mysql.jdbc.Driver");
        sopec.setJdbcUrl("jdbc:mysql://localhost:3306/test_activiti?useSSL=false");
        sopec.setJdbcUsername("root");
        sopec.setJdbcPassword("123");

        /**
         * public static final String DB_SCHEMA_UPDATE_FALSE = "false";表示不自动创建，手动创建需要的表，或者表已经存在，不需要创了
         * public static final String DB_SCHEMA_UPDATE_CREATE_DROP = "create-drop";先删除表，在创建表
         * public static final String DB_SCHEMA_UPDATE_TRUE = "true";表不存在，自动创建表
         */

        //设置工作流程表的创建策略
        sopec.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        //创建流程引擎
        ProcessEngine processEngine = sopec.buildProcessEngine();

        System.out.println("processEngine:" + processEngine);


    }


    /**
     * 方式二：配置文件读取创建信息
     * 创建流程引擎，以及创建工作流的28张数据表
     */
    @Test
    public void createTable_2(){
       /*
        ProcessEngineConfiguration sopec = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");

        //创建流程引擎
        ProcessEngine processEngine = sopec.buildProcessEngine();
        */

        ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml").buildProcessEngine();

        System.out.println("processEngine:" + processEngine);

    }

    /**
     * 方式三：配置文件读取创建信息
     * 创建流程引擎，以及创建工作流的28张数据表
     */
    @Test
    public void createTable_3(){

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();//默认去加载resources下activiti.cfg.xml文件
        System.out.println("processEngine:" + processEngine);

    }
}
