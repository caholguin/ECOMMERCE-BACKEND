package com.ecommerce.ecommerce.dto.request;

import com.ecommerce.ecommerce.dto.response.AddressDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class SaveAddressDTO implements Serializable {

    @NotBlank(message = "El campo nombre es obligatorio")
    private String description;

    private boolean isDefault;

    @NotNull(message = "El campo ciudad es obligatorio")
    private Long cityId;
    @NotNull(message = "El campo usuario es obligatorio")
    private Long userId;
    @NotNull(message = "El campo nombre es obligatorio")
    private String fullName;
    @NotNull(message = "El campo barrio es obligatorio")
    private String neighborhood;
    @NotNull(message = "El campo teléfono es obligatorio")
    private String phone;

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public boolean isDefault(){
        return isDefault;
    }

    public void setDefault(boolean aDefault){
        isDefault = aDefault;
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

    public String getFullName(){
        return fullName;
    }

    public void setFullName(String fullName){
        this.fullName = fullName;
    }

    public String getNeighborhood(){
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood){
        this.neighborhood = neighborhood;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }
}
