package resource;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import model.Model;

@Path("ratings")
public class RatingResource {
	@Context ServletContext context;
	private Model model = (Model) context.getAttribute("model");

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public String get(){
		return "kaas";
	}
	
	
}
