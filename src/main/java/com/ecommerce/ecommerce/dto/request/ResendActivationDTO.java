package com.ecommerce.ecommerce.dto.request;

import java.io.Serializable;

public class ResendActivationDTO implements Serializable {

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
