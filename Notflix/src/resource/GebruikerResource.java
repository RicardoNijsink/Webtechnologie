package resource;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
	 * @param nickname De nickname van de te zoeken gebruiker
	 * @return Een 200-response met de gevonden gebruiker. Anders een 404-response.
	 */
	@GET
	@Path("{nickname}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getGebruiker(@PathParam("nickname") String nickname) {
		Model model = (Model) context.getAttribute("model"); 
		
		Gebruiker gebruiker = model.getGebruiker(nickname);
		
		if(gebruiker != null){
			return Response.ok().entity(model.getGebruiker(nickname)).build();
		}
		return Response.status(404).build();
	}
	
	/**
	 * Methode voor het toevoegen van een gebruiker aan de database
	 * @param request De verstuurde request
	 * @param input
	 * @return Een 200-response met daarin een JSONObject met de aangemaakte gebruiker.
	 * Als niet alle vereiste parameters aanwezig zijn een 400-response.
	 * Als de gebruiker al bestaat een 409-response.
	 */
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response postGebruiker(@Context HttpServletRequest request, @FormParam(value = "achternaam") String achternaam,
			@FormParam(value = "voornaam") String voornaam, @FormParam(value = "nickname") String nickname,
			@FormParam(value = "tussenvoegsel") String tussenvoegsel, @FormParam(value = "wachtwoord") String wachtwoord) {
		
		Model model = (Model) context.getAttribute("model");
		
		if(voornaam ==null || achternaam == null || nickname == null || wachtwoord == null ||
			voornaam.length() < 0 || achternaam.length() < 0 || nickname.length() < 0 || wachtwoord.length() < 0){
			return Response.status(400).build();
			//TODO error message
		}
		
		Gebruiker toegevoegdeGebruiker = model.addGebruiker(voornaam, tussenvoegsel, achternaam, nickname, wachtwoord);
		
		if(toegevoegdeGebruiker == null){
			return Response.status(409).build();
		}
		
		return Response.status(201).entity(toegevoegdeGebruiker).build();
	}

}
