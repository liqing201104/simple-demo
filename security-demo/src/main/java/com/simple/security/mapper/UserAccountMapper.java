package com.simple.security.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.simple.security.domain.UserAccount;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserAccountMapper extends BaseMapper<UserAccount> {

}
