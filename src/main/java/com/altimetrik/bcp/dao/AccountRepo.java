package com.altimetrik.bcp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.altimetrik.bcp.entity.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer> {

//@Query(value = "UPDATE BusinessContinuityPlan bc SET ")	

}
