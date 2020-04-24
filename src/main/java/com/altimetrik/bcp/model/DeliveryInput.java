package com.altimetrik.bcp.model;

import com.altimetrik.bcp.entity.Leader;
import com.altimetrik.bcp.entity.Location;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeliveryInput {

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Location location;

	private Leader leader;

	private int teamSize;

	public int getTeamSize() {
		return teamSize;
	}

	public void setTeamSize(int teamSize) {
		this.teamSize = teamSize;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Leader getLeader() {
		return leader;
	}

	public void setLeader(Leader leader) {
		this.leader = leader;
	}

}
