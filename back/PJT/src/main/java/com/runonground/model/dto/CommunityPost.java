package com.runonground.model.dto;

public class CommunityPost {
	private int postId;
	private int authorId;
	private int teamId;
	private String content;
	private String imageUrl;
	private String regDate;
	
	public CommunityPost() {}
	
	public CommunityPost(int postId, int authorId, int teamId, String content, String imageUrl, String regDate) {
		this.postId = postId;
		this.authorId = authorId;
		this.teamId = teamId;
		this.content = content;
		this.imageUrl = imageUrl;
		this.regDate = regDate;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
}
