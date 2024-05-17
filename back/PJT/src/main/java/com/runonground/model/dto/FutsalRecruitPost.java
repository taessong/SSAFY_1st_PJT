package com.runonground.model.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class FutsalRecruitPost {
	private int recruitmentId;
	private String authorName;
	private String teamName;
	private String content;
	private int maxMembers;
	private LocalDateTime regDate;
	private boolean isFull;

	public FutsalRecruitPost() {
	}

	public FutsalRecruitPost(int recruitmentId, String authorName, String teamName, String content, int maxMembers,
			LocalDateTime regDate, boolean isFull) {
		this.recruitmentId = recruitmentId;
		this.authorName = authorName;
		this.teamName = teamName;
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

	public int getMaxMembers() {
		return maxMembers;
	}

	public void setMaxMembers(int maxMembers) {
		this.maxMembers = maxMembers;
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}

	public boolean isFull() {
		return isFull;
	}

	public void setFull(boolean isFull) {
		this.isFull = isFull;
	}

}
