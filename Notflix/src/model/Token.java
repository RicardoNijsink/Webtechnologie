package model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * this class is used to turn the token into xml and Json
 * @author auke
 *
 */
@XmlRootElement

class Token{
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
