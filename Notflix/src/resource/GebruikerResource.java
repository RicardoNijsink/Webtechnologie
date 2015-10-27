package resource;

import javax.servlet.ServletContext;
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

import model.Error;
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
	public Response getGebruikers(@HeaderParam("Authorization") String token) {
		Model model = (Model) context.getAttribute("model");
		
		if(!model.isToken(token)){
			Error errorcode = new Error();
			return Response.status(401).entity(errorcode.getErrorMessage(401)).build();
		}
		else{
			return Response.ok().entity(model.getGebruikers()).build();
		}
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
			Error errorcode = new Error();
			return Response.status(401).entity(errorcode.getErrorMessage(401)).build();
		} 
		else{
		Gebruiker gebruiker = model.getGebruiker(nickname);
		
		if(gebruiker != null){
			return Response.ok().entity(gebruiker).build();
		}
		Error errorcode = new Error();
		return Response.status(404).entity(errorcode.getErrorMessage(404)).build();
		}
	}
	
	@GET
	@Path("getbytoken")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getGebruikerByToken(@HeaderParam("Authorization") String token) {
		
		Model model = (Model) context.getAttribute("model"); 
		
		if(!model.isToken(token)){
			Error errorcode = new Error();
			return Response.status(401).entity(errorcode.getErrorMessage(401)).build();
		} 
		else{
		Gebruiker gebruiker = model.getGebruikerByToken(token);
		
		if(gebruiker != null){
			return Response.ok().entity(gebruiker).build();
		}
		Error errorcode = new Error();
		return Response.status(404).entity(errorcode.getErrorMessage(404)).build();
		}
	}
	
	/**
	 * Methode voor het toevoegen van een gebruiker aan de database
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
	public Response postGebruiker(
			@FormParam(value = "achternaam") String achternaam, @FormParam(value = "voornaam") String voornaam,
			@FormParam(value = "nickname") String nickname, @FormParam(value = "tussenvoegsel") String tussenvoegsel,
			@FormParam(value = "wachtwoord") String wachtwoord) {

		Model model = (Model) context.getAttribute("model");
		if(voornaam == null || achternaam == null || nickname == null || wachtwoord == null || voornaam.length() <= 0
				|| achternaam.length() <= 0 || nickname.length() <= 0 || wachtwoord.length() <= 0){
			Error errorcode = new Error();
			return Response.status(400).entity(errorcode.getErrorMessage(400)).build();
		}

		Gebruiker toegevoegdeGebruiker = model.addGebruiker(voornaam, tussenvoegsel, achternaam, nickname, wachtwoord);

		if(toegevoegdeGebruiker == null){
			Error errorcode = new Error();
			return Response.status(409).entity(errorcode.getErrorMessage(409)).build();
			
		}

		return Response.status(201).entity(toegevoegdeGebruiker).build();
	}
	
	/**
	 * Methode om in te loggen
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
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response postLogInGebruiker(
			@FormParam(value = "nickname") String nickname, 
			@FormParam(value = "wachtwoord") String wachtwoord) {

		Model model = (Model) context.getAttribute("model");
		if(nickname == null || wachtwoord == null || nickname.length() <= 0 || wachtwoord.length() <= 0){
			Error errorcode = new Error();
			return Response.status(400).entity(errorcode.getErrorMessage(400)).build();
		}

		Gebruiker gebruiker = model.getGebruiker(nickname);
		if(gebruiker==null){
			Error errorcode = new Error();
			return Response.status(400).entity(errorcode.getErrorMessage(400)).build();
		}
		if(!gebruiker.getWachtwoord().equals(wachtwoord)){
			Error errorcode = new Error();
			return Response.status(422).entity(errorcode.getErrorMessage(422)).build();
		}
		
		return Response.status(200).entity(gebruiker.getTokenClass()).build();
	}

}
