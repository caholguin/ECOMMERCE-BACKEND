package com.ecommerce.ecommerce.dto.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class SaveFeatureDTO implements Serializable {

    private Long id;

    @NotBlank(message = "El campo nombre es obligatorio")
    private String name;

    @NotNull(message = "El campo opción es obligatorio")
    private Long optionId;

    public SaveFeatureDTO(Long id, String name, Long optionId){
        this.id = id;
        this.name = name;
        this.optionId = optionId;
    }

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

    public Long getOptionId(){
        return optionId;
    }

    public void setOptionId(Long optionId){
        this.optionId = optionId;
    }
}
