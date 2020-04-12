$(document).ready(function(){
	
	
	var urlForAccount = urlForServer+"bcm/getAllAccounts";
	var isDateChanged = true;
	
	$("#currentDate").datepicker({ dateFormat: 'yy, mm, dd' });
	
	$("#currentDate").datepicker("setDate", "-1d").on("change", function() {
	    $('.datepicker').hide();
	    loadInitialData();
	  });
    
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
	
		$("select#project").change(function(){
		var project = $(this).children("option:selected").val();
		var account = $("#account").children("option:selected").val();
        if(project != 'empty' && account !='empty'){
        	var urlForProject = urlForServer+"bcm/getLocationAndLeader/"+project+"/"+account;
        	var urlForhistoryData = urlForServer+"bcm/getHistoryData";
			$.ajax({
						type : 'GET',
						url : urlForProject,
						success : function(response) {
							var location = response.location;
							var leader = response.leader;
							$('#engg_leader').empty();
							$('#engg_leader').attr('value', leader.name);
							$('#engg_leader').attr('name', leader.id);
							$('#location').val(location.name);
							$('#location').attr('name', location.id);
								var date = $("#currentDate").val();
								var sa = new Date(date);
								var dateObj = new Date((sa.setDate(sa.getDate()-1)));
								var hoistoryDataUrl = urlForServer+"bcm/getHistoryData";
								var dateVal = changeFormat(dateObj, 'yyyy/MM/dd');
								var quryDate = formatDate(dateVal)
								$.ajax({
									type : 'GET',
									url : urlForhistoryData,
									data : {
										projectId:project,
										fromDate:quryDate
									},
									success : function(responseData) {
										if(responseData.projectName != null){
										$('#milestone').val(responseData.milestone);
									    $('#challenges').val(responseData.deliveryChallenge);
								    	$('#wfh_challenges').val(responseData.wfhChallenge);
								    	$('#wfh_mitigation').val(responseData.mitigationPlan);
								    	$('#wfh_challenges').val(responseData.wfhChallenge);
								    	$('#key_deliverables').val(responseData.keyDeliverable);
								    	$('#mitigation').val(responseData.mitigationPlan);
								    	$('#teamSize').val(responseData.teamSize);
								    	$('#hiringUpdates').val(responseData.hiringUpdate);
										}
										else{
											$('#challenges').val("");
											$('#milestone').val("");
									    	$('#wfh_challenges').val("");
									    	$('#wfh_mitigation').val("");
									    	$('#wfh_challenges').val("");
									    	$('#key_deliverables').val("");
									    	$('#mitigation').val("");
									    	$('#teamSize').val("");
									    	$('#hiringUpdates').val("");
										}
									},error : function() {
										alert("Server error while fetching engineering leader");
									}
						});
							
						},error : function() {
							alert("Server error while fetching engineering leader");
						}
			});
        }
	});
	
	    $('select#account').on('change', function() {
    	var location = $("#location").children("option:selected").val();
    	var accountId = $(this).children("option:selected").val();
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
					
					 $("#project").val($("#project option:eq(1)").val()).change();
					 
					 $("#proj_status").val($("#proj_status option:eq(3)").val()).change();
					 
					 $("#deliverOnTrack").val($("#deliverOnTrack option:eq(1)").val()).change();
					 
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
    	var location = $("#location").attr('name');
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
    	var hiringUpdate = $("#hiringUpdates").val();
    	
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
       
        var datastr = '{"date":"'+date+'","locationId":"'+location+'","accountId":"'+account+'","leaderId":"'+engg_leader+'","projectId":"'+project+'","status":"'+proj_status+'","teamSize":"'+teamSize+'","loogedCount":"'+teamLogged+'","deliveryOnTrack":"'+deliverOnTrack+'","targetPercentage":"'+targetPercent+'","actualPercentage":"'+actualPercent+'","milestone":"'+milestone+'","deliveryChallenge":"'+challenges+'","mitigationPlan":"'+mitigation+'","wfhChallenge":"'+wfhChallenges+'","wfhMitigation":"'+wfhMitigation+'","keyDeliverable":"'+keyDeliverables+'","hiringUpdate":"'+hiringUpdate+'"}';
        $.ajax({
			contentType: 'application/json; charset=utf-8',
			type : 'POST',
			url : url,
			data : datastr,
			success : function(responseText) {
				loadInitialData();
				$("#currentDate").val("");
				$("#modalBody").empty();
				$("#myModal").modal("show");
				$("#modalBody").append("<b><font color='green'>Project added successfully!!!</font></b>");
			},error : function() {
				alert("error");
			}
		});
    });
});

function changeFormat(x, y) {
    var z = {
        M: x.getMonth() + 1,
        d: x.getDate(),
        h: x.getHours(),
        m: x.getMinutes(),
        s: x.getSeconds()
    };
    y = y.replace(/(M+|d+|h+|m+|s+)/g, function(v) {
        return ((v.length > 1 ? "0" : "") + eval('z.' + v.slice(-1))).slice(-2)
    });

    return y.replace(/(y+)/g, function(v) {
        return x.getFullYear().toString().slice(-v.length)
    });
}

function loadInitialData(){
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
	$('#hiringUpdates').val("");
}