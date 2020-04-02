function logout(){
    window.location.href="index.html";
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
	
    $('#currentDate').datetimepicker({
    	format: 'yyyy-mm-dd'
    	});
    
});
