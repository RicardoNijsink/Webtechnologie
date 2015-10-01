package resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("ratings")
public class RatingResource {

	@GET
	public String get(){
		return "kaas";
		
	}
	
	
}
