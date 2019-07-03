package com.simple.mvc.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class HelloDto implements Serializable {

	private Integer id;
	private String helloWorld;
//	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date helloDate;

}
