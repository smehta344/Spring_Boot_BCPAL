package com.altimetrik.bcp.model;

public class Team {

	private int id;
	private int teamSize;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTeamSize() {
		return teamSize;
	}

	public void setTeamSize(int teamSize) {
		this.teamSize = teamSize;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Team [id=");
		builder.append(id);
		builder.append(", teamSize=");
		builder.append(teamSize);
		builder.append("]");
		return builder.toString();
	}

}
