package exceptions;

public class IncorrectLoginInfoException extends Exception{
	public IncorrectLoginInfoException(String errorMessage){
		super(errorMessage);
	}
}
