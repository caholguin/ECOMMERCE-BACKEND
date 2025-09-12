package com.ecommerce.ecommerce.dto.response;

import java.io.Serializable;

public class OrderDTO implements Serializable {

    private Long id;
    private String pdfPath;
    private String content;
    private String address;
    private Integer paymentMethod;
    private String paymentId;
    private Double total;
    private Integer status;
    private UserDTO user;

    public OrderDTO(){
    }

    public OrderDTO(Long id, String pdfPath, String content, String address, Integer paymentMethod, String paymentId, Double total, Integer status, UserDTO user){
        this.id = id;
        this.pdfPath = pdfPath;
        this.content = content;
        this.address = address;
        this.paymentMethod = paymentMethod;
        this.paymentId = paymentId;
        this.total = total;
        this.status = status;
        this.user = user;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getPdfPath(){
        return pdfPath;
    }

    public void setPdfPath(String pdfPath){
        this.pdfPath = pdfPath;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public Integer getPaymentMethod(){
        return paymentMethod;
    }

    public void setPaymentMethod(Integer paymentMethod){
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentId(){
        return paymentId;
    }

    public void setPaymentId(String paymentId){
        this.paymentId = paymentId;
    }

    public Double getTotal(){
        return total;
    }

    public void setTotal(Double total){
        this.total = total;
    }

    public Integer getStatus(){
        return status;
    }

    public void setStatus(Integer status){
        this.status = status;
    }

    public UserDTO getUser(){
        return user;
    }

    public void setUser(UserDTO user){
        this.user = user;
    }
}