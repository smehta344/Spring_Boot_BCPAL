package com.altimetrik.bcp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.altimetrik.bcp.entity.AccLocLeaderAssoc;

@Repository
public interface AccLocLeaderAssocrepo extends JpaRepository<AccLocLeaderAssoc, Integer> {
	AccLocLeaderAssoc findTopByAccountIdAndLocationId(int accountId, int locationId);
}
