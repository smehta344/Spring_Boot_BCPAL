$(function(){
	var chart = null;
	$("#attendanceSubmit").click(function(){ 
		$('#typeOfAccountMsg').empty();
    	$('#attendanceDateMsg').empty();
	var date = $("#attandanceDate").val();
	var typeOfAccount = $("#attendanceType").val();
	$("#bar-chartcanvas").empty();
	if(typeOfAccount == null || typeOfAccount == 'empty' ){
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
	//alert(urlForAttendance);
	var total = [];
	var marked = [];
	var markedBgColor = [];
	var markedBdColor = [];
	var unmarked = [];
	var unMarkedBgColor = [];
	var unMarkedBdColor = [];
	var leave = [];
	var leaveBgColor = [];
	var leaveBdColor = [];
	var labelsArr =[];
	$.ajax({
		type : 'GET',
		url : urlForAttendance,
		data : {
			type:typeOfAccount,
			fromDate:date
		},
		success : function(response) {
			var data = JSON.stringify(response);
			$("#bar-chartcanvas").empty();
			//alert("data="+data);
			if(chart != null){
				chart.destroy();
			}
			$.each(JSON.parse(data), function(idx, item) {
				
				labelsArr.push(idx);
				total.push(item.total);
				
				marked.push(item.marked);
				markedBgColor.push("rgba(0,255,0,0.6)");
				markedBdColor.push("rgba(0,255,0,1)");
				
				unmarked.push(item.unmarked);
				unMarkedBgColor.push("rgba(255, 0, 0, 0.8)");
				unMarkedBdColor.push("rgba(255, 0, 0, 1)");
				
				
				leave.push(item.leave);
				leaveBgColor.push("rgba(255,0,255,0.6)");
				leaveBdColor.push("rgba(255,0,255,1)");
				
				
			});
			
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
			          label: "Attendance Unmarked",
			          data: unmarked,
			          backgroundColor: unMarkedBgColor,
			          borderColor: unMarkedBdColor,
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
			        fontColor: "#000000",
			        fontSize: 16
			      }
			    },
			    scales: {
			    	xAxes: [{
			    		offset: true,
			            stacked: true,
			            //barPercentage: 1.4,
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