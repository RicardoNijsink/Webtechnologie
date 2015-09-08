package nl.webtechnologie.model;

public class Kamer {
	private double huurprijs;
	private double aantalVierkanteMeters;
	private String plaats;
	
	public Kamer(double huurprijs, double aantalVierkanteMeters, String plaats) {
		this.huurprijs = huurprijs;
		this.aantalVierkanteMeters = aantalVierkanteMeters;
		this.plaats = plaats;
	}

}
