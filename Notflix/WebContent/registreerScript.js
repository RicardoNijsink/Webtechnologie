$(document).ready(function(){
	$("#registreerForm").submit(function(event) {
		/* stop form from submitting normally */
		alert("form called")
		event.preventDefault();

		var postData = $(this).serializeArray();
		var formURL = $(this).attr("action");

		var name = $("#inputVoornaam").val()
		var tussenvoegsel = $("#inputTussenvoegsel").val()
		var achternaam = $("#inputAchternaam").val()
		var nickname = $("#inputNickname").val()
		var password1 = $("#inputPassword1").val()
		var password2 = $("#inputPassword2").val()
		alert(name)
		if (name.length === 0||achternaam.length === 0||nickname.length === 0||password1.length === 0||password2.length === 0){
			alert("niet alle velden zijn ingevult")
		}else{
			alert("else")
			if (password1===password2){
				console.dir(postData)
				$.ajax({
					dataType: "json",
					type: "POST",
					url: formURL,
					data: postData

				}).fail(function(jqXHR, textStatus){ 
					alert("request failed")
				}).done(function(data){ 
					console.dir(data)
					window.location = "index.html"
				});
			}else{
				alert("wachtworden niet gelijk")
			}
		}


		
	});
	
});