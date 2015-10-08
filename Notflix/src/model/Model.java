package model;

import java.util.ArrayList;
import java.util.List;

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
	private ArrayList<Movie> movies= new ArrayList<Movie>();
	private List<Gebruiker> gebruikers= new ArrayList<Gebruiker>();
	private ArrayList<Rating> ratings= new ArrayList<Rating>();
	
	/**
	 * Constructor van het model.
	 * Hier worden hard-coded nieuwe films en gebruikers toegevoegd
	 */
	public Model() {
		movies.add(new Movie(1235, "kaas", "2015-12-12", 9.0, "ik", "heel mooi"));
		gebruikers.add(new Gebruiker("test", "test", "test", "test", "test"));
		gebruikers.get(0).getToken();
		gebruikers.get(0).addRating(new Rating(5.0, "1235"));
		movies.add(new Movie(123567, "kaas", "2015-12-12", 9.0, "ik", "heel mooi"));
		
	}
	
	/**
	 * Methode voor het ophalen van alle film uit de database
	 * @return De lijst met films
	 */
	@XmlElement(name = "movie")
	@XmlElementWrapper(name = "movies")
	public Movie[] getMovies() {
		Movie[] movieslist = new Movie[movies.size()];
		int i = 0;
		for (Movie m : movies){
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
	 * @return De lijst met gebruikers
	 */
	@XmlElement(name = "gebruiker")
	@XmlElementWrapper(name = "gebruikers")
	public List<Gebruiker> getGebruikers() {
		return gebruikers;
	}
	
	public void setGebruikers(List<Gebruiker> gebruikers) {
		this.gebruikers = gebruikers;
	}
	
	/**
	 * Methode voor het ophalen van een gebruiker aan de hand van de nickname
	 * @param nickname De nickname van de gezochte gebruiker
	 * @return De gevonden gebruiker. Anders null
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
	 * Haalt de gebruiker van de opgegeven access token op
	 * @param token De access token van de gebruiker
	 * @return De gebruiker als de access token geldig is.
	 * Anders null.
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
	public Movie getMovie(int id) {
		Movie movie = null;
		
		for(Movie m : movies){
			if(m.getImdb_nummer() == id){
				movie = m;
			}
		}
		
		return movie;
	}
	
	/**
	 * Methode voor het ophalen van de films die een rating hebben.
	 * @return Een array met de films die een rating hebben
	 */
	public Movie[] getRatedMovies() {
		ArrayList<Movie> ratedMovieslist = new ArrayList<>();
		for (Movie m : movies){
			if (hasRating(m.getImdb_nummer()+"")){
				ratedMovieslist.add(m);
			}
		}
		
		Movie[] ratedMovies = new Movie[ratedMovieslist.size()];
		int i = 0;
		for (Movie m : ratedMovieslist){
			ratedMovies[i] = m;
			i++;
		}
		return ratedMovies;
	}
	
	/**
	 * Methode om te controleren of een film bestaat
	 * @param imdbId Het IMDB-nummer van de te controleren film
	 * @return True, als de film bestaat. Anders false.
	 */
	public boolean isMovie(String imdbId){
		for (Movie m : movies){
			if ((m.getImdb_nummer()+"").equals(imdbId)){
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
		for (Gebruiker g: gebruikers){
			if (g.isRated(imdbId)){
				return true;
			}
		}
		
		return false;
	}
}
