package com.ecommerce.ecommerce.dto.request;

import java.io.Serializable;

public class SaveCityDTO implements Serializable {

    private String name;
    private int price;

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
