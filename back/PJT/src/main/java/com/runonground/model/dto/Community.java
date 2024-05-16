package com.runonground.model.dto;

public class Community {
	private int communityId;
	private int teamId;
	
	public Community() {}
	
	public Community(int communityId, int teamId) {
		this.communityId = communityId;
		this.teamId = teamId;
	}

	public int getCommunityId() {
		return communityId;
	}

	public void setCommunityId(int communityId) {
		this.communityId = communityId;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
}
