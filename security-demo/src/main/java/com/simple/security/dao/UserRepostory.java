package com.simple.security.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.simple.security.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepostory extends BaseMapper<User> {

}
