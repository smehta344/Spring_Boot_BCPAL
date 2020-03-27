package com.altimetrik.bcp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.altimetrik.bcp.entity.DailyStatus;

@Repository
public interface DailyStatusRepo extends JpaRepository<DailyStatus, Integer> {

}
