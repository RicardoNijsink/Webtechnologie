package nl.webtechnologie.model;

import java.util.ArrayList;
import java.util.HashSet;

public class Administratie {
	private HashSet<Gebruiker> gebruikers;
	private HashSet<Kamer> kamers;
	
	/**
	 * De constructor van de administratie.
	 * Hierin worden de hashsets voor de gebruiker en kamers aangemaakt.
	 * Daarnaast worden er huurders, verhuurders en een beheerder aangemaakt en kamers toegevoegd.
	 */
	public Administratie() {
		gebruikers = new HashSet<>();
		kamers = new HashSet<>();
		addGebruiker(new Huurder("kaas", "geiten"));
		addGebruiker(new Huurder("kaas1", "geiten1"));
		addGebruiker(new Huurder("kaas2", "geiten2"));
		addGebruiker(new Huurder("kaas3", "geiten3"));
		addGebruiker(new Verhuurder("kaas4", "geiten4"));
		addGebruiker(new Verhuurder("kaas5", "geiten5"));
		addGebruiker(new Beheerder("ikke", "ikke"));
		
		addKamer(new Kamer(150, 3, "hier", (Verhuurder)getUser("kaas5", "geiten5")));
		addKamer(new Kamer(150, 4, "hier", (Verhuurder)getUser("kaas4", "geiten4")));
		
	}
	
	/**
	 * Voegt een gebruiker toe aan de administratie
	 * @param gebruikerToAdd De toe te voegen gebruiker
	 * @return 1 als de gebruiker is toegevoegd. Anders 0.
	 */
	public int addGebruiker(Gebruiker gebruikerToAdd) {
		if(!gebruikers.contains(gebruikerToAdd)){
			gebruikers.add(gebruikerToAdd);
			return 1;
		}
		return 0;
	}
	
	/**
	 * Voegt een kamer toe aan de administratie
	 * @param kamerToAdd De toe te voegen kamer
	 * @return 1 als de kamer is toegevoegd. Anders 0.
	 */
	public int addKamer(Kamer kamerToAdd) {
		if(!kamers.contains(kamerToAdd)){
			kamers.add(kamerToAdd);
			return 1;
		}
		return 0;
	}
	
	/**
	 * Kijkt of er een gebruiker met de opgegeven gebruikersnaam/wachtwoordcombinatie in de administratie staat.
	 * Als deze gebruiker bestaat, wordt deze teruggegeven.
	 * @param username De gebruikersnaam van de te vinden gebruiker
	 * @param password Het wachtwoord van de te vinden gebruiker
	 * @return De gevonden gebruiker. Anders null.
	 */
	public Gebruiker getUser(String username, String password){
		for (Object objectG:gebruikers.toArray()){
			Gebruiker g = (Gebruiker) objectG;
			if (g.getName().equals(username)&&g.getPassword().equals(password)){
				return g;
			}
		}
		return null;
	}
	
	/**
	 * Kijkt of er een gebruiker met de opgegeven gebruikersnaam in de administratie staat.
	 * Als deze gebruiker bestaat, wordt deze teruggegeven.
	 * @param username De gebruikersnaam van de te vinden gebruiker
	 * @return De gevonden gebruiker. Anders null.
	 */
	public Gebruiker getUser(String username){
		for (Object objectG:gebruikers.toArray()){
			Gebruiker g = (Gebruiker) objectG;
			if (g.getName().equals(username)){
				return g;
			}
		}
		return null;
	}
	
	/**
	 * Kijkt of er een gebruiker met de opgegeven gebruikersnaam in de administratie staat.
	 * @param username De gebruikersnaam van de te vinden gebruiker.
	 * @return True als de gebruiker bestaat. Anders false.
	 */
	public boolean isUser(String username){
		for (Object objectG:gebruikers.toArray()){
			Gebruiker g = (Gebruiker) objectG;
			if (g.getName().equals(username)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Geeft een arraylist met alle gebruikers uit de administratie terug
	 * @return Een arraylist met alle gebruikers
	 */
	public ArrayList<Gebruiker> getGebruikers() {
		ArrayList<Gebruiker> g = new ArrayList<Gebruiker>();
		for (Object objectG : gebruikers.toArray()) {
			g.add((Gebruiker) objectG);
		}
		return g;
	}
	
	/**
	 * Geeft een arraylist met alle kamers uit de administratie terug
	 * @return Een arraylist met alle kamers
	 */
	public ArrayList<Kamer> getKamers() {
		ArrayList<Kamer> g = new ArrayList<Kamer>();
		for (Object objectG : kamers.toArray()) {
			g.add((Kamer) objectG);
		}
		return g;
	}

}
