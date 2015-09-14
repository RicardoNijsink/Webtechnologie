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
 * Servlet implementation class SearchRoomServlet
 */
@WebServlet("/SearchRoomServlet")
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
		            "<head><title>zoek kamers</title></head>\n" +
		            "<body>\n" +
		            "<h1>zoek kamers</h1>\n");
		 
		if (username != null) {

			if (admin.getUser(username) instanceof Huurder
					|| admin.getUser(username) instanceof Beheerder) {
				out.print("<form action=\"SearchRoomServlet\" method=\"POST\">"
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
		
		if (username != null) {
			String plaats = (String) request.getParameter("plaats");

			PrintWriter out = response.getWriter();
			out.println("<!doctype html\">\n" + 
						"<html>\n" +
						"<head><title>Zoek kamers</title></head>\n" + 
						"<body>\n" + 
						"<h1>zoek kamers</h1>\n");

			if (maximaleHuurprijs == 0.0) {
				maximaleHuurprijs = Double.MAX_VALUE;
			}
			for (Kamer k : admin.getKamers()) {
				if (k.getAantalVierkanteMeters() >= minimaalAantalVierkanteMeter
						&& k.getHuurprijs() <= maximaleHuurprijs
						&& k.getPlaats().contains(plaats)
						&& k.getHuurder() == null) {
					out.println("<br>prijs per maand: " + k.getHuurprijs());
					out.println("<br>plaats: " + k.getPlaats());
					out.println("<br>aantal vierkantemeters: " + k.getAantalVierkanteMeters());
					out.println("<br>verhuurder: " + k.getVerhuurder().getName());
					out.println("<br>");
				}
			}
		}

	}

}
