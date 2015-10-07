package resource;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.util.JSONPObject;

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
	 * @return Een 200-response met de gevonden gebruiker. Anders een 205-response
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
		return Response.noContent().build();
	}
	
	/**
	 * Methode voor het toevoegen van een gebruiker aan de database
	 * @param request De verstuurde request
	 * @param input
	 * @return Een 200-response met daarin een JSONObject met de aangemaakte gebruiker. Anders foutcode TODO
	 */
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response postGebruiker(@Context HttpServletRequest request, byte[] input) {
		Model model = (Model) context.getAttribute("model"); 
		
		JSONObject json = new JSONObject();
		json = new JSONObject(input);
		
		System.out.println(json);
		
		return Response.ok().entity(model.getGebruiker("test")).build();
	}

}
