package exceptions;

public class PasswordsDontMatchException extends Exception{
	public PasswordsDontMatchException(String errorMessage){
		super(errorMessage);
	}
}
