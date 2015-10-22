$(document).ready(function(){
	console.log("doc loaded")
	$("#formlogin").submit(function(event) {
		/* stop form from submitting normally */
		console.log("form called")
		event.preventDefault();
		alert("login submit called")

		var postData = $(this).serializeArray();
		var formURL = $(this).attr("action");
		$.ajax({
			dataType: "json",
			type: "POST",
			url: formURL,
			data: postData

		}).fail(function(jqXHR, textStatus) { 
			console.dir(jqXHR)
		}).done(function(data)  { 
			console.dir(data)
			console.log(data.token)
			localStorage.setItem("token", data.token);
			$("#formlogin").hide();
			$("#usernav").show();
		});
	});
});