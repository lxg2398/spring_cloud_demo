package com.gosun.shop.gosunactiviti.common;

/**
 * 常用变量
 */
public class CommonVariable {

    /**
     * 请假类型
     */
    //带薪假
    public static final Byte PAID_LEAVE = 0;

    //病假
    public static final Byte SICK_LEAVE = 1;

    //事假
    public static final Byte LEAVE = 2;

    /**
     * 请假流程状态
     */
    //申请
    public static final Byte APPLICATION_STATUS = 0;

    //审批中
    public static final Byte APPROVAL_STATUS = 1;

    //已完成
    public static final Byte COMPLETE_STATUS = 2;

    /**
     * 节点审批状态
     */
    //不通过
    public static final Byte REFUSE_STATUS = 0;

    //通过
    public static final Byte PASS_STATUS = 1;

    /**
     * 返回值状态码
     */
    //成功
    public static final Integer SUCCESS_CODE = 200;

    //通过
    public static final Integer FAIL_CODE = 404;
}
