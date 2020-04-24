package com.altimetrik.bcp.dao;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.altimetrik.bcp.entity.TodaySummary;


@Repository
public interface TodaySummaryRepo extends CrudRepository<TodaySummary, Integer> {
	public TodaySummary getTodaySummaryByDate(Date date);
}
