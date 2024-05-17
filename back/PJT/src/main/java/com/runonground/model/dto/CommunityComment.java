package com.runonground.model.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class CommunityComment {
	private int commentId;
	private int postId;
	private String authorName;
	private String content;
	private int recommend;
	private LocalDateTime regDate;
	
	public CommunityComment() {}

	public CommunityComment(int commentId, int postId, String authorName, String content, int recommend, LocalDateTime regDate) {
		this.commentId = commentId;
		this.postId = postId;
		this.authorName = authorName;
		this.content = content;
		this.recommend = recommend;
		this.regDate = regDate;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}


	
	
}
