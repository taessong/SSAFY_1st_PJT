package com.runonground.model.dto;

public class FutsalMatch {
	private int matchId;
	private int authorId;
	private int teamAId;
	private int teamBId;
	private int matchDate;
	private int matchTime;
	private int stadiumId;
	private String regDate;

	public FutsalMatch() {}
	
	public FutsalMatch(int matchId, int authorId, int teamAId, int teamBId, int matchDate, int matchTime, int stadiumId,
			String regDate) {
		this.matchId = matchId;
		this.authorId = authorId;
		this.teamAId = teamAId;
		this.teamBId = teamBId;
		this.matchDate = matchDate;
		this.matchTime = matchTime;
		this.stadiumId = stadiumId;
		this.regDate = regDate;
	}

	public int getMatchId() {
		return matchId;
	}

	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public int getTeamAId() {
		return teamAId;
	}

	public void setTeamAId(int teamAId) {
		this.teamAId = teamAId;
	}

	public int getTeamBId() {
		return teamBId;
	}

	public void setTeamBId(int teamBId) {
		this.teamBId = teamBId;
	}

	public int getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(int matchDate) {
		this.matchDate = matchDate;
	}

	public int getMatchTime() {
		return matchTime;
	}

	public void setMatchTime(int matchTime) {
		this.matchTime = matchTime;
	}

	public int getStadiumId() {
		return stadiumId;
	}

	public void setStadiumId(int stadiumId) {
		this.stadiumId = stadiumId;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
}
