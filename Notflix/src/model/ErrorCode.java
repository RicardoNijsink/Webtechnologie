package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;



/**
 * this class is used to return error messages in xml or JSON
 * @author auke
 *
 */
@XmlRootElement
public class ErrorCode {
	private String NICKNAME_WRONG= "1: nickname wrong";
	private String PASSWORD_WRONG= "2: password wrong";
	private String EMPTY_FIELDS = "3: some fields are empty";
	private String MOVIE_DOES_NOT_EXIST = "4: movie not found";
	private String RATING_OUT_OF_RANGE= "5: rating is either higher than 5 or lower than 0.5";
	private String TOKEN_INVALID = "6: token is invalid";
	private String TOKEN_MISSING = "7: no token was recieved";
	private String OBJECT_ALREADY_EXISTS = "8: the object you tried to add aleady exists";
	private String NO_SUCH_USER = "9: no such user exists";
	private String error = "test";
	
	public ErrorCode(){}

	@JsonIgnore
	public String getNICKNAME_WRONG() {
		return NICKNAME_WRONG;
	}
	@JsonIgnore
	public String getPASSWORD_WRONG() {
		return PASSWORD_WRONG;
	}
	@JsonIgnore
	public String getEMPTY_FIELDS() {
		return EMPTY_FIELDS;
	}
	@JsonIgnore
	public String getMOVIE_DOES_NOT_EXIST() {
		return MOVIE_DOES_NOT_EXIST;
	}
	@JsonIgnore
	public String getRATING_OUT_OF_RANGE() {
		return RATING_OUT_OF_RANGE;
	}
	@JsonIgnore
	public String getTOKEN_INVALID() {
		return TOKEN_INVALID;
	}
	@JsonIgnore
	public String getTOKEN_MISSING() {
		return TOKEN_MISSING;
	}
	@JsonIgnore
	public String getOBJECT_ALREADY_EXISTS() {
		return OBJECT_ALREADY_EXISTS;
	}
	@JsonIgnore
	public String getNO_SUCH_USER() {
		return NO_SUCH_USER;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}


}
