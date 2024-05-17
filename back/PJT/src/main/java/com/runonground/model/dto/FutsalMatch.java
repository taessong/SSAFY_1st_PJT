package com.runonground.model.dto;

import java.time.LocalDateTime;

public class FutsalMatch {
	private int matchId;
	private String authorName;
	private int teamAId;
	private int teamBId;
	private LocalDateTime matchDate;
	private LocalDateTime matchTime;
	private int stadiumId;
	private LocalDateTime regDate;

	public FutsalMatch() {
	}

	public FutsalMatch(int matchId, String authorName, int teamAId, int teamBId, LocalDateTime matchDate,
			LocalDateTime matchTime, int stadiumId, LocalDateTime regDate) {
		this.matchId = matchId;
		this.authorName = authorName;
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

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
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

	public LocalDateTime getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(LocalDateTime matchDate) {
		this.matchDate = matchDate;
	}

	public LocalDateTime getMatchTime() {
		return matchTime;
	}

	public void setMatchTime(LocalDateTime matchTime) {
		this.matchTime = matchTime;
	}

	public int getStadiumId() {
		return stadiumId;
	}

	public void setStadiumId(int stadiumId) {
		this.stadiumId = stadiumId;
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}

}
