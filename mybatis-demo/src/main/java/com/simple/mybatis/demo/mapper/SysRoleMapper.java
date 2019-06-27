package com.simple.mybatis.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.simple.mybatis.demo.domain.SysRole;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

	@Insert("insert into sys_role(role_name) values (#{roleName})")
	@Options(useGeneratedKeys = true, keyProperty = "roleId", keyColumn = "role_id")
	Integer insert(SysRole sysRole);

	@Insert("insert into sys_role(role_name) values (#{sysRole.roleName})")
	@Options(useGeneratedKeys = true, keyProperty = "roleId", keyColumn = "role_id")
	Integer insert2(@Param("sysRole") SysRole sysRole);
}
