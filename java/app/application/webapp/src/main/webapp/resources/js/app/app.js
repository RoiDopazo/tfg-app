var loadMyRoutes = function (status) {
	$("#selector-pending").removeClass("active");
	$("#selector-in_progress").removeClass("active");
	$("#selector-completed").removeClass("active");
	$("#myroutes-content").hide();
	$("#myroutes-loader").show();
	$("#myroutes-content").load("/myroutes/ajax/" +
			"getownroutes?filter=state&value=" 
			+ status, function() {
		$("#myroutes-loader").hide();
		$("#myroutes-content").show();
	});
	
	var text = "#selector-" + status.toLowerCase();
	$(text).addClass("active");
}


var getDateAsString = function(date1, date2) {
	console.log(date1);
	var text1 = moment(date1).utc().format("DD-MMM-YYYY");
   var text2 = moment(date2).utc().format("DD-MMM-YYYY");
   return text1 + " - " + text2;
  }