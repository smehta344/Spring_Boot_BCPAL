package com.altimetrik.bcp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.altimetrik.bcp.entity.Project;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Integer> {
	
	List<Project> findByAccountId(int accountId);
	
	Project findByName(String name);
	
	List<Project> findAllByName(String name);
}
