var isExcel = function(name){
	return name.match()
}

$(document).ready(function() {
	
	$(".custom-file-input").on("change", function() {
		  var fileName = $(this).val().split("\\").pop();
		  $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
		});
	
	$('INPUT[type="file"]').change(function(){
		var ext = this.value.match(/\.(.+)$/)[1];
		$('#fileTypeErrorMsg').empty();
		if(ext == 'xlsx'){
			$('#uploadBtn').attr('disabled', false);
		}
		else{
			$('#uploadBtn').attr('disabled', true);
			$('#fileTypeErrorMsg').append("<p style='margin-top: -20px;'><font color='red'>Incorrect file format. Please select xlsx file</font><p>");
		}
	});
	$("#uploadBtn").click(function () {
		$('#fileTypeErrorMsg').empty();
		if($('INPUT[type="file"]').val() == ''){
			$('#fileTypeErrorMsg').append("<p style='margin-top: -20px;'><font color='red'>Please select any file</font><p>");
			return false;
		}
		
		var fileUploadType = $("#fileType").val();
		if(fileUploadType=='Attendance'){
			$('#checklist1').prop('checked', false);
	        $('#checklist2').prop('checked', false);
	        $('#checklist3').prop('checked', false);
	        $("#checklistProceed").attr('disabled',true);
	        $("#checklistModal").modal('show');
		} else {
			
			
			//TODO: Add delivery summary section here
			fire_ajax_submit();
		}
		
       
    });
	
	$("#allCheckboxes").change(function() {
		var checklist1 = $('#checklist1').is(':checked');
		var checklist2 = $('#checklist2').is(':checked');
		var checklist3 = $('#checklist3').is(':checked');
		if(checklist1 && checklist2 && checklist3){
			$("#checklistProceed").attr('disabled',false);
		} else {
			$("#checklistProceed").attr('disabled',true);
		}
	});

	$("#checklistProceed").click(function () {
		$("#checklistModal").modal('hide');
        fire_ajax_submit();
	});
	
	
});
function fire_ajax_submit() {
	$('.loader').attr('style','display: block;');
	$("#fileUploadModal").modal('hide');
	$(".modalContent").empty();
	var fileUploadType = $("#fileType").val();
    // Get form
    var form = $('#fileForm')[0];

    var data = new FormData(form);
    
    var urlVal = urlForServer+"dashboard/uploadFile";

    data.append("fileUploadType", fileUploadType);

    $("#uploadBtn").prop("disabled", true);
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: urlVal,
        data: data,
        processData: false, //prevent jQuery from automatically transforming the data into a query string
        contentType: false,
        cache: false,
        //timeout: 600000,
        success: function (data) {
            console.log("SUCCESS : ", data);
            var success = '';
            success = success + "<div class='modal-body'><p><b><font color='green'>File uploaded successfully!!! <font><b></p></div>";
            success = success + "<div class='modal-footer'><button type='button' class='btn btn-primary' data-dismiss='modal'>OK</button></div>";
            $(".modalContent").append(success);
            $("#fileUploadModal").modal('show');
            $("#uploadBtn").prop("disabled", false);
            $('.loader').attr('style','display: none;');
            data = '';
            $(".custom-file-label").text('');
            $(".custom-file-label").text('Select excel file to upload');
        },
        error: function (e) {
        	var data = JSON.stringify(e);
        	var error = '';
            console.log("ERROR : ", data);
            $("#fileUploadModal").modal('show');
            $(".modalContent").empty();
            //error = error + "<div class='modal-header'><p><b><font color='red'>ERROR: "+e.responseText+"<font></b></p></div>";
            error = error + "<div class='modal-body'><p><font color='red'>ERROR: "+e.responseText+"<font></p></div>";
            error = error + "<div class='modal-footer'><button type='button' class='btn btn-primary' data-dismiss='modal'>OK</button></div>";
            $(".modalContent").append(error);
            $("#uploadBtn").prop("disabled", false);
            $('.loader').attr('style','display: none;');
        }
    });

}
