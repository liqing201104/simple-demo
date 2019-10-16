package com.simple.mybatis.demo.mapper;

import com.simple.mybatis.demo.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

	@Select("select id, name, create_dttm createDttm, create_dttm createDttm2 from user where id = #{id}")
	User queryById(@Param("id") Long id);

}
