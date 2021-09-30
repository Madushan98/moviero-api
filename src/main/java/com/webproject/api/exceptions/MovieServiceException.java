package com.webproject.api.exceptions;

public class MovieServiceException extends RuntimeException{
	 


	
	private static final long serialVersionUID = 2197721430322007783L;

	public MovieServiceException(String message)
	{
		super(message);
	}
	
	  public MovieServiceException(String message, Throwable cause) {
	        super(message, cause);
	    }
}
