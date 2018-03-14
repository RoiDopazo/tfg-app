function openModalDelete(id, group, entity) {
	console.log(id);
	console.log(entity);
	bootbox.confirm("Are you sure want to delete?", function(result) {
		if (result) {
			$.ajax({
				type : "DELETE",
				url : "/admin/panel/" + group +  "/ajax/" + entity + "/" + id,
				contentType : "application/json",
				success : function(result) {
					resetFilter(entity);
					console.log("result");
					bootbox
					.alert({
						message : "Entity has been successfully removed"
					});
				},
				error : function(error) {
					console.log(error);
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

var openModalEdit = function(id, group, entity) {
	$
			.ajax({
				type : "GET",
				url : "/admin/panel/" + group + "/ajax/object/" + entity + "/" + id,
				contentType : "application/json",
				success : function(object) {

					var keys = Object.keys(object);
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
											// hacer peti update
											$
													.ajax({
														type : "PUT",
														url : "/admin/panel/ajax/"
																+ entity
																+ "/"
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

function filterBy(entity) {
	var fieldHtmlId = "#" + entity + "-select-filter";
	var valueHtmlId = "#" + entity + "-input-filter";
	var contentHtmlId = "#" + entity + "-content";
	var loaderHtmlId = "#" + entity + "-loader";
	var field = $(fieldHtmlId).val();
	var value = $(valueHtmlId).val();
	var content = $(contentHtmlId);

	$(loaderHtmlId).show();
	$(contentHtmlId).hide();
	content.load("/admin/panel/ajax/" + entity + "?filterBy=" + field
			+ "&value=" + value, function() {
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
