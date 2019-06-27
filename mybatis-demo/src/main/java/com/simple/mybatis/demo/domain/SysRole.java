package com.simple.mybatis.demo.domain;

public class SysRole {

	private Long roleId;
	private String roleName;

	public SysRole(String roleName){
		this.roleName = roleName;
	}

	public Long getRoleId() {
		return roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
