function getLoginUser(){
	var urlForProject = "/person";
	$.ajax({
		type : 'GET',
		url : urlForProject,
		success : function(response) {
			userInfoMap.set("username",response.username);
			$("#uname").html("<font color='green'>Hi "+userInfoMap.get("username")+"!!</font>");
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
