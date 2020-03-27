package com.altimetrik.bcp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "name")
	@JsonProperty
	private String name;
	@OneToOne(mappedBy = "account")
	@JsonIgnore
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
		builder.append("Account [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", projLocLeaderAssoc=");
		builder.append(projLocLeaderAssoc);
		builder.append("]");
		return builder.toString();
	}

	
}
