package resource;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import model.Model;
import model.Movie;

@Path("movies")
public class MovieResource {
	@Context ServletContext context;
	private Model model = (Model) context.getAttribute("model");
	
	public MovieResource() {
		System.out.println(context);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public ArrayList<Movie> getMovies() {
		return model.getMovies();
	}
	
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Movie getMovie(@PathParam("id") int id){
		return model.getMovie(id);
	}
	

}
