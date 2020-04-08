var chart;
function getAccountNames(){
	var urlForProject = urlForServer+"dashboard/getAccountNames";
	$('#attendanceTypeValue').empty();
	$('#attendanceTypeValue').append("<option value='empty' disabled>SELECT ACCOUNT</option>");
	$.ajax({
		type : 'GET',
		url : urlForProject,
		success : function(response) {
			var data = JSON.stringify(response);
			$('#attendanceTypeValue').append("<option value='all' selected>ALL</option>");
			$.each(JSON.parse(data), function(idx, item) {
				$('#attendanceTypeValue').append("<option value="+item+">"+item+"</option>");
			});
		},error : function() {
			alert("Server error while fetching  get All Accounts");
		}
	});
}
function addElementsInList(total, num) {
	  return total + num;
}
function getClientLocations(){
	var urlForProject = urlForServer+"dashboard/getClientLocations";
	$('#attendanceTypeValue').empty();
	$('#attendanceTypeValue').append("<option value='empty' disabled>SELECT LOCATION</option>");
	$.ajax({
		type : 'GET',
		url : urlForProject,
		success : function(response) {
			var data = JSON.stringify(response);
			$('#attendanceTypeValue').append("<option value='all' selected>ALL</option>");
			$.each(JSON.parse(data), function(idx, item) {
				$('#attendanceTypeValue').append("<option value="+item+">"+item+"</option>");
			});
		},error : function() {
			alert("Server error while fetching  get All Locations");
		}
	});
}

function getAttendancePagePercentTable(attendanceWiseType, attendanceTypeValue, attendanceType,billingType,date){
	var urlForPercentTable = urlForServer+"dashboard/getAttendencePercent";
	$("#attendancePercentTable").empty();
	var totalPercent = [];
	var markedPercent = [];
	var unmarkedPercent = [];
	var leavePercent = [];
	$.ajax({
		type : 'GET',
		url : urlForPercentTable,
		data : {
			attendanceWise:attendanceWiseType,
			attdTypeValue:attendanceTypeValue,
			billingType:billingType,
			attdType:attendanceType,
			fromDate:date
		},
		success : function(response) {
			$("#attendancePercentTable").empty();
			var data = JSON.stringify(response);
			if(attendanceType != 'all'){
				var table = "<thead align='center'><tr align='center' class='table-primary'><th colspan='8'>"+attendanceType.toUpperCase()+" PERCENTAGE (%)</th>" +
						"</tr></thead><thead align='center'> ";
			} else {
				var table = "<thead align='center'><tr align='center' class='table-primary'><th colspan='8'>MARKED PERCENTAGE (%)</th>" +
				"</tr></thead><thead align='center'> ";
			}
			var counter = 0;
			$.each(JSON.parse(data), function (i,val) {
				if(counter == 0){
					table = table + "<tr class='table-primary'><th>"+i+"</th>";
				} else {
					table = table + "<tr ><th class='table-primary'>"+i+"</th>";
				}
				$.each(val, function (key, val) {
					if(counter == 0){
						table = table +"<th>"+val+"</th>";
					} else {
						table = table +"<td class='table-warning'>"+val+"</td>";
					}
				});
				if(counter == 0){
					table = table +"</tr></thead><tbody>";
				} else {
					table = table +"</tr>";
				}
				counter = counter+1;
			});
			table = table +"</tbody>";
			$("#attendancePercentTable").append(table);
			
		},error : function() {
			alert("Server error while fetching  get All Accounts");
		}
	});
}
function getAttendancePage(attendanceWiseType,attendanceTypeValue,attendanceType,billingType,date) {

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
	
	
	var totalPercent = [];
	var markedPercent = [];
	var unmarkedPercent = [];
	var leavePercent = [];
	var leaveAppPendingPercent = [];
	
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
			attdTypeValue:attendanceTypeValue,
			billingType:billingType,
			attdType:attendanceType,
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
				
				
				markedPercent.push(item.marked_percent);
				unmarkedPercent.push(item.unmarked_percent);
				leavePercent.push(item.leave_percent);
				leaveAppPendingPercent.push(item.leave_app_pend_percent);
				
				
				//sno=(sno+1);
			});
			  var sno = 1;
			  if(attendanceWiseType != 'LOCATION'){
				  var accounttable = "<tbody><thead align='center'><tr align='center' class='table-primary'><th colspan='7'>OVERALL EMPLOYEE STATUS ["+date+"]</th>" +
					"</tr></thead><thead align='center'> <tr class='table-primary'> <th scope='sNo'>S. No</th><th scope='eId'>ACCOUNT NAME</th>" +
			  		"<th scope='eName'>MARKED</th>" +
			  		"<th scope='col'>NOT MARKED</th><th scope='col'>LEAVE</th><th scope='col'>TOTAL</th><th scope='col'>MARKED (%)</th></tr>";
				  	$("#attendancePercentTable").empty();
					  for(var i = 0 in labelsArr){
						  var percent  = ((marked[i]/(total[i]-leave[i]))*100);
						  if(isNaN(percent)){
							  percent = 0;
						  }
						  accounttable = accounttable+"<tr><th class='table-primary'>"+ sno +"</th><th class='table-primary'>"+labelsArr[i]+"</th>" +
					  		"<td class='table-warning'>"+marked[i]+"</td><td class='table-warning'>"+unmarked[i]+"</td>" +
			  				"<td class='table-warning'>"+leave[i]+"</td><td class='table-warning'>"+total[i]+"</td>" +
			  						"<td class='table-warning'>"+percent.toFixed(1)+"%</td></tr>";
							
						  sno=(sno+1);
					  }
					  accounttable= accounttable+"<tr><th class='table-primary' colspan='2'>TOTAL</th>" +
					  		"<td class='table-primary'>"+marked.reduce(addElementsInList)+"</td>" +
					  				"<td class='table-primary'>"+unmarked.reduce(addElementsInList)+"</td>" +
					  						"<td class='table-primary'>"+leave.reduce(addElementsInList)+"</td>" +
					  								"<td class='table-primary'>"+total.reduce(addElementsInList)+"</td><td class='table-primary'>-</td></tr>"
					  		
					  accounttable = accounttable+"</tbody>";
					  $("#attendancePercentTable").append(accounttable);
			  }
			  if(empDetails.length > 0){
				  $("#attendanceTable").empty();
				  $("#attendanceTable").append("<tbody><thead align='center'><tr align='center' class='table-primary'> <th scope='sNo'>S. No</th>" +
				  		"<th scope='eId'>EMPLOYEE ID</th><th scope='eName'>EMPLOYEE NAME</th><th scope='eName'>EMAIL ID</th>" +
				  		"<th scope='col'>ACCOUNT</th><th>PROJECT</th><th scope='col'>LOCATION</th>" +
				  		"<th scope='col'>REPORTING MANAGER</th><th scope='col'>CATEGORY</th>" +
				  		"<th scope='col'>ATTENDANCE STATUS</th><th scope='col'>ATTENDANCE DATE</th>");
				  var sno = 1;
				  for(var i in empDetails){
					  $("#attendanceTable").append("<tr class='table-warning'><td>"+ sno +"</td><td>"+empDetails[i].empId+"</td>" +
					  		"<td>"+empDetails[i].empployeeName+"</td><td><a href=''>"+empDetails[i].emailId+"</a></td><td>"+empDetails[i].accountName+"</td>" +
					  				"<td>"+empDetails[i].project+"</td><td>"+empDetails[i].clientLocation+"</td>" +
					  						"<td>"+empDetails[i].reportManager+"</td><td>"+empDetails[i].Category+"</td><td>"+empDetails[i].attendanceStatus+"</td>" +
					  								"<td>"+empDetails[i].attendanceDate+"</td></tr>");
					  sno=(sno+1);
				  }
				  $("#attendanceTable").append("</tbody>");
			  }
			
			
			  var ctx = $("#bar-chartcanvas");
			  //bar chart data
			  var data = {
			    labels: labelsArr,
			    datasets: [
				      {
				        label: "Attendance Marked(%)",
				        data: markedPercent,
				        backgroundColor: markedBgColor,
				        borderColor: markedBdColor,
				        //hoverBackgroundColor: 'rgba(230, 236, 235, 0.75)',
				       // hoverBorderColor: 'rgba(230, 236, 235, 0.75)',
				        borderWidth: 1
				      },
			      /*{
			            label: "Leave (%)",
			            data: leavePercent,
			            backgroundColor: leaveBgColor,
			            borderColor: leaveBdColor,
			            borderWidth: 1
			          },
			      {
			          label: "Attendance Unmarked (%)",
			          data: unmarkedPercent,
			          backgroundColor: unMarkedBgColor,
			          borderColor: unMarkedBdColor,
			          //hoverBackgroundColor: 'rgba(230, 236, 235, 0.75)',
			         // hoverBorderColor: 'rgba(230, 236, 235, 0.75)',
			          borderWidth: 1
			        },
				        {
				            label: "Leave - Approval Pending(%)",
				            data: leaveAppPendingPercent,
				            backgroundColor: leaveAppPendingBgColor,
				            borderColor: leaveAppPendingBdColor,
				            borderWidth: 1
				          }*/
			    ]
			  };

			  //options
			  var options = {
			    responsive: true,
			    title: {
			      display: true,
			      position: "top",
			      text: "Marked Attendance (%)",
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
			                maxTicksLimit: 10
			              },
			              scaleLabel: {
			                  display: true,
			                  labelString: 'Percentage'
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
}

$(function(){
	var date = new Date();
	var today = new Date(date.getFullYear(), date.getMonth(), date.getDate());
	
	getAttendancePage('LOCATION', 'all', 'Marked','BILLED', formatDate(today));
	getClientLocations();
	getAttendancePagePercentTable('LOCATION', 'all', 'Marked','BILLED', formatDate(today));
	var record="";
	$("#billingType").val('BILLED');
	$("#attendanceType").val('Marked');
	$('#attandanceDate').val(formatDate(today));
	$("select#attendanceWiseType").change(function(){
		var attendanceWiseType = $(this).children("option:selected").val();
		$('#attendanceTypeValueDiv').attr('style','display: block;');
        if(attendanceWiseType != 'empty' && attendanceWiseType =='ACCOUNT'){
        	var urlForProject = urlForServer+"dashboard/getAccountNames";
        	$('#attendanceTypeValue').empty();
			$('#attendanceTypeValue').append("<option value='empty' selected disabled>SELECT ACCOUNT</option>");
        } else {
        	var urlForProject = urlForServer+"dashboard/getClientLocations";
        	$('#attendanceTypeValue').empty();
			$('#attendanceTypeValue').append("<option value='empty' selected disabled>SELECT LOCATION</option>");
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
	
	$('#export').on('click', function() {
		alert("clciked");
		html2canvas($('#attendancePercentTable'), {
	        onrendered: function(canvas) {                                      

	            var saveAs = function(uri, filename) {
	                var link = document.createElement('a');
	                if (typeof link.download === 'string') {
	                    document.body.appendChild(link); // Firefox requires the link to be in the body
	                    link.download = filename;
	                    link.href = uri;
	                    link.click();
	                    document.body.removeChild(link); // remove the link when done
	                } else {
	                    location.replace(uri);
	                }
	            };

	            var img = canvas.toDataURL("image/jpeg"),
	                uri = img.replace(/^data:image\/[^;]/, 'data:application/octet-stream');
	            alert(uri);
	            saveAs(uri, 'tableExport.jpeg');
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
	var billingType = $("#billingType").val();
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
	if(chart != null){
		chart.destroy();
	}
	getAttendancePage(attendanceWiseType, attendanceTypeValue, attendanceType, billingType, date);
	if(attendanceWiseType == 'LOCATION'){
		getAttendancePagePercentTable(attendanceWiseType, attendanceTypeValue, attendanceType, billingType, date);
	}
	});

	$("#attendanceTable").on("click", "td", function(event) {
		var attendanceType = $("#attendanceType").val();
		event.preventDefault();
		if(attendanceType == 'Not Marked'){
			var mail = $( this ).text();
			var col = $(this).parent().children().index($(this));
			var name = $(this).parent().children().next().next().html();
		    if(col == 3){
			    $("#emailmodalBody").empty();
				$("#emailModal").modal("show");
				$("#emailmodalBody").append("<b><p id='mailUname' hidden>"+name+"</p><font color='blue'>Do you want to send notification email to</font><font color='red' id='mail'> "+mail+"</font></b>");
			}
		}
	 });
	
	$("#emailNotificationYes").click(function(){ 
		var email = $("#mail").text();
		var name = $("#mailUname").text();
        var url = urlForServer+"mail/sendEmail";
       
		var datastr = '{"mailTo":"'+email+'","mailUname":"'+name+'"}';
		$.ajax({
			contentType: 'application/json; charset=utf-8',
			type : 'POST',
			url : url,
			data : datastr,
			success : function(responseText) {
				console.log("mail sent to "+email+": "+responseText);
			},error : function() {
				console.log("mail sent to "+email+": error");
			}
		});
	});
});