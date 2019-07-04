package com.simple.security.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {
	private Long id;
	private String username;
	private String password;
	private Integer status;
	private String descn;
}
