$(document).ready(function() {
	$('#todaySummaryDateMsg').empty();
	$('#txtEditorMsg').empty();
	$("#txtEditor").richText();
	
	$("#todaySummaryDate").datepicker({autoclose:true}).on("change", function() {
		 $('.datepicker').hide();
		 getSummaryByDate();
	});
	
	$("#todaySummarySubmit").click(function(){ 
		$('#todaySummaryDateMsg').empty();
		$('#txtEditorMsg').empty();
		$("#summaryAddSuccessModal").modal("hide");
		var date = $("#todaySummaryDate").val();
		var editorValue = $("#txtEditor").val().trim();
		
		if(!date.trim()){
			$('#todaySummaryDateMsg').attr('style','display: block;margin-top: -20px !important;');
			$("#todaySummaryDateMsg").append("<font color='red'>Please select date</font>");
			return false;
		} else {
			$('#todaySummaryDateMsg').attr('style','display: none;');
			$('#todaySummaryDateMsg').empty();
		}
		if(editorValue == '' || editorValue == null || editorValue == '<div><br></div>'){
			$('#txtEditorMsg').attr('style','display: block;margin-top: -20px !important;');
			$("#txtEditorMsg").append("<font color='red'>Please add summary</font>");
			return false;
		} else {
			$('#txtEditorMsg').attr('style','display: none;');
			$('#txtEditorMsg').empty();
		}
		editorValue = editorValue.replace( /[\s\n\r]+/g, ' ' );
		while(editorValue.indexOf("\"") != -1){
			editorValue = editorValue.replace("\"", "!@!@!@");
		}
		
        var url = urlForServer+"summary/addTodaySummary";
		var datastr = '{"date":"'+date+'","todaysummary":"'+editorValue+'","submittedby":"'+userInfoMap.get("username")+'"}';
		$.ajax({
			contentType: 'application/json; charset=utf-8',
			type : 'POST',
			url : url,
			data : datastr,
			datatype: 'html',
			content: "application/json; charset=utf-8",
			success : function(responseText) {
				$("#summaryAddSuccessModal").modal("show");
				$("#todaySummaryDate").val("");
				$(".richText-editor").empty()
			},error : function() {
				alert("Server error while adding today's summary");
			}
		});
	});
	
	function getSummaryByDate(){
		var date = $("#todaySummaryDate").val();
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
				var responseData = '';
				if(responseText != ''){
					responseData = responseData + responseText.summary;
					while(responseData.indexOf("!@!@!@") != -1){
						responseData = responseData.replace("!@!@!@", "\"");
					}
				}
				$(".richText-editor").html(responseData);
			},error : function() {
				alert("Server error while fetching today's summary by date");
			}
		});
	}
});