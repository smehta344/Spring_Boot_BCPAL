package com.altimetrik.bcp.model;

public class Actual {

	private int id;
	private int actualTraget;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getActualTraget() {
		return actualTraget;
	}

	public void setActualTraget(int actualTraget) {
		this.actualTraget = actualTraget;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Actual [id=");
		builder.append(id);
		builder.append(", actualTraget=");
		builder.append(actualTraget);
		builder.append("]");
		return builder.toString();
	}

}
