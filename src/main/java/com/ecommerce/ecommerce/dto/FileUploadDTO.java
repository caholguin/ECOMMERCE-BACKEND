package com.ecommerce.ecommerce.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class FileUploadDTO {

    private List<MultipartFile> files;
    private String type;
    private Long id;

    public List<MultipartFile> getFiles(){
        return files;
    }

    public void setFiles(List<MultipartFile> files){
        this.files = files;
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
