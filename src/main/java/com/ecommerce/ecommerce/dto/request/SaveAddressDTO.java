package com.ecommerce.ecommerce.dto.request;

import com.ecommerce.ecommerce.dto.response.AddressDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class SaveAddressDTO implements Serializable {

    @NotBlank(message = "El campo nombre es obligatorio")
    private String description;
    @NotNull(message = "El campo ciudad es obligatorio")
    private Long cityId;
    @NotNull(message = "El campo usuario es obligatorio")
    private Long userId;

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public Long getCityId(){
        return cityId;
    }

    public void setCityId(Long cityId){
        this.cityId = cityId;
    }

    public Long getUserId(){
        return userId;
    }

    public void setUserId(Long userId){
        this.userId = userId;
    }
}
