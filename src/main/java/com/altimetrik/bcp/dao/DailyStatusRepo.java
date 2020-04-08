package com.altimetrik.bcp.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.altimetrik.bcp.entity.DailyStatus;
import com.altimetrik.bcp.entity.Project;
import com.altimetrik.bcp.model.DeliverySummary;

@Repository
public interface DailyStatusRepo extends JpaRepository<DailyStatus, Integer> {

	DailyStatus findByDateAndProject(Date date, Project project);
	
    @Query(nativeQuery = true, name="deliverySummaryByDateQuery")	
	List<DeliverySummary> findDeliverySummaryByDate(@Param("fromDate") String fromDate);
    
    List<DailyStatus> findByDateAndStatusAndProjectIn(Date date, String status, List<Project> project);
}
