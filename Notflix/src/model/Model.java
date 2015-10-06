package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Model {
	private ArrayList<Movie> movies= new ArrayList<Movie>();
	private List<Gebruiker> gebruikers= new ArrayList<Gebruiker>();
	private ArrayList<Rating> ratings= new ArrayList<Rating>();
	
	public Model() {
		movies.add(new Movie(1235, "kaas", Date.valueOf("2015-12-12"), 9.0, "ik", "heel mooi"));
	}
	
	@XmlElement(name = "movie")
	@XmlElementWrapper(name = "movies")
	public ArrayList<Movie> getMovies() {
		return movies;
	}
	
	public void setMovies(ArrayList<Movie> movies) {
		this.movies = movies;
	}

	@XmlElement(name = "gebruiker")
	@XmlElementWrapper(name = "gebruikers")
	public List<Gebruiker> getGebruikers() {
		return gebruikers;
	}
	
	public void setGebruikers(List<Gebruiker> gebruikers) {
		this.gebruikers = gebruikers;
	}
	
	public Gebruiker getGebruiker(String nickname) {
		for(Gebruiker g : gebruikers){
			if(g.getNickname().equals(nickname)){
				return g;
			}
		}
		return null;
	}
	
	public ArrayList<Rating> getRatings() {
		return ratings;
	}
	
	public void setRatings(ArrayList<Rating> ratings) {
		this.ratings = ratings;
	}
	
	public Movie getMovie(int id) {
		Movie movie = null;
		
		for(Movie m : movies){
			if(m.getImdb_nummer() == id){
				movie = m;
			}
		}
		
		return movie;
	}
}
