import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * Class voor het registreren van de resources en Jackson
 * @author Ricardo
 *
 */
@ApplicationPath("api")
public class MyREST extends ResourceConfig {
	
	/**
	 * Registreert de recourses en Jackson
	 */
	public MyREST() {
		super();
		System.out.println("test");
		packages("resource");
		register(com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider.class);
	}
}
