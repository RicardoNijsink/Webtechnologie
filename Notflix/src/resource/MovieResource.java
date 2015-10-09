package resource;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Error;
import model.Model;
import model.Movie;

/**
 * Class voor het ophalen van de gegevens van de films uit de database
 * @author Ricardo
 *
 */
@Path("movies")
public class MovieResource {
	@Context ServletContext context;
	
	/**
	 * Methode voor het ophalen van alle films uit de database
	 * @param token De access token
	 * @return Een 200-response met de lijst van alle films uit de database.
	 * Een 401-response als de access token ongeldig is.
	 */
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getMovies(@HeaderParam("Authorization") String token) {
		
		Model model = (Model) context.getAttribute("model");
		if(!model.isToken(token)){
			Error errorcode = new Error();
			return Response.status(401).entity(errorcode.getErrorMessage(401)).build();
		} 
		else{
			return Response.ok().entity(model.getMovies()).build();
		}
	}
	
	/**
	 * Methode voor het ophalen van een film uit de database aan de hand van het IMDB-nummer
	 * @param token De access token
	 * @param imdbId Het IMDB-nummer van de gezochte film
	 * @return Een 200-response met de gevonden film.
	 * Een 401-response als de access token ongeldig is.
	 * Een 404-response als de film niet bestaat.
	 */
	@GET
	@Path("{imdbId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getMovie(@HeaderParam("Authorization") String token, @PathParam("imdbId") String imdbId){
		Model model = (Model) context.getAttribute("model");
		if(!model.isToken(token)){
			return Response.status(401).build();
		} 
		else{
			Movie movie = model.getMovie(imdbId);
			if(movie == null){
				return Response.status(404).build();
			} 
			else{
				return Response.ok().entity(movie).build();
			}
		}

	}
	
	/**
	 * Methode voor het ophalen van alle films met een rating
	 * @return Een 200-response met een lijst van alle films met een rating
	 */
	@GET
	@Path ("rated")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getRatedMovies() {
		Model model = (Model) context.getAttribute("model");
		return Response.ok().entity(model.getRatedMovies()).build();
	}

}
