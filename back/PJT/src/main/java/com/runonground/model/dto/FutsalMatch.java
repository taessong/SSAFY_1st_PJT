package com.runonground.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class FutsalMatch {
	private int matchId;
	private String authorName;
	private int teamAId;
	private int teamBId;
	private String teamAName;
	private String teamBName;
	private String teamBLeader;
	private String content;
	private LocalDate matchDate;
	private LocalTime matchTime;
	private int stadiumId;
	private LocalDateTime regDate;
	private String stadiumImg;

	public FutsalMatch() {
	}

	public FutsalMatch(int matchId, String authorName, int teamAId, int teamBId, String teamAName, String teamBName, String teamBLeader, String content, LocalDate matchDate,
			LocalTime matchTime, int stadiumId, LocalDateTime regDate, String stadiumImg) {
		this.matchId = matchId;
		this.authorName = authorName;
		this.teamAId = teamAId;
		this.teamBId = teamBId;
		this.teamAName = teamAName;
		this.teamBName = teamBName;
		this.teamBLeader = teamBLeader;
		this.content = content;
		this.matchDate = matchDate;
		this.matchTime = matchTime;
		this.stadiumId = stadiumId;
		this.regDate = regDate;
		this.stadiumImg = stadiumImg;
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
	
	public String getTeamAName() {
		return teamAName;
	}

	public void setTeamAName(String teamAName) {
		this.teamAName = teamAName;
	}

	public String getTeamBName() {
		return teamBName;
	}

	public void setTeamBName(String teamBName) {
		this.teamBName = teamBName;
	}
	
	public String getTeamBLeader() {
		return teamBLeader;
	}

	public void setTeamBLeader(String teamBLeader) {
		this.teamBLeader = teamBLeader;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDate getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(LocalDate matchDate) {
		this.matchDate = matchDate;
	}

	public LocalTime getMatchTime() {
		return matchTime;
	}

	public void setMatchTime(LocalTime matchTime) {
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

	public String getStadiumImg() {
		return stadiumImg;
	}

	public void setStadiumImg(String stadiumImg) {
		this.stadiumImg = stadiumImg;
	}
}
