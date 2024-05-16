package com.runonground.model.dto;

public class CommunityComment {
	private int commentId;
	private int postId;
	private int authorId;
	private String content;
	private String regDate;
	
	public CommunityComment() {}
	
	public CommunityComment(int commentId, int postId, int authorId, String content, String regDate) {
		this.commentId = commentId;
		this.postId = postId;
		this.authorId = authorId;
		this.content = content;
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

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
}
