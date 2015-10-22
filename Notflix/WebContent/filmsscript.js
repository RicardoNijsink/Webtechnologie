$(document).ready(function(){
	$.ajax({
		dataType: 'json',
		url: "http://localhost:8080/Notflix/api/movies",
		headers: {
			"Authorization": localStorage.getItem("token")
		}
        		
	}).fail(function(jqXHR,	textStatus)	{	
		console.dir(jqXHR)
		$(".row").append(
          '<div class="col-sm-6 col-md-4">'+
		    '<div class="thumbnail">'+
		      '<div class="caption">'+
				  	'<div class="filmInformatieDiv">'+
				  		'<label>failed to get films</label><br>'+
				  	'</div>'+
		      '</div>'+
		    '</div>'+
		  '</div>'
		)
	}).done(function(data)  { 
		console.dir(data)
		$(data).each(function(i,val){
		$(".row").append(
          '<div class="col-sm-6 col-md-4">'+
		    '<div class="thumbnail">'+
		      '<img src="http://miftyisbored.com/wp-content/uploads/2011/03/captain-america-movie-poster.jpg" alt="filmFoto" class="filmFoto">'+
		      '<div class="caption">'+
		        '<h2 class="filmTitel">Filmtitel</h2>'+
				  	'<h3>Informatie</h3>'+
				  	'<div class="filmInformatieDiv">'+
				  		'<label>Volledige titel:'+val.titel+'</label><br>'+
					  	'<label>Regisseur:'+val.regisseur+'</label><br>'+
					  	'<label>Releasedatum:'+val.release_datum+'</label><br>'+
					  	'<label>Acteurs:</label><br>'+
					  	'<label>Gemiddelde rating:'+val.gemiddeldeRating+'</label>'+
				  	'</div>'+
		        '<p>'+
		        	'<a href="#" class="btn btn-primary" role="button">Button</a> <a href="#" class="btn btn-default"'+
		        	'role="button">Button</a>'+
		        '</p>'+
		      '</div>'+
		    '</div>'+
		  '</div>'
		)
		});
	});


});