package com.ecommerce.ecommerce.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class SaveUserDTO implements Serializable {

    @Size(min = 4)
    private String username;

    @Size(min = 8)
    private String password;

    @Size(min = 8)
    private String repeatedPassword;

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getRepeatedPassword(){
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword){
        this.repeatedPassword = repeatedPassword;
    }
}
