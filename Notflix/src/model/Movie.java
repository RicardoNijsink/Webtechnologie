package model;

import java.sql.Date;

public class Movie {
	private static int lastvolgnummer;
	private int volgnummer;
	private int imdb_nummer;
	private String titel;
	private Date release_datum;
	private double lengte;
	private String regisseur;
	private String beschrijving;
	
	
	
	public Movie(int imdb_nummer, String titel,
			Date release_datum, double lengte, String regisseur,
			String beschrijving) {
		super();
		this.volgnummer = lastvolgnummer;
		lastvolgnummer++;
		this.imdb_nummer = imdb_nummer;
		this.titel = titel;
		this.release_datum = release_datum;
		this.lengte = lengte;
		this.regisseur = regisseur;
		this.beschrijving = beschrijving;
	}

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
