package nl.webtechnologie.model;

public abstract class Gebruiker {
	private String name;
	private String password;
	
	public Gebruiker(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}
	
	
}
