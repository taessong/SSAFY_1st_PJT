package com.runonground.model.service;

import com.runonground.model.dto.User;

public interface UserService {

	// 회원가입
	public void signup(User user);
	
	// 로그인
	public User login(String userId, String password);
}
