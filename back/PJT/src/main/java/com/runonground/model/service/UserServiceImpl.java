package com.runonground.model.service;

import org.springframework.stereotype.Service;

import com.runonground.model.dao.UserDao;
import com.runonground.model.dto.User;

@Service
public class UserServiceImpl implements UserService{

	private UserDao userDao;
	
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}
	
	// 회원가입
	@Override
	public void signup(User user) {
		userDao.insertUser(user);
	}

	// 로그인
	@Override
	public User login(String userId, String password) {
		return userDao.selectOneUser(userId, password);
	}

}
