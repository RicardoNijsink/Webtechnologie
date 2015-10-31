$(document).ready(function(){
	var imdbId;
	
	$.ajax({
		dataType: 'json',
		url: "http://localhost:8080/Notflix/api/ratings",
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
		$(data).each(function(i,val){
			$("tbody").append("<tr>"+
			"<td>test</td>"+
			"<td>"+val.movieId+"</td>"+
			"<td>"+val.rating+"</td>"+
			"<td>" + 
				'<button class="buttonNotflix_Small" id="ratingWijzigenButton' + i +'">Wijzigen</button>' +
				'<button class="buttonNotflix_Small" id="ratingVerwijderenButton' + i +'">Verwijderen</button>'+
			"</td>"+
			"</tr>");
			
			$("#ratingWijzigenButton" + i).click(function(event) {
				event.preventDefault();
				
				imdbId = val.movieId;
				
				$("#light").show();
				$("#fade").show();
			});
			
			$("#ratingVerwijderenButton" + i).click(function() {
				imdbId = val.movieId;
				
				$.ajax({
					dataType: 'json',
					type: "DELETE",
					url: "http://localhost:8080/Notflix/api/ratings/"+imdbId,
					headers: {
						"Authorization": localStorage.getItem("token")
					}
			        		
				}).fail(function(jqXHR,	textStatus)	{	
					console.dir(jqXHR) 
					alert("niet verwijderd")
				}).done(function(data){ 
					location.reload();
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
					if (oldValue > 0.5) {
						newVal = (parseFloat(oldValue) - 0.5).toFixed(1);
					} else {
						newVal = parseFloat(0.5).toFixed(1);
					}
				}
				btn.closest('.number-spinner').find('input').val(newVal);
			});
			
			$("#buttonVoegRatingToe").click(function(event){
				var rating = $('#ratingInput').val();
				
				$.ajax({
					dataType: 'json',
					type: "PUT",
					url: "http://localhost:8080/Notflix/api/ratings/"+imdbId,
					data: "rating="+rating,
					processData: false,
					headers: {
						"Authorization": localStorage.getItem("token")
					}
			        		
				}).fail(function(jqXHR,	textStatus)	{	
					console.dir(jqXHR) 
					alert("niet toegevoegd")
				}).done(function(data){
					$("#light").hide();
					$("#fade").hide();
					location.reload();
				});
			});
		});
	});
});