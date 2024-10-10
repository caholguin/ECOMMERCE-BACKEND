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

        public CategoryDTO(Long id, String name){
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
