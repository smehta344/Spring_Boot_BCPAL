$(function(){
	
	$("#attendanceSubmit").click(function(){ 
		$('#typeOfAccountMsg').empty();
    	$('#attendanceDateMsg').empty();
	var total = [];
	var marked = [];
	var unmarked = [];
	var leave = [];
	var labelsArr =[];
	var date = $("#attandanceDate").val();
	var typeOfAccount = $("#attendanceType").val();
	
	if(typeOfAccount == null){
		$("#typeOfAccountMsg").append("<font color='red'>Please select attendance type</font>");
		return false;
	} else {
		$('#typeOfAccountMsg').attr('style','display: none;');
		$('#typeOfAccountMsg').empty();
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
	alert(urlForAttendance);
	
	$.ajax({
		type : 'GET',
		url : urlForAttendance,
		data : {
			type:typeOfAccount,
			fromDate:date
		},
		success : function(response) {
			alert(response);
			var data = JSON.stringify(response)
			$.each(JSON.parse(data), function(idx, item) {
				labelsArr.push(idx);
				total.push(item.total);
				marked.push(item.marked);
				unmarked.push(item.unmarked);
				leave.push(item.leave);
				
			});
			
			alert("labelsArr="+labelsArr);
			alert("marked="+marked);
			alert("unmarked="+unmarked);
			alert("total="+total);
			
			  //get the bar chart canvas
			  var ctx = $("#bar-chartcanvas");
			  
			  
			  //bar chart data
			  var data = {
			    labels: labelsArr,
			    datasets: [
			      {
			        label: "Attendance Marked",
			        data: marked,
			        backgroundColor: [
			          "rgba(0,255,0,0.6)",
			          "rgba(0,255,0,0.6)",
			          "rgba(0,255,0,0.6)",
			          "rgba(0,255,0,0.6)",
			          "rgba(0,255,0,0.6)",
			          "rgba(0,255,0,0.6)",
			          "rgba(0,255,0,0.6)",
			          "rgba(0,255,0,0.6)",
			          "rgba(0,255,0,0.6)",
			          "rgba(0,255,0,0.6)"
			        ],
			        borderColor: [
			          "rgba(0,255,0,1)",
			          "rgba(0,255,0,1)",
			          "rgba(0,255,0,1)",
			          "rgba(0,255,0,1)",
			          "rgba(0,255,0,1)",
			          "rgba(0,255,0,1)",
			          "rgba(0,255,0,1)",
			          "rgba(0,255,0,1)",
			          "rgba(0,255,0,1)",
			          "rgba(0,255,0,1)",
			        ],
			        borderWidth: 1
			      },
			      {
			          label: "Attendance Unmarked",
			          data: unmarked,
			          backgroundColor: [
			            "rgba(255, 0, 0, 0.8)",
			            "rgba(255, 0, 0, 0.8)",
			            "rgba(255, 0, 0, 0.8)",
			            "rgba(255, 0, 0, 0.8)",
			            "rgba(255, 0, 0, 0.8)",
			            "rgba(255, 0, 0, 0.8)",
			            "rgba(255, 0, 0, 0.8)",
			            "rgba(255, 0, 0, 0.8)",
			            "rgba(255, 0, 0, 0.8)",
			            "rgba(255, 0, 0, 0.8)"
			            
			          ],
			          borderColor: [
			            "rgba(255, 0, 0, 1)",
			            "rgba(255, 0, 0, 1)",
			            "rgba(255, 0, 0, 1)",
			            "rgba(255, 0, 0, 1)",
			            "rgba(255, 0, 0, 1)",
			            "rgba(255, 0, 0, 1)",
			            "rgba(255, 0, 0, 1)",
			            "rgba(255, 0, 0, 1)",
			            "rgba(255, 0, 0, 1)",
			            "rgba(255, 0, 0, 1)"
			          ],
			          borderWidth: 1
			        },
			        {
			            label: "Leave",
			            data: leave,
			            backgroundColor: [
			              "rgba(255,0,255,0.6)",
			              "rgba(255,0,255,0.6)",
			              "rgba(255,0,255,0.6)",
			              "rgba(255,0,255,0.6)",
			              "rgba(255,0,255,0.6)",
			              "rgba(255,0,255,0.6)",
			              "rgba(255,0,255,0.6)",
			              "rgba(255,0,255,0.6)",
			              "rgba(255,0,255,0.6)",
			              "rgba(255,0,255,0.6)"
			              
			            ],
			            borderColor: [
			              "rgba(255,0,255,1)",
			              "rgba(255,0,255,1)",
			              "rgba(255,0,255,1)",
			              "rgba(255,0,255,1)",
			              "rgba(255,0,255,1)",
			              "rgba(255,0,255,1)",
			              "rgba(255,0,255,1)",
			              "rgba(255,0,255,1)",
			              "rgba(255,0,255,1)",
			              "rgba(255,0,255,1)"
			            ],
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
			      fontColor: "#111"
			    },
			    legend: {
			      display: true,
			      position: "bottom",
			      labels: {
			        fontColor: "#333",
			        fontSize: 16
			      }
			    },
			    scales: {
			    	xAxes: [{
			            stacked: true,
			            scaleLabel: {
			                display: true,
			                labelString: ''
			              }
			        }],
			        yAxes: [{
			            stacked: true,
			            ticks: {
			                min: 0
			              },
			              scaleLabel: {
			                  display: true,
			                  labelString: 'Employees Count'
			                }
			        }]
			    }
			  };

			  //create Chart class object
			  var chart = new Chart(ctx, {
			    type: "bar",
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