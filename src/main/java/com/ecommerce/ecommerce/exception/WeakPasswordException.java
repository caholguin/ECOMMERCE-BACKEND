package com.ecommerce.ecommerce.exception;

public class WeakPasswordException extends RuntimeException {
    public WeakPasswordException(String message){
        super(message);
    }
}
