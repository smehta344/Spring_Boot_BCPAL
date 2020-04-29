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
		if(ext == 'xlsx'){
			$('#uploadBtn').attr('disabled', false);
		}
		else{
			$('#uploadBtn').attr('disabled', true);
			alert('This is not an allowed file type.');
		}
		
		$("#uploadBtn").click(function (event) {

	        event.preventDefault();

	        fire_ajax_submit();

	    });
	});
	
	
});
function fire_ajax_submit() {
	
    // Get form
    var form = $('#fileForm')[0];

    var data = new FormData(form);
    
    var urlVal = urlForServer+"dashboard/uploadFile";

    data.append("CustomField", "This is some extra data, testing");

    $("#uploadBtn").prop("disabled", true);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: urlVal,
        data: data,
        //http://api.jquery.com/jQuery.ajax/
        //https://developer.mozilla.org/en-US/docs/Web/API/FormData/Using_FormData_Objects
        processData: false, //prevent jQuery from automatically transforming the data into a query string
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {
            console.log("SUCCESS : ", data);
            $("#uploadBtn").prop("disabled", false);

        },
        error: function (e) {
            console.log("ERROR : ", e);
            $("#uploadBtn").prop("disabled", false);

        }
    });

}
