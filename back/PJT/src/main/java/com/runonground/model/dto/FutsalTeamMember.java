package com.runonground.model.dto;

public class FutsalTeamMember {
	private int futsalTeamId;
	private int memberId;
	
	public FutsalTeamMember() {}
	
	public FutsalTeamMember(int futsalTeamId, int memberId) {
		this.futsalTeamId = futsalTeamId;
		this.memberId = memberId;
	}

	public int getFutsalTeamId() {
		return futsalTeamId;
	}

	public void setFutsalTeamId(int futsalTeamId) {
		this.futsalTeamId = futsalTeamId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
}
