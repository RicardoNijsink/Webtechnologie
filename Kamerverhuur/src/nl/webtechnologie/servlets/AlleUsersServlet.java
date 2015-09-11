package nl.webtechnologie.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nl.webtechnologie.model.Administratie;
import nl.webtechnologie.model.Beheerder;
import nl.webtechnologie.model.Gebruiker;
import nl.webtechnologie.model.Huurder;

/**
 * Servlet implementation class AlleUsersServlet
 */
@WebServlet("/AlleUsersServlet")
public class AlleUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlleUsersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s = request.getSession();
		String username= null;
		if (s!=null){
			username = (String) s.getAttribute("userName");
		}
		 Administratie admin = (Administratie) getServletContext().getAttribute("admin");
		 
		 PrintWriter out = response.getWriter();
		    out.println("<!doctype html\">\n" +
		                "<html>\n" +
		                "<head><title>alle Gebruikers</title></head>\n" +
		                "<body>\n" +
		                "<h1>Gebruikers</h1>\n");
		 
		 if (username!=null){
			 
			 if (admin.getUser(username) instanceof Beheerder){
				 Cookie[] jar = request.getCookies();
			 String aantallogin="0";
			 boolean isCookieGevonden=false;
			 boolean isDatumCookieGevonden = false;
			 String datum = null;
			 
			    if (jar != null) {
			    			    	
			    	for(int i=0; i<jar.length; i++) {
			    		Cookie c = jar[i];
			    		if (c.getName().equals("aantal")){
			    			c.setValue((Integer.parseInt(c.getValue())+1)+"");
			    			response.addCookie(c);
			    			isCookieGevonden=true;
			    			aantallogin = c.getValue();
			    		}
			    		if (c.getName().equals("laatsteKeer")){
			    			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			    			Date date = new Date();
			    			datum = c.getValue();
			    			c.setValue(dateFormat.format(date));
			    			isDatumCookieGevonden=true;
			    			response.addCookie(c);
			    		}
			    	}
			    }
			    if (!isCookieGevonden){
			    Cookie myCookie = new Cookie("aantal", ""+aantallogin);
			    myCookie.setMaxAge(Integer.MAX_VALUE);
			    response.addCookie(myCookie);
			    }
			    if (!isDatumCookieGevonden){
			    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	    			Date date = new Date();
			    	Cookie myCookie = new Cookie("laatsteKeer", dateFormat.format(date));
				    myCookie.setMaxAge(Integer.MAX_VALUE);
				    response.addCookie(myCookie);
			    }
			    out.println("<br>" + "aantal keer bezocht: "+ aantallogin );
			    if (datum==null){
			    	out.println("<br>" + "nog niet eerder bezocht");
			    }else{
			    	out.println("<br>" + "laatste keer bezocht: "+ datum );
			    }
				 for (Gebruiker g:admin.getGebruikers()){
					 out.println("<br>" + g.getName() + " " + g.getPassword());
					 out.println("<br>" + (g.getClass()+"").substring(30));
					 out.println("<br>");
				 }
			 }else{
				 out.println("je mag dit lekker niet doen");
				 out.println("<a href='login.html'> Back to the login</a>");
			 }
		 }else{
			 out.println("je bent niet in gelogd");
			 out.println("<a href='login.html'> Back to the login</a>");
		 }
		 
		    out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
