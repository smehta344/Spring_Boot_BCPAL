package com.altimetrik.bcp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.altimetrik.bcp.model.BusinessContinuityPlan;

@Repository
public interface BcpRepo extends JpaRepository<BusinessContinuityPlan, Integer> {

//@Query(value = "UPDATE BusinessContinuityPlan bc SET ")	

}
