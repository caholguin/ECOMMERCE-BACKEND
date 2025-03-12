package com.ecommerce.ecommerce.dto.response;

import java.io.Serializable;

public class RegisteredUserDTO implements Serializable {

    private Long id;

    private String name;

    private String username;

    private String email;

    private String role;

    private String jwt;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getRole(){
        return role;
    }

    public void setRole(String role){
        this.role = role;
    }

    public String getJwt(){
        return jwt;
    }

    public void setJwt(String jwt){
        this.jwt = jwt;
    }
}
