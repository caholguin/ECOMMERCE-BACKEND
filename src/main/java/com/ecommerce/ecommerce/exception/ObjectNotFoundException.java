package com.ecommerce.ecommerce.exception;

public class ObjectNotFoundException extends RuntimeException {

    private final String objectNotFoundName;

    public ObjectNotFoundException(String objectNotFoundName){
        this.objectNotFoundName = objectNotFoundName;
    }

    @Override
    public String getMessage(){

        String message = super.getMessage();

        if(message == null) return "";

        return message.concat("object not found").concat(this.objectNotFoundName);
    }

}
