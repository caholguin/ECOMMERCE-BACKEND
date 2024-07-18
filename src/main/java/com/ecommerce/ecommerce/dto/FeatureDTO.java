package com.ecommerce.ecommerce.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FeatureDTO {

    private Long id;

    @NotBlank(message = "El campo nombre es obligatorio")
    private String value;

    @NotBlank(message = "El campo nombre es obligatorio")
    private String description;

    @NotNull(message = "El campo type es obligatorio")
    private Long optionId;

    private OptionDTO option;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getValue(){
        return value;
    }

    public void setValue(String value){
        this.value = value;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public Long getOptionId(){
        return optionId;
    }

    public void setOptionId(Long optionId){
        this.optionId = optionId;
    }

    public OptionDTO getOption(){
        return option;
    }

    public void setOption(OptionDTO option){
        this.option = option;
    }
}
