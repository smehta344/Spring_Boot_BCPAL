package com.altimetrik.bcp.model;

import com.altimetrik.bcp.entity.Leader;
import com.altimetrik.bcp.entity.Location;

public class DeliveryInput {
	Location location;
	Leader leader;
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
