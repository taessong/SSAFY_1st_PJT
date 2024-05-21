package com.runonground.model.dto;

public class recommendations {
	private int id;
	private String userNickName;
	private int postId;
	private int commentId;
	
	public recommendations() {
		
	}
	
	public recommendations(int id, String userNickName, int postId, int commentId) {
		this.id = id;
		this.userNickName = userNickName;
		this.postId = postId;
		this.commentId = commentId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	
}
