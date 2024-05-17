package com.runonground.model.dao;

import com.runonground.model.dto.User;

public interface UserDao {
	
	// 회원가입
	public void insertUser(User user);
	
	// 로그인
	public User selectOneUser(String userId, String password);
	
}
