package com.runonground.model.dao;

<<<<<<< HEAD
public interface UserDao {
	
=======
import com.runonground.model.dto.User;

public interface UserDao {
	
	// 회원가입
	public void insertUser(User user);
	
	// 로그인
	public User selectOneUser(String userId, String password);
	
>>>>>>> 5e0d267082c08ca716f95682d6d232c6b20a8dae
}
