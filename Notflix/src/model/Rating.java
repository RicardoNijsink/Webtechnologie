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
	private String movieId;
	
	public Rating() {
	}
	
	/**
	 * Constructor van een rating
	 * @param rating De gegeven rating
	 * @param movie De film welke beoordeeld is
	 */
	public Rating(double rating, String movieId) {
		int tweeKeer = (int) (rating*2);
		System.out.println(tweeKeer);
		double returnRating = ((double)tweeKeer)/2;
		System.out.println(returnRating);
		this.rating = returnRating;
		this.movieId = movieId;
	}
	
	public double getRating() {
		return rating;
	}
	
	public void setRating(double rating) {
		int tweeKeer = (int) (rating*2);
		System.out.println(tweeKeer);
		double returnRating = ((double)tweeKeer)/2;
		System.out.println(returnRating);
		this.rating = returnRating;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	
	

}
