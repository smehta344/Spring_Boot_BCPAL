/**
 * 
 */
$(document).ready(function(){
	
	$("#startDate").datepicker("setDate", "-2d").
	datepicker({autoclose:true}).on("change", function() {
		 $('.datepicker').hide();
		 getDeliverySummaryList();
		 getTodaySummary();
	  });
	getDeliverySummaryList();
	getTodaySummary();
	
	function getTodaySummary(){
		$("#todaySummaryDiv").empty();
		var date = $("#startDate").val();
		var url = urlForServer+"summary/getTodaySummary";
		$.ajax({
			contentType: 'application/json; charset=utf-8',
			type : 'GET',
			url : url,
			data : {
				fromDate:date
			},
			datatype: 'html',
			content: "application/json; charset=utf-8",
			success : function(responseText) {
				var todaySummaryText = "<p style='font-size:20px;margin-bottom: -10px;'><b><u>Overall Summary for ["+date+"]:</b></u></p><br>";
				todaySummaryText = todaySummaryText + "<div class='card scrollCard' style='max-height:300px;'><div class='card-body'>";
				if(responseText != ''){
					var responseData = responseText.summary;
					while(responseData.indexOf("!@!@!@") != -1){
						responseData = responseData.replace("!@!@!@", "\"");
					}
					todaySummaryText = todaySummaryText + responseData;
				} else {
					todaySummaryText = todaySummaryText + "<p><font color='red'>No records exists!!</font></p>";
				}
				todaySummaryText = todaySummaryText + "</div></div><br>"
				$("#todaySummaryDiv").append(todaySummaryText);
			},error : function() {
				alert("error added");
			}
		});
	}
	function getDeliverySummaryList(){
		var urlForAttendance = urlForServer+"dashboard/getDeliveryList";
		var selectedDateString = $("#startDate").val();
		var dateObj = new Date(selectedDateString);
		var quryDate = formatDate(dateObj);
	$.ajax({
		type : 'GET',
		url : urlForAttendance,
		data : {
			fromDate:quryDate
		},
		success : function(data) {
				  $("#deliveryTable").empty();
				  $("#deliveryTable").append("<tbody><thead style=background-color:#ebebe0 align= 'left'><tr class='d-flex' style = align='left'> <th class='col-2' align='center' scope='col'>Account</th><th class='col-1' align='center' bdcolor='#ff0000' scope='col'>Red</th><th class= 'col-1' align='center' scope='col'>Amber</th>" +
				  		"<th class='col-1' scope='col' align='center'>Green</th><th class ='col-1' scope='col'>Total</th>"
						  + "<th class='col-2' scope='col' align='center'>Hiring Update</th>" + "<th class='col-4' scope='col' align='center'>Remarks</th>");
				  if(data.length > 0){
					  
					  var remarkData = [];
					  
					  var hiringUpdateData = [];
					  
					  for(var i=0;i<data.length;i++){
						  var remark = "";
						  var hiringUpdate = "";
						  for(var j=0;j<data[i].planList.length;j++){
							  var projectsData = data[i].planList;
							  var statusByProject = projectsData[j];
							  remark = remark + "<p style='font-weight: bold;font-size:12pt;margin-bottom: 0px !important;font-family:Calibri,sans-serif;/* margin:0 0 0 36pt; */'><span style='font-size:10pt;font-family:Verdana,sans-serif;'>" + statusByProject.projectName + ":" + "</span></p>";
							  hiringUpdate = hiringUpdate + "<p style='font-weight: bold; margin-bottom: 0px !important;font-size:12pt;font-family:Calibri,sans-serif;/* margin:0 0 0 36pt; */'><span style='font-size:10pt;font-family:Verdana,sans-serif;'>" + statusByProject.projectName + ":" + "</span></p>";;
//							  if(statusByProject.deliveryChallenge != undefined){
//								  var decodedData = decodeURIComponent(statusByProject.deliveryChallenge)
//								  var dataWithLineBreak = decodedData.replace(/(?:\r\n|\r|\n)/g, '<br>');
////								  remark = remark +  "<p style='margin-bottom: 2px;font-size:12pt;font-family:Calibri,sans-serif;/* margin:0 0 0 36pt; */'><span style='font-size:10pt;font-family:Verdana,sans-serif;'>" + "Delivery challenge" + ":" + "</span></p>";
//								  remark = remark +  "<p class='remarks' style=' word-wrap: break-word;margin-left:.75in; text-indent:0in'><span style='font-size:10.0pt; font-family:&quot;Verdana&quot;,sans-serif'>" + dataWithLineBreak +
//								  "</span></p>";
//							  }
//							  if(statusByProject.wfhChallenge != undefined){
//								  var decodedData = decodeURIComponent(statusByProject.wfhChallenge)
//								  var dataWithLineBreak = decodedData.replace(/(?:\r\n|\r|\n)/g, '<br>');
////								  remark = remark +  "<p style='margin-bottom: 2px;font-size:12pt;font-family:Calibri,sans-serif;/* margin:0 0 0 36pt; */'><span style='font-size:10pt;font-family:Verdana,sans-serif;'>" + "WFH challenge" + ":" + "</span></p>";
//								  remark = remark +  "<p class='remarks' style='word-wrap: break-word;margin-left:.75in; text-indent:0in'><span style='font-size:10.0pt; font-family:&quot;Verdana&quot;,sans-serif'>" + dataWithLineBreak +
//								  "</span></p>";
//							  }
//							  if(statusByProject.deliveryMitigationPlan != undefined){
//								  var decodedData = decodeURIComponent(statusByProject.deliveryMitigationPlan)
//								  var dataWithLineBreak = decodedData.replace(/(?:\r\n|\r|\n)/g, '<br>');
////								  remark = remark +  "<p style='margin-bottom: 2px;font-size:12pt;font-family:Calibri,sans-serif;/* margin:0 0 0 36pt; */'><span style='font-size:10pt;font-family:Verdana,sans-serif;'>" + "Delivery Mitigation Plan" + ":" + "</span></p>";
//								  remark = remark +  "<p class='remarks' style='word-wrap: break-word;margin-left:0.75in; text-indent:0in'><span style='font-size:10.0pt; font-family:&quot;Verdana&quot;,sans-serif'>" + dataWithLineBreak +
//								  "</span></p>";
//							  }
//							  if(statusByProject.wfhMitigationPlan != undefined){
//								  var decodedData = decodeURIComponent(statusByProject.wfhMitigationPlan)
//								  var dataWithLineBreak = decodedData.replace(/(?:\r\n|\r|\n)/g, '<br>');
////								  remark = remark +  "<p style='font-size:12pt;margin-bottom: 2px;font-family:Calibri,sans-serif;/* margin:0 0 0 36pt; */'><span style='font-size:10pt;font-family:Verdana,sans-serif;'>" + "WFH Miitigation Plan" + ":" + "</span></p>";
//								  remark = remark +  "<p class='remarks' style='word-wrap: break-word;margin-left:0.75in; text-indent:0in'><span style='font-size:10.0pt; font-family:&quot;Verdana&quot;,sans-serif'>" + dataWithLineBreak +
//								  "</span></p>";
//							  }
							  
							  if(statusByProject.remarks != undefined){
							  var decodedData = decodeURIComponent(statusByProject.remarks)
							  var dataWithLineBreak = decodedData.replace(/(?:\r\n|\r|\n)/g, '<br>');
//							  remark = remark +  "<p style='font-size:12pt;margin-bottom: 2px;font-family:Calibri,sans-serif;/* margin:0 0 0 36pt; */'><span style='font-size:10pt;font-family:Verdana,sans-serif;'>" + "WFH Miitigation Plan" + ":" + "</span></p>";
							  remark = remark +  "<p class='remarks' style='word-wrap: break-word;margin-left:0.75in; text-indent:0in'><span style='font-size:10.0pt; font-family:&quot;Verdana&quot;,sans-serif'>" + dataWithLineBreak +
							  "</span></p>";
							  }
							 
							  if(statusByProject.hiringUpdate != undefined){
								  var decodedData = decodeURIComponent(statusByProject.hiringUpdate)
								  var dataWithLineBreak = decodedData.replace(/(?:\r\n|\r|\n)/g, '<br>');
								  hiringUpdate = hiringUpdate +  "<p class='remarks' style='word-wrap: break-word;margin-left:0in; text-indent:0in'><span style='font-size:10.0pt; font-family:&quot;Verdana&quot;,sans-serif'>" + dataWithLineBreak +
								  "</span></p>";
							  }
						  }
						  remarkData[i] = remark;
						  hiringUpdateData[i] = hiringUpdate;
					  }
					  
					  
				  for(var i in data){
					  $("#deliveryTable").append("<tr  class='d-flex' style=background-color:#ebebe0 overflow-wrap: break-word align='left' ondblClick=changeRowColor(this)><td class='col-2'>"+ data[i].account+"</td>" + 
					  		"<td class='col-1' style=background-color:#ff3333> <button class='btn' name='red' type=button style=width:75px;border-radius:10px onClick=changeRowColor(this)>"+data[i].redCount+"</button></td>"+ "<td class = 'col-1' style=background-color:#FFBF00> <button class='btn' name ='amber' type=button onClick=changeRowColor(this) style=width:75px;border-radius:10px>"+data[i].amberCount+"</button></td>" +
					  				"<td class='col-1' style=background-color:#99e699><button class='btn' name='green' type=button style=width:75px;border-radius:10px onClick=changeRowColor(this)>"+data[i].greenCount+"</button></td>" + "<td class='col-1'>" + "<button class='btn' type=button style=width:75px;border-radius:10px>" + data[i].total+"</td>" +
					  						"<td class='col-2' style=background-color:#ebebe0>" + hiringUpdateData[i] +"</td>" + "<td class='col-4' style=background-color:#ebebe0>" + remarkData[i] + "</td>" + "</tr>");
				  }
				  }
				  else{
					  $("#deliveryTable").append("<tr  class='d-flex' style=background-color:#ebebe0 align='left' ondblClick=changeRowColor(this)><td class='col-2'>"+ "" +"</td>" + 
						  		"<td class='col-1' style=background-color:#ff3333></td>"+ "<td class = 'col-1' style=background-color:#FFBF00></td>" +
						  				"<td class='col-1' style=background-color:#99e699></td>" + "<td class='col-1'>" +""+"</td>"+ 
						  				"<td class='col-2'>" +""+"</td>" + "<td class='col-4'>" +""+"</td>" + "</tr>");
				  }
				  $("#deliveryTable").append("</tbody>");
		},error : function() {
			alert("Server error while fetching account");
		}
	});
	
	}
	
});

function changeRowColor(e){
	var pickedup = null;
	if (pickedup != null) {
        pickedup.css( "background-color", "#FFFFFF" );
    }
    $("#fillname").val($(this).find("td").eq(1).html());
    $( this ).css( "background-color", "#ccebff" );

    pickedup = $( this );
    createModalTable(e);
}

function createModalTable(eventObj){
	var urlForAttendance = urlForServer+"dashboard/getDeliveryByProject";
	var cellObj = eventObj.parentElement;
	var rowObj = cellObj.parentElement;
	var accountName = rowObj.cells[0].innerText;
	var selectedDateString = $("#startDate").val(); //deliveryChallenge
	var dateObj = new Date(selectedDateString);
	var quryDate = formatDate(dateObj);
	var name = eventObj.name;
$.ajax({
	type : 'GET',
	url : urlForAttendance,
	data : {
		accountName:accountName,
		status:name,
		fromDate:quryDate
	},
	success : function(data) {
		
		if(name == 'hiringUpdate'){
			$("#hiringTable").empty();
			  $("#hiringTable").append("<tbody><thead style=background-color:#ebebe0 align= 'left'><tr class='d-flex'> <th class='col-4' align= 'left' scope='col'>Project Name</th><th class='col-8' align='left' bdcolor='#ff0000' cope='col'>Hiring updates</th>)");
			  
			  for(var i in data){
				  $("#hiringTable").append("<tr align='left' height: 'fit-content' class='d-flex' ondblClick=changeRowColor()><td align='left' class='col-4'>"+ data[i].projectName+"</td>" + 
				  		"<td align='left' class='col-8' ondblClick=changeRowColor()>"+decodeURIComponent(data[i].hiringUpdate)+"</td></tr>");
			  }
			  
			  $("#hiringTable").append("</tbody>");
			  $('#updateModal').modal('show');
		}
		else{
			  $("#modalTable").empty();
			  $("#modalTable").append("<tbody><thead style=background-color:#ebebe0 align= 'left'><tr class='d-flex'> <th class='col-2' align= 'left' scope='col'>Project Name</th><th class='col-3' align='left' bdcolor='#ff0000' cope='col'>Delivery Challenges</th><th class='col-3' align='left' scope='col'>Mitigation Plan</th>" +
			  		"<th class='col-2' scope='col' align='left'>Milestone</th><th class='col-2' align='left' scope='col'>Deliverables of the day</th>");
			  for(var i in data){
				  $("#modalTable").append("<tr height: 'fit-content' align='left' class='d-flex' ondblClick=changeRowColor()><td align='left' class='col-2'>"+ decodeURIComponent(data[i].projectName)+"</td>" + 
				  		"<td align='left' class='col-3' ondblClick=changeRowColor()>"+decodeURIComponent(data[i].deliveryChallenge)+"</td><td align='left' class='col-3'>"+decodeURIComponent(data[i].wfhMitigationPlan)+"</td>" +
				  				"<td align='left' class='col-2'>"+decodeURIComponent(encodeURIComponent(data[i].milestone))+"</td><td align='left' class='col-2'>"+decodeURIComponent(encodeURIComponent(data[i].keyDeliverable))+"</td></tr>");
				  $("#modalTable").append("</tbody>");
				  $('#myModal').modal('show');
			  }
		}
	},error : function() {
		alert("Server error while fetching account");
	}
});

}

function CloseModalPopup() {       
    $("#updateModal").close();
}