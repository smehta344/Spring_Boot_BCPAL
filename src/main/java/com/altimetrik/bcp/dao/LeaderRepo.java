package com.altimetrik.bcp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.altimetrik.bcp.entity.Leader;
import com.altimetrik.bcp.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaderRepo extends JpaRepository<Leader, Integer> {

}
