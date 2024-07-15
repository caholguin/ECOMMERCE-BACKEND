package com.ecommerce.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CategoryDTO {

    private Long id;

    @NotBlank(message = "El campo nombre no debe estar vacio")
    private String name;

    @NotNull(message = "El campo familia no debe estar vacio")
    private Long family_id;

    private FamilyDTO family;

    //private List<Subcategory> subCategories;

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

    public FamilyDTO getFamily(){
        return family;
    }

    public void setFamily(FamilyDTO family){
        this.family = family;
    }

    public Long getFamily_id(){
        return family_id;
    }

    public void setFamily_id(Long family_id){
        this.family_id = family_id;
    }
}
