package model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Class waarin de gegevens van een gebruiker worden opgeslagen
 * @author Ricardo
 *
 */
@XmlRootElement
public class Gebruiker {
	private String achternaam = "";
	private String tussenvoegsel = "";
	private String voornaam = "";
	private String nickname = "";
	private String wachtwoord = "";
	private ArrayList<Rating> ratings = new ArrayList<>();
	private String token;
	
	public Gebruiker() {
	}
	
	/**
	 * Constructor van een gebruiker
	 * @param voornaam De voornaam van de gebruiker
	 * @param achternaam De achternaam van de gebruiker
	 * @param nickname De nickname van de gebruiker
	 */
	public Gebruiker(String voornaam, String tussenvoegsel, String achternaam, String nickname, String wachtwoord) {
		this.voornaam = voornaam;
		this.tussenvoegsel = tussenvoegsel;
		this.achternaam = achternaam;
		this.nickname = nickname;
		this.wachtwoord = wachtwoord;
	}
	
	public String getAchternaam() {
		return achternaam;
	}
	
	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}
	
	public String getTussenvoegsel() {
		return tussenvoegsel;
	}
	
	public void setTussenvoegsel(String tussenvoegsel) {
		this.tussenvoegsel = tussenvoegsel;
	}
	
	public String getVoornaam() {
		return voornaam;
	}
	
	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	@XmlTransient
	@JsonIgnore
	public String getWachtwoord() {
		return wachtwoord;
	}
	
	public ArrayList<Rating> getRatings() {
		return ratings;
	}
	
	public void setRatings(ArrayList<Rating> ratings) {
		this.ratings = ratings;
	}
	
	public String getToken(){
		if (token==null){
			genToken();
		}
		return token;
	}
	
	/**
	 * Methode voor het genereren van een logintoken van een gebruiker
	 */
	public void genToken(){
		token = nickname;
		
		for (int i = 0; i < 15; i++) {
			int random = (int) (Math.random() * 50);
			random = random + 65;
			
			if (random > 90) {
				random = random + 6;
			
			}
			char charac = (char) random;
			token = token + charac;
		}
	}
	
}
