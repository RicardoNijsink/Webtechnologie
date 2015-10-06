import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("api")
public class MyREST extends ResourceConfig {
	
	public MyREST() {
		super();
		System.out.println("test");
		packages("resource");
		register(com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider.class);
	}
}
