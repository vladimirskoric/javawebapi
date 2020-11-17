package org.xyz.exception;


/**
 * This gets thrown when the username/password does not match the records in the IAM provider.
 */
public final class AuthenticationException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 8462699502943078969L;

    /**
     * This exception requires a message.
     *
     * @param msg the exception message.
     */
    public AuthenticationException(final String msg) {
        super(msg);
    }

}
