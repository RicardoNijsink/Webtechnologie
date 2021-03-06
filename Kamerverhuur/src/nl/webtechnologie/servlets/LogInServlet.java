package nl.webtechnologie.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
 * Servlet implementation class LogInServlet
 * Servlet voor het controleren van de logingegevens en het doorsturen van de ingelogde gebruiker naar de bijbehorende pagina
 */
@WebServlet("/LogIn")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogInServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		Administratie admin = (Administratie) getServletContext().getAttribute("admin");
		Gebruiker gebruiker = admin.getUser(name, password);

		//Controleert of de gebruiker bestaat.
		//Als dit het geval is, wordt er voor deze gebruiker een nieuwe sessie aangemaakt.
		//Vervolgens wordt de gebruiker naar de bijbehorende pagina doorgestuurd.
		if (gebruiker != null) {
			HttpSession s = request.getSession();
			if (!s.isNew()) {
				s.invalidate();
				s = request.getSession();

			}
			s.setAttribute("userName", name);
			if (gebruiker instanceof Huurder) {
				response.sendRedirect("SearchRoom");
			} else if (gebruiker instanceof Verhuurder) {
				response.sendRedirect("ShowRooms");
			} else if (gebruiker instanceof Beheerder) {
				response.sendRedirect("AlleUsers");
			}

		} else {
			RequestDispatcher myDispatcher = request.getRequestDispatcher("WEB-INF/fouteLogin.html");
			myDispatcher.forward(request, response);
		}

	}

}
