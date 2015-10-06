package resource;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import model.Gebruiker;
import model.Model;

@Path("gebruikers")
public class GebruikerResource {
	@Context ServletContext context;
	
	@GET
	@Path("test")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public String test() {
		System.out.println("test");
		return "test";
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Gebruiker> getGebruikers() {
		Model model = (Model) context.getAttribute("model"); 
		
		return model.getGebruikers();
	}
	
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Gebruiker getGebruiker(@PathParam("id") int id) {
		Model model = (Model) context.getAttribute("model"); 
		
		return model.getGebruikers().get(0);
	}

}
