import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import model.Model;

/**
 * Class voor het aanmaken van een instantie van het model.
 * Vervolgens wordt deze instantie van het model aan de context toegevoegd.
 * @author Ricardo
 *
 */
@WebListener
public class NotflixServletContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent sc) {
		ServletContext ctx = sc.getServletContext();
		Model model = new Model();
		ctx.setAttribute("model", model);
	}

}
