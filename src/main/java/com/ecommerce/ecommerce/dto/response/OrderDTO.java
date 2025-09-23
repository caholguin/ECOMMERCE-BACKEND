package com.ecommerce.ecommerce.dto.response;

import com.ecommerce.ecommerce.dto.request.SaveOrderDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class OrderDTO implements Serializable {

    private Long id;
    private String pdfPath;
    private List<CartItemDTO> content;
    private AddressDTO address;
    private Integer paymentMethod;
    private String paymentId;
    private Double total;
    private Integer status;
    private UserDTO user;

    public OrderDTO(){
    }

    public OrderDTO(Long id, String pdfPath, List<CartItemDTO> content, AddressDTO address, Integer paymentMethod, String paymentId, Double total, Integer status, UserDTO user){
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

    public List<CartItemDTO> getContent(){
        return content;
    }

    public void setContent(List<CartItemDTO> content){
        this.content = content;
    }

    public AddressDTO getAddress(){
        return address;
    }

    public void setAddress(AddressDTO address){
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

    public static class UserDTO implements Serializable {
        private Long id;
        private String name;

        public UserDTO(Long id, String name){
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

    public static class AddressDTO implements Serializable {
        private Long id;
        private String description;
        private CityDTO city;
        private String fullName;
        private String neighborhood;
        private String phone;
        private Long userId;

        @JsonProperty("default") // porque en JSON es "default"
        private Boolean isDefault;

        public AddressDTO() {}

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

        public Boolean getDefault(){
            return isDefault;
        }

        public void setDefault(Boolean aDefault){
            isDefault = aDefault;
        }



        public static class CityDTO implements Serializable {
            private Long id;
            private String name;
            private Double price;

            public CityDTO() {}

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

            public Double getPrice(){
                return price;
            }

            public void setPrice(Double price){
                this.price = price;
            }
        }
    }

}