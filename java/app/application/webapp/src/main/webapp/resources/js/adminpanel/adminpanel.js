function openModalDelete(id, entity) {
	console.log(id);
	console.log(entity);
	bootbox.confirm("Are you sure want to delete?", function(result) {
		if (result) {
			$.ajax({
				type : "DELETE",
				url : "/admin/panel/ajax/" + entity + "/" + id,
				contentType : "application/json",
				success : function(result) {
					resetFilter(entity);
					console.log("result");
					alert("YES");
				},
				error : function(error) {
					console.log(error);
					alert("NO");
				}
			});
		} else {

		}
	});
};

var openModalEdit = function(id, entity) {
	$
			.ajax({
				type : "GET",
				url : "/admin/panel/ajax/object/" + entity + "/" + id,
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
																		message : "La entidad ha sido modificada correctamente",
																		backdrop : true
																	});
														},
														error : function(err) {
															bootbox
																	.alert({
																		message : "Se ha producido un error al updatear la entidad indicada"
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
	var contentHtmlId = "#" + entity + "Content";
	var field = $(fieldHtmlId).val();
	var value = $(valueHtmlId).val();
	var content = $(contentHtmlId);

	console.log(field);
	console.log(value);
	console.log(content);

	$("#routeLoader").show();
	$("#routeContent").hide();
	content.load("/admin/panel/ajax/" + entity + "?filterBy=" + field
			+ "&value=" + value, function() {
		$("#routeLoader").hide();
		$("#routeContent").show();
	});
};

function resetFilter(entity) {
	var string = "#" + entity + "-button-select";
	var valueHtmlId = "#" + entity + "-input-filter";
	var value = $(valueHtmlId).value = "";
	$(string).click();
};
