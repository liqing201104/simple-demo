package com.simple.mvc.dto;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@ToString
public class HelloDto implements Serializable {

	private Integer id;
	private String helloWorld;
//	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date helloDate;

	private Boolean isUser;

	public void setId(Integer id) {
		this.id = id;
	}

	public void setHelloDate(Date helloDate) {
		this.helloDate = helloDate;
	}

	public void setHelloWorld(String helloWorld) {
		this.helloWorld = helloWorld;
	}

	public void setIsUser(Boolean user) {
		this.isUser = user;
	}
}
