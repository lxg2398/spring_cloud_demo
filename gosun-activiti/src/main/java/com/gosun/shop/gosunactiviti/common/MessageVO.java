package com.gosun.shop.gosunactiviti.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回结果
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageVO {

    private Integer code;
    private String message = "success";
    private Object data;
}
