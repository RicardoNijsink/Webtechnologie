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
import nl.webtechnologie.model.Huurder;
import nl.webtechnologie.model.Kamer;
import nl.webtechnologie.model.Verhuurder;

/**
 * Servlet implementation class AddRoomServlet
 */
@WebServlet("/AddRoomServlet")
public class AddRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddRoomServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s = request.getSession();
		String username = (String) s.getAttribute("userName");
		Administratie admin = (Administratie) getServletContext().getAttribute("admin");
		
		if (username != null) {
			if (admin.getUser(username) instanceof Verhuurder) {
				RequestDispatcher myDispatcher = request.getRequestDispatcher("WEB-INF/addRoom.html");
				myDispatcher.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s = request.getSession();
		String aantalVierkanteMeterString = (String) request.getParameter("aantalVierkanteMeters");
		String huurprijsString = (String) request.getParameter("huurprijs");
		double aantalVierkanteMeter = 0.0;
		double huurprijs = 0.0;
		String username = (String) s.getAttribute("userName");
		Administratie admin = (Administratie) getServletContext().getAttribute("admin");
		
		if (username!=null){
			 if (admin.getUser(username) instanceof Verhuurder){
				 if (!aantalVierkanteMeterString.equals("")){
					 aantalVierkanteMeter= Double.parseDouble(aantalVierkanteMeterString);
				 }
				 
				 if (!huurprijsString.equals("")){
					 huurprijs = Double.parseDouble(huurprijsString);
				 }
				 
				 String plaats = (String) request.getParameter("plaats");
				 admin.addKamer(new Kamer(huurprijs, aantalVierkanteMeter, plaats, (Verhuurder)admin.getUser(username)));
				 response.sendRedirect("ShowRoomsServlet");
			 }
		 }
	}

}
