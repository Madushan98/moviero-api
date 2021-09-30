package com.webproject.api.exceptions;

public class FileStorageException extends RuntimeException {
   
	
	private static final long serialVersionUID = -2937174127640225487L;

	public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}