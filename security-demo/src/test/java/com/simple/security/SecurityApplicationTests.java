package com.simple.security;

import com.simple.security.dao.UserRepostory;
import com.simple.security.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SecurityApplication.class)
public class SecurityApplicationTests {

	@Autowired
	private UserRepostory userRepostory;

	@Test
	public void contextLoads() {
		User user = userRepostory.selectById(1);
		System.out.println(user);
	}

}
