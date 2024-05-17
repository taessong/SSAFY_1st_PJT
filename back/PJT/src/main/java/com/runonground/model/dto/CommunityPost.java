package com.runonground.model.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class CommunityPost {
	private int postId;
	private String authorName;
	private String teamName;
	private String content;
	private String postImgPath;
	private LocalDateTime regDate;
	
	public CommunityPost() {}

	public CommunityPost(int postId, String authorName, String teamName, String content, String postImgPath,
			LocalDateTime regDate) {
		this.postId = postId;
		this.authorName = authorName;
		this.teamName = teamName;
		this.content = content;
		this.postImgPath = postImgPath;
		this.regDate = regDate;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPostImgPath() {
		return postImgPath;
	}

	public void setPostImgPath(String postImgPath) {
		this.postImgPath = postImgPath;
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}


}
