package model;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class waarin alle ratings, gebruikers en films worden opgeslagen.
 * @author Ricardo
 *
 */
@XmlRootElement
public class Model {
	private ArrayList<Movie> movies = new ArrayList<Movie>();
	private ArrayList<Gebruiker> gebruikers = new ArrayList<Gebruiker>();
	private ArrayList<Rating> ratings = new ArrayList<Rating>();
	
	/**
	 * Constructor van het model.
	 * Hier worden hard-coded nieuwe films en gebruikers toegevoegd
	 */
	public Model() {
		movies.add(new Movie("tt0231846", "kaas", "2015-12-12", 5.0, "ik", "heel mooi"));
		gebruikers.add(new Gebruiker("test", "test", "test", "test", "test"));
		gebruikers.get(0).addRating(new Rating(5.0, "tt0231846"));
		
		movies.add(new Movie("tt2488496", "Star Wars Episode VII - The Force Awakens",
				"2015-12-16", 136.0, "J.J. Abrams", "A continuation of the saga created by George Lucas set thirty years after Star Wars: Episode VI - Return of the Jedi (1983)."));
		
		movies.add(new Movie("tt2379713", "Spectre", "2015-10-29", 148.0, "S. Mendes", "A cryptic message from Bond's past sends him on a trail to uncover a sinister organization. "
				+ "While M battles political forces to keep the secret service alive, Bond peels back the layers of deceit to reveal the terrible truth behind SPECTRE."));
		
		movies.add(new Movie("tt1355683", "Black Mass", "2015-10-15", 122.0, "S. Cooper", "The true story of Whitey Bulger, the brother of a state senator"
				+ " and the most infamous violent criminal in the history of South Boston, who became an FBI informant to take down a Mafia family invading his turf."));
		
		movies.add(new Movie("tt2679042", "Hitman: Agent 47", "2015-09-03", 96.0, "A. Bach", "An assassin teams up with a woman to help her find her father and uncover the mysteries of her ancestry."));
		
		movies.add(new Movie("tt1340138", "Terminator Genisys", "2015-07-09", 126.0, "A. Taylor", "When John Connor, leader of the human resistance, sends Sgt. Kyle Reese back to 1984 to protect Sarah Connor"
				+ " and safeguard the future, an unexpected turn of events creates a fractured timeline."));
		
		movies.add(new Movie("tt0478970", "Ant-Man", "2015-07-23", 117.0, "P. Reed", "Armed with a super-suit with the astonishing ability to shrink in scale but increase in strength,"
				+ " cat burglar Scott Lang must embrace his inner hero and help his mentor, Dr. Hank Pym, plan and pull off a heist that will save the world."));
		
		movies.add(new Movie("tt2381249", "Mission: Impossible - Rogue Nation", "2015-07-30", 131.0, "C. McQuarrie", "Ethan and team take on their most impossible mission yet, eradicating the Syndicate"
				+ " - an International rogue organization as highly skilled as they are, committed to destroying the IMF."));
		
		movies.add(new Movie("tt0369610", "Jurassic World", "2015-06-11", 124.0, "C. Trevorrow", "A new theme park is built on the original site of Jurassic Park. Everything is going well until the park's newest attraction-"
				+ "-a genetically modified giant stealth killing machine--escapes containment and goes on a killing spree."));
		
		movies.add(new Movie("tt2395427", "Avengers: Age of Ultron", "2015-04-22", 141.0, "J. Whedon", "When Tony Stark and Bruce Banner try to jump-start a dormant peacekeeping program called Ultron,"
				+ " things go horribly wrong and it's up to Earth's Mightiest Heroes to stop the villainous Ultron from enacting his terrible plans."));
		
		gebruikers.add(new Gebruiker("test1", "test", "test", "test1", "test"));
		gebruikers.add(new Gebruiker("test2", "test", "test", "test2", "test"));
		gebruikers.add(new Gebruiker("test3", "test", "test", "test3", "test"));
		gebruikers.add(new Gebruiker("test4", "test", "test", "test4", "test"));
		gebruikers.add(new Gebruiker("test5", "test", "test", "test5", "test"));
	}
	
	/**
	 * Methode voor het ophalen van alle film uit de database.
	 * Zet de arraylist met films om naar een array.
	 * @return De lijst met films
	 */
	@XmlElement(name = "movie")
	@XmlElementWrapper(name = "movies")
	public Movie[] getMovies() {
		Movie[] movieslist = new Movie[movies.size()];
		int i = 0;
		for(Movie m : movies){
			movieslist[i] = m;
			i++;
		}
		return movieslist;
	}
	
	public void setMovies(ArrayList<Movie> movies) {
		this.movies = movies;
	}

	/**
	 * Methode voor het ophalen van alle gebruikers uit de database
	 * Zet de arraylist met gebruikers om naar een array
	 * @return De lijst met gebruikers
	 */
	@XmlElement(name = "gebruiker")
	@XmlElementWrapper(name = "gebruikers")
	public Gebruiker[] getGebruikers() {
		Gebruiker[] gebruikerslist = new Gebruiker[gebruikers.size()];
		int i = 0;
		for (Gebruiker g : gebruikers){
			gebruikerslist[i] = g;
			i++;
		}
		return gebruikerslist;
	}
	
	public void setGebruikers(ArrayList<Gebruiker> gebruikers) {
		this.gebruikers = gebruikers;
	}
	
	/**
	 * Methode voor het ophalen van een gebruiker aan de hand van de nickname
	 * @param nickname De nickname van de gezochte gebruiker
	 * @return De gevonden gebruiker. Anders null.
	 */
	public Gebruiker getGebruiker(String nickname) {
		for(Gebruiker g : gebruikers){
			if(g.getNickname().equals(nickname)){
				return g;
			}
		}
		return null;
	}
	
	/**
	 * Methode voor het ophalen van de gebruiker bij de opgegeven access token
	 * @param token De access token van de gebruiker
	 * @return De gebruiker als de access token geldig is. Anders null.
	 */
	public Gebruiker getGebruikerByToken(String token) {
		for(Gebruiker g : gebruikers){
			if(g.getToken().equals(token)){
				return g;
			}
		}
		return null;
	}
	
	/**
	 * Methode voor het controleren of een access token bestaat
	 * @param token De te controleren access token
	 * @return True, als de access token bestaat. Anders false.
	 */
	public boolean isToken(String token) {
		for(Gebruiker g : gebruikers){
			if(g.getToken().equals(token)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Methode voor het toevoegen van een gebruiker aan het model
	 * @param voornaam De voornaam van de toe te voegen gebruiker
	 * @param tussenvoegsel Het tussenvoegsel van het toe te voegen gebruiker
	 * @param achternaam De achternaam van de toe te voegen gebruiker
	 * @param nickname De nickname van de toe te voegen gebruiker
	 * @param wachtwoord Het wachtwoord van de toe te voegen gebruiker
	 * @return De nieuw aangemaakte gebruiker. Als de gebruiker al bestaat null.
	 */
	public Gebruiker addGebruiker(String voornaam, String tussenvoegsel, String achternaam, String nickname, String wachtwoord){
		if(getGebruiker(nickname) != null){
			return null;
		}
		
		gebruikers.add(new Gebruiker(voornaam, tussenvoegsel, achternaam, nickname, wachtwoord));
		
		return new Gebruiker(voornaam, tussenvoegsel, achternaam, nickname, wachtwoord);
	}
	
	/**
	 * Methode voor het ophalen van alle ratings uit de database
	 * @return De lijst met ratings
	 */
	public ArrayList<Rating> getRatings() {
		return ratings;
	}
	
	public void setRatings(ArrayList<Rating> ratings) {
		this.ratings = ratings;
	}
	
	/**
	 * Methode voor het ophalen van een film aan de hand van het IMDB-nummer
	 * @param id Het IMDB-nummer van de gezochte film
	 * @return De gevonden film. Anders null
	 */
	public Movie getMovie(String id) {
		Movie movie = null;
		
		for(Movie m : movies){
			if(m.getImdb_nummer().equals(id)){
				movie = m;
			}
		}
		
		return movie;
	}
	
	/**
	 * Methode voor het ophalen van de films die een rating hebben.
	 * Zet de arraylist met films om naar een array.
	 * @return Een array met de films die een rating hebben
	 */
	public Movie[] getRatedMovies() {
		ArrayList<Movie> ratedMovieslist = new ArrayList<>();
		for (Movie m : movies){
			if(hasRating(m.getImdb_nummer())){
				m.setGemiddeldeRating(getRating(m.getImdb_nummer()));
				ratedMovieslist.add(m);
			}
		}
		
		Movie[] ratedMovies = new Movie[ratedMovieslist.size()];
		int i = 0;
		for(Movie m : ratedMovieslist){
			ratedMovies[i] = m;
			i++;
		}
		return ratedMovies;
	}
	
	public Movie[] getTitelContainsMovie(String keyWords){
		ArrayList<Movie> gevondenlist = new ArrayList<>();
		
		for (Movie m :movies){
			System.out.println("movie titel = "+m.getTitel());
			if (m.getTitel().contains(keyWords)){
				gevondenlist.add(m);
			}
		}
		Movie[] gevondenArray = new Movie[gevondenlist.size()];
		int i = 0;
		System.out.println(keyWords);
		for (Movie m :gevondenlist){
			System.out.println("movie titel = "+m.getTitel());
			gevondenArray[i] = m;
			i++;
		}
		return gevondenArray;
		
	}
	
	/**
	 * Methode om de gemiddelde rating van een film te bepalen
	 * @param imdbId Het IMDB-nummer van de film
	 * @return De gemiddelde rating van de film
	 */
	public double getRating(String imdbId) {
		double totalRating = 0;
		int ratingCount = 0;
		
		for(Gebruiker g : gebruikers){
			for(Rating r : g.getRatings()){
				if(r.getMovieId().equals(imdbId)){
					totalRating += r.getRating();
					ratingCount++;
				}
			}
		}
		
		return (double)(totalRating / ratingCount);
	}
	
	/**
	 * Methode om te controleren of een film bestaat
	 * @param imdbId Het IMDB-nummer van de te controleren film
	 * @return True, als de film bestaat. Anders false.
	 */
	public boolean isMovie(String imdbId){
		for(Movie m : movies){
			if((m.getImdb_nummer()).equals(imdbId)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Methode om te controleren of een film een rating heeft
	 * @param imdbId Het IMDB-nummer van de te controleren film
	 * @return True, als de film een rating heeft. Anders false.
	 */
	private boolean hasRating(String imdbId){
		for(Gebruiker g: gebruikers){
			if(g.isRated(imdbId)){
				return true;
			}
		}
		
		return false;
	}
}
