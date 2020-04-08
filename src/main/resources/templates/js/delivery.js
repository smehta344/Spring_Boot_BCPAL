/**
 * 
 */
$(document).ready(function(){
	
	$("#startDate").datepicker("setDate", "-2d").
	datepicker({autoclose:true}).on("change", function() {
		 $('.datepicker').hide();
		 getDeliverySummaryList();
	  });
	getDeliverySummaryList();

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
				  $("#deliveryTable").append("<tbody><thead style=background-color:#ebebe0 align= 'center'><tr class='d-flex'> <th class='col-4' align='center' scope='col'>Account</th><th class='col-2' align='center' bdcolor='#ff0000' scope='col'>Red</th><th class= 'col-2' align='center' scope='col'>Amber</th>" +
				  		"<th class='col-2' scope='col' align='center'>Green</th><th class ='col-2' scope='col'>Grand total</th>");
				  if(data.length > 0){
				  for(var i in data){
					  $("#deliveryTable").append("<tr  class='d-flex' style=background-color:#ebebe0 ondblClick=changeRowColor(this)><td class='col-4'>"+ data[i].account+"</td>" + 
					  		"<td class='col-2' style=background-color:#ff3333> <button name='red' type=button style=width:75px;border-radius:10px onClick=changeRowColor(this)>"+data[i].redCount+"</button></td>"+ "<td class = 'col-2' style=background-color:#FFBF00> <button name ='amber' type=button onClick=changeRowColor(this) style=width:75px;border-radius:10px>"+data[i].amberCount+"</button></td>" +
					  				"<td class='col-2' style=background-color:#99e699><button name='green' type=button style=width:75px;border-radius:10px onClick=changeRowColor(this)>"+data[i].greenCount+"</button></td>" + "<td class='col-2'>" +data[i].total+"</td></tr>");
				  }
				  }
				  else{
					  $("#deliveryTable").append("<tr  class='d-flex' style=background-color:#ebebe0 ondblClick=changeRowColor(this)><td class='col-4'>"+ "" +"</td>" + 
						  		"<td class='col-2' style=background-color:#ff3333></td>"+ "<td class = 'col-2' style=background-color:#FFBF00></td>" +
						  				"<td class='col-2' style=background-color:#99e699></td>" + "<td class='col-2'>" +""+"</td></tr>");
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
    $('#myModal').modal('show');
    return false;
}

function createModalTable(eventObj){
	var urlForAttendance = urlForServer+"dashboard/getDeliveryByProject";
	var cellObj = eventObj.parentElement;
	var rowObj = cellObj.parentElement;
	var accountName = rowObj.cells[0].innerText;
	var selectedDateString = $("#startDate").val(); //deliveryChallenge
	var dateObj = new Date(selectedDateString);
	var quryDate = formatDate(dateObj);
	var stausValue = eventObj.name;
$.ajax({
	type : 'GET',
	url : urlForAttendance,
	data : {
		accountName:accountName,
		status:stausValue,
		fromDate:quryDate
	},
	success : function(data) {
			  $("#modalTable").empty();
			  $("#modalTable").append("<tbody><thead style=background-color:#ebebe0 align= 'left'><tr class='d-flex'> <th class='col-2' align= 'left' scope='col'>Project Name</th><th class='col-2' align='left' bdcolor='#ff0000' cope='col'>Delivery Challenges</th><th class='col-2' align='left' scope='col'>Mitigation Plan</th>" +
			  		"<th class='col-2' scope='col' align='left'>Milestone</th><th class='col-2' align='left' scope='col'>Deliverables of the day</th><th align='left' class='col-2' scope='col'>Hiring Update</th>");
			  for(var i in data){
				  $("#modalTable").append("<tr align='left' class='d-flex' height=60 ondblClick=changeRowColor()><td align='left' class='col-2'>"+ data[i].projectName+"</td>" + 
				  		"<td align='left' class='col-2' height=60 ondblClick=changeRowColor()>"+data[i].deliveryChallenge+"</td><td align='left' class='col-2'>"+data[i].wfhMitigationPlan+"</td>" +
				  				"<td align='left' class='col-2'>"+data[i].milestone+"</td><td align='left' class='col-2'>"+data[i].keyDeliverable+"</td><td align='left' class='col-2'>"+data[i].hiringUpdate+"</td></tr>");
			  }
			  $("#modalTable").append("</tbody>");
	},error : function() {
		alert("Server error while fetching account");
	}
});

}