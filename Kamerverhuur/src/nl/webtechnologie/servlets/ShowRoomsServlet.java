package nl.webtechnologie.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nl.webtechnologie.model.Administratie;
import nl.webtechnologie.model.Beheerder;
import nl.webtechnologie.model.Huurder;
import nl.webtechnologie.model.Kamer;
import nl.webtechnologie.model.Verhuurder;

/**
 * Servlet implementation class ShowRoomsServlet
 * Servlet voor het laten zien van kamers van de ingelogde gebruiker
 */
@WebServlet("/ShowRooms")
public class ShowRoomsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowRoomsServlet() {
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
		            "<head><title>Kameroverzicht</title></head>\n" +
		            "<body>\n" +
		            "<h1>Overzicht van alle kamers</h1>\n");
		 //Controleert of de gebruiker is ingelogd. Vervolgens wordt de functie bekeken.
		 //Aan de hand van de functie worden er verschillende resultaten weergegeven.
		 if (username!=null){
			 //Als de gebruiker beheerder is, worden alle kamers uit de administratie weergegeven.
			 if (admin.getUser(username) instanceof Beheerder){
				 for (Kamer k:admin.getKamers()){
					 out.println("<br>Prijs per maand: " + k.getHuurprijs());
					 out.println("<br>Plaats: " + k.getPlaats());
					 out.println("<br>Aantal vierkante meters: " + k.getAantalVierkanteMeters());
					 out.println("<br>Verhuurder: " + k.getVerhuurder().getName());
					
					 if (k.getHuurder()==null){
						 out.println("<br>" +"Geen huurder");
					 }else{
						 out.println("<br>Huurder: " + k.getHuurder().getName());
					 }
					 out.println("<br>");
				 }
			 //Als de gebruiker verhuurder is, worden alle kamers die hij/zij verhuurt weergegeven.
			 }else if(admin.getUser(username) instanceof Verhuurder){
				 for (Kamer k:admin.getKamers()){
					if (k.getVerhuurder() == (Verhuurder) admin.getUser(username)) {
						out.println("<br>Prijs per maand: " + k.getHuurprijs());
						out.println("<br>Plaats: " + k.getPlaats());
						out.println("<br>Aantal vierkante meters: " + k.getAantalVierkanteMeters());
						
						if (k.getHuurder()==null){
							out.println("<br>" +"Geen huurder");
						}else{
							out.println("<br>Huurder: " + k.getHuurder().getName());
					 }
					 out.println("<br>");
					}
					 
				 }
				 out.println("<a href=\"addRoom.html\">Voeg een kamer toe</a>");
			 //Als de ingelogde gebruiker een huurder is, worden alle kamers die hij/zij huurt weergegeven
			 }else if(admin.getUser(username) instanceof Huurder){
				 for (Kamer k:admin.getKamers()){
					 if (k.getHuurder()==(Huurder)admin.getUser(username))
					 out.println("<br>Prijs per maand: " + k.getHuurprijs());
					 out.println("<br>Plaats: " + k.getPlaats());
					 out.println("<br>Aantal vierkante meters: " + k.getAantalVierkanteMeters());
					 out.println("<br>Verhuurder: " + k.getVerhuurder().getName());
					 out.println("<br>");
				 }
				 
			 }else{
				 out.println("U bent hier niet voor geautoriseerd");
				 out.println("<a href='login.html'>Terug naar de loginpagina</a>");
			 }
		 }else{
			 out.println("U bent niet in gelogd");
			 out.println("<a href='login.html'>Terug naar de inlogpagina/a>");
		 }
		 
		    out.println("</body></html>");	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
