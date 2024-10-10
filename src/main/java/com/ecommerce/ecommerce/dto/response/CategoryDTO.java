package com.ecommerce.ecommerce.dto.response;

import com.ecommerce.ecommerce.dto.FamilyDTO;
import com.ecommerce.ecommerce.dto.SubcategoryDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;

public class CategoryDTO implements Serializable {

    private Long id;

    private String name;

    private Long familyId;

    private FamilyDTO family;

    private List<SubcategoryDTO> subCategories;

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

    public Long getFamilyId(){
        return familyId;
    }

    public void setFamilyId(Long familyId){
        this.familyId = familyId;
    }

    public FamilyDTO getFamily(){
        return family;
    }

    public void setFamily(FamilyDTO family){
        this.family = family;
    }

    public List<SubcategoryDTO> getSubCategories(){
        return subCategories;
    }

    public void setSubCategories(List<SubcategoryDTO> subCategories){
        this.subCategories = subCategories;
    }
}
