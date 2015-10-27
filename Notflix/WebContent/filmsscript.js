$(document).ready(function(){
	$.ajax({
		dataType: 'json',
		url: "http://localhost:8080/Notflix/api/movies",
		headers: {
			"Authorization": localStorage.getItem("token")
		}
        		
	}).fail(function(jqXHR,	textStatus)	{	
		console.dir(jqXHR)
		$(".filmsRow").append(
          '<div class="col-sm-6 col-md-4">'+
		    '<div class="thumbnail">'+
		      '<div class="caption">'+
				 '<div class="filmInformatieDiv">'+
				  	'<label>Geen films gevonden</label><br>'+
				  '</div>'+
		      '</div>'+
		    '</div>'+
		  '</div>'
		);
		$("#loginForm").show();
		$("#usernav").hide();
	}).done(function(data)  { 
		console.dir(data)
		$(data).each(function(i,val){
			$(".filmsRow").append(
	          '<div class="col-sm-6 col-md-4">'+
			    '<div class="thumbnail">'+
			      '<img id = "'+i+'posterimage"src="http://miftyisbored.com/wp-content/uploads/2011/03/captain-america-movie-poster.jpg" alt="filmFoto" class="filmFoto">'+
			      '<div class="caption">'+
			        '<h2 class="filmTitel">'+val.titel+'</h2>'+
					  	'<h3>Informatie</h3>'+
					  	'<div class="filmInformatieDiv">'+
						  	'<label class="filmLabel">Regisseur: '+ val.regisseur +'</label><br>'+
						  	'<label class="filmLabel">Releasedatum: '+ val.release_datum +'</label><br>'+
						  	'<label class="filmLabel">IMDB-nummer: ' + val.imdb_nummer + '</label><br>'+
						  	'<label class="filmLabel">Lengte: ' + val.lengte + ' minuten</label><br>'+
						  	'<label class="filmLabel">Beschrijving: </label>' +
						  	'<label class="filmLabel" id="beschrijvingLabel">' + val.beschrijving + '</label><br>'+
						  	'<label class="filmLabel">Gemiddelde rating: '+ val.gemiddeldeRating +'</label>'+
					  	'</div>'+
					'<p>'+
			        	'<a href="#" class="btn btn-primary" role="button">Rating toevoegen</a> <a href="#" class="btn btn-default"'+
			        	'role="button">Button</a>'+
			        '</p>'+
			      '</div>'+
			    '</div>'+
			  '</div>'
			);

		$.ajax({
		dataType: 'json',
		url: "http://www.omdbapi.com/?i="+val.imdb_nummer+"&plot=full&r=json"
        		
		}).fail(function(jqXHR,	textStatus)	{	
			console.dir(jqXHR)
	}).done(function(data)  { 
		console.dir(data)
		console.log(i);
		console.log(data.Poster);
		$('#'+i+'posterimage').attr("src",data.Poster)
		console.log($('#'+i+'posterimage').src)
		
	});
		});
		
	});
	

});