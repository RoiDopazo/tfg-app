function openModalDelete(id, group, entity) {
	bootbox
			.confirm({
					message:"Seguro que quiere eliminar este elemento?",
					buttons: {
				        confirm: {
				            label: 'OK',
				            className: 'btn-primary'
				        },
				        cancel: {
				            label: 'Cancelar',
				            className: 'btn-default'
				        }
				    },
					callback: function(result) {
						if (result) {
							$
									.ajax({
										type : "DELETE",
										url : "/admin/panel/" + group
												+ "/ajax/" + entity + "/" + id,
										contentType : "application/json",
										success : function(result) {
											bootbox
													.alert({
														message : "Elemento eliminado correctamente."
													});
											resetFilter(entity);
										},
										error : function(error) {
											bootbox
													.alert({
														message : "Se ha producido un error intentado eliminar. Intentelo de nuevo."
													});
										}
									});
						} else {

						}
				    }});
};

function openModalEventDayDelete(idEvent, idDay) {
	bootbox
			.confirm({
				message:"Seguro que quiere eliminar este elemento?",
				buttons: {
			        confirm: {
			            label: 'OK',
			            className: 'btn-primary'
			        },
			        cancel: {
			            label: 'Cancelar',
			            className: 'btn-default'
			        }
			    },
				callback: function(result) {
						if (result) {
							$
									.ajax({
										type : "DELETE",
										url : "/admin/panel/events/ajax/eventday/"
												+ idEvent + "/day/" + idDay,
										contentType : "application/json",
										success : function(result) {
											resetFilter("eventday");
											bootbox
													.alert({
														message : "Elemento eliminado correctamente."
													});
										},
										error : function(error) {
											bootbox
													.alert({
														message : "Se ha producido un error intentado eliminar. Intentelo de nuevo."
													});
										}
									});
						} else {

						}
				}});
}


function openModalAdd(group, entity) {
	var object = {};
	if (entity == "route") {
		var keys = [ "name", "photo", "lat", "lng", "city", "country", "state",
				"creationDate", "startDate", "endDate", "numDays", "numPlaces",
				"distance", "time", "priv", "userId" ];
	}

	if (entity == "routeday") {
		var keys = [ "idRoute", "idDay", "startTime", "realTimeData" ];
	}

	if (entity == "stay") {
		var keys = [ "idRoute", "idDay", "order", "time", "travelTime",
				"travelDistance", "travelMode", "type", "idPlace",
				"idEventPlace" ];
	}

	if (entity == "event") {
		var keys = [ "name", "description", "city", "startDate", "endDate" ];
	}

	if (entity == "eventplace") {
		var keys = [ "idEvent", "idDay", "name", "description", "lat", "lng",
				"address", "startHour", "endHour" ];
	}
	
	if (entity == "user") {
		var keys = ["username", "password", "email", "creationDate", "token", "role"];
	}
	
	var bootboxText = "#" + entity + "-add-div";
	var bootboxForm = entity + "-add-form";
	var bootboxHtml = $(bootboxText).html().replace(bootboxForm,
			'js-bootboxForm');
	bootbox
			.confirm({
				message: bootboxHtml,
				buttons: {
			        confirm: {
			            label: 'Ok',
			            className: 'btn-primary'
			        },
			        cancel: {
			            label: 'Cancelar',
			            className: 'btn-default'
			        }
			    },
				callback: function(result) {
						if (result) {
							for (variable in keys) {
								var text = "#input-add-" + entity + "-"
										+ keys[variable];
								if ($(text).is("input")) {
									if ($(text).attr("type") == "date") {
										if (($(text, '.js-bootboxForm').val()) == "") {
										} else {
											var date = new Date(Date.parse($(
													text, '.js-bootboxForm')
													.val()));
											var milliseconds = date.getTime();
											object[keys[variable]] = milliseconds;
										}
									} else if ($(text).attr("type") == "time") {
										var hour = moment($(text,
												'.js-bootboxForm').val(),
												"HH:mm");
										var ms = hour.hours() * 3600000
												+ hour.minutes() * 60000;
										object[keys[variable]] = ms;
									} else {
										object[keys[variable]] = $(text,
												'.js-bootboxForm').val();
									}
								}
								if ($(text).is("textarea")) {
									object[keys[variable]] = $(text,
											'.js-bootboxForm').val();
								}
								if ($(text).is("select")) {
									object[keys[variable]] = $(text,
											'.js-bootboxForm').val();
								}

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
														message : "Entidad a\u00f1adida correctamente.",
														backdrop : true
													});
										},
										error : function(err) {
											bootbox
													.alert({
														message : "Se ha producido un error intentado a\u00f1adir la entidad. Intentelo de nuevo."
													});
										}
									});
						}
				}});
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
							if ($(text).attr("type") == "date") {
								console.log(object[keys[variable]]);
								if (object[keys[variable]] != null) {
									var date = new Date(object[keys[variable]]);
									$(text).attr('value',
											moment(date).format('YYYY-MM-DD'));
								}
							} else if ($(text).attr("type") == "time") {
								var date = moment.utc(object[keys[variable]])
										.format("HH:mm");
								$(text).attr('value', date);
							} else {
								$(text).attr('value', object[keys[variable]]);
							}
						}
						if ($(text).is("textarea")) {
							$(text).text(object[keys[variable]]);
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
							.confirm({
								message: bootboxHtml,
								buttons: {
							        confirm: {
							            label: 'Ok',
							            className: 'btn-primary'
							        },
							        cancel: {
							            label: 'Cancelar',
							            className: 'btn-default'
							        }
							    },
								callback: function(result) {
										if (result) {
											for (variable in keys) {
												var text = "#input-up-"
														+ entity + "-"
														+ keys[variable];
												if ($(text).is("input")) {
													if ($(text).attr("type") == "date") {
														if (($(text,
																'.js-bootboxForm')
																.val()) == "") {
														} else {
															var date = new Date(
																	Date
																			.parse($(
																					text,
																					'.js-bootboxForm')
																					.val()));
															var milliseconds = date
																	.getTime();
															object[keys[variable]] = milliseconds;
														}
													} else if ($(text).attr(
															"type") == "time") {
														var hour = moment(
																$(text,
																		'.js-bootboxForm')
																		.val(),
																"HH:mm");
														var ms = hour.hours()
																* 3600000
																+ hour
																		.minutes()
																* 60000;
														object[keys[variable]] = ms;
													} else {
														object[keys[variable]] = $(
																text,
																'.js-bootboxForm')
																.val();
													}
												}
												if ($(text).is("textarea")) {
													object[keys[variable]] = $(
															text,
															'.js-bootboxForm')
															.val();
												}
												if ($(text).is("select")) {
													object[keys[variable]] = $(
															text,
															'.js-bootboxForm')
															.val();
												}

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
																		message : "Entidad modificada correctamente.",
																		backdrop : true
																	});
														},
														error : function(err) {
															bootbox
																	.alert({
																		message : "Se ha producido un error intentado modificar la entidad. Intentelo de nuevo."
																	});
														}
													});
										}
								}});
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
	if ((entity == "eventday") || (entity == "eventplace")) {
		prefilter = "event=" + $(field1).val() + "&day=" + $(field2).val()
				+ "&";
	}
	if (entity == "subcategory") {
		prefilter = "category=" + $(field1).val() + "&";
	}
	var field = $(fieldHtmlId).val();
	var value = $(valueHtmlId).val();
	var value_replace = value.replace(/ /g, '+');
	var content = $(contentHtmlId);

	$(loaderHtmlId).show();
	$(contentHtmlId).hide();
	content.load("/admin/panel/" + group + "/ajax/" + entity + "?" + prefilter
			+ "filterBy=" + field + "&value=" + value_replace + "&index="
			+ index, function() {
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

