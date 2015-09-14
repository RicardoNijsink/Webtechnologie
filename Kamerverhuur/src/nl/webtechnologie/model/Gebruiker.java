package nl.webtechnologie.model;

public abstract class Gebruiker {
	private String name;
	private String password;
	
	/**
	 * Constructor voor een gebruiker
	 * @param name De naam van de gebruiker
	 * @param password Het wachtwoord van de gebruiker
	 */
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
