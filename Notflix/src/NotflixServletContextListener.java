import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import model.Model;

@WebListener
public class NotflixServletContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sc) {
		System.out.println("test2");
		ServletContext ctx = sc.getServletContext();
		Model model = new Model();
		ctx.setAttribute("model", model);
		
	}

}
