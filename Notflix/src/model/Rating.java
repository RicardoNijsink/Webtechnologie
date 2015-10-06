package model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class waarin de gegevens van een rating worden opgeslagen
 * @author Ricardo
 *
 */
@XmlRootElement
public class Rating {
	private double rating;
	private Movie movie;
	
	public Rating() {
	}
	
	/**
	 * Constructor van een rating
	 * @param rating De gegeven rating
	 * @param movie De film welke beoordeeld is
	 */
	public Rating(double rating, Movie movie) {
		this.rating = rating;
		this.movie = movie;
	}
	
	public double getRating() {
		return rating;
	}
	
	public Movie getMovie() {
		return movie;
	}
	
	public void setRating(double rating) {
		this.rating = rating;
	}
	
	public void setMovie(Movie movie) {
		this.movie = movie;
	}

}
