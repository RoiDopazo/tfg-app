function openModalDelete(id, group, entity) {
	bootbox
			.confirm(
					"Are you sure want to delete?",
					function(result) {
						if (result) {
							$
									.ajax({
										type : "DELETE",
										url : "/admin/panel/" + group
												+ "/ajax/" + entity + "/" + id,
										contentType : "application/json",
										success : function(result) {
											resetFilter(entity);
											bootbox
													.alert({
														message : "Entity has been successfully removed"
													});
										},
										error : function(error) {
											bootbox
													.alert({
														message : "An error occurred while removing the indicated entity. Try it again."
													});
										}
									});
						} else {

						}
					});
};

function openModalAdd(group, entity) {
	var object = {};
	if (entity == "route") {
		var keys = ["name", "photo", "lat", "lng", "city", "country", "state", "creationDate", "startDate", "endDate", "numDays", "numPlaces", "distance", "time", "priv", "userId"];
	}
	if (entity == "routeday") {
		var keys = ["idRoute", "idDay", "startTime", "realTimeData"];
	}
	if (entity == "stay") {
		var keys = ["idRoute", "idDay", "order", "time", "travelTime", "travelDistance", "travelMode", "type", "idPlace", "idEventPlace"];
		
	}
	var bootboxText = "#" + entity + "-add-div";
	var bootboxForm = entity + "-add-form";
	var bootboxHtml = $(bootboxText).html().replace(bootboxForm,
			'js-bootboxForm');
	bootbox
			.confirm(
					bootboxHtml,
					function(result) {
						if (result) {
							for (variable in keys) {
								var text = "#input-add-" + entity + "-"
										+ keys[variable];							
								object[keys[variable]] = $(text,
										'.js-bootboxForm').val();
							}
							console.log(object);
							// hacer peti update
							$
									.ajax({
										type : "POST",
										url : "/admin/panel/" + group
												+ "/ajax/" + entity,
										contentType : "application/json",
										data : JSON.stringify(object),
										success : function(done) {
											resetFilter(entity);
											bootbox
													.alert({
														message : "Entity has been added correctly",
														backdrop : true
													});
										},
										error : function(err) {
											bootbox
													.alert({
														message : "An error occurred while adding the indicated entity. Try it again."
													});
										}
									});
						}
					});
}

var openModalEdit = function(id, group, entity) {
	$
			.ajax({
				type : "GET",
				url : "/admin/panel/" + group + "/ajax/object/" + entity + "/"
						+ id,
				contentType : "application/json",
				success : function(object) {

					var keys = Object.keys(object);
					console.log(keys);
					for (variable in keys) {
						var text = "#input-up-" + entity + "-" + keys[variable];
						if ($(text).is("input")) {
							$(text).attr('value', object[keys[variable]]);
						}
						if ($(text).is("select")) {
							$(
									text + " option[value='"
											+ object[keys[variable]] + "']")
									.attr('selected', true);
						}
					}

					var bootboxText = "#" + entity + "-edit-div";
					var bootboxForm = entity + "-edit-form";
					var bootboxHtml = $(bootboxText).html().replace(
							bootboxForm, 'js-bootboxForm');
					bootbox
							.confirm(
									bootboxHtml,
									function(result) {
										if (result) {
											for (variable in keys) {
												var text = "#input-up-"
														+ entity + "-"
														+ keys[variable];
												object[keys[variable]] = $(
														text, '.js-bootboxForm')
														.val();
											}
											console.log(object);
											// hacer peti update
											$
													.ajax({
														type : "PUT",
														url : "/admin/panel/"
																+ group
																+ "/ajax/"
																+ entity + "/"
																+ id,
														contentType : "application/json",
														data : JSON
																.stringify(object),
														success : function(done) {
															resetFilter(entity);
															bootbox
																	.alert({
																		message : "Entity has been modified correctly",
																		backdrop : true
																	});
														},
														error : function(err) {
															bootbox
																	.alert({
																		message : "An error occurred while updating the indicated entity. Try it again."
																	});
														}
													});
										}
									});
				}
			});

};

function filterBy(group, entity, index) {
	console.log(index);
	var fieldHtmlId = "#" + entity + "-select-filter";
	var valueHtmlId = "#" + entity + "-input-filter";
	var contentHtmlId = "#" + entity + "-content";
	var loaderHtmlId = "#" + entity + "-loader";
	var field1 = "#" + entity + "-input-field1";
	var field2 = "#" + entity + "-input-field2";
	var prefilter = "";
	if (entity == "route") {
		prefilter = "user=" + $(field1).val() + "&";
	}
	if (entity == "routeday") {
		prefilter = "route=" + $(field1).val() + "&";
	}
	if (entity == "stay") {
		prefilter = "route=" + $(field1).val() + "&day=" + $(field2).val()
				+ "&";
	}
	var field = $(fieldHtmlId).val();
	var value = $(valueHtmlId).val();
	var content = $(contentHtmlId);

	$(loaderHtmlId).show();
	$(contentHtmlId).hide();
	content.load("/admin/panel/" + group + "/ajax/" + entity + "?" + prefilter
			+ "filterBy=" + field + "&value=" + value + "&index=" + index,
			function() {
				$(loaderHtmlId).hide();
				$(contentHtmlId).show();
			});
};

function resetFilter(entity) {
	var string = "#" + entity + "-button-select";
	var valueHtmlId = "#" + entity + "-input-filter";
	var value = $(valueHtmlId).value = "";
	$(string).click();
};

