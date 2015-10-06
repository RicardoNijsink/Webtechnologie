package resource;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import model.Model;

/**
 * Class voor het ophalen van ratingsgegevens uit de database
 * @author Ricardo
 *
 */
@Path("ratings")
public class RatingResource {
	@Context ServletContext context;
	private Model model = (Model) context.getAttribute("model");

	/**
	 * Methode voor het ophalen van alle ratings van een gebruiker
	 * @return Een 200-response met alle ratings van de opgegeven gebruiker
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public String get(){
		return "kaas";
	}
	
	
}
