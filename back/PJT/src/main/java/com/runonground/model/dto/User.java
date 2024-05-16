package com.runonground.model.dto;

public class User {
	private int userId;
	private String userName;
	private String password;
	private String nickname;
	private int teamId;
	
	public User() {}
	
	public User(int userId, String userName, String password, String nickname, int teamId) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.nickname = nickname;
		this.teamId = teamId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getPerferredTeamId() {
		return teamId;
	}

	public void setPerferredTeamId(int teamId) {
		this.teamId = teamId;
	}
}
