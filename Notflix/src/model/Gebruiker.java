package model;

import java.util.ArrayList;

public class Gebruiker {
	private String achternaam;
	private String tussenvoegsel;
	private String voornaam;
	private String nickname;
	private String wachtwoord;
	private ArrayList<Rating> ratings;
	private String token;
	
	
	
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
	
	public String getToken(){
		if (token==null){
			genToken();
		}
		return token;
	}
	
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
