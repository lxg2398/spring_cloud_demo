package com.gosun.shop.gosunredis.model;

import lombok.Data;


/**
 * @Description TODO
 * @Author lxg
 * @Date 2019/10/25
 */
@Data
public class Person {
    private String name;
    private int age;

    public Person(String name,int age){
        this.name = name;
        this.age = age;
    }

}
