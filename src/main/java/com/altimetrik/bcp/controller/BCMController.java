package com.altimetrik.bcp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.bcp.entity.ProjLocLeaderAssoc;
import com.altimetrik.bcp.model.PlanDetailFormData;
import com.altimetrik.bcp.service.BCMService;

/**
 * 
 * @author smehta
 *
 */
@RestController
public class BCMController {
	
	@Autowired
	BCMService bcmService;

	/*
	 * createProjectLocationAssociate
	 */
	@PostMapping(value = "/addDilyStatus")
	public void createDailyStatus(@RequestBody PlanDetailFormData formData) {
		bcmService.createDilyStatus(formData);
		
	}

	/*
	 * getProjectLocationAssociate
	 */
	@GetMapping(value = "/findbcpplan/{projectLocationAssociateId}")
	public Optional<ProjLocLeaderAssoc> findDatabyId(
			@PathVariable(" projectLocationAssociateId") int projectLocationAssociateId) {

		Optional<ProjLocLeaderAssoc> projLocLeaderAssoc = null;
		return projLocLeaderAssoc;

	}

	/*
	 * getAllProjectLocationAssociate
	 */
	@GetMapping(value = "/findallbcpplan")
	public List<ProjLocLeaderAssoc> findAllProjectLocationAssociate() {
		List<ProjLocLeaderAssoc> projLocLeaderAssocslist = null;
		return projLocLeaderAssocslist;
	}

	/*
	 * UpdateProjectLocationAssociate
	 */
	@PutMapping(value = "/updatebcpplan/{projectLocationAssociateId}")
	public ResponseEntity<String> updateProjectLocationAssociate(
			@PathVariable("projectLocationAssociateId") int projectLocationAssociateId,
			@RequestBody ProjLocLeaderAssoc projLocLeaderAssoc) {

		return new ResponseEntity<String>("UpdateSuccessfully", HttpStatus.OK);

	}

}
