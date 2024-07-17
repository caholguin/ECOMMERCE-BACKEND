package com.ecommerce.ecommerce.dto;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadDTO {

    private MultipartFile file;
    private String type;
    private Long id;

    public MultipartFile getFile(){
        return file;
    }

    public void setFile(MultipartFile file){
        this.file = file;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }
}
