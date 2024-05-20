package com.runonground.model.dto;

public class FutsalTeam {
	private int teamId;
	private String leaderName;
	private String teamName;
	
	public FutsalTeam() {}

	public FutsalTeam(int teamId, String leaderName, String teamName) {
		this.teamId = teamId;
		this.leaderName = leaderName;
		this.teamName = teamName;
	}

	public int getFutsalTeamId() {
		return teamId;
	}

	public void setFutsalTeamId(int teamId) {
		this.teamId = teamId;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
}
