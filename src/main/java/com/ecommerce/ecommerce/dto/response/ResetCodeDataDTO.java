package com.ecommerce.ecommerce.dto.response;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ResetCodeDataDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String email;
    private Long userId;
    private String code;
    private LocalDateTime createdAt;
    private int attempts;

    public ResetCodeDataDTO(){
    }

    public ResetCodeDataDTO(String email, Long userId, String code, LocalDateTime createdAt, int attempts){
        this.email = email;
        this.userId = userId;
        this.code = code;
        this.createdAt = createdAt;
        this.attempts = attempts;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public Long getUserId(){
        return userId;
    }

    public void setUserId(Long userId){
        this.userId = userId;
    }

    public String getCode(){
        return code;
    }

    public void setCode(String code){
        this.code = code;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }

    public int getAttempts(){
        return attempts;
    }

    public void setAttempts(int attempts){
        this.attempts = attempts;
    }
}


