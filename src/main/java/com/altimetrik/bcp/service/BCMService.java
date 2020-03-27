package com.altimetrik.bcp.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altimetrik.bcp.dao.DailyStatusRepo;
import com.altimetrik.bcp.dao.LeaderRepo;
import com.altimetrik.bcp.dao.ProjectAssocRepo;
import com.altimetrik.bcp.entity.DailyStatus;
import com.altimetrik.bcp.entity.Leader;
import com.altimetrik.bcp.entity.ProjLocLeaderAssoc;
import com.altimetrik.bcp.entity.Project;
import com.altimetrik.bcp.model.PlanDetailFormData;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class BCMService {
	@Autowired
	private DailyStatusRepo statusRepo;
	
	@Autowired
	ProjectAssocRepo projecAssocRepo;
	
	@Autowired
	LeaderRepo leaderRepo;
	
	public void createDilyStatus(PlanDetailFormData formaData){
		DailyStatus statusObj = createStatusObj(formaData);
		statusRepo.save(statusObj);
	}
	
	public DailyStatus createStatusObj(PlanDetailFormData formData){
		DailyStatus statusObject = new DailyStatus();
		statusObject.setDate(formData.getDate());
		statusObject.setLocationId(formData.getLocationId());
		statusObject.setProjectId(formData.getProjectId());
		return statusObject;
	}
	
	public Leader getLeader(int locationId, int accountId){
		Leader leader = new Leader();
		List<ProjLocLeaderAssoc> assocList = projecAssocRepo.findAll();
		for(ProjLocLeaderAssoc leaderAssoc:assocList){
			if((leaderAssoc.getAccount().getId() == accountId) && 
					leaderAssoc.getLocation().getId() == locationId ){
				leader = leaderAssoc.getLeader();
				return leader;
			}
		}
		return leader;
	}
	
	public List<Project> getProjectById(int accountId){
		List<ProjLocLeaderAssoc> assocList = projecAssocRepo.findAll();
		List<Project> projList = new ArrayList<Project>();
		for(ProjLocLeaderAssoc projAssoc:assocList){
			if(projAssoc.getAccount().getId() == accountId){
				projList.add(projAssoc.getProject());
			}
		}
		return projList;
	}
}
