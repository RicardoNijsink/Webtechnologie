package model;

import java.util.Date;

public class Movie {
	private int volgnummer;
	private int imdb_nummer;
	private String titel;
	private Date release_datum;
	private double lengte;
	private String regisseur;
	private String beschrijving;
	
	public int getVolgnummer() {
		return volgnummer;
	}
	
	public int getImdb_nummer() {
		return imdb_nummer;
	}
	
	public String getTitel() {
		return titel;
	}
	
	public Date getRelease_datum() {
		return release_datum;
	}
	
	public double getLengte() {
		return lengte;
	}
	
	public String getRegisseur() {
		return regisseur;
	}
	
	public String getBeschrijving() {
		return beschrijving;
	}

}
