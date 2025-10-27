package com.ecommerce.ecommerce.dto.response;

import java.io.Serializable;

public class MessageResponseDTO implements Serializable {
    private String message;

    public MessageResponseDTO(){
    }

    public MessageResponseDTO(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }
}
