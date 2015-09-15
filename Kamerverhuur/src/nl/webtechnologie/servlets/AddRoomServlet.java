package nl.webtechnologie.servlets;

import java.io.IOException;
import java.io.PrintWriter;

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
@WebServlet("/AddRoom")
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
		PrintWriter out = response.getWriter();
		// controllert of je een verhuurder bent en print dan een form wat je kan invullen
		if (username != null) {
			if (admin.getUser(username) instanceof Verhuurder) {
				RequestDispatcher myDispatcher = request.getRequestDispatcher("WEB-INF/addRoom.html");
				myDispatcher.forward(request, response);
			}else{
				out.println("<!doctype html\">\n" +
			            "<html>\n" +
			            "<head><title>add Room</title></head>\n" +
			            "<body>\n" +
			            "<h1>niet geautoriseerd</h1>\n"+
			            "u heeft niet de juiste rechten om dit te mogen bekijken");
			            if (admin.getUser(username) instanceof Huurder){
			            	out.println( "<a href='SearchRoom'>Terug naar de search room pagina</a>");
			            } else if (admin.getUser(username) instanceof Beheerder){
			            	out.println( "<a href='AlleUsers'>Terug naar users pagina</a>");
			            }
			            
			            out.println( "<a href='login.html'>Terug naar de loginpagina</a>");
			            out.println("</body></html>");
			}
		}else{
			out.println("<!doctype html\">\n" +
		            "<html>\n" +
		            "<head><title>add Room</title></head>\n" +
		            "<body>\n" +
		            "<h1>niet ingelocht</h1>\n"+
		            "u bent niet ingelogt"
		            + "<a href='login.html'>Terug naar de loginpagina</a>"
		            + "</body></html>");
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
		Administratie admin = (Administratie) getServletContext().getAttribute(
				"admin");
		PrintWriter out = response.getWriter();
		// controllert of je een verhuurder bent en of alles juist is ingevult
		if (username != null) {
			if (admin.getUser(username) instanceof Verhuurder) {
				if (!aantalVierkanteMeterString.equals("")) {
					aantalVierkanteMeter = Double
							.parseDouble(aantalVierkanteMeterString);
				}

				if (!huurprijsString.equals("")) {
					huurprijs = Double.parseDouble(huurprijsString);
				}

				
				String plaats = request.getParameter("plaats");
				
				if (!plaats.equals("")&&!(huurprijs<=0.0)&&!(aantalVierkanteMeter<=0.0)){
				admin.addKamer(new Kamer(huurprijs, aantalVierkanteMeter,
						plaats, (Verhuurder) admin.getUser(username)));
				response.sendRedirect("ShowRooms");
				}else{
					out.println("<!doctype html\">\n"
							+ "<html>\n"
							+ "<head><title>add Room</title></head>\n"
							+ "<body>\n"
							+ "<h1>niet goet ingevult</h1>\n"
							+ "u heeft niet de juiste dingen ingevult");	
				}
			} else {
				out.println("<!doctype html\">\n"
						+ "<html>\n"
						+ "<head><title>add Room</title></head>\n"
						+ "<body>\n"
						+ "<h1>niet geautoriseerd</h1>\n"
						+ "u heeft niet de juiste rechten om dit te mogen bekijken");
				if (admin.getUser(username) instanceof Huurder) {
					out.println("<a href='SearchRoom'>Terug naar de search room pagina</a>");
				} else if (admin.getUser(username) instanceof Beheerder) {
					out.println("<a href='AlleUsers'>Terug naar users pagina</a>");
				}

				out.println("<a href='login.html'>Terug naar de loginpagina</a>");
				out.println("</body></html>");
			}
		} else {
			out.println("<!doctype html\">\n" + "<html>\n"
					+ "<head><title>add Room</title></head>\n" + "<body>\n"
					+ "<h1>niet ingelocht</h1>\n" + "u bent niet ingelogt"
					+ "<a href='login.html'>Terug naar de loginpagina</a>"
					+ "</body></html>");
		}
	}

}
