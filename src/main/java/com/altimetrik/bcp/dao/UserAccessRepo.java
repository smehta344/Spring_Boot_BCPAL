package com.altimetrik.bcp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.altimetrik.bcp.entity.UserAccess;

@Repository
public interface UserAccessRepo extends CrudRepository<UserAccess, Integer> {
	public UserAccess getUserAccessByUserId(String userId);
}
