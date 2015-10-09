package model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class voor het opslaan en ophalen van de access token
 * @author Auke
 *
 */
@XmlRootElement

public class Token {
	private String token;

	public Token() {
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
