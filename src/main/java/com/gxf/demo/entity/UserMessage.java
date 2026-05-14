package com.gxf.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户消息实体类，用于演示对象消息发送
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private String username;

    private String content;

    private Date createTime;
}