package com.altimetrik.bcp.model;

public class TeamLogged {

	private int id;
	private String Name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TeamLogged [id=");
		builder.append(id);
		builder.append(", Name=");
		builder.append(Name);
		builder.append("]");
		return builder.toString();
	}

}
