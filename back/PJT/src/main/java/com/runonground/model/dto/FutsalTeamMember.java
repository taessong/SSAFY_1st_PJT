package com.runonground.model.dto;

public class FutsalTeamMember {
	private int id;
	private int futsalTeamId;
	private String memberName;
	
	public FutsalTeamMember() {}

	public FutsalTeamMember(int id, int futsalTeamId, String memberName) {
		this.id = id;
		this.futsalTeamId = futsalTeamId;
		this.memberName = memberName;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
