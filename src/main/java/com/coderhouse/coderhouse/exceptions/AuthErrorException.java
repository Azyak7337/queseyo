package com.coderhouse.coderhouse.exceptions;

public class AuthErrorException extends RuntimeException {
    public AuthErrorException(String msg) {
        super(msg);
    }
}
