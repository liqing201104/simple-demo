package com.simple.mybatis.demo.domain;

import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.Date;

@Data
@ToString
public class User {
	private Long id;
	private String name;
	private Date createDttm;
	private Timestamp createDttm2;
}
