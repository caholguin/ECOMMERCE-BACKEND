package com.ecommerce.ecommerce.dto.response;

public class PaymentResponseDTO {
    private Long id;
    private String status;
    private String detail;
    private String externalReference;

    public PaymentResponseDTO(Long id, String status, String detail, String externalReference){
        this.id = id;
        this.status = status;
        this.detail = detail;
        this.externalReference = externalReference;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getDetail(){
        return detail;
    }

    public void setDetail(String detail){
        this.detail = detail;
    }

    public String getExternalReference(){
        return externalReference;
    }

    public void setExternalReference(String externalReference){
        this.externalReference = externalReference;
    }
}
