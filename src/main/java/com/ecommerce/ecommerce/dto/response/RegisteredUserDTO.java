package com.ecommerce.ecommerce.dto.response;

import java.io.Serializable;

public class RegisteredUserDTO implements Serializable {

    private String jwt;

    private UserDTO user;

    public UserDTO getUser(){
        return user;
    }

    public void setUser(UserDTO user){
        this.user = user;
    }

    public String getJwt(){
        return jwt;
    }

    public void setJwt(String jwt){
        this.jwt = jwt;
    }
}
