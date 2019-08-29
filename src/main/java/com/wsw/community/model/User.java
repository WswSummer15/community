package com.wsw.community.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户登录信息
 */
@Setter
@Getter
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
}
