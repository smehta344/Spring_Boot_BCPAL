package com.altimetrik.bcp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.altimetrik.bcp.entity.Leader;

@Repository
public interface LeaderRepo extends JpaRepository<Leader, Integer> {

}
