package com.altimetrik.bcp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.altimetrik.bcp.entity.Leader;
import com.altimetrik.bcp.entity.Location;
import com.altimetrik.bcp.entity.ProjLocLeaderAssoc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectAssocRepo extends JpaRepository<ProjLocLeaderAssoc, Integer> {

}
