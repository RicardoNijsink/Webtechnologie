package model;

import java.sql.Date;
import java.util.ArrayList;

public class Model {
	private ArrayList<Movie> movies= new ArrayList<Movie>();
	private ArrayList<Gebruiker> gebruikers= new ArrayList<Gebruiker>();
	private ArrayList<Rating> ratings= new ArrayList<Rating>();
	
	public Model() {
		movies.add(new Movie(1235, "kaas", Date.valueOf("2015-12-12"), 9.0, "ik", "heel mooi" ));
		
	}
	
	public ArrayList<Movie> getMovies() {
		return movies;
	}
	
	public ArrayList<Gebruiker> getGebruikers() {
		return gebruikers;
	}
	
	public ArrayList<Rating> getRatings() {
		return ratings;
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
