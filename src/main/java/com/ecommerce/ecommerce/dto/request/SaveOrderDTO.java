package com.ecommerce.ecommerce.dto.request;

import com.ecommerce.ecommerce.dto.response.UserDTO;

import java.io.Serializable;
import java.util.List;

public class SaveOrderDTO implements Serializable {
    private String pdfPath;
    private List<CartItemDTO> content;
    private String address;
    private Integer paymentMethod;
    private String paymentId;
    private Double total;
    private Long userId;

    public SaveOrderDTO(){
    }

    public SaveOrderDTO(String pdfPath, List<CartItemDTO> content, String address, Integer paymentMethod, String paymentId, Double total, Long userId){
        this.pdfPath = pdfPath;
        this.content = content;
        this.address = address;
        this.paymentMethod = paymentMethod;
        this.paymentId = paymentId;
        this.total = total;
        this.userId = userId;
    }

    public String getPdfPath(){
        return pdfPath;
    }

    public void setPdfPath(String pdfPath){
        this.pdfPath = pdfPath;
    }

    public List<CartItemDTO> getContent(){
        return content;
    }

    public void setContent(List<CartItemDTO> content){
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

    public Long getUserId(){
        return userId;
    }

    public void setUserId(Long userId){
        this.userId = userId;
    }


    public static class CartItemDTO implements Serializable {
        private Long variantId;
        private Long productId;
        private String productName;
        private String image;
        private List<String> features;
        private Integer amount;
        private Double unitPrice;
        private Double subtotal;

        public CartItemDTO(){
        }

        public CartItemDTO(Long variantId, Long productId, String productName, String image, List<String> features, Integer amount, Double unitPrice, Double subtotal){
            this.variantId = variantId;
            this.productId = productId;
            this.productName = productName;
            this.image = image;
            this.features = features;
            this.amount = amount;
            this.unitPrice = unitPrice;
            this.subtotal = subtotal;
        }

        public Long getVariantId(){
            return variantId;
        }

        public void setVariantId(Long variantId){
            this.variantId = variantId;
        }

        public Long getProductId(){
            return productId;
        }

        public void setProductId(Long productId){
            this.productId = productId;
        }

        public String getProductName(){
            return productName;
        }

        public void setProductName(String productName){
            this.productName = productName;
        }

        public String getImage(){
            return image;
        }

        public void setImage(String image){
            this.image = image;
        }

        public List<String> getFeatures(){
            return features;
        }

        public void setFeatures(List<String> features){
            this.features = features;
        }

        public Integer getAmount(){
            return amount;
        }

        public void setAmount(Integer amount){
            this.amount = amount;
        }

        public Double getUnitPrice(){
            return unitPrice;
        }

        public void setUnitPrice(Double unitPrice){
            this.unitPrice = unitPrice;
        }

        public Double getSubtotal(){
            return subtotal;
        }

        public void setSubtotal(Double subtotal){
            this.subtotal = subtotal;
        }
    }


}
