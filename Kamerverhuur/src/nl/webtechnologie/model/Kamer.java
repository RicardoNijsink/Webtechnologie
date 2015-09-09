package nl.webtechnologie.model;

public class Kamer {
	private double huurprijs;
	private double aantalVierkanteMeters;
	private String plaats;
	private Verhuurder verhuurder;
	private Huurder huurder;
	
	public Kamer(double huurprijs, double aantalVierkanteMeters, String plaats, Verhuurder verhuurder) {
		this.huurprijs = huurprijs;
		this.aantalVierkanteMeters = aantalVierkanteMeters;
		this.plaats = plaats;
		this.verhuurder = verhuurder;
	}
	
	public void setHuurder(Huurder huurder){
		this.huurder = huurder;
	}

}
