package com.ecommerce.ecommerce.dto.response;

import java.io.Serializable;

public class AddressDTO implements Serializable{

    private Long id;
    private String description;
    private CityDTO city;
    private boolean isDefault;
    private String fullName;
    private String neighborhood;
    private String phone;
    private Long userId;


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

    public boolean isDefault(){
        return isDefault;
    }

    public void setDefault(boolean aDefault){
        isDefault = aDefault;
    }

    public CityDTO getCity(){
        return city;
    }

    public void setCity(CityDTO city){
        this.city = city;
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

    public Long getUserId(){
        return userId;
    }

    public void setUserId(Long userId){
        this.userId = userId;
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
