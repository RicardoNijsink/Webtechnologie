package nl.webtechnologie.model;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class KamerverhuurServletContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sc) {
		ServletContext ctx = sc.getServletContext();
		Administratie admin = new Administratie();
		ctx.setAttribute("admin", admin);
	}

}
