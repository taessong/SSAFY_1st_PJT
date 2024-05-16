package com.runonground.model.dto;

public class Stadium {
	private int stadiumId;
	private String stadiumName;
	private String location;

	public Stadium() {}
	
	public Stadium(int stadiumId, String stadiumName, String location) {
		this.stadiumId = stadiumId;
		this.stadiumName = stadiumName;
		this.location = location;
	}

	public int getStadiumId() {
		return stadiumId;
	}

	public void setStadiumId(int stadiumId) {
		this.stadiumId = stadiumId;
	}

	public String getStadiumName() {
		return stadiumName;
	}

	public void setStadiumName(String stadiumName) {
		this.stadiumName = stadiumName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
