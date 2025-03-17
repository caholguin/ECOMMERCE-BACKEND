package com.ecommerce.ecommerce.dto.response;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private String username;
    private String name;
    private String role;

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getRole(){
        return role;
    }

    public void setRole(String role){
        this.role = role;
    }
}
