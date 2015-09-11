package nl.webtechnologie.model;

import java.util.ArrayList;
import java.util.HashSet;

public class Administratie {
	private HashSet<Gebruiker> gebruikers;
	private HashSet<Kamer> kamers;
	
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
	
	public int addGebruiker(Gebruiker gebruikerToAdd) {
		if(!gebruikers.contains(gebruikerToAdd)){
			gebruikers.add(gebruikerToAdd);
			return 1;
		}
		return 0;
	}
	
	public int addKamer(Kamer kamerToAdd) {
		if(!kamers.contains(kamerToAdd)){
			kamers.add(kamerToAdd);
			return 1;
		}
		return 0;
	}
	
	public Gebruiker getUser(String username, String password){
		for (Object objectG:gebruikers.toArray()){
			Gebruiker g = (Gebruiker) objectG;
			if (g.getName().equals(username)&&g.getPassword().equals(password)){
				return g;
			}
		}
		return null;
	}
	
	public Gebruiker getUser(String username){
		for (Object objectG:gebruikers.toArray()){
			Gebruiker g = (Gebruiker) objectG;
			if (g.getName().equals(username)){
				return g;
			}
		}
		return null;
	}
	
	public boolean isUser(String username){
		for (Object objectG:gebruikers.toArray()){
			Gebruiker g = (Gebruiker) objectG;
			if (g.getName().equals(username)){
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Gebruiker> getGebruikers() {
		ArrayList<Gebruiker> g = new ArrayList<Gebruiker>();
		for (Object objectG : gebruikers.toArray()) {
			g.add((Gebruiker) objectG);
		}
		return g;
	}
	
	public ArrayList<Kamer> getKamers() {
		ArrayList<Kamer> g = new ArrayList<Kamer>();
		for (Object objectG : kamers.toArray()) {
			g.add((Kamer) objectG);
		}
		return g;
	}

}
