package com.altimetrik.bcp.model;

public class Target {

	private int id;
	private int achivedTraget;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAchivedTraget() {
		return achivedTraget;
	}

	public void setAchivedTraget(int achivedTraget) {
		this.achivedTraget = achivedTraget;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Target [id=");
		builder.append(id);
		builder.append(", achivedTraget=");
		builder.append(achivedTraget);
		builder.append("]");
		return builder.toString();
	}

}
