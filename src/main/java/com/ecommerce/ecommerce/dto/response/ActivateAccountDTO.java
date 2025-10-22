package com.ecommerce.ecommerce.dto.response;

import java.io.Serializable;

public class ActivateAccountDTO implements Serializable {

    private String message;

    public ActivateAccountDTO(){
    }

    public ActivateAccountDTO(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }
}
