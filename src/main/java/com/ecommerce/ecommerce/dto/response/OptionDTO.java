package com.ecommerce.ecommerce.dto.response;

import java.io.Serializable;
import java.util.List;

public class OptionDTO implements Serializable {

    private Long id;

    private String name;

    private int type;

    private List<FeatureDTO> features;

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

    public int getType(){
        return type;
    }

    public void setType(int type){
        this.type = type;
    }

    public List<FeatureDTO> getFeatures(){
        return features;
    }

    public void setFeatures(List<FeatureDTO> features){
        this.features = features;
    }

    public static class FeatureDTO implements Serializable {
        private Long id;
        private String name;

        public FeatureDTO(Long id, String name){
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
