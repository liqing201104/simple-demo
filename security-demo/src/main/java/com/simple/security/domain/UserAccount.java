package com.simple.security.domain;

import lombok.Data;

import java.util.Date;

@Data
public class UserAccount {

    private String userId;
    private String loginName;
    private String password;
    private String token;
    private Integer loginMode;
    private Date ctime;
    private Date etime;
    private String cuser;
    private String euser;
    private Integer isDel;

}
