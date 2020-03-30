$(function(){
	var chart = null;
	
	$("select#attendanceWiseType").change(function(){
		var attendanceWiseType = $(this).children("option:selected").val();
		$('#attendanceTypeValueDiv').attr('style','display: block;');
        if(attendanceWiseType != 'empty' && attendanceWiseType =='ACCOUNT'){
        	var urlForProject = urlForServer+"dashboard/getAccountNames";
        	$('#attendanceTypeValue').empty();
			$('#attendanceTypeValue').append("<option value='empty' selected disabled hidden>Select Account</option>");
        } else {
        	var urlForProject = urlForServer+"dashboard/getClientLocations";
        	$('#attendanceTypeValue').empty();
			$('#attendanceTypeValue').append("<option value='empty' selected disabled hidden>Select Location</option>");
        }
		$.ajax({
			type : 'GET',
			url : urlForProject,
			success : function(response) {
				var data = JSON.stringify(response);
				$('#attendanceTypeValue').append("<option value='all'>ALL</option>");
				$.each(JSON.parse(data), function(idx, item) {
					$('#attendanceTypeValue').append("<option value="+item+">"+item+"</option>");
				});
			},error : function() {
				alert("Server error while fetching  get All Accounts Or Locations");
			}
		});
        });
	
	
	$("#attendanceSubmit").click(function(){ 
		$("#attendanceTable").empty();
		$('#attendanceWiseTypeMsg').empty();
    	$('#attendanceDateMsg').empty();
    	$('#attendanceTypeMsg').empty();
    	$('#attendanceTypeValuesMsg').empty();
	var date = $("#attandanceDate").val();
	var attendanceWiseType = $("#attendanceWiseType").val();
	var attdTypeValue = $("#attendanceTypeValue").val();
	var attdType = $("#attendanceType").val();
	var attendanceType = $("#attendanceType").val();
	var attendanceTypeValue = $("#attendanceTypeValue").val();
	$("#bar-chartcanvas").empty();
	if(attendanceWiseType == null || attendanceWiseType == 'empty' ){
		$("#attendanceWiseTypeMsg").append("<font color='red'>Please select attendance wise</font>");
		return false;
	} else {
		$('#attendanceWiseTypeMsg').attr('style','display: none;');
		$('#attendanceWiseTypeMsg').empty();
	}
	if((attendanceWiseType == 'ACCOUNT') && (attendanceTypeValue =='empty' || attendanceTypeValue == null)){
		$("#attendanceTypeValuesMsg").append("<font color='red'>Please select any Account</font>");
		return false;
	} else if ((attendanceWiseType == 'LOCATION') && (attendanceTypeValue =='empty' || attendanceTypeValue == null)){
		$("#attendanceTypeValuesMsg").append("<font color='red'>Please select any Location</font>");
		return false;
	}else {
		$('#attendanceTypeValuesMsg').attr('style','display: none;');
		$('#attendanceTypeValuesMsg').empty();
	}
	
	if(attendanceType == null || attendanceType == 'empty' ){
		$("#attendanceTypeMsg").append("<font color='red'>Please select attendance type</font>");
		return false;
	} else {
		$('#attendanceTypeMsg').attr('style','display: none;');
		$('#attendanceTypeMsg').empty();
	}
	
	if(!date.trim()){
		$('#attendanceDateMsg').attr('style','margin-top: -20px;margin-bottom: 10px;');
		$("#attendanceDateMsg").append("<font color='red'>Please select date</font>");
		
		return false;
	} else {
		$('#attendanceDateMsg').attr('style','display: none;');
		$('#attendanceDateMsg').empty();
	}
	
	
	var urlForAttendance = urlForServer+"dashboard/getAttendence/";
	//alert(urlForAttendance);
	var total = [];
	var marked = [];
	var markedBgColor = [];
	var markedBdColor = [];
	var unmarked = [];
	var unMarkedBgColor = [];
	var unMarkedBdColor = [];
	var leaveAppPending = [];
	var leaveAppPendingBgColor = [];
	var leaveAppPendingBdColor = [];
	
	
	var leave = [];
	var leaveBgColor = [];
	var leaveBdColor = [];
	var labelsArr =[];
	var empDetails = [];
	$.ajax({
		type : 'GET',
		url : urlForAttendance,
		data : {
			attendanceWise:attendanceWiseType,
			attdTypeValue:attdTypeValue,
			attdType:attdType,
			fromDate:date
		},
		success : function(response) {
			var data = JSON.stringify(response);
			//alert(data);
			$("#bar-chartcanvas").empty();
			//alert("data="+data);
			if(chart != null){
				chart.destroy();
			}
			  $.each(JSON.parse(data), function(idx, item) {
				  if(item.employeeDetails != null){
					  for(var i in item.employeeDetails){
						  empDetails.push(item.employeeDetails[i]);
					  }
				  }
				labelsArr.push(idx);
				total.push(item.total);
				
				marked.push(item.marked);
				markedBgColor.push("rgba(0,255,0,1)");
				markedBdColor.push("rgba(0,255,0,1)");
				
				unmarked.push(item.unmarked);
				unMarkedBgColor.push("rgba(255, 0, 0, 0.8)");
				unMarkedBdColor.push("rgba(255, 0, 0, 1)");
				
				
				leave.push(item.leave);
				leaveBgColor.push("rgba(242, 255, 10,1");
				leaveBdColor.push("rgba(242, 255, 10,1");
				
				leaveAppPending.push(item.leaveAppPending);
				leaveAppPendingBgColor.push("rgba(54, 4, 164,0.8)");
				leaveAppPendingBdColor.push("rgba(54, 4, 164,1)");
				
				//sno=(sno+1);
			});
			  if(empDetails.length > 0){
				  $("#attendanceTable").empty();
				  $("#attendanceTable").append("<thead><tr> <th scope='sNo'>S. No</th><th scope='eId'>EMPLOYEE ID</th><th scope='eName'>EMPLOYEE NAME</th>" +
				  		"<th scope='col'>ACCOUNT</th><th scope='col'>PROJECT</th><th scope='col'>LOCATION</th>" +
				  		"<th scope='col'>REPORTING MANAGER</th><th scope='col'>ATTENDANCE STATUS</th><th scope='col'>ATTENDANCE DATE</th>");
				  var sno = 1;
			  
				  for(var i in empDetails){
					  $("#attendanceTable").append("<tr><td>"+ sno +"</td><td>"+empDetails[i].empId+"</td>" +
					  		"<td>"+empDetails[i].empployeeName+"</td><td>"+empDetails[i].accountName+"</td>" +
					  				"<td>"+empDetails[i].project+"</td><td>"+empDetails[i].clientLocation+"</td>" +
					  						"<td>"+empDetails[i].reportManager+"</td><td>"+empDetails[i].attendanceStatus+"</td>" +
					  								"<td>"+empDetails[i].attendanceDate+"</td></tr>");
					  sno=(sno+1);
				  }
				  $("#attendanceTable").append("</tbody>");
			  }
			
			
		/*	alert("labelsArr="+labelsArr);
			alert("marked="+marked);
			alert("unmarked="+unmarked);
			alert("total="+total);
			alert("leave="+leave);*/
			
			  //get the bar chart canvas
			  var ctx = $("#bar-chartcanvas");
			  
			  
			  //bar chart data
			  var data = {
			    labels: labelsArr,
			    datasets: [
			      {
			        label: "Attendance Marked",
			        data: marked,
			        backgroundColor: markedBgColor,
			        borderColor: markedBdColor,
			        //hoverBackgroundColor: 'rgba(230, 236, 235, 0.75)',
			       // hoverBorderColor: 'rgba(230, 236, 235, 0.75)',
			        borderWidth: 1
			      },
			      {
			            label: "Leave",
			            data: leave,
			            backgroundColor: leaveBgColor,
			            borderColor: leaveBdColor,
			            borderWidth: 1
			          },
			      {
			          label: "Attendance Unmarked",
			          data: unmarked,
			          backgroundColor: unMarkedBgColor,
			          borderColor: unMarkedBdColor,
			          //hoverBackgroundColor: 'rgba(230, 236, 235, 0.75)',
			         // hoverBorderColor: 'rgba(230, 236, 235, 0.75)',
			          borderWidth: 1
			        },
				        {
				            label: "Leave - Approval Pending",
				            data: leaveAppPending,
				            backgroundColor: leaveAppPendingBgColor,
				            borderColor: leaveAppPendingBdColor,
				            borderWidth: 1
				          }
			    ]
			  };

			  //options
			  var options = {
			    responsive: true,
			    title: {
			      display: true,
			      position: "top",
			      text: "Overall Attendance",
			      fontSize: 18,
			      fontColor: "#0000ff"
			    },
			    legend: {
			      display: true,
			      position: "bottom",
			      labels: {
			        fontColor: "#111",
			        fontSize: 16
			      }
			    },
			    scales: {
			    	xAxes: [{
			    		offset: true,
			            stacked: true,
			            //barPercentage: 1.4,
			            maxBarThickness : 50,
			            scaleLabel: {
			                display: true,
			                labelString: ''
			              }
			        }],
			        yAxes: [{
			        	offset: true,
			            stacked: true,
			            ticks: {
			                beginAtZero:true,
			                maxTicksLimit: 50
			              },
			              scaleLabel: {
			                  display: true,
			                  labelString: 'Employees Count'
			                }
			        }]
			    }
			  };

			  //create Chart class object
			  chart = new Chart(ctx, {
			    type: "bar",   //pie,doughnut,polarArea
			    data: data,
			    options: options
			  });
			  
			 
		},error : function() {
			$("#modalBody").empty();
			$("#myModal").modal("show");
			$("#modalBody").append("<b><font color='red'>No records found!!!</font></b>");
		}
	});
	});
});