package com.runonground.model.dto;

public class FutsalTeamMember {
	private int futsalTeamId;
	private String memberName;
	
	public FutsalTeamMember() {}

	public FutsalTeamMember(int futsalTeamId, String memberName) {
		this.futsalTeamId = futsalTeamId;
		this.memberName = memberName;
	}

	public int getFutsalTeamId() {
		return futsalTeamId;
	}

	public void setFutsalTeamId(int futsalTeamId) {
		this.futsalTeamId = futsalTeamId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
	
}
