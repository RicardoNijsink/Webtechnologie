$(document).ready(function(){
	console.log("doc loaded")
	console.log(localStorage.getItem("token"))
	
	if(localStorage.getItem("token") != null && localStorage.getItem("token").length > 0){
		$.ajax({
			dataType: 'json',
			url: "http://localhost:8080/Notflix/api/gebruikers/getbytoken",
			headers: {
				"Authorization": localStorage.getItem("token")
			}
        		
			}).fail(function(jqXHR,	textStatus)	{	
			console.dir(jqXHR)
			localStorage.setItem("token", "");
			}).done(function(data){ 
				$("#loginForm").hide();
				$("#usernav").show();
				console.dir(data)
				$(".gebruikersRow").append(
			'<div class="col-lg-4">'
	  		+ '<p class="text-center">'
	  			+ '<span class="glyphicon glyphicon-user icon-size"></span>'
			+ '</p>'
			+ '</div>'
			+ '<div class="col-lg-8">'
			 	+ '<p class="text-left"><strong id="loggedInUserFullName">'+data.voornaam+" "+data.tussenvoegsel+" "+data.achternaam+'</strong></p>'
			   	+ '<p class="text-left small" id="loggedInUserNickname">'+data.nickname+'</p>'
			   	+ '<p class="text-left">'
			   		+ '<a href="ratings.html" class="btn btn-primary btn-block btn-sm" id="ratingsPaginaButton">Bekijk ratings</a>'
			   	+ '</p>'
			+ '</div>');
		});
		
	}
	else{
		$("#loginForm").show();
		$("#usernav").hide();
	}
	
	$("#loginForm").submit(function(event) {
		/* stop form from submitting normally */
		console.log("form called")
		event.preventDefault();

		var postData = $(this).serializeArray();
		var formURL = $(this).attr("action");
		$.ajax({
			dataType: "json",
			type: "POST",
			url: formURL,
			data: postData

		}).fail(function(jqXHR, textStatus){ 
			console.dir(jqXHR);
			alert("Login mislukt")
		}).done(function(data){ 
			console.dir(data)
			console.log(data.token)
			localStorage.setItem("token", data.token);
			
			location.reload();
		});
	});
	
	//Laat het dropdown-menu verdwijnen en laat het loginForm weer zien.
	$("#logout").click(function() {
		localStorage.setItem("token", "");
		$("#loginForm").show();
		$("#usernav").hide();
		
		location.reload();
	});
});