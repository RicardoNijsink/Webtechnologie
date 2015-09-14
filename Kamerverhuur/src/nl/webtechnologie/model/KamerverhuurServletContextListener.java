package nl.webtechnologie.model;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class KamerverhuurServletContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	/**
	 * Hier wordt de adminstratie aangemaakt en aan de servletcontext meegegeven
	 */
	@Override
	public void contextInitialized(ServletContextEvent sc) {
		ServletContext ctx = sc.getServletContext();
		Administratie admin = new Administratie();
		ctx.setAttribute("admin", admin);
	}

}
