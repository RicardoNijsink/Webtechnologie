package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Rating {
	private double rating;
	private Movie movie;
	
	public Rating() {
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
