package nl.webtechnologie.model;

import java.util.HashSet;

public class Administratie {
	private HashSet<Gebruiker> gebruikers;
	private HashSet<Kamer> kamers;
	
	public Administratie() {
		gebruikers = new HashSet<>();
		kamers = new HashSet<>();
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

}
