package com.altimetrik.bcp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.altimetrik.bcp.entity.ProjLocLeaderAssoc;

@Repository
public interface ProjectAssocRepo extends JpaRepository<ProjLocLeaderAssoc, Integer> {

}
