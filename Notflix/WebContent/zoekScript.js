$(document).ready(function(){
	$("#zoekbalk").submit(function(event) {
		zoek();
	});
});

function zoek(){
		console.log("form called")
		event.preventDefault();
		console.log($('#inputInvoerveld').val())

		var postData = $(this).serializeArray();
		var formURL = $(this).attr("action");
		$.ajax({
			dataType: "json",
			type: "GET",
			url: "../api/movies/TitelContains/"+$('#inputInvoerveld').val(),
			headers: {
				"Authorization": localStorage.getItem("token")
			}
		}).fail(function(jqXHR,	textStatus)	{	
			console.dir(jqXHR)
			$(".filmsRow").empty();
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
			$(".filmsRow").empty();
			$(data).each(function(i,val){
				var gemiddelde = val.gemiddeldeRating;
				
				if(isNaN(val.gemiddeldeRating)){
					gemiddelde = "Geen gemiddelde rating";
				}
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
							  	'<label class="filmLabel">Gemiddelde rating: '+ gemiddelde +'</label>'+
						  	'</div>'+
						'<p>'+
							'<button class="buttonNotflix" id="buttonRatingToevoegen'+ i + '" hidden>Rating toevoegen</button>' +
							'<button class="buttonNotflix" id="buttonRatingVerwijderen'+ i + '" hidden>Rating verwijderen</button>' +
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
				}).done(function(data) { 
					console.dir(data)
					console.log(i);
					console.log(data.Poster);
					$('#'+i+'posterimage').attr("src",data.Poster)
					console.log($('#'+i+'posterimage').src)
					
				});
				
				if(localStorage.getItem("token").length > 0){
					$("#buttonRatingToevoegen" + i).show();
				}
				else{
					$("#buttonRatingToevoegen" + i).hide();
				}
				
				$("#buttonRatingToevoegen" + i).click(function(){
					$("#light").show();
					$("#fade").show();
					imdbId = val.imdb_nummer;
				});
			});
			
			$(".number-spinner button").click(function(event){
				event.preventDefault();
				
				var btn = $(this),
				oldValue = $("#ratingInput").val().trim(),
				newVal = 0;
			
				if (btn.attr('data-dir') == 'up') {
					newVal = (parseFloat(oldValue) + 0.5).toFixed(1);
					
					if(newVal > 5){
						newVal = parseFloat(5.0).toFixed(1);
					}
				} else {
					if (oldValue > 1) {
						newVal = (parseFloat(oldValue) - 0.5).toFixed(1);
					} else {
						newVal = parseFloat(1.0).toFixed(1);
					}
				}
				btn.closest('.number-spinner').find('input').val(newVal);
			});
			
			$("#buttonAnnuleer").click(function(){
				$("#light").hide();
				$("#fade").hide();
			});
			
			$("#buttonVoegRatingToe").click(function(event){
				event.preventDefault();
				rating = $('#ratingInput').val();
				
				alert(rating)
				alert(imdbId)
				
				var data = new FormData();
				data.append("rating", rating);
				data.append("imdbId", imdbId);
				
				$.ajax({
					dataType: 'json',
					type: "POST",
					url: "http://localhost:8080/Notflix/api/ratings",
					data: "rating="+rating+"&imdbId="+imdbId,
					processData: false,
					headers: {
						"Authorization": localStorage.getItem("token")
					}
			        		
				}).fail(function(jqXHR,	textStatus)	{	
					console.dir(jqXHR)
					alert("niet toegevoegd")
				}).done(function(data) { 
					alert("toegevoegd")
					$("#light").hide();
					$("#fade").hide();
					zoek();
				});
			});
		});
}