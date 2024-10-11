package com.ecommerce.ecommerce.dto.response;

import java.io.Serializable;
import java.util.List;

public class FamilyDTO implements Serializable{

    private Long id;

    private String name;

    List<CategoryDTO> categories;

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

    public List<CategoryDTO> getCategories(){
        return categories;
    }

    public void setCategories(List<CategoryDTO> categories){
        this.categories = categories;
    }


    public static class CategoryDTO implements Serializable {
        private Long id;
        private String name;
        private String icon;

        public CategoryDTO(Long id, String name, String icon){
            this.id = id;
            this.name = name;
            this.icon = icon;
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

        public String getIcon(){
            return icon;
        }

        public void setIcon(String icon){
            this.icon = icon;
        }
    }
}
