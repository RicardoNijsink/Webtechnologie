package model;

import java.util.ArrayList;

public class Model {
	private ArrayList<Movie> movies;
	private ArrayList<Gebruiker> gebruikers;
	private ArrayList<Rating> ratings;
	
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
