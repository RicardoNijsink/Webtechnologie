package resource;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import model.Gebruiker;
import model.Model;

/**
 * Class voor het ophalen van gebruikersdata uit de database
 * @author Ricardo
 *
 */
@Path("gebruikers")
public class GebruikerResource {
	@Context ServletContext context;
	
	/**
	 * Methode voor het ophalen van alle gebruikers uit de database
	 * @return De response met een lijst van alle gebruikers
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getGebruikers() {
		Model model = (Model) context.getAttribute("model");
		
		return Response.ok().entity(model.getGebruikers()).build();
	}
	
	/**
	 * Methode voor het ophalen van een gebruiker aan de hand van de nickname
	 * @param token De access token
	 * @param nickname De nickname van de te zoeken gebruiker
	 * @return Een 200-response met de gevonden gebruiker. 
	 * Een 401-response als de access token ongeldig is.
	 * Een 404-response als de gebruiker niet bestaat.
	 */
	@GET
	@Path("{nickname}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getGebruiker(@HeaderParam("Authorization") String token, 
			@PathParam("nickname") String nickname) {
		
		Model model = (Model) context.getAttribute("model"); 
		
		if(!model.isToken(token)){
			return Response.status(401).build();
		} 
		else{
		Gebruiker gebruiker = model.getGebruiker(nickname);
		
		if(gebruiker != null){
			return Response.ok().entity(model.getGebruiker(nickname)).build();
		}
		
		return Response.status(404).build();
		}
	}
	
	/**
	 * Methode voor het toevoegen van een gebruiker aan de database
	 * @param request De verstuurde request
	 * @param achternaam De achternaam van de toe te voegen gebruiker
	 * @param voornaam De voornaam van de toe te voegen gebruiker
	 * @param nickname De nickname van de toe te voegen gebruiker
	 * @param tussenvoegsel Het tussenvoegsel van de toe te voegen gebruiker
	 * @param wachtwoord Het wachtwoord van de toe te voegen gebruiker
	 * @return Een 201-response als de gebruiker is toegevoegd.
	 * Een 400-response als niet alle parameters correct ingevuld zijn.
	 * Een 409-response als de gebruiker al bestaat.
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response postGebruiker(@Context HttpServletRequest request,
			@FormParam(value = "achternaam") String achternaam, @FormParam(value = "voornaam") String voornaam,
			@FormParam(value = "nickname") String nickname, @FormParam(value = "tussenvoegsel") String tussenvoegsel,
			@FormParam(value = "wachtwoord") String wachtwoord) {

		Model model = (Model) context.getAttribute("model");
		if(voornaam == null || achternaam == null || nickname == null || wachtwoord == null || voornaam.length() <= 0
				|| achternaam.length() <= 0 || nickname.length() <= 0 || wachtwoord.length() <= 0){
			
			return Response.status(400).build();
			// TODO error message
		}

		Gebruiker toegevoegdeGebruiker = model.addGebruiker(voornaam, tussenvoegsel, achternaam, nickname, wachtwoord);

		if(toegevoegdeGebruiker == null){
			return Response.status(409).build();
		}

		return Response.status(201).entity(toegevoegdeGebruiker).build();
	}
	
	/**
	 * Methode om in te loggen
	 * @param request De verzonden request
	 * @param nickname De nickname van de gebruiker
	 * @param wachtwoord Het wachtwoord van de gebruiker
	 * @return Een 200-response als de gebruiker is ingelogd.
	 * Een 400-response als niet alle parameters correct zijn.
	 * Een 404-response als de gebruiker niet bestaat.
	 * Een 422-response als het wachtwoord niet correct is.
	 */
	@POST
	@Path ("login")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.TEXT_PLAIN})
	public Response postLogInGebruiker(@Context HttpServletRequest request,
			@FormParam(value = "nickname") String nickname, 
			@FormParam(value = "wachtwoord") String wachtwoord) {

		Model model = (Model) context.getAttribute("model");
		if(nickname == null || wachtwoord == null || nickname.length() <= 0 || wachtwoord.length() <= 0){
			System.out.println(request.getContentType());
			
			return Response.status(400).entity("test").build();
			// TODO error message
		}

		Gebruiker gebruiker = model.getGebruiker(nickname);
		if(gebruiker==null){
			return Response.status(404).build();
		}
		if(!gebruiker.getWachtwoord().equals(wachtwoord)){
			return Response.status(422).build();
		}
		
		return Response.status(200).entity(gebruiker.getToken()).build();
	}

}
