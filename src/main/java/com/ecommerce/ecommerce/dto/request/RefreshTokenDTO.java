package com.ecommerce.ecommerce.dto.request;

import java.io.Serializable;

public class RefreshTokenDTO implements Serializable {
    private String token;

    public String getToken(){
        return token;
    }

    public void setToken(String token){
        this.token = token;
    }
}
