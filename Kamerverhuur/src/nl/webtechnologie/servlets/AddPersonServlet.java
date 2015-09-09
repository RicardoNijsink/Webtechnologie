package nl.webtechnologie.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.webtechnologie.model.Administratie;
import nl.webtechnologie.model.Gebruiker;
import nl.webtechnologie.model.Huurder;
import nl.webtechnologie.model.Verhuurder;

/**
 * Servlet implementation class AddPersonServlet
 */
@WebServlet("/AddPersonServlet")
public class AddPersonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPersonServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String function = request.getParameter("function");
		Administratie admin = (Administratie) getServletContext().getAttribute(
				"admin");
		if (!admin.isUser(name)) {

			if (function.equals("verhuurder")) {
				Verhuurder verhuurderToAdd = new Verhuurder(name, password);
				admin.addGebruiker(verhuurderToAdd);
				response.sendRedirect("login.html");

			} else if (function.equals("huurder")) {
				Huurder huurderToAdd = new Huurder(name, password);
				admin.addGebruiker(huurderToAdd);
				response.sendRedirect("login.html");
			} else {
				// TODO not a function feedback
			}
		} else {
			// TODO username alradie exist feedback
		}
		
	}

}
