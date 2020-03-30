package com.altimetrik.bcp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.altimetrik.bcp.entity.Project;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Integer> {

//@Query(value = "UPDATE BusinessContinuityPlan bc SET ")	

}
