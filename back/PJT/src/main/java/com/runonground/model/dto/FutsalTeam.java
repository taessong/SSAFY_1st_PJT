package com.runonground.model.dto;

public class FutsalTeam {
	private int futsalTeamId;
	private int recruitmentId;
	private int leaderId;
	
	public FutsalTeam() {}
	
	public FutsalTeam(int futsalTeamId, int recruitmentId, int leaderId) {
		this.futsalTeamId = futsalTeamId;
		this.recruitmentId = recruitmentId;
		this.leaderId = leaderId;
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

	public int getLeaderId() {
		return leaderId;
	}

	public void setLeaderId(int leaderId) {
		this.leaderId = leaderId;
	}
}
