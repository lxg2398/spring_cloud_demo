package com.gosun.shop.gosunactiviti.testactiviti.servicetask;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class ServiceTaskAutomatic implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("---------------------------------------------");
        System.out.println();
        System.out.println("Hello Service " + this.toString()
                + "Is Saying Hello To Every One !");
        System.out.println("---------------------------------------------");
        System.out.println();

    }
}
