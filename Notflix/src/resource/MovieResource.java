package resource;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Gebruiker;
import model.Model;
import model.Movie;

@Path("movies")
public class MovieResource {
	@Context ServletContext context;
	
	public MovieResource() {
		System.out.println(context);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getMovies() {
		Model model = (Model) context.getAttribute("model");
		return Response.ok().entity(model.getMovies()).build();
		
	}
	
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Movie getMovie(@PathParam("id") int id){
		Model model = (Model) context.getAttribute("model");
		return model.getMovie(id);
	}
	

}
