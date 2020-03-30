package com.altimetrik.bcp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.altimetrik.bcp.entity.Location;

@Repository
public interface LocationRepo extends JpaRepository<Location, Integer> {

}
