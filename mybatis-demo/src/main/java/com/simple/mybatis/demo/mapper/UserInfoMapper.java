package com.simple.mybatis.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.simple.mybatis.demo.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

	Integer insert(UserInfo userInfo);

	Integer insert2(@Param("userInfo") UserInfo userInfo);
}
