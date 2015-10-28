package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Movie")

public class ReturnMovie extends Movie {
	private boolean rated = false;
	
	public ReturnMovie() {
		super();
	}

	public boolean isRated() {
		return rated;
	}

	public void setRated(boolean rated) {
		this.rated = rated;
	}
	
	
	
	
	
}
