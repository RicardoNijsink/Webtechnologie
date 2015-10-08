package resource;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.DELETE;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Gebruiker;
import model.Model;
import model.Rating;

/**
 * Class voor het ophalen van ratingsgegevens uit de database
 * @author Ricardo
 *
 */
@Path("ratings")
public class RatingResource {
	@Context ServletContext context;

	/**
	 * Methode voor het ophalen van alle ratings van een gebruiker
	 * @return Een 200-response met alle ratings van de opgegeven gebruiker
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response get(@HeaderParam("Authorization") String token) {
		Model model = (Model) context.getAttribute("model");
		Gebruiker gebruiker = model.getGebruikerByToken(token);
		if (gebruiker==null){
			return Response.status(401).build();
		}else{
			return Response.ok().entity(gebruiker.getRatings()).build();
		}
	}
	
	@DELETE
	@Path ("{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response DELETE(@HeaderParam("Authorization") String token, @PathParam("id") String id) {
		Model model = (Model) context.getAttribute("model");
		Gebruiker gebruiker = model.getGebruikerByToken(token);
		if (gebruiker==null){
			return Response.status(401).build();
		}else{
			Rating rating = gebruiker.getRating(id);
			System.out.println(rating);
			if (rating == null){
				return Response.status(204).build();
			}else{
				gebruiker.deleteRating(rating);
				return Response.ok().entity(rating).build();
			}
		}
	}
	
}
