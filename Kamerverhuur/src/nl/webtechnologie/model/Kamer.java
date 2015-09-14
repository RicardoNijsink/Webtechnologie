package nl.webtechnologie.model;

public class Kamer {
	private double huurprijs;
	private double aantalVierkanteMeters;
	private String plaats;
	private Verhuurder verhuurder;
	private Huurder huurder;
	
	/**
	 * Constructor voor een kamer
	 * @param huurprijs De huurprijs van de kamer
	 * @param aantalVierkanteMeters Het aantal vierkante meters van de kamer
	 * @param plaats De plaats waar de kamer zich bevindt
	 * @param verhuurder De verhuurder van de kamer
	 */
	public Kamer(double huurprijs, double aantalVierkanteMeters, String plaats, Verhuurder verhuurder) {
		this.huurprijs = huurprijs;
		this.aantalVierkanteMeters = aantalVierkanteMeters;
		this.plaats = plaats;
		this.verhuurder = verhuurder;
	}
	
	public void setHuurder(Huurder huurder){
		this.huurder = huurder;
	}

	public double getHuurprijs() {
		return huurprijs;
	}

	public double getAantalVierkanteMeters() {
		return aantalVierkanteMeters;
	}

	public String getPlaats() {
		return plaats;
	}

	public Verhuurder getVerhuurder() {
		return verhuurder;
	}

	public Huurder getHuurder() {
		return huurder;
	}
	
	

}
