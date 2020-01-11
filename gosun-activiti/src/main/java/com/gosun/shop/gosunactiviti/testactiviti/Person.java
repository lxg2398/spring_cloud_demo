package com.gosun.shop.gosunactiviti.testactiviti;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Administrator
 */
@Data
public class Person implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6757393795687480331L;
	
	private Integer id;//编号
	private String name;//姓名
	
	private String education;//学历

}
