package com.altimetrik.bcp.model;

public class KeyDeliverablesOfTheDay {

	private int id;
	private int noOfKey;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNoOfKey() {
		return noOfKey;
	}

	public void setNoOfKey(int noOfKey) {
		this.noOfKey = noOfKey;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("KeyDeliverablesOfTheDay [id=");
		builder.append(id);
		builder.append(", noOfKey=");
		builder.append(noOfKey);
		builder.append("]");
		return builder.toString();
	}

}
