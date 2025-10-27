package com.ecommerce.ecommerce.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class ResetPasswordRequestDTO implements Serializable {
    @NotBlank
    private String resetToken;

    @NotBlank
    @Size(min = 8)
    private String newPassword;

    public String getResetToken(){
        return resetToken;
    }

    public void setResetToken(String resetToken){
        this.resetToken = resetToken;
    }

    public String getNewPassword(){
        return newPassword;
    }

    public void setNewPassword(String newPassword){
        this.newPassword = newPassword;
    }
}
