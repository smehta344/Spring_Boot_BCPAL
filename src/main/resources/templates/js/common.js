function getLoginUser(){
	var urlForProject = "/person";
	$.ajax({
		type : 'GET',
		url : urlForProject,
		success : function(response) {
			userInfoMap.set("username",response.username);
			userInfoMap.set("isAdmin",response.isAdmin);
			userInfoMap.set("isManager",response.isManager);
			userInfoMap.set("status",response.status);
			userInfoMap.set("haveAccessToSummary",response.haveAccessToSummary);
			userInfoMap.set("haveAccessTofileUpload",response.haveAccessToFileUpload);
			$("#uname").html("<font color='green'>Hi "+userInfoMap.get("username")+"!!</font>");
			if(userInfoMap.get("haveAccessToSummary") == 'false' && userInfoMap.get("haveAccessTofileUpload") == 'false'){
				$(".add_summary_access").attr('style','display:none');
				$(".add_file_upload_access").attr('style','display:none');
				$(".allow_accessed_users").empty();
				$(".allow_file_accessed_users").empty();
				$("#preventUnauthorizedUsersModal").modal('show');
			}
			if(userInfoMap.get("haveAccessToSummary") == 'false'){
				$(".add_summary_access").attr('style','display:none');
				$(".allow_accessed_users").empty();
				$("#preventUnauthorizedUsersModal").modal('show');
			} 
			if(userInfoMap.get("haveAccessTofileUpload") == 'false'){
				$(".add_file_upload_access").attr('style','display:none');
				$(".allow_file_accessed_users").empty();
				$("#preventUnauthorizedUsersModalFile").modal('show');
			}
			
		},error : function() {
			alert("Error while fetching user info");
		}
	});
}
function formatDate(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) 
        month = '0' + month;
    if (day.length < 2) 
        day = '0' + day;

    return [year, month, day].join('/');
}

function isWeekend(incomeDate){
	var date = new Date(incomeDate);
	var day = date.getDay();
	return (day === 6) || (day === 0);
}

$(function () {
	
	getLoginUser();
	
	$('#attandanceDate').datepicker().on('change', function(){
        $('.datepicker').hide();
    });
	$('#todaySummaryDate').datepicker().on('change', function(){
        $('.datepicker').hide();
    });
    $('#currentDate').datepicker({
    	format: "mm/dd/yyyy",
        todayBtn: true,
        defaultDate: moment().subtract(1, 'days'),
        clearBtn: true
      }).on('change', function(){
          $('.datepicker').hide();
      });
    
   
});
