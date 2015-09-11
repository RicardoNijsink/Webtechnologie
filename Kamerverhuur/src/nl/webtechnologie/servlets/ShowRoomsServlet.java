package nl.webtechnologie.servlets;

import java.io.IOException;
import java.io.PrintWriter;

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
import nl.webtechnologie.model.Kamer;
import nl.webtechnologie.model.Verhuurder;

/**
 * Servlet implementation class ShowRoomsServlet
 */
@WebServlet("/ShowRoomsServlet")
public class ShowRoomsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowRoomsServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		                "<head><title>alle kamers</title></head>\n" +
		                "<body>\n" +
		                "<h1>alle kamers</h1>\n");
		 
		 if (username!=null){
			 
			 if (admin.getUser(username) instanceof Beheerder){
				 for (Kamer k:admin.getKamers()){
					 out.println("<br>prijs per maand: " + k.getHuurprijs());
					 out.println("<br>plaats: " + k.getPlaats());
					 out.println("<br>aantal vierkantemeters: " + k.getAantalVierkanteMeters());
					 out.println("<br>verhuurder: " + k.getVerhuurder());
					 if (k.getHuurder()==null){
						 out.println("<br>" +"geen huurder");
					 }else{
						 out.println("<br>huurder: " + k.getHuurder());
					 }
					 out.println("<br>");
				 }
			 }else if(admin.getUser(username) instanceof Verhuurder){
				 for (Kamer k:admin.getKamers()){
					 if (k.getVerhuurder()==(Verhuurder)admin.getUser(username)){
					 out.println("<br>prijs per maand: " + k.getHuurprijs());
					 out.println("<br>plaats: " + k.getPlaats());
					 out.println("<br>aantal vierkantemeters: " + k.getAantalVierkanteMeters());
					 }
					 if (k.getHuurder()==null){
						 out.println("<br>" +"geen huurder");
					 }else{
						 out.println("<br>huurder: " + k.getHuurder().getName());
					 }
					 out.println("<br>");
				 }
				 out.println("<a href=\"addRoom.html\"> add a room</a>");
			 }else if(admin.getUser(username) instanceof Huurder){
				 for (Kamer k:admin.getKamers()){
					 if (k.getHuurder()==(Huurder)admin.getUser(username))
					 out.println("<br>prijs per maand: " + k.getHuurprijs());
					 out.println("<br>plaats: " + k.getPlaats());
					 out.println("<br>aantal vierkantemeters: " + k.getAantalVierkanteMeters());
					 out.println("<br>verhuurder: " + k.getVerhuurder().getName());
					 out.println("<br>");
				 }
				 
			 }else{
				 out.println("je mag dit lekker niet doen");
				 out.println("<a href='login.html'> Back to the login</a>");
			 }
		 }else{
			 out.println("je bent niet in gelogd");
			 out.println("<a href='login.html'> Back to the login</a>");
		 }
		 
		    out.println("</body></html>");	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
