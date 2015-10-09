package resource;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

import model.ErrorCode;
import model.Gebruiker;
import model.Model;
import model.Rating;

//TODO IMDB-nummer overal hetzelfde

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
	 * @param token De access token
	 * @return Een 200-response met een lijst van alle ratings van de gebruiker.
	 * Een 401-response als de access token ongeldig is.
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response get(@HeaderParam("Authorization") String token) {
		Model model = (Model) context.getAttribute("model");
		Gebruiker gebruiker = model.getGebruikerByToken(token);
		if(gebruiker==null){
			ErrorCode errorcode = new ErrorCode();
			errorcode.setError(errorcode.getTOKEN_INVALID());
			return Response.status(401).entity(errorcode).build();
		}
		else{
			return Response.ok().entity(gebruiker.getRatings()).build();
		}
	}
	
	/**
	 * Methode voor het verwijderen van een rating
	 * @param token De access token
	 * @param id Het IMDB-nummer van de film van de te verwijderen rating
	 * @return Een 200-response met de verwijderde rating.
	 * Een 404-response als de rating niet bestaat.
	 * Een 401-response als de access token ongeldig is.
	 */
	@DELETE
	@Path ("{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response deleteRating(@HeaderParam("Authorization") String token, @PathParam("id") String id) {
		Model model = (Model) context.getAttribute("model");
		Gebruiker gebruiker = model.getGebruikerByToken(token);
		if (gebruiker==null){
			ErrorCode errorcode = new ErrorCode();
			errorcode.setError(errorcode.getTOKEN_INVALID());
			return Response.status(401).entity(errorcode).build();
		}else{
			Rating rating = gebruiker.getRating(id);
			System.out.println("kaas"+rating);
			if (rating == null){
				ErrorCode errorcode = new ErrorCode();
				errorcode.setError(errorcode.getMOVIE_DOES_NOT_EXIST());
				System.out.println(errorcode.getError());
				return Response.status(404).entity(errorcode).build();
			}else{
				gebruiker.deleteRating(rating);
				return Response.ok().entity(rating).build();
			}
		}
	}
	
	/**
	 * Methode voor het toevoegen van een rating aan een film
	 * @param token De access token
	 * @param request De verzonden request
	 * @param rating De ratingwaarde
	 * @param imdbId Het IMDB-nummer van de beoordeelde film
	 * @return Een 201-response met de toegevoegde rating.
	 * Een 400-response als de parameters niet correct zijn ingevuld.
	 * Een 401-response als de access token ongeldig is.
	 * Een 404-response als de film niet bestaat.
	 * Een 409-response als de film al beoordeeld is.
	 */
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response postRating(@HeaderParam("Authorization") String token, 
			@FormParam(value = "rating") String rating, 
			@FormParam(value = "imdbId") String imdbId) {

		Model model = (Model) context.getAttribute("model");
		Gebruiker gebruiker = model.getGebruikerByToken(token);
		if(gebruiker==null){
			ErrorCode errorcode = new ErrorCode();
			errorcode.setError(errorcode.getTOKEN_INVALID());
			return Response.status(401).entity(errorcode).build();
		}
		else{
			double doubleRating;
			try{
				doubleRating = Double.parseDouble(rating);
				if (rating == null || imdbId == null || imdbId.length() <= 0 || doubleRating < 0.5 || doubleRating > 5){
					ErrorCode errorcode = new ErrorCode();
					errorcode.setError(errorcode.getEMPTY_FIELDS());
					return Response.status(400).entity(errorcode).build();
				}
				else if (gebruiker.isRated(imdbId)){
					ErrorCode errorcode = new ErrorCode();
					errorcode.setError(errorcode.getOBJECT_ALREADY_EXISTS());
					return Response.status(409).entity(errorcode).build();
				}
				else if (!model.isMovie(imdbId)){
					ErrorCode errorcode = new ErrorCode();
					errorcode.setError(errorcode.getMOVIE_DOES_NOT_EXIST());
					return Response.status(404).entity(errorcode).build();
				}	
			} catch(NumberFormatException e){
				ErrorCode errorcode = new ErrorCode();
				errorcode.setError(errorcode.getRATING_OUT_OF_RANGE());
				return Response.status(400).entity(errorcode).build();
			}
			
			Rating toegevoegdeRating = gebruiker.addRating(new Rating(doubleRating, imdbId));
			return Response.status(201).entity(toegevoegdeRating).build();
		}
	}

	/**
	 * Methode om een rating van een film bij te werken.
	 * @param token De access token
	 * @param id Het IMDB-nummer van de film
	 * @param request De verzonden request
	 * @param rating De ratingwaarde
	 * @return Een 201-response als de rating bijgewerkt is.
	 * Een 400-response als de parameters niet correct ingevuld zijn.
	 * Een 401-response als de access token ongeldig is.
	 * Een 404-response als de film niet bestaat of als de rating niet bestaat.
	 */
	@PUT
	@Path ("{id}")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response putRating(@HeaderParam("Authorization") String token, @PathParam("id") String id,
			@FormParam(value = "rating") String rating) {

		Model model = (Model) context.getAttribute("model");
		Gebruiker gebruiker = model.getGebruikerByToken(token);
		
		if(gebruiker == null){
			ErrorCode errorcode = new ErrorCode();
			errorcode.setError(errorcode.getTOKEN_INVALID());
			return Response.status(401).entity(errorcode).build();
		} 
		else{
			if(!model.isMovie(id)){
				ErrorCode errorcode = new ErrorCode();
				errorcode.setError(errorcode.getMOVIE_DOES_NOT_EXIST());
				return Response.status(404).entity(errorcode).build();	
			}
			
			double doubleRating;
			try{
				doubleRating = Double.parseDouble(rating);
				
				if(rating != null) {
					if(doubleRating < 0.5 || doubleRating > 5){
						ErrorCode errorcode = new ErrorCode();
						errorcode.setError(errorcode.getRATING_OUT_OF_RANGE());
						return Response.status(409).entity(errorcode).build();
					}
					
					gebruiker.getRating(id).setRating(doubleRating);
					return Response.status(201).entity(gebruiker.getRating(id)).build();
				}
				else{
					ErrorCode errorcode = new ErrorCode();
					errorcode.setError(errorcode.getEMPTY_FIELDS());
					return Response.status(404).entity(errorcode).build();
				}
			} 
			catch (NumberFormatException e) {
				ErrorCode errorcode = new ErrorCode();
				errorcode.setError(errorcode.getRATING_OUT_OF_RANGE());
				return Response.status(400).entity(errorcode).build();
			}
		}
	}
	
}
