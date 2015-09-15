package nl.webtechnologie.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import nl.webtechnologie.model.Verhuurder;

/**
 * Servlet implementation class AlleUsersServlet
 * Servlet voor het geven van een overzicht van alle gebruikers binnen de administratie
 */
@WebServlet("/AlleUsers")
public class AlleUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlleUsersServlet() {
        super();
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
		            "<head><title>Gebruikersoverzicht</title></head>\n" +
		            "<body>\n" +
		            "<h1>Gebruikers</h1>\n");
		
		//Controleert of de ingelogde gebruiker beheerder is.
		//Als dat het geval is, dan worden alle cookies opgehaald en wordt het overzicht van alle gebruikers gegeven.
		 if (username!=null){
			 
			 if (admin.getUser(username) instanceof Beheerder){
				 Cookie[] jar = request.getCookies();
				 String aantalLogin="0";
				 boolean isCookieGevonden=false;
				 boolean isDatumCookieGevonden = false;
				 String datum = null;
				 
				 //Haalt alle cookies op en leest de gezochte cookies uit.
				 //Vervolgens worden de gegevens van de cookies bijgewerkt en worden de cookies weer meegegeven aan de response
				 if (jar != null) {
			    			    	
			    	for(int i=0; i<jar.length; i++) {
			    		Cookie c = jar[i];
			    		if (c.getName().equals("aantal")){
			    			c.setValue((Integer.parseInt(c.getValue())+1)+"");
			    			response.addCookie(c);
			    			isCookieGevonden=true;
			    			aantalLogin = c.getValue();
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
				 //Als de gezochte cookies niet gevonden zijn, worden ze hier aangemaakt
			    if (!isCookieGevonden){
				    Cookie myCookie = new Cookie("aantal", ""+aantalLogin);
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
			    out.println("<br>" + "Aantal keer bezocht: "+ aantalLogin );
			    if (datum==null){
			    	out.println("<br>" + "Nog niet eerder bezocht");
			    }else{
			    	out.println("<br>" + "Laatste keer bezocht: "+ datum );
			    }
				 for (Gebruiker g:admin.getGebruikers()){
					 out.println("<br>" + g.getName() + " " + g.getPassword());
					 if(g instanceof Verhuurder){
						 out.println("<br>" + "Verhuurder");
					 }
					 else if(g instanceof Huurder){
						 out.println("<br>" + "Huurder");
					 }
					 
					 out.println("<br>");
				 }
			 }else{
				 out.println("U bent hier niet voor geautoriseerd");
				 out.println("<a href='login.html'>Terug naar de inlogpagina</a>");
			 }
		 }else{
			 out.println("U bent niet in gelogd");
			 out.println("<a href='login.html'>Terug naar de inlogpagina</a>");
		 }
		 
		    out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
