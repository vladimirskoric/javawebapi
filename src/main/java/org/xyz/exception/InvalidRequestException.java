package org.xyz.exception;

public class InvalidRequestException extends RuntimeException{ 
    
    /**
     *
     */
    private static final long serialVersionUID = 1834319403159578521L;

    public InvalidRequestException(final String msg) {
        super(msg);
    }
}