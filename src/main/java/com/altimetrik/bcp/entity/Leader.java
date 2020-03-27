package com.altimetrik.bcp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "leader")
public class Leader {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "name")
	private String name;
	@OneToOne(mappedBy = "leader")
	private ProjLocLeaderAssoc projLocLeaderAssoc;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ProjLocLeaderAssoc getProjLocLeaderAssoc() {
		return projLocLeaderAssoc;
	}
	public void setProjLocLeaderAssoc(ProjLocLeaderAssoc projLocLeaderAssoc) {
		this.projLocLeaderAssoc = projLocLeaderAssoc;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Leader [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", projLocLeaderAssoc=");
		builder.append(projLocLeaderAssoc);
		builder.append("]");
		return builder.toString();
	}

	
}
