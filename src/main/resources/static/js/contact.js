$(document).ready(function(){
	var urlForLocation = urlForServer+"bcm/getAllLocations";
	$.ajax({
		type : 'GET',
		url : urlForLocation,
		success : function(response) {
			var data = JSON.stringify(response)
			$('#location').empty();
			$('#location').append("<option value='empty' selected disabled hidden>Select Location</option>");

			$.each(JSON.parse(data), function(idx, item) {
				$('#location').append("<option value="+item.id+">"+item.name+"</option>");
			});
		},error : function() {
			alert("Server error while fetching location");
		}
	});
	
	var urlForAccount = urlForServer+"bcm/getAllAccounts";
	
	$.ajax({
		type : 'GET',
		url : urlForAccount,
		success : function(response) {
			var data = JSON.stringify(response)
			$('#account').empty();
			$('#account').append("<option value='empty' selected disabled hidden>Select Account</option>");
			$.each(JSON.parse(data), function(idx, item) {
				$('#account').append("<option value="+item.id+">"+item.name+"</option>");
			});
		},error : function() {
			alert("Server error while fetching account");
		}
	});
	
	$("select#location").change(function(){
		var location = $(this).children("option:selected").val();
		var account = $("#account").children("option:selected").val();
        if(location != 'empty' && account !='empty'){
        	var urlForProject = urlForServer+"bcm/getLeader/"+location+"/"+account;
			$.ajax({
				type : 'GET',
				url : urlForProject,
				success : function(response) {
					var data = JSON.stringify(response)
					$('#project').empty();
					$('#project').append("<option value='empty' selected disabled hidden>Select Project</option>");
					$.each(JSON.parse(data), function(idx, item) {
						$('#project').append("<option value="+item+">"+item+"</option>");
					});
				},error : function() {
					alert("Server error while fetching account");
				}
			});
        }
	
	});
	
	$("select#project").change(function(){
        var projName = $(this).children("option:selected").val();
        if(projName == '0'){
        	$('#specific_proj').attr('style','display: block;');
        } else {
        	$('#specific_proj').attr('style','display: none;');
        }
    });
	
    $('select#account').on('change', function() {
    	var location = $("#location").children("option:selected").val();
    	var accountId = $(this).children("option:selected").val();
    	
    	if(location != 'empty' && accountId !='empty'){
    		var urlForLeader = urlForServer+"bcm/getLeader/"+location+"/"+accountId;
			$.ajax({
				type : 'GET',
				url : urlForLeader,
				success : function(response) {
					var data = JSON.stringify(response);
					$('#engg_leader').empty();
					$('#engg_leader').attr('value', response.name);
					$('#engg_leader').attr('name', response.id);
				},error : function() {
					alert("Server error while fetching engineering leader");
				}
			});
        }
    	
    	if(accountId != 'empty'){
    		var urlForProject = urlForServer+"bcm/getProject/"+accountId;
			$.ajax({
				type : 'GET',
				url : urlForProject,
				success : function(response) {
					var data = JSON.stringify(response);
					$('#project').empty();
					$('#project').append("<option value='empty' selected disabled hidden>Select Project</option>");
					$.each(JSON.parse(data), function(idx, item) {
						$('#project').append("<option value="+item.id+">"+item.name+"</option>");
					});
					$('#project').append("<option value='0'>Others</option>");
				},error : function() {
					alert("Server error while fetching project name");
				}
			});
         }	
    });
	
    $("#contactSubmit").click(function(){  
    	
    	$('#currentDateMsg').empty();
    	$('#locationMsg').empty();
    	$('#accountMsg').empty();
    	$('#projectMsg').empty();
    	$('#specific_projMsg').empty();
    	$('#proj_statusMsg').empty();
    	$('#teamSizeMsg').empty();
    	$('#teamLoggedMsg').empty();
    	$('#deliverOnTrackMsg').empty();
    	$('#targetMsg').empty();
    	$('#actualMsg').empty();
    	$('#key_deliverablesMsg').empty();
    	$('#milestoneMsg').empty();
    	$('#challengesMsg').empty();
    	$('#mitigationMsg').empty();
    	$('#wfh_challengesMsg').empty();
    	$('#wfh_mitigationMsg').empty();
    	
    	
    	
    	var date = $("#currentDate").val();
    	var location = $("#location").val();
    	var engg_leader = $("#engg_leader").attr('name');
    	var milestone = $("#milestone").val();
    	var project = $("#project").val();
    	var customProjectName = $("#customProjectName").val();
    	var account = $("#account").val();
    	var proj_status = $("#proj_status").val();
    	var challenges = $("#challenges").val();
    	var teamSize = $("#teamSize").val();
    	var teamLogged = $("#teamLogged").val();
    	var mitigation = $("#mitigation").val();
    	var targetPercent = $("#target").val();
    	var actualPercent = $("#actual").val();
    	var deliverOnTrack = $("#deliverOnTrack").val();
    	var wfhChallenges = $("#wfh_challenges").val();
    	var wfhMitigation = $("#wfh_mitigation").val();
    	var keyDeliverables = $("#key_deliverables").val();
    	//alert("deliverOnTrack = "+deliverOnTrack.trim()+"  proj_status="+!proj_status+" keyDeliverables= "+!keyDeliverables);
    	
    	if(!date.trim()){
    		$('#currentDateMsg').attr('style','margin-top: -20px;margin-bottom: 10px;');
    		$("#currentDateMsg").append("<font color='red'>Please select date</font>");
    		
    		return false;
    	} else {
    		$('#currentDateMsg').attr('style','display: none;');
    		$('#currentDateMsg').empty();
    	}

    	if(location == null){
    		$("#locationMsg").append("<font color='red'>Please select location</font>");
    		return false;
    	} else {
    		$('#locationMsg').attr('style','display: none;');
    		$('#locationMsg').empty();
    	}
    	
    	if(account==null){
    		$("#accountMsg").append("<font color='red'>Please select account</font>");
    		return false;
    	} else {
    		$('#accountMsg').attr('style','display: none;');
    		$('#accountMsg').empty();
    	}
    	
    	if(project==null){
    		$("#projectMsg").append("<font color='red'>Please select project</font>");
    		return false;
    	} else {
    		$('#projectMsg').attr('style','display: none;');
    		$('#projectMsg').empty();
    	}
    	if(project=='other' && customProjectName == ''){
    		$("#specific_projMsg").append("<font color='red'>Please enter project name</font>");
    		return false;
    	} else {
    		$('#specific_projMsg').attr('style','display: none;');
    		$('#specific_projMsg').empty();
    	}
    	
    	if(proj_status == null){
    		$("#proj_statusMsg").append("<font color='red'>Please select project status</font>");
    		return false;
    	} else {
    		$('#proj_statusMsg').attr('style','display: none;');
    		$('#proj_statusMsg').empty();
    	}
    	if(teamSize==''){
    		$("#teamSizeMsg").append("<font color='red'>Please enter team size</font>");
    		return false;
    	} else {
    		$('#teamSizeMsg').attr('style','display: none;');
    		$('#teamSizeMsg').empty();
    	}
    	if(teamLogged==''){
    		$("#teamLoggedMsg").append("<font color='red'>Please enter team logged in</font>");
    		return false;
    	} else {
    		$('#teamLoggedMsg').attr('style','display: none;');
    		$('#teamLoggedMsg').empty();
    	}
    	if(deliverOnTrack==null){
    		$("#deliverOnTrackMsg").append("<font color='red'>Please select delivery on track</font>");
    		return false;
    	} else {
    		$('#deliverOnTrackMsg').attr('style','display: none;');
    		$('#deliverOnTrackMsg').empty();
    	}
    	if(targetPercent==''){
    		$('#targetMsg').attr('style','margin-top: -20px;margin-bottom: 10px;');
    		$("#targetMsg").append("<font color='red'>Please enter target percentage</font>");
    		return false;
    	} else {
    		$('#targetMsg').attr('style','display: none;');
    		$('#targetMsg').empty();
    	}
    	if(actualPercent==''){
    		$('#actualMsg').attr('style','margin-top: -20px;margin-bottom: 10px;');
    		$("#actualMsg").append("<font color='red'>Please enter actual percentage</font>");
    		return false;
    	} else {
    		$('#actualMsg').attr('style','display: none;');
    		$('#actualMsg').empty();
    	}
    	
    	if(!milestone.trim()){
    		$('#milestoneMsg').attr('style','margin-top: -20px;margin-bottom: 10px;');
    		$("#milestoneMsg").append("<font color='red'>Please enter milestone</font>");
    		return false;
    	} else {
    		$('#milestoneMsg').attr('style','display: none;');
    		$('#milestoneMsg').empty();
    	}
    	if(!challenges.trim()){
    		$('#challengesMsg').attr('style','margin-top: -20px;margin-bottom: 10px;');
    		$("#challengesMsg").append("<font color='red'>Please enter delivery challenges</font>");
    		return false;
    	} else {
    		$('#challengesMsg').attr('style','display: none;');
    		$('#challengesMsg').empty();
    	}
    	
    	if(!mitigation.trim()){
    		$('#mitigationMsg').attr('style','margin-top: -20px;margin-bottom: 10px;');
    		$("#mitigationMsg").append("<font color='red'>Please enter delivery mitigation plans</font>");
    		return false;
    	} else {
    		$('#mitigationMsg').attr('style','display: none;');
    		$('#mitigationMsg').empty();
    	}
    	
    	if(!wfhChallenges.trim()){
    		$('#wfh_challengesMsg').attr('style','margin-top: -20px;margin-bottom: 10px;');
    		$("#wfh_challengesMsg").append("<font color='red'>Please enter wfh challenges</font>");
    		return false;
    	} else {
    		$('#wfh_challengesMsg').attr('style','display: none;');
    		$('#wfh_challengesMsg').empty();
    	}
    	
    	if(!wfhMitigation.trim()){
    		$('#wfh_mitigationMsg').attr('style','margin-top: -20px;margin-bottom: 10px;');
    		$("#wfh_mitigationMsg").append("<font color='red'>Please enter wfh mitigation plan</font>");
    		return false;
    	} else {
    		$('#wfh_mitigationMsg').attr('style','display: none;');
    		$('#wfh_mitigationMsg').empty();
    	}
    	if(!keyDeliverables.trim()){
    		$('#key_deliverablesMsg').attr('style','margin-top: -20px;margin-bottom: 10px;');
    		$("#key_deliverablesMsg").append("<font color='red'>Please enter key deliverables</font>");
    		return false;
    	} else {
    		$('#key_deliverablesMsg').attr('style','display: none;');
    		$('#key_deliverablesMsg').empty();
    	}
    	
        var url = urlForServer+"bcm/addDilyStatus";
       
        var datastr = '{"date":"'+date+'","locationId":"'+location+'","accountId":"'+account+'","leaderId":"'+engg_leader+'","projectId":"'+project+'","status":"'+proj_status+'","teamSize":"'+teamSize+'","loogedCount":"'+teamLogged+'","deliveryOnTrack":"'+deliverOnTrack+'","targetPercentage":"'+targetPercent+'","actualPercentage":"'+actualPercent+'","milestone":"'+milestone+'","deliveryChallenge":"'+challenges+'","mitigationPlan":"'+mitigation+'","wfhChallenge":"'+wfhChallenges+'","wfhMitigation":"'+wfhMitigation+'","keyDeliverable":"'+keyDeliverables+'"}';
		alert(datastr);
        $.ajax({
			contentType: 'application/json; charset=utf-8',
			type : 'POST',
			url : url,
			data : datastr,
			success : function(responseText) {
				$("#currentDate").val("");
				$('#location').val("empty");
				$('#engg_leader').attr('value', "Engineering Leader");
				$("#milestone").val("");
				$('#project').empty();
				$('#project').append("<option value='empty' selected disabled hidden>Select Project</option>");
				$("#customProjectName").val("");
				$('#account').val("empty");
				$("#proj_status").val("empty");
				$("#challenges").val("");
				$("#teamSize").val("");
				$("#teamLogged").val("");
				$("#mitigation").val("");
				$("#target").val("");
				$("#actual").val("");
				$("#deliverOnTrack").val("");
				$("#wfh_challenges").val("");
				$("#wfh_mitigation").val("");
				$("#key_deliverables").val("");
				$("#modalBody").empty();
				$("#myModal").modal("show");
				$("#modalBody").append("<b><font color='green'>Contact added successfully!!!</font></b>");
			},error : function() {
				alert("error");
			}
		});
    });
    
    
    
    
    
    
});