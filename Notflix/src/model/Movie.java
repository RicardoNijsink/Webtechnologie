package model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Class waarin de gegevens van een film worden opgeslagen
 * @author Ricardo
 *
 */
@XmlRootElement
public class Movie {
	private static int lastvolgnummer;
	private int volgnummer;
	private String imdb_nummer;
	private String titel;
	private String release_datum;
	private double lengte;
	private String regisseur;
	private String beschrijving;
	
	public Movie() {
	}
	
	/**
	 * Constructor van een film
	 * @param imdb_nummer Het IMDB-nummer van de film
	 * @param titel De titel van de film
	 * @param release_datum De release datum van de film
	 * @param lengte De lengte van de film in minuten
	 * @param regisseur De regisseur van de film
	 * @param beschrijving De beschrijving van de film
	 */
	public Movie(String imdb_nummer, String titel, String release_datum, double lengte, String regisseur, String beschrijving) {
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

	@XmlTransient
	@JsonIgnore
	public int getVolgnummer() {
		return volgnummer;
	}
	
	public String getImdb_nummer() {
		return imdb_nummer;
	}

	public String getTitel() {
		return titel;
	}
	
	public String getRelease_datum() {
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

	public void setVolgnummer(int volgnummer) {
		this.volgnummer = volgnummer;
	}

	public void setImdb_nummer(String imdb_nummer) {
		this.imdb_nummer = imdb_nummer;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public void setRelease_datum(String release_datum) {
		this.release_datum = release_datum;
	}

	public void setLengte(double lengte) {
		this.lengte = lengte;
	}

	public void setRegisseur(String regisseur) {
		this.regisseur = regisseur;
	}

	public void setBeschrijving(String beschrijving) {
		this.beschrijving = beschrijving;
	}

}
