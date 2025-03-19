package com.ecommerce.ecommerce.dto.response;

import java.io.Serializable;

public class LoginResponseDTO implements Serializable {

    private String jwt;

    public String getJwt(){
        return jwt;
    }

    public void setJwt(String jwt){
        this.jwt = jwt;
    }
}
