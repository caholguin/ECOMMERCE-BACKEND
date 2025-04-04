package com.ecommerce.ecommerce.dto.response;

import java.io.Serializable;

public class AddressDTO implements Serializable{

    private Long id;
    private String description;
    private CityDTO city;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public CityDTO getCity(){
        return city;
    }

    public void setCity(CityDTO city){
        this.city = city;
    }

    public static class CityDTO implements Serializable {
        private Long id;
        private String name;
        private int price;

        public CityDTO(Long id, String name, int price){
            this.id = id;
            this.name = name;
            this.price = price;
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

        public int getPrice(){
            return price;
        }

        public void setPrice(int price){
            this.price = price;
        }
    }
}
