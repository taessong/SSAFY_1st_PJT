package com.runonground.model.dto;

public class FutsalRecruitPost {
	private int recruitmentId;
	private int authorId;
	private int teamId;
	private String content;
	private int maxMembers;
	private String regDate;
	private boolean isFull;
	
	public FutsalRecruitPost() {}
	
	public FutsalRecruitPost(int recruitmentId, int authorId, int teamId, String content, int maxMembers,
			String regDate, boolean isFull) {
		this.recruitmentId = recruitmentId;
		this.authorId = authorId;
		this.teamId = teamId;
		this.content = content;
		this.maxMembers = maxMembers;
		this.regDate = regDate;
		this.isFull = isFull;
	}

	public int getRecruitmentId() {
		return recruitmentId;
	}

	public void setRecruitmentId(int recruitmentId) {
		this.recruitmentId = recruitmentId;
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

	public int getMaxMembers() {
		return maxMembers;
	}

	public void setMaxMembers(int maxMembers) {
		this.maxMembers = maxMembers;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public boolean isFull() {
		return isFull;
	}

	public void setFull(boolean isFull) {
		this.isFull = isFull;
	}
}
