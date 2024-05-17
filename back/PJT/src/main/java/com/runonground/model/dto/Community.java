package com.runonground.model.dto;

public class Community {
	private int communityId;
	private String teamName;
	
	public Community() {}

	public Community(int communityId, String teamName) {
		this.communityId = communityId;
		this.teamName = teamName;
	}

	public int getCommunityId() {
		return communityId;
	}

	public void setCommunityId(int communityId) {
		this.communityId = communityId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
}
