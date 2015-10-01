package model;

import java.util.ArrayList;

public class Gebruiker {
	private String achternaam;
	private String tussenvoegsel;
	private String voornaam;
	private String nickname;
	private String wachtwoord;
	private ArrayList<Rating> ratings;
	
	public String getAchternaam() {
		return achternaam;
	}
	
	public String getTussenvoegsel() {
		return tussenvoegsel;
	}
	
	public String getVoornaam() {
		return voornaam;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public String getWachtwoord() {
		return wachtwoord;
	}
	
	public ArrayList<Rating> getRatings() {
		return ratings;
	}
}
