package com.ecommerce.ecommerce.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public class ForgotPasswordRequestDTO implements Serializable {
    @NotBlank
    private String email;

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }
}
