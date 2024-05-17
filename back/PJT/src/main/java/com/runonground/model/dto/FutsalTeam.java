package com.runonground.model.dto;

public class FutsalTeam {
	private int futsalTeamId;
	private int recruitmentId;
	private String leaderName;
	
	public FutsalTeam() {}

	public FutsalTeam(int futsalTeamId, int recruitmentId, String leaderName) {
		this.futsalTeamId = futsalTeamId;
		this.recruitmentId = recruitmentId;
		this.leaderName = leaderName;
	}

	public int getFutsalTeamId() {
		return futsalTeamId;
	}

	public void setFutsalTeamId(int futsalTeamId) {
		this.futsalTeamId = futsalTeamId;
	}

	public int getRecruitmentId() {
		return recruitmentId;
	}

	public void setRecruitmentId(int recruitmentId) {
		this.recruitmentId = recruitmentId;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}
	

}
