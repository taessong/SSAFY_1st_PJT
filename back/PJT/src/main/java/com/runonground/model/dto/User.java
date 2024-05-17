package com.runonground.model.dto;

public class User {
	private String nickName;
	private String userId;
	private String password;
	private String realName;
	private String teamName;
	private String userImgPath;

	public User() {}

	public User(String nickName, String userId, String password, String realName, String teamName, String userImgPath) {
		this.nickName = nickName;
		this.userId = userId;
		this.password = password;
		this.realName = realName;
		this.teamName = teamName;
		this.userImgPath = userImgPath;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getUserImgPath() {
		return userImgPath;
	}

	public void setUserImgPath(String userImgPath) {
		this.userImgPath = userImgPath;
	}

}
