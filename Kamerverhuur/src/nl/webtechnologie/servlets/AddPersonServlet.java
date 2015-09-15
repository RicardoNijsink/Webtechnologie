package nl.webtechnologie.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.webtechnologie.model.Administratie;
import nl.webtechnologie.model.Huurder;
import nl.webtechnologie.model.Verhuurder;

/**
 * Servlet implementation class AddPersonServlet
 * Servlet voor het toevoegen van een gebruiker aan de administratie
 */
@WebServlet("/AddPerson")
public class AddPersonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPersonServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String function = request.getParameter("function");
		Administratie admin = (Administratie) getServletContext().getAttribute(
				"admin");
		PrintWriter out = response.getWriter();
		//Controleert of alle velden zijn ingevuld. Als dit niet het geval is, wordt er een melding weergegeven.
		if (function == null||password==null||password2==null||name==null) {
					out.println("<!doctype html\">\n"
					+ "<html>\n"
					+ "<head><title>Registreerpagina</title></head>\n"
					+ "<body>\n"
					+ "<h1>Registreren mislukt</h1>\n"
					+ "Niet alle velden zijn ingevuld"
					+ "<a href='registreer.html'>Terug naar de registreerpagina</a>"
					+ "</body></html>");
		} else {
			//Controleert of de gebruiker al bestaat, de ingevoerde wachtwoorden hetzelfde zijn of alles goed is ingevuld. 
			//Als alles goed is ingevuld, wordt er een nieuwe gebruiker aangemaakt.
			if (!admin.isUser(name)) {
				if (password.equals(password2)) {

					if (function.equals("verhuurder")) {
						Verhuurder verhuurderToAdd = new Verhuurder(name,
								password);
						admin.addGebruiker(verhuurderToAdd);
						response.sendRedirect("login.html");

					} else if (function.equals("huurder")) {
						Huurder huurderToAdd = new Huurder(name, password);
						admin.addGebruiker(huurderToAdd);
						response.sendRedirect("login.html");
					} else {
						out.println("<!doctype html\">\n"
								+ "<html>\n"
								+ "<head><title>Registreerpagina</title></head>\n"
								+ "<body>\n"
								+ "<h1>Registreren mislukt</h1>\n"
								+ "U heeft geen functie opgegeven"
								+ "<a href='registreer.html'>Terug naar de registreerpagina</a>"
								+ "</body></html>");

					}
				} else {
					out.println("<!doctype html\">\n"
							+ "<html>\n"
							+ "<head><title>Registreerpagina</title></head>\n"
							+ "<body>\n"
							+ "<h1>Registreren mislukt</h1>\n"
							+ "De wachtwoorden zijn niet aan elkaar gelijk"
							+ "<a href='registreer.html'>Terug naar de registreerpagina</a>"
							+ "</body></html>");
				}
			} else {
				out.println("<!doctype html\">\n"
						+ "<html>\n"
						+ "<head><title>Registreerpagina</title></head>\n"
						+ "<body>\n"
						+ "<h1>Registreren mislukt</h1>\n"
						+ "De opgegeven gebruikersnaam is al in gebruik"
						+ "<a href='registreer.html'>Terug naar de registreerpagina</a>"
						+ "</body></html>");
			}

		}
	}

}
