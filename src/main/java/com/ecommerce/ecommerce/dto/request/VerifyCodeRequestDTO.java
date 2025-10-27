package com.ecommerce.ecommerce.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class VerifyCodeRequestDTO implements Serializable {

    @NotBlank
    @Size(min = 6, max = 6)
    private String code;

    public String getCode(){
        return code;
    }

    public void setCode(String code){
        this.code = code;
    }
}
