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
	 * @param rating De ratingwaarde
	 * @param movieId Het IMDB-nummer van de beoordeelde film
	 */
	public Rating(double rating, String movieId) {
		int tweeKeer = (int) (rating*2);
		double returnRating = ((double)tweeKeer)/2;
		
		this.rating = returnRating;
		this.movieId = movieId;
	}
	
	public double getRating() {
		return rating;
	}
	
	/**
	 * Methode om de ratingwaarde te veranderen
	 * @param rating De nieuwe ratingwaarde
	 */
	public void setRating(double rating) {
		int tweeKeer = (int) (rating*2);
		double returnRating = ((double)tweeKeer)/2;
		this.rating = returnRating;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
}
