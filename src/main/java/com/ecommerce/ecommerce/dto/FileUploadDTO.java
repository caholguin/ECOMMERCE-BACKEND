package com.ecommerce.ecommerce.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class FileUploadDTO {

    private List<MultipartFile> files;
    private String bucket;
    private Long id;

    public List<MultipartFile> getFiles(){
        return files;
    }

    public void setFiles(List<MultipartFile> files){
        this.files = files;
    }

    public String getBucket(){
        return bucket;
    }

    public void setBucket(String bucket){
        this.bucket = bucket;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }
}
