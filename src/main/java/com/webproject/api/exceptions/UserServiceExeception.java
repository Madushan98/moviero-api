package com.webproject.api.exceptions;

public class UserServiceExeception extends RuntimeException{
	 


	
	private static final long serialVersionUID = 2197721430322007783L;

	public UserServiceExeception(String message)
	{
		super(message);
	}
	
	  public UserServiceExeception(String message, Throwable cause) {
	        super(message, cause);
	    }
}
