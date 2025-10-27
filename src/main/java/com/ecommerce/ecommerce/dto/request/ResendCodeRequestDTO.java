package com.ecommerce.ecommerce.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public class ResendCodeRequestDTO implements Serializable {

    @NotBlank
    private String oldCode;

    public String getOldCode(){
        return oldCode;
    }

    public void setOldCode(String oldCode){
        this.oldCode = oldCode;
    }
}
