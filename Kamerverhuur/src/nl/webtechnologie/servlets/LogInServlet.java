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
 */
@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		Administratie admin = (Administratie) getServletContext().getAttribute("admin");
		Gebruiker gebruiker =admin.getUser(name);
		if (gebruiker!=null){
			HttpSession s = request.getSession();
			 if (!s.isNew()) {
				  s.invalidate();
				  s = request.getSession();
				  
				 
			  }
			  s.setAttribute("userName", name);
			if (gebruiker instanceof Huurder){
				response.sendRedirect("SearchRoomServlet");
			}else if (gebruiker instanceof Verhuurder){
				response.sendRedirect("addRoom.html");
			}else if (gebruiker instanceof Beheerder){
				response.sendRedirect("AlleUsersServlet");
			}
				
		}else {
			RequestDispatcher myDispatcher = request.getRequestDispatcher("WEB-INF/fouteLogin.html");
			myDispatcher.forward(request, response);
		}
		
	}

}
