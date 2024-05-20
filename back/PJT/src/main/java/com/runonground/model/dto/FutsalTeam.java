package com.runonground.model.dto;

public class FutsalTeam {
	private int futsalTeamId;
	private String leaderName;
	
	public FutsalTeam() {}

	public FutsalTeam(int futsalTeamId, String leaderName) {
		this.futsalTeamId = futsalTeamId;
		this.leaderName = leaderName;
	}

	public int getFutsalTeamId() {
		return futsalTeamId;
	}

	public void setFutsalTeamId(int futsalTeamId) {
		this.futsalTeamId = futsalTeamId;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}
	

}
