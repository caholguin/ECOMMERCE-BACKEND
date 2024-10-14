package com.ecommerce.ecommerce.dto.response;

import java.io.Serializable;
import java.util.List;

public class CategoryDTO implements Serializable {

    private Long id;

    private String name;

    private String icon;

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

    public String getIcon(){
        return icon;
    }

    public void setIcon(String icon){
        this.icon = icon;
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


    public static class FamilyDTO implements Serializable {
        private Long id;
        private String name;

        public FamilyDTO(Long id, String name){
            this.id = id;
            this.name = name;
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
    }

    public static class SubcategoryDTO implements Serializable {
        private Long id;
        private String name;

        public SubcategoryDTO(Long id, String name){
            this.id = id;
            this.name = name;
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
    }
}
