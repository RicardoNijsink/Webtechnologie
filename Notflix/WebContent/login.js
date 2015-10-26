$(document).ready(function(){
	console.log("doc loaded")
	console.log(localStorage.getItem("token"))
	
	if(localStorage.getItem("token").length > 0){
		$("#loginForm").hide();
		$("#usernav").show();
		$.ajax({
			dataType: 'json',
			url: "http://localhost:8080/Notflix/api/gebruikers/getbytoken",
			headers: {
				"Authorization": localStorage.getItem("token")
			}
        		
			}).fail(function(jqXHR,	textStatus)	{	
			console.dir(jqXHR)
			$("tbody").append("<tr>"+
    	        "<p>failed</p>"+
    	      "</tr>"); 
			}).done(function(data){ 
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
			   		+ '<a href="#" class="btn btn-primary btn-block btn-sm">ratings</a>'
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
		}).done(function(data){ 
			console.dir(data)
			console.log(data.token)
			localStorage.setItem("token", data.token);
			$("#loginForm").hide();
			$("#usernav").show();
			
			$(".gebruikersRow").append(
			'<div class="col-lg-4">'
  			+ '<p class="text-center">'
  				+ '<span class="glyphicon glyphicon-user icon-size"></span>'
			+ '</p>'
		    + '</div>'
		    + '<div class="col-lg-8">'
		    	+ '<p class="text-left"><strong id="loggedInUserFullName">...</strong></p>'
		    	+ '<p class="text-left small" id="loggedInUserEmail">...</p>'
		    	+ '<p class="text-left">'
		    		+ '<a href="#" class="btn btn-primary btn-block btn-sm">...</a>'
		    	+ '</p>'
		    + '</div>');
			
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