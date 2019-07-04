package com.simple.security.service;

import com.simple.security.dao.UserRepostory;
import com.simple.security.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class UserService {

	@Autowired
	private UserRepostory userRepostory;

	public String register(String userName, String password) {
		User user = new User();

		if (exist(userName)) {
			throw new RuntimeException("用户名已存在！");
		}
		// 随机使用加密方式
		Random random = new Random();
		int x = random.nextInt(5);
		String encoderType = ENCODER_TYPE.get(x);
		PasswordEncoder passwordEncoder = ENCODER_MAP.get(encoderType);
		user.setUsername(userName);
		user.setPassword(String.format(PASSWORD_FORMAT, encoderType, passwordEncoder.encode(password)));


		userRepostory.insert(user);

		return user.getId() + "";
	}

	private final static Map<Integer, String> ENCODER_TYPE = new HashMap<>();

	private final static Map<String, PasswordEncoder> ENCODER_MAP = new HashMap<>();

	private final static String PASSWORD_FORMAT = "{%s}%s";

	static {
		ENCODER_TYPE.put(0, "noop");
		ENCODER_TYPE.put(1, "bcrypt");
		ENCODER_TYPE.put(2, "pbkdf2");
		ENCODER_TYPE.put(3, "scrypt");
		ENCODER_TYPE.put(4, "sha256");
		ENCODER_MAP.put("noop", NoOpPasswordEncoder.getInstance());
		ENCODER_MAP.put("bcrypt", new BCryptPasswordEncoder());
		ENCODER_MAP.put("pbkdf2", new Pbkdf2PasswordEncoder());
		ENCODER_MAP.put("scrypt", new SCryptPasswordEncoder());
		ENCODER_MAP.put("sha256", new StandardPasswordEncoder());
	}


	/**
	 * 判断用户是否存在
	 */
	private boolean exist(String username) {
		Map<String, Object> map = new HashMap<>();
		map.put("username", username);
		List<User> userDO = userRepostory.selectByMap(map);
		return (userDO != null && userDO.size() > 0);
	}
}
