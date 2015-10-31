$(document).ready(function(){
	$.ajax({
		dataType: 'json',
		url: "http://localhost:8080/Notflix/api/gebruikers",
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
			"<td>"+val.voornaam+"</td>"+
			"<td>"+val.achternaam+"</td>"+
			"<td>"+val.nickname+"</td>"+
			"<td>"+val.voornaam+"</td>"+
			"</tr>");
		});
	});


});