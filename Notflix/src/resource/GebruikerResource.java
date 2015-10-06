package resource;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import model.Gebruiker;
import model.Model;

@Path("gebruikers")
public class GebruikerResource {
	@Context ServletContext context;
	private Model model = (Model) context.getAttribute("model");
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Gebruiker> getGebruikers() {
		return model.getGebruikers();
	}

}
