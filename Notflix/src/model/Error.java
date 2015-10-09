package model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class voor het bijhouden en weergeven van error messages bij foutcodes
 * @author Auke
 *
 */
@XmlRootElement
public class Error {
	private static final String PASSWORD_WRONG = "Het opgegeven wachtwoord is incorrect";
	private static final String EMPTY_FIELDS = "Niet alle verplichte velden zijn correct ingevuld";
	private static final String TOKEN_INVALID = "De access token is incorrect";
	private static final String OBJECT_ALREADY_EXISTS = "Het opgegeven object bestaat al";
	private static final String OBJECT_DOES_NOT_EXIST = "Het opgegeven object bestaat niet";
	private String error_message = "";
	
	public Error(){}
	
	/**
	 * Constructor voor een error
	 * @param errorMessage Het bericht van de error
	 */
	public Error(String errorMessage) {
		this.error_message = errorMessage;
	}

	public String getError() {
		return error_message;
	}

	public void setError(String error) {
		this.error_message = error;
	}
	
	/**
	 * Methode voor het weergeven van een error message bij de bijbehorende foutcode
	 * @param foutCode De gegeven HTTP-foutcode
	 * @return Een error met een error message
	 */
	public Error getErrorMessage(int foutCode) {
		Error errorCode = new Error();
		
		if(foutCode == 400){
			return new Error(EMPTY_FIELDS);
		}
		else if(foutCode == 401){
			return new Error(TOKEN_INVALID);
		}
		else if(foutCode == 404){
			return new Error(OBJECT_DOES_NOT_EXIST);
		}
		else if(foutCode == 409){
			return new Error(OBJECT_ALREADY_EXISTS);
		}
		else if(foutCode == 422){
			return new Error(PASSWORD_WRONG);
		}
		return errorCode;
	}
}
