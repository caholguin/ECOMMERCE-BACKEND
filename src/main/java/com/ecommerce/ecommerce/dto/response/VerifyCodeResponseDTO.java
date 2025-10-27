package com.ecommerce.ecommerce.dto.response;

import java.io.Serializable;

public class VerifyCodeResponseDTO implements Serializable {

    private String resetToken;

    public VerifyCodeResponseDTO(){
    }

    public VerifyCodeResponseDTO(String resetToken){
        this.resetToken = resetToken;
    }

    public String getResetToken(){
        return resetToken;
    }

    public void setResetToken(String resetToken){
        this.resetToken = resetToken;
    }
}
