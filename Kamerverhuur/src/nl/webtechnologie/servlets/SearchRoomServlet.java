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

/**
 * Servlet implementation class SearchRoomServlet
 * Servlet voor het zoeken van kamers in de administratie
 */
@WebServlet("/SearchRoom")
public class SearchRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchRoomServlet() {
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
		            "<head><title>Kamers zoeken</title></head>\n" +
		            "<body>\n" +
		            "<h1>Zoek kamers</h1>\n");
		
		//Controleert of de ingelogde gebruiker een huurder is.
		//Als dit het geval is, wordt er een form gegevens waarin de huurder zoekcriteria kun invullen.
		if (username != null) {

			if (admin.getUser(username) instanceof Huurder
					|| admin.getUser(username) instanceof Beheerder) {
				out.print("<form action=\"SearchRoom\" method=\"POST\">"
						+ "<label>Minimaal Aantal vierkante meters</label>"
						+ "<input type=\"number\" name=\"aantalVierkanteMeters\">"
						+ "<label>Maximale huurprijs</label>"
						+ "<input type=\"number\" name=\"maximaleHuurprijs\">"
						+ "<label>Plaats</label>"
						+ "<input type=\"text\" name=\"plaats\">"
						+ "<input type=\"submit\" value=\"Zoek\"></form>");
			} else {
				out.println("U bent hier niet voor geautoriseerd");
				out.println("<a href='login.html'>Terug naar de inlogpagina</a>");
			}
		}else{
			out.println("U bent niet ingelogd");
			out.println("<a href='login.html'>Terug naar de inlogpagina</a>");
		}
		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s = request.getSession();
		String minimaalAantalVierkanteMeterString = (String) request.getParameter("aantalVierkanteMeters");
		String maximaleHuurprijsString = (String) request.getParameter("maximaleHuurprijs");
		double minimaalAantalVierkanteMeter = 0.0;
		double maximaleHuurprijs = 0.0;
		
		if (!minimaalAantalVierkanteMeterString.equals("")) {
			minimaalAantalVierkanteMeter = Double.parseDouble(minimaalAantalVierkanteMeterString);
		}
		if (!maximaleHuurprijsString.equals("")) {
			maximaleHuurprijs = Double.parseDouble(maximaleHuurprijsString);
		}
		
		String username = (String) s.getAttribute("userName");
		Administratie admin = (Administratie) getServletContext().getAttribute("admin");
		//Controleert of de ingelogde gebruiker huurder of beheerder is.
		//Als dit het geval is, wordt de lijst van kamers die voldoen aan zoekcriteria weergegeven.
		if (username != null) {
			PrintWriter out = response.getWriter();
			if (admin.getUser(username) instanceof Huurder || admin.getUser(username) instanceof Beheerder) {
				String plaats = (String) request.getParameter("plaats");
			
				out.println("<!doctype html\">\n" + 
						"<html>\n" +
						"<head><title>Kamers zoeken</title></head>\n" + 
						"<body>\n" + 
						"<h1>Zoek kamers</h1>\n");

				if (maximaleHuurprijs == 0.0) {
					maximaleHuurprijs = Double.MAX_VALUE;
				}
				
				boolean kamersGevonden = false;
				
				for (Kamer k : admin.getKamers()) {
					if (k.getAantalVierkanteMeters() >= minimaalAantalVierkanteMeter
							&& k.getHuurprijs() <= maximaleHuurprijs
							&& k.getPlaats().contains(plaats)
							&& k.getHuurder() == null) {
						out.println("<br>Prijs per maand: " + k.getHuurprijs());
						out.println("<br>Plaats: " + k.getPlaats());
						out.println("<br>Aantal vierkante meters: " + k.getAantalVierkanteMeters());
						out.println("<br>Verhuurder: " + k.getVerhuurder().getName());
						out.println("<br>");
						kamersGevonden = true;
					}
					
				}
				if (!kamersGevonden){
						out.println("Geen kamers gevonden. Stel de zoekcriteria bij<br>"+
								"<a href='SearchRoom'>Terug naar de zoekpagina</a>");
				}
			}else{
				out.println("<!doctype html\">\n" + 
						"<html>\n" +
						"<head><title>Kamers zoeken</title></head>\n" + 
						"<body>\n" + 
						"<h1>Zoek kamers</h1>\n"+
						"U bent hier niet voor geautoriseerd <br>"+
						"<a href='login.html'>Terug naar de inlogpagina</a>");
			}
			out.println("</body></html>");
			
		}

	}

}
